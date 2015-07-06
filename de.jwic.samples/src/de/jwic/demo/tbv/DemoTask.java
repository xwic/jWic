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
package de.jwic.demo.tbv;

import java.io.Serializable;

/**
 * Represents a row in the demo tasklist. 
 */
public class DemoTask implements Serializable {
	
	private static int nextId = 0;
	int id = nextId++; // unique id
	boolean done = false;
	String title = "";
	String owner = "";
	int completed = 0;
	
	/**
	 * default constructor.
	 */
	public DemoTask() {
		
	}
	/**
	 * @param title
	 * @param owner
	 * @param completed
	 */
	public DemoTask(String title, String owner, int completed) {
		super();
		this.title = title;
		this.owner = owner;
		this.completed = completed;
	}
}