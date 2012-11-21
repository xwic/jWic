/*
 * de.jwic.controls.ButtonControl
 * $Id: Button.java,v 1.3 2011/09/09 15:09:01 adrianionescu12 Exp $
 */
package de.jwic.controls;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.jwic.base.IControlContainer;
import de.jwic.base.ImageRef;
import de.jwic.base.JavaScriptSupport;
import de.jwic.ecolib.actions.IAction;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * Represents the &lt;button&gt; html element. A button displays text and/or an
 * image.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
@JavaScriptSupport
public class Button extends SelectableControl {

	private static final long serialVersionUID = 1L;

	private String title = null;
	private ImageRef iconEnabled = null;
	private ImageRef iconDisabled = null;
	private boolean submitButton = false;
	private String tooltip = null;
	private String confirmMsg = "";
	private IAction action;

	private PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {
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
	public Button(IControlContainer container) {
		super(container);
		init();
	}

	/**
	 * @param container
	 * @param name
	 */
	public Button(IControlContainer container, String name) {
		super(container, name);
		init();
	}

	public Button(IControlContainer container, String name, IAction action) {
		this(container, name);
		this.setAction(action);
	}

	public Button(IControlContainer container, IAction action) {
		this(container, null, action);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.base.Control#init()
	 */
	private void init() {
		setCssClass("j-button");
		if (title == null) {
			title = getName();
		}
	}

	/**
	 * 
	 * @param event
	 */
	protected void onClick(SelectionEvent event) {
		if (action != null) {
			this.action.run();
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
	 * Returns the icon for the button depending on the state.
	 * 
	 * @return
	 */
	public ImageRef getIcon() {
		if (!isEnabled()) {
			return iconDisabled != null ? iconDisabled : iconEnabled;
		} else {
			return iconEnabled;
		}
	}

	/**
	 * Returns true if an icon is specified.
	 * 
	 * @return
	 */
	public boolean hasIcon() {
		return iconEnabled != null;
	}

	/**
	 * Returns true if the button should behave like a submit button.
	 * 
	 * @return boolean
	 */
	public boolean isSubmitButton() {
		return submitButton;
	}

	/**
	 * Set to true if the button should be of the type submit.
	 * 
	 * @param submitButton
	 *            The submitButton to set.
	 */
	public void setSubmitButton(boolean submitButton) {
		this.submitButton = submitButton;
		requireRedraw();
	}

	/**
	 * @return Returns the tooltip.
	 */
	public String getTooltip() {
		return tooltip;
	}

	/**
	 * @param tooltip
	 *            The tooltip to set.
	 */
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
		requireRedraw();
	}

	/**
	 * @return Returns the confirmMsg.
	 */
	public String getConfirmMsg() {
		return confirmMsg;
	}

	/**
	 * @param confirmMsg
	 *            The confirmMsg to set.
	 */
	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
		requireRedraw();
	}

	/**
	 * @return the iconDisabled
	 */
	public ImageRef getIconDisabled() {
		return iconDisabled;
	}

	/**
	 * @param iconDisabled
	 *            the iconDisabled to set
	 */
	public void setIconDisabled(ImageRef iconDisabled) {
		this.iconDisabled = iconDisabled;
		requireRedraw();
	}

	/**
	 * @return the iconEnabled
	 */
	public ImageRef getIconEnabled() {
		return iconEnabled;
	}

	/**
	 * @param iconEnabled
	 *            the iconEnabled to set
	 */
	public void setIconEnabled(ImageRef iconEnabled) {
		this.iconEnabled = iconEnabled;
		requireRedraw();
	}

	/**
	 * 
	 * @param action
	 *            the action to be set
	 */
	public void setAction(IAction action) {
		this.action = action;
		setTitle(action.getTitle());
		setIconEnabled(action.getIconEnabled());
		setIconDisabled(action.getIconDisabled());
		setEnabled(action.isEnabled());
		setVisible(action.isVisible());
		setTooltip(action.getTooltip());

		action.addPropertyChangeListener(this.propertyChangeListener);
		addSelectionListener(selectionListener);
	}

	/**
	 * 
	 * @return the action
	 */
	public IAction getAction() {
		return action;
	}

	@Override
	public void click() {
		super.click();
		if (action != null) {
			action.run();
		}
	}
}
