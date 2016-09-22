/**
 * 
 */
package de.jwic.controls.mobile;

import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.ToolBarGroup;

/**
 * @author vedad
 *
 */
public class MToolBarGroup extends ToolBarGroup {

	private static final long serialVersionUID = 1L;

	/**
	 * @param container
	 */
	public MToolBarGroup(IControlContainer container) {
		super(container);
	}

	/**
	 * @param container
	 * @param name
	 */
	public MToolBarGroup(IControlContainer container, String name) {
		super(container, name);
	}

	/* (non-Javadoc)
	 * @see de.jwic.controls.ToolBarGroup#addButton()
	 */
	@Override
	public MButton addButton() {
		MButton button = new MButton(this, null);
		return button;
	}
	
	

}
