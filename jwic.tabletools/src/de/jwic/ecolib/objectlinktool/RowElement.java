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
 * de.jwic.ecolib.objectlinktool.RowElement
 * Created on 13.04.2007
 * $Id: RowElement.java,v 1.1 2007/04/25 09:59:13 lordsam Exp $
 */
package de.jwic.ecolib.objectlinktool;

import java.io.Serializable;

/**
 * Stores the key and the column data for each element in the list.
 * 
 * @author Florian Lippisch
 */
public class RowElement implements Serializable {

	private boolean empty = false;
	private double score = 0;
	private String key = null;
	private String[] data = null;
	
	
	/**
	 * Construct an empty row element.
	 *
	 */
	public RowElement() {
		empty = true;
	}
	
	/**
	 * Construct a new RowElement.
	 * @param key
	 * @param data
	 */
	public RowElement(String key, String[] data) {
		empty = false;
		this.key = key;
		this.data = data;
	}
	
	/**
	 * @return the data
	 */
	public String[] getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String[] data) {
		this.data = data;
	}
	/**
	 * @return the empty
	 */
	public boolean isEmpty() {
		return empty;
	}
	/**
	 * @param empty the empty to set
	 */
	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the score
	 */
	public double getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}
	
	
	
}
