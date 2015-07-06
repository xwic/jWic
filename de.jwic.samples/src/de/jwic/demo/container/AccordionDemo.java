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
import de.jwic.controls.LabelControl;
import de.jwic.controls.Tab;
import de.jwic.controls.accordion.Accordion;
import de.jwic.controls.accordion.Panel;
import de.jwic.demo.basics.ButtonDemo;
import de.jwic.demo.basics.Calculator;
import de.jwic.demo.basics.LabelDemo;
import de.jwic.demo.basics.ToolBarDemo;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;
import de.jwic.samples.controls.propeditor.PropertyEditorView;

/**
 * Accordion Control Demo
 * @author dotto
 *
 */
public class AccordionDemo extends ControlContainer {


	public AccordionDemo(IControlContainer container, String name) {
		super(container, name);
		
		createControls();
	}

	private void createControls() {
		final Accordion accordion = new Accordion(this, "accordion");
		
		Panel p1 = accordion.createPanel("Selection 1");
				
		Panel p2 = accordion.createPanel("Selection 2");
		
		Panel p3 = accordion.createPanel("Selection 3");
				
		LabelControl lbl = new LabelControl(p1);
		lbl.setText("The TabStrip control is using the jQuery UI control 'Tabs' to visualize a container that displays" +
				" only one child at a time with a selector on top or bottom. The 'strip' on top is handled using JavaScript" +
				" without a client/server refresh, but the content area is re-rendered when activated on the server and " +
				" then refreshed."); 
		
		Calculator calculator = new Calculator(p2, "calculator");
		
		LabelControl lbl2 = new LabelControl(p3);
		lbl2.setText("SourceViewer here would be handy...");
		
		Button btNext = new Button(this, "button");
		btNext.setTitle("Next Accordion");
		btNext.addSelectionListener(new SelectionListener() {
			
			@Override
			public void objectSelected(SelectionEvent event) {
				int nextIndex = (accordion.getActiveIndex() +1) % 3;
				accordion.setActiveIndex(nextIndex);
			}
		});
		
		final PropertyEditorView propEditor = new PropertyEditorView(this, "propEditor");
		propEditor.setBean(accordion);
						
		propEditor.loadValues(); // refresh values.
		
	}

}
