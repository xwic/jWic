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
package de.jwic.controls.dialogs;

import de.jwic.base.IControlContainer;
import de.jwic.base.Page;
import de.jwic.controls.Button;
import de.jwic.controls.InputBox;
import de.jwic.controls.Label;
import de.jwic.controls.Window;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.util.Messages;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.4 $
 */
public class InputDialog extends BasicDialog {
	private static final long serialVersionUID = 1L;
	private String title = "";
	private String message = "";
	private String inputValue = "";
	private InputBox inpBox = null;
	
	/**
	 * @param parent
	 */
	public InputDialog(IControlContainer parent) {
		super(parent);
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.dialogs.BasicDialog#createControls(de.jwic.base.IControlContainer)
	 */
	protected void createControls(IControlContainer container) {
		
		Messages messages = new Messages(container.getSessionContext().getLocale(), "de.jwic.controls.dialogs.messages");

		Window win = new Window(container);
		win.setTitle(title);
		win.setWidth(400);
		win.setTemplateName(getClass().getName());
		//win.setPosition(InlineWindow.Position.CENTER_SCREEN);
		
		new Label(win, "lblMessage").setText(message);
		
		Button btOk = new Button(win, "btOk");
		btOk.setTitle(messages.getString("dialog.ok"));
		btOk.addSelectionListener(new SelectionListener() {
			private static final long serialVersionUID = 1L;
			public void objectSelected(SelectionEvent event) {
				finish();
			};
		});
		Button btAbort = new Button(win, "btAbort");
		btAbort.setTitle(messages.getString("dialog.abort"));
		btAbort.addSelectionListener(new SelectionListener() {
			private static final long serialVersionUID = 1L;
			public void objectSelected(SelectionEvent event) {
				abort();
			};
		});
		
		inpBox = new InputBox(win, "input");
		inpBox.setWidth(375);
		inpBox.setText(getInputValue());
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.dialogs.BasicDialog#openAsPage()
	 */
	public Page openAsPage() {
		Page page = super.openAsPage();
		page.setTitle(title);
		return page;
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.dialogs.BasicDialog#finish()
	 */
	public void finish() {
		inputValue = inpBox.getText();
		super.finish();
	}
	
	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message The message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return Returns the inputValue.
	 */
	public String getInputValue() {
		return inputValue;
	}

	/**
	 * @param inputValue The inputValue to set.
	 */
	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
		if (inpBox != null) {
			inpBox.setText(inputValue);
		}
	}


}
