/*
 * Copyright 2005 jWic Group (http://www.jwic.de)
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
 * de.jwic.spring.IocApplicationSetup
 * Created on 23.05.2005
 * $Id: IocApplicationSetup.java,v 1.2 2006/08/14 09:35:00 lordsam Exp $
 */
package de.jwic.spring;

import java.util.Properties;

import de.jwic.base.IApplication;
import de.jwic.base.IApplicationSetup;

/**
 * Inversion-of-Control based implementation of the ApplicationSetup. This class
 * may be used in an spring component container. 
 * Note that the application object must not be a singleton!
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class IocApplicationSetup implements IApplicationSetup {

	private static final long serialVersionUID = 1L;

	private String name = null;
	private IApplication application = null;
	private boolean serializable = true;
	private boolean singleSession = false;
	private boolean requireAuthentication = false;
	private boolean useAjaxRendering = true;
	private Properties properties = null;
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IApplicationSetup#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IApplicationSetup#createApplication()
	 */
	public IApplication createApplication() {
		return application;
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IApplicationSetup#isSerializable()
	 */
	public boolean isSerializable() {
		return serializable;
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IApplicationSetup#isSingleSession()
	 */
	public boolean isSingleSession() {
		return singleSession;
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IApplicationSetup#isRequireAuthentication()
	 */
	public boolean isRequireAuthentication() {
		return requireAuthentication;
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IApplicationSetup#getProperty(java.lang.String)
	 */
	public String getProperty(String key) {
		if (properties != null) {
			return properties.getProperty(key);
		}
		return null;
	}

	/**
	 * @return Returns the properties.
	 */
	public Properties getProperties() {
		return properties;
	}
	/**
	 * @param properties The properties to set.
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param requireAuthentication The requireAuthentication to set.
	 */
	public void setRequireAuthentication(boolean requireAuthentication) {
		this.requireAuthentication = requireAuthentication;
	}
	/**
	 * @param serializable The serializable to set.
	 */
	public void setSerializable(boolean serializable) {
		this.serializable = serializable;
	}
	/**
	 * @param singleSession The singleSession to set.
	 */
	public void setSingleSession(boolean singleSession) {
		this.singleSession = singleSession;
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IApplicationSetup#isUseAjaxRendering()
	 */
	public boolean isUseAjaxRendering() {
		return useAjaxRendering;
	}
	/**
	 * @param useAjaxRendering The useAjaxRendering to set.
	 */
	public void setUseAjaxRendering(boolean useAjaxRendering) {
		this.useAjaxRendering = useAjaxRendering;
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
	public void setApplication(IApplication application) {
		this.application = application;
	}
}
