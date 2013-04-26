package de.jwic.controls.accordion;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;

/**
 * 
 * @author dotto
 *
 */

@JavaScriptSupport
public class Accordion extends ControlContainer{
	private static final long serialVersionUID = 1L;
	private int activeIndex = 0;

	/**
	 * 
	 * @param container
	 */
	public Accordion(IControlContainer container) {
		super(container);
	}

	/**
	 * @param container
	 * @param name
	 */
	public Accordion(IControlContainer container, String name) {
		super(container, name);
	}
	
	
	public Panel createPanel(){
		return new Panel(this);
	}
	
	public Panel createPanel(String panelTitle){
		Panel p = new Panel(this);
		p.setTitle(panelTitle);		
		return p;
	}

	/**
	 * @return the activeIndex
	 */
	public int getActiveIndex() {
		return activeIndex;
	}

	/**
	 * @param activeIndex the activeIndex to set
	 */
	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
		getSessionContext().queueScriptCall("JWic.controls.Accordion.activate('" + getControlID() + "', " + getActiveIndex() +");");
	}
	
	
	@Override
	public void actionPerformed(String actionId, String parameter) {
		if("activeAccordion".equals(actionId)){
			activeIndex = Integer.parseInt(parameter);
		}
	}
}
