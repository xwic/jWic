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
 * de.jwic.ecolib.samples.htmleditor.HtmlEditorDemo
 * Created on 27.04.2006
 * $Id: CKEditorDemo.java,v 1.3 2011/06/02 14:05:35 lordsam Exp $
 */
package de.jwic.ecolib.samples.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.InputBoxControl;
import de.jwic.controls.LabelControl;
import de.jwic.controls.ListBoxControl;
import de.jwic.ecolib.ckeditor.CKEditor;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * This demo illustrates the usage of the HtmlEditorControl.
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class CKEditorDemo extends ControlContainer {

	private CKEditor editor;
	private LabelControl lblPreview;
	private InputBoxControl code;
	private Button btEnabled;
	
	/**
	 * @param container
	 */
	public CKEditorDemo(IControlContainer container) {
		super(container);
		
		
		editor = new CKEditor(this, "editor");
		
		/*
		editor.setCustomToolBar(
			new ToolBarBand(ToolBarBand.Default_Clipboard),
			new ToolBarBand(ToolBarBand.Default_Paragraph),
			new ToolBarBand(ToolBarBand.Default_Links),
			new ToolBarBand(true, ToolBarBand.Default_Styles),
			new ToolBarBand(ToolBarBand.Default_Colors),
			new ToolBarBand(ToolBarBand.Default_BasicStyles)
		);*/
		editor.setToolBarName("Full");
		
		
		lblPreview = new LabelControl(this, "preview");
		code = new InputBoxControl(this, "code");
		code.setMultiLine(true);
		code.setWidth(400);
		code.setRows(10);
		

		Button btPreview = new Button(this, "btPreview");
		btPreview.setTitle("Show Preview");
		btPreview.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				lblPreview.setText(editor.getText());
			};

		});
		Button btVisibility = new Button(this, "btVisibility");
		btVisibility.setTitle("Visibility");
		btVisibility.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				editor.setVisible(!editor.isVisible());
			};
		});

		Button btGet = new Button(this, "btGet");
		btGet.setTitle("Get");
		btGet.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				code.setText(editor.getText());
			};
		});

		Button btSet = new Button(this, "btSet");
		btSet.setTitle("Set");
		btSet.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				editor.setText(code.getText());
			};
		});
		
		btEnabled = new Button(this, "btEnabled");
		btEnabled.setTitle(editor.isEnabled() ? "Disable" : "Enable");
		btEnabled.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				changeEnabled();
			};
		});
		
		// Change Listbox Width
		ListBoxControl lbWidth = new ListBoxControl(this, "lbWidth");
		lbWidth.addElement("0 - Unspecified", "0");
		for (int i = 50; i < 701; i += 50) {
			lbWidth.addElement(Integer.toString(i) + "px", Integer.toString(i));
		}
		lbWidth.setSelectedKey(Integer.toString(editor.getWidth()));
		lbWidth.setChangeNotification(true);
		lbWidth.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				editor.setWidth(Integer.parseInt((String)event.getElement()));
			};
		});
		
		// Change Listbox Height
		ListBoxControl lbHeight = new ListBoxControl(this, "lbHeight");
		lbHeight.addElement("0 - Unspecified", "0");
		for (int i = 50; i < 401; i += 50) {
			lbHeight.addElement(Integer.toString(i) + "px", Integer.toString(i));
		}
		lbHeight.setSelectedKey(Integer.toString(editor.getHeight()));
		lbHeight.setChangeNotification(true);
		lbHeight.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				editor.setHeight(Integer.parseInt((String)event.getElement()));
			};
		});

	}

	/**
	 * Change the Enabled property of the button.
	 */
	protected void changeEnabled() {
		
		editor.setEnabled(!editor.isEnabled());
		btEnabled.setTitle(editor.isEnabled() ? "Disable" : "Enable");
		
	}
}
