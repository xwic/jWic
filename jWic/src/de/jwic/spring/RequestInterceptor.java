/*
 * Copyright 2005 jWic Group (http://www.jwic.de)
 *
 * de.jwic.spring.RequestInterceptor
 * Created on 29.08.2005
 *
 */

package de.jwic.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Makes the HttpServletRequest available for classes using a static TheadLocal.
 * @author Florian Lippisch
 */
public class RequestInterceptor implements HandlerInterceptor {

	private static ThreadLocal<HttpServletRequest> requestStore = new ThreadLocal<HttpServletRequest>();

	/**
	 * Returns the HttpServletRequest for the current thread.
	 * @return
	 * @throws IllegalStateException
	 */
	public static HttpServletRequest getRequest() throws IllegalStateException {
		HttpServletRequest request = requestStore.get();
		if (request == null) {
			throw new IllegalStateException("HttpServletRequest has not been stored!");
		}
		return request;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		requestStore.set(request);
		return true; 	// process next cinterceptors in the chain.
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
		
		requestStore.set(null);	// clear
		
	}
	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		// nothing to do here
		
	}
	
	
}
