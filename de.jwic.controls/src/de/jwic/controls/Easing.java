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
package de.jwic.controls;

/**
 * Easing functions specify the speed at which an animation progresses at
 * different points within the animation. jQuery core ships with two easings:
 * linear, which progresses at a constant pace throughout the animation, and
 * swing (jQuery core's default easing), which progresses slightly slower at the
 * beginning and end of the animation than it does in the middle of the
 * animation. jQuery UI provides several additional easing functions, ranging
 * from variations on the swing behavior to customized effects such as bouncing.
 * 
 * @author dotto
 * 
 */
public enum Easing {
	LINEAR("linear"), SWING("swing"), EASE_IN_QUAD("easeInQuad"), EASE_OUT_QUAD(
			"easeOutQuad"), EASE_IN_OUT_QUAD("easeInOutQuad"), EASE_IN_CUBIC(
			"easeInCubic"), EASE_OUT_CUBI("easeOutCubic"), EASE_IN_OUT_CUBIC(
			"easeInOutCubic"), EASE_IN_QUART("easeInQuart"), EASE_OUT_QUART(
			"easeOutQuart"), EASE_IN_OUT_QUART("easeInOutQuart"), EASE_IN_QUINT(
			"easeInQuint"), EASE_OUT_QUINT("easeOutQuint"), EASE_IN_OUT_QUINT(
			"easeInOutQuint"), EASE_IN_EXPO("easeInExpo"), EASE_OUT_EXPO(
			"easeOutExpo"), EASE_IN_OUT_EXPO("easeInOutExpo"), EASE_IN_SINE(
			"easeInSine"), EASE_OUT_SINE("easeOutSine"), EASE_IN_OUT_SINE(
			"easeInOutSine"), EASE_IN_CIRC("easeInCirc"), EASE_OUT_CIRC(
			"easeOutCirc"), EASE_IN_OUT_CIRC("easeInOutCirc"), EASE_IN_ELASTIC(
			"easeInElastic"), EASE_OUT_ELASTIC("easeOutElastic"), EASE_IN_OUT_ELASTIC(
			"easeInOutElastic"), EASE_IN_BACK("easeInBack"), EASE_OUT_BACK(
			"easeOutBack"), EASE_IN_OUT_BACK("easeInOutBack"), EASE_IN_BOUNCE(
			"easeInBounce"), EASE_OUT_BOUNCE("easeOutBounce"), EASE_IN_OUT_BOUNCE(
			"easeInOutBounce");

	private String code;

	private Easing(String c) {
		code = c;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return getCode();
	}
}
