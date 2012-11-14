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
 * de.jwic.samples.controls.RadioButtonDemo
 * Created on Jul 15, 2010
 * $Id: RadioButtonDemo.java,v 1.1 2010/07/15 11:59:14 lordsam Exp $
 */
package de.jwic.samples.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.LabelControl;
import de.jwic.controls.RadioButton;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;
import de.jwic.samples.controls.propeditor.PropertyEditorView;

/**
 * Demo for the RadioButton control.
 * @author lippisch
 */
public class RadioButtonDemo extends ControlContainer {

	private RadioButton rbApples;
	private RadioButton rbOranges;
	private RadioButton rbNoFruit;
	private LabelControl lbResult;

	/**
	 * @param container
	 * @param name
	 */
	public RadioButtonDemo(IControlContainer container, String name) {
		super(container, name);

		
		rbApples = new RadioButton(this, "rbApples");
		rbApples.setTitle("I like Apples");
		rbApples.setUserObject("So you like apple juice as well?");
		
		rbOranges = new RadioButton(this, "rbOranges", rbApples);
		rbOranges.setTitle("I like Oranges");
		rbOranges.setUserObject("Orange is a nice color as well!");
		
		rbNoFruit = new RadioButton(this, "rbNoFruit", rbApples);
		rbNoFruit.setTitle("I like no fruits at all!");
		rbNoFruit.setUserObject("Come on, they are so healthy!");
		
		lbResult = new LabelControl(this, "lbResult");
	
		updateResult();
		
		// create the property editor
		PropertyEditorView propEditor = new PropertyEditorView(this, "propEditor");
		propEditor.setBean(rbApples);
		
		rbApples.addValueChangedListener(new ValueChangedListener() {
			public void valueChanged(ValueChangedEvent event) {
				updateResult();
			}
		});

		
	}
	
	private void updateResult() {
		
		RadioButton rb = rbApples.getSelectedRadioButton();
		if (rb != null) {
			String txt = (String)rb.getUserObject();
			if (rbNoFruit.isSelected()) {						// just illustrate that status can be checked individually as well
				lbResult.setText("<i>" + txt + "</i>");
			} else {
				lbResult.setText(txt);
			}
		} else {
			lbResult.setText("Go ahead, choose what fruit you like..");
		}
		
	}
	

}
