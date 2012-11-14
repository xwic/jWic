/*
 * Copyright 2006 jWic group (http://www.jwic.de)
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
 * de.jwic.ecolib.samples.controls.MenuDemo.java
 * Created on Feb 9, 2006
 * $Id: DialogDemo.java,v 1.4 2010/02/07 14:26:33 lordsam Exp $
 * @author jbornema
 */
package de.jwic.ecolib.samples.controls;

import java.util.Date;


import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.FileUploadControl;
import de.jwic.controls.InputBoxControl;
import de.jwic.controls.LabelControl;
import de.jwic.controls.ListBoxControl;
import de.jwic.controls.layout.TableLayoutContainer;
import de.jwic.ecolib.controls.divwin.DivPopupWindow;
import de.jwic.ecolib.dialogs.DialogEvent;
import de.jwic.ecolib.dialogs.DialogListener;
import de.jwic.ecolib.dialogs.InputDialog;
import de.jwic.ecolib.dialogs.MessageDialog;
import de.jwic.events.FileReceivedEvent;
import de.jwic.events.FileReceivedListener;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/* Created on Feb 9, 2006
 * @author jbornema
 */
public class DialogDemo extends ControlContainer {

	private static final long serialVersionUID = 3782042858778812921L;

	private ListBoxControl lbcEvents = null;
	private DivPopupWindow divPopup = null;
	
	public DialogDemo(IControlContainer container) {
		super(container);
		
		
		Button btMessageDialog = new Button(this, "btMsg");
		btMessageDialog.setTitle("Message Dialog");
		btMessageDialog.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				showMessageDialog();
			};
		});

		Button btInputDialog = new Button(this, "btInp");
		btInputDialog.setTitle("Input Dialog");
		btInputDialog.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				showInputDialog();
			};
		});

		Button btDivDlg = new Button(this, "btDlg");
		btDivDlg.setTitle("Div Popup");
		btDivDlg.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				showDivPopup(false);
			};
		});

		Button btDivDlgModal = new Button(this, "btDlgModal");
		btDivDlgModal.setTitle("Div Popup (Modal)");
		btDivDlgModal.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				showDivPopup(true);
			};
		});

		divPopup = new DivPopupWindow(this, "divPopup");
		divPopup.setTitle("Dialog Title");
		divPopup.setModal(false);
		
		TableLayoutContainer tlc = new TableLayoutContainer(divPopup, "table", 2);
		
		LabelControl lbl = new LabelControl(tlc, "label");
		lbl.setText("This is the text inside of the popup dialog.");
		
		Button btClosePopup = new Button(tlc, "close");
		btClosePopup.setTitle("Close");
		btClosePopup.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				closeDivPopup();
			}
		});

		
		final InputBoxControl inpTest = new InputBoxControl(tlc, "inp");
		inpTest.setText("Press update");
		inpTest.setWidth(300);
		
		Button btUpdate = new Button(tlc);
		btUpdate.setTitle("Update");
		btUpdate.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				inpTest.setText(new Date().toString());	
				lbcEvents.addElement("Test Label Updated with " + inpTest.getText());
			}
		});
		
		ListBoxControl lbcTest = new ListBoxControl(tlc);
		lbcTest.addElement("Element 1");
		lbcTest.addElement("Element 2");
		lbcTest.addElement("Element 3");
		lbcTest.setChangeNotification(true);
		lbcTest.setConfirmMsg("Are you sure?");
		
		lbcEvents = new ListBoxControl(this, "events");
		lbcEvents.setSize(10);
		lbcEvents.setWidth(500);
		
		// also add a FileUploadControl
		FileUploadControl fileUpload = new FileUploadControl(tlc, "file");
		fileUpload.addFileReceivedListener(new FileReceivedListener() {
			/* (non-Javadoc)
			 * @see de.jwic.events.FileReceivedListener#fileReceived(de.jwic.events.FileReceivedEvent)
			 */
			public void fileReceived(FileReceivedEvent event) {
				lbcEvents.addElement("File uploaded: " + event.getFileName());
			}
		});
		
	}

	/**
	 * 
	 */
	protected void closeDivPopup() {

		divPopup.hide();
		
	}

	/**
	 * 
	 */
	protected void showDivPopup(boolean modal) {
		
		divPopup.setModal(modal);
		divPopup.show();
		
	}

	/**
	 * 
	 */
	protected void showMessageDialog() {
		
		final MessageDialog dialog = new MessageDialog(this);
		dialog.setTitle("This is the dialogs title");
		dialog.setMessage("This is the message of the dialog. The message may contain <b>HTML</b> tags.");
		
		// add an event listener to get notified when the dialog is done
		dialog.addDialogListener(new DialogListener() {
			public void dialogAborted(DialogEvent event) {
				lbcEvents.addElement("event: dialogAborted");
			};
			public void dialogFinished(DialogEvent event) {
				lbcEvents.addElement("event: dialogFinished. Selected Button: " + dialog.getSelectedButton());
			};
		});
		
		// add custom buttons
		dialog.getButtons().add("Ok");
		dialog.getButtons().add("No, that is not ok!");
		
		dialog.openAsPage();
		
	}
	
	/**
	 * 
	 */
	protected void showInputDialog() {
		
		final InputDialog dialog = new InputDialog(this);
		dialog.setTitle("This is the dialogs title");
		dialog.setMessage("This is the message of the dialog. The message may contain <b>HTML</b> tags.");
		dialog.setInputValue("Default Input Value");
		
		// add an event listener to get notified when the dialog is done
		dialog.addDialogListener(new DialogListener() {
			public void dialogAborted(DialogEvent event) {
				lbcEvents.addElement("event: dialogAborted");
			};
			public void dialogFinished(DialogEvent event) {
				lbcEvents.addElement("event: dialogFinished. Text: " + dialog.getInputValue());
			};
		});
		
		dialog.openAsPage();
		
	}
	

}
