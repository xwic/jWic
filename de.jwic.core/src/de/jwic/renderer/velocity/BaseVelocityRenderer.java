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
 * de.jwic.renderer.velocity.BaseVelocityRenderer
 * $Id: BaseVelocityRenderer.java,v 1.6 2009/10/30 07:59:20 lordsam Exp $
 */
package de.jwic.renderer.velocity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import de.jwic.base.ConfigurationTool;
import de.jwic.base.Control;
import de.jwic.base.IControlRenderer;
import de.jwic.renderer.util.JWicTools;

/**
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.6 $
 */
public abstract class BaseVelocityRenderer implements IControlRenderer {
	
	protected final Log log = LogFactory.getLog(getClass());
	protected VelocityEngine ve = null; 
	
	protected Map<String, String> tplNames = new HashMap<String, String>();
	protected List<String> tplExtension = new ArrayList<String>();
	
	/**
	 * Default constructor.
	 * @throws Exception
	 */
	public BaseVelocityRenderer() throws Exception {
		tplExtension.add(".vtl");
	}

	/**
	 * Set the velocityEngine used by this renderer.
	 * @param engine
	 */
	public void setVelocityEngine(VelocityEngine engine) {
		ve = engine;
	}
	/**
	 * @param velocityProperties The velocityProperties to set.
	 * @throws Exception
	 */
	public void setVelocityProperties(Properties velocityProperties) throws Exception {
		ve = new VelocityEngine();
		ConfigurationTool.insertRootPath(velocityProperties);
		ve.init(velocityProperties);
	}
	
	/**
	 * Creates a new velocity context and adds standard objects.
	 * @return
	 */
	protected VelocityContext createContext(Control control) {
		VelocityContext vCtx = new VelocityContext();
		vCtx.put("jwic", new JWicTools(control.getSessionContext().getLocale(), control.getSessionContext().getTimeZone()));
		vCtx.put("escape", new StringEscapeUtils());
		return vCtx;
	}
	/**
	 * Returns the template with the given ID. 
	 * @param templateId
	 * @return
	 * @throws Exception
	 * @throws ParseErrorException
	 */
	protected Template getTemplate(String tplName) throws ParseErrorException, Exception {
		return getTemplate(tplName, null);
	}
	
	/**
	 * Returns the template with the given ID. 
	 * @param templateId
	 * @param includesExtension
	 * @return
	 * @throws Exception
	 * @throws ParseErrorException
	 */
	protected Template getTemplate(String tplName, String extension) throws ParseErrorException, Exception {
		
		Template tpl = null;
		String tryName = null;
		
		if (tplName == null) {
			return null;
		}
		
		String cacheKey = tplName + (extension != null ? "|" + extension : "");
		
		// check if we already looked up the template with the given name.
		if (tplNames.containsKey(cacheKey)) {
			tryName = tplNames.get(cacheKey);
			if (tryName != null) {	// if tryName is null, there is no template with the given name.
				tpl = ve.getTemplate(tryName);
			}
		} else {
			String ext = "";
			if (extension != null) {
				ext = extension;
				tryName = tplName;
				try {
					tpl = ve.getTemplate(tryName + ext);
				} catch (ResourceNotFoundException rnfe) {
					// try classloader format
					tryName = tryName.replace('.', '/');
					try {
						tpl = ve.getTemplate(tryName + ext);
					} catch (ResourceNotFoundException rnfe3) {
						// do nothing to remember that there is no template.
					}
				}
			} else {
				// the template name is unknown - try to find out wich name to use for the template.
				for (int i = 0; i < tplExtension.size(); i++) {
					tryName = tplName;
					ext = tplExtension.get(i).toString();
					try {
						tpl = ve.getTemplate(tryName + ext);
						break;
					} catch (ResourceNotFoundException rnfe) {
						// try classloader format
						tryName = tryName.replace('.', '/');
						try {
							tpl = ve.getTemplate(tryName + ext);
							break;
						} catch (ResourceNotFoundException rnfe3) {
							// do nothing to remember that there is no template.
						}
					}
					
				}
			}
			if (tpl == null) {
				tplNames.put(cacheKey, null);	// remember that there is no template with that name
			} else {
				tplNames.put(cacheKey, tryName + ext);	// remember the name that "worked"
			}
		}
		return tpl;

	}
	
	/**
	 * Returns the supported template file extension list.
	 * Default element is ".vtl".
	 * @return
	 */
	public List<String> getTemplateExtension() {
		return tplExtension;
	}
	
	/**
	 * Sets the template file extension list.
	 * Default element is ".vtl".
	 * @param templateExtension
	 */
	public void setTemplateExtension(List<String> templateExtension) {
		this.tplExtension = templateExtension;
	}
}
