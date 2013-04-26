/*
 * de.jwic.controls.ListEntry
 * $Id: ListEntry.java,v 1.4 2008/02/18 10:04:27 cosote Exp $
 */
package de.jwic.controls;

import java.io.Serializable;

/**
 * Contains the title and optionaly a key for an entry in a list.
 * @author Florian Lippisch
 */
public class ListEntry implements Serializable {

	private static final long serialVersionUID = 1L;
	public String title = "";
	public String key = null;
	private String cssClass = null;
	
	/**
	 * ListEntry constructor comment.
	 */
	public ListEntry() {
		super();
	}
	/**
	 * ListEntry constructor comment.
	 */
	public ListEntry(String newTitle) {
		title = newTitle;
	}
	/**
	 * ListEntry constructor comment.
	 */
	public ListEntry(String newTitle, String newKey) {
		title = newTitle;
		key = newKey;
	}
	/**
	 * @return Returns the key.
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key The key to set.
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return Returns the cssClass.
	 */
	public String getCssClass() {
		return cssClass;
	}
	
	/**
	 * @param cssClass The cssClass to set.
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof ListEntry) {
			return key.equals(((ListEntry)obj).key);
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return key.hashCode();
	}
}
