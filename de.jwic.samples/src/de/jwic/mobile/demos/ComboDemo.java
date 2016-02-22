/**
 * 
 */
package de.jwic.mobile.demos;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.mobile.MCombo;
import de.jwic.mobile.MobileDemoModule;

/**
 * @author vedad
 *
 */
public final class ComboDemo extends MobileDemoModule {

	public ComboDemo() {
		super("Combo Demo");
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.mobile.MobileDemoModule#createPage(de.jwic.base.IControlContainer)
	 */
	@Override
	public Control createPage(IControlContainer controlContainer) {
		final ControlContainer container = new ControlContainer(controlContainer, "controlContainer");
		
		MCombo firstcombo = new MCombo(container, "firstcombo");
		firstcombo.setFilterReveal(true);
		firstcombo.setFilterPlaceholder("Enter City Name");
		firstcombo.setRemoteDataURL("http://gd.geobytes.com/AutoCompleteCity");
		
		return container;
	}

}
