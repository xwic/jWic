/**
 * 
 */
package de.jwic.demo.chart;

import java.util.ArrayList;
import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.controls.chart.api.ChartInconsistencyException;
import de.jwic.controls.chart.api.ValueListDataset;
import de.jwic.controls.chart.api.ValueListDatasetModel;
import de.jwic.controls.chart.impl.OverlayChart;
import de.jwic.demo.chart.util.DataModelCreator;

/**
 * @author vedad
 *
 */
public class OverlayChartDemo extends ChartDemo<OverlayChart, ValueListDatasetModel> {

	private static final long serialVersionUID = 1L;

	/**
	 * @param container
	 */
	public OverlayChartDemo(IControlContainer container) {
		super(container);
	}

	/* (non-Javadoc)
	 * @see de.jwic.demo.chart.ChartDemo#createChart(de.jwic.controls.chart.api.ChartModel)
	 */
	@Override
	protected OverlayChart createChart(ValueListDatasetModel model) {
		OverlayChart chart = new OverlayChart(this, "chart", model);
		return chart;
	}

	/* (non-Javadoc)
	 * @see de.jwic.demo.chart.ChartDemo#changeFillColor(java.lang.String)
	 */
	@Override
	protected void changeFillColor(String text) throws ChartInconsistencyException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see de.jwic.demo.chart.ChartDemo#changeHighColor(java.lang.String)
	 */
	@Override
	protected void changeHighColor(String text) throws ChartInconsistencyException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see de.jwic.demo.chart.ChartDemo#createModel()
	 */
	@Override
	protected ValueListDatasetModel createModel() {
		return DataModelCreator.getOverlayValueListDatasetModel();
	}

	/* (non-Javadoc)
	 * @see de.jwic.demo.chart.ChartDemo#addElementToTheChart(de.jwic.demo.chart.TableElement)
	 */
	@Override
	protected void addElementToTheChart(TableElement element) throws ChartInconsistencyException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see de.jwic.demo.chart.ChartDemo#updateElementInChart(de.jwic.demo.chart.TableElement)
	 */
	@Override
	protected void updateElementInChart(TableElement selectedTableElement) throws ChartInconsistencyException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see de.jwic.demo.chart.ChartDemo#deleteElementFromChart(de.jwic.demo.chart.TableElement)
	 */
	@Override
	protected void deleteElementFromChart(TableElement selectedTableElement) throws ChartInconsistencyException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see de.jwic.demo.chart.ChartDemo#convertChartModelToTableElements()
	 */
	@Override
	protected List<TableElement> convertChartModelToTableElements() {
		List<TableElement> elements = new ArrayList<TableElement>();

		for (ValueListDataset set : model.getDatasets()) {
			int i = 0;
			for (Double in : set.getData()) {
				TableElement el = new TableElement();
				el.setTitle(model.getLabels().get(i));
				el.setValue(in.toString());
				el.setFillColor(set.getFillColor());
				el.setHighlightColor(set.getHighlightColor());
				elements.add(el);
				i++;
			}

		}

		return elements;
	}

}
