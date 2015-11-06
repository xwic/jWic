package de.jwic.controls.chart.api.configuration;

import java.awt.Color;

import de.jwic.controls.chart.impl.util.DatenConverter;

public class ChartOptions {

	// Boolean - Whether we should show a stroke on each segment
	private boolean segmentShowStroke = true;

	// String - The colour of each segment stroke
	private Color segmentStrokeColor = new Color(0, 0, 0);
	// Number - The width of each segment stroke
	private int segmentStrokeWidth = 2;

	// Number - The percentage of the chart that we cut out of the
	// middle
	private int percentageInnerCutout = 50; // This is 0 for Pie charts

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

	// /Boolean - Whether grid lines are shown across the chart
	private boolean scaleShowGridLines = true;

	// String - Colour of the grid lines
	private Color scaleGridLineColor = new Color(0, 0, 0);

	// Number - Width of the grid lines
	private int scaleGridLineWidth = 1;

	// Boolean - Whether to show horizontal lines (except X
	// axis)
	private boolean scaleShowHorizontalLines = true;

	// Boolean - Whether to show vertical lines (except Y axis)
	private boolean scaleShowVerticalLines = true;

	// Boolean - Whether the line is curved between points
	private boolean bezierCurve = true;

	// Number - Tension of the bezier curve between points
	private double bezierCurveTension = 0.4;

	// Boolean - Whether to show a dot for each point
	private boolean pointDot = true;

	// Number - Radius of each point dot in pixels
	private int pointDotRadius = 4;

	// Number - Pixel width of point dot stroke
	private int pointDotStrokeWidth = 1;

	// Number - amount extra to add to the radius to cater for
	// hit detection outside the drawn point
	private int pointHitDetectionRadius = 20;

	// Boolean - Whether to show a stroke for datasets
	private boolean datasetStroke = true;

	// Number - Pixel width of dataset stroke
	private int datasetStrokeWidth = 2;

	// Boolean - Whether to fill the dataset with a colour
	private boolean datasetFill = true;

	// String - A legend template
	private String legendTemplate = "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color=<%=datasets[i].strokeColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>";

	public boolean isScaleShowGridLines() {
		return scaleShowGridLines;
	}

	public void setScaleShowGridLines(boolean scaleShowGridLines) {
		this.scaleShowGridLines = scaleShowGridLines;
	}

	public String getScaleGridLineColor() {
		return DatenConverter.convertToJSColor(scaleGridLineColor, "0.5");
	}

	public void setScaleGridLineColor(Color scaleGridLineColor) {
		this.scaleGridLineColor = scaleGridLineColor;
	}

	public int getScaleGridLineWidth() {
		return scaleGridLineWidth;
	}

	public void setScaleGridLineWidth(int scaleGridLineWidth) {
		this.scaleGridLineWidth = scaleGridLineWidth;
	}

	public boolean isScaleShowHorizontalLines() {
		return scaleShowHorizontalLines;
	}

	public void setScaleShowHorizontalLines(boolean scaleShowHorizontalLines) {
		this.scaleShowHorizontalLines = scaleShowHorizontalLines;
	}

	public boolean isScaleShowVerticalLines() {
		return scaleShowVerticalLines;
	}

	public void setScaleShowVerticalLines(boolean scaleShowVerticalLines) {
		this.scaleShowVerticalLines = scaleShowVerticalLines;
	}

	public boolean isBezierCurve() {
		return bezierCurve;
	}

	public void setBezierCurve(boolean bezierCurve) {
		this.bezierCurve = bezierCurve;
	}

	public double getBezierCurveTension() {
		return bezierCurveTension;
	}

	public void setBezierCurveTension(double bezierCurveTension) {
		this.bezierCurveTension = bezierCurveTension;
	}

	public boolean isPointDot() {
		return pointDot;
	}

	public void setPointDot(boolean pointDot) {
		this.pointDot = pointDot;
	}

	public int getPointDotRadius() {
		return pointDotRadius;
	}

	public void setPointDotRadius(int pointDotRadius) {
		this.pointDotRadius = pointDotRadius;
	}

	public int getPointDotStrokeWidth() {
		return pointDotStrokeWidth;
	}

	public void setPointDotStrokeWidth(int pointDotStrokeWidth) {
		this.pointDotStrokeWidth = pointDotStrokeWidth;
	}

	public int getPointHitDetectionRadius() {
		return pointHitDetectionRadius;
	}

	public void setPointHitDetectionRadius(int pointHitDetectionRadius) {
		this.pointHitDetectionRadius = pointHitDetectionRadius;
	}

	public boolean isDatasetStroke() {
		return datasetStroke;
	}

	public void setDatasetStroke(boolean datasetStroke) {
		this.datasetStroke = datasetStroke;
	}

	public int getDatasetStrokeWidth() {
		return datasetStrokeWidth;
	}

	public void setDatasetStrokeWidth(int datasetStrokeWidth) {
		this.datasetStrokeWidth = datasetStrokeWidth;
	}

	public boolean isDatasetFill() {
		return datasetFill;
	}

	public void setDatasetFill(boolean datasetFill) {
		this.datasetFill = datasetFill;
	}

	public String getLegendTemplate() {
		return legendTemplate;
	}

	public void setLegendTemplate(String legendTemplate) {
		this.legendTemplate = legendTemplate;
	}
	
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
		
		// Boolean - Whether the scale should start at zero, or
		// an order of magnitude down from the lowest value
		private boolean scaleBeginAtZero = true;

		// Boolean - Whether grid lines are shown across the chart
		private boolean scaleShowGridLines = true;

		// String - Colour of the grid lines
		private Color scaleGridLineColor = new Color(0, 0, 0);

		// Number - Width of the grid lines
		private int scaleGridLineWidth = 1;

		// Boolean - Whether to show horizontal lines (except X
		// axis)
		private boolean scaleShowHorizontalLines = true;

		// Boolean - Whether to show vertical lines (except Y axis)
		private boolean scaleShowVerticalLines = true;

		// Boolean - If there is a stroke on each bar
		private boolean barShowStroke = true;

		// Number - Pixel width of the bar stroke
		private int barStrokeWidth = 2;

		// Number - Spacing between each of the X value sets
		private int barValueSpacing = 5;

		// Number - Spacing between data sets within X values
		private int barDatasetSpacing = 1;

		// String - A legend template
		private String legendTemplate = "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>";

		public boolean isScaleBeginAtZero() {
			return scaleBeginAtZero;
		}

		public void setScaleBeginAtZero(boolean scaleBeginAtZero) {
			this.scaleBeginAtZero = scaleBeginAtZero;
		}

		public boolean isScaleShowGridLines() {
			return scaleShowGridLines;
		}

		public void setScaleShowGridLines(boolean scaleShowGridLines) {
			this.scaleShowGridLines = scaleShowGridLines;
		}

		public String getScaleGridLineColor() {
			return DatenConverter.convertToJSColor(scaleGridLineColor);
		}

		public void setScaleGridLineColor(Color scaleGridLineColor) {
			this.scaleGridLineColor = scaleGridLineColor;
		}

		public int getScaleGridLineWidth() {
			return scaleGridLineWidth;
		}

		public void setScaleGridLineWidth(int scaleGridLineWidth) {
			this.scaleGridLineWidth = scaleGridLineWidth;
		}

		public boolean isScaleShowHorizontalLines() {
			return scaleShowHorizontalLines;
		}

		public void setScaleShowHorizontalLines(boolean scaleShowHorizontalLines) {
			this.scaleShowHorizontalLines = scaleShowHorizontalLines;
		}

		public boolean isScaleShowVerticalLines() {
			return scaleShowVerticalLines;
		}

		public void setScaleShowVerticalLines(boolean scaleShowVerticalLines) {
			this.scaleShowVerticalLines = scaleShowVerticalLines;
		}

		public boolean isBarShowStroke() {
			return barShowStroke;
		}

		public void setBarShowStroke(boolean barShowStroke) {
			this.barShowStroke = barShowStroke;
		}

		public int getBarStrokeWidth() {
			return barStrokeWidth;
		}

		public void setBarStrokeWidth(int barStrokeWidth) {
			this.barStrokeWidth = barStrokeWidth;
		}

		public int getBarValueSpacing() {
			return barValueSpacing;
		}

		public void setBarValueSpacing(int barValueSpacing) {
			this.barValueSpacing = barValueSpacing;
		}

		public int getBarDatasetSpacing() {
			return barDatasetSpacing;
		}

		public void setBarDatasetSpacing(int barDatasetSpacing) {
			this.barDatasetSpacing = barDatasetSpacing;
		}

		public String getLegendTemplate() {
			return legendTemplate;
		}

		public void setLegendTemplate(String legendTemplate) {
			this.legendTemplate = legendTemplate;
		}
		
		
		


}
