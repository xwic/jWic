/*
 * Copyright 2005-2007 jWic group (http://www.jwic.de)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * de.jwic.ecolib.objectlinktool.AbstractMatcherTest
 * Created on 19.04.2007
 * $Id: AbstractMatcherTest.java,v 1.1 2007/04/25 09:59:16 lordsam Exp $
 */
package de.jwic.ecolib.objectlinktool;

import junit.framework.TestCase;

/**
 *
 * @author Florian Lippisch
 */
public class AbstractMatcherTest extends TestCase {

	private class TestMatcher extends AbstractMatcher {
		protected double evaluateString(String a, String b) {
			double result = super.evaluateString(a, b);
			System.out.println("a [" + a + "] : [" + b + "] = " + result);
			return result;
		}
	}
	
	
	public void testStringEval() {
		
		TestMatcher matcher = new TestMatcher();
		
		double result = matcher.evaluateString("Test", "Test");
		assertEquals((double)1, result,  0);
		
		result = matcher.evaluateString("Test", "Test1");
		
		result = matcher.evaluateString("Test", "TestTest");
		
		result = matcher.evaluateString("Test 1 Hugobert", "Test Hugobert");

		result = matcher.evaluateString("Test 1 Hugo", "Test 1 Moni");
		
	}
	
}
