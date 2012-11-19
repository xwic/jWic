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
 * de.jwic.base.SessionContext
 * $Id: SessionContext.java,v 1.13 2012/01/19 14:11:33 adrianionescu12 Exp $
 */
package de.jwic.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TimeZone;

import org.apache.commons.lang.StringEscapeUtils;

import de.jwic.events.SessionEvent;
import de.jwic.events.SessionListener;
/**
 * Holds the configuration data for a specific application session. The lifecycle of
 * the SessionContext is managed by the JWicRuntime.
 *  
 * @author Florian Lippisch
 */
public class SessionContext implements IControlContainer, Serializable {

	private static final long serialVersionUID = 6009335074727417445L;
	
	final static int AFTER_DESERIALIZATION = 0;
	final static int BEFORE_SERIALIZATION = 1;
	final static int SESSION_REUSED = 2;
	final static int SESSION_STARTED = 3;
	final static int SESSION_STOPPED = 4;
	
	public final static int STATE_NORMAL = 0;
	public final static int STATE_DESTROYED = 1;
	public final static int STATE_STORED = 2;
	
	public final static String PROP_AUTHENTICATION = "authentication";
	
	private Map<String, Control> controls = new HashMap<String, Control>();
	private Map<String, Control> layer = new HashMap<String, Control>();
	
	private String strRedirectToURL = null;
	private boolean bolDoExit = false;
	private String strCallBackURL = null;

	private String strExitURL = "";
	private String strTopControlID = "";
	private Stack<Control> stkTopControls = new Stack<Control>();
	
	private IApplication application = null;
	private IApplicationSetup appSetup = null;
	private Locale locale = null;
	private TimeZone timeZone;
	
	private String clientId = null;
	private String sessionId = null;
	
	private String baseStyle = "default";
	
	private Map<String, String[]> initParameter = null;
	private List<SessionListener> listeners = null;
	private long requestTicket = 0;
	
	private Map<String, Long> layerTickets = new HashMap<String, Long>();
	
	private boolean requireRedraw = true;
	
	private int state = STATE_NORMAL;
	private UserAgentInfo userAgent = null;
	
	private MouseEvent mouseEvent = null;
	
	private List<String> scriptQueue = new ArrayList<String>();
	
	private transient IActionController actionController = new DefaultActionController();

	
	/**
	 * Creates a blank new SessionContext. The contstructor is just visible to
	 * the package.
	 * @param timeZone 
	 */
	SessionContext(IApplicationSetup appSetup, Locale locale, TimeZone timeZone) {
		this.appSetup = appSetup;
		this.locale = locale;
		this.timeZone = timeZone;
		
		if (locale == null) {
			throw new NullPointerException("Locale must not be null.");
		}
		if (timeZone == null) {
			throw new NullPointerException("TimeZone must not be null");
		}
		if (appSetup == null) {
			throw new NullPointerException("ApplicationSetup must not be null");
		}
		
	}
	
	/**
	 * Adds a SessionListener to this SessionContext.
	 * @param listener
	 */
	public void addSessionListener(SessionListener listener) {
		if (listeners == null) {
			listeners = new ArrayList<SessionListener>();
		}
		listeners.add(listener);
	}
	/**
	 * Adds a SessionListener to this SessionContext.
	 * @param listener
	 */
	public void removeSessionListener(SessionListener listener) {
		if (listeners != null) {
			listeners.remove(listener);
		}
	}
	/**
	 * Fire the specified event/method.
	 * @param event
	 * @param method
	 */
	void fireEvent(SessionEvent event, int method) {
		
		if (method == AFTER_DESERIALIZATION) {
			// recreate transient objects.
			actionController = new DefaultActionController();
		}
		
		event.setSessionContext(this);
		
		if (listeners != null) {
			for (Iterator<SessionListener> it = listeners.iterator(); it.hasNext(); ) {
				SessionListener listener = it.next();
				switch (method) {
					case AFTER_DESERIALIZATION:
						listener.afterDeserialization(event);
						break;
					case BEFORE_SERIALIZATION:
						listener.beforeSerialization(event);
						break;
					case SESSION_REUSED:
						listener.sessionReused(event);
						break;
					case SESSION_STARTED :
						listener.sessionStarted(event);
						break;
					case SESSION_STOPPED :
						listener.sessionStopped(event);
						break;
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IControlContainer#adopt(de.jwic.base.Control)
	 */
	public void adopt(Control control, String name) {
		
		// notify the old container that the control has been moved (remove it)
		IControlContainer oldParent = control.getContainer();
		if (oldParent == this) {
			return; // nothing to do
		}
		oldParent.unregisterControl(control);
		registerControl(control, name);
		
	}
	
	/**
	 * Add a new FrameControl to this context. The name can not contain '.', ' ' or '_' characters.
	 */
	public void registerControl(Control control, String name) {

		if (control.getContainer() != null) {
			throw new JWicException("The control has already been registerd to a container.");
		}

		if (name == null) {
			int idx = controls.size();
			name = "c" + idx;
			while (controls.containsKey(name)) {
				idx++;
				name = "c" + idx;
			}
		}
			
		control.setSessionContext(this);
		control.setContainer(this);
		control.setName(name);
		control.setControlID(control.getName());
		controls.put(control.getName(), control);
			
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IControlContainer#unregisterControl(de.jwic.base.Control)
	 */
	public void unregisterControl(Control control) {
		
		if (control.getContainer() == this) {
			controls.remove(control.getName());
		}

	}
	
	/**
	 * Add a link to a control. A link can be used to render a control
	 * in a seperate frame.
	 *  
	 * @param linkID
	 * @param control
	 */
	public void addLayer(String layerID, Control control) {
		layer.put(layerID, control);
		layerTickets.put(layerID, new Long(0));
	}
	/**
	 * Stop the frame from redirecting to a previous specified URL.
	 */
	public void clearRedirect() {
	
		strRedirectToURL = null;
		
	}
	
	/**
	 * Remove all controls in the context and destroy them.
	 *
	 */
	public void destroy() {
		
		application.preDestroy();
		if (state != STATE_DESTROYED) {
			fireEvent(new SessionEvent(null), SESSION_STOPPED );
			state = STATE_DESTROYED;
			for (Iterator<Control> it = controls.values().iterator(); it.hasNext(); ) {
				Control control = it.next();
				it.remove();
				control.destroy();
			}
		}
		JWicRuntime.getJWicRuntime().sessionDestroyed(this);
		application.postDestroy();
		
	}
	
	/**
	 * Returns the IApplicationSetup this session is based upon.
	 * @return
	 */
	public IApplicationSetup getApplicationSetup() {
		return appSetup;
	}
	
	/**
	 * Get the URL that will display the frame/session in the current state.
	 * @return java.lang.String
	 */
	public java.lang.String getCallBackURL() {
		return strCallBackURL;
	}
	/**
	 * Returns the FrameControl specified by its name.
	 * @return FrameControl
	 */
	public Control getControl(String ctrlName) {
		return controls.get(ctrlName);
	}
	/**
	 * Returns the FrameControl specified by a link.
	 * @return FrameControl
	 */
	public Control getControlByLayerID(String layerID) {
		return layer.get(layerID);
	}
	/**
	 * Returns an Iterator for all FrameControl objects.
	 */
	public Iterator<Control> getControls() {
		return controls.values().iterator();
	}
	
	/**
	 * Returns the URL a redirect should be sent to.
	 */
	public String getRedirectToURL() {
	
		return strRedirectToURL;	
		
	}
	/**
	 * Returns the control that is on top of the stack of visible controls.
	 * @return The top control.
	 */
	public Control getTopControl() {
		if (stkTopControls.empty()) {
			return null;
		}
		return stkTopControls.lastElement();
	}
	
	/**
	 * Returns the id of the control that is currently renderd.
	 * @return java.lang.String
	 */
	public String getTopControlID() {
		return strTopControlID;
	}
	/**
	 * Returns the value for the specified key in the application 
	 * properties.
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String getProperty(String key, String defaultValue) {
		String value = appSetup.getProperty(key); 
		return value == null ? defaultValue : value;
	}
	
	/**
	 * Returns true if the frameset should be left and the data should be purged.
	 */
	public boolean isDoExit() {
	
		return bolDoExit;	
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.jwic.base.IControlContainer#isRenderingRelevant(de.jwic.base.Control)
	 */
	public boolean isRenderingRelevant(Control childControl) {
		return true;
	}

	/**
	 * Remove the control from the stack of pushed top controls.
	 * @param control
	 */
	void removeTopControl(Control control) {
		if (stkTopControls.remove(control)) {
			// control was pushed
			if (!stkTopControls.empty()) {
				Control fctrl = stkTopControls.lastElement();
				strTopControlID = fctrl.getControlID();
			} else {
				strTopControlID = null;
			}
		}
	}
	/**
	 * Remove the top control from the stack of visible controls.
	 *
	 */
	public void popTopControl() {
		Control frameControl = getTopControl();
		stkTopControls.pop();
		if (!stkTopControls.empty()) {
			Control fctrl = stkTopControls.lastElement();
			strTopControlID = fctrl.getControlID();
			if (frameControl instanceof Page && fctrl instanceof Page) {
				((Page)frameControl).setClientSettings((Page)fctrl);
			}
		} else {
			strTopControlID = null;
		}
		setRequireRedraw(true);
	}
	
	/**
	 * Push a control on the stack of visible controls. The top control
	 * (including it's childs) is rendered only. Use popTopControl() to 
	 * return to the previous control.
	 * @param frameControl
	 */
	public void pushTopControl(Control frameControl) {
		if (frameControl instanceof Page && getTopControl() instanceof Page) {
			((Page)frameControl).setClientSettings((Page)getTopControl());
		}
		stkTopControls.push(frameControl);
		strTopControlID = frameControl.getControlID();	
		frameControl.setVisible(true);
		setRequireRedraw(true);
	}
	
	/**
	 * Sends a redirect to the specified URL with the next page update
	 * sent to the client. If the isExit flag is true, all data is purged.
	 * 
	 * @param sURL java.lang.String
	 * @param isExit boolean
	 */
	public void redirectTo(String sURL, boolean isExit) {
	
		strRedirectToURL = sURL;
		bolDoExit = isExit;	
		setRequireRedraw(true);
		
	}
	
	/**
	 * Remove a control from the container. 
	 */
	public void removeControl(String name) {
		Control control = controls.get(name);
		if (control != null) {
			unregisterControl(control);
			control.destroy();
		}
	}
	
	/**
	 * Remove the specified layer.
	 * @param layerId
	 */
	public void removeLayer(String layerId) {
		layer.remove(layerId);
	}
	
	/**
	 * Set the URL that will display the frame/session in the current state.
	 * @param newCallBackURL java.lang.String
	 */
	public void setCallBackURL(java.lang.String newCallBackURL) {
		strCallBackURL = newCallBackURL;
	}

	/**
	 * Get a control by its control ID.
	 * @param string
	 * @return
	 */
	public Control getControlById(String ctrlID) throws ControlNotFoundException {
		
		Control control = null;
		StringTokenizer stk = new StringTokenizer(ctrlID, ".");
		IControlContainer container = this;
		while (stk.hasMoreTokens()) {
			String name = stk.nextToken();
			control = container.getControl(name);
			if (stk.hasMoreTokens()) {
				if (control == null || !(control instanceof IControlContainer))
					throw new ControlNotFoundException(ctrlID);
				container = (IControlContainer)control;
			}
		}
		if (control == null)
			throw new ControlNotFoundException(ctrlID);
		return control;
	}

	/**
	 * Returns the URL where the session context redirects to when the application exits.
	 * @return
	 */
	public String getExitURL() {
		return strExitURL;
	}

	/**
	 * Set the URL where the session context redirects to when the application exits.
	 * @param string
	 */
	public void setExitURL(String string) {
		strExitURL = string;
	}
	/**
	 * Exit the session and clean up all controls.
	 * @throws AgoraException
	 */
	public void exit()  {
		if (strExitURL != null && strExitURL.length() != 0)
			redirectTo(strExitURL, true);
		else {
			bolDoExit = true;
		}
		setRequireRedraw(true);
	}

	/**
	 * @return Returns the actionController.
	 */
	public IActionController getActionController() {
		return actionController;
	}
	/**
	 * @param actionController The actionController to set.
	 */
	public void setActionController(IActionController actionController) {
		this.actionController = actionController;
	}
	/**
	 * @return Returns the locale.
	 */
	public Locale getLocale() {
		return locale;
	}
	/**
	 * @return Returns the sessionId.
	 */
	public String getClientId() {
		return clientId;
	}
	/**
	 * @param sessionId The sessionId to set.
	 */
	void setClientId(String clientId) {
		this.clientId = clientId;
	}
	/**
	 * Returns the value of a parameter that was specified when the application
	 * has been started.
	 * @param key
	 * @return
	 */
	public String getInitParameter(String key) {
		if (initParameter != null) {
			String[] str = initParameter.get(key);
			if (str != null) {
				return str[0];
			} 
		}
		return null;
	}
	/**
	 * Returns a map of parameters that have been specified when the application
	 * has been started.
	 * @return Returns the initParameter.
	 */
	public Map<String, String[]> getInitParameters() {
		return initParameter;
	}
	/**
	 * @param initParameter The initParameter to set.
	 */
	void setInitParameters(Map<String, String[]> initParameter) {
		this.initParameter = initParameter;
	}
	/**
	 * @return Returns the state.
	 */
	public int getState() {
		return state;
	}
	
	/**
	 * Returns the current request ticket number.
	 * @return Returns the requestTicket.
	 */
	public long getRequestTicket() {
		return requestTicket;
	}
	
	/**
	 * Returns the ticket number for the specified layer.
	 * @param layerId
	 * @return
	 */
	public long getRequestTicket(String layerId) {
		if (layerId == null || layerId.length() == 0) {
			return requestTicket;
		}
		Long l = layerTickets.get(layerId);
		if (l == null) {
			return 0;
		}
		return l.longValue();
	}
	
	/**
	 * Validates if the ticket number equals the expected ticket number.
	 * If the numbers are equal, true is returned and the request ticket 
	 * number is increased by one.
	 * @param ticket
	 * @return
	 */
	public boolean validateTicket(long ticket) {
		if (ticket == requestTicket) {
			if (requestTicket == Long.MAX_VALUE) {
				requestTicket = 0;
			} else { 
				requestTicket++;
			}
			return true;
		} 
		return false;
	}
	/**
	 * Validates if the ticket number of the specified layerId equals the expected ticket number.
	 * If the numbers are equal, true is returned and the request ticket 
	 * number is increased by one.
	 * @param ticket
	 * @return
	 */
	public boolean validateTicket(long ticket, String layerId) {
		if (layerId == null || layerId.length() == 0) {
			return validateTicket(ticket);
		}
		Long lngTicket = layerTickets.get(layerId);
		long lTicket = lngTicket == null ? 0 : lngTicket.longValue();
		
		if (ticket == lTicket) {
			if (lTicket == Long.MAX_VALUE) {
				lTicket = 0;
			} else { 
				lTicket++;
			}
			layerTickets.put(layerId, new Long(lTicket));
			return true;
		} 
		return false;
	}	
	/**
	 * Returns true if the page content has been changed since the last rendering
	 * and must be rendered again to reflect the last changes.
	 * @return Returns the requireRedraw.
	 */
	public boolean isRequireRedraw() {
		return requireRedraw;
	}
	/**
	 * Set to true if the page content need to be rendered again to reflect the changes
	 * of the application since it was last rendered. 
	 * @param requireRedraw The requireRedraw to set.
	 */
	public void setRequireRedraw(boolean requireRedraw) {
		this.requireRedraw = requireRedraw;
	}
	/**
	 * @return Returns the application.
	 */
	public IApplication getApplication() {
		return application;
	}
	/**
	 * @param application The application to set.
	 */
	void setApplication(IApplication application) {
		this.application = application;
	}
	/**
	 * Set the Locale for this session.
	 * @param locale
	 */
	public void setLocale(Locale locale) {
		if (locale == null) {
			throw new NullPointerException("Locale must not be null");
		}
		this.locale = locale;
	}

	/**
	 * @return Returns the sessionId.
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId The sessionId to set.
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IControlContainer#getSessionContext()
	 */
	public SessionContext getSessionContext() {
		return this;
	}
	/**
	 * Returns Stack of pushed Control's.
	 * @return
	 */
	public Stack<Control> getTopControls() {
		return stkTopControls;
	}

	/**
	 * @return Returns the userAgent.
	 */
	public UserAgentInfo getUserAgent() {
		return userAgent;
	}

	/**
	 * @param userAgent The userAgent to set.
	 */
	void setUserAgent(UserAgentInfo userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * @return the timeZone
	 */
	public TimeZone getTimeZone() {
		return timeZone;
	}

	/**
	 * @param timeZone the timeZone to set
	 */
	public void setTimeZone(TimeZone timeZone) {
		if (timeZone == null) {
			throw new NullPointerException("TimeZone must not be null");
		}
		this.timeZone = timeZone;
	}

	/**
	 * @return the mouseEvent, null if none exists.
	 */
	public MouseEvent getMouseEvent() {
		return mouseEvent;
	}

	/**
	 * @param mouseEvent the MouseEvent to set
	 */
	public void setMouseEvent(MouseEvent mouseEvent) {
		this.mouseEvent = mouseEvent;
	}

	/**
	 * @return the baseStyle
	 */
	public String getBaseStyle() {
		return baseStyle;
	}

	/**
	 * The base style defines the style based for the controls. Each (standard) control
	 * is using this base style as part of its CSS class.
	 * 
	 * @param baseStyle the baseStyle to set
	 */
	public void setBaseStyle(String baseStyle) {
		this.baseStyle = baseStyle;
	}

	/**
	 * Returns the list of queued script commands. These are executed with the next regular
	 * page update.
	 * @return the scriptQueue
	 */
	public List<String> getScriptQueue() {
		return Collections.unmodifiableList(scriptQueue);
	}
	
	/**
	 * Clear the queue.
	 */
	public void clearScriptQueue() {
		scriptQueue.clear();
	}
	
	/**
	 * Adds a script that is executed after the next page update.
	 * @param script
	 */
	public void queueScriptCall(String script) {
		scriptQueue.add(script);
	}

	/**
	 * Displays a notification message.
	 * @param message
	 */
	public void notifyMessage(String message) {
		queueScriptCall("JWic.ui.Notify.display('" + StringEscapeUtils.escapeJavaScript(message) + "');");
	}

	/**
	 * Displays a notification message with a custom css clazz.
	 * @param message
	 */
	public void notifyMessage(String message, String cssClazz) {
		queueScriptCall("JWic.ui.Notify.display('" + StringEscapeUtils.escapeJavaScript(message) + "', '" + cssClazz + "');");
	}

	/**
	 * Displays a notification message with a custom css clazz and a custom delay.
	 * @param message
	 */
	public void notifyMessage(String message, String cssClazz, double duration, double delay) {
		queueScriptCall("JWic.ui.Notify.display('" + StringEscapeUtils.escapeJavaScript(message) + "', '" + cssClazz + "', " + duration + ", " + delay + ");");
	}

}
