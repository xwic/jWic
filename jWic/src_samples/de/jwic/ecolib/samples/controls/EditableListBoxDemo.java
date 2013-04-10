package de.jwic.ecolib.samples.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.ecolib.controls.editablelistbox.EditableListBoxControl;

/**
 *  Created on 26.09.2006
 * @author Mark Frewin
 */
public class EditableListBoxDemo extends ControlContainer {

	/**
	 * 
	 * @param container
	 */
	public EditableListBoxDemo(IControlContainer container) {
		this(container, null);
	}

	/**
	 * 
	 * @param container
	 * @param name
	 */
	public EditableListBoxDemo(IControlContainer container, String name) {
		super(container, name);
		initialise();
	}
	
	/**
	 * Initialise
	 *
	 */
	private void initialise() {
		EditableListBoxControl elb = new EditableListBoxControl(this);
		elb.addElement("check this out");
		elb.addElement("funk soul brother");
		elb.addElement("G-funk");
		elb.addElement("homie");
		elb.addElement("pimp my ride");
		
	}

}
