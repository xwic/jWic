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
package de.jwic.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.jwic.events.IPageListener;
import de.jwic.events.PageEvent;

/**
 * A Page is a control container that is displayed as a root control. 
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class Page extends ControlContainer {

	private static final long serialVersionUID = 6009335074727417445L;
	
	private Dimension pageSize = new Dimension();
	private int clientTop = 0;
	private int clientLeft = 0;
	private String title = null;
	private String forceFocusElement = "";
	private boolean multipart = false;
	
	private List<IPageListener> listeners = Collections.synchronizedList(new ArrayList<IPageListener>());
	
	protected enum EventType {
		PAGE_SIZE_CHANGED
	}
	
	/**
	 * @param container
	 */
	public Page(IControlContainer container) {
		super(container);
		title = getControlID();
	}
	/**
	 * @param container
	 * @param name
	 */
	public Page(IControlContainer container, String name) {
		super(container, name);
		title = getControlID();
	}
	
	/**
	 * Searches for the root page of the specified control by
	 * iterating up the control hierachy until it reaches the end.
	 * Return <code>null</code> if the root element is not a Page.
	 *   
	 * @param control
	 * @return
	 */
	public static Page findPage(IControl control) {
		
		IControlContainer container = control.getContainer();
		Object o = control;
		while (container instanceof ControlContainer) {
			ControlContainer cc = (ControlContainer)container;
			container = cc.getContainer();
			o = cc;
		}

		if (container instanceof SessionContext) {
			if (o instanceof Page) {
				return (Page)o;
			}
		}
		return null;
	}
	
	/**
	 * Add a IPageListener.
	 * @param pageListener
	 */
	public void addPageListener(IPageListener pageListener) {
		listeners.add(pageListener);
	}
	
	/**
	 * Remove a pageListener.
	 * @param pageListener
	 */
	public void removePageListener(IPageListener pageListener) {
		listeners.remove(pageListener);
	}
	
	/**
	 * Fire a new page event.
	 * @param type
	 * @param event
	 */
	protected void fireEvent(EventType type, PageEvent event) {
		
		IPageListener[] lst = new IPageListener[listeners.size()];
		lst = listeners.toArray(lst);
		for (IPageListener listener : lst) {
			switch (type) {
			case PAGE_SIZE_CHANGED:
				listener.pageSizeChanged(event);
				break;
			}
		}
		
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
	}
	/**
	 * @return Returns the clientHeight.
	 * @deprecated use getPageSize().height
	 */
	public int getClientHeight() {
		return pageSize.height;
	}

	/**
	 * @return Returns the clientLeft.
	 */
	public int getClientLeft() {
		return clientLeft;
	}
	
	/**
	 * @param clientLeft The clientLeft to set.
	 */
	public void setClientLeft(int clientLeft) {
		this.clientLeft = clientLeft;
	}
	/**
	 * @return Returns the clientTop.
	 */
	public int getClientTop() {
		return clientTop;
	}
	/**
	 * @param clientTop The clientTop to set.
	 */
	public void setClientTop(int clientTop) {
		this.clientTop = clientTop;
	}
	/**
	 * @return Returns the clientWidth.
	 * @deprecated Use getPageSize().width
	 */
	public int getClientWidth() {
		return pageSize.width;
	}
	/**
	 * @return Returns the multipart.
	 */
	public boolean isMultipart() {
		return multipart;
	}
	/**
	 * Set to true if the data on the page should be submited using multipart encoding.
	 * This is required to handle file uploads.
	 * @param multipart The multipart to set.
	 */
	public void setMultipart(boolean multipart) {
		this.multipart = multipart;
		requireRedraw();
	}
	/**
	 * @return Returns the forceFocusElement.
	 */
	public String getForceFocusElement() {
		return forceFocusElement;
	}
	/**
	 * @param forceFocusElement The forceFocusElement to set.
	 */
	public void setForceFocusElement(String forceFocusElement) {
		this.forceFocusElement = forceFocusElement;
	}
	/**
	 * @param page The page to get the client width and height from .
	 */
	public void setClientSettings(Page page) {
		// Fix for request id 1388862
		setPageSize(page.getPageSize());
	}
	/**
	 * @return the pageSize
	 */
	public Dimension getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Dimension pageSize) {
		if (!this.pageSize.equals(pageSize)) {
			this.pageSize = pageSize;
			fireEvent(EventType.PAGE_SIZE_CHANGED, new PageEvent(this, pageSize));
		}
		
	}
}
