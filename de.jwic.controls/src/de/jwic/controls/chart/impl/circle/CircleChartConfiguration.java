package de.jwic.controls.chart.impl.circle;


import de.jwic.controls.chart.api.configuration.ChartConfiguration;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 14.11.2015
 */
public class CircleChartConfiguration extends ChartConfiguration {


	// Number - The percentage of the chart that we cut out of the
	// middle
	private int percentageInnerCutout = 50; // This is 0 for Pie charts



	public CircleChartConfiguration() {
		super(
				"<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color=<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>");
	}

}
