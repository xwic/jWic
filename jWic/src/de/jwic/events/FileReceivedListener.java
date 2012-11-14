/*
 * Created on 24.11.2004
 */
package de.jwic.events;

import java.io.Serializable;

/**
 * FileReceivedListener is call when the FileUploadControl receives an uploaded file.
 * 
 * $Id: FileReceivedListener.java,v 1.2 2009/08/09 19:32:51 lordsam Exp $
 * @version $Revision: 1.2 $
 * @author JBornemann
 */
public interface FileReceivedListener extends Serializable {

	void fileReceived(FileReceivedEvent event);
}
