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
package de.jwic.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Contains methods to handle compatibility issues between the differnet
 * JDKs and ServletAPIs that may be used for jWic.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class Compatibility {

	/**
	 * request.getParameterMap() is not supported by older ServletAPIs.
	 * the map must be created the old (compatible) way.
	 */
	public static Map<String, String[]> getParameterMap(HttpServletRequest request) {
		Map<String, String[]> parameter = new HashMap<String, String[]>();
		for(Enumeration<?> e = request.getParameterNames(); e.hasMoreElements(); ) {
			String key = (String)e.nextElement();
			parameter.put(key, request.getParameterValues(key));
		}
		return Collections.unmodifiableMap(parameter);
	}
	
}
