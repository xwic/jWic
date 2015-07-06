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
import de.jwic.controls.GroupControl;
import de.jwic.controls.InputBox;
import de.jwic.controls.LabelControl;
import de.jwic.controls.ListBoxControl;
import de.jwic.controls.RadioGroupControl;
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
public class GroupControlDemo extends ControlContainer {

	private GroupControl groupControl;
	
	private LabelControl lblTitle = null;
	private InputBox inpTitle = null;
	private Button butTitle = null;
	
	public GroupControlDemo(IControlContainer container) {
		this(container, null);
	}

	public GroupControlDemo(IControlContainer container, String name) {
		super(container, name);

		groupControl = createDemoGroup("group1");

		
		lblTitle = new LabelControl(this, "lblTitle");
		lblTitle.setText("Title");
		
		inpTitle = new InputBox(this, "inpTitle");
		inpTitle.setWidth(200);
		inpTitle.setText(groupControl.getTitle());
		
		butTitle = new Button(this, "butTitle");
		butTitle.setTitle("Set new title");
		butTitle.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				groupControl.setTitle(inpTitle.getText());
			}
		});
		
		// Change Listbox Width
		ListBoxControl lbWidth = new ListBoxControl(this, "lbWidth");
		lbWidth.addElement("0 - Unspecified", "0");
		for (int i = 50; i < 401; i += 50) {
			lbWidth.addElement(Integer.toString(i) + "px", Integer.toString(i));
		}
		lbWidth.setSelectedKey(Integer.toString(groupControl.getWidth()));
		lbWidth.setChangeNotification(true);
		lbWidth.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				groupControl.setWidth(Integer.parseInt((String)event.getElement()));
			};
		});
	
		// Change Height
		ListBoxControl lbHeight = new ListBoxControl(this, "lbHeight");
		lbHeight.addElement("0 - Unspecified", "0");
		for (int i = 50; i < 401; i += 50) {
			lbHeight.addElement(Integer.toString(i) + "px", Integer.toString(i));
		}
		lbHeight.setSelectedKey(Integer.toString(groupControl.getHeight()));
		lbHeight.setChangeNotification(true);
		lbHeight.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				groupControl.setHeight(Integer.parseInt((String)event.getElement()));
			};
		});
		
		ListBoxControl lbCssClass = new ListBoxControl(this, "lbLayout");
		for (GroupControl.GroupControlLayout layout : GroupControl.GroupControlLayout.values()) {
			lbCssClass.addElement(layout.name());
		}
		lbCssClass.setChangeNotification(true);
		lbCssClass.setSelectedKey(groupControl.getCssClass());
		lbCssClass.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				groupControl.setLayout(GroupControl.GroupControlLayout.valueOf((String)event.getElement()));
			};
		});
		
	}
	
	private GroupControl createDemoGroup(String name) {
		
		GroupControl group = new GroupControl(this, name);
		group.setTitle("Group title");
		group.setTemplateName(getClass().getName() + "_demogroup");
		group.setCloseable(true);
		
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
		
		group.setCssClass("grpDefault");
		return group;
	}
}
