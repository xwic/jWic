/*
 * Copyright (c) 2009 Network Appliance, Inc.
 * All rights reserved.
 */

package de.jwic.controls;

import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.DatePicker;
import de.jwic.controls.DateTimePicker;

/**
 * This DateTimePicker can be linked to another DateTimePicker to always update the "slave"
 * when this one is updated. When this controls date is changed and the slaves control has
 * no date or had the same date as this control had before it was changed, it is updated. 
 * This is all done through JavaScript events on the client side.
 * 
 * @author lippisch
 * 
 * @author dotto
 * @version 2.0 - Replaced by jQuery solution
 */
@JavaScriptSupport(jsTemplate="de.jwic.controls.DateTimeMasterPicker")
public class DateTimeMasterPicker extends DateTimePicker {
	
	private DateTimePicker slave;

	/**
	 * @param container
	 */
	public DateTimeMasterPicker(IControlContainer container) {
		super(container);
		setTemplateName(DatePicker.class.getName());
	}

	/**
	 * @param container
	 * @param name
	 */
	public DateTimeMasterPicker(IControlContainer container, String name) {
		super(container, name);
	}

	/**
	 * @return the slave
	 */
	public DatePicker getSlave() {
		return slave;
	}
	
	/**
	 * Returns the id of the slave.
	 * @return
	 */
	public String getSlaveId() {
		return slave != null ? slave.getControlID() : null;
	}

	/**
	 * @param slave the slave to set
	 */
	public void setSlave(DateTimePicker slave) {
		this.slave = slave;
	}

}
