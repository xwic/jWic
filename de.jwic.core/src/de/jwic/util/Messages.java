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

import java.util.ArrayList;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Handles localized messages.
 * @version $Revision: 1.2 $
 * @author Florian Lippisch
 */
public class Messages {

	private ResourceBundle RESOURCE_BUNDLE = null;
	private ArrayList<ResourceBundle> bundles = null;

	/**
	 * @param locale
	 */
	public Messages(Locale locale, String bundleName) {
		RESOURCE_BUNDLE = ResourceBundle.getBundle(bundleName, locale);
	}

	/**
	 * Returns a string from the resource bundle defined by the key.
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		if (RESOURCE_BUNDLE == null) {
			throw new IllegalStateException("RESOURCE BUNDLE not yet initialized.");
		}
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			if (bundles != null) {
				// look in added bundles
				for (int i = 0; i < bundles.size(); i++) {
					ResourceBundle bundle = bundles.get(i);
					try {
						return bundle.getString(key);
					} catch (MissingResourceException mre) {
						// resource not found
					}
				}
			}
			// key unkown
			return '!' + key + '!';
		}
	}
	
	/**
	 * Add a bundle.
	 * @param bundleName
	 */
	public void addBundle(String bundleName) {
		if (bundles == null) {
			bundles = new ArrayList<ResourceBundle>();
		}
		bundles.add(ResourceBundle.getBundle(bundleName, RESOURCE_BUNDLE.getLocale()));
	}
	
}
