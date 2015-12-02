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
package de.jwic.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The render context contains the request and response objects required by 
 * renderer implementations to render a control. The context is given from one
 * renderer to the other.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class RenderContext {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private PrintWriter writer;
	
	private Map<String, String> scripts = new LinkedHashMap<String, String>();
	private Set<String> requiredStaticJs = new HashSet<String>();
	
	/**
	 * Constructs a new RenderContext.
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public RenderContext(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.request = request;
		this.response = response;
		writer = response.getWriter();
	}

	/**
	 * Constructs a new RenderContext, using a custom writer.
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public RenderContext(HttpServletRequest request, HttpServletResponse response, PrintWriter writer) {
		this.request = request;
		this.response = response;
		this.writer = writer;
	}

	
	/**
	 * @return Returns the request.
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * @return Returns the response.
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * Returns the writer.
	 * @return
	 */
	public PrintWriter getWriter() {
		return writer;
	}
	
	/**
	 * Add a script to the update.
	 * @param controlId
	 * @param script
	 * @param staticName 
	 */
	public void addScript(String controlId, String script) {
		if (script != null && script.trim().length() > 0) {
			scripts.put(controlId, script);
		}
	}
	
	/**
	 * Add a library that needs to be loaded at least once.
	 * @param name
	 */
	public void addRequiredJSContent(String name) {
		requiredStaticJs.add(name);
	}
	
	/**
	 * Returns the script map.
	 * @return
	 */
	public Map<String, String> getScripts() {
		return scripts;
	}
	/**
	 * @return the requiredStaticJs
	 */
	public Set<String> getRequiredStaticJs() {
		return requiredStaticJs;
	}

}
