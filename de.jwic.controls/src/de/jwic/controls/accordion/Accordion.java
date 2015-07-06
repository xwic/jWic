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
package de.jwic.controls.accordion;

import java.util.HashMap;
import java.util.Map;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.Easing;

/**
 * 
 * @author dotto
 *
 */

@JavaScriptSupport
public class Accordion extends ControlContainer{
	private static final long serialVersionUID = 1L;
	private int activeIndex = 0;
	private boolean disabled = false;
	private Easing animate;
	private boolean collapsible;
	private String headerIconCls;
	private String activeHeaderIconCls;
	private HeightStyle heightStyle;

	/**
	 * 
	 * @param container
	 */
	public Accordion(IControlContainer container) {
		super(container);
	}

	/**
	 * @param container
	 * @param name
	 */
	public Accordion(IControlContainer container, String name) {
		super(container, name);
	}
	
	/**
	 * 
	 * @return
	 */
	public Panel createPanel(){
		return createPanel("");
	}
	
	/**
	 * 
	 * @param panelTitle
	 * @return
	 */
	public Panel createPanel(String panelTitle){
		Panel p = new Panel(this);
		p.setTitle(panelTitle);		
		requireRedraw();
		return p;
	}

	/**
	 * @return the activeIndex
	 */
	public int getActiveIndex() {
		return activeIndex;
	}
	
	/**
	 * Property for setting Active Page on Accordion
	 * @return the activeIndex
	 */
	@IncludeJsOption
	public int getActive() {
		return activeIndex;
	}

	/**
	 * @param activeIndex the activeIndex to set
	 */
	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
		getSessionContext().queueScriptCall("JWic.controls.Accordion.activate('" + getControlID() + "', " + getActiveIndex() +");");
	}
	
	
	@Override
	public void actionPerformed(String actionId, String parameter) {
		if("activeAccordion".equals(actionId)){
			activeIndex = Integer.parseInt(parameter);
		}
	}

	/**
	 * @return the disabled
	 */
	@IncludeJsOption
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
		getSessionContext().queueScriptCall("JWic.controls.Accordion.disabled('" + getControlID() + "', " + isDisabled() +");");
	}

	/**
	 * @return the animate
	 */
	@IncludeJsOption
	public Easing getAnimate() {
		return animate;
	}

	/**
	 * @param animate the animate to set
	 */
	public void setAnimate(Easing animate) {
		this.animate = animate;
		requireRedraw();
	}

	/**
	 * @return the collapsible
	 */
	@IncludeJsOption
	public boolean isCollapsible() {
		return collapsible;
	}

	/**
	 * @param collapsible the collapsible to set
	 */
	public void setCollapsible(boolean collapsible) {
		this.collapsible = collapsible;
		requireRedraw();
	}

	/**
	 * @return the headerIconCls
	 */
	public String getHeaderIconCls() {
		return headerIconCls;
	}

	/**
	 * @param headerIconCls the headerIconCls to set
	 */
	public void setHeaderIconCls(String headerIconCls) {
		this.headerIconCls = headerIconCls;
		requireRedraw();
	}
	
	/**
	 * 
	 * @return
	 */
	@IncludeJsOption
	public Map<String, String> getIcons(){
		if(getHeaderIconCls() == null && getActiveHeaderIconCls() == null)
			return null;
		Map<String, String> result = new HashMap<String, String>();
		if(getHeaderIconCls() != null){
			result.put("header", getHeaderIconCls());
		}
		if(getActiveHeaderIconCls() != null){
			result.put("activeHeader", getActiveHeaderIconCls());
		}
		
		return result;
	}

	/**
	 * @return the activeHeaderIconCls
	 */
	public String getActiveHeaderIconCls() {
		return activeHeaderIconCls;
	}

	/**
	 * @param activeHeaderIconCls the activeHeaderIconCls to set
	 */
	public void setActiveHeaderIconCls(String activeHeaderIconCls) {
		this.activeHeaderIconCls = activeHeaderIconCls;
		requireRedraw();
	}

	/**
	 * @return the heightStyle
	 */
	@IncludeJsOption
	public HeightStyle getHeightStyle() {
		return heightStyle;
	}

	/**
	 * @param heightStyle the heightStyle to set
	 */
	public void setHeightStyle(HeightStyle heightStyle) {
		this.heightStyle = heightStyle;
		requireRedraw();
	}

	/**
	 * Controls the height of the accordion and each panel. Possible values:
	 * "auto": All panels will be set to the height of the tallest panel.
	 * "fill": Expand to the available height based on the accordion's parent height.
	 * "content": Each panel will be only as tall as its content.
	 * @author dotto
	 *
	 */
	public enum HeightStyle {
		AUTO("auto"), FILL("fill"), CONTENT("content");
		
		private String code;

		private HeightStyle(String c) {
			code = c;
		}

		public String getCode() {
			return code;
		}

		@Override
		public String toString() {
			return getCode();
		}
	}
}
