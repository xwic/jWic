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
 * de.jwic.samples.sample3.ValueStrategy
 * Created on 02.05.2005
 * $Id: ValueStrategy.java,v 1.2 2007/03/28 08:54:19 lordsam Exp $
 */
package de.jwic.samples.sample3;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.2 $
 */
public interface ValueStrategy {
	
	public double get();
	public void set(double d);
	public void increase();
	public void decrease();
	
	public String getFormated();
	
	/**
	 * @return the name of the strategy.
	 */
	public String getName();

}
