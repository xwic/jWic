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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.jwic.upload.UploadFile;

/**
 * Default implementation of the IActionController interface.
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class DefaultActionController implements IActionController {
	
	protected final Log log = LogFactory.getLog(getClass());

	/* (non-Javadoc)
	 * @see de.jwic.base.IActionController#handleField(de.jwic.base.SessionContext, java.lang.String, java.lang.String)
	 */
	public void handleField(SessionContext sc, ValueChangedQueue queue, String fieldname, String[] values) {
		
		String[] ctrlIds = getControlIdFromField(fieldname);
		if (ctrlIds != null) {
			Control ctrl = sc.getControlById(ctrlIds[0]);
			Field field = ctrl.getField(ctrlIds[1]);
			if (field != null) {
				if (log.isDebugEnabled()) {
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < values.length; i++) {
						if (i > 0) {
							sb.append(";");
						}
						sb.append(values[i]);
					}
					log.debug("Saving (" + values.length + ") values '" + sb + "' to field '" + field.getName() + "' in control " + ctrlIds[0]);
				}
				field.batchUpdate(values, queue);
			} else {
				log.warn("The field with the name '" + ctrlIds[1] + "' does not exist in the control '" + ctrlIds[0] + "'");
				// XXX Remove in later versions (FLI 28.08.2005)
				field = new Field(ctrl, ctrlIds[1]);
				field.batchUpdate(values, queue);
			}
		}

	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IActionController#handleFile(de.jwic.base.SessionContext, java.lang.String, de.jwic.upload.UploadFile)
	 */
	public void handleFile(SessionContext sc, String fieldname, UploadFile file) {
		
		String[] ctrlIds = getControlIdFromField(fieldname);
		if (ctrlIds != null) {
			Control ctrl = sc.getControlById(ctrlIds[0]);
			if (ctrl instanceof IFileReciever) {
				IFileReciever reciever = (IFileReciever)ctrl;
				reciever.handleFile(ctrlIds[1], file);
			} else {
				log.error("Can not store file in " + ctrl.getControlID() + " because the control does not implement IFileReciever interface.");
			}
		}

	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IActionController#handleAction(de.jwic.base.SessionContext, java.lang.String, java.lang.String)
	 */
	public void handleAction(SessionContext sc, String ctrlId, String action, String param) throws ControlNotFoundException {
		
		// if the session had been removed ctrlId, action and param is ""
		if (ctrlId != null && ctrlId.length() > 0) {
			Control ctrl = null;
			try {
				ctrl = sc.getControlById(ctrlId);
			} catch (ControlNotFoundException cnfe) {
				log.warn("The action '" + action + "' was not executed as the control '" + ctrlId + "' was not found. ");
			}
			if (ctrl != null) {
				ctrl.actionPerformed(action, param);
			}
		}		
	}
	
	/**
	 * @param paramName
	 * @return
	 */
	private String[] getControlIdFromField(String paramName) {

		if (paramName.startsWith("fld_")) {
			String fieldId = paramName.substring(4);
			int lastDot = fieldId.lastIndexOf('.');
			if (lastDot != -1) {
				String ctrlId = fieldId.substring(0, lastDot);
				String fieldname = fieldId.substring(lastDot + 1);
				return new String[] { ctrlId, fieldname };
			}
		}
		return null;
	}


}
