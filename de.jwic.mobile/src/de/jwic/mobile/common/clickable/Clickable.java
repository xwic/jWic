package de.jwic.mobile.common.clickable;

/**
 * Created by boogie on 10/28/14.
 */
public interface Clickable{
	void click();
	void addClickListener(ClickListener listener);
	void removeClickListener(ClickListener listener);
}
