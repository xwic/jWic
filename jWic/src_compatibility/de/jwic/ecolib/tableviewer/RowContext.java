/*
 * Copyright 2007 jWic group (http://www.jwic.de)
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
 * de.jwic.ecolib.treeviewer.RowContext.java
 * Created on Apr 12, 2007
 * $Id: RowContext.java,v 1.1 2007/04/20 12:46:19 lordsam Exp $
 * @author jbornema
 */

package de.jwic.ecolib.tableviewer;

/**
 * Created on Apr 12, 2007
 * @author jbornema
 */
public class RowContext {
	
	private boolean expanded;
	private int level;
	
	/**
	 * 
	 */
	public RowContext() {
		super();
	}

	/**
	 * Constructor.
	 * @param expanded
	 * @param level
	 */
	public RowContext(boolean expanded, int level) {
		this.expanded = expanded;
		this.level = level;
	}
	
	/**
	 * @return the expanded
	 */
	public boolean isExpanded() {
		return expanded;
	}
	
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
}
