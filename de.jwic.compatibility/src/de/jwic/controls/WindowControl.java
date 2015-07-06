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
/*
 * de.jwic.controls.WindowControl
 * $Id: WindowControl.java,v 1.1 2008/09/16 21:55:47 lordsam Exp $
 */
package de.jwic.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.IOuterLayout;

/**
 * Displays the contained controls within a table that looks like a window with
 * a title.
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public class WindowControl extends ControlContainer implements IOuterLayout {

	private static final long serialVersionUID = 2L;

	private String outerTemplateName = WindowControl.class.getName();
	private String title = null;
	private String align = "left";
	private String width = "100%";

	
	/**
	 * @param container
	 */
	public WindowControl(IControlContainer container) {
		super(container);
		init();
	}
	/**
	 * @param container
	 * @param name
	 */
	public WindowControl(IControlContainer container, String name) {
		super(container, name);
		init();
	}
	
	/**
	 * Initialize the control.
	 */
	private void init() {
		setRendererId(DEFAULT_OUTER_RENDERER);
		title = getName();
	}
	
	
	
	/**
     * @return Returns the align.
     */
    public String getAlign() {
        return align;
    }
    /**
     * @return Returns the width.
     */
    public String getWidth() {
        return width;
    }

	
	/**
	 * Returns <code>null</code> if the class has not been extended and
	 * no template name has been set.
	 * @see de.jwic.base.Control#getTemplateName()
	 */
	public String getTemplateName() {
		String tmpl = super.getTemplateName();
		if (tmpl.equals(outerTemplateName) || tmpl.equals(WindowControl.class.getName())) {
			return null;
		}
		return super.getTemplateName();
	}
	
	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
		requireRedraw();
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IOuterLayout#getOuterTemplateName()
	 */
	public String getOuterTemplateName() {
		return outerTemplateName;
	}
	/**
	 * The outerTemplateName to set.
	 * @param outerTemplateName
	 */
	public void setOuterTemplateName(String outerTemplateName) {
		this.outerTemplateName = outerTemplateName;
	}
	
	/**
	 * @param string
	 */
	public void setAlign(String string) {
		align = string;
		requireRedraw();
		
	}
	/**
	 * @param string
	 */
	public void setWidth(String string) {
		width = string;
		requireRedraw();
	}

}
