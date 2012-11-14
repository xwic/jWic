/*
 * Copyright 2005 jWic group (http://www.jwic.de)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * de.jwic.spring.JWicSpringController
 * Created on 20.05.2005
 * $Id: JWicSpringController.java,v 1.3 2008/09/17 15:19:36 lordsam Exp $
 */
package de.jwic.spring;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import de.jwic.base.ConfigurationTool;
import de.jwic.base.IApplicationSetup;
import de.jwic.base.JWicRuntime;
import de.jwic.upload.Upload;
import de.jwic.web.IApplicationSetupProvider;
import de.jwic.web.IAuthenticator;
import de.jwic.web.UploadHttpServletRequest;
import de.jwic.web.WebEngine;

/**
 * Starts applications and dispatches incoming events to those applications.
 * The application setup is loaded from the spring component container.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class JWicSpringController extends AbstractController implements IApplicationSetupProvider {


	protected final Log log = LogFactory.getLog(getClass());

	private JWicRuntime jRuntime = null;
	private int uploadLimit = 1024 * 1024 * 16; // 16 mb
	private WebEngine engine = null;
	
	private IAuthenticator authenticator = null;
	private String loginPage = null;
	
	/**
	 * Default constructor.
	 */
	public JWicSpringController() {
		jRuntime = JWicRuntime.getJWicRuntime();
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.context.support.ApplicationObjectSupport#initApplicationContext()
	 */
	protected void initApplicationContext() throws BeansException {
		super.initApplicationContext();

		ServletContext srvCtx = getServletContext();
		String webAppRoot = srvCtx.getRealPath("/");
		jRuntime.setRootPath(webAppRoot);
		ConfigurationTool.setRootPath(webAppRoot);
		try {
			engine = new WebEngine(this, webAppRoot);
		} catch (Exception e) {
			throw new RuntimeException("Error initializing WebEngine");
		}
		if (loginPage != null) {
			engine.setLoginPage(loginPage);
		}
		if (authenticator != null) {
			engine.setAuthenticator(authenticator);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res) throws Exception {


    	// Store the context path initialy in the JWicRuntime to allow
    	// other tools to build full path links.
    	if (jRuntime.getContextPath() == null) {
    		jRuntime.setContextPath(req.getContextPath());
    	}

    	Upload upload = null;
		if(req.getMethod().equals("POST") && req.getContentType().startsWith("multipart")) {
			// parse data using "multipart" mode
			upload = new Upload(req, ".", uploadLimit, 1 * 1024 * 1024);
			// get parameters from the stream and set them as AgoraRequest parameters.
			// fill webform
			Map<String, String> fields = upload.getParams();
			req = new UploadHttpServletRequest(req, fields);
		}

		engine.handleRequest(req, res, upload);
		
		return null;
	}
	/**
	 * @return Returns the uploadLimit.
	 */
	public int getUploadLimit() {
		return uploadLimit;
	}
	/**
	 * @param uploadLimit The uploadLimit to set.
	 */
	public void setUploadLimit(int uploadLimit) {
		this.uploadLimit = uploadLimit;
	}
	/**
	 * @return Returns the authenticator.
	 */
	public IAuthenticator getAuthenticator() {
		return authenticator;
	}
	/**
	 * @param authenticator The authenticator to set.
	 */
	public void setAuthenticator(IAuthenticator authenticator) {
		this.authenticator = authenticator;
	}
	
	/**
	 * @return Returns the loginPage.
	 */
	public String getLoginPage() {
		return loginPage;
	}
	/**
	 * @param loginPage The loginPage to set.
	 */
	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.web.IApplicationSetupProvider#createApplicationSetup(javax.servlet.http.HttpServletRequest)
	 */
	public IApplicationSetup createApplicationSetup(HttpServletRequest request) throws IOException {
		// load the application setup
		WebApplicationContext context = getWebApplicationContext();
		
		String beanId = getFileName(request);
		if (!context.containsBean(beanId)) {
			throw new FileNotFoundException("Application with id " + beanId + " not found/specified in application context.");
		}

		return (IApplicationSetup)context.getBean(beanId);

	}
	
	/**
	 * @param req
	 * @return
	 */
	private String getFileName(HttpServletRequest req) {
		String path = req.getServletPath();
		int i = path.lastIndexOf('/'); 
		if (i != -1) {
			return path.substring(i + 1);
		}
		return path;
	}
	
}
