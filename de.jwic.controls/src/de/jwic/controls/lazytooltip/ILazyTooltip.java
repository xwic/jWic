package de.jwic.controls.lazytooltip;

import org.json.JSONException;
import org.json.JSONObject;

public interface ILazyTooltip {
	public String getJSLabelProviderClass();
	public JSONObject getData() throws JSONException;
}
