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
 * de.jwic.ecolib.objectlinktool.RowElementContentProvider
 * Created on 19.04.2007
 * $Id: RowElementContentProvider.java,v 1.1 2007/04/25 09:59:13 lordsam Exp $
 */
package de.jwic.ecolib.objectlinktool;

import java.util.ArrayList;
import java.util.List;

import de.jwic.ecolib.tableviewer.defaults.ListContentProvider;

/**
 *
 * @author Florian Lippisch
 */
public class RowElementContentProvider extends ListContentProvider {

	/**
	 * @param list
	 */
	public RowElementContentProvider(List list) {
		super(list);
	}

	/**
	 * Construct with an empty list.
	 */
	public RowElementContentProvider() {
		super(new ArrayList());
	}

	/**
	 * Set a new List.
	 * @param list
	 */
	public void setData(List list) {
		this.data = list;
	}
	
	/**
	 * Returns the RowElement with the specified key.
	 * @param key
	 * @return
	 */
	public RowElement getElementByKey(String key) {
		
		if (key != null && key.length() != 0) {
			return (RowElement)data.get(Integer.parseInt(key));
		}
		return null;
		
	}
	
}
