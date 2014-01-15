/*
 * Copyright 2005-2007 jWic group (http://www.jwic.de)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * de.jwic.ecolib.tableviewer.DefaultTableRenderer
 * Created on 12.03.2007
 * $Id: FixColumnTableRenderer.java,v 1.17 2010/05/11 13:21:17 lordsam Exp $
 */
package de.jwic.controls.tableviewer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.LogFactory;

import de.jwic.base.Control;
import de.jwic.base.Field;
import de.jwic.base.IControlRenderer;
import de.jwic.base.ImageRef;
import de.jwic.base.JWicRuntime;
import de.jwic.base.RenderContext;
import de.jwic.data.IContentProvider;
import de.jwic.data.Range;

/**
 * Default implementation of the ITableRenderer interface. The default renderer
 * generates HTML "by code", without using templates. The reason for this is 
 * because its faster, costs less resources and the generated HTML is quite
 * complex so that templates would be quite ugly anyway.
 * 
 * @author Jens Bornemann
 */
public class FixColumnTableRenderer extends DefaultTableRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int fixColumn = 0;
	protected boolean reuseContent = true;

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.tableviewer.ITableRenderer#renderTable(de.jwic.base.RenderContext, de.jwic.ecolib.tableviewer.TableViewer, de.jwic.ecolib.tableviewer.TableModel)
	 */
	@SuppressWarnings("unused")
	public void renderTable(RenderContext renderContext, TableViewer viewer, TableModel model, ITableLabelProvider labelProvider) {
		
		if (!viewer.isScrollable() || fixColumn < 0) {
			super.renderTable(renderContext, viewer, model, labelProvider);
			return;
		}

		String tblvWebPath = JWicRuntime.getJWicRuntime().getContextPath() + "/ecolib/tblviewer/";
		
		PrintWriter writer = renderContext.getWriter();
		
		IContentProvider<?> contentProvider = model.getContentProvider();
		
		int tblWidth = 0;
		for (Iterator<TableColumn> it = model.getColumnIterator(); it.hasNext(); ) {
			TableColumn tc = it.next();
			if (!tc.isVisible()) {
				continue;
			}
			tblWidth += tc.getWidth();
		}
		
		int defaultColumnWidth = 150;
		// TODO use defined css border width instead of this fixed one...
		int leftWidth = (fixColumn + 1) * 2;
		{
			int c = 0;
			for (Iterator<TableColumn> itC = model.getColumnIterator(); itC.hasNext(); ) {
				TableColumn column = itC.next();
				if (c++ > fixColumn || !column.isVisible()) {
					break;
				}
				int width = column.getWidth() != 0 ? column.getWidth() : defaultColumnWidth;
				leftWidth += width;
			}
		}
		int rightWidth = viewer.getWidth() != 0 ? viewer.getWidth() - leftWidth : 0;

		// Add resizer div 
		if (viewer.isResizeableColumns()) {
			writer.print("<DIV id=\"tblViewResizer_" + viewer.getControlID() + "\" class=\"tblViewResizer\" ");
			/*writer.print("onMouseUp=\"tblViewer_resizeColumnDone()\"" +
					" onMouseMove=\"tblViewer_resizeColumMove()\"");*/
			writer.print(" style=\"height: " + (viewer.getHeight() != 0 ? viewer.getHeight() : 20) + "px\"");
			writer.println("></DIV>");
		}
		
		// render main table.
		writer.print("<table cellspacing=\"0\" cellpadding=\"0\" class=\"" + viewer.getCssClass() + "\"");
		if (viewer.isFillWidth()) {
			writer.print(" width=\"100%\"");
		} else {
			if (viewer.getWidth() != 0) {
				writer.print(" width=\"" + viewer.getWidth() + "\"");
			}
		}
		writer.println(">");
		writer.print("<tr><td>");
		
		// render data table.
		String divHeight = viewer.getHeight() != 0 ? 
				(viewer.isShowStatusBar() ? viewer.getHeight() - 18 : viewer.getHeight() ) + "px" 
				: "100%";
		String divWidth = viewer.getWidth() != 0 ? viewer.getWidth() + "px" : "100%";
		
		// main table content DIV
		writer.print("<div class=\"tblContent\"");
		writer.print(" id=\"tblContent_" + viewer.getControlID() + "\"");
		writer.print(" style=\"height: " + divHeight + "; width: " + divWidth + "; overflow: hidden\">");
		
		StringWriter leftHtml = new StringWriter();
		PrintWriter leftWriter = new PrintWriter(leftHtml);
		StringWriter rightHtml = new StringWriter();
		PrintWriter rightWriter = new PrintWriter(rightHtml);
		
		writer = leftWriter;
		String tblView = "tblViewLeft";
		for (int i = 0; i < 2; i++) {
			
			boolean right = i == 1;
			int dataWidth = leftWidth; 
			if (right && writer != rightWriter) {
				writer = rightWriter;
				tblView = "tblView";
				dataWidth = rightWidth;				
			}
			if (dataWidth == 0) {
				dataWidth = 300;
			}
		
			if (viewer.isShowHeader()) {
				writer.print("<DIV id=\"" + tblView + "Head_" + viewer.getControlID() + "\"");
				writer.print("style=\"width: " + dataWidth + "px; ");
				writer.print("height: 20px; overflow: hidden;");
				writer.print("\">");
			}
			
			// create required table attributes.
			StringBuffer sbTblSelAttrs = new StringBuffer();
			switch (model.getSelectionMode()) {
			case TableModel.SELECTION_SINGLE: {
				String clearKey = model.getFirstSelectedKey();
				if (clearKey == null) {
					clearKey = "";
				}
				sbTblSelAttrs.append(" tbvSelKey=\"" + clearKey + "\"");
				sbTblSelAttrs.append(" tbvSelMode=\"single\"");
				break;
			}
			case TableModel.SELECTION_MULTI: {
				sbTblSelAttrs.append(" tbvSelKey=\"\"");
				sbTblSelAttrs.append(" tbvSelMode=\"multi\"");
				break;
			}
			default: {
				sbTblSelAttrs.append(" tbvSelKey=\"\"");
				sbTblSelAttrs.append(" tbvSelMode=\"none\"");
			}
			}
			
			if (viewer.isShowHeader()) {
			
				writer.print("<table");
				writer.print(" tbvctrlid=\"" + viewer.getControlID() + "\"");
				writer.print(" id=\"" + tblView + "Data_" + viewer.getControlID() + "\"");
				writer.print(" class=\"tblData\" cellspacing=\"0\" cellpadding=\"0\" ");
				if (viewer.isScrollable()) {
					// must add a width attribute, otherwise table-layout: fixed isnt working on Mozilla
					writer.print(" width=\"" + dataWidth + "\" ");
				}
				writer.print(sbTblSelAttrs);
				writer.println(">");
			}
			
			// render HEADER columns
			if (viewer.isShowHeader()) {
				renderHeader(writer, model, viewer, tblvWebPath, right, dataWidth);
			}		
			// if scrollable, seperate the data table from the header
			// and render it within its own, scrollable DIV. Will only
			// look proper if the columns have a fixed width.
			
			int dataHeight = viewer.getHeight() != 0 ? viewer.getHeight() - (viewer.isShowHeader() ? 20 : 0): 200;
			if (viewer.isShowStatusBar()) {
				dataHeight -= 18;
			}
			if (viewer.isShowHeader()) {
				writer.println("</TABLE></DIV>");
			}
			if (!right) {
				writer.print("<DIV style=\"");
				writer.print("width: " + dataWidth + "px; height: " + dataHeight + "px; overflow: hidden;\"");
				writer.print("\" id=\"tblViewLeftDataLayer_" + viewer.getControlID() + "\"");
				writer.print(">");
				writer.print("<table");
				writer.print(" tbvctrlid=\"" + viewer.getControlID() + "\"");
				writer.print(" id=\"tblViewLeftDataTbl_" + viewer.getControlID() + "\"");
				writer.print(" class=\"tblData\" cellspacing=\"0\" cellpadding=\"0\" width=\"" + dataWidth + "\"");
				writer.print(sbTblSelAttrs);
				writer.print(">");
			} else {
				writer.print("<DIV onscroll=\"JWic.controls.TableViewer.handleScroll(event, '" + viewer.getControlID() + "')\" style=\"");
				writer.print("width: " + dataWidth + "px; height: " + dataHeight + "px; overflow: auto;");
				writer.print("\" id=\"tblViewDataLayer_" + viewer.getControlID() + "\"");
				writer.print(">");
				writer.print("<table");
				writer.print(" tbvctrlid=\"" + viewer.getControlID() + "\"");
				writer.print(" id=\"tblViewDataTbl_" + viewer.getControlID() + "\"");
				writer.print(" class=\"tblData\" cellspacing=\"0\" cellpadding=\"0\" width=\"" + dataWidth + "\"");
				writer.print(sbTblSelAttrs);
				writer.print(">");
			}
		}
		
		// render table BODY
		leftWriter.print("<TBODY class=\"tblData\">");
		rightWriter.print("<TBODY class=\"tblData\">");
		
		int count = 0;
		try {
			writer = leftWriter;
			tblView = "tblViewLeft";
			
			Range range = model.getRange();
			if (range.getMax() == 0) { // = Auto
				int max = -1;
				int rowSpace = viewer.getHeight() - (35 + 20); // - header height + scroll bar height
				if (rowSpace != 0) {
					if (viewer.isShowStatusBar()) {
						rowSpace -= 18;
					}
					max = rowSpace / viewer.getRowHeightHint(); //
					if (max < 1) {
						max = 1;
					}
					model.setLastRenderedPageSize(max);
				}
				range = new Range(range.getStart(), max);
			}

			
			List<Object> list = null;
			if (reuseContent) {
				list = new ArrayList<Object>();
				for (Iterator<?> it = contentProvider.getContentIterator(range); it.hasNext();) {
					list.add(it.next());
				}
			}
			
			
			for (int i = 0; i < 2; i++) {
				
				boolean right = i == 1;
				if (right && writer != rightWriter) {
					writer = rightWriter;
					tblView = "tblView";
				}
				Iterator<?> it = list != null ? list.iterator() : contentProvider.getContentIterator(model.getRange());
				count = renderRows(0, false, writer, it, viewer, labelProvider, right, tblView);
			}
		} catch (Exception e) {
			writer.println("Error reading data from ContentProvider: " + e);
			log.error("Error reading data from ContentProvider", e);
		}
		model.setLastRowRenderCount(count);

		writer = leftWriter;
		for (int i = 0; i < 2; i++) {
			
			boolean right = i == 1;
			if (right && writer != rightWriter) {
				writer = rightWriter;
			}
			writer.print("</TBODY>");
			writer.print("</table>");
			writer.print("</DIV>");
		}
		
		// finish left and right...
		leftWriter.close();
		rightWriter.close();
		writer = renderContext.getWriter();
		writer.print("<table cellspacing=\"0\" cellpadding=\"0\"><tr><td valign=top>");
		writer.print(leftHtml.toString());
		writer.print("</td><td valign=top>");
		writer.print(rightHtml.toString());
		writer.print("</td></tr></table>");
		
		writer.println("</div></td></tr>");
		
		// render STATUS BAR
		if (viewer.isShowStatusBar()) {
			writer.println("<tr><td>");
			// render context
			Control sb = viewer.getControl("statusBar");
			IControlRenderer renderer = JWicRuntime.getRenderer(sb.getRendererId());
			renderer.renderControl(sb, renderContext);
			writer.print("</td></tr>");
		}
		
		writer.println("</table>");
		
		// div to fix IE6 scrolling
		writer.print("<div id=\"ie6fixscroll_" + viewer.getControlID() + "\" style=display:none;;width:0px;height:0px\"></div>");
		
		// add scroll fields
		Field fldLeft = viewer.getField("left");
		Field fldTop = viewer.getField("top");
		writer.println("<INPUT TYPE=\"HIDDEN\" NAME=\"" + fldLeft.getId() + "\" VALUE=\"" + fldLeft.getValue() + "\">");
		writer.println("<INPUT TYPE=\"HIDDEN\" NAME=\"" + fldTop.getId() + "\" VALUE=\"" + fldTop.getValue() + "\">");

		writer.print("<script language=\"javascript\">");
		writer.print("window.setTimeout('var h = document.getElementById(\"tblViewDataLayer_" + viewer.getControlID() + "\").clientHeight;");
		writer.print("if (h > 0) document.getElementById(\"tblViewLeftDataLayer_" + viewer.getControlID() + "\").style.height=h + \"px\";");
		writer.print("JWic.restoreScrolling(\"" + viewer.getControlID() + "\", \"tblViewDataLayer_" + viewer.getControlID() + "\");', 0);");
		writer.print("</script>");
	}
	
	/**
	 * 
	 */
	protected void renderHeader(PrintWriter writer, TableModel model, TableViewer viewer, String tblvWebPath, boolean right, int dataWidth) {
		
		boolean isResizable = viewer.isResizeableColumns() && viewer.isEnabled();
		boolean isColSelectable = viewer.isSelectableColumns() && viewer.isEnabled();
		
		writer.println("<THEAD>");
		
		writer.println("<tr>");
		int c = -1;
		for (Iterator<TableColumn> itC = model.getColumnIterator(); itC.hasNext(); ) {
			TableColumn column = itC.next();
			c++;
			if (!right) {
				if (c > fixColumn) {
					break;
				}
			} else {
				if (c <= fixColumn) {
					continue;
				}
			}
			if (!column.isVisible()) {
				continue;
			}

			if (isResizable && column.getWidth() == 0) {
				// must set a default width if resizeable columns is activated
				column.setWidth(150);
			}
			
			writer.print("<th");
			int innerWidth = 0;
			if (column.getWidth() > 0) {
				writer.print(" width=\"" + column.getWidth() + "\"");
				innerWidth = column.getWidth() - (viewer.isResizeableColumns() ? 5 : 0);
				innerWidth = innerWidth - (column.getSortIcon() != TableColumn.SORT_ICON_NONE ? 8 : 0);
				if (innerWidth < 3) {
					innerWidth = 3;
				}
			}
			writer.print(" colIdx=\"" + column.getIndex() + "\" ");
			
            //header tooltip
            if (column.getToolTip() != null && column.getToolTip().length() > 0) {
                writer.print(" title=\"" + column.getToolTip() + "\"");
            }
			
			writer.println(">");
			// create cell table
			writer.print("<TABLE class=\"tbvColHeader\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"><TR>");
			writer.print("<TD class=\"" + getHeaderCssClass(column) +  "\" width=\"" + innerWidth + "\"");
			if (isColSelectable) {
				writer.print(" onClick=\"JWic.fireAction('" + viewer.getControlID() + "', 'columnSelection', '" + column.getIndex() + "')\"");
				writer.print(" onMouseDown=\"JWic.controls.TableViewer.pushColumn(" + column.getIndex() + ", '" + viewer.getControlID() + "', " + (right ? "false" : "true") + ")\"");
				writer.print(" onMouseUp=\"JWic.controls.TableViewer.releaseColumn()\"");
				writer.print(" onMouseOut=\"JWic.controls.TableViewer.releaseColumn()\"");
			}
			writer.print(">");
			writer.print("<NOBR>");
			if (column.getImage() != null) {
				writer.print("<IMG SRC=\"" + column.getImage().getPath() + "\" border=0/>");
			}
			writer.print(column.getTitle());
			writer.print("</NOBR>");
			writer.print("</TD>");
			if (column.getSortIcon() != TableColumn.SORT_ICON_NONE) {
				ImageRef imgSort = null;
				switch (column.getSortIcon()) {
				case TableColumn.SORT_ICON_UP:
					imgSort = ICON_SORTUP;
					break;
				case TableColumn.SORT_ICON_DOWN:
					imgSort = ICON_SORTDOWN;
					break;
				}
				if (imgSort != null) {
					writer.print("<TD class=\"tbvColHeadCell\" width=\"8\">");
					writer.print("<IMG SRC=\"" + imgSort.getPath() + "\" border=0>");
					writer.print("</TD>");
				}
			}
			if (isResizable) {
				writer.print("<TD class=\"tbvColHeadCellPoint\" width=\"3\"><IMG SRC=\"" + tblvWebPath + "resizer.gif\" width=\"3\" height=\"13\"");
				writer.print(" colIdx=\"" + column.getIndex() + "\"");
				writer.print(" onMouseDown=\"JWic.controls.TableViewer.resizeColumn(event, '" + viewer.getControlID() + "', " + (right ? "false" : "true") + ")\" class=\"tblResize\" border=0>");
				writer.print("</TD>");
			}
			writer.print("</TR></TABLE>");
			writer.println("</th>");
		}
		
		// if the width is fixed, we must render an empty column at the end so that the
		// browser will not adjust the columns width
		if (dataWidth != 0 && right) {
			writer.print("<TH width=\"" + dataWidth + "\">&nbsp;</TH>");
		}
		
		writer.println("</tr>");
		writer.println("</THEAD>");
		
	}
	/**
	 * @param writer
	 * @param rootIterator
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected int renderRows(int level, boolean hasNext, PrintWriter writer, Iterator<?> it, TableViewer viewer, ITableLabelProvider labelProvider, boolean right, String tblView) {
		int expandIconWidth = getExpandIconWidth();
		int expandIconHeight = getExpandIconHeight();

		TableModel model = viewer.getModel();
		IContentProvider contentProvider = model.getContentProvider();
		
		int count = 0;
		while(it.hasNext()) {
			Object row = it.next();
			count++;
			String key = contentProvider.getUniqueKey(row) ;
			boolean expanded = model.isExpanded(key);
			writer.print("<tr");
			String rowCssClass = "";
			if (!(it.hasNext() || hasNext || contentProvider.hasChildren(row))) {
				rowCssClass="lastRow";
			}

			// handle selection
			writer.print(" tbvRowKey=\"" + key + "\"");
			if (model.getSelectionMode() != TableModel.SELECTION_NONE) {
				if (viewer.isEnabled()) {
					writer.print(" onClick=\"JWic.controls.TableViewer.clickRow(this, event)\"");
					writer.print(" onDblClick=\"JWic.controls.TableViewer.clickRow(this, event, true)\"");
				}
				if (model.isSelected(key)) {
					rowCssClass = rowCssClass + "selected";
				}
			}
			if (rowCssClass.length() != 0) {
				writer.print(" class=\"" + rowCssClass + "\"");
			}
			writer.println(">");
			int c = -1;
			for (Iterator<TableColumn> itC = model.getColumnIterator(); itC.hasNext(); ) {
				TableColumn column = itC.next();
				c++;
				if (!right) {
					if (c > fixColumn) {
						break;
					}
				} else {
					if (c <= fixColumn) {
						continue;
					}
				}
				if (!column.isVisible()) {
					continue;
				}

				CellLabel cell = labelProvider.getCellLabel(row, column, new RowContext(expanded, level));
				
				writer.print("<td");
				if (column.getWidth() > 0) {
					writer.print(" width=\"" + column.getWidth() + "\"");
				}
				if (cell.cssClass != null) {
					writer.print(" class=\"" + cell.cssClass + "\"");
				}
                if (cell.toolTip != null && cell.toolTip.length() > 0) {
                    writer.print(" title=\"" + cell.toolTip + "\"");
                }
                if (cell.colspan != null && cell.colspan.intValue() > 0) {
                	writer.print(" colspan=\"" + cell.colspan + "\"");
                }

				writer.print(">");
				
				boolean innerTable = column.getIndex() == viewer.getExpandableColumn() || 
										cell.image != null && cell.text != null;
				//innerTable = true;
				if (innerTable) {
					writer.print("<table class=\"inner\" cellspacing=0 cellpadding=0 border=0><tr><td>");
					//writer.print("</p>");
				}
				// handle exp/collapse
				if (column.getIndex() == viewer.getExpandableColumn()) {
					// indent
					for (int i = 0; i < level; i++) {
						writer.print(ICON_CLEAR.toImgTag(expandIconWidth, expandIconHeight));
					}
					if (contentProvider.hasChildren(row)) {
						if (viewer.isEnabled()) {
							writer.print("<a href=\"#\" onClick=\"return ");
							writer.print(expanded ? "trV_Collapse(event)" : "trV_Expand(event)");
							writer.print("\";return false;\">");
							writer.print((expanded ? ICON_COLLAPSE : ICON_EXPAND).toImgTag(expandIconWidth, expandIconHeight));
							writer.print("</A>");
						} else {
							writer.print((expanded ? ICON_COLLAPSE : ICON_EXPAND).toImgTag(expandIconWidth, expandIconHeight));
						}
					} else {
						if (isExpandLinkSpacing()) {
							writer.print(ICON_CLEAR.toImgTag(expandIconWidth, expandIconHeight));
						}
					}
					//writer.print("</td><td class=\"content\">");
				}
				
				// print cell value
				if (cell.image != null) {
					writer.print(cell.image.toImgTag());
					if (cell.text != null) {
						//writer.print("</td><td class=\"content\">");
					}
				}
				if (cell.text != null) {
					writer.print("<NOBR>");
					if (cell.text.length() == 0 && cell.image == null) {
						writer.print("&nbsp;");
					} else {
						writer.print(cell.text);
					}
					writer.print("</NOBR>");
				}
				if (innerTable) {
					writer.print("</td></tr></table>");
					//writer.print("</p>");
				}				
				writer.println("</td>");
				
			}
			// if its a fixed width, must render an empty column that fills up the space.
			if (viewer.getWidth() != 0 && right) {
				writer.println("<TD>&nbsp;</TD>");
			}
			writer.println(" </tr>");
			
			if (contentProvider.hasChildren(row) && expanded) {
				renderRows(level + 1, hasNext || it.hasNext(), writer, contentProvider.getChildren(row), viewer, labelProvider, right, tblView);
			}
			
		}
		return count;
	}

	/**
	 * Returns CssClass used for table headers.
	 * @param column
	 * @return
	 */
	protected String getHeaderCssClass(TableColumn column) {
		return "tbvColHeadCell";
	}

	/**
	 * Get new logger after deserialization.
	 * @param s
	 * @throws IOException
	 */
	private void readObject(ObjectInputStream s) throws IOException  {
		try {
			s.defaultReadObject();
		} catch (ClassNotFoundException e) {
			throw new IOException("ClassNotFound in readObject");
		}
		log = LogFactory.getLog(getClass());
	}

	/**
	 * @return the fixColumn
	 */
	public int getFixColumn() {
		return fixColumn;
	}
	
	/**
	 * @param fixColumn the fixColumn to set
	 */
	public void setFixColumn(int fixColumn) {
		this.fixColumn = fixColumn;
	}

	/**
	 * @return the reuseContent
	 */
	public boolean isReuseContent() {
		return reuseContent;
	}

	/**
	 * @param reuseContent the reuseContent to set
	 */
	public void setReuseContent(boolean reuseContent) {
		this.reuseContent = reuseContent;
	}
	
}
