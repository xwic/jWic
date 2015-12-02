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
package de.jwic.demo.basics;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.InputBox;
import de.jwic.controls.LabelControl;
import de.jwic.events.SelectionListener;

/**
 * 
 * Demonstrates the usage of the LabelControl.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class LabelDemo extends ControlContainer {

	private LabelControl label;
	private InputBox text;
	private Button btVisible;
	
	public LabelDemo(IControlContainer container) {
		super(container);
		
		label = new LabelControl(this, "label");
		label.setText("jWic - a pleasure to work with.");
		
		text = new InputBox(this, "text");
		text.setText(label.getText());
		text.setEmptyInfoText("Fill in a text for the label here..");
		text.setWidth(400);	// width in px
		
		Button btApply = new Button(this, "btApply");
		btApply.setTitle("Apply");
		btApply.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				applyText();
			};
		});

		btVisible = new Button(this, "btVisible");
		btVisible.setTitle(label.isVisible() ? "Set Invisible" : "Set Visible");
		btVisible.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				changeVisible();
			};
		});

		
	}

	/**
	 * Change the Visible flag of the label.
	 */
	protected void changeVisible() {
		
		label.setVisible(!label.isVisible());
		btVisible.setTitle(label.isVisible() ? "Set Invisible" : "Set Visible");
		
	}

	/**
	 * Change the text of the Label.
	 */
	protected void applyText() {
		
		label.setText(text.getText());
		
	}
	
}
