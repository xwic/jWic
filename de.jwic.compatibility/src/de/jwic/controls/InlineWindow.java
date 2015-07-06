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
package de.jwic.controls;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.IOuterLayout;
import de.jwic.base.JavaScriptSupport;
import de.jwic.util.Util;

/**
 * A container for controls with the style of a window.
 * 
 * @author Florian Lippisch
 */
@JavaScriptSupport
public class InlineWindow extends HTMLElementContainer implements IOuterLayout {
	private static final long serialVersionUID = 1L;
	public enum Position { INLINE, CENTER_SCREEN, CENTER }; 
	
	private String outerTemplateName = InlineWindow.class.getName();
	private String text = null;
	private Position position = Position.INLINE;
	
	/**
	 * @param container
	 */
	public InlineWindow(IControlContainer container) {
		this(container, null);
	}

	/**
	 * @param container
	 * @param name
	 */
	public InlineWindow(IControlContainer container, String name) {
		super(container, name);
		setRendererId(Control.DEFAULT_OUTER_RENDERER);
		cssClass = "j-window";
		text = getName();
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IOuterLayout#getOuterTemplateName()
	 */
	public String getOuterTemplateName() {
		return outerTemplateName;
	}
	/**
	 * Returns <code>null</code> if the class has not been extended and
	 * no template name has been set.
	 * @see de.jwic.base.Control#getTemplateName()
	 */
	public String getTemplateName() {
		String tmpl = super.getTemplateName();
		if (tmpl.equals(outerTemplateName) || tmpl.equals(InlineWindow.class.getName())) {
			return null;
		}
		return super.getTemplateName();
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		if (!Util.equals(this.text, text)) {
			this.text = text;
			requireRedraw();
		}
	}

	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

}
