/*
 * Copyright 2005 jWic Group (http://www.jwic.de)
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
 * de.jwic.samples.sample3.TemperatureDemo
 * Created on 02.05.2005
 * $Id: TemperatureDemo.java,v 1.2 2007/03/28 08:54:19 lordsam Exp $
 */
package de.jwic.samples.sample3;

import de.jwic.base.Application;
import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.Page;

/**
 * This demo shows how to write MVC (Model-View-Controller) applications
 * with jWic. It is based upon a guide from Joseph Begin from the Pace
 * University (http://csis.pace.edu/~bergin/mvc/mvcgui.html).
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class TemperatureDemo extends Application {

	/* (non-Javadoc)
	 * @see de.jwic.base.Application#createRootControl(de.jwic.base.IControlContainer)
	 */
	public Control createRootControl(IControlContainer container) {
		
		Page page = new Page(container);
		page.setTitle("Temperature Demo");
		page.setTemplateName(getClass().getName());
		TemperatureModel model = new TemperatureModel();
		
		new TemperatureGUI(page, model, new FarenheitStrategy(model));
		new TemperatureGUI(page, model, new CelsiusStrategy(model));
		
		return page;
		
	}
	
}
