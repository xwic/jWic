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
 * $Id: DateInputBoxDemo.java,v 1.4 2010/04/22 16:00:11 lordsam Exp $
 */
package de.jwic.samples.controls;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.DateInputBoxControl;
import de.jwic.controls.InputBoxControl;
import de.jwic.controls.ListBoxControl;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SelectionListener;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;

/**
 * 
 * Demonstrates the usage of the LabelControl.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.4 $
 */
public class DateInputBoxDemo extends ControlContainer {

	private DateInputBoxControl inputbox;
	
	private InputBoxControl text;
	private InputBoxControl inpPattern;
	private Button btVisible;
	private Button btEnabled;
	private Button btReadonly;	
	private Button btMulti;
	private Button btPassword;
	private ListBoxControl eventLog;
	
	private class EventLogListener implements ValueChangedListener {
		/* (non-Javadoc)
		 * @see de.jwic.events.ValueChangedListener#valueChanged(de.jwic.events.ValueChangedEvent)
		 */
		public void valueChanged(ValueChangedEvent event) {
			
			DateFormat df = DateFormat.getDateTimeInstance();
			String eventInfo = df.format(new Date()) + ": valueChanged - old value: '" + event.getOldValue() + "' new value: '" + event.getNewValue() + "'";
			
			eventLog.addElement(eventInfo);
		}
	}

	public DateInputBoxDemo(IControlContainer container) {
		super(container);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2006);
		calendar.set(Calendar.MONTH, Calendar.JUNE);
		calendar.set(Calendar.DAY_OF_MONTH, 15);
		
		inputbox = new DateInputBoxControl(this, "inputbox");
		inputbox.setDate(calendar.getTime());
		
		inputbox.addValueChangedListener(new EventLogListener());
		//inputbox.setTextInputEnabled(false);
		
		text = new InputBoxControl(this, "text");
		text.setText(inputbox.getText());
		text.setWidth(400);	// width in px
		
		inpPattern = new InputBoxControl(this, "inpPattern");
		inpPattern.setText(inputbox.getDatePattern());
		inpPattern.setWidth(400);	// width in px
		
		Button btApply = new Button(this, "btApply");
		btApply.setTitle("Apply");
		btApply.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				applyText();
			};
		});

		Button btApplyPattern = new Button(this, "btApplyPattern");
		btApplyPattern.setTitle("Apply");
		btApplyPattern.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				applyPattern();
			};
		});

		
		// Change Width
		ListBoxControl lbWidth = new ListBoxControl(this, "lbWidth");
		lbWidth.addElement("0 - Unspecified", "0");
		for (int i = 50; i < 401; i += 50) {
			lbWidth.addElement(Integer.toString(i) + "px", Integer.toString(i));
		}
		lbWidth.setSelectedKey(Integer.toString(inputbox.getWidth()));
		lbWidth.setChangeNotification(true);
		lbWidth.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				setInputBoxWidth(Integer.parseInt((String)event.getElement()));
			};
		});
	
		// Change Height
		ListBoxControl lbHeight = new ListBoxControl(this, "lbHeight");
		lbHeight.addElement("0 - Unspecified", "0");
		for (int i = 50; i < 401; i += 50) {
			lbHeight.addElement(Integer.toString(i) + "px", Integer.toString(i));
		}
		lbHeight.setSelectedKey(Integer.toString(inputbox.getHeight()));
		lbHeight.setChangeNotification(true);
		lbHeight.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				setInputBoxHeight(Integer.parseInt((String)event.getElement()));
			};
		});

		// Change Rows property
		ListBoxControl lbRows = new ListBoxControl(this, "lbRows");
		ListBoxControl lbCols = new ListBoxControl(this, "lbCols");
		for (int i = 0; i < 50; i++) {
			lbRows.addElement(Integer.toString(i));
			lbCols.addElement(Integer.toString(i));
		}
		lbRows.setChangeNotification(true);
		lbRows.setSelectedKey(Integer.toString(inputbox.getRows()));
		lbRows.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				inputbox.setRows(Integer.parseInt((String)event.getElement()));
			};
		});
		
		lbCols.setChangeNotification(true);
		lbCols.setSelectedKey(Integer.toString(inputbox.getCols()));
		lbCols.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				inputbox.setCols(Integer.parseInt((String)event.getElement()));
			};
		});


		btVisible = new Button(this, "btVisible");
		btVisible.setTitle(inputbox.isVisible() ? "Set Invisible" : "Set Visible");
		btVisible.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				changeVisible();
			};
		});

		btEnabled = new Button(this, "btEnabled");
		btEnabled.setTitle(inputbox.isEnabled() ? "Disable" : "Enable");
		btEnabled.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				changeEnabled();
			};
		});

		btReadonly = new Button(this, "btReadonly");
		btReadonly.setTitle(inputbox.isReadonly() ? "True" : "False");
		btReadonly.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				changeReadonly();
			};
		});

		btMulti = new Button(this, "btMulti");
		btMulti.setTitle(inputbox.isMultiLine() ? "Disable" : "Enable");
		btMulti.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				changeMultiple();
			};
		});

		btPassword = new Button(this, "btPassword");
		btPassword.setTitle(inputbox.isPassword() ? "Disable" : "Enable");
		btPassword.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				changePassword();
			};
		});

		
		eventLog = new ListBoxControl(this, "eventLog");
		eventLog.setSize(8);
	
	}
	
	protected void applyPattern() {

		inputbox.setDatePattern(inpPattern.getText());
		
	}

	/**
	 * @param i
	 */
	protected void setInputBoxHeight(int i) {
		inputbox.setHeight(i);
	}

	/**
	 * Change between multiple and single selection.
	 */
	protected void changePassword() {
		
		inputbox.setPassword(!inputbox.isPassword());
		btPassword.setTitle(inputbox.isPassword() ? "Disable" : "Enable");
		
	}

	/**
	 * Change between multiple and single selection.
	 */
	protected void changeMultiple() {
		
		inputbox.setMultiLine(!inputbox.isMultiLine());
		btMulti.setTitle(inputbox.isMultiLine() ? "Disable" : "Enable");
		
	}

	/**
	 * Set the with of the input box in px.
	 */
	protected void setInputBoxWidth(int i) {
		
		inputbox.setWidth(i);
		
	}

	/**
	 * Change the Enabled property of the button.
	 */
	protected void changeEnabled() {
		
		inputbox.setEnabled(!inputbox.isEnabled());
		btEnabled.setTitle(inputbox.isEnabled() ? "Disable" : "Enable");
		
	}

	/**
	 * Change the Enabled property of the button.
	 */
	protected void changeReadonly() {
		
		inputbox.setReadonly(!inputbox.isReadonly());
		btReadonly.setTitle(inputbox.isReadonly() ? "True" : "False");
		
	}

	/**
	 * Change the Visible flag of the label.
	 */
	protected void changeVisible() {
		
		inputbox.setVisible(!inputbox.isVisible());
		btVisible.setTitle(inputbox.isVisible() ? "Set Invisible" : "Set Visible");
		
	}

	/**
	 * Change the text of the Label.
	 */
	protected void applyText() {
		
		inputbox.setText(text.getText());
		
	}
	
}
