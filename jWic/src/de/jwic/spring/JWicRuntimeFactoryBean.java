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
 * de.jwic.spring.JWicRuntimeFactoryBean
 * Created on 20.05.2005
 * $Id: JWicRuntimeFactoryBean.java,v 1.2 2008/09/17 15:19:36 lordsam Exp $
 */
package de.jwic.spring;

import java.io.InputStream;

import javax.servlet.ServletException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

import de.jwic.base.JWicRuntime;

/**
 * Spring FactoryBean wrapper for a JWicRuntime singleton.
 * 
 * @version $Revision: 1.2 $
 * @author Christian Jaeckel
 * @since Nov 25, 2004
 */
public class JWicRuntimeFactoryBean  implements ApplicationContextAware, FactoryBean, InitializingBean {
	  private String savePath;
	  private String webRootProperty = "";
	  private ApplicationContext context = null;
	  /* (non-Javadoc)
	   * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	   */
	  public void afterPropertiesSet() throws Exception {
	    JWicRuntime jr= JWicRuntime.getJWicRuntime();
	    
	    if (savePath !=null)
	      jr.setSavePath(savePath);
	    
	    Resource res = context.getResource("/WEB-INF/jwic-setup.xml");
	    if (res.exists()) {
			InputStream in = res.getInputStream();
			if (in != null) {
				jr.setupRuntime(in);
			} else {
				throw new ServletException("WEB-INF/jwic-setup.xml not found.");
			}
	    }
		
	  }

	  /* (non-Javadoc)
	   * @see org.springframework.beans.factory.FactoryBean#getObject()
	   */
	  public Object getObject() throws Exception {
	    return JWicRuntime.getJWicRuntime();
	  }

	  /* (non-Javadoc)
	   * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	   */
	  public Class<?> getObjectType() {
	    return JWicRuntime.class;
	  }

	  /* (non-Javadoc)
	   * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	   */
	  public boolean isSingleton() {
	    return true;
	  }

	/**
	 * @return Returns the webRootDir.
	 */
	public String getWebRootProperty() {
		return webRootProperty;
	}
	/**
	 * @param webRootDir The webRootDir to set.
	 */
	public void setWebRootProperty(String webRootProperty) {
		this.webRootProperty = webRootProperty;
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
		ApplicationContextUtil.setApplicationContext(context);
	}
}

