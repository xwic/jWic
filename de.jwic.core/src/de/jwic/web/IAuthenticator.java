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
 * Created on 16.11.2004
 */
package de.jwic.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


/**
 * Authentication.
 * 
 * $Id: IAuthenticator.java,v 1.1 2006/01/16 08:31:13 lordsam Exp $
 * @version $Revision: 1.1 $
 * @author JBornemann
 */
public interface IAuthenticator {

	public final static String SESSION_REDIRECT_URL = "auth_redirect_url";
	
	/**
	 * Returns true if the remote user of the request is authenticated.
	 * @param req
	 * @param res
	 */
	public abstract boolean isAuthenticated(HttpServletRequest req);
	
	/**
	 * Initialisation with ServletConfig called by the DispatcherServlet.
	 * @param cfg
	 */
	public abstract void init(ServletConfig cfg) throws ServletException;
	
}
