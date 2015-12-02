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
 * de.jwic.renderer.velocity.OuterContainerRenderer
 * $Id: OuterContainerRenderer.java,v 1.4 2008/09/18 18:17:24 lordsam Exp $
 */
package de.jwic.renderer.velocity;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.IControlRenderer;
import de.jwic.base.IOuterLayout;
import de.jwic.base.JWicRuntime;
import de.jwic.base.JavaScriptSupport;
import de.jwic.base.RenderContext;
import de.jwic.renderer.util.ChildRenderer;

/**
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.4 $
 */
public class OuterContainerRenderer extends BaseVelocityRenderer {

	private IControlRenderer containerRenderer = null;
	
	public class OuterContentRenderer {
		
		private Control ctrl = null;
		private RenderContext context = null;
		IControlRenderer renderer = null;
		
		public OuterContentRenderer(Control control, IControlRenderer renderer, RenderContext context) {
			this.ctrl = control;
			this.context = context;
			this.renderer = renderer;
		}
		/**
		 * Render the root control.
		 * @return
		 */
		public void render() {
			
			renderer.renderControl(ctrl, context);
			
		}

	}

	
	/**
	 * Default constructor.
	 * @throws Exception
	 */
	public OuterContainerRenderer() throws Exception {
		super();
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.ControlRenderer#renderControl(de.jwic.base.Control, java.io.Writer)
	 */
	public void renderControl(Control control, RenderContext context) {

		PrintWriter writer = context.getWriter();
		try {
			if (control instanceof IOuterLayout) {
				control.setRequireRedraw(false); // clear redraw flag
				String ctrlDivID = "ctrl_" + control.getControlID();//.replace('.', '_');

				boolean isContainer = control instanceof IControlContainer;
				String tplName = ((IOuterLayout)control).getOuterTemplateName();

				if (control.getClass().isAnnotationPresent(JavaScriptSupport.class)) {
					String jsTemplateName = control.getClass().getAnnotation(JavaScriptSupport.class).jsTemplate();
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

				if (!control.isVisible()) {
					// JBO 2005-09-06: render span tag for invisible controls, better ajax support
					writer.print("<span id=\"" + ctrlDivID + "\">");
					writer.print("</span>");
					return;
				}
					
				log.debug("Rendering control '" + control.getControlID() + "' using template '" + tplName + "'");
				Template tpl = getTemplate(tplName);
				VelocityContext vCtx = createContext(control);
				vCtx.put("control", control);
				vCtx.put("content", new OuterContentRenderer(control, getContainerRenderer(), context));
				if (isContainer) {
					vCtx.put("insert", new ChildRenderer((IControlContainer)control, context));
				}
				
				// FLI 2005-05-24: To emulate the outerHTML setter method in a mozilla
				// browser, a <span> tag requires a single parent, wich is another span tag.
				
				writer.print("<span id=\"" + ctrlDivID + "\">");
				tpl.merge(vCtx, writer);
				writer.print("</span>");
			} else {
				writer.print("[Not instance of IOuterLayout: " + control.getControlID() + "]");
			}
						
		} catch (Exception e) {
			writer.print("ERR (" + e + ")");
		}

	}

	/**
	 * @return Returns the containerRenderer.
	 */
	public IControlRenderer getContainerRenderer() {
		if (containerRenderer == null) {
			return JWicRuntime.getRenderer("jwic.renderer.Default");
		}
		return containerRenderer;
	}
	/**
	 * @param containerRenderer The containerRenderer to set.
	 */
	public void setContainerRenderer(IControlRenderer containerRenderer) {
		this.containerRenderer = containerRenderer;
	}
}
