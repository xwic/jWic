/*******************************************************************************
 * Copyright 2015 xWic group (http://www.xwic.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *  
 *******************************************************************************/
package de.jwic.sourceviewer.model;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import de.jwic.sourceviewer.model.reader.FilesContentReader;

/**
 * The workspace is the root of all element the source viewer may display. The
 * workspace itself contains only groups of projects.
 * 
 * After the workspace configuration is build, the .build() method should be
 * invoked to generate the list of packages and files.
 * 
 * @author Florian Lippisch
 */
public class Workspace extends ContainerElement {

	private Set nameFilter = new HashSet();

	/**
	 * Add a filename that should get filtered.
	 * 
	 * @param name
	 */
	public void addFilter(String name) {
		nameFilter.add(name);
	}

	/**
	 * Add a group.
	 * 
	 * @param group
	 */
	public void addGroup(Group group) {
		addChild(group);
	}

	/**
	 * Build the full workspace by scanning all pathes for relevant
	 * informations.
	 * 
	 * @param rootPath
	 */
	public void build(File rootPath) {

		for (Iterator itGroups = getChilds().iterator(); itGroups.hasNext();) {
			Group group = (Group) itGroups.next();
			group.loadComments(rootPath);

			for (Iterator itProjects = group.getChilds().iterator(); itProjects.hasNext();) {
				Project project = (Project) itProjects.next();
				project.loadComments(rootPath);

				buildFolders(rootPath, project);

			}

		}

	}

	/**
	 * @param project
	 */
	private void buildFolders(File root, ContainerElement container) {

		if (container instanceof PathElement) {
			PathElement path = (PathElement) container;
			path.loadComments(root);

			File fPath = path.getFilePath();
			if (fPath == null) {
				if (new File(path.getPath()).isAbsolute()) {
					fPath = new File(path.getPath());
				} else {
					fPath = new File(root, path.getPath());
				}
				path.setFilePath(fPath);
			}
			if (!fPath.exists()) {
				path.setBadPath(true);
			}
			
			if (!path.isBadPath()) {
				if (path.isScan()) {

					if (path instanceof SourceFolder) {

						buildPackages((SourceFolder) path, fPath, new String[0]);

					} else {
						File[] files = fPath.listFiles();
						for (int i = 0; i < files.length; i++) {
							if (!isExcluded((files[i]))) {
								if (files[i].isDirectory()) {
									Folder childPath = new Folder(files[i]);
									path.addPathElement(childPath);
								} else {
									FileElement childFile = new FileElement(files[i]);
									path.addFileElement(childFile);
								}
							}
						}
					}
				}
			}
		}

		// build childs
		for (Iterator it = container.getChilds().iterator(); it.hasNext();) {

			Object child = it.next();
			if (child instanceof SourcePackage) {
				if (container instanceof SourceFolder) {
					SourceFolder sf = (SourceFolder) container;
					SourcePackage sp = (SourcePackage) child;
					if (!sp.isFilesLoaded() && !sf.isBadPath()) {
						sp.loadFileList(sf.getFilePath());
					}
				}
			} else if (child instanceof ContainerElement) {
				buildFolders(root, (ContainerElement) child);
			}

			if (child instanceof ContainerElement) {
				((ContainerElement) child).sortChilds();
			}
		}

	}

	/**
	 * @param folder
	 * @param path
	 * @param string
	 */
	private void buildPackages(SourceFolder folder, File root, String[] path) {

		StringBuffer osPath = new StringBuffer();
		StringBuffer pName = new StringBuffer();
		String[] subPath = new String[path.length + 1]; // used for recursive
														// 'path' argument

		for (int i = 0; i < path.length; i++) {
			if (i > 0) {
				osPath.append(File.separator);
				pName.append(".");
			}
			subPath[i] = path[i]; // copy values
			osPath.append(path[i]);
			pName.append(path[i]);
		}

		File pDir = new File(root, osPath.toString());
		if (pDir.exists() && pDir.isDirectory()) {

			File[] childs = pDir.listFiles();
			if (childs.length == 0) {
				// empty package -> yet to be ignored
			} else {

				SourcePackage sp = null;
				for (int i = 0; i < childs.length; i++) {
					if (!isExcluded(childs[i])) {
						if (childs[i].isDirectory()) {
							subPath[path.length] = childs[i].getName();
							buildPackages(folder, root, subPath);

						} else {
							if (sp == null) {
								sp = new SourcePackage();
								sp.setName(pName.toString());
								sp.setFilesLoaded(true);
								folder.addPackage(sp);
							}
							FileElement element = new FileElement(childs[i]);
							;
							try {
								if (childs[i].getName().endsWith(".java")) {
									element = new JavaElement(childs[i]);
									FilesContentReader.getJavaMetaData((JavaElement) element);
								} else if (childs[i].getName().equals("package.html")) {
									sp.setComment(FilesContentReader.getBodyContent(childs[i]));
								}
							} catch (IOException e) {
								// step further
							}

							sp.addFileElement(element);
						}
					}
				}

			}

		}
	}

	/**
	 * Check if the file is filtered.
	 * 
	 * @param filename
	 * @return
	 */
	private boolean isExcluded(File file) {
		return nameFilter.contains(file.getName());
	}

}
