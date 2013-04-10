package de.jwic.ecolib.controls.stackpanel;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;

/**
 * 
 * @author bogdan
 *
 */

@JavaScriptSupport
public class StackPanelControl extends ControlContainer{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param container
	 */
	public StackPanelControl(IControlContainer container) {
		super(container);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param container
	 * @param name
	 */
	public StackPanelControl(IControlContainer container, String name) {
		super(container, name);
		// TODO Auto-generated constructor stub
	}
	
	
	public Panel createPanel(){
		return new Panel(this);
	}
	public Panel createPanel(String panelTitle){
		Panel p = new Panel(this);
		p.setTitle(panelTitle);		
		return p;
	}
	
	
	

}
