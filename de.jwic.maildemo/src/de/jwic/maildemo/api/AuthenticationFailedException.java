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
package de.jwic.maildemo.api;

/**
 * Thrown when the logon attempt on the mail server failed.
 * 
 * @author Florian Lippisch
 */
public class AuthenticationFailedException extends Exception {

	/**
	 * 
	 */
	public AuthenticationFailedException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AuthenticationFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public AuthenticationFailedException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AuthenticationFailedException(Throwable cause) {
		super(cause);
	}

}
