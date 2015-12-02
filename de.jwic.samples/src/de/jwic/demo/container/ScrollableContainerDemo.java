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
package de.jwic.demo.container;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.InputBox;
import de.jwic.controls.LabelControl;
import de.jwic.controls.ListBoxControl;
import de.jwic.controls.RadioGroupControl;
import de.jwic.controls.ScrollableContainer;
import de.jwic.controls.layout.TableLayoutContainer;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * This class demonstrates the Group Control
 *
 * @author Aron Cotrau
 */
public class ScrollableContainerDemo extends ControlContainer {

	private ScrollableContainer container;
	
	public ScrollableContainerDemo(IControlContainer container) {
		this(container, null);
	}

	public ScrollableContainerDemo(IControlContainer parent, String name) {
		super(parent, name);

		container = createDemoContent("group1");
		container.setWidth("300px");
		container.setHeight("150px");

		
		// Change Listbox Width
		ListBoxControl lbWidth = new ListBoxControl(this, "lbWidth");
		lbWidth.addElement("0 - Unspecified", "0");
		lbWidth.addElement("100%", "100%");
		for (int i = 50; i < 401; i += 50) {
			String size = Integer.toString(i) + "px";
			lbWidth.addElement(size, size);
		}
		lbWidth.setSelectedKey(container.getWidth());
		lbWidth.setChangeNotification(true);
		lbWidth.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				container.setWidth((String)event.getElement());
			};
		});
	
		// Change Height
		ListBoxControl lbHeight = new ListBoxControl(this, "lbHeight");
		lbHeight.addElement("0 - Unspecified", "0");
		lbHeight.addElement("100%", "100%");
		for (int i = 50; i < 401; i += 50) {
			String size = Integer.toString(i) + "px";
			lbHeight.addElement(size, size);
		}
		lbHeight.setSelectedKey(container.getHeight());
		lbHeight.setChangeNotification(true);
		lbHeight.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				container.setHeight((String)event.getElement());
			};
		});
		
		Button btVisibility = new Button(this, "btVisibility");
		btVisibility.setTitle("Toggle Visibility");
		btVisibility.addSelectionListener(new SelectionListener() {
			@Override
			public void objectSelected(SelectionEvent event) {
				container.setVisible(!container.isVisible());
			}
		});
		
	}
	
	private ScrollableContainer createDemoContent(String name) {
		
		ScrollableContainer group = new ScrollableContainer(this, name);
		group.setTemplateName(getClass().getName() + "_demogroup");
		
		TableLayoutContainer tlc = new TableLayoutContainer(group, "table");
		tlc.setColumnCount(2);
		LabelControl label1 = new LabelControl(tlc);
		label1.setText("Option 1");
		
		InputBox inp = new InputBox(tlc);
		inp.setText("Abc");

		LabelControl label2 = new LabelControl(tlc);
		label2.setText("Option 2");
		
		RadioGroupControl radio = new RadioGroupControl(tlc);
		radio.addElement("Red");
		radio.addElement("Green");
		radio.addElement("Blue");
		
		return group;
	}
}
