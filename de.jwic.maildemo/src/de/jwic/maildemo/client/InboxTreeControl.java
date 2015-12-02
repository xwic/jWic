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
package de.jwic.maildemo.client;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.TreeControl;
import de.jwic.maildemo.api.IFolder;
import de.jwic.maildemo.main.MailModel;

/**
 *
 * @author Florian Lippisch
 */
public class InboxTreeControl extends ControlContainer {

	private TreeControl tree = null;
	
	/**
	 * @param container
	 * @param name
	 */
	public InboxTreeControl(IControlContainer container, String name, MailModel model) {
		super(container, name);
		
		tree = new TreeControl(this);
		tree.setTemplateName(getClass().getName() + "_tree");
		IFolder topFolder = model.getSession().getRootFolder();
		FolderTreeNode topNode = new FolderTreeNode(null, topFolder);
		tree.setRootNode(topNode);
		
		tree.setRenderRootNode(false);
	}
	
	/**
	 * @return the tree
	 */
	public TreeControl getTree() {
		return tree;
	}
}
