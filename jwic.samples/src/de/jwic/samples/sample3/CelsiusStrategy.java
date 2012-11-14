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
 * de.jwic.samples.sample3.FarenheitStrategy
 * Created on 02.05.2005
 * $Id: CelsiusStrategy.java,v 1.2 2007/03/28 08:54:19 lordsam Exp $
 */
package de.jwic.samples.sample3;

import java.io.Serializable;
import java.text.NumberFormat;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class CelsiusStrategy implements ValueStrategy, Serializable {

	private TemperatureModel model = null;
	
	public CelsiusStrategy(TemperatureModel model) {
		this.model = model;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.samples.sample3.ValueStrategy#get()
	 */
	public double get() {
		return model.getC();
	}

	/* (non-Javadoc)
	 * @see de.jwic.samples.sample3.ValueStrategy#set(double)
	 */
	public void set(double d) {
		model.setC(d);
	}

	/* (non-Javadoc)
	 * @see de.jwic.samples.sample3.ValueStrategy#increase()
	 */
	public void increase() {
		model.setC(model.getC() + 1);
	}

	/* (non-Javadoc)
	 * @see de.jwic.samples.sample3.ValueStrategy#decrease()
	 */
	public void decrease() {
		model.setC(model.getC() - 1);
	}

	/* (non-Javadoc)
	 * @see de.jwic.samples.sample3.ValueStrategy#getFormated()
	 */
	public String getFormated() {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(1);
		nf.setMinimumFractionDigits(1);
		return nf.format(model.getC()) + " C°";
	}

	/* (non-Javadoc)
	 * @see de.jwic.samples.sample3.ValueStrategy#getName()
	 */
	public String getName() {
		return "Celsius";
	}

}
