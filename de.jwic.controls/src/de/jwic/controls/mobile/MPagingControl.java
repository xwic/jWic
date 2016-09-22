/**
 * 
 */
package de.jwic.controls.mobile;

import de.jwic.base.IControlContainer;
import de.jwic.controls.tableviewer.PagingControl;
import de.jwic.controls.tableviewer.TableModel;

/**
 * @author vedad
 *
 */
public class MPagingControl extends PagingControl {

	private static final long serialVersionUID = 8601328643673158600L;

	/**
	 * @param container
	 * @param name
	 * @param model
	 */
	public MPagingControl(IControlContainer container, String name, TableModel model) {
		super(container, name, model);
	}

}
