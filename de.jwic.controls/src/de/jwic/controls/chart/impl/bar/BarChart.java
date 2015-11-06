package de.jwic.controls.chart.impl.bar;

import java.awt.Color;

import de.jwic.base.IControlContainer;
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
@JavaScriptSupport()
public class BarChart extends Chart<BarChartModel> {

	public BarChart(IControlContainer container, String name,
			BarChartModel model) throws ChartInconsistencyException {
		super(container, name, ChartType.BAR, model);
		setTemplateName(Chart.class.getName());
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
