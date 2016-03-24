package de.jwic.controls.tableviewer;

import de.jwic.base.RenderContext;

/**
 * Used to render the table.
 * 
 * @author vedad
 *
 */
public interface IMTableRenderer {
	
	public void renderMTable(RenderContext renderContext, TableViewer viewer, TableModel model, ITableLabelProvider tableLabelProvider);
}
