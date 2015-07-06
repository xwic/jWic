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
 * Thrown when a control was not found.
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public class ControlNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6009335074727417445L;

	private String controlId = "unknown";
	/**
	 * 
	 */
	public ControlNotFoundException() {
		super();
	}
	/**
	 * @param message
	 */
	public ControlNotFoundException(String message) {
		super(message);
	}
	/**
	 * @param message
	 * @param cause
	 */
	public ControlNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	/**
	 * @param cause
	 */
	public ControlNotFoundException(Throwable cause) {
		super(cause);
	}
	/**
	 * @return Returns the controlTypeId.
	 */
	public String getControlId() {
		return controlId;
	}
	/**
	 * @param controlTypeId The controlTypeId to set.
	 */
	public void setControlId(String controlId) {
		this.controlId = controlId;
	}
}
