/*
 * Copyright 2005 jWic group (http://www.jwic.de)
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
 * de.jwic.samples.sample4.Row
 * Created on 04.11.2005
 * $Id: Row.java,v 1.2 2006/08/14 09:35:00 lordsam Exp $
 */
package de.jwic.controls.layout;

import java.io.Serializable;

/**
 * Specifies a row during rendering.
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class Row implements Serializable {

	private static final long serialVersionUID = 1L;

	private int num;
	private String height = "";
	
	/**
	 * Constructs a new row.
	 * @param num
	 */
	public Row(int num) {
		this.num = num;
	}

	/**
	 * @return Returns the height.
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @param height The height to set.
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * @return Returns the num.
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num The num to set.
	 */
	public void setNum(int num) {
		this.num = num;
	}
	
}
