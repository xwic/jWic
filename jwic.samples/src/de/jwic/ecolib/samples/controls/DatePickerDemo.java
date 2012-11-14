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
 * de.jwic.ecolib.samples.controls.DatePickerDemo
 * Created on 13.11.2012
 * $Id:$
 */
package de.jwic.ecolib.samples.controls;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.ImageRef;
import de.jwic.controls.LabelControl;
import de.jwic.controls.combo.Combo;
import de.jwic.controls.combo.DropDown;
import de.jwic.data.ISelectElement;
import de.jwic.ecolib.controls.datapicker.DateChangedListener;
import de.jwic.ecolib.controls.datapicker.DatePickerControl;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;

/**
 *
 * @author bogdan
 */
public class DatePickerDemo extends ControlContainer {

	/**
	 * @param container
	 * @param name
	 */
	public DatePickerDemo(IControlContainer container, String name) {
		super(container, name);
		
		final DatePickerControl datePickerControl = new DatePickerControl(this, "datePicker");
		
		final LabelControl lbl = new LabelControl(this,"label");
		
		datePickerControl.addDateChangedListener(new DateChangedListener() {
			
			public void onDateChanged(Date oldDate, Date newDate) {
				lbl.setText("Selected Date is: "+newDate.toString());
			
			}
		});
		
		final DropDown localeCombo = new DropDown(this, "cbLocale");
		for (Locale locale : Locale.getAvailableLocales()) {
			localeCombo.addElement(new LocaleDd(locale));
		}
		
		localeCombo.addElementSelectedListener(new ElementSelectedListener() {
			
			public void elementSelected(ElementSelectedEvent event) {
				datePickerControl.setLocale(((LocaleDd)localeCombo.getSelectedElement()).getLocale()); 
			}
		});
		
	}
	
	private class LocaleDd implements ISelectElement {

		private Locale locale;
		
		public LocaleDd(Locale locale){
			this.locale = locale;
		}
		/* (non-Javadoc)
		 * @see de.jwic.data.ISelectElement#getKey()
		 */
		public String getKey() {
			
			return locale.getLanguage();
		}

		/* (non-Javadoc)
		 * @see de.jwic.data.ISelectElement#getTitle()
		 */
		public String getTitle() {
			
			return locale.getDisplayLanguage() + " - " + locale.getLanguage();
		}

		/* (non-Javadoc)
		 * @see de.jwic.data.ISelectElement#getImage()
		 */
		public ImageRef getImage() {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see de.jwic.data.ISelectElement#isSelectable()
		 */
		public boolean isSelectable() {
			return true;
		}
		/**
		 * @return the locale
		 */
		public Locale getLocale() {
			return locale;
		}
		
	}

}
