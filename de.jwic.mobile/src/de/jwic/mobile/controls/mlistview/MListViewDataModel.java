package de.jwic.mobile.controls.mlistview;

import java.util.List;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 22.12.2015
 */
public class MListViewDataModel<T extends Object> {

	public IListElementRenderer<T> renderer;

	private List<ListViewElement<T>> elements;

	public MListViewDataModel(IListElementRenderer<T> renderer) {
		this.renderer = renderer;
	}

	public List<ListViewElement<T>> getElements() {
		return elements;
	}

	public void setElements(List<ListViewElement<T>> elements) {
		this.elements = elements;
	}

	public String render(ListViewElement<T> element) {
		return renderer.render(element.getElement());
	}

}
