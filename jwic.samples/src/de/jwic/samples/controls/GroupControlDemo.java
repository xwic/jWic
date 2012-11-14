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
 * de.jwic.samples.controls.GroupControlDemo
 * Created on Mar 30, 2007
 * $Id: GroupControlDemo.java,v 1.10 2010/01/26 11:25:17 lordsam Exp $
 */
package de.jwic.samples.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.GroupControl;
import de.jwic.controls.InputBoxControl;
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

	private GroupControl[] groupControls;
	
	private LabelControl lblTitle = null;
	private InputBoxControl inpTitle = null;
	private Button butTitle = null;
	
	public GroupControlDemo(IControlContainer container) {
		this(container, null);
	}

	public GroupControlDemo(IControlContainer container, String name) {
		super(container, name);
		groupControls = new GroupControl[3];

		groupControls[0] = createDemoGroup("group1");
		groupControls[1] = createDemoGroup("group2");
		groupControls[2] = createDemoGroup("group3");
		
		lblTitle = new LabelControl(this, "lblTitle");
		lblTitle.setText("Title");
		
		inpTitle = new InputBoxControl(this, "inpTitle");
		inpTitle.setWidth(200);
		inpTitle.setText(groupControls[0].getTitle());
		
		butTitle = new Button(this, "butTitle");
		butTitle.setTitle("Set new title");
		butTitle.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				for (int i = 0; i < groupControls.length; i++) {
					groupControls[i].setTitle(inpTitle.getText());
				}
			}
		});
		
		// Change Listbox Width
		ListBoxControl lbWidth = new ListBoxControl(this, "lbWidth");
		lbWidth.addElement("0 - Unspecified", "0");
		for (int i = 50; i < 401; i += 50) {
			lbWidth.addElement(Integer.toString(i) + "px", Integer.toString(i));
		}
		lbWidth.setSelectedKey(Integer.toString(groupControls[0].getWidth()));
		lbWidth.setChangeNotification(true);
		lbWidth.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				for (int i = 0; i < groupControls.length; i++) {
					groupControls[i].setWidth(Integer.parseInt((String)event.getElement()));
				}
			};
		});
	
		// Change Height
		ListBoxControl lbHeight = new ListBoxControl(this, "lbHeight");
		lbHeight.addElement("0 - Unspecified", "0");
		for (int i = 50; i < 401; i += 50) {
			lbHeight.addElement(Integer.toString(i) + "px", Integer.toString(i));
		}
		lbHeight.setSelectedKey(Integer.toString(groupControls[0].getHeight()));
		lbHeight.setChangeNotification(true);
		lbHeight.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				for (int i = 0; i < groupControls.length; i++) {
					groupControls[i].setHeight(Integer.parseInt((String)event.getElement()));
				}
			};
		});
		
		ListBoxControl lbCssClass = new ListBoxControl(this, "lbCssClass");
		lbCssClass.addElement("Default syle", "grpDefault");
		lbCssClass.addElement("Bordered style", "grpDemo");
		lbCssClass.setChangeNotification(true);
		lbCssClass.setSelectedKey(groupControls[0].getCssClass());
		lbCssClass.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				for (int i = 0; i < groupControls.length; i++) {
					groupControls[i].setCssClass((String)event.getElement());
				}
			};
		});
		
	}
	
	private GroupControl createDemoGroup(String name) {
		GroupControl group = new GroupControl(this, name);
		group.setTitle("Group title");
		
		TableLayoutContainer tlc = new TableLayoutContainer(group);
		tlc.setColumnCount(2);
		LabelControl label1 = new LabelControl(tlc);
		label1.setText("Option 1");
		
		InputBoxControl inp = new InputBoxControl(tlc);
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
