package de.jwic.mobile.controls.mtable;

import java.io.Serializable;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 18.12.2015
 */
public class MColumn implements Serializable {
	private int priority;
	private String name;
	private String cssClass;

	private String fieldName;

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * returns the css class which will be assigned to defined column
	 * @return
	 */
	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	/**
	 * returns the name of the field which will be considered as tableCell,
	 * CellRenderer search using reflection this field and get value of it
	 */
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

}
