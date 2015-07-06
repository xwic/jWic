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
