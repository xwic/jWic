/*
 * Copyright 2005 jWic Group (http://www.jwic.de)
 * de.jwic.controls.ListBoxControlTest
 * Created on 19.11.2004
 * $Id: ListBoxControlTest.java,v 1.4 2010/02/07 14:24:24 lordsam Exp $
 */
package de.jwic.controls;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;

/**
 * @version $Revision: 1.4 $
 * @author Florian Lippisch
 */
public class ListBoxControlTest extends ListControlTest {

	private ListBoxControl lbox = null;
	
	/* (non-Javadoc)
	 * @see de.jwic.test.ControlTestCase#createControl(de.jwic.base.IControlContainer)
	 */
	public Control createControl(IControlContainer container) {
		lbox = new ListBoxControl(container);
		list = lbox;
		return list;
	}
	
}
