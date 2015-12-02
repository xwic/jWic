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
package de.jwic.ecolib.tableviewer.defaults;

import java.util.List;

/**
 * A simple content provider that provides the data from a List object.
 * Proxy for de.jwic.data.ListContentProvider
 * 
 * @author Florian Lippisch
 * @deprecated use de.jwic.data.ListContentProivider instead.
 */
public class ListContentProvider<A> extends de.jwic.data.ListContentProvider<A> {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * @param data
	 */
	public ListContentProvider(List<A> list) {
		super(list);
	}
}