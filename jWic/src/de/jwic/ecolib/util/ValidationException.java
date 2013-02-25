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
 * de.jwic.wap.util.ValidationException
 * Created on 23.03.2006
 * $Id: ValidationException.java,v 1.4 2008/09/18 18:20:27 lordsam Exp $
 */
package de.jwic.ecolib.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains detailed informations about the result of a validation. Users
 * can add informations to each field that failed validation checks.
 *  
 * @author Florian Lippisch
 * @version $Revision: 1.4 $
 */
public class ValidationException extends Exception {
	private static final long serialVersionUID = 1L;
	private Map<String, String> errors = new HashMap<String, String>();
	
	/**
	 * 
	 */
	public ValidationException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ValidationException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ValidationException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * Add a field th at failed validation to the list of errors.
	 * @param field - Field that failed validation
	 * @param message - Message describing the reason
	 */
	public void addError(String field, String message) {
		errors.put(field, message);
	}
	
	/**
	 * @return Collection of error fields added.
	 */
	public Collection<String> getErrorFields() {
		return errors.keySet();
	}

	/**
	 * @return
	 */
	public boolean hasErrors() {
		return errors.size() > 0;
	}

	/**
	 * Returns true if there is an error for the specified field.
	 * @param field
	 * @return
	 */
	public boolean hasError(String field) {
		return errors.containsKey(field);
	}
	
	/**
	 * Returns the error message for the specified field.
	 * @param field
	 * @return
	 */
	public String getMessage(String field) {
		return errors.get(field);
	}

}
