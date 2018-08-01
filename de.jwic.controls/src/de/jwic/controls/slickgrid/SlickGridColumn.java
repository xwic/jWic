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
public class SlickGridColumn<T> implements Serializable {
	
	private static final long serialVersionUID = 1876300346980560080L;
	
	/**
	 * Simple interface created to allow developers to define custom formatters
	 * @author Adrian Ionescu
	 */
	public interface IFormatters {
		public String getJsName();
	}
	
	/**
	 * Simple interface created to allow developers to define custom editors
	 * @author Adrian Ionescu
	 */
	public interface IEditors {
		public String getJsName();
	}
	
	public enum Formatters implements IFormatters {
		// these come with SlickGrid, defined in slick.formatters.js
		YES_NO("Slick.Formatters.YesNo"),
		CHECKBOX("Slick.Formatters.Checkbox"),
		CHECKMARK("Slick.Formatters.Checkmark"),
		PERCENT_COMPLETE("Slick.Formatters.PercentComplete"),
		PERCENT_COMPLETE_BAR("Slick.Formatters.PercentCompleteBar"),
		// these are custom made, defined in SlickGrid.static.js  
		DATE("Jwic.controls.Slickgrid.Formatters.Date"),
		KEY_TITLE("Jwic.controls.Slickgrid.Formatters.KeyTitle"),
		DECIMAL("Jwic.controls.Slickgrid.Formatters.Decimal");
		
		private String jsName;
		
		private Formatters(String jsName) {
			this.jsName = jsName;
		}
		
		@Override
		public String getJsName() {
			return jsName;
		}
	}
	
	public enum Editors implements IEditors {
		// these come with SlickGrid, defined in slick.editors.js
		TEXT("Slick.Editors.Text"),
		INTEGER("Slick.Editors.Integer"),
		FLOAT("Slick.Editors.Float"),
		DATE("Slick.Editors.Date"),
		YES_NO("Slick.Editors.YesNoSelect"),
		CHECKBOX("Slick.Editors.Checkbox"),
		PERCENT_COMPLETE("Slick.Editors.PercentComplete"),
		LONG_TEXT("Slick.Editors.LongText"),
		// these are custom made, defined in SlickGrid.static.js  
		KEY_TITLE_DROP_DOWN("JWic.controls.SlickGrid.KeyTitleDropDownEditor");
		
		private String jsName;
		
		private Editors(String jsName) {
			this.jsName = jsName;
		}
		
		@Override
		public String getJsName() {
			return jsName;
		}
	}
	
	private String id;
	private String field;
	private String name;
	private String cssClass;
	private String headerCssClass;
	private String toolTip;
	private String columnGroup;
	private String totalLabel;
	private IFormatters formatter;
	private IEditors editor;
	
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
	private String dateFormat = "dd/MM/yyyy";
	private transient ISlickGridColumnValueProvider<T> valueProvider;
	
	
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
	public ISlickGridColumnValueProvider<T> getValueProvider() {
		return valueProvider;
	}

	/**
	 * @param valueProvider the valueProvider to set
	 */
	public void setValueProvider(ISlickGridColumnValueProvider<T> valueProvider) {
		this.valueProvider = valueProvider;
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

	/**
	 * @return the dateFormat
	 */
	public String getDateFormat() {
		return dateFormat;
	}

	/**
	 * @see <a href="https://github.com/phstc/jquery-dateFormat">https://github.com/phstc/jquery-dateFormat</a> for the supported formats
	 * @param dateFormat the dateFormat to set
	 */
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	/**
	 * @return the formatter
	 */
	public IFormatters getFormatter() {
		return formatter;
	}

	/**
	 * @param formatter the formatter to set
	 */
	public void setFormatter(IFormatters formatter) {
		this.formatter = formatter;
	}

	/**
	 * @return the editor
	 */
	public IEditors getEditor() {
		return editor;
	}

	/**
	 * @param editor the editor to set
	 */
	public void setEditor(IEditors editor) {
		this.editor = editor;
	}
}
