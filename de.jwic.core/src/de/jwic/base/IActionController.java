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

import de.jwic.upload.UploadFile;

/**
 * The ActionController maps actions recieved by a client to the corrosponding controls.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public interface IActionController {

	/**
	 * Handle a field by storing the value at the corrosponding control. 
	 * @param sc
	 * @param fieldname
	 * @param value
	 */
	public void handleField(SessionContext sc, ValueChangedQueue queue, String fieldname, String[] values);
	
	/**
	 * Handle a file by storing the UploadFile object at the corrosponding control. 
	 * @param sc
	 * @param fieldname
	 * @param file
	 */
	public void handleFile(SessionContext sc, String fieldname, UploadFile file);
	
	/**
	 * Notify the corrosponding control that a link was clicked the control has created.
	 * @param sc
	 * @param action
	 * @param param
	 */
	public void handleAction(SessionContext sc, String ctrlId, String action, String param);
	
}
