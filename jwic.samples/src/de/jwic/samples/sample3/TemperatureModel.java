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
 * de.jwic.samples.sample3.TemperatureModel
 * Created on 02.05.2005
 * $Id: TemperatureModel.java,v 1.2 2006/07/26 16:46:21 lordsam Exp $
 */
package de.jwic.samples.sample3;

import java.io.Serializable;

import de.jwic.util.SerObservable;

/**
 * Stores the temperature in farenheit. You can set and get the temperature in
 * fahrenheit and celsius. Thus it acts as a converter as well as a temperature.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public class TemperatureModel extends SerObservable implements Serializable {

	private double temperatureF = 32.0;

	public double getF(){return temperatureF;}
	
	public double getC(){return (temperatureF - 32.0) * 5.0 / 9.0;}
	
	public void setF(double tempF)
	{	temperatureF = tempF;
		setChanged();
		notifyObservers();
	}
	
	public void setC(double tempC)
	{	temperatureF = tempC*9.0/5.0 + 32.0;
		setChanged();
		notifyObservers();
	}

	
	
}
