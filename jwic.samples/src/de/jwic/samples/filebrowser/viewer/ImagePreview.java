/*
 * Copyright 2005 jWic group (http://www.jwic.de)
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
 * de.jwic.samples.filebrowser.viewer.GifPreview
 * Created on 03.06.2005
 * $Id: ImagePreview.java,v 1.1 2006/01/16 08:31:31 lordsam Exp $
 */
package de.jwic.samples.filebrowser.viewer;

import java.io.File;
import java.io.IOException;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public class ImagePreview extends Control {

	private File file = null;
	private File rootFile = null;
	
	public ImagePreview(IControlContainer container, String name, File file, File rootFile) {
		super(container, name);
		this.file = file;
		this.rootFile = rootFile;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IControl#actionPerformed(java.lang.String, java.lang.String)
	 */
	public void actionPerformed(String actionId, String parameter) {

	}
	/**
	 * Returns the file handle.
	 * @return
	 */
	public File getFile() {
		return file;
	}
	
	public String getDownloadPath() {
		
		try {
			String path = file.getCanonicalPath();
			String rootPath = rootFile.getCanonicalPath();
			
			String relativePath = path.substring(rootPath.length());
			relativePath.replace('\\', '/');	// make slash out of backslashes (required on windows systems)
			
			if (relativePath.indexOf("WEB-INF") == -1) {
				return relativePath;
			}
			
		} catch (IOException e) {
			log.error("Error calculating download path for file " + file.getName());
		}
		
		return null;
	}

}
