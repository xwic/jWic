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
package de.jwic.sourceviewer.main;

import de.jwic.base.Dimension;
import de.jwic.base.IControlContainer;
import de.jwic.base.Page;

/**
 *
 * @author Florian Lippisch
 */
public class SourceViewerPage extends Page {

	private SourceViewer sourceViewer;
	
	/**
	 * @param container
	 * @param model 
	 */
	public SourceViewerPage(IControlContainer container, SVModel model) {
		super(container);
		
		setTitle("SourceViewer");
		sourceViewer = new SourceViewer(this, "svMain", model);
		
	}

	@Override
	public void setPageSize(Dimension pageSize) {
		super.setPageSize(pageSize);
		sourceViewer.setHeight(pageSize.height - 110);
		sourceViewer.setWidth(pageSize.width - 50);
	}
	
}
