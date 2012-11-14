/*
 * Copyright 2005-2007 jWic group (http://www.jwic.de)
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
 * de.jwic.util.Util
 * Created on 16.09.2008
 * $Id: Util.java,v 1.1 2008/09/16 21:55:47 lordsam Exp $
 */
package de.jwic.util;

/**
 * Utility collection.
 * @author Florian Lippisch
 */
public class Util {

	/**
	 * Returns true if both objects are NULL or if they equal each other.
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static boolean equals(Object o1, Object o2) {
		if (o1 == null && o2 == null) {
			return true;	// both are null
		}
		if (o1 == null || o2 == null) {
			return false;	// just 1 is null
		}
		return o1.equals(o2);
	}
	
}
