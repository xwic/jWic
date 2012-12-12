/*
 * de.jwic.samples.controls.SlowRenderingControl 
 */
package de.jwic.samples.controls;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;

/**
 * This control is simmulating a control that takes a few seconds to render
 * because it loads data from some slow data source.
 * 
 * @author lippisch
 */
public class SlowRenderingControl extends Control {

	/**
	 * @param container
	 * @param name
	 */
	public SlowRenderingControl(IControlContainer container, String name) {
		super(container, name);
	}

	
	public String getTheAnswer() {
		try {
			Thread.sleep(3000);// sleep 3 seconds
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		return "42";
	}
	
	
}
