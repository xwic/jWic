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
 * de.jwic.ecolib.async.IResultViewer
 * Created on 29.04.2008
 * $Id: IResultViewer.java,v 1.1 2008/04/29 14:37:15 lordsam Exp $
 */
package de.jwic.ecolib.async;

import de.jwic.base.IControl;

/**
 * Controls that display the result of a process must
 * implement this interface.
 * @author Florian Lippisch
 */
public interface IResultViewer extends IControl {

	/**
	 * The result of the finished process.
	 * @param result
	 */
	public void setResult(Object result);
	
}
