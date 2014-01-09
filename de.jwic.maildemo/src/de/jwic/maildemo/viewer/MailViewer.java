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
 * de.jwic.maildemo.client.MailViewer
 * Created on 23.04.2007
 * $Id: MailViewer.java,v 1.5 2007/04/27 09:29:52 aroncotrau Exp $
 */
package de.jwic.maildemo.viewer;

import java.util.Iterator;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.tableviewer.TableColumn;
import de.jwic.controls.tableviewer.TableModel;
import de.jwic.controls.tableviewer.TableModelAdapter;
import de.jwic.controls.tableviewer.TableModelEvent;
import de.jwic.controls.tableviewer.TableViewer;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.maildemo.api.IFolder;
import de.jwic.maildemo.api.IMail;
import de.jwic.maildemo.main.MailModel;
import de.jwic.maildemo.resources.SharedImages;

/**
 * Displays a list of mails and a preview panel.
 * @author Florian Lippisch
 */
public class MailViewer extends ControlContainer {

	private int width = 500;
	private int height = 400;
	
	private IFolder folder = null;
	
	private TableViewer tableViewer = null;
	private MailContentProvider contentProvider = null;
	private MailInfoControl mailInfo;
	private MailPreviewControl mailPreview;
	private MailModel model;
	
	/**
	 * Handles mail selection.
	 *
	 * @author Florian Lippisch
	 */
	private class MailSelectionHandler implements ElementSelectedListener {
		public void elementSelected(ElementSelectedEvent event) {
			String key = (String)event.getElement();
			IMail mail = model.getSession().getMailByID(key);
			model.setSelectedMail(mail);
			
			// update the info and preview control by assigning the mail directly.
			// another (cleaner) approach would be if the controls would listen
			// to the model on their own...
			mailInfo.setMail(mail);
			mailPreview.setMessage(mail != null ? mail.getMessage() : null);
		}
	}
	
	/**
	 * @param container
	 */
	public MailViewer(IControlContainer container, MailModel model) {
		super(container);
		
		this.model = model;
		mailInfo = new MailInfoControl(this, "mailInfo");
		mailPreview = new MailPreviewControl(this, "preview");
		
		setupTable();
		
	}

	/**
	 * Setup the table.
	 */
	private void setupTable() {
		
		tableViewer = new TableViewer(this, "mailList");
		tableViewer.setScrollable(true);
		tableViewer.setSelectableColumns(true);
		tableViewer.setResizeableColumns(true);
		tableViewer.setShowStatusBar(false);
		tableViewer.setCssClass("tblViewerNB"); // use custom style
		
		// set the label provider and the content provider
		tableViewer.setTableLabelProvider(new MailLabelProvider(getSessionContext().getLocale()));
		
		contentProvider = new MailContentProvider();
		tableViewer.setContentProvider(contentProvider);
		
		TableModel tm = tableViewer.getModel();
		TableColumn column = new TableColumn("", 20, "attachment");
		column.setImage(SharedImages.ICON_ATTACHMENT);
		tm.addColumn(column);
		tm.addColumn(new TableColumn("From", 180, "from"));
		tm.addColumn(new TableColumn("Subject", 250, "subject"));
		tm.addColumn(new TableColumn("Recieved", 130, "recieved"));
		tm.addColumn(new TableColumn("Size", 100, "size"));
		
		tm.setMaxLines(-1); // Show All
		tm.setSelectionMode(TableModel.SELECTION_SINGLE);
		tm.addElementSelectedListener(new MailSelectionHandler());
		
		// Add a listener to handle column selection/sorting.
		tm.addTableModelListener(new TableModelAdapter() {
			public void columnSelected(TableModelEvent event) {
				handleSorting(event.getTableColumn());
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
		tableViewer.setHeight(getListHeight());
		mailPreview.setHeight(Integer.toString(getPreviewHeight()) + "px");
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
		tableViewer.setWidth(width);
		mailPreview.setWidth(Integer.toString(width) + "px");
	}

	/**
	 * Calculate the height of the list ( 3/5 of the total height).
	 * @return
	 */
	public int getListHeight() {
		return ((height - 45) / 5) * 3;
	}
	
	/**
	 * Calculate the height of the preview panel (2/5 of the total height).
	 * @return
	 */
	public int getPreviewHeight() {
		return ((height - 45) / 5) * 2;
	}

	/**
	 * @return the folder
	 */
	public IFolder getFolder() {
		return folder;
	}

	/**
	 * @param folder the folder to set
	 */
	public void setFolder(IFolder folder) {
		this.folder = folder;
		contentProvider.setFolder(folder);
		tableViewer.setRequireRedraw(true);
	}
	
	/**
	 * Change the sort icon.
	 * @param tableColumn
	 */
	protected void handleSorting(TableColumn tableColumn) {
		
		if (tableColumn.getSortIcon() == TableColumn.SORT_ICON_NONE) {
			// clear all columns
			for (Iterator it = tableViewer.getModel().getColumnIterator(); it.hasNext(); ) {
				TableColumn col = (TableColumn)it.next();
				col.setSortIcon(TableColumn.SORT_ICON_NONE);
			}
		}
		boolean up = true;
		switch (tableColumn.getSortIcon()) {
		case TableColumn.SORT_ICON_NONE: 
			tableColumn.setSortIcon(TableColumn.SORT_ICON_UP);
			break;
		case TableColumn.SORT_ICON_UP:
			tableColumn.setSortIcon(TableColumn.SORT_ICON_DOWN);
			up = false;
			break;
		case TableColumn.SORT_ICON_DOWN:
			// once sorted, the list can not be displayed in the
			// original order as we sort the original table,
			// therefor loosing the original order.
			tableColumn.setSortIcon(TableColumn.SORT_ICON_UP);
			//tableColumn.setSortIcon(TableColumn.SORT_ICON_NONE);
			break;
		}
		
		// do the sort
		contentProvider.sortData((String)tableColumn.getUserObject(), up);
		
		tableViewer.setRequireRedraw(true);
		
	}
	
}
