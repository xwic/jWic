package de.jwic.controls.chart.api.configuration;

import java.awt.Color;
import java.io.Serializable;

import de.jwic.controls.chart.impl.util.DatenConverter;

/**
 * 
 * @author karolina
 *
 */
public class GlobalChartConfiguration implements Serializable {

	private boolean responsive = true;

	private boolean animation = true;

	private int animationSteps = 60;

	private String animationEasing = "easeOutQuart";

	private boolean showScale = true;

	private boolean scaleOverride = false;

	private Color scaleLineColor = new Color(0, 0, 0, 1);

	public boolean isResponsive() {
		return responsive;
	}

	public void setResponsive(boolean responsive) {
		this.responsive = responsive;
	}

	public boolean isAnimation() {
		return animation;
	}

	public void setAnimation(boolean animation) {
		this.animation = animation;
	}

	public int getAnimationSteps() {
		return animationSteps;
	}

	public void setAnimationSteps(int animationSteps) {
		this.animationSteps = animationSteps;
	}

	public String getAnimationEasing() {
		return animationEasing;
	}

	public void setAnimationEasing(String animationEasing) {
		this.animationEasing = animationEasing;
	}

	public boolean isShowScale() {
		return showScale;
	}

	public void setShowScale(boolean showScale) {
		this.showScale = showScale;
	}

	public boolean isScaleOverride() {
		return scaleOverride;
	}

	public void setScaleOverride(boolean scaleOverride) {
		this.scaleOverride = scaleOverride;
	}

	public String getScaleLineColor() {

		return DatenConverter.convertToJSColor(scaleLineColor);
	}

	public void setScaleLineColor(Color scaleLineColor) {
		this.scaleLineColor = scaleLineColor;
	}

}
