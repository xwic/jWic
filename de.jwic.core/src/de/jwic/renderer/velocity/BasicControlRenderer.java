/*******************************************************************************
 * Copyright 2015 xWic group (http://www.xwic.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *  
 *******************************************************************************/
/*
 * de.jwic.renderer.velocity.BasicControlRenderer
 * $Id: BasicControlRenderer.java,v 1.5 2010/05/03 12:52:36 lordsam Exp $
 */
package de.jwic.renderer.velocity;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

	protected Map<String, Boolean> jsStaticExists = new HashMap<String, Boolean>(); 
	
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
				final String jsTemplateName = control.getClass().getAnnotation(JavaScriptSupport.class).jsTemplate();

				final String jsTplName = jsTemplateName.length() == 0 ? tplName : jsTemplateName;
				if (hasJsStaticFile(jsTplName)) {
					context.addRequiredJSContent(jsTplName.replace('.', '/') + ".static.js");
				}

				final Template tplJs = getTemplate(jsTplName, ".js");
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

	/**
	 * Returns true if a static javascript file exists.
	 * @param jsTplName
	 * @return
	 */
	private boolean hasJsStaticFile(String jsTplName) {
		if(jsTplName == null) {
			return false;
		}
		
		String cpName = jsTplName.replace('.', '/') + ".static.js";
		Boolean exists = jsStaticExists.get(cpName);
		if (exists == null) {
			exists = getClass().getClassLoader().getResource(cpName) != null;
			jsStaticExists.put(cpName, exists);
		}
		return exists;
	}
}
