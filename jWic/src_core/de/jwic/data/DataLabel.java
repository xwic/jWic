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
 * de.jwic.data.Label
 * Created on Mar 13, 2010
 * $Id: Label.java,v 1.1 2010/04/22 16:00:10 lordsam Exp $
 */
package de.jwic.data;

import de.jwic.base.ImageRef;

/**
 * Encapsulates a title and an image that can be used to display
 * an element.
 * @author lippisch
 */
public class DataLabel {

	public String text = "";
	public ImageRef image = null;
	
	public DataLabel(String text) {
		super();
		this.text = text;
	}

	public DataLabel(String text, ImageRef image) {
		super();
		this.text = text;
		this.image = image;
	}

	public DataLabel(ImageRef image) {
		super();
		this.image = image;
	}
	
}
