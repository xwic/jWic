package de.jwic.controls.lazytooltip;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONWriter;

import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JWicException;
import de.jwic.base.JavaScriptSupport;
import de.jwic.json.JsonResourceControl;

/**
 * @author bogdan
 *
 */
@JavaScriptSupport
public class LazyTooltipControl extends JsonResourceControl{
	private final Map<String, ILazyTooltipProvider> tooltipMap;
	/**
	 * Constructor
	 * @param container
	 */
	public LazyTooltipControl(IControlContainer container){
		this(container,null);
	}

	
	/**
	 * Constructor with name
	 * @param container
	 * @param name
	 */
	public LazyTooltipControl(IControlContainer container, String name) {
		super(container, name);
		tooltipMap = new HashMap<String, ILazyTooltipProvider>();
	}

	/**
	 * Adds a tooltip Provider to this LazyTooltipControl.
	 * The id must be unique.
	 * @param id
	 * @param provider
	 */
	public void addLazyTooltipProvider(String id, ILazyTooltipProvider provider){
		if(this.tooltipMap.containsKey(id)){
			throw new JWicException("a label provider with this id already exists!");
		}
		this.tooltipMap.put(id, provider);
		this.requireRedraw();
	}
	
	/**
	 * 
	 * @param id
	 */
	public void removeLazyTooltipProvider(String id){
		this.tooltipMap.remove(id);
		this.requireRedraw();
	}
	

	@Override
	public void handleJSONResponse(HttpServletRequest req, JSONWriter jsonOut) throws JSONException {
		String id = req.getParameter("tooltipProviderId");
		String params = req.getParameter("tooltipParams");
		String delay = req.getParameter("tooltipDelay");
		ILazyTooltipProvider provider = this.tooltipMap.get(id);
		if(provider != null){
			ILazyTooltip tooltip = provider.getTooltip(params);
			jsonOut.object()
				.key("providerClass").value(tooltip.getJSLabelProviderClass())
				.key("tooltipDelay").value(delay)
				.key("data").value(tooltip.getData())
				.key("currentControlId").value(this.getControlID())
				.endObject();
		}
	}
	
	/**
	 * Used in companion javascript
	 * @return a String of comma separated names of providers
	 */
	@IncludeJsOption
	public String getProviders(){
		return StringUtils.join(this.tooltipMap.keySet().toArray(),",");
	}
	
}
