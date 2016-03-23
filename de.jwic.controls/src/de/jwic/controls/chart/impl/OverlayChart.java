/**
 * 
 */
package de.jwic.controls.chart.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.chart.api.Chart;
import de.jwic.controls.chart.api.ChartType;
import de.jwic.controls.chart.api.ValueListDatasetModel;

/**
 * @author vedad
 *
 */
@JavaScriptSupport
public class OverlayChart extends Chart<ValueListDatasetModel, OverlayChartConfiguration> {

	/**
	 * @param container
	 * @param name
	 * @param model
	 */
	public OverlayChart(IControlContainer container, String name, ValueListDatasetModel model) {
		super(container, name, ChartType.OVERLAY, model);
		setConfiguration(new OverlayChartConfiguration());
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IResourceControl#attachResource(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void attachResource(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// TODO Auto-generated method stub

	}

}
