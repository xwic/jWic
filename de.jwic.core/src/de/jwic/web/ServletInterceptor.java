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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementors of this Interface are invoked before and after
 * the DispatcherServlet has handled a request. 
 * 
 * @version $Revision: 1.1 $
 * @author Florian Lippisch
 */
public interface ServletInterceptor {

	/**
	 * This method is invoked by the DispatcherServlet before the 
	 * request is handled.
	 * @param req
	 * @param request
	 * @param resp
	 */
	void preHandle(HttpServletRequest request, HttpServletResponse resp);	
	/**
	 * This method is invoked by the DispatcherServlet after the request
	 * was handled and the data has been sent to the client.
	 * @param req
	 * @param request
	 * @param resp
	 */
	void postHandle(HttpServletRequest request, HttpServletResponse resp);
	
}
