/*
 * Copyright 2005 jWic Group (http://www.jwic.de)
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
 * de.jwic.samples.filebrowser.FileListControl
 * Created on 24.05.2005
 * $Id: FileListControl.java,v 1.6 2008/09/17 15:19:43 lordsam Exp $
 */
package de.jwic.samples.filebrowser;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.controls.ScrollableContainer;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;

/**
 * Displays a list of files in a directory.
 * @author Florian Lippisch
 * @version $Revision: 1.6 $
 */
public class FileListControl extends ScrollableContainer {

	private final static long KILOBYTE = 1024;
	private final static long MEGABYTE = 1024 * KILOBYTE;

	private File directory = null;
	private FileComparator fileComparator = null;
	
	private List<ElementSelectedListener> listeners = new ArrayList<ElementSelectedListener>();
	private DirectoryModel model = null;
	
	
	private class FileComparator implements Comparator<File>, Serializable {
		String sortmode = "name"; 
		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compare(File o1, File o2) {
			File f1 = (File)o1;
			File f2 = (File)o2;
			
			if (sortmode.equals("lm")) {
				if (f1.lastModified() < f2.lastModified()) {
					return -1;
				} else if (f1.lastModified() > f2.lastModified()){
					return 1;
				} else {
					return 0;
				}
				
			} else {
				if (f1.isDirectory()) {
					if (f2.isDirectory()) {
						return f1.getName().compareTo(f2.getName());
					} else {
						return -1;
					}
				} else {
					if (f2.isDirectory()) {
						return 1;
					} else {
						if (sortmode.equals("name")) {
							return f1.getName().compareTo(f2.getName());
						} else {
							if (f1.length() < f2.length()) {
								return -1;
							} else if (f1.length() > f2.length()){
								return 1;
							} else {
								return 0;
							}
						}
					}
				}
			}
			
		}
	}
	
	
	/**
	 * @param container
	 * @param name
	 */
	public FileListControl(IControlContainer container, String name) {
		super(container, name);
		
		fileComparator = new FileComparator();
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IControl#actionPerformed(java.lang.String, java.lang.String)
	 */
	public void actionPerformed(String actionId, String parameter) {
		
		if (actionId.equals("select")) {
			
			selectElement(parameter);
			
		}

	}

	/**
	 * 
	 * @param listener
	 */
	public void addElementSelectedListener(ElementSelectedListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * @param parameter
	 */
	private void selectElement(String filename) {

		File selectedFile = new File(directory.getAbsolutePath() + System.getProperty("file.separator") + filename);
		if (selectedFile.exists()) {
			if (selectedFile.isDirectory()) {
				model.setDirectory(selectedFile);
			}
			// dispatch event.
			ElementSelectedEvent event = new ElementSelectedEvent(this, selectedFile);
			for (Iterator<ElementSelectedListener> it = listeners.iterator(); it.hasNext();) {
				
				ElementSelectedListener listener = it.next();
				listener.elementSelected(event);
				
			}
			
		}
		
	}
	
	/**
	 * Returns the files listed in the current directory.
	 * @return
	 */
	public File[] getFiles() {
		if (directory != null) {
			File[] files = directory.listFiles();
			Arrays.sort(files, fileComparator);
			return files;
		} else {
			return null;
		}
	}
	
	/**
	 * Returns the name of the file. Cut's the name if it has more then 40 chars.
	 * @param file
	 * @return
	 */
	public String getFileName(File file) {
		
		String name = file.getName();
		if (name.length() > 32) {
			return name.substring(0, 32) + "...";
		}
		return name;
		
	}
	
	/**
	 * Returns the file type.
	 * @param file
	 * @return
	 */
	public String getFileType(File file) {
		
		String type;
		if (file.isDirectory()) {
			type = "Directory";
		} else if (file.getName().endsWith(".xwic")) {
			type = "jWic Application";
		} else if (file.getName().endsWith(".xml")) {
			type = "XML File";
		} else if (file.getName().endsWith(".gif")) {
			type = "GIF Image";
		} else if (file.getName().endsWith(".js")) {
			type = "JavaScript Library";
		} else {
			String tmp = file.getName();
			int i = tmp.lastIndexOf(".");
			if (i != -1) {
				type = tmp.substring(i) + " file";
			} else {
				type = "unknown";
			}
		}
		
		return type;
		
	}
	
	/**
	 * Returns the size of the file in byte, kbyte or mbyte.
	 * @param file
	 * @return
	 */
	public String getNamedSize(File file) {
		
		long length = file.length();
		if (length > MEGABYTE) {
			return length / MEGABYTE + " MB";
		} else if (length > KILOBYTE) {
			return length / KILOBYTE + " KB";
		} else {
			return "1 KB";
		}
		
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
		this.directory = directory;
		setRequireRedraw(true);
	}

	/**
	 * @param model
	 */
	public void setDirectoryModel(DirectoryModel model) {
		this.model = model;
		model.addPropertyChangeListener(new ModelObserver());
		setDirectory(model.getDirectory());
	}

	/**
	 * @param parameter
	 */
	public void setSort(String parameter) {
		fileComparator.sortmode = parameter;
		setRequireRedraw(true);
		
	}
	
	/**
	 * Used to observe the model.
	 * @author Florian Lippisch
	 * @version $Revision: 1.6 $
	 */
	private class ModelObserver implements PropertyChangeListener, Serializable {
		public void propertyChange(PropertyChangeEvent evt) {
			setDirectory((File)evt.getNewValue());
		}
	}
}
