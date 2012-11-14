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
 * de.jwic.ecolib.objectlinktool.ILinkMatcher
 * Created on 19.04.2007
 * $Id: ILinkMatcher.java,v 1.1 2007/04/25 09:59:13 lordsam Exp $
 */
package de.jwic.ecolib.objectlinktool;

import java.io.Serializable;

/**
 * Determines how much two RowElements match to each other. 
 * @author Florian Lippisch
 */
public interface ILinkMatcher extends Serializable {

	/**
	 * Returns an indicator how good the two elements match together. The higher the returned 
	 * value is, the better they match each other. A score of 1 means exact match.
	 * @param left
	 * @param right
	 * @return
	 */
	public double evaluate(RowElement left, RowElement right);
	
}
