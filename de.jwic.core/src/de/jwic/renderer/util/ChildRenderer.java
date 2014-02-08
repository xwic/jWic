/*
 * de.jwic.renderer.velocity.ChildRenderer
 * $Id: ChildRenderer.java,v 1.1 2006/01/16 08:31:13 lordsam Exp $
 */
package de.jwic.renderer.util;

import java.util.HashSet;
import java.util.Set;

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
				final String message = String.format("Control '%s' already added to this page!", controlName);
				new IllegalArgumentException(message).printStackTrace();;
			}
			IControlRenderer renderer = JWicRuntime.getRenderer(ctrl.getRendererId());
			renderer.renderControl(ctrl, context);
			alreadyAdded.add(controlName);
		} else {
			context.getWriter().write("[no control named " + controlName + "]");
		}
		
	}

}
