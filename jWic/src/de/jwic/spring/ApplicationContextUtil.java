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
 * de.jwic.spring.ApplicationContextUtil
 * Created on 13.09.2005
 * $Id: ApplicationContextUtil.java,v 1.1 2006/01/16 08:31:13 lordsam Exp $
 */
package de.jwic.spring;

import org.springframework.context.ApplicationContext;

/**
 * Provides static access to the ApplicationContext.
 * 
 * @author Alex Soellner
 * @version $Revision: 1.1 $
 */
public class ApplicationContextUtil {

	private static ApplicationContext applicationContext = null;

	/**
	 * @return Returns the applicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * @param applicationContext The applicationContext to set.
	 */
	public static void setApplicationContext(ApplicationContext applicationContext) {
		ApplicationContextUtil.applicationContext = applicationContext;
	}
	
	
}
