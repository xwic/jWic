/*
 * de.jwic.renderer.velocity.BasicControlRenderer
 * $Id: BasicControlRenderer.java,v 1.5 2010/05/03 12:52:36 lordsam Exp $
 */
package de.jwic.renderer.velocity;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Iterator;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.IControlRenderer;
import de.jwic.base.JWicRuntime;
import de.jwic.base.JavaScriptSupport;
import de.jwic.base.RenderContext;
import de.jwic.renderer.util.ChildRenderer;

/**
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.5 $
 */
public class BasicControlRenderer extends BaseVelocityRenderer {

	/**
	 * @throws Exception
	 */
	public BasicControlRenderer() throws Exception {
		super();
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.ControlRenderer#renderControl(de.jwic.base.Control, java.io.Writer)
	 */
	public void renderControl(Control control, RenderContext context) {

		PrintWriter writer = context.getWriter();
		try {

			control.setRequireRedraw(false);
			
			boolean isContainer = control instanceof IControlContainer;
			String tplName = control.getTemplateName();
			Template tpl = null;
			if (tplName != null) {
				tpl = getTemplate(tplName);
			}
			String ctrlDivID = "ctrl_" + control.getControlID();//.replace('.', '_');
			
			if (!control.isVisible()) {	// DO NOT send JS Code if control is not visible...
				// JBO 2005-09-06: render span tag for invisible controls, better ajax support
				writer.print("<span id=\"" + ctrlDivID + "\">");
				writer.print("</span>");
				return;
			}
				
			if (control.getClass().isAnnotationPresent(JavaScriptSupport.class)) {
				String jsTemplateName = control.getClass().getAnnotation(JavaScriptSupport.class).jsTemplate();
				
				Template tplJsStatic = getTemplate(jsTemplateName.length() == 0 ? tplName : jsTemplateName, ".static.js");
				if (tplJsStatic != null) {
					context.addRequiredJSContent(tplName.replace('.', '/') + ".static.js");
				}

				Template tplJs = getTemplate(jsTemplateName.length() == 0 ? tplName : jsTemplateName, ".js");
				if (tplJs != null) {
					VelocityContext vCtx = createContext(control);
					vCtx.put("control", control);
					
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					PrintWriter pw = new PrintWriter(out); 
					tplJs.merge(vCtx, pw);
					pw.flush();
					pw.close();
					context.addScript(control.getControlID(), out.toString());
				}
			}
			
			if (tpl != null) {
				log.debug("Rendering '" + control.getControlID() + "' using template '" + tplName + "'");
				VelocityContext vCtx = createContext(control);
				vCtx.put("control", control);
				if (isContainer) {
					vCtx.put("insert", new ChildRenderer((IControlContainer)control, context));
				}
				
				writer.print("<span id=\"" + ctrlDivID + "\">");
				tpl.merge(vCtx, writer);
				writer.print("</span>");
			} else {
				if (!isContainer) {
					writer.print("[ Template '" + tplName + "' not found for: " + control.getControlID() + "]");
				} else {
					// no template -> render all controls "one by one"
					log.debug("Rendering '" + control.getControlID() + "' without template.");
					writer.print("<span id=\"" + ctrlDivID + "\">");
					for (Iterator<Control> it = ((IControlContainer)control).getControls(); it.hasNext(); ) {
						Control child = it.next();
						IControlRenderer renderer = JWicRuntime.getRenderer(child.getRendererId());
						renderer.renderControl(child, context);
					}
					writer.print("</span>");
				}
			}

		} catch (Exception e) {
			writer.print("ERR (" + e + ")");
		}

	}
}
