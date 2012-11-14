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
 * de.jwic.ecolib.tableviewer.IContentProvider
 * Created on 12.03.2007
 * $Id: IContentProvider.java,v 1.3 2008/09/18 18:19:53 lordsam Exp $
 */
package de.jwic.ecolib.tableviewer;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Provides objects displayed by a TableViewer.
 * 
 * @author Florian Lippisch
 */
public interface IContentProvider<A> extends Serializable {

	/**
	 * Returns an iterator for the specified range.
	 * 
	 * @param range
	 * @return
	 */
	public Iterator<A> getContentIterator(Range range);
	
	/**
	 * Returns the total number of elements that may be displayed.
	 * @return
	 */
	public int getTotalSize();
	
	/**
	 * Returns a string that identifies for the object provided
	 * by the ContentProvider implementation. This method must
	 * be implemented to support any SelectionMode.
	 * @param object
	 * @return
	 */
	public String getUniqueKey(A object);

	/**
	 * Returns the children of the specified object.
	 * @param object
	 * @return
	 */
	public Iterator<A> getChildren(A object);
	
	/**
	 * Returns true if the specified object has children.
	 * @param object
	 * @return
	 */
	public boolean hasChildren(A object);

}
