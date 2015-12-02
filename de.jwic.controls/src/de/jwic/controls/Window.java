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
import de.jwic.base.JavaScriptSupport;

/**
 * Renders an overlay layer in window style, using the PWC window
 * library.
 * 
 * This class should not be extended to use.
 * 
 * This is a re-implementation of the ecolib DivPopupWindow originally
 * implemented by Mohammed Ataulla.
 *
 * @author Florian Lippisch
 */
@JavaScriptSupport
public class Window extends ControlContainer {

	private static final long serialVersionUID = 2455896644524072540L;

	protected String title = "";
	protected boolean modal = true;
	protected String cssClass = "jwicdefault";

	protected boolean resizable = true;
	protected boolean closeable = true;
	protected boolean minimizable = true;
	protected boolean maximizable = true;
	protected boolean draggable = true;
	protected boolean popup = false;
	
	protected Field height;
	protected Field width;
	protected Field top;
	protected Field left;

	/**
	 * Create new default window.
	 * @param container
	 */
	public Window(IControlContainer container) {
		this(container, null);
	}

	/**
	 * Create new default window with the specified name.
	 * @param container
	 * @param name
	 */
	public Window(IControlContainer container, String name) {
		super(container, name);
		height = new Field(this, "height");
		width = new Field(this, "width");
		top = new Field(this, "top");
		left = new Field(this, "left");
	}
	
	/**
	 * Invoked when the window is closed.
	 */
	public void actionClose() {
		setVisible(false);
	}
	
	/**
	 * Returns true if the window is displayed without a header.
	 * @return
	 */
	public boolean isPopup() {
		return popup;
	}

	/**
	 * Set to true if you want the window to be displayed without a header.
	 * @param popup
	 */
	public void setPopup(boolean popup) {
		this.popup = popup;
		if(popup == true) {
			this.modal = false;
			this.closeable = false;
			this.maximizable= false;
			this.minimizable = false;
			this.resizable = false;
		}
		requireRedraw();
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
		requireRedraw();
	}

	/**
	 * @return the modal
	 */
	public boolean isModal() {
		return modal;
	}

	/**
	 * @param modal the modal to set
	 */
	public void setModal(boolean modal) {
		this.modal = modal;
		requireRedraw();
	}

	/**
	 * @return the cssClass
	 */
	public String getCssClass() {
		return cssClass;
	}

	/**
	 * @param cssClass the cssClass to set
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
		requireRedraw();
	}

	/**
	 * Returns the height of the window.
	 * @return
	 */
	public int getHeight() {
		try {
			return Integer.parseInt(height.getValue());
		} catch (Exception e) {
			return 0;
		}
	}

	/** 
	 * Set the height of the window.
	 */
	public void setHeight(int height) {
		this.height.setValue(Integer.toString(height));
		requireRedraw();
	}

	/**
	 * Returns the width of the window.
	 * @return
	 */
	public int getWidth() {
		try {
			return Integer.parseInt(width.getValue());
		} catch (Exception e) {
			return 0;
		}
	}

	/** 
	 * Set the width of the window.
	 */
	public void setWidth(int width) {
		this.width.setValue(Integer.toString(width));
		requireRedraw();
	}
	/**
	 * Returns the top position of the window.
	 * @return
	 */
	public int getTop() {
		try {
			return Integer.parseInt(top.getValue());
		} catch (Exception e) {
			return 0;
		}
	}

	/** 
	 * Set the width of the window.
	 */
	public void setTop(int top) {
		this.top.setValue(Integer.toString(top));
		requireRedraw();
	}
	/**
	 * Returns the width of the window.
	 * @return
	 */
	public int getLeft() {
		try {
			return Integer.parseInt(left.getValue());
		} catch (Exception e) {
			return 0;
		}
	}

	/** 
	 * Set the width of the window.
	 */
	public void setLeft(int left) {
		this.left.setValue(Integer.toString(left));
		requireRedraw();
	}

	/**
	 * @return the resizable
	 */
	public boolean isResizable() {
		return resizable;
	}

	/**
	 * @param resizable the resizable to set
	 */
	public void setResizable(boolean resizable) {
		if (this.resizable != resizable) {
			this.resizable = resizable;
			requireRedraw();
		}
	}

	/**
	 * @return the closable
	 */
	public boolean isCloseable() {
		return closeable;
	}

	/**
	 * @param closable the closable to set
	 */
	public void setCloseable(boolean closeable) {
		if (this.closeable != closeable) {
			this.closeable = closeable;
			requireRedraw();
		}
	}

	/**
	 * @return the minimizable
	 */
	public boolean isMinimizable() {
		return minimizable;
	}

	/**
	 * @param minimizable the minimizable to set
	 */
	public void setMinimizable(boolean minimizable) {
		if (this.minimizable != minimizable) {
			this.minimizable = minimizable;
			requireRedraw();
		}
	}

	/**
	 * @return the maximizable
	 */
	public boolean isMaximizable() {
		return maximizable;
	}

	/**
	 * @param maximizable the maximizable to set
	 */
	public void setMaximizable(boolean maximizable) {
		if (this.maximizable != maximizable) {
			this.maximizable = maximizable;
			requireRedraw();
		}
	}

	/**
	 * @return the draggable
	 */
	public boolean isDraggable() {
		return draggable;
	}

	/**
	 * @param draggable the draggable to set
	 */
	public void setDraggable(boolean draggable) {
		if (this.draggable != draggable) {
			this.draggable = draggable;
			requireRedraw();
		}
	}
}
