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
 * de.jwic.wap.core.wizard.Wizard
 * Created on 23.03.2006
 * $Id: Wizard.java,v 1.2 2008/09/18 18:20:00 lordsam Exp $
 */
package de.jwic.controls.wizard;

import java.util.ArrayList;
import java.util.List;

import de.jwic.base.SessionContext;

/**
 * Provides and controlles a set of WizardPages. A Wizard is hosted within a
 * WizardDialog. 
 *  
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public abstract class Wizard {

	private List<WizardPage> pages = new ArrayList<WizardPage>();
	private String title = "Wizard";
	private int width = 0;
	private int height = 0;
	
	/**
	 * Returns the title
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Set the title of the wizard.
	 * @param newTitle
	 */
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	
	/**
	 * Creates the WizardPages used by this wizard and returns the initial page.
	 * @param site 
	 * @return
	 */
	public abstract WizardPage createWizardPages(SessionContext sessionContext);
	
	
	/**
	 * Add a WizardPage to the wizard.
	 * @param page
	 */
	protected final void addWizardPage(WizardPage page) {
		
		pages.add(page);
		
	}
	
	/**
	 * Returns true if the specified page has a following page. This method
	 * may be overriden to implement content-specific page navigation.
	 * @param page
	 * @return
	 */
	public boolean hasNext(WizardPage page) {
		int idx = pages.indexOf(page);
		return (idx != -1 && (idx < pages.size() - 1));
	}

	/**
	 * Returns the following page to the specified page. This method
	 * may be overriden to implement content-specific page navigation.
	 * @param page
	 * @return
	 */
	public WizardPage getNextPage(WizardPage page) {
		int idx = pages.indexOf(page);
		if (idx != -1 && (idx < pages.size() - 1)) {
			return pages.get(idx + 1);
		} 
		return null;
	}

	/**
	 * Returns true if the specified page has a following page. This method
	 * may be overriden to implement content-specific page navigation.
	 * @param page
	 * @return
	 */
	public boolean hasPrevious(WizardPage page) {
		int idx = pages.indexOf(page);
		return (idx != -1 && (idx > 0));
	}

	/**
	 * Returns the following page to the specified page. This method
	 * may be overriden to implement content-specific page navigation.
	 * @param page
	 * @return
	 */
	public WizardPage getPreviousPage(WizardPage page) {
		int idx = pages.indexOf(page);
		if (idx != -1 && (idx > 0)) {
			return pages.get(idx - 1);
		} 
		return null;
	}

	
	/**
	 * Returns the list of pages.
	 * @return
	 */
	public final List<WizardPage> getWizardPages() {
		return pages;
	}
	
	/**
	 * Perform the finish action.
	 * @return
	 * @throws ValidationException 
	 */
	public abstract boolean performFinish() throws ValidationException;
	
	/**
	 * The user aborted the wizard. 
	 */
	public void performAbort() {
		// nothing to do
	}

	/**
	 * @return Returns the height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height The height to set.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return Returns the widthHint.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width The width to set.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @param currentPage
	 * @return
	 */
	public boolean canFinish(WizardPage currentPage) {
		return !hasNext(currentPage);
	}
	
}
