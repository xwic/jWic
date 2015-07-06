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
package de.jwic.renderer.self;

import java.io.PrintWriter;

import de.jwic.base.Control;
import de.jwic.base.IControlRenderer;
import de.jwic.base.RenderContext;

/**
 * This renderer passes the renderContext to a control so that it can
 * render itself to HTML. This rendering method may be used by controls
 * that do not use templates and create the HTML "by code". 
 * 
 * A control that wants to use this renderer must implement the ISelfRenderingControl
 * interface.
 * 
 * @author Florian Lippisch
 */
public class SelfRenderer implements IControlRenderer {

	public final static String RENDERER_ID = "jwic.selfRenderer";
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IControlRenderer#renderControl(de.jwic.base.Control, de.jwic.base.RenderContext)
	 */
	public void renderControl(Control control, RenderContext context) {
		
		control.setRequireRedraw(false);
		PrintWriter writer = context.getWriter();
		String ctrlDivID = "ctrl_" + control.getControlID();//.replace('.', '_');
		
		writer.print("<span id=\"" + ctrlDivID + "\">");
		if (control instanceof ISelfRenderingControl) {
			ISelfRenderingControl sfCtrl = (ISelfRenderingControl)control;
			if (control.isVisible()) {
				sfCtrl.render(context);
			}
		} else {
			writer.write("[" + control.getControlID() + " does not implement ISelfRenderingControl]");
		}
		writer.print("</span>");

	}

}
