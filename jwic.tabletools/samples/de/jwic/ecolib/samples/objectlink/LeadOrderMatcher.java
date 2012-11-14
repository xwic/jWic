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
 * de.jwic.ecolib.samples.objectlink.LeadOrderMatcher
 * Created on 19.04.2007
 * $Id: LeadOrderMatcher.java,v 1.1 2007/04/25 09:59:15 lordsam Exp $
 */
package de.jwic.ecolib.samples.objectlink;

import de.jwic.ecolib.objectlinktool.AbstractMatcher;
import de.jwic.ecolib.objectlinktool.RowElement;

/**
 *
 * @author Florian Lippisch
 */
public class LeadOrderMatcher extends AbstractMatcher {

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.objectlinktool.AbstractMatcher#evaluate(de.jwic.ecolib.objectlinktool.RowElement, de.jwic.ecolib.objectlinktool.RowElement)
	 */
	public double evaluate(RowElement left, RowElement right) {
		
		// compare name and company name
		double scoreName =  evaluateString(left.getData()[1], right.getData()[1]);
		double scoreDate = evaluateString(left.getData()[3], right.getData()[3]);
		return ((scoreName * 2.0) + (scoreDate)) / 3.0;
	}
	
}
