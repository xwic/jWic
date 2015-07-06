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

import java.util.Iterator;
import java.util.Properties;

/**
 * Used to modify <code>Properties</code> configuration. The rootPath is
 * set by the DispatcherServlet during startup. Classes that depend on 
 * resources from the file system (like renderer) can then "insert" the root
 * path into their configuration.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class ConfigurationTool {
	
	public final static String ROOT_PATH_VAR = "${rootPath}";
	private static String rootPath = "./";
	
	/**
	 * Returns the root path of the Web Application.
	 * @return
	 */
	public static String getRootPath() {
		return rootPath;
	}


	/**
	 * Searches for the variable ${rootPath} in the property values and
	 * replaces it with the ControlFactory.getRootPath().
	 * @param prop
	 */
	public static void insertRootPath(Properties prop) {
		
		for (Iterator<?> it = prop.keySet().iterator(); it.hasNext(); ) {
			String key = (String)it.next();
			String value = prop.getProperty(key);
			int idx = value.indexOf(ROOT_PATH_VAR);
			if (idx != -1) {
				prop.setProperty(key,
							value.substring (0, idx) + rootPath + value.substring(idx + ROOT_PATH_VAR.length())
						);
			}
		}
		
	}
	
	/**
	 * Searches for the variable ${rootPath} in the string and replaces
	 * it with the rootPath setting. 
	 * @param value
	 * @return
	 */
	public static String insertRootPath(String value) {

		int idx = value.indexOf(ROOT_PATH_VAR);
		if (idx != -1) {
			return value.substring (0, idx) + rootPath + value.substring(idx + ROOT_PATH_VAR.length());
		}
		return value;
		
	}

	/**
	 * Set the root path of the web application.
	 * @param newRootPath
	 */
	public static void setRootPath(String newRootPath) {
		rootPath = newRootPath;
	}
	
}
