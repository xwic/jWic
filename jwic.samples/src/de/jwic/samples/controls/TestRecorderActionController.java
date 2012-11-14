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
 * de.jwic.samples.controls.TestRecorderActionController
 * Created on Feb 28, 2011
 * $Id: TestRecorderActionController.java,v 1.1 2011/05/06 13:24:20 lordsam Exp $
 */
package de.jwic.samples.controls;

import de.jwic.base.ControlNotFoundException;
import de.jwic.base.DefaultActionController;
import de.jwic.base.SessionContext;
import de.jwic.base.ValueChangedQueue;
import de.jwic.upload.UploadFile;

/**
 *
 * @author lippisch
 */
public class TestRecorderActionController extends DefaultActionController {

	/* (non-Javadoc)
	 * @see de.jwic.base.DefaultActionController#handleAction(de.jwic.base.SessionContext, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void handleAction(SessionContext sc, String ctrlId, String action,
			String param) throws ControlNotFoundException {
		super.handleAction(sc, ctrlId, action, param);
		System.out.println("action: " + ctrlId + ", " + action + ", " + param);
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.DefaultActionController#handleField(de.jwic.base.SessionContext, de.jwic.base.ValueChangedQueue, java.lang.String, java.lang.String[])
	 */
	@Override
	public void handleField(SessionContext sc, ValueChangedQueue queue,
			String fieldname, String[] values) {
		// TODO Auto-generated method stub
		super.handleField(sc, queue, fieldname, values);
		System.out.println("field: " + fieldname + " = " + values[0]);
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.DefaultActionController#handleFile(de.jwic.base.SessionContext, java.lang.String, de.jwic.upload.UploadFile)
	 */
	@Override
	public void handleFile(SessionContext sc, String fieldname, UploadFile file) {
		// TODO Auto-generated method stub
		super.handleFile(sc, fieldname, file);
	}
}

