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
 * de.jwic.samples.controls.TabStripDemo
 * Created on Apr 3, 2007
 * $Id: TabStripDemo.java,v 1.4 2010/01/07 10:47:24 lordsam Exp $
 */
package de.jwic.samples.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.InputBoxControl;
import de.jwic.controls.LabelControl;
import de.jwic.controls.ListBoxControl;
import de.jwic.controls.RadioGroupControl;
import de.jwic.controls.TabControl;
import de.jwic.controls.TabStripControl;
import de.jwic.controls.InlineWindow;
import de.jwic.controls.layout.TableLayoutContainer;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;

/**
 * This class demonstrates the behaviour of the TabStripControl
 *
 * @author Aron Cotrau
 */
public class TabStripDemo extends ControlContainer {

	private TabStripControl tabStrip = null;
	
	public TabStripDemo(IControlContainer container) {
		super(container);
		
		InlineWindow win = new InlineWindow(this, "window");
		win.setWidth(650);
		//win.setAlign("center");
		win.setText("Tab Strip Demo");
		
		tabStrip = new TabStripControl(win, "tabStrip");
		tabStrip.setLocation(TabStripControl.LOCATION_TOP);
		
		TabControl tab = tabStrip.addTab("First tab", "first");
		LabelControl label = new LabelControl(tab);
		label.setText("This is the content of the first tab");
		TableLayoutContainer tlc = new TableLayoutContainer(tab);
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
		
		tab = tabStrip.addTab("Second tab");
		label = new LabelControl(tab);
		label.setText("This is the content of the second tab");
		tlc = new TableLayoutContainer(tab);
		tlc.setColumnCount(2);
		label1 = new LabelControl(tlc);
		label1.setText("Option 1");
		
		inp = new InputBoxControl(tlc);
		inp.setText("Abc");

		label2 = new LabelControl(tlc);
		label2.setText("Option 2");
		
		radio = new RadioGroupControl(tlc);
		radio.addElement("Red");
		radio.addElement("Green");
		radio.addElement("Blue");		
		
		ListBoxControl lbMode = new ListBoxControl(this, "lbMode");
		lbMode.addElement("Top area", "top");
		lbMode.addElement("Bottom area", "bottom");
		lbMode.addElement("Left area", "left");
		lbMode.addElement("Right area", "right");
		lbMode.setWidth(200);
		lbMode.setChangeNotification(true);
		lbMode.setSelectedKey(tabStrip.getLocation());
		lbMode.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				tabStrip.setLocation((String)event.getElement());
			};
		});
	}
}
