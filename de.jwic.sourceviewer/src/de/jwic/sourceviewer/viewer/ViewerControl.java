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
package de.jwic.sourceviewer.viewer;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.LabelControl;
import de.jwic.sourceviewer.main.SVModel;
import de.jwic.sourceviewer.main.SVModelAdapter;
import de.jwic.sourceviewer.main.SVModelEvent;
import de.jwic.sourceviewer.model.NavigationElement;
import de.jwic.sourceviewer.viewer.impl.CssCodeViewer;
import de.jwic.sourceviewer.viewer.impl.FolderViewer;
import de.jwic.sourceviewer.viewer.impl.GroupViewer;
import de.jwic.sourceviewer.viewer.impl.HTMLCodeViewer;
import de.jwic.sourceviewer.viewer.impl.ImageViewer;
import de.jwic.sourceviewer.viewer.impl.JavaCodeViewer;
import de.jwic.sourceviewer.viewer.impl.PackageViewer;
import de.jwic.sourceviewer.viewer.impl.ProjectViewer;
import de.jwic.sourceviewer.viewer.impl.PropertiesViewer;
import de.jwic.sourceviewer.viewer.impl.SourceFolderViewer;
import de.jwic.sourceviewer.viewer.impl.TextViewer;
import de.jwic.sourceviewer.viewer.impl.VelocityCodeViewer;

/**
 * 
 * @author Florian Lippisch
 */
public class ViewerControl extends ControlContainer {

	private int width = 600;

	private NavigationElement element = null;
	private SVModel model;

	private ElementInfoControl elementInfo;
	private ContentViewer content;
	private LabelControl emptyLabel = null;

	private IObjectViewer currViewer = null;
	private IObjectViewer[] viewers;

	/**
	 * @param container
	 * @param name
	 * @param model
	 */
	public ViewerControl(IControlContainer myContainer, String name, SVModel model) {
		super(myContainer, name);
		this.model = model;

		model.addSVModelListener(new SVModelAdapter() {
			public void elementSelected(SVModelEvent event) {
				setElement(event.getElement());
			}
		});

		content = new ContentViewer(this, "content");

		elementInfo = new ElementInfoControl(this, "info");

		emptyLabel = new LabelControl(content);
		emptyLabel.setText("<br><p align=center>- no viewer for this file available -</p>");
		content.setCurrControl(emptyLabel.getName());

		createViewers();
	}

	/**
	 * Creates the available viewers. In future versions, this step should be
	 * based upon configuration or some plugin mechanism.
	 * 
	 */
	private void createViewers() {

		viewers = new IObjectViewer[12];

		viewers[0] = new JavaCodeViewer(content, "java");
		viewers[1] = new VelocityCodeViewer(content, "vel");
		viewers[2] = new ImageViewer(content, "img");
		viewers[3] = new HTMLCodeViewer(content, "html");
		viewers[4] = new PropertiesViewer(content, "prop");
		viewers[5] = new CssCodeViewer(content, "css");
		viewers[6] = new PackageViewer(content, "package");
		viewers[7] = new FolderViewer(content,"folder");
		viewers[8] = new SourceFolderViewer(content,"srcfolder");
		viewers[9] = new ProjectViewer(content,"project");
		viewers[10] = new GroupViewer(content,"group");
		viewers[11] = new TextViewer(content, "txt");

		for (int i = 0; i < viewers.length; i++) {
			viewers[i].init(model);
		}
	}

	/**
	 * @return the element
	 */
	public NavigationElement getElement() {
		return element;
	}

	/**
	 * @param element
	 *            the element to set
	 */
	public void setElement(NavigationElement element) {
		this.element = element;
		elementInfo.setElement(element);

		if (currViewer != null) {
			currViewer.setNavigationElement(null); // clear
			currViewer = null;
		}

		for (int i = 0; i < viewers.length; i++) {
			if (viewers[i].isSupported(element)) {
				currViewer = viewers[i];
				break;
			}
		}

		if (currViewer == null) {
			content.setCurrControl(emptyLabel.getName());
		} else {
			content.setCurrControl(currViewer.getName());
			currViewer.setNavigationElement(element);
		}

		content.setTop(0); // scroll to the top
	}

	/**
	 * Set the width.
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
		content.setWidth(width + "px");
	}

	/**
	 * Set the height.
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		content.setHeight((height - 40) + "px");
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

}
