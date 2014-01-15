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
 * de.jwic.sourceviewer.viewer.impl.PropertiesViewer
 * Created on 27.04.2007
 * $Id: PropertiesViewer.java,v 1.3 2007/04/27 12:50:57 aroncotrau Exp $
 */
package de.jwic.sourceviewer.viewer.impl;

import java.util.regex.Pattern;

import de.jwic.base.IControlContainer;
import de.jwic.sourceviewer.model.FileType;

/**
 *
 * @author Florian Lippisch
 */
public class PropertiesViewer extends AbstractTextViewer {

	/**
	 * @param container
	 * @param name
	 */
	public PropertiesViewer(IControlContainer container, String name) {
		super(container, name);
		
		supportedFiles.add(FileType.PROPERTIES);

		addReplace(Pattern.compile("\\t"), "&nbsp;&nbsp;");
		addReplace(Pattern.compile("^[ ]+"), "&nbsp;");
		addReplace(Pattern.compile("(^#.*)"), "<font color=green>$1</font>");
		addReplace(Pattern.compile("(^[^#<].[^#]*)=(.[^#]*)"), "<font color=black>$1</font>=<font color=blue>$2</font>");
	}

}
