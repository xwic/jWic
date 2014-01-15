package de.jwic.controls.actions;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.jwic.base.IControlContainer;
import de.jwic.controls.AnchorLink;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
/**
 * An AnchorLinkControl that offers support for Action
 * @author bogdan
 *
 */
public class ActionAnchorLink extends AnchorLink{
	private static final long serialVersionUID = 1L;

	private final IAction action;
	
	public ActionAnchorLink(IControlContainer container,IAction action) {
		super(container);
		this.action = action;
		setTemplateName(AnchorLink.class.getName());
		
		init();
	}



	public ActionAnchorLink(IControlContainer container, String name,IAction action) {
		super(container, name);
		this.action = action;
		setTemplateName(AnchorLink.class.getName());
		
		init();
	}



	private void init() {
		setTitle(action.getTitle());
		setEnabled(action.isEnabled());
		setVisible(action.isVisible());
		
		action.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				requireRedraw();
			}
		});
		
		addSelectionListener(new SelectionListener() {
			private static final long serialVersionUID = 1L;
			public void objectSelected(SelectionEvent event) {
				onClick(event);
			}			
		});
	}



	protected void onClick(SelectionEvent event) {
		action.run();
	}
	
	/**
	 * 
	 * @return
	 */
	public IAction getAction() {
		return action;
	}



	/**
	 * @return
	 * @see de.jwic.controls.actions.IAction#getTitle()
	 */
	public String getTitle() {
		return action.getTitle();
	}



	/**
	 * @return
	 * @see de.jwic.controls.actions.IAction#isEnabled()
	 */
	public boolean isEnabled() {
		return action.isEnabled();
	}



	/**
	 * @return
	 * @see de.jwic.controls.actions.IAction#isVisible()
	 */
	public boolean isVisible() {
		return action.isVisible();
	}
	
	
	

}
