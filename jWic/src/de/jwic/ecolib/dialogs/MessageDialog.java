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
 * de.jwic.ecolib.dialogs.MessageDialog
 * Created on 27.04.2006
 * $Id: MessageDialog.java,v 1.4 2010/02/07 14:26:33 lordsam Exp $
 */
package de.jwic.ecolib.dialogs;

import java.util.ArrayList;
import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.base.Page;
import de.jwic.controls.Button;
import de.jwic.controls.LabelControl;
import de.jwic.controls.InlineWindow;
import de.jwic.controls.layout.TableLayoutContainer;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.4 $
 */
public class MessageDialog extends BasicDialog {
	private static final long serialVersionUID = 1L;
	private String title = "";
	private String message = "";
	
	private List<String> buttons = new ArrayList<String>();
	private int selectedButton = -1;
	
	private class ButtonSelectionHandler implements SelectionListener {
		private static final long serialVersionUID = 1L;
		public void objectSelected(SelectionEvent event) {
			Button button = (Button)event.getEventSource();
			selectedButton = Integer.parseInt(button.getName());
			finish();
		}
	}
	
	/**
	 * @param parent
	 */
	public MessageDialog(IControlContainer parent) {
		super(parent);
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.dialogs.BasicDialog#createControls(de.jwic.base.IControlContainer)
	 */
	protected void createControls(IControlContainer container) {
		
		InlineWindow win = new InlineWindow(container);
		win.setText(title);
		//win.setAlign("center");
		win.setWidth(400);
		win.setTemplateName(getClass().getName());
		win.setPosition(InlineWindow.Position.CENTER_SCREEN);
		
		new LabelControl(win, "lblMessage").setText(message);
		
		if (buttons.size() == 0) {
			buttons.add("Ok");
		}
		
		ButtonSelectionHandler handler = new ButtonSelectionHandler();
		
		TableLayoutContainer btBox = new TableLayoutContainer(win, "buttons");
		btBox.setColumnCount(buttons.size());
		for (int i = 0; i < buttons.size(); i++) {
			Button button = new Button(btBox, Integer.toString(i));
			button.setTitle(buttons.get(i));
			//button.setWidth("40");
			button.addSelectionListener(handler);
		}

	}
	
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.dialogs.BasicDialog#openAsPage()
	 */
	public Page openAsPage() {
		Page page = super.openAsPage();
		page.setTitle(title);
		return page;
	}

	/**
	 * @return Returns the buttons.
	 */
	public List<String> getButtons() {
		return buttons;
	}

	/**
	 * @param buttons The buttons to set.
	 */
	public void setButtons(List<String> buttons) {
		this.buttons = buttons;
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
	 * @return Returns the selectedButton.
	 */
	public int getSelectedButton() {
		return selectedButton;
	}

}
