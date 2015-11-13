package de.jwic.demo.chart;

import java.io.Serializable;

public class TableElement

implements Serializable {

	private String title;
	private String value;

	/**
	 * default constructor.
	 */
	public TableElement() {

	}

	/**
	 * @param title
	 * @param owner
	 * @param completed
	 */
	public TableElement(String title, String value) {
		super();
		this.title = title;
		this.value = value;

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
