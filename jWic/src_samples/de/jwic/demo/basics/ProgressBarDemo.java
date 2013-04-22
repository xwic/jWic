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
 * $Id: ButtonDemo.java,v 1.4 2010/01/26 11:25:17 lordsam Exp $
 */
package de.jwic.demo.basics;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.GroupControl;

/**
 * 
 * Demonstrates the usage of the ButtonControl.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.4 $
 */
public class ProgressBarDemo extends ControlContainer {

	
	/**
	 * Constructor.
	 * @param container
	 */
	public ProgressBarDemo(IControlContainer container) {
		super(container);
		
		GroupControl group = new GroupControl(this, "group");
		group.setTitle("Simple ProgressBar usage");
		group.setWidth(450);
		
		new SimpleProgressBarDemo(group);

		GroupControl group2 = new GroupControl(this, "group2");
		group2.setTitle("ProgressBar showing background process status");
		group2.setWidth(450);
		
		new BackgroundProgressBarDemo(group2);

		
	}
}
