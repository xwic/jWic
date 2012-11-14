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
 * de.jwic.ecolib.objectlinktool.AbstractMatcher
 * Created on 19.04.2007
 * $Id: AbstractMatcher.java,v 1.1 2007/04/25 09:59:14 lordsam Exp $
 */
package de.jwic.ecolib.objectlinktool;

/**
 * Contains usefull functions that help creating the total score.
 * @author Florian Lippisch
 */
public abstract class AbstractMatcher implements ILinkMatcher {

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.objectlinktool.ILinkMatcher#evaluate(de.jwic.ecolib.objectlinktool.RowElement, de.jwic.ecolib.objectlinktool.RowElement)
	 */
	public double evaluate(RowElement left, RowElement right) {
		return 0;
	}

	/**
	 * Evaluates a score between 2 strings.
	 * @param a
	 * @param b
	 * @return
	 */
	protected double evaluateString(String a, String b) {
		
		a = a.toLowerCase();
		b = b.toLowerCase();
		
		// start scanning starting characters
		if (a.equals(b)) {
			return 1; //
		}
		
		// scan starting characters
		int i;
		int max = Math.max(a.length(), b.length());
		int min = Math.min(a.length(), b.length());
		
		for (i = 0; i < min; i++) {
			if (a.charAt(i) != b.charAt(i)) {
				break;
			}
		}
		int startingMatch = i; // number of characters that match from the beginning
		int occurences = 0;
		if (startingMatch < min) { // remaining characters
			String bRest = b.substring(startingMatch);
			for (i = startingMatch; i < min; i++) {
				if (bRest.indexOf(a.charAt(i)) != -1) {
					occurences++;
				}
			}
		}
		
		
		return ((double)(startingMatch * 2 + occurences)) / ((double)(max * 2));
	}
	
}
