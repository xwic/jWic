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
package de.jwic.renderer.jsp;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.IControlRenderer;
import de.jwic.base.RenderContext;
import de.jwic.renderer.util.ChildRenderer;
import de.jwic.renderer.util.JWicTools;

/**
 * Renders controls using a JSP page. The jsp pages must be stored in the
 * directory /WEB-INF/jwic/jsp. The filename is the templatename with a
 * ".jsp" extension.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.4 $
 */
public class JspRenderer implements IControlRenderer {

	protected final Log log = LogFactory.getLog(getClass());

	/* (non-Javadoc)
	 * @see de.jwic.base.IControlRenderer#renderControl(de.jwic.base.Control, de.jwic.base.RenderContext)
	 */
	public void renderControl(Control control, RenderContext context) {
	
		if (log.isDebugEnabled()) {
			log.debug("Rendering control '" + control.getControlID() + "' with template '" + control.getTemplateName() + "'");
		}
		
		String ctrlDivID = "ctrl_" + control.getControlID();//.replace('.', '_');
		PrintWriter writer = context.getWriter();
		if (!control.isVisible()) {
			writer.print("<span id=\"" + ctrlDivID + "\">");
			writer.print("</span>");
			return;
		}

		HttpServletRequest request = context.getRequest();
		request.setAttribute("control", control);
		request.setAttribute("jwic", new JWicTools(control.getSessionContext().getLocale(), control.getSessionContext().getTimeZone()));
		if (control instanceof IControlContainer) {
			request.setAttribute("insert", new ChildRenderer((IControlContainer)control, context));			
		}
		
		RequestDispatcher dispatcher = context.getRequest().getRequestDispatcher("/WEB-INF/jwic/jsp/" + control.getTemplateName() + ".jsp");
		try {
			HttpServletResponseFacade res = new HttpServletResponseFacade(context.getResponse(), context.getWriter());
			writer.print("<span><span id=\"" + ctrlDivID + "\">");
			dispatcher.include(context.getRequest(), res);
			writer.print("</span></span>");

		} catch (Exception e) {
			log.error("Error including jsp page", e);
			context.getWriter().write("Error rendering Control (" + e + ")");
		}

	}

}
