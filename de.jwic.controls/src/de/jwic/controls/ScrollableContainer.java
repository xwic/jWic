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

import de.jwic.base.ControlContainer;
import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.base.IOuterLayout;
import de.jwic.base.JavaScriptSupport;

/**
 * This container allows horizontal and vertical scrolling of the content. The 
 * container must specify a fixed width and/or height.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
@JavaScriptSupport
public class ScrollableContainer extends ControlContainer implements IOuterLayout {

	private static final long serialVersionUID = 1L;
	private String width = "100%";
	private String height = "100%";
	
	private Field fldTop;
	private Field fldLeft;
	
	/**
	 * @param container
	 */
	public ScrollableContainer(IControlContainer container) {
		super(container);
		init();
	}
	/**
	 * @param container
	 * @param name
	 */
	public ScrollableContainer(IControlContainer container, String name) {
		super(container, name);
		init();
	}
	
	/**
	 * Initialize the control.
	 */
	private void init() {
		setRendererId(DEFAULT_OUTER_RENDERER);
		fldTop = new Field(this, "top");
		fldTop.setValue("0");
		fldLeft = new Field(this, "left");
		fldLeft.setValue("0");
	}
	
	/**
	 * Returns <code>null</code> if the class has not been extended and
	 * no template name has been set.
	 * @see de.jwic.base.Control#getTemplateName()
	 */
	public String getTemplateName() {
		if (super.getTemplateName().equals(ScrollableContainer.class.getName())) {
			return null;
		}
		return super.getTemplateName();
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IOuterLayout#getOuterTemplateName()
	 */
	public String getOuterTemplateName() {
		
		return ScrollableContainer.class.getName();
	}

	/**
	 * @return Returns the height.
	 */
	public String getHeight() {
		return height;
	}
	/**
	 * Set the height of the container. Can be set to either a % value or px, i.e. 200px;
	 * @param height The height to set.
	 */
	public void setHeight(String height) {
		this.height = height;
		requireRedraw();
	}
	/**
	 * @return Returns the width.
	 */
	public String getWidth() {
		return width;
	}
	/**
	 * @param width The width to set.
	 */
	public void setWidth(String width) {
		this.width = width;
		requireRedraw();
	}
	
	/**
	 * Set the top scrolling position.
	 * @param top
	 */
	public void setTop(int top) {
		fldTop.setValue(Integer.toString(top));
		requireRedraw();
	}
	
	public void updateSize() {
		// should be overwritten to implement dynamic sizing
	}
}
