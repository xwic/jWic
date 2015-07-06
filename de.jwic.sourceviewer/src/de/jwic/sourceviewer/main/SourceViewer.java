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
package de.jwic.sourceviewer.main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.ImageRef;
import de.jwic.controls.ActionBarControl;
import de.jwic.controls.Button;
import de.jwic.controls.ListBoxControl;
import de.jwic.ecolib.controls.StackedContainer;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.events.SessionAdapter;
import de.jwic.events.SessionEvent;
import de.jwic.sourceviewer.model.Group;
import de.jwic.sourceviewer.model.NavigationElement;
import de.jwic.sourceviewer.model.Workspace;
import de.jwic.sourceviewer.navigator.GroupNavigator;
import de.jwic.sourceviewer.viewer.ViewerControl;

/**
 * The SourceViewer control - the main part.
 * @author Florian Lippisch
 */
public class SourceViewer extends ControlContainer {

	private int width = 800;
	private int height = 600;
	
	private ListBoxControl lbGroup;
	private GroupNavigator navigator;
	private ActionBarControl actionBar;
	private ViewerControl viewer;
	private AboutControl about;
	
	private StackedContainer content;
	
	private SVModel model;
	private Map groupNameMap = new HashMap();

	
	/**
	 *
	 * @author Florian Lippisch
	 */
	private final class ModelController extends SVModelAdapter {
		public void elementSelected(SVModelEvent event) {
			// ensure the viewer is visible.
			if (!content.getCurrentControlName().equals(viewer.getName())) {
				content.setCurrentControlName(viewer.getName());
			}
		}

		public void groupSelected(SVModelEvent event) {
			lbGroup.setSelectedKey(event.getElement().getName());
		}
	}

	/**
	 * @param container
	 * @param name
	 */
	public SourceViewer(IControlContainer container, String name, SVModel svModel) {
		super(container, name);
		this.model = svModel;
		
		createControls();
		
		String sName = getSessionContext().getInitParameter("name"); 
		if (sName != null) {
			model.openEntryByName(sName);
		} else {
			if (model.getWorkspace().getChilds().size() > 0) {
				model.setCurrentElement((NavigationElement)model.getWorkspace().getChilds().get(0));
			}
		}
		
		// Add a listener to select a resource when a session is
		// reactivated. This happens when the user is coming "back"
		// through a link.
		getSessionContext().addSessionListener(new SessionAdapter() {
			/* (non-Javadoc)
			 * @see de.jwic.events.SessionAdapter#sessionReused(de.jwic.events.SessionEvent)
			 */
			public void sessionReused(SessionEvent event) {
				String sName = event.getParameter("name");
				if (sName != null) {
					model.openEntryByName(sName);
				}
			}
		});
		
	}
	
	/**
	 * Create the controls.
	 *
	 */
	private void createControls() {
		
		content = new StackedContainer(this, "content");
		
		lbGroup = new ListBoxControl(this, "lbGroup");
		lbGroup.setFillWidth(true);
		
		//lbGroup.setCssClass("small");
		
		// load groups
		Workspace ws = model.getWorkspace();
		for (Iterator it = ws.getChilds().iterator(); it.hasNext(); ) {
			Group group = (Group)it.next();
			lbGroup.addElement(group.getName());
			groupNameMap.put(group.getName(), group);
			if (lbGroup.getSelectedKey().equals("")) {
				lbGroup.setSelectedKey(group.getName());
			}
		}
		
		lbGroup.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				handleGroupSelection((String)event.getElement());
			}
		});
		lbGroup.setChangeNotification(true);
		
		// the navigator
		navigator = new GroupNavigator(this, "navigator", model);
		navigator.setWidth("300px");
		navigator.setHeight("600px");
		
		// the action bar
		setupActionBar();
		
		model.addSVModelListener(new ModelController());
		
		// the viewer.
		viewer = new ViewerControl(content, "viewer", model);
		about = new AboutControl(content, "about");
		about.addBackSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				// produce an element selection event, to hide
				// the about window and to properly set history
				model.setCurrentElement(model.getCurrentElement());
			}
		});
		
		content.setCurrentControlName(viewer.getName());
		
	}

	/**
	 * 
	 */
	private void setupActionBar() {

		actionBar = new ActionBarControl(this, "abar");
		
		// disabled, because we use the browsers history with the
		// HistoryController control. 
		/*
		final ButtonControl btBack = new ButtonControl(actionBar);
		btBack.setTitle("Back");
		btBack.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				model.back();
			}
		});
		btBack.setIconEnabled(new ImageRef(getClass().getPackage(), "back_a.gif"));
		btBack.setIconDisabled(new ImageRef(getClass().getPackage(), "back_i.gif"));
		btBack.setEnabled(false);
		*/
		
		Button btExit = new Button(actionBar);
		btExit.setTitle("Exit");
		btExit.setIconEnabled(new ImageRef(getClass().getPackage(), "exit.gif"));
		btExit.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				doExit();
			}
		});

		Button btAbout = new Button(actionBar);
		btAbout.setTitle("About");
		btAbout.setIconEnabled(new ImageRef(getClass().getPackage(), "jlogo16.gif"));
		btAbout.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				doAbout();
			}
		});
		
	}

	/**
	 * Open the about Page.
	 *
	 */
	protected void doAbout() {
		
		if (!content.getCurrentControlName().equals(about.getName())) {
			content.setCurrentControlName(about.getName());
		}
	}

	/**
	 * Exit the application.
	 *
	 */
	protected void doExit() {
		
		getSessionContext().exit();
		
	}
	
	
	/**
	 * @param string
	 */
	protected void handleGroupSelection(String name) {

		Group group = (Group)groupNameMap.get(name);
		model.setCurrentGroup(group);
		
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
		navigator.setHeight((height - 40) + "px");
		viewer.setHeight((height - 40));
		requireRedraw();
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
		viewer.setWidth((width - 303)); // because "borders" must get substracted as well, we have -303 instead of -300.
		requireRedraw();
	}

}
