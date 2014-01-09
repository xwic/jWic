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
 * de.jwic.sourceviewer.viewer.impl.TextViewer
 * Created on 26.04.2007
 * $Id: AbstractTextViewer.java,v 1.4 2007/05/03 14:27:46 aroncotrau Exp $
 */
package de.jwic.sourceviewer.viewer.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.sourceviewer.main.SVModel;
import de.jwic.sourceviewer.model.FileElement;
import de.jwic.sourceviewer.model.NavigationElement;
import de.jwic.sourceviewer.viewer.IObjectViewer;

/**
 * 
 * @author Florian Lippisch
 */
public class AbstractTextViewer extends Control implements IObjectViewer {

	private String htmlCode = "";
	protected Set supportedFiles = new HashSet();

	protected String startingHTML = "<code>";
	protected String endingHTML = "</code>";

	protected boolean nobr = true;

	private List replaceList = new ArrayList();

	protected class RegExpReplace implements Serializable {
		Pattern pattern;
		String replaceWith;

		public RegExpReplace(Pattern p, String with) {
			pattern = p;
			replaceWith = with;
		}
	}

	/**
	 * @param container
	 * @param name
	 */
	public AbstractTextViewer(IControlContainer container, String name) {
		super(container, name);
		setTemplateName(AbstractTextViewer.class.getName());
	}

	protected void addReplace(Pattern pattern, String replaceWidth) {
		replaceList.add(new RegExpReplace(pattern, replaceWidth));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.sourceviewer.viewer.IObjectViewer#isSupported(de.jwic.sourceviewer.model.NavigationElement)
	 */
	public boolean isSupported(NavigationElement element) {

		if (element instanceof FileElement) {
			FileElement file = (FileElement) element;

			if (supportedFiles.contains(file.getType())) {
				return true;
			}

		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.sourceviewer.viewer.IObjectViewer#setNavigationElement(de.jwic.sourceviewer.model.NavigationElement)
	 */
	public void setNavigationElement(NavigationElement element) {

		if (element == null) {
			htmlCode = "";
			return; // direct exit
		}

		FileElement fe = (FileElement) element;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fe.getFile()));
			StringWriter writer = new StringWriter();

			writer.write(startingHTML);
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.trim().startsWith(getStartMultiLineCommentTag())) {
					// handle multiline-comments for xml, css and html formats
					String commentLine = createCommentLine(line); 
					writeLine(writer, commentLine);

					if (line.indexOf(getEndMultiLineCommentTag()) == -1) {
						while (null != line && line.indexOf(getEndMultiLineCommentTag()) == -1) {
							line = reader.readLine();
							if (null != line && !"".equals(line)) {
								commentLine = createCommentLine(line);
								writeLine(writer, commentLine);
							}
						}
					}
				} else {
					for (Iterator it = replaceList.iterator(); it.hasNext();) {
						RegExpReplace rep = (RegExpReplace) it.next();
						line = rep.pattern.matcher(line).replaceAll(rep.replaceWith);
					}

					writeLine(writer, line);
				}
			}

			writer.write(endingHTML);
			htmlCode = writer.toString();
			reader.close();
		} catch (Exception e) {
			log.error("Error creating HTML code", e);
			htmlCode = "Error creating html code: " + e;
		}
	}

	private String createCommentLine(String line) {
		int i = 0;
		String tempLine = new String(line.trim());

		// prepare to html form
		tempLine = tempLine.replaceAll("<", "&lt;");
		tempLine = tempLine.replaceAll(">", "&gt;");

		while (line.charAt(i++) == ' ' && i < line.length()-1) {
			tempLine = "&nbsp;" + tempLine;
		}
		tempLine = "<font color=green>" + tempLine.trim() + "</font>";
		return tempLine;
	}

	private void writeLine(StringWriter writer, String line) {
		if (nobr) {
			writer.write("<nobr>");
		}
		writer.write(line);
		if (nobr) {
			writer.write("</nobr>");
		}
		writer.write("<br>\n");
	}

	/**
	 * @return the start sequence for a multiline comment
	 */
	public String getStartMultiLineCommentTag() {
		return "<!--";
	}

	/**
	 * @return the end sequence for a multiline comment
	 */
	public String getEndMultiLineCommentTag() {
		return "-->";
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
