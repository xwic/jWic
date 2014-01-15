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
 * de.jwic.sourceviewer.main.ISVModelListener
 * Created on 26.04.2007
 * $Id: ISVModelListener.java,v 1.1 2007/04/26 16:07:19 lordsam Exp $
 */
package de.jwic.sourceviewer.main;

import java.io.Serializable;

/**
 * Listener to the SVModel.
 * @author Florian Lippisch
 */
public interface ISVModelListener extends Serializable {

	/**
	 * A group has been selected.
	 * @param event
	 */
	public void groupSelected(SVModelEvent event);

	/**
	 * An element has selected for viewing.
	 * @param event
	 */
	public void elementSelected(SVModelEvent event);

}
