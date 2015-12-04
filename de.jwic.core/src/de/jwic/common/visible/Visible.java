package de.jwic.common.visible;

import de.jwic.base.IncludeJsOption;

/**
 * Should be implemented by any control that can be shown or hidden.
 *
 * Created by boogie on 10/28/14.
 */
public interface Visible {
	void show();
	void hide();

	@IncludeJsOption
	boolean isVisible();
	void setVisible(boolean visible);
}
