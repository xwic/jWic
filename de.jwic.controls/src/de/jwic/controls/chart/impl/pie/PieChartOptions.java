package de.jwic.controls.chart.impl.pie;

import java.awt.Color;

import de.jwic.controls.chart.api.configuration.ChartOptions;
import de.jwic.controls.chart.impl.util.DatenConverter;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 29.10.2015
 */
public class PieChartOptions extends ChartOptions {

	// Boolean - Whether we should show a stroke on each segment
	private boolean segmentShowStroke = true;

	// String - The colour of each segment stroke
	private Color segmentStrokeColor = new Color(0, 0, 0);
	// Number - The width of each segment stroke
	private int segmentStrokeWidth = 2;

	// Number - The percentage of the chart that we cut out of the
	// middle
	private int percentageInnerCutout = 0; // This is 0 for Pie charts

	// Number - Amount of animation steps
	private int animationSteps = 100;

	// String - Animation easing effect
	private String animationEasing = "easeOutBounce";

	// Boolean - Whether we animate the rotation of the Doughnut
	private boolean animateRotate = true;

	// Boolean - Whether we animate scaling the Doughnut from the
	// centre
	private boolean animateScale = false;

	// String - A legend template
	private String legendTemplate = "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color=<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>";

	public boolean isSegmentShowStroke() {
		return segmentShowStroke;
	}

	public void setSegmentShowStroke(boolean segmentShowStroke) {
		this.segmentShowStroke = segmentShowStroke;
	}

	public String getSegmentStrokeColor() {
		return DatenConverter.convertToJSColor(segmentStrokeColor);
	}

	public void setSegmentStrokeColor(Color segmentStrokeColor) {
		this.segmentStrokeColor = segmentStrokeColor;
	}

	public int getSegmentStrokeWidth() {
		return segmentStrokeWidth;
	}

	public void setSegmentStrokeWidth(int segmentStrokeWidth) {
		this.segmentStrokeWidth = segmentStrokeWidth;
	}

	public int getPercentageInnerCutout() {
		return percentageInnerCutout;
	}

	public void setPercentageInnerCutout(int percentageInnerCutout) {
		this.percentageInnerCutout = percentageInnerCutout;
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

	public boolean isAnimateRotate() {
		return animateRotate;
	}

	public void setAnimateRotate(boolean animateRotate) {
		this.animateRotate = animateRotate;
	}

	public boolean isAnimateScale() {
		return animateScale;
	}

	public void setAnimateScale(boolean animateScale) {
		this.animateScale = animateScale;
	}

	public String getLegendTemplate() {
		return legendTemplate;
	}

	public void setLegendTemplate(String legendTemplate) {
		this.legendTemplate = legendTemplate;
	}

}
