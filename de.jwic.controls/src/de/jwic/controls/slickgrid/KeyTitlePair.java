/*
 * Copyright (c) NetApp Inc. - All Rights Reserved
 * 
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * 
 * de.jwic.controls.slickgrid.KeyTitlePair 
 */
package de.jwic.controls.slickgrid;

import java.io.Serializable;

/**
 * @author Adrian Ionescu
 */
public class KeyTitlePair implements Serializable {
	
	private static final long serialVersionUID = -1130459018450142527L;
	
	/**
	 * This is used in the SlickGrid.static.js - DropDownEditor
	 */
	public final static String KEY = "key";
	
	/**
	 * This is used in the SlickGrid.static.js - DropDownEditor
	 */
	public final static String TITLE = "title";
	
	private String key;
	private String title;
	
	/**
	 * 
	 */
	public KeyTitlePair() {
		
	}
	
	/**
	 * @param key
	 * @param title
	 */
	public KeyTitlePair(String key, String title) {
		super();
		this.key = key;
		this.title = title;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	};

}
