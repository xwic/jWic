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
 * de.jwic.sourceviewer.viewer.impl.VelocityCodeViewer
 * Created on 26.04.2007
 * $Id: HTMLCodeViewer.java,v 1.5 2007/05/07 14:30:41 aroncotrau Exp $
 */
package de.jwic.sourceviewer.viewer.impl;

import java.util.regex.Pattern;

import de.jwic.base.IControlContainer;
import de.jwic.sourceviewer.model.FileType;
import de.jwic.sourceviewer.viewer.IObjectViewer;

/**
 *
 * @author Florian Lippisch
 */
public class HTMLCodeViewer extends AbstractTextViewer implements IObjectViewer {

	/**
	 * @param container
	 * @param name
	 */
	public HTMLCodeViewer(IControlContainer container, String name) {
		super(container, name);
		supportedFiles.add(FileType.HTML);
		supportedFiles.add(FileType.XML);
		
		addReplace(Pattern.compile("\\t"), "&nbsp;&nbsp;");
		addReplace(Pattern.compile("[ ]"), "&nbsp;");
		addReplace(Pattern.compile("^(<\\?)"), "&lt;?");
		addReplace(Pattern.compile("(\\?>)"), "?&gt;");
		addReplace(Pattern.compile("^(<\\!)"), "&lt;!");
		addReplace(Pattern.compile("<([/]{0,1}[A-Z][A-Z0-9]*\\b[^>]*)>", Pattern.CASE_INSENSITIVE), "<font color=blue>&lt;$1&gt;</font>");
		addReplace(Pattern.compile("<([^>]*<|[^>]*$)"), "<font color=blue>&lt;$1</font>");
		
	}
}
