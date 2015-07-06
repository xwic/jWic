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
package de.jwic.json;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONWriter;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.IResourceControl;

/**
 * Base class for controls that use JSON communication.
 * @author lippisch
 */
public abstract class JsonResourceControl extends Control implements
		IResourceControl {
	private static final long serialVersionUID = 1L;
	/**
	 * @param container
	 * @param name
	 */
	public JsonResourceControl(IControlContainer container, String name) {
		super(container, name);
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IResourceControl#attachResource(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void attachResource(HttpServletRequest req, HttpServletResponse res) throws IOException {

		res.setContentType("text/json; charset=UTF-8");
		PrintWriter pw;
		try {
			pw = res.getWriter();
		} catch (Exception e) {
			log.error("Error getting writer!");
			return;
		}
		JSONWriter jsonOut = new JSONWriter(pw);
		try {
			handleJSONResponse(req, jsonOut);
		} catch (JSONException e) {
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
			log.error("Error generating JSON response", e);
		}
		pw.close();

	}

	/**
	 * Implement this method to send a JSON response.
	 * @param req
	 * @param jsonOut
	 * @throws JSONException 
	 */
	public abstract void handleJSONResponse(HttpServletRequest req, JSONWriter jsonOut) throws JSONException;
	
}
