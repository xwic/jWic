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
 * de.jwic.wap.core.IHistoryListener
 * Created on 18.01.2006
 * $Id: HistoryListener.java,v 1.2 2007/05/08 12:10:12 lordsam Exp $
 */
package de.jwic.ecolib.history;

import java.io.Serializable;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public interface HistoryListener extends Serializable {

	/**
	 * Called when the user has selected a specific savepoint using the
	 * browsers back or forward button.
	 * @param event
	 */
	public void savepointSelected(HistoryEvent event);
	
}
