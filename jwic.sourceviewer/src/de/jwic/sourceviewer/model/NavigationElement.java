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
 * de.jwic.sourceviewer.model.NavigationElement
 * Created on 25.04.2007
 * $Id: NavigationElement.java,v 1.5 2007/05/07 13:19:48 lordsam Exp $
 */
package de.jwic.sourceviewer.model;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.jwic.sourceviewer.model.reader.FilesContentReader;

/**
 *
 * @author Florian Lippisch
 */
public class NavigationElement implements Serializable, Comparable {

	protected transient Log log = LogFactory.getLog(getClass());

	protected String name = "";
	protected String comment = "";
	protected String commentFile = "";
	
	/**
	 * Get new logger after deserialization.
	 * @param s
	 * @throws IOException
	 */
	private void readObject(ObjectInputStream s) throws IOException  {
		try {
			s.defaultReadObject();
		} catch (ClassNotFoundException e) {
			throw new IOException("ClassNotFound in readObject");
		}
		log = LogFactory.getLog(getClass());
	}

	/**
	 * @return the commentFile
	 */
	public String getCommentFile() {
		return commentFile;
	}

	/**
	 * @param commentFile the commentFile to set
	 */
	public void setCommentFile(String commentFile) {
		this.commentFile = commentFile;
	}

	/**
	 * Returns the element type. This is often used within
	 * templates to display the various elements in different styles. 
	 * @return
	 */
	public String getElementType() {
		return "element";
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the name to be displayed. Might get overriden by subclasses.
	 * @return
	 */
	public String getDisplayName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns only the first sentence of the comment.
	 * @return
	 */
	public String getShortComment() {
		if (comment != null) {
			int idx = comment.indexOf(". ");
			if (idx == -1) {
				idx = comment.indexOf(".\n");
			}
			String tmp;
			if (idx != -1) {
				tmp = comment.substring(0, idx + 1);
			} else {
				tmp = comment;
			}
			// cut @
			idx = tmp.indexOf('@');
			if (idx != -1) {
				tmp = tmp.substring(0, idx);
			}
			if (tmp.length() > 120) {
				tmp = tmp.substring(0, 120) + "...";
			}
			return tmp;
		}
		return "";
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if (!(o instanceof NavigationElement)) {
			return -1;
		} 
		NavigationElement ne = (NavigationElement)o;
		return name.compareTo(ne.name);
	}
	
	/**
	 * Load the comment from the file into the comment property.
	 * This method is called during build time.
	 * @param rootPath
	 */
	public void loadComments(File rootPath) {
		if (commentFile != null && commentFile.length() != 0) {
			File file = new File(rootPath, commentFile);
			if (file.exists()) {
				try {
					comment = FilesContentReader.getBodyContent(file);
				} catch (Exception e) {
					log.error("Error loading comment", e);
					comment = "Error loading comment: " + e;
				}
			} else {
				log.warn("commentFile not found: " + file.getAbsolutePath());
			}
		}

	}
	
}
