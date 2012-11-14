/*
 * Copyright 2005 jWic Group (http://www.jwic.de)
 * de.jwic.web.ServletInterceptor
 * $Id: ServletInterceptor.java,v 1.1 2006/01/16 08:31:13 lordsam Exp $
 */
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
