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
 * de.jwic.web.UploadHttpServletRequest
 * $Id: UploadHttpServletRequest.java,v 1.2 2008/09/17 15:19:38 lordsam Exp $
 */
package de.jwic.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The UploadHttpServletRequest replaces the normal HttpServletRequest after data
 * has been send and processed in multipart format. The getParameter() method will
 * then return data that has been read from the stream.
 *  
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */

public class UploadHttpServletRequest implements HttpServletRequest {

	private HttpServletRequest parentRequest = null;
	private Map<String, List<String>> paramMap = null;
	
	/**
	 * Wrapper to return an Enumeration instead of an Iterator.
	 * 
	 * @author Florian Lippisch
	 * @version $Revision: 1.2 $
	 */
	@SuppressWarnings("rawtypes")
	private class Iterator2Enumerator implements Enumeration {
		
		private Iterator<String> it;
		public Iterator2Enumerator(Iterator<String> iterator) {
			it = iterator;
		}
		public boolean hasMoreElements() {
			return it.hasNext();
		}

		public Object nextElement() {
			return it.next();
		}
	}
	
	/**
	 * Constructor.
	 * @param parent
	 */
	public UploadHttpServletRequest(HttpServletRequest parent, Map<String, List<String>> parameterMap) {
		parentRequest = parent;
		paramMap = parameterMap;
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getAuthType()
	 */
	public String getAuthType() {
		return parentRequest.getAuthType();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getCookies()
	 */
	public Cookie[] getCookies() {
		return parentRequest.getCookies();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getDateHeader(java.lang.String)
	 */
	public long getDateHeader(String arg0) {
		return parentRequest.getDateHeader(arg0);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getHeader(java.lang.String)
	 */
	public String getHeader(String arg0) {
		return parentRequest.getHeader(arg0);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getHeaders(java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	public Enumeration getHeaders(String arg0) {
		return parentRequest.getHeaders(arg0);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getHeaderNames()
	 */
	@SuppressWarnings("rawtypes")
	public Enumeration getHeaderNames() {
		return parentRequest.getHeaderNames();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getIntHeader(java.lang.String)
	 */
	public int getIntHeader(String arg0) {
		return parentRequest.getIntHeader(arg0);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getMethod()
	 */
	public String getMethod() {
		return parentRequest.getMethod();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getPathInfo()
	 */
	public String getPathInfo() {
		return parentRequest.getPathInfo();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getPathTranslated()
	 */
	public String getPathTranslated() {
		return parentRequest.getPathTranslated();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getContextPath()
	 */
	public String getContextPath() {
		return parentRequest.getContextPath();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getQueryString()
	 */
	public String getQueryString() {
		return parentRequest.getQueryString();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getRemoteUser()
	 */
	public String getRemoteUser() {
		return parentRequest.getRemoteUser();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#isUserInRole(java.lang.String)
	 */
	public boolean isUserInRole(String arg0) {
		return parentRequest.isUserInRole(arg0);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getUserPrincipal()
	 */
	public Principal getUserPrincipal() {
		return parentRequest.getUserPrincipal();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getRequestedSessionId()
	 */
	public String getRequestedSessionId() {
		return parentRequest.getRequestedSessionId();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getRequestURI()
	 */
	public String getRequestURI() {
		return parentRequest.getRequestURI();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getRequestURL()
	 */
	public StringBuffer getRequestURL() {
		return parentRequest.getRequestURL();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getServletPath()
	 */
	public String getServletPath() {
		return parentRequest.getServletPath();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getSession(boolean)
	 */
	public HttpSession getSession(boolean arg0) {
		return parentRequest.getSession(arg0);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getSession()
	 */
	public HttpSession getSession() {
		return parentRequest.getSession();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdValid()
	 */
	public boolean isRequestedSessionIdValid() {
		return parentRequest.isRequestedSessionIdValid();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromCookie()
	 */
	public boolean isRequestedSessionIdFromCookie() {
		return parentRequest.isRequestedSessionIdFromCookie();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromURL()
	 */
	public boolean isRequestedSessionIdFromURL() {
		return parentRequest.isRequestedSessionIdFromURL();
	}

	/**
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromUrl()
	 * @deprecated
	 */
	public boolean isRequestedSessionIdFromUrl() {
		return parentRequest.isRequestedSessionIdFromUrl();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getAttribute(java.lang.String)
	 */
	public Object getAttribute(String arg0) {
		return parentRequest.getAttribute(arg0);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getAttributeNames()
	 */
	@SuppressWarnings("rawtypes")
	public Enumeration getAttributeNames() {
		return parentRequest.getAttributeNames();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getCharacterEncoding()
	 */
	public String getCharacterEncoding() {
		return parentRequest.getCharacterEncoding();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#setCharacterEncoding(java.lang.String)
	 */
	public void setCharacterEncoding(String arg0)
			throws UnsupportedEncodingException {
		parentRequest.setCharacterEncoding(arg0);

	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getContentLength()
	 */
	public int getContentLength() {
		return parentRequest.getContentLength();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getContentType()
	 */
	public String getContentType() {
		return parentRequest.getContentType();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getInputStream()
	 */
	public ServletInputStream getInputStream() throws IOException {
		return parentRequest.getInputStream();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getParameter(java.lang.String)
	 */
	public String getParameter(String arg0) {
		List<String> result = paramMap.get(arg0);
		if(result == null)
			return null;
		return result.get(0);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getParameterNames()
	 */
	@SuppressWarnings("rawtypes")
	public Enumeration getParameterNames() {
		return new Iterator2Enumerator(paramMap.keySet().iterator());
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getParameterValues(java.lang.String)
	 */
	public String[] getParameterValues(String key) {
		if (paramMap.containsKey(key)) {
			List<String> values = paramMap.get(key);
			return values.toArray(new String[values.size()]);
		}
		return new String[0];
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getParameterMap()
	 */
	public Map<String, String> getParameterMap() {
		Map<String, String> result = new HashMap<String, String>();
		for (String key : paramMap.keySet()) {
			result.put(key, paramMap.get(key).get(0));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getProtocol()
	 */
	public String getProtocol() {
		return parentRequest.getProtocol();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getScheme()
	 */
	public String getScheme() {
		return parentRequest.getScheme();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getServerName()
	 */
	public String getServerName() {
		return parentRequest.getServerName();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getServerPort()
	 */
	public int getServerPort() {
		return parentRequest.getServerPort();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getReader()
	 */
	public BufferedReader getReader() throws IOException {
		return parentRequest.getReader();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getRemoteAddr()
	 */
	public String getRemoteAddr() {
		return parentRequest.getRemoteAddr();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getRemoteHost()
	 */
	public String getRemoteHost() {
		return parentRequest.getRemoteHost();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#setAttribute(java.lang.String, java.lang.Object)
	 */
	public void setAttribute(String arg0, Object arg1) {
		parentRequest.setAttribute(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#removeAttribute(java.lang.String)
	 */
	public void removeAttribute(String arg0) {
		parentRequest.removeAttribute(arg0);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getLocale()
	 */
	public Locale getLocale() {
		return parentRequest.getLocale();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getLocales()
	 */
	@SuppressWarnings("rawtypes")
	public Enumeration getLocales() {
		return parentRequest.getLocales();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#isSecure()
	 */
	public boolean isSecure() {
		return parentRequest.isSecure();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getRequestDispatcher(java.lang.String)
	 */
	public RequestDispatcher getRequestDispatcher(String arg0) {
		return parentRequest.getRequestDispatcher(arg0);
	}

	/** (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getRealPath(java.lang.String)
	 * @deprecated
	 */
	public String getRealPath(String arg0) {
		return parentRequest.getRealPath(arg0);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getRemotePort()
	 */
	public int getRemotePort() {
		return parentRequest.getRemotePort();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getLocalName()
	 */
	public String getLocalName() {
		return parentRequest.getLocalName();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getLocalAddr()
	 */
	public String getLocalAddr() {
		return parentRequest.getLocalAddr();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getLocalPort()
	 */
	public int getLocalPort() {
		return parentRequest.getLocalPort();
	}

}

