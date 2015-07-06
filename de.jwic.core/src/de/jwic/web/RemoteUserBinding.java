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
/*
 * de.jwic.web.RemoteUserBinding
 * Created on 22.12.2004
 * $Id: RemoteUserBinding.java,v 1.3 2008/09/17 15:19:38 lordsam Exp $
 */
package de.jwic.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Stores the HttpServletRequest object in a ThreadLocal object to
 * allow jWic applications to gather information about the current user.
 * This class must be registerd as ServletInterceptor to the
 * DispatcherServlet.
 * 
 *  <init-param>
 *     <param-name>servlet.interceptors</param-name>
 *     <param-value>de.jwic.web.RemoteUserBinding</param-value>
 *   </init-param>
 * 
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class RemoteUserBinding implements ServletInterceptor {

	private static ThreadLocal<HttpServletRequest> locale = new ThreadLocal<HttpServletRequest>();
	
	/**
	 * Returns the remote user.
	 * @return
	 */
	public static String getRemoteUser() {
		HttpServletRequest request = locale.get();
		if (request != null) {
			return request.getRemoteUser();
		} 
		return null;
	}

	/**
	 * Returns the result of HttpServletRequest.isUserInRole(role).
	 * @param role
	 * @return
	 */
	public static boolean isUserInRole(String role) {
		HttpServletRequest request = locale.get();
		if (request != null) {
			return request.isUserInRole(role);
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.web.ServletInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void preHandle(HttpServletRequest request, HttpServletResponse resp) {
		
		locale.set(request);

	}

	/* (non-Javadoc)
	 * @see de.jwic.web.ServletInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse resp) {
		
		locale.set(null);	// clear reference

	}

}
