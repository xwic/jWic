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
 * de.jwic.samples.controls.LabelDemo
 * Created on 28.10.2005
 * $Id: SysInfoDemo.java,v 1.4 2010/01/26 11:25:17 lordsam Exp $
 */
package de.jwic.samples.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.Page;
import de.jwic.base.UserAgentInfo;
import de.jwic.controls.Button;
import de.jwic.controls.LabelControl;
import de.jwic.events.IPageListener;
import de.jwic.events.PageEvent;
import de.jwic.events.SelectionListener;

/**
 * 
 * Displays the system informations about the clients that are available. 
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.4 $
 */
public class SysInfoDemo extends ControlContainer {

	private LabelControl lblDimension;

	public SysInfoDemo(IControlContainer container) {
		super(container);
		
		lblDimension = new LabelControl(this, "lblDimension");
		lblDimension.setText(Page.findPage(this).getPageSize().toString());
		
		Button btApply = new Button(this, "btRefresh");
		btApply.setTitle("Refresh");
		btApply.addSelectionListener(new SelectionListener() {
			public void objectSelected(de.jwic.events.SelectionEvent event) {
				refreshInfo();
			};
		});

		Page.findPage(this).addPageListener(new IPageListener() {
			/* (non-Javadoc)
			 * @see de.jwic.events.IPageListener#pageSizeChanged(de.jwic.events.PageEvent)
			 */
			public void pageSizeChanged(PageEvent event) {
				refreshInfo();
			}
		});
		
	}

	/**
	 * 
	 */
	protected void refreshInfo() {
		lblDimension.setText(Page.findPage(this).getPageSize().toString());
		requireRedraw();
	}
	
	/**
	 * Returns details about the user agent.
	 * @return
	 */
	public UserAgentInfo getUserAgentInfo() {
		return getSessionContext().getUserAgent();
	}
	
}
