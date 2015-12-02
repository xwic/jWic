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

import java.text.SimpleDateFormat;
import java.util.Date;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.Label;
import de.jwic.controls.ListBoxControl;
import de.jwic.controls.StackedContainer;
import de.jwic.demo.basics.Calculator;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * 
 * Demonstrates the usage of the TabStripControl.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class StackedContainerDemo extends ControlContainer {

	private StackedContainer stackedContainer;
	private Calculator calculator;

	public StackedContainerDemo(IControlContainer container) {
		super(container);
		
		stackedContainer = new StackedContainer(this, "container");
		
		Label lbl = new Label(stackedContainer, "label1");
		lbl.setText("The TabStrip control is using the jQuery UI control 'Tabs' to visualize a container that displays" +
				" only one child at a time with a selector on top or bottom. The 'strip' on top is handled using JavaScript" +
				" without a client/server refresh, but the content area is re-rendered when activated on the server and " +
				" then refreshed."); 
		
		calculator = new Calculator(stackedContainer, "calculator");
		
		
		Label lbl2 = new Label(stackedContainer, "source");
		lbl2.setText("SourceViewer here would be handy...");
		
		// modifications
		
		Button btActivateFirstTab = new Button(this, "btActivateFirst");
		btActivateFirstTab.setTitle("Activate Label 1");
		btActivateFirstTab.addSelectionListener(new SelectionListener() {
			@Override
			public void objectSelected(SelectionEvent event) {
				stackedContainer.setCurrentControlName("label1");
			}
		});
		
		Button btActivate2ndTab = new Button(this, "btActivate2nd");
		btActivate2ndTab.setTitle("Activate Calculator");
		btActivate2ndTab.addSelectionListener(new SelectionListener() {
			@Override
			public void objectSelected(SelectionEvent event) {
				// another common approach is to get the name from the control you want to display now
				stackedContainer.setCurrentControlName(calculator.getName()); 
			}
		});
		
		Button btAddTab = new Button(this, "btAddControl");
		btAddTab.setTitle("Add Control");
		btAddTab.addSelectionListener(new SelectionListener() {
			@Override
			public void objectSelected(SelectionEvent event) {
				Label label = new Label(stackedContainer);
				label.setText("This new label was created at " + 
						new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss")
						.format(new Date()));
				stackedContainer.setCurrentControlName(label.getName());
			}
		});

		Button btRandomNum = new Button(this, "btRandomNum");
		btRandomNum.setTitle("Randomize Calculator");
		btRandomNum.addSelectionListener(new SelectionListener() {
			@Override
			public void objectSelected(SelectionEvent event) {
				calculator.doSomeRandomization();
			}
		});
		
		// Change Listbox Width
		ListBoxControl lbWidth = new ListBoxControl(this, "lbWidth");
		lbWidth.addElement("0 - Unspecified", "0");
		for (int i = 50; i < 401; i += 50) {
			String size = Integer.toString(i) + "px";
			lbWidth.addElement(size, Integer.toString(i));
		}
		lbWidth.setSelectedKey("0");
		lbWidth.setChangeNotification(true);
		lbWidth.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				String width = (String)event.getElement();
				stackedContainer.setWidth(Integer.parseInt(width));
			};
		});
	
		// Change Height
		ListBoxControl lbHeight = new ListBoxControl(this, "lbHeight");
		lbHeight.addElement("0 - Unspecified", "0");
		for (int i = 50; i < 401; i += 50) {
			String size = Integer.toString(i) + "px";
			lbHeight.addElement(size, Integer.toString(i));
		}
		lbHeight.setSelectedKey("0");
		lbHeight.setChangeNotification(true);
		lbHeight.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				String height = (String)event.getElement();
				stackedContainer.setHeight(Integer.parseInt(height));
			};
		});

	}
	
}
