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
