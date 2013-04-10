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
 * de.jwic.data.SelectElementBaseLabelProvider
 * Created on Mar 13, 2010
 * $Id: SelectElementBaseLabelProvider.java,v 1.1 2010/04/22 16:00:10 lordsam Exp $
 */
package de.jwic.data;

import java.io.Serializable;

/**
 * Default base label provider for select elements.
 * @author lippisch
 */
public class SelectElementBaseLabelProvider implements IBaseLabelProvider<ISelectElement>, Serializable {
	private static final long serialVersionUID = 1L;
	/* (non-Javadoc)
	 * @see de.jwic.data.IBaseLabelProvider#getBaseLabel(java.lang.Object)
	 */
	public DataLabel getBaseLabel(ISelectElement object) {
		return new DataLabel(object.getTitle(), object.getImage());
	}

}
