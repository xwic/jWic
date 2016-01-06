package de.jwic.mobile.controls.mgrid;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.IOuterLayout;
import de.jwic.common.properties.WithTextProperty;
import de.jwic.mobile.controls.mtable.MColumn;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 15.12.2015
 */
public class MGrid extends ControlContainer implements WithTextProperty {

	private String text;
	private MGridDataModel model;
	private String gridClass;

	public enum GridColumns {
		ONE("solo"), TWO("a"), THREE("b"), FOUR("c"), FIVE("d"), SIX("e"), SEVEN("f");

		GridColumns(String type) {
			this.type = type;
		}

		private String type;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

	}

	public MGrid(IControlContainer container, GridColumns columns) {
		this(container, null, columns);
	}

	public MGrid(IControlContainer container, String name, GridColumns columns) {
		super(container, name);
		this.setTemplateName(MGrid.class.getName());
		gridClass = "ui-grid-" + columns.getType();

	}

	@Override
	public void setText(String title) {
		this.text = title;

	}

	@Override
	public String getText() {
		return text;
	}

	public MGridDataModel getModel() {
		return model;
	}

	public void setModel(MGridDataModel model) {
		this.model = model;
	}

	public String getGridClass() {
		return gridClass;
	}

	public void setGridClass(String gridClass) {
		this.gridClass = gridClass;
	}

	public String render(MColumn name, Object row) {
		return model.getCellRenderer().renderCell();
	}

}
