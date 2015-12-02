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
 * de.jwic.events.ElementSelectedListener
 * $Id: ElementSelectedListener.java,v 1.1 2006/01/16 08:30:40 lordsam Exp $
 */
package de.jwic.events;

import java.io.Serializable;

/**
 * This listener listens to the selection of an element.
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public interface ElementSelectedListener extends Serializable {
	
	public abstract void elementSelected(ElementSelectedEvent event);
	
}
