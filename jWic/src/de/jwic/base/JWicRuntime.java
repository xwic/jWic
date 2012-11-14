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
 * de.jwic.base.JWicRuntime
 * Created on 20.10.2004
 * $Id: JWicRuntime.java,v 1.15 2008/09/17 15:19:25 lordsam Exp $
 */
package de.jwic.base;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import de.jwic.events.SessionEvent;
import de.jwic.renderer.velocity.BaseVelocityRenderer;
import de.jwic.util.Compatibility;
import de.jwic.util.DTDEntityResolver;
import de.jwic.util.XMLTool;

/**
 * The JWicRuntime manages the lifecycle of jWic applications.
 * 
 * <p>The runtime takes care of the special velocity behaivior since the default jWic
 * renderer is velocity, wich makes things easier.</p>
 *  
 * @author Florian Lippisch
 * @version $Revision: 1.15 $
 */
public class JWicRuntime {

	public final static String DTD_PUBLICID = "-//jWic//DTD jwic-setup 3.2//EN";
	public final static String DTD_SYSTEMID = "http://jwic.sourceforge.net/jwic-setup-3.2.dtd";
	public final static String DTD_RESOURCEPATH = "/de/jwic/base/jwic-setup.dtd";
	
	private static final String ATTR_NOTIFIER = "JWicRuntime.sessionNotifier";
	private static JWicRuntime singleton = null;
	private static boolean initialized = false;

	protected final Log log = LogFactory.getLog(getClass());
	private String rootPath = "./"; 
	private String savePath = "./";
	private int sessionStoreTime = 0;
	
	/** The map of registerd velocity engines **/
	private Map<String, VelocityEngine> velocityEngines = new HashMap<String, VelocityEngine>();
	private Map<String, IControlRenderer> renderers = new HashMap<String, IControlRenderer>();
	
    private SessionManager sessionManager = null; 
	
    private String contextPath = null;
    
	/**
	 * This class is set as a session attribute to get notified when a session
	 * is closed by the server.
	 * @author Florian Lippisch
	 */
	private class HttpSessionClosedListener implements HttpSessionBindingListener {
		private JWicRuntime runtime;
		private String sessionID;
		public HttpSessionClosedListener(JWicRuntime rt, String sessionID) {
			this.runtime = rt;
			this.sessionID = sessionID;
		}
		public void valueBound(HttpSessionBindingEvent arg0) {
			// nothing to do
		}
		public void valueUnbound(HttpSessionBindingEvent arg0) {
			runtime.sessionClosed(sessionID);
		}
	}
	
	/**
	 * Default, private contructor. Use getJWicRuntime() to get an instance.
	 */
	private JWicRuntime() {
	
	}

	/**
	 * This method is invoked by the HttpSessionClosedListener when a session is 
	 * closed.
	 * @param sessionID
	 */
	void sessionClosed(String clientID) {
		sessionManager.destroyClient(clientID);
	}

	/**
	 * Returns the singleton instance of the JWicRuntime.
	 * @return
	 */
	public static JWicRuntime getJWicRuntime() {
		if (!initialized) {
			synchronized (JWicRuntime.class) {
				if (!initialized) {
					singleton = new JWicRuntime();
					initialized = true;
				}
			}
		}
		return singleton;
	}
	
	/**
	 * Returns the SessionManager. The SessionManager should only be used
	 * for debug and diagnostic purpose.
	 * 
	 * @return
	 */
	public SessionManager getSessionManager() {
		return sessionManager;
	}
	
	/**
	 * Returns the renderer with the specified id. If no renderer can be
	 * found, a JWicException is thrown.
	 * @param rendererId
	 * @return
	 */
	public static IControlRenderer getRenderer(String rendererId) throws JWicException {
		IControlRenderer renderer = singleton.renderers.get(rendererId);;
		if (renderer == null) {
			throw new JWicException("Renderer unknown: " + rendererId);
		}
		return renderer;
	}
	
	/**
	 * Returns the session with the specified ID.
	 * @param clientID
	 * @param sessionID
	 * @return
	 */
	public SessionContext getSessionContext(String clientID, String sessionID) {
		return getSessionContext(clientID, sessionID, null);
	}
	
	/**
	 * Returns the session with the specified ID.
	 * Request is used to pass parameter to SessionEvent.
	 * @param clientID
	 * @param sessionID
	 * @param request
	 * @return
	 */
	public SessionContext getSessionContext(String clientID, String sessionID, HttpServletRequest request) {
		
		SessionContainer container = sessionManager.get(clientID, sessionID);
		if (container != null) {
			
			if (container.getState() == SessionContainer.STATE_STORED) {
				sessionManager.deserialize(container);
			} else if (container.getState() == SessionContainer.STATE_DESTROYED) {
				throw new JWicException("Session is destroyed.");
			}
			container.access();
			SessionContext sc = container.getSessionContext();
			if (request.getMethod().equals("GET")) { // only fire reused event on "GET" requests.
				log.debug("SessionReused : " + sessionID);
				sc.fireEvent(new SessionEvent(request == null ? null : Compatibility.getParameterMap(request)), SessionContext.SESSION_REUSED);
			}
			return sc;
			
		} 
		return null;
		
	}
	
	/**
	 * Creates a new SessionContext with the application bean specified in the
	 * appProperties argument. The appProperties must contain the name and beanId
	 * of the application control.
	 * 
	 * Sample:<br>
	 * <pre> appid=myapp.id
	 *  name=app
	 *  control=myapp.AppControl </pre>
	 * 
	 * The request argument is optional. It can be <code>null</code> if you are creating an
	 * Application for test cases. In this case, the session is always handled as multisession.
	 * 
	 * @param appProperties
	 * @return
	 */
	public SessionContext createSessionContext(IApplicationSetup appSetup, Locale locale, TimeZone timeZone, HttpServletRequest request) {
		
		HttpSession session = request != null ? request.getSession() : null;
		String clientID = session != null ? session.getId() : "test"; // testenvironment
		
		if (session != null && session.getAttribute(ATTR_NOTIFIER) == null) {
			// Add a "listener" so we know when the session is closed.
			session.setAttribute(ATTR_NOTIFIER, new HttpSessionClosedListener(this, session.getId()));
		}
		
		SessionContext sc = null;
		if (appSetup.isSingleSession() && session != null) {
			SessionContainer container = sessionManager.getByAppID(clientID, appSetup.getName());
			if (container == null) {
				sc = setupSessionContext(appSetup, locale, timeZone, request);
			} else {
				// notify the session that it has been "reused"
				if (container.getState() == SessionContainer.STATE_STORED) {
					sessionManager.deserialize(container);
				}
				container.access();
				sc = container.getSessionContext();
				sc.fireEvent(new SessionEvent(Compatibility.getParameterMap(request)), SessionContext.SESSION_REUSED);
			}
			
		} else {
			sc = setupSessionContext(appSetup, locale, timeZone, request);
		}

		return sc;
	}
	
	/**
	 * Create a new SessionContext instance.
	 * @param appProperties
	 * @param locale
	 * @param timeZone 
	 * @param request
	 * @return
	 */
	private SessionContext setupSessionContext(IApplicationSetup appSetup, Locale locale, TimeZone timeZone, HttpServletRequest request) {

		log.debug("creating new SessionContext for application '" + appSetup.getName() + "'.");
		
		SessionContext sc = new SessionContext(appSetup, locale, timeZone);
		String clientID;
		if (request != null) {
			clientID = request.getSession().getId();
			HashMap<String, String[]> parameters = new HashMap<String, String[]>();
			parameters.putAll(Compatibility.getParameterMap(request));
			sc.setInitParameters(parameters);
		} else {
			clientID = "test";
		}
		sc.setClientId(clientID);

		IApplication app = appSetup.createApplication();
		sc.setApplication(app);
		SessionContainer container = sessionManager.create(clientID, appSetup.getName());
		boolean cleanUp = true;
		try {
			container.setSessionContext(sc);
			sc.setSessionId(container.getId());
			sc.setUserAgent(new UserAgentInfo(request));

			app.initialize(sc);
			Control root = app.createRootControl(sc);
			// push root control only if no control had been push during root creation
			if (sc.getTopControl() == null) {
				sc.pushTopControl(root);
			}
			sc.fireEvent(new SessionEvent(request == null ? null : Compatibility.getParameterMap(request)), SessionContext.SESSION_STARTED );
			cleanUp = false;
		} finally {
			if (cleanUp) {
				log.warn("Session was not created successfully!");
				sessionManager.remove(container);
			}
		}
		
		return sc;

	}
	/**
	 * Invoked by the SessionContext when the sessionContext is destroyed.
	 * The context is then removed from the singleSession map.
	 * @param context
	 */
	void sessionDestroyed(SessionContext context) {

		log.debug("Session " + context.getClientId() + " destroyed.");
		// remove from local single_session cache
		SessionContainer container = sessionManager.get(context.getClientId(), context.getSessionId());
		if (container != null) {
			sessionManager.remove(container);
		}
		
	}

	/**
	 * Setup the JWicRuntime from the jwic-setup.xml file. The setup
	 * defines the available renderer and global settings.
	 * @param in
	 */
	public void setupRuntime(InputStream stream) {
		
		try {
			SAXReader reader = new SAXReader();
			reader.setEntityResolver(new DTDEntityResolver(DTD_PUBLICID, DTD_SYSTEMID, DTD_RESOURCEPATH));
			reader.setIncludeExternalDTDDeclarations(false);
			
			Document document = reader.read(stream);
			
			readDocument(document);
			
			sessionManager = new SessionManager(getSavePath());
			sessionManager.setStoreTime(sessionStoreTime);
			
		} catch (NoClassDefFoundError ncdfe) {
			if (ncdfe.getMessage().indexOf("dom4j") != -1) {
				log.error("Can not read jwic-setup.xml: the dom4j library is not in the classpath.");
				throw new RuntimeException("Can not read jwic-setup.xml: the dom4j library is not in the classpath.", ncdfe);
			}
			log.error("Error reading jwic-setup.xml.", ncdfe);
			throw new RuntimeException("Error reading jwic-setup.xml: " + ncdfe, ncdfe);
		} catch (Exception e) {
			throw new RuntimeException("Error reading jwic-setup.xml: " + e, e);
		}

		
	}

	/**
	 * Read the setup document.
	 * @param document
	 */
	private void readDocument(Document document) {
		
		Element root = document.getRootElement();
		
		for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
			Element node = (Element)it.next();

			String nodeName = node.getName();
			// setup a velocity-engine
			if (nodeName.equals("velocity-engine")) {
				
				String veId = node.attribute("id").getValue();
				Properties prop = null;
				for (Iterator<?> pi = node.elementIterator(); pi.hasNext(); ) {
					Element node2 = (Element)pi.next();
					if (node2.getName().equals("properties")) {
						prop = XMLTool.getProperties(node2);
					}
				}
				// replace ${rootPath} with real path
				ConfigurationTool.insertRootPath(prop);
				VelocityEngine engine = new VelocityEngine();
				try {
					engine.init(prop);
				} catch (Exception e) {
					String msg = "Can not instanciate velocity engine '" + veId + "'";
					log.error(msg, e);
					throw new RuntimeException(msg + e, e);
				}
				velocityEngines.put(veId, engine);
				
			} else if (nodeName.equals("session-swap-time")) {
				sessionStoreTime = Integer.parseInt(node.getText());
			
			} else if (nodeName.equals("session-storage-path")) {
				setSavePath(ConfigurationTool.insertRootPath(node.getText()));
				
			} else if (nodeName.equals("renderer")) {
				String id = node.attribute("id").getValue();
				String type = node.attribute("type").getValue();
				String classname = node.attribute("classname").getValue();
				
				IControlRenderer renderer;
				try {
					renderer = (IControlRenderer) Class.forName(classname).newInstance();
				} catch (Exception e) {
					String msg = "Can not instanciate renderer '" + id + "'";
					log.error(msg, e);
					throw new RuntimeException(msg + e, e);
				}
				
				if (type.equals("velocity")) {
					Element nEngine = node.element("engine");
					if (nEngine != null) {
						String engineId = nEngine.getText();
						if (renderer instanceof BaseVelocityRenderer) {
							BaseVelocityRenderer vr = (BaseVelocityRenderer)renderer;
							VelocityEngine engine = velocityEngines.get(engineId);
							if (engine == null) {
								throw new RuntimeException("Specified velocity engine not found: " + engineId);
							}
							vr.setVelocityEngine(engine);
						} else {
							throw new RuntimeException("renderer " + id + " is specified as type 'velocity' but is not instance of BaseVelocityRenderer");
						}
					}
				} 
				renderers.put(id, renderer);
				
			}
		}
		
	}
	
	/**
	 * @return Returns the contextPath.
	 */
	public String getContextPath() {
		return contextPath;
	}
	/**
	 * @param contextPath The contextPath to set.
	 */
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	/**
	 * Returns the path where the serialized applications are stored.
	 * @return
	 */
	public String getSavePath() {
		return savePath;
	}
	
	/**
	 * Set the path where the serialized applications are stored.
	 * @param savePath
	 */
	public void setSavePath(String savePath) {
		if (savePath.endsWith("/") || savePath.endsWith("\\")) {
			this.savePath = savePath;
		} else {
			this.savePath = savePath + "/";
		}
	}
	
	/**
	 * @return Returns the rootPath.
	 */
	public String getRootPath() {
		return rootPath;
	}

	/**
	 * Set the root path of the WebApplication.
	 * @param savepath
	 * @return
	 */
	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	/**
	 * Destroy the runtime.
	 */
	public void destroy() {
		
		log.info("JWicRuntime.destroy()");
		sessionManager.destroy();
		initialized = false;
		singleton = null;
	}

}
