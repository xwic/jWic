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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import de.jwic.util.DTDEntityResolver;

/**
 * Loads the application setup from an xml file.
 *  
 * @author Florian Lippisch
 * @version $Revision: 1.9 $
 */
public class XmlApplicationSetup implements IApplicationSetup {

	public final static String PUBLICID = "-//jWic//DTD xwic 3.2//EN";
	public final static String SYSTEMID = "http://jwic.sourceforge.net/xwic-3.2.dtd";
	public final static String DTD_RESOURCEPATH = "/de/jwic/base/xwic.dtd";
	
	private final static long serialVersionUID = 6009335074727417446L;
	
	private final static String NODE_NAME = "name";
	private final static String NODE_CLASS = "class";
	private final static String NODE_ROOTCONTROL = "rootcontrol";
	private final static String NODE_SERIALIZABLE = "serializable";
	private final static String NODE_SINGLESESSION = "singlesession";
	private final static String NODE_USEAJAX = "useAjaxRendering";
	private final static String NODE_REQUIREAUTH = "requireauth";
	private final static String NODE_PROPERTY = "property";
	private final static String ATTR_NAME = "name";
	private final static String ATTR_CLASSNAME = "classname";
	
	
	/** The name of the application */
	private String name = null;
	/** The classname of the root control */
	private String rootControlClass = null;
	/** The name of the root control. Default is "root" */
	private String rootControlName = "root";
	/** The name of the application class */
	private String appClassName = null;
	
	private boolean serializable = true;
	private boolean singleSession = false;
	private boolean requireAuth = false;
	private boolean useAjaxRendering = true;
	private Map<String, String> properties = null;

	/**
	 * Create an ApplicationSetup from the specified file.
	 * @param filename
	 */
	public XmlApplicationSetup(String filename) {
		
		try {
			SAXReader reader = new SAXReader();
			reader.setEntityResolver(new DTDEntityResolver(PUBLICID, SYSTEMID, DTD_RESOURCEPATH));
			reader.setIncludeExternalDTDDeclarations(false);
			
			Document document = reader.read(new File(filename));
			
			readDocument(document);
			
		} catch (Exception e1) {
			throw new RuntimeException("Error reading applicationSetup: " + e1, e1);
		}
		
	}
	/**
	 * Create an ApplicationSetup from the specified stream.
	 * @param filename
	 */
	public XmlApplicationSetup(InputStream stream) {
		
		
		try {
			SAXReader reader = new SAXReader();
			reader.setEntityResolver(new DTDEntityResolver(PUBLICID, SYSTEMID, DTD_RESOURCEPATH));
			reader.setIncludeExternalDTDDeclarations(false);
			
			Document document = reader.read(stream);
			
			readDocument(document);
			
		} catch (Exception e1) {
			throw new RuntimeException("Error reading applicationSetup: " + e1, e1);
		}
		
		
	}

	
	/**
	 * @param source
	 * @throws IOException
	 */
	public XmlApplicationSetup(InputSource source) {
		
		try {
			SAXReader reader = new SAXReader();
			reader.setEntityResolver(new DTDEntityResolver(PUBLICID, SYSTEMID, DTD_RESOURCEPATH));
			reader.setIncludeExternalDTDDeclarations(false);
			
			Document document = reader.read(source);
			
			readDocument(document);
			
		} catch (Exception e1) {
			throw new RuntimeException("Error reading applicationSetup: " + e1, e1);
		}
	}
	/**
	 * Read the document content.
	 * @param document
	 */
	private void readDocument(Document document) {
		
		Element root = document.getRootElement();
		for (Iterator<?> it = root.elementIterator(); it.hasNext(); ) {
			Element node = (Element)it.next();
			String nodeName = node.getName();
			if (nodeName.equals(NODE_NAME)) {
				name = node.getText();
			} else if (nodeName.equals(NODE_CLASS)) {
				appClassName = node.getText();
			} else if (nodeName.equals(NODE_ROOTCONTROL)) {
				rootControlName = node.attribute(ATTR_NAME).getValue();
				rootControlClass = node.attribute(ATTR_CLASSNAME).getValue();
			} else if (nodeName.equals(NODE_SERIALIZABLE)) {
				String s = node.getText();
				serializable = "true".equals(s) | "1".equals(s) | "on".equals(s);
			} else if (nodeName.equals(NODE_SINGLESESSION)) {
				String s = node.getText();
				singleSession = "true".equals(s) | "1".equals(s) | "on".equals(s);
			} else if (nodeName.equals(NODE_REQUIREAUTH)) {
				String s = node.getText();
				requireAuth = "true".equals(s) | "1".equals(s) | "on".equals(s);
			} else if (nodeName.equals(NODE_USEAJAX)) {
				String s = node.getText();
				useAjaxRendering = "true".equals(s) | "1".equals(s) | "on".equals(s);
			} else if (nodeName.equals(NODE_PROPERTY)) {
				if (properties == null) {
					properties = new HashMap<String, String>();
				}
				properties.put(node.attribute(ATTR_NAME).getValue(), node.getText());
			}
		}
		
		
	}
	

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

	/**
	 * Returns the classname of the root control.
	 * @return
	 */
	public String getRootControlClass() {
		return rootControlClass;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IApplicationSetup#createApplication()
	 */
	public IApplication createApplication() {
		
		try {
			if (appClassName != null) {
				return (IApplication)Class.forName(appClassName).newInstance();
			} 
			// create a dummy application that creates the root class. This is
			// for compatibility with jWic v2.x.
			IApplication app = new Application() {
				private static final long serialVersionUID = 1L;
				public Control createRootControl(IControlContainer container) {
					try {
						Class<?> clazz = Class.forName(rootControlClass);
						Control control;
						Constructor<?> cstr = clazz.getConstructor(new Class[] { IControlContainer.class, String.class } );
						control = (Control)cstr.newInstance(new Object[] { container, rootControlName });
						return control;
					} catch (Exception e) {
						throw new ControlNotAvailableException("Can not create instance of '" + rootControlClass + "'. Cause: " + e, e);
					}
				}
			};
			return app;
		} catch (Exception e) {
			throw new JWicException("Can not create application '" + appClassName + "':" + e, e); 
		}
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IApplicationSetup#isRequireAuthentication()
	 */
	public boolean isRequireAuthentication() {
		return requireAuth;
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
	 * @see de.jwic.base.IApplicationSetup#getProperty(java.lang.String)
	 */
	public String getProperty(String key) {
		if (properties == null) {
			return null;
		}
		return properties.get(key);
	}
	/* (non-Javadoc)
	 * @see de.jwic.base.IApplicationSetup#isUseAjaxRendering()
	 */
	public boolean isUseAjaxRendering() {
		return useAjaxRendering;
	}
	/**
	 * @return Returns the appClassName.
	 */
	public String getAppClassName() {
		return appClassName;
	}

}

	