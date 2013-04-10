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
 * de.jwic.ecolib.samples.controls.AsyncDemoResult
 * Created on 29.04.2008
 * $Id: AsyncDemoResult.java,v 1.1 2008/04/29 14:37:14 lordsam Exp $
 */
package de.jwic.ecolib.samples.controls;

import java.text.DateFormat;
import java.util.Date;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.ecolib.async.IResultViewer;

/**
 *
 * @author Florian Lippisch
 */
public class AsyncDemoResult extends Control implements IResultViewer {

	private Object result = null;
	
	/**
	 * @param container
	 * @param name
	 */
	public AsyncDemoResult(IControlContainer container, String name) {
		super(container, name);
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.async.IResultViewer#setResult(java.lang.Object)
	 */
	public void setResult(Object result) {
		this.result = result;
	}
	
	/**
	 * Returns a throwable if an error occured.
	 * @return
	 */
	public Throwable getThrowable() {
		if (result instanceof Throwable) {
			return (Throwable)result;
		}
		return null;
	}
	
	/**
	 * Returns the result string.
	 * @return
	 */
	public String getResult() {
		if (result instanceof Date) {
			return DateFormat.getDateTimeInstance().format(result);
		}
		return "Result is NULL or not a date";
	}

}
