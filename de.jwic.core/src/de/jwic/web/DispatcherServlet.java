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
 * de.jwic.web.DispatcherServlet
 * $Id: DispatcherServlet.java,v 1.9 2012/09/02 13:21:09 lordsam Exp $
 */
package de.jwic.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import de.jwic.base.ConfigurationTool;
import de.jwic.base.IApplicationSetup;
import de.jwic.base.JWicRuntime;
import de.jwic.base.XmlApplicationSetup;
import de.jwic.upload.Upload;

/**
 * Dispatches incoming request to an existing or new JWic session.
 * 
 * <p>The DispatcherServlet can initialize a log4j system if required. To do so, 
 * place a log4j.properties file somewhere (WEB-INF is a good place) and point 
 * to it using the init-param "log4j-init-file" in the servlet definition.</p>
 * <p>Sample:
 * <pre>
 *  <init-param>
 *    <param-name>log4j-init-file</param-name>
 *    <param-value>WEB-INF/log4j.properties</param-value>
 *  </init-param>
 * </pre>
 * </p> 
 *   
 * @author Florian Lippisch
 * @version $Revision: 1.9 $
 */
public class DispatcherServlet extends HttpServlet implements IApplicationSetupProvider {
    
	private static final long serialVersionUID = 2L;

	public final static String INIT_LOG4JINIT = "log4j-init-file";
	public final static String INIT_SETROOTDIR = "setRootDir";
	public final static String INIT_INTERCEPTORS = "servlet.interceptors";
	public final static String INIT_ENGINE_LISTENERS = "engine.listeners";
	public final static String INIT_AUTHENTICATOR = "authenticator";
	public final static String INIT_LOGIN_URL = "login";
	public final static String INIT_UPLOAD_LIMIT = "uploadlimit";

	protected final Log log = LogFactory.getLog(getClass());
    
	private JWicRuntime jRuntime = null;
	private ServletInterceptor[] interceptors = null;
   
	private WebEngine engine = null;
	private long uploadLimit = 4 * 1024 * 1024;
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// invoke all configured interceptors
		if (interceptors != null) {
			for (int i = 0; i < interceptors.length; i++) {
				try {
					if (interceptors[i] != null) {
						interceptors[i].preHandle(req, res);
					}
				} catch (Throwable thr) {
					log.error("Error executing interceptor.preHandle " + interceptors[i].getClass().getName(), thr);
				}
			}
		}

    	// Store the context path initialy in the JWicRuntime to allow
    	// other tools to build full path links.
    	if (jRuntime.getContextPath() == null) {
    		jRuntime.setContextPath(req.getContextPath());
    	}
    	
		super.service(req, res);
		
		
		// invoke all configured interceptors
		if (interceptors != null) {
			for (int i = 0; i < interceptors.length; i++) {
				try {
					if (interceptors[i] != null) {
						interceptors[i].postHandle(req, res);
					}
				} catch (Throwable thr) {
					log.error("Error executing interceptor.postHandle " + interceptors[i].getClass().getName(), thr);
				}
			}
		}

	}
	
    /* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    
		log.debug("incoming GET request: " + req.getRequestURI());
	    try {
	    	
	    	engine.handleRequest(req, res, null);
	    	
		} catch (Exception e) {
			log.error("Error in doGet()", e);
			engine.displayError(req, res, e, null);
		}
	    
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		Upload upload = null;
		if(req.getContentType().startsWith("multipart")) {
			// parse data using "multipart" mode
			upload = new Upload(req, ".", uploadLimit, 1 * 1024 * 1024);
			// get parameters from the stream and set them as AgoraRequest parameters.
			// fill webform
			Map<String, List<String>> fields = upload.getParams();
			req = new UploadHttpServletRequest(req, fields);
		}
		
		engine.handleRequest(req, res, upload);
			
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig cfg) throws ServletException {
		
		super.init(cfg);
		
		ServletContext srvCtx = getServletContext();
		String webAppRoot = srvCtx.getRealPath("/");
		
		/* Set the web application root directory as system.property named by "setRootDir" param */
		String propertyName = getInitParameter(INIT_SETROOTDIR);
		if (propertyName != null) {
			System.setProperty(propertyName, webAppRoot);
		}
		
		/* Setup the log4j system if specified in the web.xml */
	    String file = getInitParameter(INIT_LOG4JINIT);
	    if(file != null) {
	    	PropertyConfigurator.configure(webAppRoot + file);
	    }

		/* Setup the runtime */
		jRuntime = JWicRuntime.getJWicRuntime();
		jRuntime.setRootPath(webAppRoot);
		jRuntime.setSavePath(srvCtx.getRealPath("WEB-INF/jwic/sessions"));
		
		ConfigurationTool.setRootPath(webAppRoot);
		
		// because some WebServers need a leading slash while others do 
		// only work with a relative path, we try both ways.
		InputStream in = getServletContext().getResourceAsStream("WEB-INF/jwic-setup.xml");
		if (in == null) {
			in = getServletContext().getResourceAsStream("/WEB-INF/jwic-setup.xml");
		}
		if (in != null) {
			jRuntime.setupRuntime(in);
		} else {
			throw new ServletException("/WEB-INF/jwic-setup.xml not found.");
		}
		
		try {
			engine = new WebEngine(this, webAppRoot);
		} catch (Exception e1) {
			log.error("Can not instanciate WebEngine.", e1);
			throw new ServletException("Can not instanciate WebEngine: " + e1);
		}
		
		// init Authentication
		String authenticationClass = getInitParameter(INIT_AUTHENTICATOR);
		String loginPage = getInitParameter(INIT_LOGIN_URL);
		if (authenticationClass != null) {
			// use custom class
			try {
				IAuthenticator authenticator = (IAuthenticator)Class.forName(authenticationClass).newInstance();
				engine.setAuthenticator(authenticator);
				engine.setLoginPage(loginPage);
				
			} catch (Exception e) {
				log.error("Error creating Authentication '" + authenticationClass + "'", e);
			}
		} else {
			log.warn("No authenticator configured.");
		}
		
		// set the upload limit
		try {
			String upLimit = getInitParameter(INIT_UPLOAD_LIMIT);
			if (upLimit != null) {
				uploadLimit = Long.parseLong(upLimit);
			}
		} catch (NumberFormatException nfe) {
			log.warn("Upload-Limit parameter can not be read: ", nfe);
		}

		// Initialize the interceptors
		String intClasses = getInitParameter(INIT_INTERCEPTORS);
		if (intClasses != null && intClasses.length() != 0) {
			StringTokenizer stk = new StringTokenizer(intClasses, ";");
			interceptors = new ServletInterceptor[stk.countTokens()];
			int i = 0;
			while (stk.hasMoreTokens()) {
				String classname = stk.nextToken();
				try {
					interceptors[i++] = (ServletInterceptor)Class.forName(classname).newInstance();
				} catch (Throwable t) {
					log.error("Error creating ServletInterceptor '" + classname + "'", t);
				}
			}
		}
		
		// Initialize the engine listeners
		String intListeners = getInitParameter(INIT_ENGINE_LISTENERS);
		if (intListeners != null && intListeners.length() != 0) {
			StringTokenizer stk = new StringTokenizer(intListeners, ";");
			while (stk.hasMoreTokens()) {
				String classname = stk.nextToken();
				try {
					IWebEngineListener listener = (IWebEngineListener)Class.forName(classname).newInstance();
					engine.addWebEngineListener(listener);
				} catch (Throwable t) {
					log.error("Error creating IWebEngineListener '" + classname + "'", t);
				}
			}
		}

		
	}
	
	/**
	 * Destroy the servlet and the JWicRuntime.
	 */
	public void destroy() {
		
		log.info("DispatcherServlet.destroy()");
		JWicRuntime.getJWicRuntime().destroy();
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.web.IApplicationSetupProvider#createApplicationSetup(javax.servlet.http.HttpServletRequest)
	 */
	public IApplicationSetup createApplicationSetup(HttpServletRequest request) throws IOException {

		String name = request.getServletPath();
		InputStream in = null;
		if (name.endsWith(".xwic")) {
			in = getServletContext().getResourceAsStream(name + ".xml");
		}
		if (in == null) {
			in = getServletContext().getResourceAsStream(name); // try old name, for compatibility
		}
		if (in == null) { // still not found?
			throw new FileNotFoundException(request.getServletPath());
		}
		return new XmlApplicationSetup(in);
	}

	

}
