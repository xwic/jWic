package de.jwic.ecolib.samples.controls;

import java.beans.PropertyChangeListener;
import java.io.Serializable;

import de.jwic.base.ImageRef;
import de.jwic.ecolib.actions.IAction;

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
