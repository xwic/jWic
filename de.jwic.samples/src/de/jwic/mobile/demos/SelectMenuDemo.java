package de.jwic.mobile.demos;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.mobile.MSelectmenu;
import de.jwic.data.ISelectElement;
import de.jwic.mobile.MobileDemoModule;

/**
 * @author vedad
 *
 */
public final class SelectMenuDemo extends MobileDemoModule {

	/**
	 * @param title
	 */
	public SelectMenuDemo() {
		super("SelectMenu Demo");
	}

	/* (non-Javadoc)
	 * @see de.jwic.mobile.MobileDemoModule#createPage(de.jwic.base.IControlContainer)
	 */
	@Override
	public Control createPage(IControlContainer controlContainer) {
		final ControlContainer container = new ControlContainer(controlContainer, "controlContainer");
		
		MSelectmenu selectmenu = new MSelectmenu(container, "selectmenu");
		selectmenu.setCorners(false);
		ISelectElement firstone = selectmenu.addElement("firstitem", "First");
		selectmenu.addElement("seconditem", "Second");
		selectmenu.addElement("thirditem", "Third");
		selectmenu.setSelectedElement(firstone);
		
		MSelectmenu selectmenuinline = new MSelectmenu(container, "selectmenuinline");
		selectmenuinline.setInline(true);
		selectmenuinline.addElement("firstiteminline", "First Inline");
		selectmenuinline.addElement("seconditeminline", "Second Inline");
		selectmenuinline.addElement("thirditeminline", "Third Inline");
		selectmenuinline.addElement("fourthiteminline", "Fourth Inline");
		
		MSelectmenu selectmenumini = new MSelectmenu(container, "selectmenumini");
		selectmenumini.setMini(true);
		selectmenumini.addElement("firstitemmini", "First Mini");
		selectmenumini.addElement("seconditemmini", "Second Mini");
		selectmenumini.addElement("thirditemmini", "Third Mini");
		
		return container;
	}

}
