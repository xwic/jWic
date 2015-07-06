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
package de.jwic.controls.combo;

import de.jwic.base.IControlContainer;

/**
 *
 * @author lippisch
 */
public class LifeSearchCombo<A> extends Combo<A> {
	private static final long serialVersionUID = 1L;
	/**
	 * @param container
	 * @param name
	 */
	public LifeSearchCombo(IControlContainer container, String name) {
		super(container, name);
		comboBehavior.setOpenContentOnTextboxFocus(false);
		comboBehavior.setSelectTextOnFocus(true);
		comboBehavior.setTextEditable(true);
		comboBehavior.setMaxFetchRows(20);
		comboBehavior.setMinSearchKeyLength(3); // defaults to 3
		comboBehavior.setShowOpenBoxIcon(false);
		comboBehavior.setCacheData(false);
		comboBehavior.setClientSideFilter(false); // filter is applied on the server side.
		comboBehavior.setKeyDelayTime(500); // longer delay time as it will force a server side reload.
		
	}

	/**
	 * @return
	 * @see de.jwic.controls.combo.ComboBehavior#getMaxFetchRows()
	 */
	public int getMaxFetchRows() {
		return comboBehavior.getMaxFetchRows();
	}

	/**
	 * @param maxFetchRows
	 * @see de.jwic.controls.combo.ComboBehavior#setMaxFetchRows(int)
	 */
	public void setMaxFetchRows(int maxFetchRows) {
		comboBehavior.setMaxFetchRows(maxFetchRows);
	}


	/**
	 * @return
	 * @see de.jwic.controls.combo.ComboBehavior#isAutoPickFirstHit()
	 */
	public boolean isAutoPickFirstHit() {
		return comboBehavior.isAutoPickFirstHit();
	}

	/**
	 * Set to true to select the first element that was found and matches the search 
	 * criteria. Only works properly if the search is working by an "startsWith" logic.
	 * @param autoPickFirstHit
	 * @see de.jwic.controls.combo.ComboBehavior#setAutoPickFirstHit(boolean)
	 */
	public void setAutoPickFirstHit(boolean autoPickFirstHit) {
		comboBehavior.setAutoPickFirstHit(autoPickFirstHit);
	}
	
	
}
