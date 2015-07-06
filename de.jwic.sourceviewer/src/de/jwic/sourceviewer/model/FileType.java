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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import de.jwic.base.ImageRef;

/**
 *
 * @author Florian Lippisch
 */
public class FileType implements Serializable {

	public final static FileType UNKNOWN = new FileType("Unknown", ".*", new ImageRef("icons/file.gif"));
	public final static FileType JAVA = new FileType("Java", ".java$", new ImageRef("icons/jcu_obj.gif"));
	public final static FileType HTML = new FileType("HTML", "\\.html$|\\.htm$", new ImageRef("icons/file.gif"));
	public final static FileType XML = new FileType("XML", "\\.xml$|\\.xwic$", new ImageRef("icons/file.gif"));
	public final static FileType VTL = new FileType("Velocity Template", "\\.vtl$|\\.page$|\\.body$|\\.footer$|\\.header$|\\.debug$", new ImageRef("icons/template_obj.gif"));
	public final static FileType JS = new FileType("JavaScript", "\\.js$", new ImageRef("icons/file.gif"));
	public final static FileType CSS = new FileType("CSS", "\\.css$", new ImageRef("icons/file.gif"));
	public final static FileType PROPERTIES = new FileType("Properties", "\\.properties$", new ImageRef("icons/file.gif"));

	public final static FileType IMAGE = new FileType("GIF", "\\.gif$|\\.png$|\\.jpg$|\\.jpeg$", new ImageRef("icons/image_obj.gif"));
	
	
	public final static List TYPES = new ArrayList();
	static {
		TYPES.add(JAVA);
		TYPES.add(HTML);
		TYPES.add(XML);
		TYPES.add(VTL);
		TYPES.add(JS);
		TYPES.add(CSS);
		TYPES.add(PROPERTIES);
		TYPES.add(IMAGE);
		TYPES.add(UNKNOWN);
	}
	
	private String name;
	private String match;
	private Pattern pMatch;
	private ImageRef image;
	
	/**
	 * @param name
	 * @param extension
	 * @param image
	 */
	public FileType(String name, String match, ImageRef image) {
		super();
		this.name = name;
		this.match = match;
		this.image = image;
		pMatch = Pattern.compile(match, Pattern.CASE_INSENSITIVE);
	}

	/**
	 * @return the image
	 */
	public ImageRef getImage() {
		return image;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns true if the specified filename matches this type.
	 * @param filename
	 * @return
	 */
	public boolean isMatch(String filename) {
		return pMatch.matcher(filename).find(0);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((match == null) ? 0 : match.hashCode());
		result = PRIME * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final FileType other = (FileType) obj;
		if (match == null) {
			if (other.match != null)
				return false;
		} else if (!match.equals(other.match))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
