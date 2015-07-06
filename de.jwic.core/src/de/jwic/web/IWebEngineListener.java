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
package de.jwic.web;

/**
 * This listener is invoked for certain engine events such as action invocation or rendering. 
 * Useful for tracing an application.
 * 
 * NOTE that the event object between the PRE and POST invocation remains the same object. It can be used to 
 * attach objects that may be used in the post rendering call.
 * 
 * @author lippisch
 */
public interface IWebEngineListener {

	/**
	 * This event is invoked before the rendering process starts.
	 * @param event
	 */
	public void preRendering(WebEngineEvent event);
	
	/**
	 * This event is invoked after the rendering process is completed.
	 * @param event
	 */
	public void postRendering(WebEngineEvent event);
	
	/**
	 * This event is invoked before a single control is rendered.
	 * @param event
	 */
	public void preControlRendering(WebEngineEvent event);
	
	/**
	 * This event is invoked after a single control was rendered.
	 * @param event
	 */
	public void postControlRendering(WebEngineEvent event);
		
	/**
	 * This event is invoked before an action is handled. 
	 * @param event
	 */
	public void preHandleAction(WebEngineEvent event);
	
	/**
	 * This event is invoked after an action was handled.
	 * @param event
	 */
	public void postHandleAction(WebEngineEvent event);
	
	/**
	 * This event is invoked before a ResourceRequest is delegated to a control. 
	 * @param event
	 */
	public void preResourceRequest(WebEngineEvent event);
	
	/**
	 * This event is invoked after a ResourceRequest was completed.
	 * @param event
	 */
	public void postResourceRequest(WebEngineEvent event);

}
