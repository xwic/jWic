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
 * $Id: JavaScriptDemo.java,v 1.2 2011/06/02 12:30:18 lordsam Exp $
 */
package de.jwic.samples.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.samples.controls.propeditor.PropertyEditorView;

/**
 * 
 * Demonstrates the usage of the WindowControl.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class JavaScriptDemo extends ControlContainer {

	private JavaScriptDemoControl control;
	
	public JavaScriptDemo(IControlContainer container) {
		super(container);
		
		control = new JavaScriptDemoControl(this, "control");
		
		// create the property editor
		PropertyEditorView propEditor = new PropertyEditorView(this, "propEditor");
		propEditor.setBean(control);
		
		
		Button btState1 = new Button(this, "btState1");
		btState1.setTitle("Click Me");
		btState1.addSelectionListener(new SelectionListener() {
			/* (non-Javadoc)
			 * @see de.jwic.events.SelectionListener#objectSelected(de.jwic.events.SelectionEvent)
			 */
			public void objectSelected(SelectionEvent event) {
				control.setSomeString("This box is rather small I would say.");
				control.setWidth(60);
				control.setHeight(100);
			}
		});

		Button btState2 = new Button(this, "btState2");
		btState2.setTitle("Or me?");
		btState2.addSelectionListener(new SelectionListener() {
			/* (non-Javadoc)
			 * @see de.jwic.events.SelectionListener#objectSelected(de.jwic.events.SelectionEvent)
			 */
			public void objectSelected(SelectionEvent event) {
				control.setSomeString("Now this is what I call enough space! Anyone wants to rent a few pixel? :-)");
				control.setWidth(300);
				control.setHeight(100);
			}
		});

		
	}
		
}
