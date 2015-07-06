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
package de.jwic.ecolib.samples.controls;

import java.beans.PropertyChangeListener;
import java.io.Serializable;

import de.jwic.base.ImageRef;
import de.jwic.controls.actions.IAction;

public class TestAction implements IAction, Serializable {

	private static final long serialVersionUID = 8672921576673923822L;

	public TestAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	public void run() {
		System.out.println("TESTACTION");
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#getIconDisabled()
	 */
	public ImageRef getIconDisabled() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#getIconEnabled()
	 */
	public ImageRef getIconEnabled() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#setIconDisabled(de.jwic.base.ImageRef)
	 */
	public void setIconDisabled(ImageRef iconDisabled) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#setIconEnabled(de.jwic.base.ImageRef)
	 */
	public void setIconEnabled(ImageRef iconEnabled) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#setTitle(java.lang.String)
	 */
	public void setTitle(String title) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#setVisible(boolean)
	 */
	public void setVisible(boolean visible) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#getTooltip()
	 */
	public String getTooltip() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#setTooltip(java.lang.String)
	 */
	public void setTooltip(String tooltip) {
		// TODO Auto-generated method stub
		
	}
}
