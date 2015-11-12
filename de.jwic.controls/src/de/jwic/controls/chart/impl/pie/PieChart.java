package de.jwic.controls.chart.impl.pie;

import java.awt.Color;

import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.chart.api.Chart;
import de.jwic.controls.chart.api.ChartType;
import de.jwic.controls.chart.api.exception.ChartInconsistencyException;
import de.jwic.controls.chart.impl.util.DatenConverter;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 29.11.2015
 */
@JavaScriptSupport
public class PieChart extends Chart<PieChartModel> {

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

	public PieChart(IControlContainer container, String name,
			PieChartModel model) throws ChartInconsistencyException {
		super(container, name, ChartType.PIE, model);

	}

	@IncludeJsOption(jsPropertyName = "segmentShowStroke")
	public boolean isSegmentShowStroke() {
		return segmentShowStroke;
	}

	public void setSegmentShowStroke(boolean segmentShowStroke) {
		this.segmentShowStroke = segmentShowStroke;
	}

	@IncludeJsOption(jsPropertyName = "segmentStrokeColor")
	public String getSegmentStrokeColor() {
		return DatenConverter.convertToJSColor(segmentStrokeColor);
	}

	public void setSegmentStrokeColor(Color segmentStrokeColor) {
		this.segmentStrokeColor = segmentStrokeColor;
	}

	@IncludeJsOption(jsPropertyName = "segmentStrokeWidth")
	public int getSegmentStrokeWidth() {
		return segmentStrokeWidth;
	}

	public void setSegmentStrokeWidth(int segmentStrokeWidth) {
		this.segmentStrokeWidth = segmentStrokeWidth;
	}

	@IncludeJsOption(jsPropertyName = "percentageInnerCutout")
	public int getPercentageInnerCutout() {
		return percentageInnerCutout;
	}

	public void setPercentageInnerCutout(int percentageInnerCutout) {
		this.percentageInnerCutout = percentageInnerCutout;
	}

	@IncludeJsOption(jsPropertyName = "animationSteps")
	public int getAnimationSteps() {
		return animationSteps;
	}

	public void setAnimationSteps(int animationSteps) {
		this.animationSteps = animationSteps;
	}

	@IncludeJsOption(jsPropertyName = "segmentStrokeColor")
	public String getAnimationEasing() {
		return animationEasing;
	}

	public void setAnimationEasing(String animationEasing) {
		this.animationEasing = animationEasing;
	}

	@IncludeJsOption(jsPropertyName = "segmentStrokeColor")
	public boolean isAnimateRotate() {
		return animateRotate;
	}

	public void setAnimateRotate(boolean animateRotate) {
		this.animateRotate = animateRotate;
	}
	@IncludeJsOption(jsPropertyName = "segmentStrokeColor")
	public boolean isAnimateScale() {
		return animateScale;
	}

	public void setAnimateScale(boolean animateScale) {
		this.animateScale = animateScale;
	}
	@IncludeJsOption(jsPropertyName = "segmentStrokeColor")
	public String getLegendTemplate() {
		return legendTemplate;
	}

	public void setLegendTemplate(String legendTemplate) {
		this.legendTemplate = legendTemplate;
	}

}
