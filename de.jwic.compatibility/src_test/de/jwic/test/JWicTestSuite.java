/*
 * Copyright 2005 jWic Group (http://www.jwic.de)
 * de.jwic.test.JWicTestSuite
 * Created on 19.11.2004
 * $Id: JWicTestSuite.java,v 1.5 2006/10/06 08:52:02 lordsam Exp $
 */
package de.jwic.test;

import de.jwic.base.TestLifecycle;
import de.jwic.controls.*;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @version $Revision: 1.5 $
 * @author Florian Lippisch
 */
public class JWicTestSuite {
	
	public static Test suite() {
		TestSuite ts = new TestSuite();
		
		ts.addTestSuite(TestLifecycle.class);

		// control tests
		ts.addTestSuite(ListBoxControlTest.class);
		ts.addTestSuite(ActionBarControlTest.class);
		ts.addTestSuite(DateInputBoxControlTest.class);
		ts.addTestSuite(ListControlTest.class);
		ts.addTestSuite(RadioGroupControlTest.class);
		ts.addTestSuite(TabStripControlTest.class);

		return ts;
	}

}
