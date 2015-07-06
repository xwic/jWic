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
package de.jwic.base;

import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * Bean-style application setup.
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class ApplicationSetupBean implements IApplicationSetup {

	private static final long serialVersionUID = 1L;
	
	private String name = null;
	private String classname = null;
	private String rootControlName = "root";
	private String rootControlClassName = null;
	private boolean serializable = true;
	private boolean singleSession = false;
	private boolean requireAuthentication = false;
	private boolean useAjaxRendering = true;
	private Properties properties = new Properties();
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IApplicationSetup#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IApplicationSetup#getRootControlName()
	 */
	public String getRootControlName() {
		return rootControlName;
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IApplicationSetup#createRootControl()
	 */
	public IApplication createApplication() {
		
		try {
			if (classname != null) {
				return (IApplication)Class.forName(classname).newInstance();
			} 
			// create a dummy application that creates the root class. This is
			// for compatibility with jWic v2.x.
			IApplication app = new Application() {
				private static final long serialVersionUID = 1L;
				public Control createRootControl(IControlContainer container) {
					try {
						Class<?> clazz = Class.forName(rootControlClassName);
						Control control;
						Constructor<?> cstr = clazz.getConstructor(new Class[] { IControlContainer.class, String.class } );
						control = (Control)cstr.newInstance(new Object[] { container, rootControlName });
						return control;
					} catch (Exception e) {
						throw new ControlNotAvailableException("Can not create instance of '" + rootControlClassName + "'. Cause: " + e, e);
					}
				}
			};
			return app;
		} catch (Exception e) {
			throw new JWicException("Can not create application '" + classname + "':" + e, e); 
		}
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
		if (properties == null) {
			return null;
		}
		return properties.getProperty(key);
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
	
	public void setProperty(String key, String value) {
		if (properties == null) {
			properties = new Properties();
		}
		properties.setProperty(key, value);
	}
	/**
	 * @return Returns the rootControlClassName.
	 */
	public String getRootControlClassName() {
		return rootControlClassName;
	}
	/**
	 * @param rootControlClassName The rootControlClassName to set.
	 */
	public void setRootControlClassName(String rootControlClassName) {
		this.rootControlClassName = rootControlClassName;
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
	 * @param rootControlName The rootControlName to set.
	 */
	public void setRootControlName(String rootControlName) {
		this.rootControlName = rootControlName;
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
	 * Get the classname of the IApplication implementation.
	 * @return Returns the classname.
	 */
	public String getClassname() {
		return classname;
	}
	/**
	 * Set the classname of the IApplication implementation.
	 * @param classname The classname to set.
	 */
	public void setClassname(String classname) {
		this.classname = classname;
	}
}
