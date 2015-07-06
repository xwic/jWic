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
/*
 * Created on 24.11.2004
 */
package de.jwic.events;

import java.io.IOException;
import java.io.InputStream;

import de.jwic.base.Control;
import de.jwic.base.Event;
import de.jwic.upload.UploadFile;

/**
 * FileReceivedEvent contains the InputStream of an uploaded file with the FileUploadControl.
 * 
 * $Id: FileReceivedEvent.java,v 1.3 2006/08/14 09:34:59 lordsam Exp $
 * @version $Revision: 1.3 $
 * @author JBornemann
 */
public class FileReceivedEvent extends Event {

	private static final long serialVersionUID = 1L;
	protected UploadFile file = null;
	
	/**
	 * FileReceivedEvent constructor.
	 * @param sourceControl
	 */
	public FileReceivedEvent(Control sourceControl, UploadFile file) {
		super(sourceControl);
		this.file = file;;
	}
	
	/**
	 * Returns the InputStream containing the uploaded file data.
	 * @return
	 * @throws IOException
	 */
	public InputStream getInputStream() throws IOException {
		return file.getInputStream();
	}
	
	/**
	 * Returns the name of the uploaded file.
	 * @return
	 */
	public String getFileName() {
		return file.getName();
	}

	/**
	 * Returns the name of the uploaded file.
	 * @return
	 */
	public long getFileSize() {
		return file.length();
	}
}
