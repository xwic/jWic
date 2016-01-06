package de.jwic.mobile.controls.mlistview;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 22.12.2015
 */
public  class ListViewElement<T extends Object> {

	public String textForFilter;
	public T element;

	public ListViewElement(T element, String textForFilter) {
		this.element = element;
		this.textForFilter = textForFilter;
	}

	public ListViewElement(T element) {
		this(element, null);
	}

	public String getTextForFilter() {
		return textForFilter;
	}

	public void addTextForFilter(String textForFilter) {
		if (this.textForFilter != null) {
			this.textForFilter = this.textForFilter + ":" + textForFilter;
		} else {
			this.textForFilter = textForFilter;
		}

	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

}
