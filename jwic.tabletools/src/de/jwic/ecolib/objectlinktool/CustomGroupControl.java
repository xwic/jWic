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
 * de.jwic.ecolib.objectlinktool.CustomGroupControl
 * Created on 19.04.2007
 * $Id: CustomGroupControl.java,v 1.2 2012/08/29 07:46:52 lordsam Exp $
 */
package de.jwic.ecolib.objectlinktool;

import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.ListBoxControl;
import de.jwic.controls.SubFrameControl;
import de.jwic.ecolib.tableviewer.TableColumn;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 *
 * @author Florian Lippisch
 */
public class CustomGroupControl extends SubFrameControl {

	private ListBoxControl lbLeft = null;
	private ListBoxControl lbRight = null;
	private ListBoxControl lbPrecision = null;
	private ObjectLinkModel model = null;
	
	/**
	 * @param container
	 * @param name
	 */
	public CustomGroupControl(IControlContainer container, String name, ObjectLinkModel model) {
		super(container, name);
		
		setTitle("Group Data");
		this.model = model;
		
		lbLeft = new ListBoxControl(this, "lbLeft");
		lbRight = new ListBoxControl(this, "lbRight");
		lbPrecision = new ListBoxControl(this, "lbPrecision");
		for (int i = 1; i < 10; i++) {
			lbPrecision.addElement(Integer.toString(i));
		}
		lbPrecision.addElement("99");
		lbPrecision.setSelectedKey(Integer.toString(model.getPrecicion()));
		
		Button btGroup = new Button(this, "btGroup");
		btGroup.setTitle("Group");
		btGroup.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				doGroup();
			}
		});

	}

	private void doGroup() {
		String lKey = lbLeft.getSelectedKey();
		String rKey = lbRight.getSelectedKey();
		if (lKey != null && lKey.length() != 0 && rKey != null && rKey.length() != 0) {
			model.setPrecicion(Integer.parseInt(lbPrecision.getSelectedKey()));
			model.doGroup(Integer.parseInt(lKey), Integer.parseInt(rKey));
		}
	}

	public void setup(TableColumn[] left, TableColumn[] right) {
		setupGroupColumns(lbLeft, left);
		setupGroupColumns(lbRight, right);
	}
	/**
	 * @param lbLeft2
	 * @param leftObject2
	 */
	private void setupGroupColumns(ListBoxControl lb, TableColumn[] cols) {
		
		for (int i = 0; i < cols.length; i++) {
			TableColumn col = cols[i];
			lb.addElement(col.getTitle(), Integer.toString(i));
		}
		lb.setSelectedKey("0");
		
	}


}
