/*
 * Copyright 2005-2008 jWic group (http://www.jwic.de)
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
 * de.jwic.ecolib.controls.balloon.BalloonControl
 * Created on Jun 5, 2008
 * $Id: BalloonControl.java,v 1.5 2010/05/11 13:21:18 lordsam Exp $
 */
package de.jwic.ecolib.controls.balloon;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControl;
import de.jwic.base.IControlContainer;
import de.jwic.base.IOuterLayout;
import de.jwic.base.MouseEvent;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 *
 * @author jbornema
 */
public class BalloonControl extends ControlContainer implements IOuterLayout {
	private static final long serialVersionUID = 1L;
	public final static int DIRECTION_POINTING_BOTTUM_LEFT = 0;
	public final static int DIRECTION_POINTING_TOP_LEFT = 1;
	public final static int DIRECTION_POINTING_BOTTUM_RIGHT = 2;
	public final static int DIRECTION_POINTING_TOP_RIGHT = 3;
	
	/*
	 * Click (left mouse) event type.
	 */
	public static String ON_EVENT_CLICK = "click";
	/*
	 * Context menu (right mouse) event type.
	 */
	public static String ON_EVENT_CONTEXT_MENU = "contextmenu";
	
	/**
	 * Returned object for getRegisterOnClick() and getRegisterOnContextMenu() methods representing
	 * an IControl registered a listener for an event.
	 *
	 * @author jbornema
	 */
	public class RegisterOnEvent implements Serializable {
		private static final long serialVersionUID = 1L;
		private String type;
		private IControl control;
		private SelectionListener selectionListener;
		/**
		 * @return the type
		 */
		public String getType() {
			return type;
		}
		/**
		 * @param type the type to set
		 */
		public void setType(String type) {
			this.type = type;
		}
		/**
		 * @return the control
		 */
		public IControl getControl() {
			return control;
		}
		/**
		 * @param control the control to set
		 */
		public void setControl(IControl control) {
			this.control = control;
		}
		/**
		 * @return the selectionListener
		 */
		public SelectionListener getSelectionListener() {
			return selectionListener;
		}
		/**
		 * @param selectionListener the selectionListener to set
		 */
		public void setSelectionListener(SelectionListener selectionListener) {
			this.selectionListener = selectionListener;
		}
		/**
		 * @return BalloonControl for this event
		 */
		public BalloonControl getBalloonControl() {
			return BalloonControl.this;
		}
	}
	
	private Set<RegisterOnEvent> registeredOnClick;
	private Set<RegisterOnEvent> registeredOnContextMenu;
	private String cssClass;
	private boolean show;
	private String onEvent;
	private int x;
	private int y;
	private int defaultPointingDirection;
	
	/**
	 * @param container
	 */
	public BalloonControl(IControlContainer container) {
		this(container, null);
	}

	/**
	 * @param container
	 * @param name
	 */
	public BalloonControl(IControlContainer container, String name) {
		super(container, name);
		
		setRendererId("jwic.renderer.OuterContainer");
		registeredOnClick = new HashSet<RegisterOnEvent>();
		registeredOnContextMenu = new HashSet<RegisterOnEvent>();
		show = false;
		cssClass = "balloon_default_";
		defaultPointingDirection = DIRECTION_POINTING_BOTTUM_LEFT;
	}

	/**
	 * Register control on click (left mouse button) with given listener.
	 * @param control
	 * @param listener
	 */
	public void registerOnClick(IControl control, SelectionListener listener) {
		RegisterOnEvent onEvent = new RegisterOnEvent();
		onEvent.setControl(control);
		onEvent.setSelectionListener(listener);
		onEvent.setType(ON_EVENT_CLICK);
		registeredOnClick.add(onEvent);
	}
	
	/**
	 * Register control on context menu (right mouse button) with given listener.
	 * @param control
	 * @param listener
	 */
	public void registerOnContextMenu(IControl control, SelectionListener listener) {
		RegisterOnEvent onEvent = new RegisterOnEvent();
		onEvent.setControl(control);
		onEvent.setSelectionListener(listener);
		onEvent.setType(ON_EVENT_CONTEXT_MENU);
		registeredOnContextMenu.add(onEvent);
	}
	
	/**
	 * @return RegisterOnEvent objects registered on click event (left mouse button).
	 */
	public Collection<RegisterOnEvent> getRegisteredOnClick() {
		return registeredOnClick;
	}
	
	/**
	 * @return RegisterOnEvent objects registered on context menu event (right mouse button).
	 */
	public Collection<RegisterOnEvent> getRegisteredOnContextMenu() {
		return registeredOnContextMenu;
	}
	
	/*(non-Javadoc)
	 * @see de.jwic.base.Control#getTemplateName()
	 */
	public String getTemplateName() {
		if (super.getTemplateName().equals(BalloonControl.class.getName())) {
			return null;
		}
		return super.getTemplateName();
	}

	/*(non-Javadoc)
	 * @see de.jwic.base.IOuterLayout#getOuterTemplateName()
	 */
    public String getOuterTemplateName() {
        return BalloonControl.class.getName();
    }
    
    /**
     * Show action triggered by java-script.
     * @param param
     */
    public void actionShow(String param) {
		String[] keys = param.split(";");
		setOnEvent(keys[0]);
		setX(Integer.parseInt(keys[1]));
		setY(Integer.parseInt(keys[2]));
		if (fireEvent()) {
			show();
		}
	}
    
    /**
     * Show action triggered by java.
     * @param param
     */
    public void actionShow(MouseEvent mouseEvent) {
    	if (mouseEvent == null) {
    		hide();
    		return;
    	}
		setOnEvent(mouseEvent.getType());
		setX(mouseEvent.getX());
		setY(mouseEvent.getY());
		if (fireEvent()) {
			show();
		}
	}
    
    /**
     * Returns true for showing the balloon.
     * If any listener set the event source to null no other listener
     * will be called and the balloon won't be shown. 
     * @return
     */
    protected boolean fireEvent() {
    	SelectionEvent event = new SelectionEvent(this);
    	Set<RegisterOnEvent> registerOn = registeredOnClick;
    	if (ON_EVENT_CONTEXT_MENU.equals(getOnEvent())) {
    		registerOn = registeredOnContextMenu;
    	}
    	for (Iterator<RegisterOnEvent> it = registerOn.iterator(); it.hasNext();) {
    		RegisterOnEvent onEvent = it.next();
    		onEvent.getSelectionListener().objectSelected(event);
    		// check for event interruption that prevents the balloon to pop-up 
    		if (event.getEventSource() == null) {
    			return false;
    		}
    	}
    	return true;
    }
	
    /**
     * Hide action triggered by java-script.
     * @param param
     */
	public void actionHide(String param) {
		String[] keys = param.split(";");
		if (keys.length == 3) {
			setOnEvent(keys[0]);
			int x = getX();
			int y = getY();
			setX(Integer.parseInt(keys[1]));
			setY(Integer.parseInt(keys[2]));
			// hide only if coordinates are different
			// this solves a problem in IE6 that re-opening a balloon gets hidden right after the display
			// caused by the fireAction "hide" send AFTER the fireAction "show"
			if (x != getX() || y != getY()) {
				hide();
			}
		} else {
			// compatible mode for initial balloon.js release
			hide();
		}
	}
	
	/**
	 * @return If the balloon is displayed.
	 */
	public boolean balloonVisible() {
		return show;
	}

	/**
	 * Show balloon, no event is fired.
	 */
	public void show() {
		show = true;
		// TODO create new method for lastMousePosition
		//x = -1;
		//y = -1;
		setRequireRedraw(true);
	}

	/**
	 * Hide balloon, no event is fired.
	 */
	public void hide() {
		show = false;
		setRequireRedraw(true);
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
	}
	
	/**
	 * @return the onEvent
	 */
	public String getOnEvent() {
		return onEvent;
	}

	/**
	 * @param onEvent the onEvent to set
	 */
	public void setOnEvent(String onEvent) {
		this.onEvent = onEvent;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the defaultPointingDirection
	 */
	public int getDefaultPointingDirection() {
		return defaultPointingDirection;
	}

	/**
	 * @param defaultPointingDirection the defaultPointingDirection to set
	 */
	public void setDefaultPointingDirection(int defaultPointingDirection) {
		this.defaultPointingDirection = defaultPointingDirection;
	}
	
}