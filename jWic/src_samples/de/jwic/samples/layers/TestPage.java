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
 * de.jwic.sandbox.TestPage
 * Created on 20.06.2007
 * $Id: TestPage.java,v 1.2 2010/01/26 11:25:18 lordsam Exp $
 */
package de.jwic.samples.layers;

import de.jwic.base.IControlContainer;
import de.jwic.base.Page;
import de.jwic.controls.Button;
import de.jwic.controls.LabelControl;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 *
 * @author Florian Lippisch
 */
public class TestPage extends Page {

	private String openWindowUrl = "";
	private LabelControl lbInfo;
	
	/**
	 * @param container
	 * @param name
	 */
	public TestPage(IControlContainer container, String name) {
		super(container, name);
		
		setTitle("Testpage");
		
		Button btOpen = new Button(this, "btOpen");
		btOpen.setTitle("Open Window");
		btOpen.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				openTestWindow();
			}
		});
		
		lbInfo = new LabelControl(this, "lbInfo");
		
	}
	
	private void openTestWindow() {
		
		OtherPage otherPage = (OtherPage)getControl("otherPage");
		if (otherPage == null) {
			// if the control does not already exist (or has been destroyed),
			// create it..
			otherPage = new OtherPage(this, "otherPage");
			otherPage.addElementSelectedListener(new ElementSelectedListener() {
				public void elementSelected(ElementSelectedEvent event) {
					// update label...
					lbInfo.setText("Element Selected: " + event.getElement());
				}
			});
			// register the control with a layer id.
			// the layer id is used in the url to tell jWic wich "root" control to use.
			getSessionContext().addLayer("testWindow", otherPage);
		}
		
		openWindowUrl = getSessionContext().getCallBackURL() + "&layerid=testWindow";
		requireRedraw();
	}

	public String getOpenWindowUrl() {
		return openWindowUrl;
	}
	
	public void windowOpenend() {
		openWindowUrl = "";
	}
	
}
