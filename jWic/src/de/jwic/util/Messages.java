/*
 * Copyright 2005 jWic Group (http://www.jwic.de)
 * de.jwic.util.Messages
 * $Id: Messages.java,v 1.2 2008/09/17 15:19:42 lordsam Exp $
 */
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
