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
 * de.jwic.samples.controls.LabelDemo
 * Created on 28.10.2005
 * $Id: TreeDemo.java,v 1.1 2010/04/22 16:00:11 lordsam Exp $
 */
package de.jwic.samples.controls;

import java.io.File;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.LabelControl;
import de.jwic.controls.Tree;
import de.jwic.data.IBaseLabelProvider;
import de.jwic.data.DataLabel;
import de.jwic.samples.controls.propeditor.PropertyEditorView;

/**
 * 
 * Demonstrates the usage of the ButtonControl.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public class TreeDemo extends ControlContainer {

	private Tree<Folder> tree;
	private LabelControl lblInfo;
	
	/**
	 * Constructor.
	 * @param container
	 */
	public TreeDemo(IControlContainer container) {
		super(container);
		
		// create the button instance
		tree = new Tree<Folder>(this, "tree");
		
		// create a simple data model -> wrap around directory tree
		Folder root = new Folder(new File("."));
		tree.setContentProvider(new FolderContentProvider(root));
		tree.setLabelProvider(new IBaseLabelProvider<Folder>() {
			/* (non-Javadoc)
			 * @see de.jwic.data.IBaseLabelProvider#getBaseLabel(java.lang.Object)
			 */
			public DataLabel getBaseLabel(Folder folder) {
				return new DataLabel(folder.getName());
			}
		});
		
		// create the property editor
		final PropertyEditorView propEditor = new PropertyEditorView(this, "propEditor");
		propEditor.setBean(tree);

		lblInfo = new LabelControl(this, "lblInfo");
		lblInfo.setText("- Select something -");
		
	}

	/**
	 * @param eventSource
	 */
	protected void showInfo(String info) {
		
		lblInfo.setText(info);
		
	}
	
}
