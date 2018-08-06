/*
 * Copyright (c) NetApp Inc. - All Rights Reserved
 * 
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * 
 * de.jwic.controls.slickgrid.SlickGridOptions 
 */
package de.jwic.controls.slickgrid;

import java.io.Serializable;

/**
 * Explanations for these options can be found here: https://github.com/6pac/SlickGrid/wiki/Grid-Options
 * 
 * @author Adrian Ionescu
 */
public class SlickGridOptions implements Serializable {

	private static final long serialVersionUID = -7037218169331241766L;

	public enum SelectionModel {
		ROW,
		CELL
	}
	
	private boolean asyncEditorLoading = false;
	private boolean autoEdit = false;
	private boolean autoHeight = false;
	private boolean editable = false;
	//private boolean enableAddRow = false; not supported yet
	private boolean enableCellNavigation = true;
	private boolean enableColumnReorder = true;
	private boolean enableTextSelectionOnCells = true;
	private boolean forceFitColumns = false;
	private boolean fullWidthRows = false;
	private boolean multiColumnSort = true;
	private boolean multiSelect = false;
	private boolean syncColumnCellResize = true;
	private boolean showHeaderRow = false;
	
	/**
	 * The following two properties need to be true in order to use column grouping
	 * <br/><br/>
	 * Recommended to use the {@link #setShowColumnGrouping(boolean) showColumnGrouping} shorthand method
	 */
	private boolean createPreHeaderPanel = false;
	private boolean showPreHeaderPanel = false;
	
	/**
	 * The following two properties need to be true in order to use the totals row
	 * <br/><br/>
	 * Recommended to use the {@link #setShowTotalsRow(boolean) showTotalsRow} shorthand method
	 */
	private boolean createFooterRow = false;
	private boolean showFooterRow = false;
	
	private String cellFlashingCssClass = "flashing";
	private String cellHighlightCssClass = "selected";
	private String selectedCellCssClass = "selected";

	private int asyncEditorLoadDelay = 100;
	private int defaultColumnWidth = 100;
	private int rowHeight = 25;
	private int headerRowHeight = 30;
	private int preHeaderPanelHeight = 23;
	private int footerRowHeight = 21;
	
	/**
	 * The following properties are not part of the SlickGrid implementation, they are for support in the jWic control. 
	 * The transient ones are not needed in the JS code, only in the Java/VTL one, therefore they won't be serialized
	 */
	private boolean stopRowSelectedEvent = false;
	private String nonEditableCellCssClass = "slickgrid-cell-disabled";
	private transient int width = 600;
	private transient int height = 300;
	private boolean sendCellChangesImmediatelyToServer = false;
	private transient SelectionModel selectionModel = SelectionModel.ROW;
	
	/**
	 * 
	 */
	public SlickGridOptions() {

	}

	/**
	 * @return the asyncEditorLoading
	 */
	public boolean isAsyncEditorLoading() {
		return asyncEditorLoading;
	}

	/**
	 * @param asyncEditorLoading the asyncEditorLoading to set
	 */
	public void setAsyncEditorLoading(boolean asyncEditorLoading) {
		this.asyncEditorLoading = asyncEditorLoading;
	}

	/**
	 * @return the autoEdit
	 */
	public boolean isAutoEdit() {
		return autoEdit;
	}

	/**
	 * This method will also update the stopRowSelectedEvent flag to the value of autoEdit.
	 * <br/><br/>
	 * By default we want to fire the selection event to the server only if autoEdit = false
	 * The reason is that on auto-editing we jump between rows with enter and sending the row selected event to the server 
	 * would disrupt this flow.
	 * <br/><br/>
	 * However, if for some functionality we still need to trigger the row selection event, this flag can 
	 * be overridden using {@link #setStopRowSelectedEvent setStopRowSelectedEvent()}
	 * 
	 * @param autoEdit the autoEdit to set
	 */
	public void setAutoEdit(boolean autoEdit) {
		this.autoEdit = autoEdit;
		
		this.stopRowSelectedEvent = autoEdit;
	}
	
	/**
	 * @return the stopRowSelectedEvent
	 */
	public boolean isStopRowSelectedEvent() {
		return stopRowSelectedEvent;
	}
	
	/**
	 * Stops the client from sending the row selection event to the server
	 * @param stopRowSelectedEvent the stopRowSelectedEvent to set
	 */
	public void setStopRowSelectedEvent(boolean stopRowSelectedEvent) {
		this.stopRowSelectedEvent = stopRowSelectedEvent;
	}

	/**
	 * @return the autoHeight
	 */
	public boolean isAutoHeight() {
		return autoHeight;
	}

	/**
	 * @param autoHeight the autoHeight to set
	 */
	public void setAutoHeight(boolean autoHeight) {
		this.autoHeight = autoHeight;
	}

	/**
	 * @return the editable
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * @param editable the editable to set
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

//	/**
//	 * @return the enableAddRow
//	 */
//	public boolean isEnableAddRow() {
//		return enableAddRow;
//	}
//
//	/**
//	 * @param enableAddRow the enableAddRow to set
//	 */
//	public void setEnableAddRow(boolean enableAddRow) {
//		this.enableAddRow = enableAddRow;
//	}

	/**
	 * @return the enableCellNavigation
	 */
	public boolean isEnableCellNavigation() {
		return enableCellNavigation;
	}

	/**
	 * @param enableCellNavigation the enableCellNavigation to set
	 */
	public void setEnableCellNavigation(boolean enableCellNavigation) {
		this.enableCellNavigation = enableCellNavigation;
	}

	/**
	 * @return the enableColumnReorder
	 */
	public boolean isEnableColumnReorder() {
		return enableColumnReorder;
	}

	/**
	 * @param enableColumnReorder the enableColumnReorder to set
	 */
	public void setEnableColumnReorder(boolean enableColumnReorder) {
		this.enableColumnReorder = enableColumnReorder;
	}

	/**
	 * @return the enableTextSelectionOnCells
	 */
	public boolean isEnableTextSelectionOnCells() {
		return enableTextSelectionOnCells;
	}

	/**
	 * @param enableTextSelectionOnCells the enableTextSelectionOnCells to set
	 */
	public void setEnableTextSelectionOnCells(boolean enableTextSelectionOnCells) {
		this.enableTextSelectionOnCells = enableTextSelectionOnCells;
	}

	/**
	 * @return the forceFitColumns
	 */
	public boolean isForceFitColumns() {
		return forceFitColumns;
	}

	/**
	 * @param forceFitColumns the forceFitColumns to set
	 */
	public void setForceFitColumns(boolean forceFitColumns) {
		this.forceFitColumns = forceFitColumns;
	}

	/**
	 * @return the fullWidthRows
	 */
	public boolean isFullWidthRows() {
		return fullWidthRows;
	}

	/**
	 * @param fullWidthRows the fullWidthRows to set
	 */
	public void setFullWidthRows(boolean fullWidthRows) {
		this.fullWidthRows = fullWidthRows;
	}

	/**
	 * @return the multiColumnSort
	 */
	public boolean isMultiColumnSort() {
		return multiColumnSort;
	}

	/**
	 * @param multiColumnSort the multiColumnSort to set
	 */
	public void setMultiColumnSort(boolean multiColumnSort) {
		this.multiColumnSort = multiColumnSort;
	}

	/**
	 * @return the multiSelect
	 */
	public boolean isMultiSelect() {
		return multiSelect;
	}

	/**
	 * @param multiSelect the multiSelect to set
	 */
	public void setMultiSelect(boolean multiSelect) {
		this.multiSelect = multiSelect;
	}

	/**
	 * @return the syncColumnCellResize
	 */
	public boolean isSyncColumnCellResize() {
		return syncColumnCellResize;
	}

	/**
	 * @param syncColumnCellResize the syncColumnCellResize to set
	 */
	public void setSyncColumnCellResize(boolean syncColumnCellResize) {
		this.syncColumnCellResize = syncColumnCellResize;
	}

	/**
	 * @return the cellFlashingCssClass
	 */
	public String getCellFlashingCssClass() {
		return cellFlashingCssClass;
	}

	/**
	 * @param cellFlashingCssClass the cellFlashingCssClass to set
	 */
	public void setCellFlashingCssClass(String cellFlashingCssClass) {
		this.cellFlashingCssClass = cellFlashingCssClass;
	}

	/**
	 * @return the cellHighlightCssClass
	 */
	public String getCellHighlightCssClass() {
		return cellHighlightCssClass;
	}

	/**
	 * @param cellHighlightCssClass the cellHighlightCssClass to set
	 */
	public void setCellHighlightCssClass(String cellHighlightCssClass) {
		this.cellHighlightCssClass = cellHighlightCssClass;
	}

	/**
	 * @return the selectedCellCssClass
	 */
	public String getSelectedCellCssClass() {
		return selectedCellCssClass;
	}

	/**
	 * @param selectedCellCssClass the selectedCellCssClass to set
	 */
	public void setSelectedCellCssClass(String selectedCellCssClass) {
		this.selectedCellCssClass = selectedCellCssClass;
	}

	/**
	 * @return the asyncEditorLoadDelay
	 */
	public int getAsyncEditorLoadDelay() {
		return asyncEditorLoadDelay;
	}

	/**
	 * @param asyncEditorLoadDelay the asyncEditorLoadDelay to set
	 */
	public void setAsyncEditorLoadDelay(int asyncEditorLoadDelay) {
		this.asyncEditorLoadDelay = asyncEditorLoadDelay;
	}

	/**
	 * @return the defaultColumnWidth
	 */
	public int getDefaultColumnWidth() {
		return defaultColumnWidth;
	}

	/**
	 * @param defaultColumnWidth the defaultColumnWidth to set
	 */
	public void setDefaultColumnWidth(int defaultColumnWidth) {
		this.defaultColumnWidth = defaultColumnWidth;
	}

	/**
	 * @return the rowHeight
	 */
	public int getRowHeight() {
		return rowHeight;
	}

	/**
	 * @param rowHeight the rowHeight to set
	 */
	public void setRowHeight(int rowHeight) {
		this.rowHeight = rowHeight;
	}

	/**
	 * @return the createPreHeaderPanel
	 */
	public boolean isCreatePreHeaderPanel() {
		return createPreHeaderPanel;
	}

	/**
	 * One of the properties that needs to be true in order to use column grouping
	 * <br/><br/>
	 * Don't use this method directly, it is recommended to use the {@link #setShowColumnGrouping(boolean) showColumnGrouping} shorthand method instead
	 * <br/><br/>
	 * @param createPreHeaderPanel the createPreHeaderPanel to set
	 */
	@Deprecated
	public void setCreatePreHeaderPanel(boolean createPreHeaderPanel) {
		this.createPreHeaderPanel = createPreHeaderPanel;
	}

	/**
	 * @return the showPreHeaderPanel
	 */
	public boolean isShowPreHeaderPanel() {
		return showPreHeaderPanel;
	}

	/**
	 * One of the properties that needs to be true in order to use column grouping
	 * <br/><br/>
	 * Don't use this method directly, it is recommended to use the {@link #setShowColumnGrouping(boolean) showColumnGrouping} shorthand method instead
	 * <br/><br/>
	 * @param showPreHeaderPanel the showPreHeaderPanel to set
	 */
	@Deprecated
	public void setShowPreHeaderPanel(boolean showPreHeaderPanel) {
		this.showPreHeaderPanel = showPreHeaderPanel;
	}

	/**
	 * @return the preHeaderPanelHeight
	 */
	public int getPreHeaderPanelHeight() {
		return preHeaderPanelHeight;
	}

	/**
	 * @param preHeaderPanelHeight the preHeaderPanelHeight to set
	 */
	public void setPreHeaderPanelHeight(int preHeaderPanelHeight) {
		this.preHeaderPanelHeight = preHeaderPanelHeight;
	}

	/**
	 * Alias for the {@link #getFilterRowHeight() getFilterRowHeight} method
	 */
	public int getHeaderRowHeight() {
		return headerRowHeight;
	}

	/**
	 * Alias for the {@link #setFilterRowHeight(int) setFilterRowHeight} method
	 */
	public void setHeaderRowHeight(int headerRowHeight) {
		this.headerRowHeight = headerRowHeight;
	}
	
	/**
	 * Alias for the {@link #getHeaderRowHeight() getHeaderRowHeight} method
	 */
	public int getFilterRowHeight() {
		return headerRowHeight;
	}

	/**
	 * Alias for the {@link #setHeaderRowHeight(int) setHeaderRowHeight} method
	 */
	public void setFilterRowHeight(int headerRowHeight) {
		this.headerRowHeight = headerRowHeight;
	}

	/**
	 * @return the createFooterRow
	 */
	public boolean isCreateFooterRow() {
		return createFooterRow;
	}

	/**
	 * One of the properties that needs to be true in order to show the totals row
	 * <br/><br/>
	 * Don't use this method directly, it is recommended to use the {@link #setShowTotalsRow(boolean) showTotalsRow} shorthand method instead
	 * <br/><br/>
	 * @param createFooterRow the createFooterRow to set
	 */
	@Deprecated
	public void setCreateFooterRow(boolean createFooterRow) {
		this.createFooterRow = createFooterRow;
	}

	/**
	 * @return the showFooterRow
	 */
	public boolean isShowFooterRow() {
		return showFooterRow;
	}

	/**
	 * One of the properties that needs to be true in order to show the totals row
	 * <br/><br/>
	 * Don't use this method directly, it is recommended to use the {@link #setShowTotalsRow(boolean) showTotalsRow} shorthand method instead
	 * <br/><br/>
	 * @param showFooterRow the showFooterRow to set
	 */
	@Deprecated
	public void setShowFooterRow(boolean showFooterRow) {
		this.showFooterRow = showFooterRow;
	}

	/**
	 * @return the footerRowHeight
	 */
	public int getFooterRowHeight() {
		return footerRowHeight;
	}

	/**
	 * @param footerRowHeight the footerRowHeight to set
	 */
	public void setFooterRowHeight(int footerRowHeight) {
		this.footerRowHeight = footerRowHeight;
	}
	
	/**
	 * Enables the total row
	 */
	public void setShowTotalsRow(boolean b) {
		setCreateFooterRow(b);
		setShowFooterRow(b);		
	}
	
	/**
	 * Enables column grouping
	 */
	public void setShowColumnGrouping(boolean b) {
		setCreatePreHeaderPanel(b);
		setShowPreHeaderPanel(b);
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
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * @return the selectionModel
	 */
	public SelectionModel getSelectionModel() {
		return selectionModel;
	}
	
	/**
	 * @param selectionModel the selectionModel to set
	 */
	public void setSelectionModel(SelectionModel selectionModel) {
		this.selectionModel = selectionModel;
	}

	/**
	 * @return the nonEditableCellCssClass
	 */
	public String getNonEditableCellCssClass() {
		return nonEditableCellCssClass;
	}

	/**
	 * @param nonEditableCellCssClass the nonEditableCellCssClass to set
	 */
	public void setNonEditableCellCssClass(String nonEditableCellCssClass) {
		this.nonEditableCellCssClass = nonEditableCellCssClass;
	}

	/**
	 * Alias for the {@link #isShowHeaderRow() isShowHeaderRow} method
	 */
	public boolean isShowQuickFilters() {
		return showHeaderRow;
	}

	/**
	 * Alias for the {@link #setShowHeaderRow(boolean) setShowHeaderRow} method
	 */
	public void setShowQuickFilters(boolean showHeaderRow) {
		this.showHeaderRow = showHeaderRow;
	}
	
	/**
	 * Alias for the {@link #isShowQuickFilters() isShowQuickFilters} method
	 */
	public boolean isShowHeaderRow() {
		return showHeaderRow;
	}
	
	/**
	 * Alias for the {@link #setShowQuickFilters(boolean) setShowQuickFilters} method
	 */
	public void setShowHeaderRow(boolean showHeaderRow) {
		this.showHeaderRow = showHeaderRow;
	}

	/**
	 * @return the sendCellChangesToServer
	 */
	public boolean isSendCellChangesToServer() {
		return sendCellChangesImmediatelyToServer;
	}

	/**
	 * Set to true in order to send the cell changes to the server immediately after the cell is edited
	 * <br/><br/>
	 * To be used in combination with {@link SlickGridModel}.addCellChangedListener
	 * @param sendCellChangesImmediatelyToServer the sendCellChangesToServer to set
	 */
	public void setSendCellChangesImmediatelyToServer(boolean sendCellChangesImmediatelyToServer) {
		this.sendCellChangesImmediatelyToServer = sendCellChangesImmediatelyToServer;
	}
}
