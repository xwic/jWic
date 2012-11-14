/*
 * Copyright 2005 jWic Group (http://www.jwic.de)
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
 * de.jwic.samples.filebrowser.FileBrowserControl
 * Created on 24.05.2005
 * $Id: FileBrowserControl.java,v 1.10 2010/02/08 21:09:50 lordsam Exp $
 */
package de.jwic.samples.filebrowser;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.Serializable;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.ImageRef;
import de.jwic.base.JWicRuntime;
import de.jwic.controls.ActionBarControl;
import de.jwic.controls.Button;
import de.jwic.controls.ButtonControl;
import de.jwic.controls.ActionBarControl.Align;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.10 $
 */
public class FileBrowserControl extends ControlContainer {

	private final static ImageRef IMG_UPPERDIR = new ImageRef(FileBrowserControl.class.getPackage(), "upperdir.png");
	private final static ImageRef IMG_UPPERDIR_DIS = new ImageRef(FileBrowserControl.class.getPackage(), "upperdir_dis.png");
	private final static ImageRef IMG_EXIT = new ImageRef(FileBrowserControl.class.getPackage(), "exit.gif");
	
	private File rootFile = null;
	private FileListView list = null;
	private DirectoryModel model = null;
	private FileTreeView tree = null;
	private ControlContainer info = null;
	private FileInfoControl fileInfo = null;
	private ButtonControl btUp = null;
	
	/**
	 * @param container
	 */
	public FileBrowserControl(IControlContainer container) {
		super(container);
		init();
	}
	/**
	 * @param container
	 * @param name
	 */
	public FileBrowserControl(IControlContainer container, String name) {
		super(container, name);
		init();
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.Control#init()
	 */
	private void init() {
		
		tree = new FileTreeView(this, "tree");
		tree.setHeight("390px");
		
		rootFile = new File(JWicRuntime.getJWicRuntime().getRootPath());
		model = new DirectoryModel(rootFile);
		
		tree.initView(model, rootFile);
		
		// simply register the PathInfoControl by creating an instance...
		new PathInfoControl(this, "pathinfo", model, rootFile); 

		info = new ControlContainer(this, "info");
		
		list = new FileListView(info, "list");
		list.getFileListControl().addElementSelectedListener(new FileSelectionController());
		list.getFileListControl().setDirectoryModel(model);
		list.getFileListControl().setHeight("390px");
		
		fileInfo = new FileInfoControl(info, "fileInfo", rootFile);
		fileInfo.setVisible(false);
		fileInfo.addClosedListener(new ClosedListener() {
			public void closed(ClosedEvent event) {
				fileInfo.setVisible(false);
				list.setVisible(true);
			}
		});
		
		ActionBarControl abar = new ActionBarControl(this, "actionbar");
		setupActionBar(abar);
		
		model.addPropertyChangeListener(new ModelObserver());
		//model.setDirectory(rootFile);
		
	}
	
	
	/**
	 * @param abar
	 */
	private void setupActionBar(ActionBarControl abar) {
		
		
		ButtonControl btExit = new ButtonControl(abar);
		btExit.setTitle("Exit");
		btExit.setIconEnabled(IMG_EXIT);
		btExit.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				doExit();
			}
		});
		
		abar.setPosition(btExit, Align.RIGHT);
		
		btUp = new ButtonControl(abar);
		btUp.setTitle("Upper Dir");
		btUp.setIconEnabled(IMG_UPPERDIR);
		btUp.setIconDisabled(IMG_UPPERDIR_DIS);
		btUp.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				goUpperDir();
			}
		});
		btUp.setEnabled(false);
		
	}


	/**
	 * 
	 */
	protected void doExit() {
		
		getSessionContext().exit();
		
	}


	/**
	 * Move to the upper directory.
	 */
	protected void goUpperDir() {
		
		if (!model.getDirectory().equals(rootFile)) {
			model.setDirectory(model.getDirectory().getParentFile());
		}
		
	}


	/**
	 * The current directory has changed. 
	 */
	protected void directoryChanged() {

		btUp.setEnabled(!model.getDirectory().equals(rootFile));
		fileInfo.setVisible(false);
		list.setVisible(true);
		
	}


	/**
	 * Handle selections in the filelist 
	 */
	class FileSelectionController implements ElementSelectedListener {
		public void elementSelected(ElementSelectedEvent event) {
			
			File file = (File)event.getElement();
			if (!file.isDirectory()) {
				// display file info
				list.setVisible(false);
				fileInfo.setFile(file);
				fileInfo.setVisible(true);
			}
		}
	}

	private class ModelObserver implements PropertyChangeListener, Serializable {
		public void propertyChange(PropertyChangeEvent evt) {
			directoryChanged();
		}
	}
}
