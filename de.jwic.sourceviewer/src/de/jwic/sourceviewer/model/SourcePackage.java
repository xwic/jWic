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
import java.io.FileFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

import de.jwic.sourceviewer.model.reader.FilesContentReader;

/**
 *
 * @author Florian Lippisch
 */
public class SourcePackage extends ContainerElement {
	
	private boolean filesLoaded = false;
	
	/**
	 * Returns the element type. This is often used within
	 * templates to display the various elements in different styles. 
	 * @return
	 */
	public String getElementType() {
		return "package";
	}

	/* (non-Javadoc)
	 * @see de.jwic.sourceviewer.model.NavigationElement#getDisplayName()
	 */
	public String getDisplayName() {
		if (name == null || name.length() == 0) {
			return "(default package)";
		}
		return super.getDisplayName();
	}
	
	/**
	 * Add a file element.
	 * @param file
	 */
	public void addFileElement(FileElement file) {
		addChild(file);
	}

	/**
	 * @return the filesLoaded
	 */
	boolean isFilesLoaded() {
		return filesLoaded;
	}

	/**
	 * @param filesLoaded the filesLoaded to set
	 */
	void setFilesLoaded(boolean filesLoaded) {
		this.filesLoaded = filesLoaded;
	}

	/**
	 * @param filePath
	 */
	public void loadFileList(File srcPath) {
		
		String path = getName().replace('.', File.separatorChar);
		File fDir = new File(srcPath, path);
		if (fDir.exists()) {
			
			File[] files = fDir.listFiles(new FileFilter() {
				public boolean accept(File pathname) {
					return pathname.isFile();
				}
			});
			
			for (int i = 0; i < files.length; i++) {
				FileElement element = new FileElement(files[i]);;
				if (files[i].getName().endsWith(".java")) {
					try {
						element = new JavaElement(files[i]);
						FilesContentReader.getJavaMetaData((JavaElement) element);
					} catch (IOException e) {
						// step further
					}
				}
				
				addFileElement(element);
			}
			
		}
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.sourceviewer.model.ContainerElement#sortChilds()
	 */
	public void sortChilds() {
		
		Collections.sort(childs, new Comparator() {
			/* (non-Javadoc)
			 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
			 */
			public int compare(Object o1, Object o2) {
				FileElement f1 = (FileElement)o1;
				FileElement f2 = (FileElement)o2;
				if (f2.getType() != f1.getType()) {
					if (f1.getType() == FileType.JAVA) {
						return -1;
					} else if (f2.getType() == FileType.JAVA) {
						return 1;
					}
				}
				return f1.compareTo(f2);
			}
		});
		
	}

}
