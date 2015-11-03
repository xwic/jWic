package de.jwic.controls.chart.api;

import de.jwic.base.Control;
import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.chart.api.configuration.ChartOptions;
import de.jwic.controls.chart.api.configuration.GlobalChartConfiguration;
import de.jwic.controls.chart.api.exception.ChartInconsistencyException;
import de.jwic.controls.chart.impl.bar.BarChartOptions;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 19.10.2015
 */
@JavaScriptSupport
public abstract class Chart<Model extends ChartModel, Options extends ChartOptions>
		extends Control {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8011236594229480026L;
	protected Field content;
	private int width = 800;
	private int height = 800;
	private boolean enabled = true;
	private ChartType chartType;
	private Model model;
	private GlobalChartConfiguration global = new GlobalChartConfiguration();
	private Options options;

	public Chart(IControlContainer container, String name, ChartType type,
			Model model, Options options) throws ChartInconsistencyException {

		super(container, name);

		content = new Field(this, "chartContent");
		this.chartType = type;
		this.model = model;
		this.options = options;
		checkConsistencyOfTheControl();

	}

	private void checkConsistencyOfTheControl()
			throws ChartInconsistencyException {
		// TODO Auto-generated method stub

	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getChartType() {
		return chartType.getChartName();
	}

	public void setChartType(ChartType chartType) {
		this.chartType = chartType;
		setRequireRedraw(true);
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Field getContent() {
		return content;
	}

	public void setContent(Field content) {
		this.content = content;
	}

	public GlobalChartConfiguration getGlobal() {
		return global;
	}

	public void setGlobal(GlobalChartConfiguration global) {
		this.global = global;
	}

	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}

}
