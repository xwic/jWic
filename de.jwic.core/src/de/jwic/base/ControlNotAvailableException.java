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
 * de.jwic.base.ControlNotAvailableException
 * Created on 24.03.2005
 * $Id: ControlNotAvailableException.java,v 1.1 2006/01/16 08:30:40 lordsam Exp $
 */
package de.jwic.base;

/**
 * Thrown by the runtime when a control can not be created. This may be 
 * the cause if a specified class can not be found. If the desired control
 * is managed by a container, it might not be mapped.
 *  
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public class ControlNotAvailableException extends RuntimeException {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 6009335074727417445L;

	
	
	/**
	 * 
	 */
	public ControlNotAvailableException() {
		super();
	}
	/**
	 * @param message
	 */
	public ControlNotAvailableException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param message
	 * @param cause
	 */
	public ControlNotAvailableException(String message, Throwable cause) {
		super(message, cause);
	}
	/**
	 * @param cause
	 */
	public ControlNotAvailableException(Throwable cause) {
		super(cause);
	}
}
