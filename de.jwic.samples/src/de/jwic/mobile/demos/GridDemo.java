package de.jwic.mobile.demos;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.mobile.MobileDemoModule;
import de.jwic.mobile.controls.mgrid.MGrid;
import de.jwic.mobile.controls.mgrid.MGrid.GridColumns;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 16.12.2015
 */
public class GridDemo extends MobileDemoModule {

	public GridDemo() {
		super("Grid Demo");
	}

	@Override
	public Control createPage(IControlContainer controlContainer) {
		final ControlContainer container = new ControlContainer(
				controlContainer);

		GridColumns columns = null;
		final MGrid mtable = new MGrid(container, "mGrid", columns );
		mtable.setText("grid example");

		return container;
	}
}
