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
 * de.jwic.sourceviewer.model.RegTest
 * Created on 27.04.2007
 * $Id: RegTest.java,v 1.1 2007/04/27 11:36:10 lordsam Exp $
 */
package de.jwic.sourceviewer.model;

import java.util.regex.Pattern;

import junit.framework.TestCase;

/**
 *
 * @author Florian Lippisch
 */
public class RegTest extends TestCase {

	
	public void testReg() {
		
		String regexp = ".java$";
		String string = "This_is_a_test.java";
		
		Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
		
		System.out.println(pattern.matcher(string).replaceAll("X"));
		System.out.println(pattern.matcher(string).matches());
		System.out.println(pattern.matcher(string).find(0));
		
	}
	
}
