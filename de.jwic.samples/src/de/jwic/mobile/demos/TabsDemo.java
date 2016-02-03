/**
 * 
 */
package de.jwic.mobile.demos;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.LabelControl;
import de.jwic.controls.Tab;
import de.jwic.controls.mobile.MTabStrip;
import de.jwic.mobile.MobileDemoModule;

/**
 * @author vedad
 *
 */
public final class TabsDemo extends MobileDemoModule {

	public TabsDemo() {
		super("Tabs Demo");
	}

	/* (non-Javadoc)
	 * @see de.jwic.mobile.MobileDemoModule#createPage(de.jwic.base.IControlContainer)
	 */
	@Override
	public Control createPage(IControlContainer controlContainer) {
		final ControlContainer container = new ControlContainer(controlContainer, "controlContainer");
		
		final MTabStrip tabstrip = new MTabStrip(container, "tabstrip");

		Tab firsttab = tabstrip.addTab("First Tab", "firsttab");
		LabelControl lbl1 = new LabelControl(firsttab);
		lbl1.setText("This is the first tab text and some other random text just to test auto growth.");

		Tab secondtab = tabstrip.addTab("Second Tab", "secondtab");
		LabelControl lbl2 = new LabelControl(secondtab);
		lbl2.setText("This is second tab text.");

		Tab thirdtab = tabstrip.addTab("Third Tab", "thirdtab");
		LabelControl lbl3 = new LabelControl(thirdtab);
		lbl3.setText("Another random text for the third tab. "
				+ "This tab is set as active");
		
		tabstrip.setActiveTabName("thirdtab");
		
		return container;
	}

}
