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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * IResourceControl prvides access to HttpServletResponse so download or image
 * functionallity can be provided by a control.
 * The resource URL must contain "_resreq=1" and its control id "controlId=Control.getControlId()"
 * 
 * @author Bornemann
 */
public interface IResourceControl {

	public final static String URL_RESOURCE_PARAM = "_resreq";
	public final static String URL_CONTROLID_PARAM = "controlId";

	/**
	 * Attach the resource to the HttpServletResponse.
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	public void attachResource(HttpServletRequest req, HttpServletResponse res) throws IOException;
}
