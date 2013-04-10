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
