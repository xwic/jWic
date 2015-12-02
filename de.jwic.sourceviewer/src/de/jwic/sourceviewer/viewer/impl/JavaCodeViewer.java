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
package de.jwic.sourceviewer.viewer.impl;

import java.io.FileReader;
import java.io.StringWriter;

import de.java2html.converter.JavaSource2HTMLConverter;
import de.java2html.javasource.JavaSource;
import de.java2html.javasource.JavaSourceParser;
import de.java2html.options.JavaSourceConversionOptions;
import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.sourceviewer.main.SVModel;
import de.jwic.sourceviewer.model.FileElement;
import de.jwic.sourceviewer.model.FileType;
import de.jwic.sourceviewer.model.NavigationElement;
import de.jwic.sourceviewer.viewer.IObjectViewer;

/**
 *
 * @author Florian Lippisch
 */
public class JavaCodeViewer extends Control implements IObjectViewer {

	private String htmlCode = "";
	
	/**
	 * @param container
	 * @param name
	 */
	public JavaCodeViewer(IControlContainer container, String name) {
		super(container, name);
	}

	/* (non-Javadoc)
	 * @see de.jwic.sourceviewer.viewer.IObjectViewer#isSupported(de.jwic.sourceviewer.model.NavigationElement)
	 */
	public boolean isSupported(NavigationElement element) {
		
		if (element instanceof FileElement) {
			FileElement file = (FileElement)element;
			return file.getType().equals(FileType.JAVA) || file.getType().equals(FileType.JS);
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see de.jwic.sourceviewer.viewer.IObjectViewer#setNavigationElement(de.jwic.sourceviewer.model.NavigationElement)
	 */
	public void setNavigationElement(NavigationElement element) {
		
		if (element == null) {
			htmlCode = "";
			return;				// direct exit 
		} 
		
		if (false) {
			htmlCode = "test";
			return;
		}
		FileElement fe = (FileElement)element;
		try {
			FileReader reader = new FileReader(fe.getFile());
			JavaSource source = new JavaSourceParser().parse(reader);
			JavaSource2HTMLConverter converter = new JavaSource2HTMLConverter();
			StringWriter writer = new StringWriter();
			
			JavaSourceConversionOptions options = JavaSourceConversionOptions.getDefault();
			//options.setShowLineNumbers(true);
			
			converter.convert(source, options, writer);
			htmlCode = writer.toString();
			reader.close();
			
		} catch (Exception e) {
			log.error("Error creating HTML code", e);
			htmlCode = "Error creating html code: " + e;
		}

	}

	/**
	 * @return the htmlCode
	 */
	public String getHtmlCode() {
		return htmlCode;
	}

	public void init(SVModel model) {
		// do nothing atm
	}
}
