/*
 * Copyright 2005-2007 jWic group (http://www.jwic.de)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * de.jwic.sourceviewer.model.reader.HtmlBodyContentReader
 * Created on May 4, 2007
 * $Id: FilesContentReader.java,v 1.6 2007/05/09 08:43:38 aroncotrau Exp $
 */
package de.jwic.sourceviewer.model.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import de.jwic.sourceviewer.model.JavaElement;

/**
 * This class holds static methods for reading various important things from
 * several types of files.
 * 
 * @author Aron Cotrau
 */
public class FilesContentReader {

	/**
	 * @param htmlFile
	 * @return the content between the "body" tags from the html file.
	 * @throws IOException
	 */
	static public String getBodyContent(File htmlFile) throws IOException {
		String result = "";

		if (null != htmlFile) {
			BufferedReader reader = new BufferedReader(new FileReader(htmlFile));
			String line = reader.readLine();
			StringBuffer bodyContent = new StringBuffer();

			while (null != line) {
				int idx = line.indexOf("<body>");
				if (idx != -1) {
					String tempLine = line.substring(idx + "<body>".length());
					idx = tempLine.indexOf("</body>");
					if (idx != -1) {
						tempLine = tempLine.substring(0, idx);
						bodyContent.append(tempLine + "\n");
						break;
					} else {
						while ((line = reader.readLine()) != null) {
							idx = line.indexOf("</body>");
							if (idx == -1) {
								bodyContent.append(line);
							} else {
								bodyContent.append(line.substring(idx + "</body>".length()));
								break;
							}
							bodyContent.append("\n");
						}
					}
				}

				line = reader.readLine();
			}

			reader.close();
			result = bodyContent.toString();
		}

		return result;
	}

	/**
	 * @param javaFile
	 * @throws IOException
	 */
	static public void getJavaMetaData(JavaElement javaElement) throws IOException {
		boolean hasImports = false;
		boolean extracted = false;
		boolean typeSet = false;

		String typeJavadoc = "";
		File javaFile = javaElement.getFile();

		if (null != javaFile) {
			BufferedReader reader = new BufferedReader(new FileReader(javaFile));
			String line = reader.readLine();
			StringBuffer javadocContent = new StringBuffer();
			StringBuffer fileContent = new StringBuffer();

			while (null != line) {
				if (line.indexOf("public") != -1 && !line.trim().startsWith("/*") && !line.trim().startsWith("*")) {
					if (line.indexOf("class") != -1) {
						typeSet = true;
						javaElement.setJavaType(JavaElement.TYPE_CLASS);
					} else if (line.indexOf("interface") != -1) {
						typeSet = true;
						javaElement.setJavaType(JavaElement.TYPE_INTERFACE);
					}
				}

				int idx = line.indexOf("import");
				if (idx >= 0) {
					// skip the imports
					hasImports = true;
					while ((line = reader.readLine()) != null) {
						idx = line.indexOf("import");
						if (idx == -1 && !line.trim().equals("")) {
							break;
						}
					}

					while (line != null) {
						// maybe there is no type javadoc, and we encounter the
						// class definition.
						// in this case, don't continue
						if (line.indexOf("public") != -1 && !line.trim().startsWith("/*")
								&& !line.trim().startsWith("*")) {
							if (line.indexOf("class") != -1) {
								typeSet = true;
								javaElement.setJavaType(JavaElement.TYPE_CLASS);
								extracted = true;
								break;
							} else if (line.indexOf("interface") != -1) {
								typeSet = true;
								javaElement.setJavaType(JavaElement.TYPE_INTERFACE);
								extracted = true;
								break;
							}
						} else {
							// get the type javadoc
							idx = line.indexOf("*/");
							if (idx >= 0) {
								extracted = true;
								break;
							}

							javadocContent.append(line);
						}

						line = reader.readLine();
					}
				}

				if (null != line) {
					if (line.indexOf("public") == -1
							&& (line.indexOf("class") == -1 || line.indexOf("interface") == -1)) {
						fileContent.append(line);
					}
				}
				if (extracted && typeSet) {
					break;
				}
				line = reader.readLine();
			}
			if (!hasImports) {
				// no imports
				String cont = fileContent.toString();
				int idx = cont.indexOf("package");
				if (idx >= 0) {
					cont = cont.substring(idx);
					idx = cont.indexOf(';');
					if (cont.indexOf("/**") > -1) {
						cont = cont.substring(idx + 1, cont.indexOf("*/"));
						if (cont.trim().length() > 0) {
							cont = cont.trim().substring(3);
							javadocContent.append(cont);
						}
					}
				}
			}

			// final cuts
			typeJavadoc = javadocContent.toString();
			typeJavadoc = typeJavadoc.replaceAll("\\*", "");
			int idx = typeJavadoc.trim().indexOf('@');
			if (idx > 0) {
				typeJavadoc = typeJavadoc.substring(0, idx) + "\n" + typeJavadoc.substring(idx);
			}
			if (typeJavadoc.startsWith("/")) {
				typeJavadoc = typeJavadoc.substring(1);
			}
		}

		// set the values
		javaElement.setComment(typeJavadoc);
	}
}
