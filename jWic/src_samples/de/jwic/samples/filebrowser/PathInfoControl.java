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
 * de.jwic.samples.filebrowser.PathInfoControl
 * Created on 25.05.2005
 * $Id: PathInfoControl.java,v 1.4 2010/01/26 11:25:18 lordsam Exp $
 */
package de.jwic.samples.filebrowser;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.InputBoxControl;
import de.jwic.controls.LabelControl;
import de.jwic.events.KeyListener;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.4 $
 */
public class PathInfoControl extends ControlContainer implements PropertyChangeListener {

	private InputBoxControl txtPath = null;
	private DirectoryModel model = null;
	private File root = null;
	
	/**
	 * @param control
	 * @param string
	 * @param model2
	 * @param rootFile
	 */
	public PathInfoControl(IControlContainer container, String name, DirectoryModel model, File root) {
		super(container, name);
		this.model = model;
		this.root = root;
		
		txtPath = new InputBoxControl(this, "txtPath");
		txtPath.setWidth(500);
		txtPath.setListenKeyCode(13); // Listen to the ENTER key
		txtPath.addKeyListener(new KeyListener() {
			public void keyPressed(de.jwic.events.KeyEvent event) {
				gotoPath(txtPath.getText());
			};
		});
				
		LabelControl lblPath = new LabelControl(this, "label");
		lblPath.setText("Path:");
		
		Button btGo = new Button(this, "btGo");
		btGo.setTitle("Go there");
		btGo.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				gotoPath(txtPath.getText());
			}
		});
		
		updatePath();
		model.addPropertyChangeListener(this);
		
	}

	/**
	 * @param text
	 */
	protected void gotoPath(String text) {
		
		try {
			String newPath = root.getAbsolutePath() + text;
			File f = new File(newPath);
			if (f.exists() && 
					f.isDirectory() &&
					f.getCanonicalPath().startsWith(root.getCanonicalPath()) &&
					text.indexOf("..") == -1) {
				
				
				model.setDirectory(f);
			} else {
				updatePath();
			}
		} catch (IOException ioe) {
			updatePath();
		}
		
	}

	/**
	 * 
	 */
	private void updatePath() {
		
		File path = model.getDirectory();
		int l = root.getAbsolutePath().length();
		txtPath.setText(path.getAbsolutePath().substring(l));
		
	}
	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		updatePath();
	}
}
