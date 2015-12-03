package de.jwic.demo.chart;

import java.io.Serializable;

public class TableElement

		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2908277730445350249L;
	private String title;
	private String value;
	private String fillColor;
	private String strokeColor;
	private String highlightColor;
	private String highlightStroke;

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

	public void setHighlightStroke(String highlightStroke) {
		this.highlightStroke = highlightStroke;

	}

	public void setHighlightColor(String highlightColor) {
		this.highlightColor = highlightColor;

	}

	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;

	}

	public void setStrokeColor(String strokeColor) {
		this.strokeColor = strokeColor;

	}

	public String getFillColor() {
		return fillColor;
	}

	public String getStrokeColor() {
		return strokeColor;
	}

	public String getHighlightColor() {
		return highlightColor;
	}

	public String getHighlightStroke() {
		return highlightStroke;
	}

}
