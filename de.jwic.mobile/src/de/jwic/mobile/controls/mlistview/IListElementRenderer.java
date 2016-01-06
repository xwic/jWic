package de.jwic.mobile.controls.mlistview;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 22.12.2015
 */
public interface IListElementRenderer<T extends Object> {

	public String render(T element);
}
