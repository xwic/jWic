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

import java.util.regex.Pattern;

import de.jwic.base.IControlContainer;
import de.jwic.sourceviewer.model.FileType;
import de.jwic.sourceviewer.viewer.IObjectViewer;

/**
 *
 * @author Florian Lippisch
 */
public class TextViewer extends AbstractTextViewer implements IObjectViewer {

	/**
	 * @param container
	 * @param name
	 */
	public TextViewer(IControlContainer container, String name) {
		super(container, name);
		
		supportedFiles.add(FileType.HTML);
		supportedFiles.add(FileType.VTL);
		supportedFiles.add(FileType.XML);
		supportedFiles.add(FileType.JS);
		supportedFiles.add(FileType.CSS);

		addReplace(Pattern.compile("\\t"), "&nbsp;&nbsp;");
		addReplace(Pattern.compile("^[ ]+"), "&nbsp;");
		addReplace(Pattern.compile("<"), "&lt;");
		addReplace(Pattern.compile(">"), "&gt;");
		
	}

}
