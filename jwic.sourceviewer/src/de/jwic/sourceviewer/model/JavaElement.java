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
 * de.jwic.sourceviewer.model.JavaElement
 * Created on May 7, 2007
 * $Id: JavaElement.java,v 1.4 2007/05/07 11:44:28 aroncotrau Exp $
 */
package de.jwic.sourceviewer.model;

import java.io.File;

/**
 * This class defines a Java Element.
 *
 * @author Aron Cotrau
 */
public class JavaElement extends FileElement {
	
	public static int TYPE_CLASS = 0;
	public static int TYPE_INTERFACE = 1;
	
	private int javaType = TYPE_CLASS;
	
	/**
	 * default constructor
	 */
	public JavaElement() {
		type = FileType.JAVA;
	}
	
	/**
	 * @param file
	 */
	public JavaElement(File file) {
		super(file);
		type = FileType.JAVA;
	}

	/* (non-Javadoc)
	 * @see de.jwic.sourceviewer.model.FileElement#getElementType()
	 */
	public String getElementType() {
		return "java";
	}
	
	/**
	 * @return the javadocTypeComment
	 */
	public String getJavadocTypeComment() {
		return comment;
	}
	
	/**
	 * @return the javaType
	 */
	public int getJavaType() {
		return javaType;
	}

	/**
	 * @param javaType the javaType to set
	 */
	public void setJavaType(int javaType) {
		this.javaType = javaType;
	}
}
