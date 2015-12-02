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
/**
 * 
 */
package de.jwic.ecolib.minichart;

import java.awt.Color;

/**
 * Defines the style how a minichart is rendered.
 *  
 * @author Florian Lippisch
 */
public class MiniChartStyle {

	private boolean useFixedBaseValue = true;
	private boolean drawBaseLine = true;
		
	private boolean transparentBackground = true;
	private Color backgroundColor = Color.WHITE;
	private Color defaultFgColor = Color.BLUE;
	private Color negativeFgColor = Color.RED;
	private Color baseLineColor = Color.GRAY;
	
	/**
	 * @return the backgroundColor
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	/**
	 * @param backgroundColor the backgroundColor to set
	 */
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	/**
	 * @return the baseLineColor
	 */
	public Color getBaseLineColor() {
		return baseLineColor;
	}
	/**
	 * @param baseLineColor the baseLineColor to set
	 */
	public void setBaseLineColor(Color baseLineColor) {
		this.baseLineColor = baseLineColor;
	}
	/**
	 * @return the defaultFgColor
	 */
	public Color getDefaultFgColor() {
		return defaultFgColor;
	}
	/**
	 * @param defaultFgColor the defaultFgColor to set
	 */
	public void setDefaultFgColor(Color defaultFgColor) {
		this.defaultFgColor = defaultFgColor;
	}
	/**
	 * @return the negativeFgColor
	 */
	public Color getNegativeFgColor() {
		return negativeFgColor;
	}
	/**
	 * @param negativeFgColor the negativeFgColor to set
	 */
	public void setNegativeFgColor(Color negativeFgColor) {
		this.negativeFgColor = negativeFgColor;
	}
	/**
	 * @return the useFixedBaseValue
	 */
	public boolean isUseFixedBaseValue() {
		return useFixedBaseValue;
	}
	/**
	 * @param useFixedBaseValue the useFixedBaseValue to set
	 */
	public void setUseFixedBaseValue(boolean useFixedBaseValue) {
		this.useFixedBaseValue = useFixedBaseValue;
	}
	/**
	 * @return the drawBaseLine
	 */
	public boolean isDrawBaseLine() {
		return drawBaseLine;
	}
	/**
	 * @param drawBaseLine the drawBaseLine to set
	 */
	public void setDrawBaseLine(boolean drawBaseLine) {
		this.drawBaseLine = drawBaseLine;
	}
	/**
	 * @return the transparentBackground
	 */
	public boolean isTransparentBackground() {
		return transparentBackground;
	}
	/**
	 * @param transparentBackground the transparentBackground to set
	 */
	public void setTransparentBackground(boolean transparentBackground) {
		this.transparentBackground = transparentBackground;
	}

	
}
