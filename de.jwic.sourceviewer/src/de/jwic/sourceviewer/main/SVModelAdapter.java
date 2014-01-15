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
 * de.jwic.sourceviewer.main.SVModelAdapter
 * Created on 26.04.2007
 * $Id: SVModelAdapter.java,v 1.1 2007/04/26 16:07:19 lordsam Exp $
 */
package de.jwic.sourceviewer.main;

/**
 * Adapter for the ISVModelListener.
 * @author Florian Lippisch
 */
public abstract class SVModelAdapter implements ISVModelListener {

	/* (non-Javadoc)
	 * @see de.jwic.sourceviewer.main.ISVModelListener#groupSelected(de.jwic.sourceviewer.main.SVModelEvent)
	 */
	public void groupSelected(SVModelEvent event) {

	}

	/* (non-Javadoc)
	 * @see de.jwic.sourceviewer.main.ISVModelListener#elementSelected(de.jwic.sourceviewer.main.SVModelEvent)
	 */
	public void elementSelected(SVModelEvent event) {
				
	}
	
}
