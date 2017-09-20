package de.jwic.controls.chart.api;

import java.io.Serializable;

import de.jwic.controls.chart.impl.util.DataConverter;

/**
 * 
 * Common configuration for all chart types. The specific values have been
 * adnotated because sometimes each chart has its own name of the config
 * property
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 13.11.2015
 */
public abstract class ChartConfiguration implements Serializable {

	/**
	 * 
	 */
	private boolean customTooltip = false;
	private String customTooltipGenerator;
	private int width = 500;
	private int height = 500;
	private boolean enabled = true;
	private boolean responsive = false;
	private boolean zoomEnabled = true;
	private boolean animation = true;
	private int animationSteps = 60;
	private AnimationEffect animationEasing = AnimationEffect.EASEINOUTBOUNCE;
	private boolean showScale = true;
	private boolean scaleOverride = false;
	private String scaleLineColor = "rgba(0,0,0,1)";
	private int scaleLineWidth = 0;
	private boolean scaleShowLabels = true;
	private String scaleLabel = "<%=value%>";
	private boolean scaleIntegersOnly = true;
	private boolean scaleBeginAtZero = false;
	private String scaleFontFamily = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";
	private int scaleFontSize = 11;
	private String scaleFontStyle = "normal";
	private String scaleFontColor = "#666";
	private boolean maintainAspectRatio = true;
	private boolean showTooltips = true;
	private String tooltipFillColor = "rgba(0,0,0,0.8)";
	private String tooltipFontFamily = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";
	private int tooltipFontSize = 11;
	private String tooltipFontStyle = "normal";
	private String tooltipFontColor = "#fff";
	private String tooltipTitleFontFamily = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";
	private int tooltipTitleFontSize = 14;
	private String tooltipTitleFontStyle = "bold";
	private String tooltipTitleFontColor = "#fff";
	private int tooltipYPadding = 6;
	private int tooltipXPadding = 6;
	private int tooltipCaretSize = 0;
	private int tooltipCornerRadius = 6;
	private int tooltipXOffset = 0;
	private String tooltipTemplate = "<%if (label){%><%=label%>= <%}%><%= value %>";

	private String legendTemplate;

	@JsonChartName(bar = "barShowStroke", circle = "segmentShowStroke", line = "datasetStroke", polar = "segmentShowStroke", radar = "segmentShowStroke", dateTime = "datasetStroke", stacked = "barShowStroke", overlay = "")
	private boolean showStroke = true;

	@JsonChartName(bar = "", circle = "segmentStrokeColor", line = "", polar = "segmentStrokeColor", radar = "segmentStrokeColor", dateTime = "", stacked = "", overlay = "")
	private String strokeColor = "rgba(0,0,0,1)";

	@JsonChartName(bar = "barStrokeWidth", circle = "segmentStrokeWidth", line = "datasetStrokeWidth", polar = "segmentStrokeWidth", radar = "segmentStrokeWidth", dateTime = "datasetStrokeWidth", stacked = "barStrokeWidth", overlay = "")
	private int segmentStrokeWidth = 2;

	@JsonChartName(bar = "scaleShowHorizontalLines", circle = "", line = "scaleShowHorizontalLines", polar = "", radar = "", dateTime = "scaleShowHorizontalLines", stacked = "scaleShowHorizontalLines", overlay = "")
	private boolean scaleShowHorizontalLines = false;

	@JsonChartName(bar = "scaleShowVerticalLines", circle = "", line = "scaleShowVerticalLines", polar = "", radar = "", dateTime = "scaleShowVerticalLines", stacked = "scaleShowVerticalLines", overlay = "")
	private boolean scaleShowVerticalLines = false;

	@JsonChartName(bar = "scaleShowGridLines", circle = "", line = "scaleShowGridLines", polar = "", radar = "", dateTime = "scaleShowGridLines", stacked = "scaleShowGridLines", overlay = "")
	private boolean scaleShowGridLines = true;

	@JsonChartName(bar = "scaleGridLineColor", circle = "", line = "scaleGridLineColor", polar = "", radar = "", dateTime = "scaleGridLineColor", stacked = "scaleGridLineColor", overlay = "")
	private String scaleGridLineColor = "rgba(0,0,0,1)";

	@JsonChartName(bar = "scaleGridLineWidth", circle = "", line = "scaleGridLineWidth", polar = "", radar = "", dateTime = "scaleGridLineWidth", stacked = "scaleGridLineWidth", overlay = "")
	private int scaleGridLineWidth = 1;

	@JsonChartName(bar = "", circle = "animateScale", line = "", polar = "animateScale", radar = "animateScale", dateTime = "", stacked = "", overlay = "")
	private boolean animateScale = false;

	@JsonChartName(bar = "", circle = "animateRotate", line = "", polar = "animateRotate", radar = "animateRotate", dateTime = "", stacked = "", overlay = "")
	private boolean animateRotate = true;

	public ChartConfiguration(String legend) {
		this.legendTemplate = legend;
	}

	/**
	 * 
	 * @return String - A legend template, format for the legend tooltip
	 */
	public String getLegendTemplate() {
		return legendTemplate;
	}

	/**
	 * 
	 * @param legend
	 *            - set default for each chart
	 */
	public void setLegendTemplate(String legendTemplate) {
		this.legendTemplate = legendTemplate;
	}

	/**
	 * 
	 * @return - in pixels height of the chart, only taken under account if
	 *         chart responsive is set to false
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 
	 * @param width
	 *            - in pixels width of the chart, only taken under account if
	 *            chart responsive is set to false
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * 
	 * @return - in pixels height of the chart, only taken under account if
	 *         chart responsive is set to false
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 
	 * @param height
	 *            - in pixels height of the chart, only taken under account if
	 *            chart responsive is set to false
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * 
	 * @return Boolean - should the chart be enabled or not
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * 
	 * @param enabled
	 *            Boolean - should the chart be enabled or not
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * 
	 * @return Boolean - Whether we should show a stroke on each segment
	 */
	public boolean isShowStroke() {
		return showStroke;
	}

	/**
	 * @param showStroke
	 *            - Boolean - Whether we should show a stroke on each segment
	 */
	public void setShowStroke(boolean showStroke) {
		this.showStroke = showStroke;
	}

	/**
	 * 
	 * @return String - The colour of each segment stroke
	 */
	public String getStrokeColor() {
		return strokeColor;
	}

	/**
	 * 
	 * @param strokeColor
	 *            String - The colour of each segment stroke
	 */
	public void setStrokeColor(String strokeColor) {
		String color = DataConverter.convertToJSColor(strokeColor);
		if (color != null) {
			this.strokeColor = color;
		}
	}

	/**
	 * 
	 * @return - Number - The width of each segment stroke
	 */
	public int getSegmentStrokeWidth() {
		return segmentStrokeWidth;
	}

	/**
	 * 
	 * @param segmentStrokeWidth
	 *            - Number - The width of each segment stroke
	 */
	public void setSegmentStrokeWidth(int segmentStrokeWidth) {
		this.segmentStrokeWidth = segmentStrokeWidth;
	}

	/**
	 * Boolean - Whether to show horizontal lines (except X axis)
	 * 
	 * @param scaleShowHorizontalLines
	 */
	public void setScaleShowHorizontalLines(boolean scaleShowHorizontalLines) {
		this.scaleShowHorizontalLines = scaleShowHorizontalLines;
	}

	/**
	 * 
	 * @return Boolean - Whether to show vertical lines (except Y axis)
	 */
	public boolean isScaleShowVerticalLines() {
		return scaleShowVerticalLines;
	}

	/**
	 * 
	 * @return Boolean - Whether to show horizontal lines (except X axis)
	 */
	public boolean isScaleShowHorizontalLines() {
		return scaleShowHorizontalLines;
	}

	/**
	 * Boolean - Whether to show vertical lines (except Y axis)
	 * 
	 * @param scaleShowVerticalLines
	 */
	public void setScaleShowVerticalLines(boolean scaleShowVerticalLines) {
		this.scaleShowVerticalLines = scaleShowVerticalLines;
	}

	/**
	 * @return Boolean - Whether grid lines are shown across the chart
	 */
	public boolean isScaleShowGridLines() {
		return scaleShowGridLines;
	}

	/**
	 * Boolean - Whether grid lines are shown across the chart
	 * 
	 * @param scaleShowGridLines
	 */
	public void setScaleShowGridLines(boolean scaleShowGridLines) {
		this.scaleShowGridLines = scaleShowGridLines;
	}

	/**
	 * 
	 * @return String - Color of the grid lines
	 */
	public String getScaleGridLineColor() {
		return scaleGridLineColor;
	}

	/**
	 * String - Color of the grid lines
	 * 
	 * @param scaleGridLineColor
	 */
	public void setScaleGridLineColor(String scaleGridLineColor) {
		String color = DataConverter.convertToJSColor(scaleGridLineColor);
		if (color != null) {
			this.scaleGridLineColor = color;
		}
	}

	/**
	 * @return Number - Width of the grid lines
	 */
	public int getScaleGridLineWidth() {
		return scaleGridLineWidth;
	}

	/**
	 * Number - Width of the grid lines
	 * 
	 * @param scaleGridLineWidth
	 */
	public void setScaleGridLineWidth(int scaleGridLineWidth) {
		this.scaleGridLineWidth = scaleGridLineWidth;
	}

	/**
	 * 
	 * @return Boolean - whether or not the chart should be responsive and
	 *         resize when the browser does.
	 */
	public boolean isResponsive() {
		return responsive;
	}

	/**
	 * 
	 * @param responsive
	 *            Boolean - whether or not the chart should be responsive and
	 *            resize when the browser does.
	 */
	public void setResponsive(boolean responsive) {
		this.responsive = responsive;
	}

	/**
	 * 
	 * @return Boolean - Whether to animate the chart
	 */
	public boolean isAnimation() {
		return animation;
	}

	/**
	 * 
	 * @param animation
	 *            Boolean - Whether to animate the chart
	 */
	public void setAnimation(boolean animation) {
		this.animation = animation;
	}

	/**
	 * 
	 * @return Number - Number of animation steps
	 */
	public int getAnimationSteps() {
		return animationSteps;
	}

	/**
	 * 
	 * @param animationSteps
	 *            Number - Number of animation steps
	 */
	public void setAnimationSteps(int animationSteps) {
		this.animationSteps = animationSteps;
	}

	/**
	 * 
	 * @return String - Animation easing effect
	 */
	public String getAnimationEasing() {
		return animationEasing.getName();
	}

	/**
	 * 
	 * @param animationEasing
	 *            String - Animation easing effect
	 */
	public void setAnimationEasing(String animationEasing) {
		AnimationEffect anim = AnimationEffect.getAfterName(animationEasing);
		if (anim != null) {
			this.animationEasing = anim;
		}
	}

	/**
	 * 
	 * @return Boolean - If we should show the scale at all
	 */
	public boolean isShowScale() {
		return showScale;
	}

	/**
	 * 
	 * @param showScale
	 *            Boolean - If we should show the scale at all
	 */
	public void setShowScale(boolean showScale) {
		this.showScale = showScale;
	}

	/**
	 * 
	 * @return Boolean - If we want to override with a hard coded scale
	 */
	public boolean isScaleOverride() {
		return scaleOverride;
	}

	/**
	 * 
	 * @param scaleOverride
	 *            Boolean - If we want to override with a hard coded scale
	 */
	public void setScaleOverride(boolean scaleOverride) {
		this.scaleOverride = scaleOverride;
	}

	/**
	 * 
	 * @return String - Colour of the scale line
	 */
	public String getScaleLineColor() {
		return scaleLineColor;
	}

	/**
	 * setting the color two formats: rgba with the string comma separated
	 * (r;g;b;a) or as #FFFFFF
	 * 
	 * @param scaleLineColor
	 */
	public void setScaleLineColor(String scaleLineColor) {
		String color = DataConverter.convertToJSColor(scaleLineColor);
		if (color != null) {
			this.scaleLineColor = color;
		}
	}

	/**
	 * 
	 * @return Number - Pixel width of the scale line
	 */
	public int getScaleLineWidth() {
		return scaleLineWidth;
	}

	/**
	 * 
	 * @param scaleLineWidth
	 *            Number - Pixel width of the scale line
	 */
	public void setScaleLineWidth(int scaleLineWidth) {
		this.scaleLineWidth = scaleLineWidth;
	}

	/**
	 * 
	 * @return Boolean - Whether to show labels on the scale
	 */
	public boolean isScaleShowLabels() {
		return scaleShowLabels;
	}

	/**
	 * 
	 * @param scaleShowLabels
	 *            Boolean - Whether to show labels on the scale
	 */
	public void setScaleShowLabels(boolean scaleShowLabels) {
		this.scaleShowLabels = scaleShowLabels;
	}

	/**
	 * 
	 * @return Interpolated JS string - can access value
	 */
	public String getScaleLabel() {
		return scaleLabel;
	}

	/**
	 * 
	 * @param scaleLabel
	 *            Interpolated JS string - can access value
	 */
	public void setScaleLabel(String scaleLabel) {
		this.scaleLabel = scaleLabel;
	}

	/**
	 * 
	 * @return Boolean - Whether the scale should stick to integers, not floats
	 *         even if drawing space is there
	 */
	public boolean isScaleIntegersOnly() {
		return scaleIntegersOnly;
	}

	/**
	 * 
	 * @param scaleIntegersOnly
	 *            Boolean - Whether the scale should stick to integers, not
	 *            floats even if drawing space is there
	 */
	public void setScaleIntegersOnly(boolean scaleIntegersOnly) {
		this.scaleIntegersOnly = scaleIntegersOnly;
	}

	/**
	 * 
	 * @return // Boolean - Whether the scale should start at zero, or an order
	 *         of magnitude down from the lowest value
	 */
	public boolean isScaleBeginAtZero() {
		return scaleBeginAtZero;
	}

	/**
	 * 
	 * @param scaleBeginAtZero
	 *            // Boolean - Whether the scale should start at zero, or an
	 *            order of magnitude down from the lowest value
	 */
	public void setScaleBeginAtZero(boolean scaleBeginAtZero) {
		this.scaleBeginAtZero = scaleBeginAtZero;
	}

	/**
	 * 
	 * @return String - Scale label font declaration for the scale label
	 */
	public String getScaleFontFamily() {
		return scaleFontFamily;
	}

	/**
	 * 
	 * @param scaleFontFamily
	 *            String - Scale label font declaration for the scale label
	 */
	public void setScaleFontFamily(String scaleFontFamily) {
		this.scaleFontFamily = scaleFontFamily;
	}

	/**
	 * 
	 * @return Number - Scale label font size in pixels
	 */
	public int getScaleFontSize() {
		return scaleFontSize;
	}

	/**
	 * 
	 * @param scaleFontSize
	 *            Number - Scale label font size in pixels
	 */
	public void setScaleFontSize(int scaleFontSize) {
		this.scaleFontSize = scaleFontSize;
	}

	/**
	 * 
	 * @return Number - Scale label font weight style
	 */
	public String getScaleFontStyle() {
		return scaleFontStyle;
	}

	/**
	 * 
	 * @param scaleFontStyle
	 *            String - Scale label font weight style
	 */
	public void setScaleFontStyle(String scaleFontStyle) {
		this.scaleFontStyle = scaleFontStyle;
	}

	/**
	 * 
	 * @return String - Scale label font colour
	 */
	public String getScaleFontColor() {
		return scaleFontColor;
	}

	/**
	 * 
	 * @param scaleFontColor
	 *            String - Scale label font colour
	 */
	public void setScaleFontColor(String scaleFontColor) {
		String color = DataConverter.convertToJSColor(scaleFontColor);
		if (color != null) {
			this.scaleFontColor = color;
		}
	}

	/**
	 * 
	 * @return Boolean - whether to maintain the starting aspect ratio or not
	 *         when responsive, if set to false, will take up entire container
	 */
	public boolean isMaintainAspectRatio() {
		return maintainAspectRatio;
	}

	/**
	 * 
	 * @param maintainAspectRatio
	 *            Boolean - whether to maintain the starting aspect ratio or not
	 *            when responsive, if set to false, will take up entire
	 *            container
	 */
	public void setMaintainAspectRatio(boolean maintainAspectRatio) {
		this.maintainAspectRatio = maintainAspectRatio;
	}

	/**
	 * 
	 * @return Boolean - Determines whether to draw tooltips on the canvas or
	 *         not
	 */
	public boolean isShowTooltips() {
		return showTooltips;
	}

	/**
	 * 
	 * @param showTooltips
	 *            Boolean - Determines whether to draw tooltips on the canvas or
	 *            not
	 */
	public void setShowTooltips(boolean showTooltips) {
		this.showTooltips = showTooltips;
	}

	/**
	 * 
	 * @return String - Tooltip background colour
	 */
	public String getTooltipFillColor() {
		return tooltipFillColor;
	}

	/**
	 * 
	 * @param tooltipFillColor
	 *            - String - Tooltip background colour
	 */
	public void setTooltipFillColor(String tooltipFillColor) {
		String color = DataConverter.convertToJSColor(tooltipFillColor);
		if (color != null) {
			this.tooltipFillColor = color;
		}
	}

	/**
	 * 
	 * @return String - Tooltip label font declaration for the scale label
	 */
	public String getTooltipFontFamily() {
		return tooltipFontFamily;
	}

	/**
	 * 
	 * @param tooltipFontFamily
	 *            String - Tooltip label font declaration for the scale label
	 */
	public void setTooltipFontFamily(String tooltipFontFamily) {
		this.tooltipFontFamily = tooltipFontFamily;
	}

	/**
	 * 
	 * @return Number - Tooltip label font size in pixels
	 */
	public int getTooltipFontSize() {
		return tooltipFontSize;
	}

	/**
	 * 
	 * @param tooltipFontSize
	 *            Number - Tooltip label font size in pixels
	 */
	public void setTooltipFontSize(int tooltipFontSize) {
		this.tooltipFontSize = tooltipFontSize;
	}

	/**
	 * 
	 * @return String - Tooltip font weight style
	 */
	public String getTooltipFontStyle() {
		return tooltipFontStyle;
	}

	/**
	 * 
	 * @param tooltipFontStyle
	 *            String - Tooltip font weight style
	 */
	public void setTooltipFontStyle(String tooltipFontStyle) {
		this.tooltipFontStyle = tooltipFontStyle;
	}

	/**
	 * 
	 * @return String - Tooltip label font colour
	 */
	public String getTooltipFontColor() {
		return tooltipFontColor;
	}

	/**
	 * 
	 * @param tooltipFontColor
	 *            String - Tooltip label font colour
	 */
	public void setTooltipFontColor(String tooltipFontColor) {
		this.tooltipFontColor = tooltipFontColor;
	}

	/**
	 * 
	 * @return String - Tooltip title font declaration for the scale label
	 */
	public String getTooltipTitleFontFamily() {
		return tooltipTitleFontFamily;
	}

	/**
	 * 
	 * @param tooltipTitleFontFamily
	 *            - String - Tooltip title font declaration for the scale label
	 */
	public void setTooltipTitleFontFamily(String tooltipTitleFontFamily) {
		this.tooltipTitleFontFamily = tooltipTitleFontFamily;
	}

	/**
	 * 
	 * @return Number - Tooltip title font size in pixels
	 */
	public int getTooltipTitleFontSize() {
		return tooltipTitleFontSize;
	}

	/**
	 * 
	 * @param tooltipTitleFontSize
	 *            Number - Tooltip title font size in pixels
	 */
	public void setTooltipTitleFontSize(int tooltipTitleFontSize) {
		this.tooltipTitleFontSize = tooltipTitleFontSize;
	}

	/**
	 * 
	 * @return String - Tooltip title font weight style
	 */
	public String getTooltipTitleFontStyle() {
		return tooltipTitleFontStyle;
	}

	/**
	 * 
	 * @param tooltipTitleFontStyle
	 *            - String - Tooltip title font weight style
	 */
	public void setTooltipTitleFontStyle(String tooltipTitleFontStyle) {

		this.tooltipTitleFontStyle = tooltipTitleFontStyle;
	}

	/**
	 * 
	 * @return String - Tooltip title font weight style
	 */
	public String getTooltipTitleFontColor() {
		return tooltipTitleFontColor;
	}

	/**
	 * 
	 * @param tooltipTitleFontColor
	 *            String - Tooltip title font colour
	 */
	public void setTooltipTitleFontColor(String tooltipTitleFontColor) {
		String color = DataConverter.convertToJSColor(tooltipTitleFontColor);
		if (color != null) {
			this.tooltipTitleFontColor = color;
		}
	}

	/**
	 * 
	 * @return Number - pixel height of padding around tooltip text
	 */
	public int getTooltipYPadding() {
		return tooltipYPadding;
	}

	/**
	 * 
	 * @param tooltipYPadding
	 *            -Number - pixel height of padding around tooltip text
	 */
	public void setTooltipYPadding(int tooltipYPadding) {
		this.tooltipYPadding = tooltipYPadding;
	}

	/**
	 * 
	 * @return Number - pixel width of padding around tooltip text
	 */
	public int getTooltipXPadding() {
		return tooltipXPadding;
	}

	/**
	 * 
	 * @param tooltipXPadding
	 *            Number - pixel width of padding around tooltip text
	 */
	public void setTooltipXPadding(int tooltipXPadding) {
		this.tooltipXPadding = tooltipXPadding;
	}

	/**
	 * 
	 * @return Number - Size of the caret on the tooltip
	 */
	public int getTooltipCaretSize() {
		return tooltipCaretSize;
	}

	/**
	 * 
	 * @param tooltipCaretSize
	 *            Number - Size of the caret on the tooltip
	 */
	public void setTooltipCaretSize(int tooltipCaretSize) {
		this.tooltipCaretSize = tooltipCaretSize;
	}

	/**
	 * 
	 * @return Number - Pixel radius of the tooltip border
	 */
	public int getTooltipCornerRadius() {
		return tooltipCornerRadius;
	}

	/**
	 * 
	 * @param tooltipCornerRadius
	 *            Number - Pixel radius of the tooltip border
	 */
	public void setTooltipCornerRadius(int tooltipCornerRadius) {
		this.tooltipCornerRadius = tooltipCornerRadius;
	}

	/**
	 * 
	 * @return Number - Pixel offset from point x to tooltip edge
	 */
	public int getTooltipXOffset() {
		return tooltipXOffset;
	}

	/**
	 * 
	 * @param tooltipXOffset
	 *            - Number - Pixel offset from point x to tooltip edge
	 */
	public void setTooltipXOffset(int tooltipXOffset) {
		this.tooltipXOffset = tooltipXOffset;
	}

	/**
	 * 
	 * @return String - Template string for single tooltips
	 */
	public String getTooltipTemplate() {
		return tooltipTemplate;
	}

	/**
	 * 
	 * @param tooltipTemplate
	 *            String - Template string for single tooltips
	 */
	public void setTooltipTemplate(String tooltipTemplate) {
		this.tooltipTemplate = tooltipTemplate;
	}

	public boolean isCustomTooltip() {
		return customTooltip;
	}

	public void setCustomTooltip(boolean customTootlip) {
		this.customTooltip = customTootlip;
	}

	/**
	 * Returns the JavaScript function handle that will generate the HTML for the
	 * custom tooltip.
	 * @return
	 */
	public String getCustomTooltipGenerator() {
		return customTooltipGenerator;
	}

	/**
	 * Specifies a JavaScript function handle that will generate the HTML for the
	 * custom tooltip. You can point to a custom implementation or use one of the
	 * build in ones:
	 * <ul>
	 * <li>JWic.controls.Chart.customTooltipGenerators.standardList
	 * </ul>
	 * @param customTooltipHtml
	 */
	public void setCustomTooltipGenerator(String customTooltipHtml) {
		this.customTooltipGenerator = customTooltipHtml;
	}

	public boolean isZoomEnabled() {
		return zoomEnabled;
	}

	public void setZoomEnabled(boolean zoomEnabled) {
		this.zoomEnabled = zoomEnabled;
	}

}
