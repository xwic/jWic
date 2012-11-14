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
 * de.jwic.ecolib.samples.controls.BalloonDemo
 * Created on Jun 6, 2008
 * $Id: BalloonDemo.java,v 1.2 2008/07/21 07:50:48 cosote Exp $
 */
package de.jwic.ecolib.samples.controls;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.InputBoxControl;
import de.jwic.controls.LabelControl;
import de.jwic.ecolib.controls.balloon.BalloonControl;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.samples.controls.GroupControlDemo;

/**
 *
 * @author jbornema
 */
public class BalloonDemo extends ControlContainer {

	/**
	 * @param container
	 */
	public BalloonDemo(IControlContainer container) {
		this(container, null);
	}

	/**
	 * @param container
	 * @param name
	 */
	public BalloonDemo(IControlContainer container, String name) {
		super(container, name);
		
		new LabelControl(this).setText("For this Balloon Demo click with <b>left</b> or <b>right</b> mouse button on any element of <b><i>GroupControl Demo</i></b>");
		GroupControlDemo demo = new GroupControlDemo(this);
		
		final BalloonControl balloon = new BalloonControl(this);
		final LabelControl label = new LabelControl(balloon);
		new InputBoxControl(balloon);
		SelectionListener listener = new SelectionListener() {
			/* (non-Javadoc)
			 * @see de.jwic.events.SelectionListener#objectSelected(de.jwic.events.SelectionEvent)
			 */
			public void objectSelected(SelectionEvent event) {
				// TODO Auto-generated method stub
				String onEvent = balloon.getOnEvent();
				label.setText("<br>Event <b>" + onEvent + "</b> fired on Mouse X=" + balloon.getX() + ", Y=" + balloon.getY() + "<br><br>");
			}
		};
		balloon.registerOnClick(demo, listener);
		balloon.registerOnContextMenu(demo, listener);
	}

}
