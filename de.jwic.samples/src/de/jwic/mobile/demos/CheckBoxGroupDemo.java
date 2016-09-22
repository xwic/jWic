/**
 * 
 */
package de.jwic.mobile.demos;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.mobile.MCheckBoxGroup;
import de.jwic.mobile.MobileDemoModule;

/**
 * @author vedad
 *
 */
public class CheckBoxGroupDemo extends MobileDemoModule {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public CheckBoxGroupDemo() {
		super("CheckBoxGroup Demo");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.mobile.MobileDemoModule#createPage(de.jwic.base.
	 * IControlContainer)
	 */
	@Override
	public Control createPage(IControlContainer controlContainer) {
		final ControlContainer container = new ControlContainer(controlContainer, "chkboxgrpContainer");
		
		MCheckBoxGroup chkboxgroup1 = new MCheckBoxGroup(container, "chkboxgroup1");
		chkboxgroup1.addElement("Element One", "elm1");
		chkboxgroup1.addElement("Element Two", "elm2");
		chkboxgroup1.addElement("Element Three", "elm3");
		chkboxgroup1.setCorners(false);

		return container;
	}

}
