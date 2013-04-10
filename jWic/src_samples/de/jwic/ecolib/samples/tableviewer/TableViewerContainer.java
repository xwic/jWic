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
 * de.jwic.ecolib.samples.controls.tbv.TableViewerContainer
 * Created on Apr 4, 2007
 * $Id: TableViewerContainer.java,v 1.5 2010/02/07 14:26:33 lordsam Exp $
 */
package de.jwic.ecolib.samples.tableviewer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.ImageRef;
import de.jwic.base.Page;
import de.jwic.controls.ActionBarControl;
import de.jwic.controls.Button;
import de.jwic.ecolib.dialogs.DialogAdapter;
import de.jwic.ecolib.dialogs.DialogEvent;
import de.jwic.ecolib.dialogs.DialogListener;
import de.jwic.ecolib.samples.tableviewer.dialog.AddDemoTaskDialog;
import de.jwic.ecolib.tableviewer.TableColumn;
import de.jwic.ecolib.tableviewer.TableModel;
import de.jwic.ecolib.tableviewer.TableModelAdapter;
import de.jwic.ecolib.tableviewer.TableModelEvent;
import de.jwic.ecolib.tableviewer.TableViewer;
import de.jwic.ecolib.tableviewer.export.ExcelExportControl;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * 
 *
 * @author Aron Cotrau
 */
public class TableViewerContainer extends ControlContainer {

	private TableViewer viewer = null;
	private ExcelExportControl excelExport = null;
	private Object selectedElement = null;
	private DemoTaskContentProvider contentProvider = null;
	
	private class DemoTableViewerListener implements ElementSelectedListener {
		public void elementSelected(ElementSelectedEvent event) {
			selectedElement = event.getElement();
		}
	}
	
	/**
	 * @param container
	 */
	public TableViewerContainer(IControlContainer container) {
		super(container);
		init();
	}

	/**
	 * @param container
	 * @param name
	 */
	public TableViewerContainer(IControlContainer container, String name) {
		super(container, name);
		init();
	}

	public void setWidthHeight() {
		Page page = (Page) getSessionContext().getTopControl();
		int width = page.getClientWidth();
		int height = page.getClientHeight();
		
		viewer.setWidth(width - 100);
		viewer.setHeight(height - 200);
	}
	
	private void init() {
		viewer = new TableViewer(this, "table");
		
		contentProvider = new DemoTaskContentProvider(createDemoData());
		viewer.setContentProvider(contentProvider);
		viewer.setTableLabelProvider(new LabelProvider());
		viewer.setScrollable(true);
		viewer.setShowStatusBar(true);
		viewer.setResizeableColumns(true);
		viewer.setSelectableColumns(true);
		
		TableModel model = viewer.getModel();
		model.setMaxLines(-1); // all
		DemoTableViewerListener listener = new DemoTableViewerListener();
		model.addElementSelectedListener(listener);
		// add listener to demonstrate sorting/images
		model.addTableModelListener(new TableModelAdapter() {
			public void columnSelected(TableModelEvent event) {
				handleSorting(event.getTableColumn());
			}
		});
		model.setSelectionMode(TableModel.SELECTION_SINGLE);
		createColumns();
		
		// add excel export button
		ActionBarControl abar = new ActionBarControl(this, "abar");
		abar.setTemplateName("de.jwic.ecolib.samples.tableviewer.ActionBar");
		
		excelExport = new ExcelExportControl(abar, "excel", viewer);
		excelExport.setTitle("Excel Export");
		
		Button addTask = new Button(abar, "addTask");
		addTask.setTitle("Add Task");
		addTask.addSelectionListener(new SelectionListener() {
			private DialogListener dl = new DialogAdapter() {
				public void dialogFinished(DialogEvent event) {
					AddDemoTaskDialog dialog = ((AddDemoTaskDialog) event.getEventSource());
					DemoTask task = dialog.getDemoTask();
					DemoTaskContentProvider contentProvider = (DemoTaskContentProvider) viewer.getModel().getContentProvider();
					contentProvider.addElement(task);
					
					viewer.setRequireRedraw(true);
				}
			};
			
			public void objectSelected(SelectionEvent event) {
				AddDemoTaskDialog dialog = new AddDemoTaskDialog(viewer.getContainer());
				dialog.addDialogListener(dl);
				dialog.openAsPage();
			}
		});
		
		Button removeTask = new Button(abar, "removeTask");
		removeTask.setTitle("Remove Task");
		removeTask.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				DemoTaskContentProvider contentProvider = (DemoTaskContentProvider) viewer.getModel().getContentProvider();
				DemoTask task = contentProvider.getElement(selectedElement.toString());
				contentProvider.removeElement(task);
				
				viewer.setRequireRedraw(true);
			}
		});
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
	 * @param tableColumn
	 */
	protected void handleSorting(TableColumn tableColumn) {
		
		if (tableColumn.getSortIcon() == TableColumn.SORT_ICON_NONE) {
			// clear all columns
			for (Iterator it = viewer.getModel().getColumnIterator(); it.hasNext(); ) {
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
		
		viewer.setRequireRedraw(true);
		
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
