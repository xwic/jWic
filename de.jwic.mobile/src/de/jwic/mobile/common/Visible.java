package de.jwic.mobile.common;

import de.jwic.base.IncludeJsOption;

/**
 * Created by boogie on 10/28/14.
 */
public interface Visible {
	void show();
	void hide();

	@IncludeJsOption
	boolean isVisible();
	void setVisible(boolean visible);
}
