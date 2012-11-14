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
 * de.jwic.samples.filebrowser.FileInfoControl
 * Created on 25.05.2005
 * $Id: FileInfoControl.java,v 1.4 2010/01/26 11:25:18 lordsam Exp $
 */
package de.jwic.samples.filebrowser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.LabelControl;
import de.jwic.controls.ScrollableContainer;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.samples.filebrowser.viewer.ImagePreview;
import de.jwic.samples.filebrowser.viewer.XWicPreview;

/**
 * Displays informations about a file. 
 * @author Florian Lippisch
 * @version $Revision: 1.4 $
 */
public class FileInfoControl extends ControlContainer {

	private File file = null;
	private File rootFile = null;
	private ScrollableContainer preview = null;
	
	private List<ClosedListener> listeners = new ArrayList<ClosedListener>();
	
	/**
	 * Constructor.
	 * @param container
	 * @param name
	 * @param rootFile
	 */
	public FileInfoControl(IControlContainer container, String name, File rootFile) {
		super(container, name);
		this.rootFile = rootFile;

		Button btOk = new Button(this, "btOk");
		btOk.setTitle("Ok");
		btOk.addSelectionListener( new SelectionListener () {
			public void objectSelected(SelectionEvent event) {
				close();
			}
		});
	
		preview = new ScrollableContainer(this, "preview");
		preview.setWidth("100%");
		preview.setHeight("200px");

	}
	
	/**
	 * Add a listener to the control that should be notified when the control 
	 * requests to be closed.
	 * @param listener
	 */
	public void addClosedListener(ClosedListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Request closing of this control. Notifies the listeners (usualy the container)
	 * to do the requried actions (hide it).
	 */
	protected void close() {
		
		ClosedEvent event = new ClosedEvent(this);
		for (Iterator<ClosedListener> it = listeners.iterator(); it.hasNext(); ) {
			ClosedListener listener = it.next();
			listener.closed(event);
		}
		
	}

	/**
	 * @return Returns the file.
	 */
	public File getFile() {
		return file;
	}
	/**
	 * @param file The file to set.
	 */
	public void setFile(File file) {
		this.file = file;
		
		log.debug("preview file " + file.getName());
		preview.removeControl("preview");
		if (file.getName().endsWith(".gif") ||
			file.getName().endsWith(".png") ||
			file.getName().endsWith(".jpeg") ||
			file.getName().endsWith(".jpg")) {
			new ImagePreview(preview, "preview", file, rootFile);
		} else if (file.getName().endsWith(".xwic")) {
			new XWicPreview(preview, "preview", file);
		} else {
			LabelControl l = new LabelControl(preview, "preview");
			l.setText("No Preview available");
		}
		
	}
	
	public String getDownloadPath() {
		
		try {
			String path = file.getCanonicalPath();
			String rootPath = rootFile.getCanonicalPath();
			
			String relativePath = path.substring(rootPath.length());
			relativePath = relativePath.replace('\\', '/');	// make slash out of backslashes (required on windows systems)
			
			
			if (relativePath.indexOf("WEB-INF") == -1) {
				return relativePath;
			}
			
		} catch (IOException e) {
			log.error("Error calculating download path for file " + file.getName());
		}
		
		return null;
	}
}
