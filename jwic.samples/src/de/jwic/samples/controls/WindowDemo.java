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
 * $Id: WindowDemo.java,v 1.4 2010/02/07 14:24:24 lordsam Exp $
 */
package de.jwic.samples.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.GroupControl;
import de.jwic.controls.InputBoxControl;
import de.jwic.controls.LabelControl;
import de.jwic.controls.ListBoxControl;
import de.jwic.controls.Window;
import de.jwic.controls.layout.TableLayoutContainer;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.samples.controls.propeditor.PropertyEditorView;

/**
 * 
 * Demonstrates the usage of the Window control.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.4 $
 */
public class WindowDemo extends ControlContainer {

	private Window window;
	
	public WindowDemo(IControlContainer container) {
		super(container);
		
		window = new Window(this, "WINDOW");
		window.setTitle("Sample Window");
		window.setModal(false);
		window.setWidth(500);
		
		// create a little demo content.
		GroupControl group = new GroupControl(window, "group");
		group.setTitle("Properties");
		group.setFillWidth(true);
		TableLayoutContainer cont = new TableLayoutContainer(group);
		cont.setColumnCount(2);
		cont.setWidth("100%");
		cont.setColWidth(0, 150);

		LabelControl lbl = new LabelControl(cont);
		lbl.setText("Property 1");
		
		final InputBoxControl inp = new InputBoxControl(cont);
		inp.setFillWidth(true);
		inp.setEmptyInfoText("Make Selection..");
		
		Button button =  new Button(cont,"BUTTON");
		button.setTitle("Apply");
		button.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				onApply();
				System.out.println(inp.getText());
				inp.setText("Hello");
			}
		});
				
		ListBoxControl lb = new ListBoxControl(cont,"LISTBOX");
		lb.setChangeNotification(true);
		lb.addElement("Alpha");
		lb.addElement("Red");
		lb.addElement("Hot");
		lb.addSelectionListener(new SelectionListener() {
			
			@Override
			public void objectSelected(SelectionEvent event) {
				System.out.println("\n\n"+event.toString()+"\n\n");
				
			}
		});
		lb.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				System.out.println("\n\n"+event.getElement().toString()+"\n\n");
				inp.setText(event.getElement().toString());
				
			}
		});
		
		
		// create the property editor
		PropertyEditorView propEditor = new PropertyEditorView(this, "propEditor");
		propEditor.setBean(window);
	}

	/**
	 * 
	 */
	protected void onApply() {
	//	window.setVisible(false); // close it
		System.out.println("Hello world");
		window.setTitle("Clicked on the button");
		
	}
		
}
