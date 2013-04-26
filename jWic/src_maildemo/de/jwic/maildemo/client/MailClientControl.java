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
 * de.jwic.maildemo.client.MailClientControl
 * Created on 23.04.2007
 * $Id: MailClientControl.java,v 1.7 2012/08/29 07:46:51 lordsam Exp $
 */
package de.jwic.maildemo.client;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.ActionBarControl;
import de.jwic.controls.Button;
import de.jwic.controls.LabelControl;
import de.jwic.controls.TreeControl;
import de.jwic.controls.accordion.Accordion;
import de.jwic.controls.accordion.Panel;
import de.jwic.ecolib.controls.StackedContainer;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.maildemo.api.IFolder;
import de.jwic.maildemo.main.MailModel;
import de.jwic.maildemo.main.MailModelAdapter;
import de.jwic.maildemo.main.MailModelEvent;
import de.jwic.maildemo.resources.SharedImages;
import de.jwic.maildemo.viewer.MailViewer;

/**
 *
 * @author Florian Lippisch
 */
public class MailClientControl extends ControlContainer {

	private MailModel model = null;
	private int height = 400;
	private int width = 700;
	
	private Accordion stackPanel = null;
	private StackedContainer mainView = null;
	
	private MailViewer mailViewer = null;
	
	private Button btReply;
	private Button btForward;
	private Button btDelete;
	
	
	private class ModelHandler extends MailModelAdapter {

		public void mailSelected(MailModelEvent event) {
			
			boolean selected = event.getSelectedMail() != null;
			btReply.setEnabled(selected);
			btForward.setEnabled(selected);
			btDelete.setEnabled(selected);
			
		}
	}
	
	/**
	 * @param container
	 * @param name
	 */
	public MailClientControl(IControlContainer container, String name, MailModel model) {
		super(container, name);
		this.model = model;
		
		init();
		
		model.addMailModelListener(new ModelHandler());
	}

	/**
	 * 
	 */
	private void init() {
		
		ActionBarControl abar = new ActionBarControl(this, "abar");
		
		Button btNewMail = new Button(abar);
		btNewMail.setTitle("New Mail");
		btNewMail.setIconEnabled(SharedImages.ICON_NEW);
		
		btReply = new Button(abar);
		btReply.setTitle("Reply");
		btReply.setIconEnabled(SharedImages.ICON_REPLY);
		btReply.setIconDisabled(SharedImages.ICON_REPLY_DISABLED);
		btReply.setEnabled(false);

		btDelete = new Button(abar);
		btDelete.setTitle("Delete");
		btDelete.setIconEnabled(SharedImages.ICON_DELETE);
		btDelete.setIconDisabled(SharedImages.ICON_DELETE_DISABLED);
		btDelete.setEnabled(false);
		btDelete.setConfirmMsg("Do you realy want to delete the selected mail?");
		
		
		btForward = new Button(abar);
		btForward.setTitle("Forward");
		btForward.setIconEnabled(SharedImages.ICON_FORWARD);
		btForward.setIconDisabled(SharedImages.ICON_FORWARD_DISABLED);
		btForward.setEnabled(false);
		
		
		stackPanel = new Accordion(this, "leftStack");
		Panel mailStack = stackPanel.createPanel("Mail");
		InboxTreeControl inboxTreeControl = new InboxTreeControl(mailStack, "ibt", model);
		
		Panel tasks = stackPanel.createPanel("Tasks");
		LabelControl label = new LabelControl(tasks);
		label.setText("Not Implemented");
		
		
		Panel contacts = stackPanel.createPanel("Contacts");
		label = new LabelControl(contacts);
		label.setText("Not Implemented");

		mainView = new StackedContainer(this, "mainView");
	
		mailViewer = new MailViewer(mainView, model);
		mailViewer.setFolder(model.getSession().getFolder("Inbox"));
		
		mainView.setCurrentControlName(mailViewer.getName());
		
		inboxTreeControl.getTree().addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				if (event.getElement() instanceof String) {
					String nodeid = (String)event.getElement();
					FolderTreeNode node = (FolderTreeNode) ((TreeControl) event.getEventSource()).getNode(nodeid);
					IFolder folder = node.getFolder();
					mailViewer.setFolder(folder);
				}
			}
		});		
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
		mailViewer.setHeight(height - 27);
		requireRedraw();
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
		mailViewer.setWidth(width - 250);
		requireRedraw();
	}

}
