package de.jwic.controls.chart.impl.polar;

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
public class PolarChart extends Chart<PolarChartModel> {

	// Boolean - Show a backdrop to the scale label
	private boolean scaleShowLabelBackdrop = true;
	// String - The colour of the label backdrop
	private Color scaleBackdropColor = new Color(255, 255, 255);
	// Boolean - Whether the scale should begin at zero
	private boolean scaleBeginAtZero = true;
	// Number - The backdrop padding above & below the label in
	// pixels
	private int scaleBackdropPaddingY = 2;
	// Number - The backdrop padding to the side of the label in
	// pixels
	private int scaleBackdropPaddingX = 2;
	// Boolean - Show line for each value in the scale
	private boolean scaleShowLine = true;
	// Boolean - Stroke a line around each segment in the chart
	private boolean segmentShowStroke = true;
	// String - The colour of the stroke on each segement.
	private String segmentStrokeColor = "#fff";
	// Number - The width of the stroke value in pixels
	private int segmentStrokeWidth = 2;
	// Number - Amount of animation steps
	private int animationSteps = 100;
	// String - Animation easing effect.
	private String animationEasing = "easeOutBounce";
	// Boolean - Whether to animate the rotation of the chart
	private boolean animateRotate = true;
	// Boolean - Whether to animate scaling the chart from the
	// centre
	private boolean animateScale = false;
	// String - A legend template
	private String legendTemplate = "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>";

	public PolarChart(IControlContainer container, String name,
			PolarChartModel model) throws ChartInconsistencyException {
		super(container, name, ChartType.POLAR, model);

	}

	@IncludeJsOption(jsPropertyName = "scaleShowLabelBackdrop")
	public boolean isScaleShowLabelBackdrop() {
		return scaleShowLabelBackdrop;
	}

	public void setScaleShowLabelBackdrop(boolean scaleShowLabelBackdrop) {
		this.scaleShowLabelBackdrop = scaleShowLabelBackdrop;
	}

	@IncludeJsOption(jsPropertyName = "scaleBackdropColor")
	public String getScaleBackdropColor() {
		return DatenConverter.convertToJSColor(scaleBackdropColor, "0.75");
	}

	public void setScaleBackdropColor(Color scaleBackdropColor) {
		this.scaleBackdropColor = scaleBackdropColor;
	}

	@IncludeJsOption(jsPropertyName = "scaleBeginAtZero")
	public boolean isScaleBeginAtZero() {
		return scaleBeginAtZero;
	}

	public void setScaleBeginAtZero(boolean scaleBeginAtZero) {
		this.scaleBeginAtZero = scaleBeginAtZero;
	}

	@IncludeJsOption(jsPropertyName = "scaleBackdropPaddingY")
	public int getScaleBackdropPaddingY() {
		return scaleBackdropPaddingY;
	}

	public void setScaleBackdropPaddingY(int scaleBackdropPaddingY) {
		this.scaleBackdropPaddingY = scaleBackdropPaddingY;
	}

	@IncludeJsOption(jsPropertyName = "scaleBackdropPaddingX")
	public int getScaleBackdropPaddingX() {
		return scaleBackdropPaddingX;
	}

	public void setScaleBackdropPaddingX(int scaleBackdropPaddingX) {
		this.scaleBackdropPaddingX = scaleBackdropPaddingX;
	}

	@IncludeJsOption(jsPropertyName = "scaleShowLine")
	public boolean isScaleShowLine() {
		return scaleShowLine;
	}

	public void setScaleShowLine(boolean scaleShowLine) {
		this.scaleShowLine = scaleShowLine;
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
		return segmentStrokeColor;
	}

	public void setSegmentStrokeColor(String segmentStrokeColor) {
		this.segmentStrokeColor = segmentStrokeColor;
	}

	@IncludeJsOption(jsPropertyName = "segmentStrokeWidth")
	public int getSegmentStrokeWidth() {
		return segmentStrokeWidth;
	}

	public void setSegmentStrokeWidth(int segmentStrokeWidth) {
		this.segmentStrokeWidth = segmentStrokeWidth;
	}

	@IncludeJsOption(jsPropertyName = "animationSteps")
	public int getAnimationSteps() {
		return animationSteps;
	}

	public void setAnimationSteps(int animationSteps) {
		this.animationSteps = animationSteps;
	}

	@IncludeJsOption(jsPropertyName = "animationEasing")
	public String getAnimationEasing() {
		return animationEasing;
	}

	public void setAnimationEasing(String animationEasing) {
		this.animationEasing = animationEasing;
	}

	@IncludeJsOption(jsPropertyName = "animateRotate")
	public boolean isAnimateRotate() {
		return animateRotate;
	}

	public void setAnimateRotate(boolean animateRotate) {
		this.animateRotate = animateRotate;
	}

	@IncludeJsOption(jsPropertyName = "animateScale")
	public boolean isAnimateScale() {
		return animateScale;
	}

	public void setAnimateScale(boolean animateScale) {
		this.animateScale = animateScale;
	}

	@Override
	public String getLegendTemplate() {
		return legendTemplate;
	}

	@Override
	public void setLegendTemplate(String legendTemplate) {
		this.legendTemplate = legendTemplate;
	}

}
