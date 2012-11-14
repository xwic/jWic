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
 * de.jwic.ecolib.samples.objectlink.ObjectLinkDemo
 * Created on 12.04.2007
 * $Id: ObjectLinkDemo.java,v 1.1 2007/04/25 09:59:15 lordsam Exp $
 */
package de.jwic.ecolib.samples.objectlink;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import de.jwic.base.Application;
import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.Page;
import de.jwic.controls.TabControl;
import de.jwic.controls.TabStripControl;
import de.jwic.controls.WindowControl;
import de.jwic.ecolib.objectlinktool.ObjectLinkAdapter;
import de.jwic.ecolib.objectlinktool.ObjectLinkEvent;
import de.jwic.ecolib.objectlinktool.ObjectLinkTool;
import de.jwic.ecolib.objectlinktool.ObjectListDef;
import de.jwic.ecolib.tableviewer.TableColumn;
import de.jwic.ecolib.tableviewer.TableViewer;
import de.jwic.ecolib.tableviewer.defaults.ListContentProvider;

/**
 *
 * @author Florian Lippisch
 */
public class ObjectLinkDemo extends Application {

	/* (non-Javadoc)
	 * @see de.jwic.base.Application#createRootControl(de.jwic.base.IControlContainer)
	 */
	public Control createRootControl(IControlContainer container) {
		
		Page page = new Page(container);
		page.setTitle("ObjectLink Demo");
		
		WindowControl win = new WindowControl(page);
		win.setTitle("Object Link Demo");
		win.setWidth("100%");
		
		try {
			ObjectListDef leftData = createLeadData();
			ObjectListDef rightData = createOrderData();
			
			TabStripControl tabStrip = new TabStripControl(win);
			
			TabControl tab = tabStrip.addTab("Object Link Tool");

			ObjectLinkTool linkTool = new ObjectLinkTool(tab, leftData, rightData);
			linkTool.setWidthHint(1000);
			linkTool.setLinkMatcher(new LeadOrderMatcher());
			linkTool.getObjectLinkModel().setMinScore(0.3);
			
			// add other 2 tables for testing
			tab = tabStrip.addTab("Leads");
			
			TableViewer leadViewer = new TableViewer(tab);
			leadViewer.setContentProvider(leftData.getContentProvider());
			leadViewer.setTableLabelProvider(leftData.getLabelProvider());
			TableColumn[] cols = leftData.getTableColumns(); 
			for (int i = 0; i < cols.length; i++) {
				leadViewer.getModel().addColumn((TableColumn)cols[i].clone());
			}
			
			tab = tabStrip.addTab("Orders");
			
			TableViewer orderViewer = new TableViewer(tab);
			orderViewer.setContentProvider(rightData.getContentProvider());
			orderViewer.setTableLabelProvider(rightData.getLabelProvider());
			cols = rightData.getTableColumns(); 
			for (int i = 0; i < cols.length; i++) {
				orderViewer.getModel().addColumn((TableColumn)cols[i].clone());
			}
			
			linkTool.addLinkListener(new ObjectLinkAdapter() {
				/* (non-Javadoc)
				 * @see de.jwic.ecolib.objectlinktool.IObjectLinkListener#elementsSelected(de.jwic.ecolib.objectlinktool.ObjectLinkEvent)
				 */
				public void linkElementsRequested(ObjectLinkEvent event) {
					System.out.println("linkElements: " + event.getKeyLeft() + " to " + event.getKeyRight());					
				}
			});
			
		} catch (Exception e) {
			// TODO proper Error handling...
			throw new RuntimeException("Error initializing application.: " + e, e);
		}
		
		
		return page;
	}

	/**
	 * @return
	 * @throws ParseException 
	 */
	private ObjectListDef createOrderData() throws ParseException {
		ObjectListDef data = new ObjectListDef();
		
		// 1.) create ContentProvider based upon some demo data entries
		List entries = new ArrayList();
		entries.add(new Order("PO-0010", "BMW AG", "ABC Database Server", "2007-03-01"));
		entries.add(new Order("PO-0011", "Money Bank", "ABC Database Server", "2007-03-01"));
		entries.add(new Order("PO-0012", "Ronny Pfretzschner", "jWic Enterprise Edition", "2007-03-01"));
		entries.add(new Order("PO-0310", "Evil Genius", "Rule the World Kit", "2007-04-01"));
		entries.add(new Order("XZ-0410", "More Money Bank", "ABC Database Server", "2007-03-21"));
		entries.add(new Order("XZ-4711", "Markus D. Services", "Developer Toolkit", "2007-02-21"));
		entries.add(new Order("PO-0020", "pol GmbH", "ABC Database Server", "2007-01-01"));
		entries.add(new Order("SO-0123", "Googzone", "Storage System 4711", "2007-07-15"));
		entries.add(new Order("PO-0440", "Googzone", "Enterprise Database Server", "2006-10-01"));
		entries.add(new Order("PO-0433", "pol GmbH", "Office Server", "2006-03-01"));
		entries.add(new Order("PO-5512", "Far Far Away AG", "ABC Database Server", "2006-11-01"));
		entries.add(new Order("SO-2345", "DemoCompany", "A Guide To jWic", "2005-10-01"));
		entries.add(new Order("PO-5555", "BMW AG", "Database Server Kit", "2005-10-01"));
		entries.add(new Order("PO-5553", "Bichelmayer", "Database Server Kit", "2006-10-01"));
		entries.add(new Order("PO-5559", "Abstract Samples", "Database Server Kit", "2006-02-01"));

		data.setContentProvider(new ListContentProvider(entries) {
			public String getUniqueKey(Object object) {
				Order order = (Order)object;
				return order.id;
			}
		});
		
		// 2.) set LabelProvider
		data.setLabelProvider(new OrderLabelProvider());
		
		// 3.) add Columns
		TableColumn[] columns = new TableColumn[4];
		columns[0] = new TableColumn("Order ID", 80);
		columns[1] = new TableColumn("Customer Name", 150);
		columns[2] = new TableColumn("Product");
		columns[3] = new TableColumn("Order Date", 80);
		
		data.setTableColumns(columns);
		
		return data;
	}

	/**
	 * @return
	 * @throws ParseException 
	 */
	private ObjectListDef createLeadData() throws ParseException {
		ObjectListDef data = new ObjectListDef();
		
		// 1.) create ContentProvider based upon some demo data entries
		List entries = new ArrayList();
		entries.add(new Lead("A192990010", "BMW AG", "Germany", "2007-02-18"));
		entries.add(new Lead("A939929221", "Money Bank Europe", "Swizerland", "2007-03-01"));
		entries.add(new Lead("A099491123", "Ronny H. Pfretzschner", "Germany", "2007-02-21"));
		entries.add(new Lead("B010102023", "Ronny H. Pfretzschner", "Germany", "2007-04-01"));
		entries.add(new Lead("C203023021", "More Money Bank", "Swizerland", "2007-03-21"));
		entries.add(new Lead("A020203333", "pol IT GmbH", "Germany", "2007-01-01"));
		entries.add(new Lead("B202020202", "Googzone", "France", "2007-07-15"));
		entries.add(new Lead("A112312312", "Googzone", "France", "2006-10-01"));
		entries.add(new Lead("A000000001", "pol IT GmbH", "Germany", "2006-03-01"));
		entries.add(new Lead("LE12020202", "DemoCompany", "USA", "2005-10-01"));
		entries.add(new Lead("LE12020203", "Mustermann KG", "Germany", "2005-10-01"));

		data.setContentProvider(new ListContentProvider(entries) {
			public String getUniqueKey(Object object) {
				Lead lead = (Lead)object;
				return lead.id;
			}
		});
		
		// 2.) set LabelProvider
		data.setLabelProvider(new LeadLabelProvider());
		
		// 3.) add Columns
		TableColumn[] columns = new TableColumn[4];
		columns[0] = new TableColumn("Lead ID");
		columns[1] = new TableColumn("Name");
		columns[2] = new TableColumn("Area");
		columns[3] = new TableColumn("Contact Date");
		
		data.setTableColumns(columns);
		
		return data;
	}

}
