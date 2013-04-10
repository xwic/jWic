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
 * de.jwic.samples.filebrowser.DirectoryModel
 * Created on 25.05.2005
 * $Id: DirectoryModel.java,v 1.2 2006/06/02 11:05:57 lordsam Exp $
 */
package de.jwic.samples.filebrowser;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.Serializable;

/**
 * Acts as a model for the FileBrowser. Holds the current directory selected
 * by the user.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class DirectoryModel implements Serializable {

	private File directory = null;
	private PropertyChangeSupport propSupport = new PropertyChangeSupport(this);
	
	/**
	 * Add a listener to this model.
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propSupport.addPropertyChangeListener(listener);
	}
	
	/**
	 * Removes the specified listener.
	 * @param listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propSupport.removePropertyChangeListener(listener);
	}
	
	/**
	 * @param rootFile
	 */
	public DirectoryModel(File file) {
		directory = file;
	}
	/**
	 * @return Returns the directory.
	 */
	public File getDirectory() {
		return directory;
	}
	/**
	 * @param directory The directory to set.
	 */
	public void setDirectory(File directory) {
		File old = this.directory;
		this.directory = directory;
		propSupport.firePropertyChange("directory", old, directory);
	}
}
