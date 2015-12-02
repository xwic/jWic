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
package de.jwic.demo.tbv;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.ImageRef;
import de.jwic.controls.Button;
import de.jwic.controls.ToolBar;
import de.jwic.controls.ToolBarGroup;
import de.jwic.controls.actions.Action;
import de.jwic.controls.actions.IAction;
import de.jwic.controls.dialogs.DialogAdapter;
import de.jwic.controls.dialogs.DialogEvent;
import de.jwic.controls.menu.Menu;
import de.jwic.controls.tableviewer.TableColumn;
import de.jwic.controls.tableviewer.TableModel;
import de.jwic.controls.tableviewer.TableModelAdapter;
import de.jwic.controls.tableviewer.TableModelEvent;
import de.jwic.controls.tableviewer.TableViewer;
import de.jwic.demo.ImageLibrary;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * TableViewer Demo. This class contains a few inner classes that would usualy
 * be implemented as "normal" classes. This is done to enhance reading the demo
 * code on the website.
 *
 * @author Florian Lippisch
 */
public class BasicTBVDemo extends ControlContainer {

	private static final long serialVersionUID = 2L;

	private TableViewer viewer;
	private DemoTaskContentProvider contentProvider;

	private IAction flagRed;
	private IAction flagBlue;
	private IAction deleteTask;
	
	private class DemoTableViewerListener implements ElementSelectedListener {
		public void elementSelected(ElementSelectedEvent event) {
			
			if (event.getElement() == null) {
				refreshActions(null);
			} else {
				DemoTask task = contentProvider.getObjectFromKey((String)event.getElement());
				if (task != null) {
					refreshActions(task);
					if (event.isDblClick()) {
						getSessionContext().notifyMessage("Element Selected: " + task.title);
					}
				}
			}
		}
	}
	
	/**
	 * Constructor.
	 * @param container
	 */
	public BasicTBVDemo(IControlContainer container) {
		super(container);
		
		// create the viewer
		viewer = new TableViewer(this, "table");
		
		contentProvider = new DemoTaskContentProvider(createDemoData());
		viewer.setContentProvider(contentProvider);
		viewer.setTableLabelProvider(new LabelProvider());
		viewer.setScrollable(true);
		viewer.setShowStatusBar(true);
		viewer.setResizeableColumns(true);
		viewer.setSelectableColumns(true);
		viewer.setWidth(700);
		viewer.setHeight(250);
		
		TableModel model = viewer.getModel();
		model.setMaxLines(50); // all
		
		DemoTableViewerListener listener = new DemoTableViewerListener();
		
		// add listener to demonstrate sorting/images
		model.addTableModelListener(new TableModelAdapter() {
			public void columnSelected(TableModelEvent event) {
				handleSorting(event.getTableColumn());
			}
		});
		model.setSelectionMode(TableModel.SELECTION_SINGLE);
		createColumns();
		
		// create Actions
		createActions();
		
		// create the toolbar
		ToolBar tb = new ToolBar(this, "toolbar");
		tb.setCssClass("j-toolbar ui-corner-top");
		ToolBarGroup group = tb.addGroup();
		Button btNew = group.addButton();
		btNew.setTitle("Add Task");
		btNew.setIconEnabled(ImageLibrary.IMG_ADD);
		btNew.addSelectionListener(new SelectionListener() {
			@Override
			public void objectSelected(SelectionEvent event) {
				addDemoTask();
			}
		});
		
		group.addAction(deleteTask);
		group.addAction(flagBlue);
		group.addAction(flagRed);
		
		// Add a context menu
		Menu menu = new Menu(this, "contextMenu");
		menu.addMenuItem(flagRed);
		menu.addMenuItem(flagBlue);
		menu.addMenuItem(deleteTask);
		viewer.setMenu(menu);
		
		// Add the listener after all other controls have been created
		model.addElementSelectedListener(listener);

	}

	/**
	 * 
	 */
	protected void addDemoTask() {
		
		AddDemoTaskDialog dialog = new AddDemoTaskDialog(viewer.getContainer());
		dialog.addDialogListener(new DialogAdapter() {
			public void dialogFinished(DialogEvent event) {
				AddDemoTaskDialog dialog = ((AddDemoTaskDialog) event.getEventSource());
				DemoTask task = dialog.getDemoTask();
				contentProvider.addElement(task);
				
				viewer.setRequireRedraw(true);
			}
		});
		dialog.openAsPage();
		
	}

	/**
	 * @param task
	 */
	public void refreshActions(DemoTask task) {
		
		deleteTask.setEnabled(task != null);
		flagBlue.setEnabled(task != null);
		flagRed.setEnabled(task != null && !task.done);
		
	}

	/**
	 * 
	 */
	private void createActions() {
		
		flagRed = new Action() {
			@Override
			public void run() {
				handleFlagRed();
			}
		};
		flagRed.setTitle("Mark Completed");
		flagRed.setIconEnabled(new ImageRef("icons/flag_red.png"));

		flagBlue = new Action() {
			@Override
			public void run() {
				handleFlagBlue();
			}
		};
		flagBlue.setTitle("Mark for Review");
		flagBlue.setIconEnabled(new ImageRef("icons/flag_blue.png"));

		deleteTask = new Action() {
			public void run() {
				getSessionContext().notifyMessage("Sorry, not implemented", "error");
			}
		};
		deleteTask.setTitle("Delete");
		deleteTask.setIconEnabled(ImageLibrary.IMG_CROSS);

		refreshActions(null);
	}

	/**
	 * 
	 */
	protected void handleFlagBlue() {
		String key = viewer.getModel().getFirstSelectedKey();
		if (key != null) {
			DemoTask task = contentProvider.getObjectFromKey(key);
			getSessionContext().notifyMessage("Task '" + task.title + "' marked for review.", "info");
		}
	}

	/**
	 * 
	 */
	protected void handleFlagRed() {
		String key = viewer.getModel().getFirstSelectedKey();
		if (key != null) {
			DemoTask task = contentProvider.getObjectFromKey(key);
			if (task != null) {
				task.completed = 100;
				task.done = true;
				viewer.requireRedraw();
			}
		}
	}

	/**
	 * Change the sort icon.
	 * @param tableColumn
	 */
	protected void handleSorting(TableColumn tableColumn) {
		
		if (tableColumn.getSortIcon() == TableColumn.SORT_ICON_NONE) {
			// clear all columns
			for (Iterator<TableColumn> it = viewer.getModel().getColumnIterator(); it.hasNext(); ) {
				TableColumn col = it.next();
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
		
		viewer.setRequireRedraw(true);
		
	}

	/**
	 * 
	 */
	private void createColumns() {
		
		TableModel model = viewer.getModel();
		// add Columns
		TableColumn col = new TableColumn("");
		col.setImage(new ImageRef(getClass().getPackage(), "checked.gif"));
		col.setWidth(20);
		col.setUserObject("done");
		model.addColumn(col);
		
		col = new TableColumn("Task");
		col.setUserObject("title");
		col.setWidth(250);
		model.addColumn(col);
		
		col = new TableColumn("Owner");
		col.setUserObject("owner");
		col.setWidth(120);
		model.addColumn(col);
		
		col = new TableColumn("Complete");
		col.setUserObject("completed");
		col.setWidth(80);
		model.addColumn(col);
		
		
	}

	/**
	 * @return
	 */
	private List<DemoTask> createDemoData() {
		List<DemoTask> data = new ArrayList<DemoTask>();
		
		data.add(new DemoTask("Implement Demo", "Sam", 0));
		DemoTask task = new DemoTask("Write Docu", "Mark", 0);
		task.done = true;
		data.add(task);
		data.add(new DemoTask("Setup buildserver", "Ronny", 20));
		data.add(new DemoTask("Update jwic homepage", "?", 0));
		data.add(new DemoTask("Unknown", "", 0));
		data.add(new DemoTask("Change default implementation", "Sam", 10));
		data.add(new DemoTask("Evaluate library XYZ for relevance", "Mark", 50));
		
		
		for (int i = 1; i < 105; i++) {
			DemoTask demoTask = new DemoTask();
			demoTask.done = i % 5 == 0;
			demoTask.completed = i;
			demoTask.title = "Task " + i;
			demoTask.owner = "?";
			data.add(demoTask);
		}
		
		return data;
	}

}
