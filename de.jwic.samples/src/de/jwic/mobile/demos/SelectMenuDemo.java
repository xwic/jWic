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

	private static final long serialVersionUID = 1L;

	private MSelectmenu selectmenuinline;

	/**
	 * @param title
	 */
	public SelectMenuDemo() {
		super("SelectMenu Demo");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.mobile.MobileDemoModule#createPage(de.jwic.base.
	 * IControlContainer)
	 */
	@Override
	public Control createPage(IControlContainer controlContainer) {
		final ControlContainer container = new ControlContainer(controlContainer, "controlContainer");

		MSelectmenu selectmenu = new MSelectmenu(container, "selectmenu");
		selectmenu.setCorners(false);
		selectmenu.setMultiSelect(true);
		selectmenu.addElement("First", "firstitem");
		selectmenu.addElement("Second", "seconditem");
		selectmenu.addElement("Third", "thirditem");

		selectmenuinline = new MSelectmenu(container, "selectmenuinline");
		selectmenuinline.setInline(true);
		selectmenuinline.addElement("First Inline", "firstiteminline");
		selectmenuinline.addElement("Second Inline", "seconditeminline");
		ISelectElement selectedElm = selectmenuinline.addElement("Third Inline", "thirditeminline");
		selectmenuinline.addElement("Fourth Inline", "fourthiteminline");
		selectmenuinline.setSelectedElement(selectedElm);

		MSelectmenu selectmenumini = new MSelectmenu(container, "selectmenumini");
		selectmenumini.setMini(true);
		selectmenumini.setEmptyInfoText("Choose option");
		selectmenumini.addElement("First Mini", "firstitemmini");
		selectmenumini.addElement("Second Mini", "seconditemmini");
		selectmenumini.addElement("Third Mini", "thirditemmini");

		return container;
	}

}
