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
 * de.jwic.ecolib.controls.stackpanel.StackPanelControl
 * Created on 20.04.2007
 * $Id: StackPanelControl.java,v 1.2 2008/09/18 18:20:42 lordsam Exp $
 */
package de.jwic.ecolib.controls.stackpanel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;

/**
 * A panel that stacks its children vertically, displaying only one at 
 * a time, with a header for each child which the user can click to display.
 * 
 * @author Sebastian
 */
public class StackPanelControl extends ControlContainer {
	/**
	 * the serialVersionID
	 */
	private static final long serialVersionUID = -5224879969993697644L;

	/** the stacks */
	private List<Stack> stacks;

	/** width in pixel */
	private int width;

	/** height in pixel */
	private int height;

	/** the default stackheight */
	private int stackHeight = 25;

	/** the selected stack */
	private String selectedStack = "";

	/** the registered listeners */
	private List<StackPanelChangeListener> listeners = Collections.synchronizedList(new ArrayList<StackPanelChangeListener>());

	/**
	 * 
	 * @param container
	 *            the parent container
	 * @param name
	 *            name of the component
	 */
	public StackPanelControl(IControlContainer container, String name) {
		super(container, name);
		stacks = new ArrayList<Stack>();
	}

	/**
	 * 
	 * This method is called when a stack get's clicked. The stack with the
	 * given title get's selected if it isn't yet and the stackpanelchangeevent
	 * gets fired.
	 * 
	 * @param name
	 *            title of the stack to select
	 */
	public void actionSelectStack(String name) {
		if (!name.equals(selectedStack)) {
			String old = new String(selectedStack);
			this.selectedStack = name;
			requireRedraw();

			fireStackPanelChange(getStackByIdentifier(old),
					getStackByIdentifier(name));
		}
	}

	private Stack getStackByIdentifier(String identifier) {
		Stack stack = null;
		for (int i = 0, j = stacks.size(); i < j; i++) {
			if (identifier
					.equals(stacks.get(i).getUniqueIdentifier())) {
				stack = stacks.get(i);
				break;
			}
		}
		return stack;
	}

	/**
	 * This method calculates the height for a stack. If innerHeight is true,
	 * the size is calculated for the childcontrol, else the size of the
	 * complete stack (stack header and childcontrol) is calculated
	 * 
	 * @param stack
	 *            the stack
	 * @param innerHeight
	 *            inner height or complete
	 * @return the height
	 */
	public int calculateHeight(String stack, boolean innerHeight) {
		int h = innerHeight ? stackHeight : 0;
		if (selectedStack.equals(stack)) {
			return height - (stacks.size() * stackHeight) - h;
		} else {
			return stackHeight;
		}
	}

	/**
	 * 
	 * Create's a new stack with the given title and childcontrol.
	 * 
	 * @param title
	 *            the title
	 * @param childControl
	 *            the childcontrol
	 * @return the created stack
	 */
	public Stack createStackControl(String title, Control childControl) {
		Stack sc = new Stack(title, childControl, childControl.getName());
		sc.setUniqueIdentifier("advStackItem-" + stacks.size());
		stacks.add(sc);

		if ("".equals(selectedStack)) {
			this.selectedStack = title;
		}

		requireRedraw();

		return sc;
	}

	/**
	 * Register a new StackPanelChangeListener to this Panel
	 * 
	 * @param l -
	 *            the listener
	 */
	public void registerStackPanelChangeListener(StackPanelChangeListener l) {
		if (!listeners.contains(l)) {
			listeners.add(l);
		}
	}

	/**
	 * Remove a registered StackPanelListener from this Panel
	 * 
	 * @param l -
	 *            the listener
	 */
	public void removeStackPanelChangeListener(StackPanelChangeListener l) {
		if (listeners.contains(l)) {
			listeners.remove(l);
		}
	}

	/**
	 * Fire a StackPanelChangeEvent.
	 * 
	 * @param oldStack
	 *            the old selection
	 * @param newStack
	 *            the new selection
	 */
	private void fireStackPanelChange(Stack oldStack, Stack newStack) {
		StackPanelChangeEvent evt = new StackPanelChangeEvent(this, oldStack,
				newStack);

		for (int i = 0, j = listeners.size(); i < j; i++) {
			listeners.get(i)
					.stackPanelChanged(evt);
		}
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the selectedStack
	 */
	public String getSelectedStack() {
		return selectedStack;
	}

	/**
	 * @param selectedStack
	 *            the selectedStack to set
	 */
	public void setSelectedStack(String selectedStack) {
		this.selectedStack = selectedStack;
	}

	/**
	 * @return the stackHeight
	 */
	public int getStackHeight() {
		return stackHeight;
	}

	/**
	 * @param stackHeight
	 *            the stackHeight to set
	 */
	public void setStackHeight(int stackHeight) {
		this.stackHeight = stackHeight;
	}

	/**
	 * @return the stacks
	 */
	public List<Stack> getStacks() {
		return stacks;
	}

	/**
	 * @param stacks
	 *            the stacks to set
	 */
	public void setStacks(List<Stack> stacks) {
		this.stacks = stacks;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

}
