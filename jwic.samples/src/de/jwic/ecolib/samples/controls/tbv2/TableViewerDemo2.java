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
 * de.jwic.ecolib.samples.controls.TableViewerDemo
 * Created on 12.03.2007
 * $Id: TableViewerDemo2.java,v 1.5 2010/02/07 14:26:34 lordsam Exp $
 * @author flippisch
 */
package de.jwic.ecolib.samples.controls.tbv2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.ImageRef;
import de.jwic.controls.Button;
import de.jwic.controls.ListBoxControl;
import de.jwic.ecolib.tableviewer.TableColumn;
import de.jwic.ecolib.tableviewer.TableModel;
import de.jwic.ecolib.tableviewer.TableModelAdapter;
import de.jwic.ecolib.tableviewer.TableModelEvent;
import de.jwic.ecolib.tableviewer.TableViewer;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SelectionListener;

/**
 * TableViewer Demo. This class contains a few inner classes that would usualy
 * be implemented as "normal" classes. This is done to enhance reading the demo
 * code on the website.
 *
 * @author Florian Lippisch
 */
public class TableViewerDemo2 extends ControlContainer {

	private static final long serialVersionUID = 2L;

	private ListBoxControl lbcEvents = null;
	private Button btFillWidth;
	private Button btEnabled;
	
	private TableViewer viewer;
	private DemoTaskContentProvider contentProvider;
	
	private class DemoTableViewerListener extends TableModelAdapter implements ElementSelectedListener {
		public void rangeUpdated(TableModelEvent event) {
			lbcEvents.addElement("rangeUpdated()");
		}
		public void elementSelected(ElementSelectedEvent event) {
			lbcEvents.addElement("elementSelected(" + event.getElement() + ") " + (event.isDblClick() ? "DBLCLICK" : ""));
			
		}
		public void columnSelected(TableModelEvent event) {
			lbcEvents.addElement("columnSelected(" + event.getTableColumn().getTitle() + ")");
		}
		/* (non-Javadoc)
		 * @see de.jwic.ecolib.treeviewer.ITreeModelListener#nodeCollapsed(de.jwic.ecolib.treeviewer.TreeModelEvent)
		 */
		public void nodeCollapsed(TableModelEvent event) {
			lbcEvents.addElement("nodeCollapsed(" + event.getRowKey() + ")");
			
		}
		/* (non-Javadoc)
		 * @see de.jwic.ecolib.treeviewer.ITreeModelListener#nodeExpanded(de.jwic.ecolib.treeviewer.TreeModelEvent)
		 */
		public void nodeExpanded(TableModelEvent event) {
			lbcEvents.addElement("nodeExpanded(" + event.getRowKey() + ")");
		}
		public void columnResized(TableModelEvent event) {
			lbcEvents.addElement("columnResized(" + event.getTableColumn().getTitle() + ")");
		}
	}
	
	/**
	 * Constructor.
	 * @param container
	 */
	public TableViewerDemo2(IControlContainer container) {
		super(container);
		
		lbcEvents = new ListBoxControl(this, "events");
		lbcEvents.setSize(10);
		lbcEvents.setWidth(500);
	
		viewer = new TableViewer(this, "tree");
		
		contentProvider = new DemoTaskContentProvider(createDemoData());
		viewer.setContentProvider(contentProvider);
		viewer.setTableLabelProvider(new LabelProvider());
		viewer.setScrollable(true);
		viewer.setResizeableColumns(true);
		viewer.setSelectableColumns(true);
		viewer.setWidth(400);
		viewer.setHeight(200);
		viewer.setExpandableColumn(1); // the expand action should be in the 2nd column.
		
		TableModel model = viewer.getModel();
		DemoTableViewerListener listener = new DemoTableViewerListener();
		model.addTableModelListener(listener);
		model.addElementSelectedListener(listener);
		// add listener to demonstrate sorting/images
		model.addTableModelListener(new TableModelAdapter() {
			public void columnSelected(TableModelEvent event) {
				handleSorting(event.getTableColumn());
			}
		});
		model.setSelectionMode(TableModel.SELECTION_SINGLE);
		createColumns();
		
		/*
		 * Add controls to modify TableViewer properties.
		 */
		ListBoxControl lbClass = new ListBoxControl(this, "lbClass");
		lbClass.addElement("tblViewer (default)", "tblViewer");
		lbClass.addElement("tblVFlat");
		lbClass.addElement("treeSimple");
		lbClass.setSelectedKey("tblViewer");
		lbClass.setChangeNotification(true);
		lbClass.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				viewer.setCssClass((String)event.getElement());
			};
		});
		
		
		// Change Status Bar property
		ListBoxControl lbStatusBar = new ListBoxControl(this, "lbStatusBar");
		lbStatusBar.addElement("True", "true");
		lbStatusBar.addElement("False", "false");
		lbStatusBar.setSelectedKey(viewer.isShowStatusBar() ? "true" : "false");
		lbStatusBar.setChangeNotification(true);
		lbStatusBar.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				viewer.setShowStatusBar(event.getElement().equals("true"));
			};
		});

		// showHeader
		ListBoxControl lbShowHead = new ListBoxControl(this, "lbShowHead");
		lbShowHead.addElement("True", "true");
		lbShowHead.addElement("False", "false");
		lbShowHead.setSelectedKey(viewer.isResizeableColumns() ? "true" : "false");
		lbShowHead.setChangeNotification(true);
		lbShowHead.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				viewer.setShowHeader(event.getElement().equals("true"));
			};
		});

		// Change ResizeableColumn property
		ListBoxControl lbResCol = new ListBoxControl(this, "lbResCol");
		lbResCol.addElement("True", "true");
		lbResCol.addElement("False", "false");
		lbResCol.setSelectedKey(viewer.isResizeableColumns() ? "true" : "false");
		lbResCol.setChangeNotification(true);
		lbResCol.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				viewer.setResizeableColumns(event.getElement().equals("true"));
			};
		});

		// Change SelectableColumn property
		ListBoxControl lbSelCol = new ListBoxControl(this, "lbSelCol");
		lbSelCol.addElement("True", "true");
		lbSelCol.addElement("False", "false");
		lbSelCol.setSelectedKey(viewer.isSelectableColumns() ? "true" : "false");
		lbSelCol.setChangeNotification(true);
		lbSelCol.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				viewer.setSelectableColumns(event.getElement().equals("true"));
			};
		});

		// Change ResizeableColumn property
		ListBoxControl lbScrollable = new ListBoxControl(this, "lbScrollable");
		lbScrollable.addElement("True", "true");
		lbScrollable.addElement("False", "false");
		lbScrollable.setSelectedKey(viewer.isScrollable() ? "true" : "false");
		lbScrollable.setChangeNotification(true);
		lbScrollable.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				viewer.setScrollable(event.getElement().equals("true"));
			};
		});

		// Change Listbox Width
		ListBoxControl lbWidth = new ListBoxControl(this, "lbWidth");
		lbWidth.addElement("0 - Unspecified", "0");
		for (int i = 200; i < 601; i += 50) {
			lbWidth.addElement(Integer.toString(i) + "px", Integer.toString(i));
		}
		lbWidth.setSelectedKey(Integer.toString(viewer.getWidth()));
		lbWidth.setChangeNotification(true);
		lbWidth.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				viewer.setWidth(Integer.parseInt((String)event.getElement()));
			};
		});
		
		// Change Listbox Height
		ListBoxControl lbHeight = new ListBoxControl(this, "lbHeight");
		lbHeight.addElement("0 - Unspecified", "0");
		for (int i = 50; i < 401; i += 50) {
			lbHeight.addElement(Integer.toString(i) + "px", Integer.toString(i));
		}
		lbHeight.setSelectedKey(Integer.toString(viewer.getHeight()));
		lbHeight.setChangeNotification(true);
		lbHeight.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				viewer.setHeight(Integer.parseInt((String)event.getElement()));
			};
		});

		// Change Listbox Width
		ListBoxControl lbVisible = new ListBoxControl(this, "btVisible");
		lbVisible.addElement("True", "true");
		lbVisible.addElement("False", "false");
		lbVisible.setSelectedKey(viewer.isVisible() ? "true" : "false");
		lbVisible.setChangeNotification(true);
		lbVisible.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				viewer.setVisible(event.getElement().equals("true"));
			};
		});

		// Change Listbox Width
		ListBoxControl lbSelMode = new ListBoxControl(this, "lbSelMode");
		lbSelMode.addElement("SELECTION_NONE", "0");
		lbSelMode.addElement("SELECTION_SINGLE", "1");
		lbSelMode.addElement("SELECTION_MULTI", "2");		
		lbSelMode.setSelectedKey(Integer.toString(viewer.getModel().getSelectionMode()));
		lbSelMode.setChangeNotification(true);
		lbSelMode.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				viewer.getModel().setSelectionMode(Integer.parseInt((String)event.getElement()));
				viewer.setRequireRedraw(true);
			};
		});

		
		btEnabled = new Button(this, "btEnabled");
		btEnabled.setTitle(viewer.isEnabled() ? "Disable" : "Enable");
		btEnabled.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				changeEnabled();
			};
		});

		btFillWidth = new Button(this, "btFillWidth");
		btFillWidth.setTitle(viewer.isFillWidth() ? "Disable" : "Enable");
		btFillWidth.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				changeFillWidth();
			};
		});
	
		
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
		DemoTask task = new DemoTask("Write Docu", "Mark", 100);
		task.done = true;
		data.add(task);
		task.addSubTask(new DemoTask("Tutorial", "Mark", 60));
		task.addSubTask(new DemoTask("Introduction", "Flo", 60));
		task.addSubTask(new DemoTask("Main Document", "Mark", 20));
		
		data.add(new DemoTask("Setup buildserver", "Ronny", 20));
		task = new DemoTask("Update jwic homepage", "?", 0);
		data.add(task);
		task.addSubTask(new DemoTask("Update Demos", "Flo", 0));
		task.addSubTask(new DemoTask("Write Tutorial", "Flo", 0));
		
		
		data.add(new DemoTask("Change default implementation", "Sam", 10));
		data.add(new DemoTask("Evaluate library XYZ for relevance", "Mark", 50));
		
		
		for (int i = 1; i < 105; i++) {
			DemoTask demoTask = new DemoTask();
			demoTask.done = i % 5 == 0;
			demoTask.completed = i;
			demoTask.title = "Task " + i;
			demoTask.owner = "?";
			data.add(demoTask);
			
			if (i % 4 == 0 || i % 11 == 0) {
				for (int a = 1; a < 5; a++) {
					DemoTask subTask = new DemoTask("Subtask " + i + "." + a, "Someone", i / 2);
					demoTask.addSubTask(subTask);
					if (a % 2 == 0) {
						subTask.addSubTask(new DemoTask("SubSubTask", "Anotherone", 0));
					}
				}
			}
			
		}
		
		return data;
	}
	
	/**
	 * Change between multiple and single selection.
	 */
	protected void changeFillWidth() {
		
		viewer.setFillWidth(!viewer.isFillWidth());
		btFillWidth.setTitle(viewer.isFillWidth() ? "Disable" : "Enable");
		
	}

	/**
	 * Change the Enabled property of the button.
	 */
	protected void changeEnabled() {
		
		viewer.setEnabled(!viewer.isEnabled());
		btEnabled.setTitle(viewer.isEnabled() ? "Disable" : "Enable");
		
	}

}
