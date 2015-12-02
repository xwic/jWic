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
 * JWicException is thrown by the JWic framework if errors occur that can not
 * be handled by the framework.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public class JWicException extends RuntimeException {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public JWicException() {
		super();
	}

	/**
	 * @param message
	 */
	public JWicException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public JWicException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public JWicException(Throwable cause) {
		super(cause);
	}

}
