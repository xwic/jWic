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
 * de.jwic.renderer.velocity.ChildRenderer
 * $Id: ChildRenderer.java,v 1.1 2006/01/16 08:31:13 lordsam Exp $
 */
package de.jwic.renderer.util;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.IControlRenderer;
import de.jwic.base.JWicRuntime;
import de.jwic.base.RenderContext;

/**
 * Used as a context object to insert a control at a specified location. 
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public class ChildRenderer {
	
	private final static Log log = LogFactory.getLog(ChildRenderer.class); 
	
	private IControlContainer container;
	private RenderContext context;

	private final Set<String> alreadyAdded = new HashSet<String>();

	public ChildRenderer(IControlContainer controlContainer, RenderContext context) {
		this.container = controlContainer;
		this.context = context;
	}
	/**
	 * Renders a child control.
	 * @param controlName
	 */
	public void control(String controlName) {
		
		Control ctrl = container.getControl(controlName);
		if (ctrl != null) {
			if (alreadyAdded.contains(controlName)){
				final String warning = "[control '" + controlName + "' already rendered!]";
				log.error(warning, new IllegalStateException());
				context.getWriter().write(warning);
				return;
			}
			IControlRenderer renderer = JWicRuntime.getRenderer(ctrl.getRendererId());
			renderer.renderControl(ctrl, context);
			alreadyAdded.add(controlName);
		} else {
			context.getWriter().write("[no control named " + controlName + "]");
		}
		
	}

}
