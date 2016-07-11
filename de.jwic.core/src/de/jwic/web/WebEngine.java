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
 * $Id: WebEngine.java,v 1.25 2013/02/23 22:33:31 lordsam Exp $
 */
package de.jwic.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.json.JSONException;
import org.json.JSONWriter;

import de.jwic.base.ConfigurationTool;
import de.jwic.base.Control;
import de.jwic.base.ControlNotFoundException;
import de.jwic.base.Dimension;
import de.jwic.base.IActionController;
import de.jwic.base.IApplicationSetup;
import de.jwic.base.IControlContainer;
import de.jwic.base.IResourceControl;
import de.jwic.base.JWicException;
import de.jwic.base.JWicRuntime;
import de.jwic.base.MouseEvent;
import de.jwic.base.Page;
import de.jwic.base.RenderContext;
import de.jwic.base.SessionContext;
import de.jwic.base.UserAgentInfo;
import de.jwic.base.ValueChangedQueue;
import de.jwic.renderer.util.JWicTools;
import de.jwic.upload.Upload;
import de.jwic.upload.UploadFile;

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
 * @version $Revision: 1.25 $
 */
public class WebEngine  {
    
	/**
	 * 
	 */
	private static final String RESPONSE_FORMAT_JSON = "JSON";

	private final static String PAGE_FILE = "jwic.page";
	private final static String PAGE_LAYER_FILE = "jwic_layer.page";
	protected final Log log = LogFactory.getLog(getClass());
    
	private VelocityEngine ve = null; 
	private JWicRuntime jRuntime = null;
	private IAuthenticator authenticator = null;
	private IApplicationSetupProvider appSetupProvider = null;
	private String loginPage = null;
    
	private IWebEngineListener[] listeners = new IWebEngineListener[0];
	
	private enum EngineEvent {
		preHandleAction,
		postHandleAction,
		preRendering,
		postRendering,
		preControlRendering,
		postControlRendering,
		preResourceRequest,
		postResourceRequest
	}
	
	/**
	 * Used to prepare AjaxUpdates.
	 *
	 * @author Developer
	 */
	private class Updateable {
		String htmlCode = null;
		Map<String, String> javaScript = null;
		Set<String> requiredStaticJS = null;
		
		/**
		 * @param htmlCode
		 */
		public Updateable(String htmlCode) {
			super();
			this.htmlCode = htmlCode;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			// for compatibility reasons, this must return the htmlCode.
			return htmlCode;
		}
	}
	
	/**
	 * Constructs a new WebEngine.
	 * @param provider
	 * @throws Exception 
	 */
	public WebEngine(IApplicationSetupProvider provider, String rootDir) throws Exception {
		this.appSetupProvider = provider;
		
		/*
		 * Initialize the VelocityEngine used by this servlet.
		 */
		ve = new VelocityEngine();
		Properties veprop = new Properties();
		try {
			File fRootDir = new File(rootDir);
			veprop.load(new FileInputStream(new File(fRootDir, "WEB-INF/jwic/velocity.WebEngine.properties")));
			ConfigurationTool.insertRootPath(veprop);
		} catch (Exception ex) {
			log.warn("WEB-INF/jwic/velocity.WebEngine.properties not found, using defaults");
			veprop.setProperty("resource.loader", "file,class");
			veprop.setProperty("file.resource.loader.description", "Velocity File Resource Loader");
			veprop.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
			veprop.setProperty("file.resource.loader.path", rootDir);
			veprop.setProperty("file.resource.loader.cache", "true");
			veprop.setProperty("file.resource.loader.modificationCheckInterval", "2");
			veprop.setProperty("class.resource.loader.description", "Velocity Classpath Resource Loader");
			veprop.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		}
		ve.init(veprop);
		
		jRuntime = JWicRuntime.getJWicRuntime();

		
	}
	
	/**
	 * Add a IWebEngineListener.
	 */
	public synchronized void addWebEngineListener(IWebEngineListener listener) {
		IWebEngineListener[] l2 = new IWebEngineListener[listeners.length + 1];
		System.arraycopy(listeners, 0, l2, 0, listeners.length);
		l2[listeners.length] = listener;
		listeners = l2;
	}

	/**
	 * Add a IWebEngineListener.
	 */
	public synchronized void removeWebEngineListener(IWebEngineListener listener) {
		ArrayList<IWebEngineListener> lTmp = new ArrayList<IWebEngineListener>();
		for(IWebEngineListener l : listeners) {
			if (l != listener) {
				lTmp.add(l);
			}
		}
		listeners = (IWebEngineListener[]) lTmp.toArray();
	}
	
	/**
	 * Fire the event.
	 * @param evType
	 * @param event
	 */
	private void fireEvent(EngineEvent evType, WebEngineEvent event) {
		if (listeners.length != 0) {
			
			for (IWebEngineListener listener : listeners) {
				switch (evType) {
				case preRendering:
					listener.preRendering(event);
					break;
	
				case postRendering:
					listener.postRendering(event);
					break;

				case preControlRendering:
					listener.preControlRendering(event);
					break;
	
				case postControlRendering:
					listener.postControlRendering(event);
					break;
					
				case preHandleAction:
					listener.preHandleAction(event);
					break;
					
				case postHandleAction:
					listener.postHandleAction(event);
					break;
					
				case preResourceRequest:
					listener.preResourceRequest(event);
					break;
					
				case postResourceRequest:
					listener.postResourceRequest(event);
					break;

				}
			}
		}
	}
	
	/**
	 * Checks ModuleSession authentication.
	 * @param ms
	 * @param req
	 * @param res
	 * @return true if request is authenticated
	 * @throws NotAuthenticatedException
	 */
	private void handleAuthentication(SessionContext sc, HttpServletRequest req, HttpServletResponse res) throws NotAuthenticatedException {
		
		if (sc.getProperty(SessionContext.PROP_AUTHENTICATION, "false").equals("true")) {
			if (authenticator == null || !authenticator.isAuthenticated(req)) {
				// redirect to login page
				throw new NotAuthenticatedException();
			} 
		}
		
	}
	
	/**
	 * @param req
	 * @param res
	 */
	public void handleRequest(HttpServletRequest req, HttpServletResponse res, Upload upload) {

		servletContainerFixes(req, res);
		long start = System.currentTimeMillis();
    	boolean resourceMode = "1".equals(req.getParameter(IResourceControl.URL_RESOURCE_PARAM));
    	boolean ajaxMode = "1".equals(req.getParameter("_ajaxreq"));
    	boolean conTest = "1".equals(req.getParameter("_jConTest"));
    	SessionContext sc = null;
		try {

	    	boolean isNew = false;
	    	boolean isReloaded = false;
	    	
	    	String sessionID = req.getParameter("_msid");
	    	String clientID = req.getSession().getId();
	    	if (sessionID != null) {
	    		sc = jRuntime.getSessionContext(clientID, sessionID, req);
	    		if (sc == null) {
	    			// mark as reloaded so the user can be notified.
	    			isReloaded = true;
	    		}
	    	} 
	    	
	    	// This is a connection test. Do not initialize the app if it isn't initialized yet.
	    	if (conTest) {
	    		respondConnectionTest(sc, req, res);
	    	} else {
	    	
		    	if (sc == null) {
		    		sc = initSession(req); 
		    		isNew = true;
		    	}
	
		    	handleAuthentication(sc, req, res);
		    	
		    	if (!isNew && !resourceMode && !isReloaded) {
		    		handleAction(sc, req, res, upload); 
		    	}
		    	boolean render = handleRedirect(sc, req, res);
	
		    	
		    	if (render) {
		    		// render control
		    		if (resourceMode) {
	        			renderResourceControl(sc, req, res, isReloaded);
		        	} else if (ajaxMode) {
		        		renderAjax(sc, req, res, isReloaded);
		        	} else {
		        		render(sc, req, res, isReloaded);
		        	}
		    	}
	    	}
		} catch (FileNotFoundException fnf) {
			try {
				res.sendError(HttpServletResponse.SC_NOT_FOUND, fnf.getMessage());
			} catch (IOException ioe) {
				log.error("Error sending error to client", ioe);
			}
			log.warn("File not found: " + fnf.getMessage());
		} catch (NotAuthenticatedException nae) {
			authFailed(req, res);
			
		} catch (Exception e) {
			log.error("Error in doPost()", e);
			displayError(req, res, e, sc);
		}
		long time = System.currentTimeMillis() - start;
		log.debug("total processing time: " + time + "ms");
		
	}

	/**
	 * @param sc
	 * @param req
	 * @param res
	 */
	private void respondConnectionTest(SessionContext sc, HttpServletRequest req, HttpServletResponse res) {
		
		res.setContentType("text/json; charset=UTF-8");
		try {
			PrintWriter pw = res.getWriter();
			pw.println("{\"sessionInitialized\":" + (sc != null) + "}");
			pw.flush();
			pw.close();
			
		} catch (IOException e) {
			log.error("Error sending response on connection test request", e);
		}
		
	}

	/**
	 * Checks if a redirect/exit has to happen.
	 * @param ms
	 * @param req
	 * @param res
	 * @return
	 */
	private boolean handleRedirect(SessionContext sc, HttpServletRequest req, HttpServletResponse res) {
		boolean render = true;
		boolean ajaxMode = "1".equals(req.getParameter("_ajaxreq"));
		
		// check if redirect had been set
		if (sc.getRedirectToURL() != null && !ajaxMode) {

			try {
				res.sendRedirect(sc.getRedirectToURL());
				sc.clearRedirect();
			} catch (IOException ioe) {
				// sth. went wrong
				throw new RuntimeException("Redirect to " + sc.getRedirectToURL() + " not successful: " + ioe);
			}
			render = false;
		} else if (sc.isDoExit() && !ajaxMode) {
			// display a message say "application has exited."
			displayError(req, res, new JWicException("The application has been terminated and no exit/redirect URL is given."), null);
		}

		// check if application should be closed
		if (sc.isDoExit() && !ajaxMode) {
			// close session
			sc.destroy();
			render = false;
			
		}
		return render;
	}

	/**
	 * Displays a page that describes the error.
	 * @param req
	 * @param res
	 * @param e
	 */
	public void displayError(HttpServletRequest req, HttpServletResponse res, Exception e, SessionContext sc) {
		
    	boolean ajaxMode = "1".equals(req.getParameter("_ajaxreq"));
		String responseFormat = req.getParameter("_format");

		if (RESPONSE_FORMAT_JSON.equals(responseFormat)) {
			res.setContentType("text/json; charset=UTF-8");
		} else {
			// compatibility mode.
			res.setContentType("text/xml; charset=UTF-8");
		}
    	if (RESPONSE_FORMAT_JSON.equals(responseFormat)) {
    		
    		PrintWriter pw;
    		try {
    			pw = res.getWriter();
				JSONWriter jsonOut = new JSONWriter(pw);
				jsonOut.object();
				jsonOut.key("exception")
					   .value(e.toString());
				
	    		jsonOut.endObject();
    		} catch (Exception er) {
    			log.error("Error getting writer!", er);
    			return;
    		}
    		
    	} else { // compatibility mode
			res.setContentType(ajaxMode ? "text/xml" : "text/html");
			PrintWriter pw;
			try {
				pw = res.getWriter();
			} catch (Exception ex) {
				log.error("Error getting writer!");
				return;
			}
			
			VelocityContext ctx = new VelocityContext();
			ctx.put("jwic", new JWicTools(Locale.getDefault()));
			if (sc != null) {
				ctx.put("context", sc);
			}
			ctx.put("exception", e);
			try {
				String errorFile = ajaxMode? "WEB-INF/jwic/pages/jwic.ajax.page" 
											: "WEB-INF/jwic/pages/jwic_error.page";
				Template pageBase = ve.getTemplate(errorFile);
				pageBase.merge(ctx, pw);
			} catch (Exception ex) {
				pw.println("Error displaying error information. " + ex);
			}
    	}
	}
	
	
	/**
	 * Handle the incoming actions.
	 * @param ms
	 * @param req
	 * @param res
	 * @return true if control should be rendered
	 */
	private void handleAction(SessionContext sc, HttpServletRequest req, HttpServletResponse res, Upload upload) {

		
		IActionController controller = sc.getActionController();
		
		String srcCtrl = req.getParameter("__ctrlid");
		String action = req.getParameter("__action");
		String acpara = req.getParameter("__acpara");
		String sysinfo = req.getParameter("__sysinfo");
		String ticketNo = req.getParameter("__ticket");
		String layerid = req.getParameter("layerid");
		String mouseEvent = req.getParameter("__mouseevent");

		ValueChangedQueue queue = new ValueChangedQueue();
		
		// double post protection
		if (ticketNo != null && ticketNo.length() != 0) {
			long ticket = Long.parseLong(ticketNo);
			if (!sc.validateTicket(ticket, layerid)) {
				log.info("DPP: ticket numbers don't match. actions ignored.");
				return;
			}
		} else {
			log.warn("DPP: No ticket number in the request.");
		}
		
		if ("".equals(srcCtrl) && "redraw".equals(action)) {
			log.debug("Redraw required - ignoring post content.");
			return;
		}
		
		WebEngineEvent event = new WebEngineEvent(sc, srcCtrl, action, acpara);
		fireEvent(EngineEvent.preHandleAction, event);
		
		// store system informations
		if (sysinfo != null) {
			// refresh user agent
			UserAgentInfo userAgent = sc.getUserAgent();
			StringTokenizer stk = new StringTokenizer(sysinfo, ";");
			int tokenNo = 0;
			while (stk.hasMoreTokens()) {
				String tokenValue = stk.nextToken();
				int value = 0;
				try {
					Double valueDouble = new Double(tokenValue);
					value = valueDouble.intValue();
				}catch(NumberFormatException ex){
					log.error("Error while parsing client size value: " + tokenValue + " for token: " + tokenNo);
				}
				
				switch (tokenNo) {
					case 0: // visible width
						userAgent.setClientWidth(value);
						break;
					case 1: // visible height
						userAgent.setClientHeight(value);
						break;
					case 2:
						userAgent.setClientLeft(value);
						break;
					case 3:
						userAgent.setClientTop(value);
						break;
				}
				tokenNo++;
			}
			
			if (sc.getTopControl() instanceof Page) {
				// update page 
				Page page = (Page)sc.getTopControl();
				page.setClientLeft(userAgent.getClientLeft());
				page.setClientTop(userAgent.getClientTop());
				page.setPageSize(new Dimension(userAgent.getClientWidth(), userAgent.getClientHeight()));
			}
		}
		
		// set mouse event in session context
		if (mouseEvent != null && mouseEvent.length() > 0) {
			StringTokenizer stk = new StringTokenizer(mouseEvent, ";");
			int tokenNo = 0;
			String type = null;
			int x = 0;
			int y = 0;
			while (stk.hasMoreTokens()) {
				String value = stk.nextToken();
				switch (tokenNo) {
					case 0: // mouse event type
						type = value;
						break;
					case 1: // mouse x
						x = Integer.parseInt(value);
						break;
					case 2: // mouse y
						y = Integer.parseInt(value);
						break;
				}
				tokenNo++;
			}
			// set mouse event
			sc.setMouseEvent(new MouseEvent(sc, type, x, y));
		} else {
			// remove mouse event
			sc.setMouseEvent(null);
		}

		// store recieved "fields" into the coresponding controls
		for (Enumeration<?> e = req.getParameterNames(); e.hasMoreElements(); ) {
			String paramName = (String)e.nextElement();
			try {
				controller.handleField(sc, queue, paramName, req.getParameterValues(paramName));
			} catch (ControlNotFoundException cnfe) {
				log.warn("Can not assign field '" + paramName + "'. Control not found.");
			}
		}
		
		// store recieved files into the coresponding controls
		if (upload != null) {
			Map<String, UploadFile> files = upload.getFiles();
			for (Iterator<String> it = files.keySet().iterator(); it.hasNext(); ) {
				String fieldname = it.next();
				UploadFile file = files.get(fieldname);
				controller.handleFile(sc, fieldname, file);
			}
		}
		
		// fire the valueChangedEvents before the action processing
		queue.processQueue();
		
		// notify the control that has created the "action" link that the
		// link has been clicked by the user.
		if (action != null && srcCtrl != null && srcCtrl.length() != 0) {
			controller.handleAction(sc, srcCtrl, action, acpara);
		}
		
		fireEvent(EngineEvent.postHandleAction, event);
		
		return;		
	}

	/**
	 * Render the jWic Application.
	 * @param sc
	 * @param req
	 * @param res
	 */
	private void render(SessionContext sc, HttpServletRequest req, HttpServletResponse res, boolean markReloaded) {

		res.setContentType("text/html; charset=UTF-8");
		RenderContext context;
		try {
			context = new RenderContext(req, res);
		} catch (Exception e) {
			log.error("Error creating renderContext", e);
			return;
		}
		String layerid = req.getParameter("layerid");
		Control ctrl = (layerid == null || layerid.length() == 0) ? sc.getTopControl() : sc.getControlByLayerID(layerid);
		
		VelocityContext ctx = new VelocityContext();
		ctx.put("jwic", new JWicTools(sc.getLocale(), sc.getTimeZone()));
		ctx.put("page", ctrl);
		ctx.put("content", new ContentRenderer(ctrl, context));
		ctx.put("context", sc);
		ctx.put("layerid", layerid == null ? "" : layerid);
		ctx.put("reloaded", markReloaded ? "1" : "0");
		ctx.put("contextPath", req.getContextPath());
		ctx.put("renderContext", context);
		List<String> scriptQueue = sc.getScriptQueue();
		if (!scriptQueue.isEmpty()) {
			StringWriter sw = new StringWriter();
			JSONWriter writer = new JSONWriter(sw);
			try {
				writer.array();
				for (String script : scriptQueue) {
					writer.value(script);
				}
				
				writer.endArray();
				ctx.put("scriptQueue", sw.toString());
			}catch(Exception e){
				throw new RuntimeException("Error while configuring Json Option for Script Queue.", e);
			}
			sc.clearScriptQueue();
		}
		
		String templateName;
		if (layerid == null || layerid.length() == 0) {
			templateName = sc.getProperty("pagefile", PAGE_FILE);
		} else {
			templateName = sc.getProperty("layerpagefiler", PAGE_LAYER_FILE);
		}
		
		// build path to page template
		String path = req.getServletPath();
		int idx = path.lastIndexOf('/');
		if (idx != -1) {
			templateName = path.substring(0, idx + 1) + templateName;
		}
		
		try {
			WebEngineEvent event = new WebEngineEvent(sc, false);
			fireEvent(EngineEvent.preRendering, event);
			
			Template pageBase = ve.getTemplate(templateName);
			pageBase.merge(ctx, context.getWriter());
			
			fireEvent(EngineEvent.postRendering, event);
		} catch (Exception ex) {
			displayError(req, res, ex, sc);
		}
		sc.setRequireRedraw(false);
	}

	/**
	 * Render the jWic Application and return it in XML format so that the 
	 * jWic ajax script can update the DHTML controls.
	 * @param sc
	 * @param req
	 * @param res
	 */
	private void renderAjax(SessionContext sc, HttpServletRequest req, HttpServletResponse res, boolean markReloaded) {

		String responseFormat = req.getParameter("_format");

		WebEngineEvent event = new WebEngineEvent(sc, true);
		fireEvent(EngineEvent.preRendering, event);

		if (RESPONSE_FORMAT_JSON.equals(responseFormat)) {
			res.setContentType("text/json; charset=UTF-8");
		} else {
			// compatibility mode.
			res.setContentType("text/xml; charset=UTF-8");
		}

		PrintWriter pw;
		try {
			pw = res.getWriter();
		} catch (Exception e) {
			log.error("Error getting writer!");
			return;
		}
		String layerid = req.getParameter("layerid");
		Control ctrl = (layerid == null || layerid.length() == 0) ? sc.getTopControl() : sc.getControlByLayerID(layerid);
			
		// create a list of all controls to be updated
		Map<String, Updateable> toUpdate = null;
		Exception renderingException = null;
		try {
			if (!sc.isRequireRedraw()) {
				toUpdate = new HashMap<String, Updateable>();
				scanForUpdates(toUpdate, ctrl, req, res);
			}
		} catch (Exception e) {
			log.error("Error rendering controls.", e);
			renderingException = e;
		}

		if (RESPONSE_FORMAT_JSON.equals(responseFormat)) {
			JSONWriter jsonOut = new JSONWriter(pw);
			try {
				jsonOut.object()
					.key("ticket")
					.value(sc.getRequestTicket(layerid));
				if (renderingException != null) {
					jsonOut.key("exception")
						   .value(renderingException.toString());
				}
				if (sc.isRequireRedraw()) {
					jsonOut.key("requireRedraw")
						.value(sc.getSessionId());
				}
				if (ctrl instanceof Page) {
					Page page = (Page)ctrl;
					if (page.getForceFocusElement() != null && page.getForceFocusElement().length() != 0) {
						jsonOut.key("forceFocus")
							.value(page.getForceFocusElement());
					}
				}
				
				if (toUpdate != null) {
					
					// merge required static JS files
					Set<String> allRequiredLibs = new HashSet<String>();
					for (String key : toUpdate.keySet()) {
						Updateable updateable = toUpdate.get(key);
						if (updateable.requiredStaticJS != null) {
							allRequiredLibs.addAll(updateable.requiredStaticJS);
						}
					}
					if (!allRequiredLibs.isEmpty()) {
						jsonOut.key("requiredJS")
							.array();
						for (String lib : allRequiredLibs) {
							jsonOut.value(lib);
						}
						jsonOut.endArray();
					}
								
					
					jsonOut.key("updateables")
						.array();
					for (String key : toUpdate.keySet()) {
						Updateable updateable = toUpdate.get(key);
						jsonOut.object();
						jsonOut.key("key").value(key);
						jsonOut.key("html").value(updateable.htmlCode);
						if (updateable.javaScript != null && updateable.javaScript.size() > 0) {
							jsonOut.key("scripts")
								.array();
							for (Map.Entry<String, String> entry : updateable.javaScript.entrySet()) {
								jsonOut.object();
								jsonOut.key("controlId");
								jsonOut.value(entry.getKey());
								jsonOut.key("script");
								jsonOut.value(entry.getValue());
								jsonOut.endObject();
							}
							jsonOut.endArray();	
						}
						jsonOut.endObject();
					}
					jsonOut.endArray();
				}
				
				List<String> scriptQueue = sc.getScriptQueue();
				if (!scriptQueue.isEmpty()) {
					jsonOut.key("scriptQueue").array();
					for (String line : scriptQueue) {
						jsonOut.value(line);
					}
					jsonOut.endArray();
					sc.clearScriptQueue();
				}
				
				jsonOut.endObject();
			} catch (JSONException e) {
				log.error("Error generating AJAX response", e);
				displayError(req, res, e, sc);
			}
		} else {
			// compatibility mode.
			VelocityContext ctx = new VelocityContext();
			ctx.put("jwic", new JWicTools(sc.getLocale(), sc.getTimeZone()));
			ctx.put("page", ctrl);
			ctx.put("context", sc);
			ctx.put("layerid", layerid == null ? "" : layerid);
			ctx.put("reloaded", markReloaded ? "1" : "0");
			ctx.put("contextPath", req.getContextPath());
			if (toUpdate != null) {
				ctx.put("updateables", toUpdate);
			}
			
			String templateName = "WEB-INF/jwic/pages/jwic.ajax.page";
			try {
				Template pageBase = ve.getTemplate(templateName);
				pageBase.merge(ctx, pw);
			} catch (Exception ex) {
				displayError(req, res, ex, sc);
			}
		}
		fireEvent(EngineEvent.postRendering, event);

	}
	/**
	 * Render a jWic resource control.
	 * @param sc
	 * @param req
	 * @param res
	 * @param markReloaded
	 */
	private void renderResourceControl(SessionContext sc, HttpServletRequest req, HttpServletResponse res, boolean markReloaded) {

		String controlId = req.getParameter(IResourceControl.URL_CONTROLID_PARAM);
		try {
			Control ctrl = controlId != null ? sc.getControlById(controlId) : null;
			if (ctrl instanceof IResourceControl) {
				IResourceControl control = (IResourceControl)ctrl;
				WebEngineEvent event = new WebEngineEvent(sc, controlId);
				fireEvent(EngineEvent.preResourceRequest, event);
				try {
					control.attachResource(req, res);
				} catch (Throwable t) {
					log.error("Error during IResourceControl.attachResource", t);
				}
				fireEvent(EngineEvent.postResourceRequest, event);
			}
		} catch (ControlNotFoundException cnfe) {
			// the control does no longer exist, send back notification
			log.debug("ResourceRequest to control id '" + controlId + "' failed. A control with this id does not exist.");
			try {
				res.sendError(HttpServletResponse.SC_NOT_FOUND, "A control with this ID can not be found.");
			} catch (IOException e) {
				log.error("Error sending control not found exception.", e);
			}
			return;
		}
		
	}	

	/**
	 * Scan the control tree for controls that require rendering.
	 * If the control requires redraw it's rendered.
	 * If it's visible and instance of IControlContainer its controls
	 * are scaned for updates (more time intensive!).
	 * @param toUpdate
	 * @param ctrl
	 */
	private void scanForUpdates(Map<String, Updateable> toUpdate, Control ctrl, HttpServletRequest req, HttpServletResponse res) {
		
		long start = System.currentTimeMillis();
		boolean showTime = false;

		if (showTime = ctrl.isRequireRedraw()) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PrintWriter pw = new PrintWriter(out); 
			RenderContext context = new RenderContext(req, res, pw);
			ContentRenderer cr = new ContentRenderer(ctrl, context);
			try {
				WebEngineEvent event = new WebEngineEvent(ctrl.getSessionContext(), ctrl.getControlID());
				fireEvent(EngineEvent.preControlRendering, event);

				cr.render();
				
				fireEvent(EngineEvent.postControlRendering, event);

			} catch (Throwable t) {
				log.error("Error rendering control " + ctrl.getControlID() + " type " + ctrl.getClass().getName(), t);
				pw.print("Error rendering control: " + t.toString());
			}
			pw.flush();
			Updateable updateable = new Updateable(out.toString());
			updateable.javaScript = context.getScripts();
			updateable.requiredStaticJS = context.getRequiredStaticJs();
			toUpdate.put(ctrl.getControlID(), updateable);
		} else {
			if (!ctrl.isVisible()) {
				return;
			}
			if (ctrl instanceof IControlContainer) {
				IControlContainer container = (IControlContainer)ctrl;
				for (Iterator<Control> it = container.getControls(); it.hasNext(); ) {
					Control control = it.next();
					if (container.isRenderingRelevant(control)) {
						scanForUpdates(toUpdate, control, req, res);
					}
				}
			}
		}

		if (showTime /* TODO nicer implementation */) {
			long time = System.currentTimeMillis() - start;
			Runtime rt = Runtime.getRuntime();
			log.debug("scan for updates time: " + time + "ms for " + ctrl.getControlID() + 
					", Java free heap: " + rt.freeMemory() + " of max " + rt.maxMemory());
			
		}
	}

	/**
	 * Initialize the module session by launching the specified jWic application.
	 * @param req
	 * @return
	 * @throws NotAuthenticatedException
	 * @throws FileNotFoundException
	 */
	private SessionContext initSession(HttpServletRequest req) throws NotAuthenticatedException, IOException {
		
		// get configuration file
		IApplicationSetup appSetup = appSetupProvider.createApplicationSetup(req);
		
		if (appSetup.isRequireAuthentication()) {
			if (authenticator == null || !authenticator.isAuthenticated(req)) {
				throw new NotAuthenticatedException();
			}
		}
	
		HttpSession session = req.getSession();
		Locale locale;
		TimeZone timeZone;
		if (session.getAttribute(Locale.class.getName()) instanceof Locale) {
			locale = (Locale)session.getAttribute(Locale.class.getName());
		} else {
			// use the default locale if there is no locale specified for the 
			// current session.
			locale = Locale.getDefault();
		}
		if (session.getAttribute(TimeZone.class.getName()) instanceof TimeZone) {
			timeZone = (TimeZone)session.getAttribute(TimeZone.class.getName());
		} else {
			timeZone = TimeZone.getDefault();
		}
		SessionContext sc = jRuntime.createSessionContext(appSetup, locale, timeZone, req);
		sc.setCallBackURL(getFileName(req) + "?_msid=" + sc.getSessionId());

		return sc;
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

	/**
	 * Set the Authenticator.
	 * @param authenticator
	 */
	public void setAuthenticator(IAuthenticator authenticator) {
		this.authenticator = authenticator;
	}
	
	/**
	 * Set the loginPage.
	 * @param loginPage
	 */
	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.security.Authentication#authenticate(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void authFailed(HttpServletRequest req, HttpServletResponse res) {
		try {
			if (loginPage != null) {
				// set current url in ClientSession for later redirect
				req.getSession().setAttribute(IAuthenticator.SESSION_REDIRECT_URL, req.getRequestURI());
				// show login page
				res.sendRedirect(loginPage);
			} else {
				res.getWriter().println("Configuration failure: No login page configured.");
				return;
			}
		} catch (IOException ioe) {
			log.error("Error redirecting to login page: " + loginPage, ioe);
		}
	}
	
	/**
	 * Fix servlet container problems, e.g. jetty 6.1.2rc3 (and earlier) ajp13 usage.
	 * @param req
	 * @param res
	 */
	private void servletContainerFixes(HttpServletRequest req, HttpServletResponse res) {
		// correct character encoding and try to read it from header
		String enc = req.getCharacterEncoding();
		if (enc == null) {
			String contentType = req.getHeader("Content-Type");
			if (contentType != null) {
				contentType = contentType.toLowerCase();
				int i = contentType.indexOf("charset");
				if (i != -1) {
					String s = contentType.substring(i + 7).trim();
					s = s.substring(s.indexOf('=') + 1).trim();
					i = s.indexOf(';');
					if (i != -1) {
						s = s.substring(0, i).trim();
					}
					enc = s;
					try {
						req.setCharacterEncoding(enc);
					} catch (UnsupportedEncodingException e) {
						log.warn("Cannot set encoding", e);
					}
				}
			}
		}
	}
}
