/*
 * Copyright 2005 jWic Group (http://www.jwic.de)
 * de.jwic.test.JWicTestSuite
 * Created on 19.11.2004
 * $Id: JWicTestSuite.java,v 1.5 2006/10/06 08:52:02 lordsam Exp $
 */
package de.jwic.test;

import de.jwic.base.ControlMovementTest;
import de.jwic.base.ControlTest;
import de.jwic.base.TestLifecycle;
import de.jwic.controls.AnchorLinkControlTest;
import de.jwic.controls.ButtonControlTest;
import de.jwic.controls.InputBoxControlTest;
import de.jwic.renderer.util.JWicToolsTest;
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
		ts.addTestSuite(ControlTest.class);

		// control tests
		ts.addTestSuite(AnchorLinkControlTest.class);
		ts.addTestSuite(ButtonControlTest.class);
		ts.addTestSuite(InputBoxControlTest.class);
		ts.addTestSuite(ControlMovementTest.class);
		ts.addTestSuite(JWicToolsTest.class);

		return ts;
	}

}
