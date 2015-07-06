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
