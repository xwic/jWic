package de.jwic.mobile.controls.mlistview;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.common.properties.WithTextProperty;
import de.jwic.mobile.controls.mgrid.MGrid;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 22.12.2015
 */
public class MListView<T extends Object> extends ControlContainer implements WithTextProperty {

	private String text;

	private MListViewDataModel<T> model;

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

	public MListViewDataModel<T> getModel() {
		return model;
	}

	public void setModel(MListViewDataModel<T> model) {
		this.model = model;
	}

	public String render(ListViewElement<T> element) {
		return model.render(element);
	}

}
