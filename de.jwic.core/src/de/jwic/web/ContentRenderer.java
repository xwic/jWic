/*
 * de.jwic.web.ContentRenderer
 * $Id: ContentRenderer.java,v 1.2 2008/04/29 14:29:34 lordsam Exp $
 */
package de.jwic.web;

import de.jwic.base.Control;
import de.jwic.base.IControlRenderer;
import de.jwic.base.JWicRuntime;
import de.jwic.base.RenderContext;


/**
 * Used by the webEngine to render the root control.
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class ContentRenderer {
	
	private Control ctrl;
	private RenderContext context;
	
	public ContentRenderer(Control control, RenderContext context) {
		this.ctrl = control;
		this.context = context;
	}
	/**
	 * Render the root control.
	 * @return
	 */
	public void render() {
		if (ctrl != null) {
			IControlRenderer renderer = JWicRuntime.getRenderer(ctrl.getRendererId());
			renderer.renderControl(ctrl, context);
		}
	}

}
