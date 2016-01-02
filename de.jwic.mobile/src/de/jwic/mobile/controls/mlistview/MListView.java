package de.jwic.mobile.controls.mlistview;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.common.properties.WithTextProperty;
import de.jwic.mobile.controls.mgrid.MGrid;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 02.01.2016
 */
public class MListView extends ControlContainer implements WithTextProperty {

	private String text;

	public MListView(IControlContainer container) {
		this(container, null);
	}

	public MListView(IControlContainer container, String name) {
		super(container, name);
		this.setTemplateName(MListView.class.getName());

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
