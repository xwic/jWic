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
 * $Id: InlineWindowDemo.java,v 1.2 2010/01/26 11:25:17 lordsam Exp $
 */
package de.jwic.samples.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.GroupControl;
import de.jwic.controls.InputBoxControl;
import de.jwic.controls.LabelControl;
import de.jwic.controls.InlineWindow;
import de.jwic.controls.layout.TableLayoutContainer;
import de.jwic.samples.controls.propeditor.PropertyEditorView;

/**
 * 
 * Demonstrates the usage of the WindowControl.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class InlineWindowDemo extends ControlContainer {

	private InlineWindow window;
	
	public InlineWindowDemo(IControlContainer container) {
		super(container);
		
		window = new InlineWindow(this, "control");
		window.setText("WindowTitle!");
		window.setTemplateName(InlineWindowDemo.class.getName() + "Content");
		window.setWidth(400);
		
		// create a little demo content.
		GroupControl group = new GroupControl(window, "group");
		group.setTitle("Properties");
		TableLayoutContainer cont = new TableLayoutContainer(group);
		cont.setColumnCount(2);
		new LabelControl(cont).setText("Property 1");
		new InputBoxControl(cont).setText("Value");
		new Button(cont).setTitle("Apply");
		
		
		// create the property editor
		PropertyEditorView propEditor = new PropertyEditorView(this, "propEditor");
		propEditor.setBean(window);
	}
		
}
