/*
 * Copyright 2005 jWic group (http://www.jwic.de)
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
 * de.jwic.samples.controls.LabelDemo
 * Created on 28.10.2005
 * $Id: LabelDemo.java,v 1.2 2010/01/26 11:25:17 lordsam Exp $
 */
package de.jwic.demo.basics;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.basics.Button;
import de.jwic.controls.basics.Label;
import de.jwic.controls.basics.Tab;
import de.jwic.controls.basics.TabStrip;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * 
 * Demonstrates the usage of the TabStripControl.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class TabStripDemo extends ControlContainer {

	private TabStrip tabStrip;

	public TabStripDemo(IControlContainer container) {
		super(container);
		
		tabStrip = new TabStrip(this, "tabStrip");
		
		Tab tOverview = tabStrip.addTab("Overview", "overview");
		
		Label lbl = new Label(tOverview);
		lbl.setText("The TabStrip control is using the jQuery UI control 'Tabs' to visualize a container that displays" +
				" only one child at a time with a selector on top or bottom. The 'strip' on top is handled using JavaScript" +
				" without a client/server refresh, but the content area is re-rendered when activated on the server and " +
				" then refreshed."); 
		
		Tab tDetails = tabStrip.addTab("Details", "details");
		new Calculator(tDetails, "calculator");
		
		
		Tab tSource = tabStrip.addTab("Source");
		Label lbl2 = new Label(tSource);
		lbl2.setText("SourceViewer here would be handy...");
		
		// modifications
		
		Button btActivateFirstTab = new Button(this, "btActivateFirstTab");
		btActivateFirstTab.setTitle("Activate Overview Tab");
		btActivateFirstTab.addSelectionListener(new SelectionListener() {
			@Override
			public void objectSelected(SelectionEvent event) {
				tabStrip.setActiveTabName("overview");
			}
		});
		
		Button btActivate2ndTab = new Button(this, "btActivate2ndTab");
		btActivate2ndTab.setTitle("Activate Details Tab");
		btActivate2ndTab.addSelectionListener(new SelectionListener() {
			@Override
			public void objectSelected(SelectionEvent event) {
				tabStrip.setActiveTabName("details");
			}
		});
		
		Button btAddTab = new Button(this, "btAddTab");
		btAddTab.setTitle("Add Tab");
		btAddTab.addSelectionListener(new SelectionListener() {
			@Override
			public void objectSelected(SelectionEvent event) {
				Tab newTab = tabStrip.addTab("New Tab #" + (tabStrip.getTabs().size() - 2));
				Label label = new Label(newTab);
				label.setText("This new tab was created at " + 
						new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss")
						.format(new Date()));
				tabStrip.setActiveTabName(newTab.getName());
			}
		});

		
	}
	
}
