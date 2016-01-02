package de.jwic.mobile.controls.mgrid;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.IOuterLayout;
import de.jwic.common.properties.WithTextProperty;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 15.12.2015
 */
public class MGrid extends ControlContainer implements WithTextProperty {

	private String text;

	public MGrid(IControlContainer container) {
		this(container, null);
	}

	public MGrid(IControlContainer container, String name) {
		super(container, name);
		this.setTemplateName(MGrid.class.getName());

	}

	@Override
	public void setText(String title) {
		this.text = title;

	}

	@Override
	public String getText() {
		return text;
	}

}
