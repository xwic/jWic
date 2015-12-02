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
