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
 * de.jwic.ecolib.async.ProcessEvent
 * Created on 29.04.2008
 * $Id: ProcessEvent.java,v 1.1 2008/04/29 14:37:14 lordsam Exp $
 */
package de.jwic.ecolib.async;

/**
 *
 * @author Florian Lippisch
 */
public class ProcessEvent {

	private IAsyncProcess process = null;

	/**
	 * @param process
	 */
	public ProcessEvent(IAsyncProcess process) {
		super();
		this.process = process;
	}

	/**
	 * @return the process
	 */
	public IAsyncProcess getProcess() {
		return process;
	}
	
}
