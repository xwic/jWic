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
 * de.jwic.samples.sample2.Sample2
 * Created on 11.09.2005
 * $Id: Sample2.java,v 1.2 2010/01/26 11:25:19 lordsam Exp $
 */
package de.jwic.samples.sample2;

import de.jwic.base.Application;
import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.Page;
import de.jwic.controls.Button;
import de.jwic.controls.LabelControl;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * This sample displays a label with a value and two buttons to increase and
 * decrease the value displayed by the label.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class Sample2 extends Application {

	private LabelControl label = null;
	private int value = 0;
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IApplication#createRootControl(de.jwic.base.IControlContainer)
	 */
	public Control createRootControl(IControlContainer container) {
		
		// Create a page
		Page page = new Page(container);
		
		// Set the title the browser should display 
		page.setTitle("jWic Sample #2");
		
		// Specify a template for our sample. The template name
		// usualy contains the full package-name and a resource name.
		// A similar code would look like this:
		// page.setTemplateName(getClass().getName());
		page.setTemplateName("de.jwic.samples.sample2.Sample2");
		
		// now add the Label and the two Buttons.
		label = new LabelControl(page, "label");
		label.setText("0");

		Button btInc = new Button(page, "btInc");
		btInc.setTitle("Increase");
		btInc.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				modify(1);
			}
		});

		Button btDec = new Button(page, "btDec");
		btDec.setTitle("Decrease");
		btDec.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				modify(-1);
			}
		});
		
		return page;
	}

	/**
	 * Modify the current value and update the label.
	 * @param i
	 */
	protected void modify(int i) {

		value += i;
		label.setText(Integer.toString(value));
		
	}

}
