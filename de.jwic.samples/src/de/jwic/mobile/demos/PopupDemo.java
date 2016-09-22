/**
 * 
 */
package de.jwic.mobile.demos;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.mobile.MPopup;
import de.jwic.mobile.MobileDemoModule;

/**
 * @author vedad
 *
 */
public class PopupDemo extends MobileDemoModule {

	private static final long serialVersionUID = 1L;

	/**
	 * @param title
	 */
	public PopupDemo() {
		super("Popup Demo");
	}

	/* (non-Javadoc)
	 * @see de.jwic.mobile.MobileDemoModule#createPage(de.jwic.base.IControlContainer)
	 */
	@Override
	public Control createPage(IControlContainer controlContainer) {
		final ControlContainer container = new ControlContainer(controlContainer, "popupContainer");
		
		final MPopup popup = new MPopup(container, "mpopup");
		popup.setBtnTitle("Open Popup");
		
		return container;
	}

}
