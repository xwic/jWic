/**
 * 
 */
package de.jwic.controls.chart.api;

import java.io.Serializable;

/**
 * @author vedad
 *
 */
public class Animation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonChartName(bar = "duration", circle = "duration", line = "duration", polar = "duration", radar = "duration", dateTime = "duration", stacked = "duration", overlay = "duration")
	private int duration = 1000;
	@JsonChartName(bar = "easing", circle = "easing", line = "easing", polar = "easing", radar = "easing", dateTime = "easing", stacked = "easing", overlay = "easing")
	private String easing = AnimationEffect.EASEOUTQUART.getName();
	
	/**
	 * 
	 */
	public Animation() {
		super();
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @return the easing
	 */
	public String getEasing() {
		return easing;
	}

	/**
	 * @param easing the easing to set
	 */
	public void setEasing(String easing) {
		AnimationEffect anim = AnimationEffect.getAfterName(easing);
		if (anim != null) {
			this.easing = anim.getName();
		}
	}

}
