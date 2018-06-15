/*
 * Copyright (c) NetApp Inc. - All Rights Reserved
 * 
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * 
 * de.jwic.controls.slickgrid.SlickGridChangeEvent 
 */
package de.jwic.controls.slickgrid;

import de.jwic.base.Event;

/**
 * @author Adrian Ionescu
 */
public class SlickGridChangeEvent extends Event {

	private SlickGridChange change;

	/**
	 * @param eventSource
	 */
	public SlickGridChangeEvent(Object eventSource, SlickGridChange change) {
		super(eventSource);
		
		this.change = change;
	}
	
	/**
	 * @return the change
	 */
	public SlickGridChange getChange() {
		return change;
	}

}
