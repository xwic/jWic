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
 * $Id: TableViewerDemo3.java,v 1.3 2010/02/07 14:26:34 lordsam Exp $
 * @author flippisch
 */
package de.jwic.ecolib.samples.controls.tbv3;

import java.io.File;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.JWicRuntime;
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
 * This TreeViewer sample demonstrates how to create an explorer like
 * tree.
 *
 * @author Florian Lippisch
 */
public class TableViewerDemo3 extends ControlContainer {

	private static final long serialVersionUID = 2L;

	private ListBoxControl lbcEvents = null;
	private Button btFillWidth;
	private Button btEnabled;
	
	private TableViewer viewer;
	private FileNodeContentProvider contentProvider;
	
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
	public TableViewerDemo3(IControlContainer container) {
		super(container);
		
		lbcEvents = new ListBoxControl(this, "events");
		lbcEvents.setSize(10);
		lbcEvents.setWidth(500);
	
		
		viewer = new TableViewer(this, "tree");
		
		FileTreeNode rootNode = new FileTreeNode(new File(JWicRuntime.getJWicRuntime().getRootPath()));
		contentProvider = new FileNodeContentProvider(rootNode);
		viewer.setContentProvider(contentProvider);
		viewer.setTableLabelProvider(new FileLabelProvider());
		viewer.setScrollable(true);
		viewer.setResizeableColumns(false);
		viewer.setSelectableColumns(false);
		viewer.setWidth(200);
		viewer.setHeight(300);
		viewer.setShowHeader(false);
		viewer.setCssClass("treeSimple");
		viewer.setShowStatusBar(false);
		viewer.setExpandableColumn(0);
		
		TableModel model = viewer.getModel();
		DemoTableViewerListener listener = new DemoTableViewerListener();
		model.addTableModelListener(listener);
		model.addElementSelectedListener(listener);
		model.setSelectionMode(TableModel.SELECTION_SINGLE);
		createColumns();
		
		/*
		 * Add controls to modify TableViewer properties.
		 */
		ListBoxControl lbClass = new ListBoxControl(this, "lbClass");
		lbClass.addElement("tblViewer (default)", "tblViewer");
		lbClass.addElement("tblVFlat");
		lbClass.addElement("treeSimple");
		lbClass.setSelectedKey("treeSimple");
		lbClass.setChangeNotification(true);
		lbClass.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				viewer.setCssClass((String)event.getElement());
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

		// Change ResizeableColumn property
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
	 * 
	 */
	private void createColumns() {
		
		// creats an empty column
		TableModel model = viewer.getModel();
		model.addColumn(new TableColumn("Directory", viewer.getWidth() - 4));
		
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
