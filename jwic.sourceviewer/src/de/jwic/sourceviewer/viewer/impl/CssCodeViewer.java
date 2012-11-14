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
 * de.jwic.sourceviewer.viewer.impl.CssCodeViewer
 * Created on Apr 27, 2007
 * $Id: CssCodeViewer.java,v 1.3 2007/05/07 08:42:11 lordsam Exp $
 */
package de.jwic.sourceviewer.viewer.impl;

import java.util.regex.Pattern;

import de.jwic.base.IControlContainer;
import de.jwic.sourceviewer.model.FileType;
import de.jwic.sourceviewer.viewer.IObjectViewer;

/**
 * 
 *
 * @author Aron Cotrau
 */
public class CssCodeViewer extends AbstractTextViewer implements IObjectViewer {

	public CssCodeViewer(IControlContainer container, String name) {
		super(container, name);
		supportedFiles.add(FileType.CSS);
		
		addReplace(Pattern.compile("\\t"), "&nbsp;&nbsp;");
		addReplace(Pattern.compile("^[ ]+"), "&nbsp;");
		addReplace(Pattern.compile("(\"[^\"\r\n]*\")"), "<font color=green>$1</font>");
		addReplace(Pattern.compile("(.*[{])"), "<font color=black>$1</font>");
		addReplace(Pattern.compile("(.*):([^;]*)"), "<font color=#7f0055>$1</font>:<font color=blue>$2</font>");
		addReplace(Pattern.compile("(/\\*.*?\\*/)", Pattern.DOTALL), "<font color=green>$1</font>");
	}
	
	public String getStartMultiLineCommentTag() {
		return "/*";
	}
	
	public String getEndMultiLineCommentTag() {
		return "*/";
	}
}

