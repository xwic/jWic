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
package de.jwic.controls.ckeditor;

import java.util.ArrayList;
import java.util.List;

import de.jwic.base.Control;
import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.base.IHaveEnabled;
import de.jwic.base.JavaScriptSupport;
import de.jwic.events.ValueChangedListener;

/**
 * Wrapps the CKeditor control.
 * @author lippisch
 */
@JavaScriptSupport
public class CKEditor extends Control implements IHaveEnabled {
	private static final long serialVersionUID = 1L;
	private Field content;
	private int width = 800;
	private int height = 200;
	private boolean enabled = true;
	
	private String toolBarName = "Basic";
	private List<ToolBarBand> customToolBar = null;
	
	private boolean fullRedraw = false;
	
	/**
	 * @param container
	 * @param name
	 */
	public CKEditor(IControlContainer container, String name) {
		super(container, name);
		
		content = new Field(this, "content");
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IHaveEnabled#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IHaveEnabled#setEnabled(boolean)
	 */
	@Override
	public void setEnabled(boolean enabled) {
		if (this.enabled != enabled) {
			requireRedraw();
		}
		this.enabled = enabled;
	}

	/**
	 * Returns the content of the editor.
	 * @return
	 */
	public String getText() {
		return content.getValue();
	}
	
	/**
	 * Replace the content.
	 * @param text
	 * @return
	 */
	public void setText(String text) {
		content.setValue(text);
		requireRedraw();
	}

	/**
	 * @return the toolBarName
	 */
	public String getToolBarName() {
		return toolBarName;
	}

	/**
	 * @param toolBarName the toolBarName to set
	 */
	public void setToolBarName(String toolBarName) {
		this.toolBarName = toolBarName;
		requireRedraw();
	}

	/**
	 * @return the customToolBar
	 */
	public List<ToolBarBand> getCustomToolBar() {
		return customToolBar;
	}

	/**
	 * @param customToolBar the customToolBar to set
	 */
	public void setCustomToolBar(List<ToolBarBand> customToolBar) {
		this.customToolBar = customToolBar;
		requireRedraw();
	}
	
	/**
	 * Set custom toolbars.
	 * @param bands
	 */
	public void setCustomToolBar(ToolBarBand... bands) {
		if (customToolBar == null) {
			customToolBar = new ArrayList<ToolBarBand>();
		} else {
			customToolBar.clear();
		}
		for (ToolBarBand band : bands) {
			customToolBar.add(band);
		}
		requireRedraw();
	}

	/**
	 * @return the fullRedraw
	 */
	public boolean isFullRedraw() {
		return fullRedraw;
	}

	/**
	 * Used internally to indicate if the editor control needs to be complete re-created. If set
	 * to false, the control is juse partly updated using javascript.
	 * @param fullRedraw the fullRedraw to set
	 */
	public void setFullRedraw(boolean fullRedraw) {
		this.fullRedraw = fullRedraw;
	}

	/**
	 * Add a value changed listener to the <b>field</b> used by this
	 * input box control.
	 * @param listener
	 */
	public void addValueChangedListener(ValueChangedListener listener) {
		content.addValueChangedListener(listener);
	}


}
