/*******************************************************************************
 * Copyright 2015 xWic group (http://www.xwic.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *  
 *******************************************************************************/
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
	 * Position to place tooltip, below hover element by default.
	 */
	private TooltipPosition position = TooltipPosition.AUTO;
	
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
	
	/**
	 * Returns the position of the LazyTooltipControl.
	 * @return
	 */
	@IncludeJsOption
	public TooltipPosition getPosition() {
		return position;
	}

	/**
	 * 
	 * @param position
	 */
	public void setPosition(TooltipPosition position) {
		this.position = position;
	}

	/**
	 * Definition of possible LazyTooltipControl positions.
	 * @author emir
	 *
	 */
	public enum TooltipPosition {
		/**
		 * Default position.
		 */
		AUTO("auto"),
		/**
		 * Position below the hover element.
		 */
		BELOW("below"),
		/**
		 * Position over the hover element.
		 */
		OVER("over"),
		/**
		 * Position above the hover element.
		 */
		ABOVE("above");
		/**
		 * String position value.
		 */
		private final String code;

		private TooltipPosition(String c) {
			code = c;
		}

		/**
		 * 
		 * @return code
		 */
		public String getCode() {
			return code;
		}

		@Override
		public String toString() {
			return getCode();
		}
	}

}
