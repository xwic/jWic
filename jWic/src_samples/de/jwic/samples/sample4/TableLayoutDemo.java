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
 * de.jwic.samples.sample4.TableLayoutDemo
 * Created on 04.11.2005
 * $Id: TableLayoutDemo.java,v 1.3 2010/01/26 11:25:18 lordsam Exp $
 */
package de.jwic.samples.sample4;

import de.jwic.base.Application;
import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.Page;
import de.jwic.controls.Button;
import de.jwic.controls.InputBoxControl;
import de.jwic.controls.LabelControl;
import de.jwic.controls.RadioGroupControl;
import de.jwic.controls.layout.TableData;
import de.jwic.controls.layout.TableLayoutContainer;
import de.jwic.events.SelectionListener;

/**
 * Demonstrates the usage of the TableLayoutContainer.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class TableLayoutDemo extends Application {

	/* (non-Javadoc)
	 * @see de.jwic.base.Application#createRootControl(de.jwic.base.IControlContainer)
	 */
	public Control createRootControl(IControlContainer container) {
		
		getSessionContext().setExitURL("byebye.html");
		
		// create a Page and add the TableLayoutContainer
		Page page = new Page(container);
		page.setTitle("TableLayoutDemo");
		
		TableLayoutContainer table = new TableLayoutContainer(page);
		table.setColumnCount(4);
		
		table.setBorderSize(3);
		table.setCellPadding(10);
		table.setCellSpacing(3);
		table.setWidth("800");
		table.setAlign(TableLayoutContainer.ALIGN_CENTER);
		
		// make first and 3rd column with a fixed size of 150
		table.setColWidth(0, 100); 
		table.setColWidth(2, 80); 
		
		// add some labels and fields
		LabelControl label = new LabelControl(table);
		label.setText("Name");
		TableData td = new TableData();
		td.setCSSClass("caption");
		table.setLayoutData(label, td);
		
		InputBoxControl inpName = new InputBoxControl(table);
		inpName.setText("Name");
		inpName.setWidth(300);
		
		new LabelControl(table).setText("Type");
		RadioGroupControl lbType = new RadioGroupControl(table);
		lbType.addElement("Business Contact");
		lbType.addElement("Personal Contact");
		lbType.addElement("Other...");
		lbType.setColumns(1);
		table.setLayoutData(lbType, new TableData(TableData.ALIGN_LEFT, TableData.ALIGN_TOP, 1, 2));
		
		new LabelControl(table).setText("City");
		InputBoxControl inpCity = new InputBoxControl(table);
		inpCity.setText("City");
		inpCity.setWidth(300);
		
		table.setLayoutData(inpCity, new TableData(TableData.ALIGN_LEFT, 2, 1));
		
		
		// Add a button to be able to exit the application.
		
		Button btDone = new Button(table);
		btDone.setTitle("Done");
		btDone.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				getSessionContext().exit();
			};
		});
		table.setLayoutData(btDone, new TableData(TableData.ALIGN_CENTER, 4, 1));
		
		return page;
	}

}
