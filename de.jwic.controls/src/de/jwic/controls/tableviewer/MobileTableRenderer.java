/**
 * 
 */
package de.jwic.controls.tableviewer;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.jwic.base.Control;
import de.jwic.base.IControlRenderer;
import de.jwic.base.ImageRef;
import de.jwic.base.JWicRuntime;
import de.jwic.base.RenderContext;
import de.jwic.data.IContentProvider;
import de.jwic.data.Range;

/**
 * 
 * Default implementation of the ITableRenderer interface. The default renderer
 * generates HTML "by code", without using templates. The reason for this is
 * because its faster, costs less resources and the generated HTML is quite
 * complex so that templates would be quite ugly anyway.
 * 
 * @author vedad
 *
 */
public class MobileTableRenderer implements ITableRenderer, Serializable {

	private static final long serialVersionUID = 1L;
	
	/** default icon used for sort-up image */
	public final static ImageRef ICON_SORTUP = new ImageRef("/jwic/gfx/sortup.gif");
	/** default icon used for sort-down image */
	public final static ImageRef ICON_SORTDOWN = new ImageRef("/jwic/gfx/sortdn.gif");

	protected transient Log log = LogFactory.getLog(getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.jwic.controls.tableviewer.IMTableRenderer#renderMTable(de.jwic.base.
	 * RenderContext, de.jwic.controls.tableviewer.TableViewer,
	 * de.jwic.controls.tableviewer.TableModel,
	 * de.jwic.controls.tableviewer.ITableLabelProvider)
	 */
	@Override
	public void renderTable(RenderContext renderContext, TableViewer viewer, TableModel model,
			ITableLabelProvider labelProvider) {

		renderContext.addScript(viewer.getControlID(),
				"{ afterUpdate: function(element) {JWic.mobile.TableViewer.initialize(JWic.$('" + viewer.getControlID()
						+ "'), '" + viewer.getControlID() + "', {" + " colResize : " + viewer.isResizeableColumns()
						+ ", menu : "
						+ (viewer.getMenu() != null ? "\'" + viewer.getMenu().getControlID() + "\'"
								: "null" + ", fitToParent:" + model.isFitToParent() + ", defaults : "
										+ viewer.getModel().isDefaults() + ", disabled : "
										+ viewer.getModel().isDisabled())
						+ "});}}");

		PrintWriter writer = renderContext.getWriter();

		IContentProvider<?> contentProvider = model.getContentProvider();

		for (Iterator<TableColumn> it = model.getColumnIterator(); it.hasNext();) {
			TableColumn tc = it.next();
			if (!tc.isVisible()) {
				continue;
			}
		}
		
		writer.print("<div style=\"overflow:auto; width: 100%; \">");

		writer.print("<table data-role=\"table\" id=\"" + viewer.getControlID() + "\"" + " data-mode=\"columntoggle\""
				+ "class=\"" + viewer.getmCssClass() + "\"");
		writer.print(">");

		if (viewer.isShowHeader())
			renderMHeader(writer, model, viewer);

		Range range = model.getRange();
		if (range.getMax() == 0) { // = Auto
			int max = -1;
			int rowSpace = viewer.getHeight() - (35 + 20);

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

		writer.println("<TBODY");
		writer.print(" tbvctrlid=\"" + viewer.getControlID() + "\"");
		
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
		
		writer.print(sbTblSelAttrs);
		writer.println(">");
		
		try {
			int count = renderMRows(0, false, writer, contentProvider.getContentIterator(range), viewer, labelProvider);

			if (count == 0) {
				renderEmptyRow(writer, viewer);
			}

			model.setLastRowRenderCount(count);
		} catch (Exception e) {
			writer.println("Error reading data from ContentProvider: " + e);
			log.error("Error reading data from ContentProvider", e);
		}

		writer.println("</TBODY>");
		writer.println("</table>");
		writer.println("</div>");

		if (viewer.isShowMStatusBar()) {
			writer.println("<TABLE>");
			writer.println("<TBODY>");
			writer.println("<tr><td>");
			// render context
			Control sb = viewer.getControl("mStatusBar");
			IControlRenderer renderer = JWicRuntime.getRenderer(sb.getRendererId());
			renderer.renderControl(sb, renderContext);
			writer.print("</td></tr>");
			writer.println("</TABLE>");
			writer.println("</TBODY>");
		}
	}

	/**
	 * 
	 */
	protected void renderMHeader(PrintWriter writer, TableModel model, TableViewer viewer) {

		boolean isResizable = viewer.isResizeableColumns() && viewer.isEnabled();
		boolean isColSelectable = viewer.isSelectableColumns() && viewer.isEnabled();

		writer.println("<THEAD>");

		for (Iterator<TableColumn> itC = model.getColumnIterator(); itC.hasNext();) {
			TableColumn column = itC.next();

			if (!column.isVisible()) {
				continue;
			}

			if (isResizable && column.getWidth() == 0) {
				// must set a default width if resizable columns is activated
				column.setWidth(150);
			}

			writer.print("<th");
			writer.print(" colIdx=\"" + column.getIndex() + "\"");
			if (isColSelectable) {
				writer.print(" onClick=\"JWic.fireAction('" + viewer.getControlID() + "', 'columnSelection', '"
						+ column.getIndex() + "')\"");
			}

			writer.println(">");
			// create cell table
			writer.print(column.getTitle());

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
					writer.print("<IMG SRC=\"" + imgSort.getPath() + "\" border=0>");
				}
			}

			writer.println("</th>");
		}

		// if the width is fixed, we must render an empty column at the end so
		// that the
		// browser will not adjust the columns width
		if (viewer.getWidth() != 0) {
			if (viewer.isScrollable()) {
				writer.println("<TH width=\"" + viewer.getWidth() + "\">&nbsp;</TH>");
			} else {
				writer.println("<TH>&nbsp;</TH>");
			}
		}

		writer.println("</THEAD>");

	}

	/**
	 * @param writer
	 * @param rootIterator
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected int renderMRows(int level, boolean hasNext, PrintWriter writer, Iterator<?> it, TableViewer viewer,
			ITableLabelProvider labelProvider) {

		TableModel model = viewer.getModel();
		IContentProvider contentProvider = model.getContentProvider();

		int count = 0;
		while (it.hasNext()) {
			Object row = it.next();
			count++;
			String key = contentProvider.getUniqueKey(row);
			boolean expanded = model.isExpanded(key);
			writer.print("<tr");
			String rowCssClass = "";
			// handle selection
			writer.print(" tbvRowKey=\"" + key + "\"");
			if (model.getSelectionMode() != TableModel.SELECTION_NONE) {
				if (viewer.isEnabled()) {
					writer.print(" onClick=\"JWic.mobile.TableViewer.clickRow(this, event)\"");
					writer.print(" onDblClick=\"JWic.mobile.TableViewer.clickRow(this, event, true)\"");
				}
				if (model.isSelected(key)) {
					rowCssClass = rowCssClass + "selected";
				}
				writer.print(" class=\"" + rowCssClass + "\"");
			}
			
			writer.print(">");

			for (Iterator<TableColumn> itC = model.getColumnIterator(); itC.hasNext();) {
				TableColumn column = itC.next();
				if (!column.isVisible()) {
					continue;
				}

				CellLabel cell = labelProvider.getCellLabel(row, column, new RowContext(expanded, level));

				writer.print("<td");
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

				// print cell value
				if (cell.image != null) {
					writer.print(cell.image.toImgTag());
					if (cell.text != null) {
						writer.print("</td><td class=\"content\">");
					}
				}
				if (cell.text != null) {
					if (cell.text.length() == 0 && cell.image == null) {
						writer.print("&nbsp;");
					} else {
						writer.print(cell.text);
					}
				}
				writer.println("</td>");

			}
			// if its a fixed width, must render an empty column that fills up
			// the space.
			if (viewer.getWidth() != 0) {
				writer.println("<TD>&nbsp;</TD>");
			}
			writer.println(" </tr>");
		}
		return count;
	}

	/**
	 * @param writer
	 * @param viewer
	 */
	protected void renderEmptyRow(PrintWriter writer, TableViewer viewer) {
		writer.print("<tr class=\"lastRow\">");
		writer.print("<td colspan=\"" + (viewer.getModel().getColumnsCount() - 1) + "\" style=\"font-style: italic\">");
		writer.print("no rows available");
		writer.println("</td></tr>");
	}

}
