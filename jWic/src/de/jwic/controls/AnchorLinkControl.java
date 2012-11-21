/*
 * de.jwic.controls.AnchorLinkControl
 * $Id: AnchorLinkControl.java,v 1.2 2006/08/09 14:52:40 lordsam Exp $
 */
package de.jwic.controls;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.jwic.base.IControlContainer;
import de.jwic.ecolib.actions.Action;
import de.jwic.ecolib.actions.IAction;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * Displays an anchor (&lt;a href="..."&gt;) link.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class AnchorLinkControl extends SelectableControl {

	private static final long serialVersionUID = 1L;

	private String title = null;
	private String infoMessage = null;

	private IAction action;

	private final PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			requireRedraw();
		}
	};

	private final SelectionListener selectionListener = new SelectionListener() {

		@Override
		public void objectSelected(SelectionEvent event) {
			onClick(event);
		}

	};

	/**
	 * @param container
	 */
	public AnchorLinkControl(IControlContainer container) {
		super(container);
		title = getName();
	}

	/**
	 * @param container
	 * @param name
	 */
	public AnchorLinkControl(IControlContainer container, String name) {
		super(container, name);
		title = name;

	}

	/**
	 * 
	 * @param container
	 * @param name
	 * @param action
	 */
	public AnchorLinkControl(IControlContainer container, String name,
			IAction action) {
		this(container, name);
		this.setAction(action);

	}

	/**
	 * 
	 * @param container
	 * @param action
	 */
	public AnchorLinkControl(IControlContainer container, IAction action) {
		this(container, null, action);
	}

	protected void onClick(SelectionEvent event) {
		if (this.action != null) {
			action.run();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.base.Control#actionPerformed(java.lang.String,
	 * java.lang.String)
	 */
	public void actionPerformed(String actionId, String parameter) {
		click();
	}

	/**
	 * 
	 * @param action
	 *            the action to be set
	 */
	public void setAction(IAction action) {
		this.action = action;

		setTitle(action.getTitle());
		setEnabled(action.isEnabled());
		setVisible(action.isVisible());

		action.addPropertyChangeListener(this.propertyChangeListener);
		addSelectionListener(this.selectionListener);
	}

	/**
	 * 
	 * @return the action
	 */
	public IAction getAction() {
		return action;
	}

	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
		setRequireRedraw(true);
	}

	/**
	 * @return Returns the infoMessage.
	 */
	public String getInfoMessage() {
		return infoMessage;
	}

	/**
	 * This text is displayed in the infobar of the browser during mouseover and
	 * as the title of the anchor tag, wich results in a little popup info.
	 * 
	 * @param infoMessage
	 *            The infoMessage to set.
	 */
	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
		setRequireRedraw(true);
	}

}
