/*
 * Copyright (c) NetApp Inc. - All Rights Reserved
 * 
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * 
 * de.jwic.controls.slickgrid.SlickGridColumn 
 */
package de.jwic.controls.slickgrid;

import java.io.Serializable;

/**
 * Explanations for these options can be found here: https://github.com/6pac/SlickGrid/wiki/Column-Options
 * 
 * @author Adrian Ionescu
 */
public class SlickGridColumn implements Serializable {
	
	private static final long serialVersionUID = 1876300346980560080L;
	
	public final static String FORMATTER_YES_NO = "Slick.Formatters.YesNo";
	public final static String FORMATTER_CHECKBOX = "Slick.Formatters.Checkbox";
	public final static String FORMATTER_CHECKMARK = "Slick.Formatters.Checkmark";
	public final static String FORMATTER_PERCENT_COMPLETE = "Slick.Formatters.PercentComplete";
	public final static String FORMATTER_PERCENT_COMPLETE_BAR = "Slick.Formatters.PercentCompleteBar";
	
	public final static String EDITOR_TEXT = "Slick.Editors.Text";
	public final static String EDITOR_INTEGER = "Slick.Editors.Integer";
	public final static String EDITOR_FLOAT = "Slick.Editors.Float";
	public final static String EDITOR_DATE = "Slick.Editors.Date";
	public final static String EDITOR_YES_NO = "Slick.Editors.YesNoSelect";
	public final static String EDITOR_CHECKBOX = "Slick.Editors.Checkbox";
	public final static String EDITOR_PERCENT_COMPLETE = "Slick.Editors.PercentComplete";
	public final static String EDITOR_LONG_TEXT = "Slick.Editors.LongText";
	
	private String id;
	private String field;
	private String name;
	private String cssClass;
	private String headerCssClass;
	private String toolTip;
	/**
	 *  You can use one of the existing formatters, defined as constants above, or write your own (see slick.formatters.js)
	 */
	private String formatter;
	/**
	 * You can use one of the existing editors, defined as constants above, or write your own (see slick.editors.js)
	 */
	private String editor;
	private String columnGroup;
	private String totalLabel;
	
	private boolean resizable = true;
	private boolean sortable = true;
	
	private int width;
	private int minWidth = 30;
	private int maxWidth;
	
	/**
	 * The following properties are not part of the SlickGrid implementation, they are for support in the jWic control.
	 * The transient ones are not needed in the JS code, only in the Java/VTL one, therefore they won't be serialized 
	 */	
	private boolean canBeSummedUp = false;
	private transient String dateFormat = "dd-MMM-yyyy";
	private transient ISlickGridColumnValueProvider valueProvider;
	
	/**
	 * @param id
	 * @param name
	 * @param width
	 */
	public SlickGridColumn(String id, String name, int width) {
		this.id = id;
		this.field = id;
		this.name = name;
		this.width = width;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}
	
	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the cssClass
	 */
	public String getCssClass() {
		return cssClass;
	}
	
	/**
	 * @param cssClass the cssClass to set
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	
	/**
	 * @return the resizable
	 */
	public boolean isResizable() {
		return resizable;
	}
	
	/**
	 * @param resizable the resizable to set
	 */
	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}
	
	/**
	 * @return the sortable
	 */
	public boolean isSortable() {
		return sortable;
	}
	
	/**
	 * @param sortable the sortable to set
	 */
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}
	
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * @return the minWidth
	 */
	public int getMinWidth() {
		return minWidth;
	}
	
	/**
	 * @param minWidth the minWidth to set
	 */
	public void setMinWidth(int minWidth) {
		this.minWidth = minWidth;
	}
	
	/**
	 * @return the maxWidth
	 */
	public int getMaxWidth() {
		return maxWidth;
	}
	
	/**
	 * @param maxWidth the maxWidth to set
	 */
	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}

	/**
	 * @return the valueProvider
	 */
	public ISlickGridColumnValueProvider getValueProvider() {
		return valueProvider;
	}

	/**
	 * @param valueProvider the valueProvider to set
	 */
	public void setValueProvider(ISlickGridColumnValueProvider valueProvider) {
		this.valueProvider = valueProvider;
	}

	/**
	 * @return the formatter
	 */
	public String getFormatter() {
		return formatter;
	}

	/**
	 * @param formatter the formatter to set
	 */
	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	/**
	 * @return the headerCssClass
	 */
	public String getHeaderCssClass() {
		return headerCssClass;
	}

	/**
	 * @param headerCssClass the headerCssClass to set
	 */
	public void setHeaderCssClass(String headerCssClass) {
		this.headerCssClass = headerCssClass;
	}

	/**
	 * @return the toolTip
	 */
	public String getToolTip() {
		return toolTip;
	}

	/**
	 * @param toolTip the toolTip to set
	 */
	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}

	/**
	 * @return the editor
	 */
	public String getEditor() {
		return editor;
	}

	/**
	 * @param editor the editor to set
	 */
	public void setEditor(String editor) {
		this.editor = editor;
	}

	/**
	 * @return the dateFormat
	 */
	public String getDateFormat() {
		return dateFormat;
	}

	/**
	 * @param dateFormat the dateFormat to set
	 */
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	/**
	 * @return the columnGroup
	 */
	public String getColumnGroup() {
		return columnGroup;
	}

	/**
	 * @param columnGroup the columnGroup to set
	 */
	public void setColumnGroup(String columnGroup) {
		this.columnGroup = columnGroup;
	}

	/**
	 * @return the canBeSummedUp
	 */
	public boolean isCanBeSummedUp() {
		return canBeSummedUp;
	}

	/**
	 * @param canBeSummedUp the canBeSummedUp to set
	 */
	public void setCanBeSummedUp(boolean canBeSummedUp) {
		this.canBeSummedUp = canBeSummedUp;
	}
	
	/**
	 * @return the totalLabel
	 */
	public String getTotalLabel() {
		return totalLabel;
	}

	/**
	 * @param totalLabel the totalLabel to set
	 */
	public void setTotalLabel(String totalLabel) {
		this.totalLabel = totalLabel;
	}
}
