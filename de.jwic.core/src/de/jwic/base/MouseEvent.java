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
package de.jwic.base;

/**
 * @author jbornema
 *
 */
public class MouseEvent extends Event {

	private static final long serialVersionUID = 1L;
	
	public static String CLICK = "click";
	public static String CONTEXTMENU = "contextmenu";
	
	private String type;
	private int x;
	private int y;
	
	/**
	 * @param eventSource
	 */
	public MouseEvent(Object eventSource, String type, int x, int y) {
		super(eventSource);
		this.type = type;
		this.x = x;
		this.y = y;
	}

	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @return Returns the x.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return Returns the y.
	 */
	public int getY() {
		return y;
	}
}
