/**
 * 
 */
package de.jwic.controls.mobile;

import de.jwic.base.IControlContainer;
import de.jwic.controls.ToolBar;
import de.jwic.controls.ToolBarGroup;

/**
 * @author vedad
 *
 */
public class MToolbar extends ToolBar {

	private static final long serialVersionUID = -3158436517910509648L;

	/**
	 * @param container
	 */
	public MToolbar(IControlContainer container) {
		super(container);
	}

	/**
	 * @param container
	 * @param name
	 */
	public MToolbar(IControlContainer container, String name) {
		super(container, name);
	}

	/* (non-Javadoc)
	 * @see de.jwic.controls.ToolBar#addGroup()
	 */
	@Override
	public MToolBarGroup addGroup() {
		MToolBarGroup grp = new MToolBarGroup(this);
		grp.setCssClass("float:left;");
		return grp;
	}

	/* (non-Javadoc)
	 * @see de.jwic.controls.ToolBar#addRightGroup()
	 */
	@Override
	public MToolBarGroup addRightGroup() {
		MToolBarGroup grp = new MToolBarGroup(this);
		grp.setCssClass("float:right;");
		return grp;
	}
	
	

}
