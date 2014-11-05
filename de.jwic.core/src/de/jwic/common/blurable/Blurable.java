package de.jwic.common.blurable;

import de.jwic.base.IncludeJsOption;

/**
 * Created by boogie on 10/28/14.
 */
public interface Blurable {
	void blur();
	void addBlurListener(BlurListener listener);
	void removeBlurListener(BlurListener listener);


	@IncludeJsOption
	boolean isUpdateOnBlur();

	void setUpdateOnBlur(boolean updateOnBlur);
}
