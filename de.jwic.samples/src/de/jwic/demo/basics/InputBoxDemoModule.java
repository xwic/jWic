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
/**
 * 
 */
package de.jwic.demo.basics;

import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.DatePicker;
import de.jwic.controls.InputBox;
import de.jwic.controls.Label;
import de.jwic.controls.LabelControl;
import de.jwic.controls.NumericInputBox;
import de.jwic.controls.ValidatedInputBox;
import de.jwic.controls.layout.TableLayoutContainer;
import de.jwic.demo.DemoModule;
import de.jwic.events.KeyEvent;
import de.jwic.events.KeyListener;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * @author lippisch
 *
 */
public class InputBoxDemoModule extends DemoModule {
	
	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * 
	 */
	public InputBoxDemoModule() {
		setTitle("Input Box");
		setDescription("A control that allows the user to enter text");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.demo.DemoModule#createModule(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createModule(IControlContainer container) {

		TableLayoutContainer tlc = new TableLayoutContainer(container);
		tlc.setColumnCount(2);

		new LabelControl(tlc).setText("Basic Input Field");
		final InputBox inptBox = new InputBox(tlc);
		inptBox.setListenKeyCode(13);
		inptBox.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent event) {
				log.debug("Key pressed for InputBox with listen key code " + inptBox.getListenKeyCode());
			}
		});

		new LabelControl(tlc).setText("With content");
		InputBox txt = new InputBox(tlc);
		txt.setWidth(500);
		txt.setText("This is text in a InputBox. It can be modified.");

		new LabelControl(tlc).setText("I am disabled");
		InputBox txtDis = new InputBox(tlc);
		txtDis.setWidth(500);
		txtDis.setEnabled(false);
		txtDis.setText("This is text in a InputBox. It can be modified.");

		new LabelControl(tlc).setText("TextArea");
		InputBox txtArea = new InputBox(tlc);
		txtArea.setWidth(500);
		txtArea.setMultiLine(true);
		txtArea.setHeight(100);
		txtArea.setText("This is a multiline InputBox with specific dimensions.");

		new LabelControl(tlc).setText("Empty with EmptyInfoText");
		InputBox txt2 = new InputBox(tlc);
		txt2.setWidth(500);
		txt2.setEmptyInfoText("Enter something smart here..");

		new LabelControl(tlc).setText("In Error State");
		InputBox txt3 = new InputBox(tlc);
		txt3.setWidth(500);
		txt3.setFlagAsError(true);
		txt3.setText("This is not correct.");

		new LabelControl(tlc).setText("NumericInputBox");
		final NumericInputBox numInp = new NumericInputBox(tlc);
		numInp.setWidth(120);
		numInp.setNumber(4d);
		numInp.setListenKeyCode(13);
		numInp.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent event) {
				log.debug("Key pressed for NumericInputBox with listen key code " + numInp.getListenKeyCode());
			}
		});

		new LabelControl(tlc).setText("DatePicker");
		DatePicker datePicker = new DatePicker(tlc);
		datePicker.setWidth(500);
		datePicker.setEmptyInfoText("Select a date..");

		new Label(tlc).setText("Validated Input (Here its an email address) :");
		final ValidatedInputBox vib = new ValidatedInputBox(tlc);
		vib.setRegExp(ValidatedInputBox.EMAIL_PATTERN); // validates an email
														// address

	}

}
