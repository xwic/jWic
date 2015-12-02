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

/**
 * Utility class for string processing.
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public class StringTool {
	
	/**
	 * Returns the values in an array as semmicolon seperated string. If the
	 * array is null or empty, an empty string is returned.
	 * @param values
	 * @return
	 */
	public static String getSingleString(String[] values) {
		if (values != null) {
			if (values.length == 1) {
				return values[0] == null ? "" : values[0];
			} else if (values.length > 1) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < values.length; i++) {
					if (i > 0) {
						sb.append(";");
					}
					sb.append(values[i]);
				}
				return sb.toString();
			}
		}
		return "";
	}
	
}
