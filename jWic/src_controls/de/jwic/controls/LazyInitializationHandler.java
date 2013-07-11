/*
 * de.jwic.controls.LazyInitializationListener 
 */
package de.jwic.controls;

import java.io.Serializable;

import de.jwic.base.IControlContainer;

/**
 * Enables lazy control initialization in containers.
 * 
 * @author lippisch
 */
public interface LazyInitializationHandler extends Serializable {

	/**
	 * Initialize the controls
	 * @param container
	 */
	public void initialize(IControlContainer container);
	
	/**
	 * 
	 */
	public void success();
	
	/**
	 * @param t
	 */
	public void failure(Throwable t);
	
}
