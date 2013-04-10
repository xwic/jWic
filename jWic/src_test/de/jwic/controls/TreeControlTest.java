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
 * de.jwic.controls.TreeControlTest
 * Created on 21.09.2006
 * $Id: TreeControlTest.java,v 1.1 2006/09/21 09:41:18 lordsam Exp $
 */
package de.jwic.controls;

import javax.swing.tree.DefaultMutableTreeNode;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.test.ControlTestCase;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public class TreeControlTest extends ControlTestCase {

	private TreeControl tree;
	private DefaultMutableTreeNode rootNode = null; 
	private int invocationCount = 0;
	
	private class TestListener implements ElementSelectedListener {
		public void elementSelected(ElementSelectedEvent event) {
			invocationCount++;
		}
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.test.ControlTestCase#createControl(de.jwic.base.IControlContainer)
	 */
	public Control createControl(IControlContainer container) {
		tree = new TreeControl(container);
		return tree;
	}
	
	private void addTestNodes() {
		rootNode = new DefaultMutableTreeNode("root");
		tree.setRootNode(rootNode);
		
		rootNode.add(new DefaultMutableTreeNode("node1"));
		rootNode.add(new DefaultMutableTreeNode("node2"));
		rootNode.add(new DefaultMutableTreeNode("node3"));
		
		DefaultMutableTreeNode node4 = new DefaultMutableTreeNode("node4");
		node4.add(new DefaultMutableTreeNode("node4.1"));
		node4.add(new DefaultMutableTreeNode("node4.2"));
		node4.add(new DefaultMutableTreeNode("node4.3"));
		
	}
	
	/*
	 * Test method for 'de.jwic.controls.TreeControl.addElementSelectedListener(ElementSelectedListener)'
	 */
	public void testElementSelectedListener() {

		addTestNodes();
		ElementSelectedListener listener1 = new TestListener();
		ElementSelectedListener listener2 = new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				assertEquals(1, tree.getSelected().size());
				assertEquals(event.getElement(), tree.getSelected().iterator().next());
			};
		};
		tree.addElementSelectedListener(listener1);
		tree.addElementSelectedListener(listener2);
		
		invocationCount = 0;
		sendAction(tree, TreeControl.ACTION_CLICK, "0");
		assertEquals(1, invocationCount);
		
		invocationCount = 0;
		sendAction(tree, TreeControl.ACTION_CLICK, "0-1");
		assertEquals(1, invocationCount);
		
		//tree.removeElementSelectedListener(listener);
		
		// test manual selection
		invocationCount = 0;
		tree.clearSelection();
		tree.select("0");
		assertEquals(1, invocationCount);

		// test remove
		tree.removeElementSelectedListener(listener1);
		tree.removeElementSelectedListener(listener2);
		
		invocationCount = 0;
		tree.clearSelection();
		tree.select("0");
		assertEquals(0, invocationCount);

		
	}

}
