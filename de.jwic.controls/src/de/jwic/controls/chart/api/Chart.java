package de.jwic.controls.chart.api;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.IResourceControl;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.chart.impl.util.DataConverter;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * Coomon implementation of the all chart types 
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 19.10.2015
 */
@JavaScriptSupport
public abstract class Chart<M extends ChartModel, L extends ChartConfiguration>
		extends Control implements IResourceControl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8011236594229480026L;

	private ChartType chartType;
	private M model;
	protected List<SelectionListener> elementClickListeners = null;
	private List<ElementSelectedListener> elementSelectedListeners;
	private List<ActionListener> animationInProgressListeners;
	private L configuration;
	
	/**
	 * 
	 * @param container
	 * @param name
	 * @param type
	 * @param model
	 */
	public Chart(IControlContainer container, String name, ChartType type,
			M model) {
		super(container, name);
		setTemplateName(Chart.class.getName());
		this.chartType = type;
		this.model = model;
		this.model.setChart(this);

	}

	/**
	 * 
	 * @return
	 */
	public L getConfiguration() {
		return configuration;
	}

	/**
	 * 
	 * @param configuration
	 */
	protected void setConfiguration(L configuration) {
		this.configuration = configuration;
	}

	/**
	 * process some events during animation i the chart
	 */
	public void actionAnimationInProgress() {
		if (animationInProgressListeners != null) {
			for (Iterator<ActionListener> it = animationInProgressListeners
					.iterator(); it.hasNext();) {
				ActionListener osl = it.next();
				osl.actionPerformed(null);
			}
		}
	}

	/**
	 * process listener after clicking in the chart area
	 * 
	 * @param param
	 */
	public void actionClick(String param) {
		if (elementClickListeners != null) {
			SelectionEvent e = new SelectionEvent(param, false);
			for (Iterator<SelectionListener> it = elementClickListeners
					.iterator(); it.hasNext();) {
				SelectionListener osl = it.next();
				osl.objectSelected(e);
			}
		}
	}

	public void actionMove(String param) {
		System.out.println("Move action" + param);
	}

	/**
	 * process listener after selecting chart area still not working
	 * 
	 * @param param
	 */
	public void actionSelect(String param) {
		if (elementSelectedListeners != null) {
			ElementSelectedEvent e = new ElementSelectedEvent(this, param);
			for (Iterator<ElementSelectedListener> it = elementSelectedListeners
					.iterator(); it.hasNext();) {
				ElementSelectedListener osl = it.next();
				osl.elementSelected(e);
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	@IncludeJsOption
	public String getChartType() {
		return chartType.getChartName();
	}

	/**
	 * set the chart type using enumeration ChartType
	 * 
	 * @param chartType
	 */
	public void setChartType(ChartType chartType) {
		this.chartType = chartType;
		setRequireRedraw(true);
	}

	/**
	 * the model containing data in the chart
	 * 
	 * @return
	 */
	public M getModel() {
		return model;
	}

	/**
	 * set the model data for chart. Can be also set after presentation. Refresh
	 * is not needed anymore
	 * 
	 * @param model
	 */
	public void setModel(M model) {
		this.model = model;
		model.setChart(this);
		requireRedraw();
	}

	/**
	 * Register a listener that will be notified when an element has been
	 * selected.
	 * 
	 * @param listener
	 */
	public void addElementSelectedListener(ElementSelectedListener listener) {
		if (elementSelectedListeners == null) {
			elementSelectedListeners = new ArrayList<ElementSelectedListener>();
		}
		elementSelectedListeners.add(listener);
	}

	/**
	 * Removes the specified listener.
	 * 
	 * @param listener
	 */
	public void removeElementSelectedListener(ElementSelectedListener listener) {
		if (elementSelectedListeners != null) {
			elementSelectedListeners.remove(listener);
		}
	}

	/**
	 * Register a listener that will be notified when an element has been
	 * selected.
	 * 
	 * @param listener
	 */
	public void addSelectionListener(SelectionListener listener) {
		if (elementClickListeners == null) {
			elementClickListeners = new ArrayList<SelectionListener>();
		}
		elementClickListeners.add(listener);
	}

	/**
	 * Removes the specified listener.
	 * 
	 * @param listener
	 */
	public void removeSelectionListener(SelectionListener listener) {
		if (elementClickListeners != null) {
			elementClickListeners.remove(listener);
		}
	}

	/**
	 * returns the configuration as json array, which will be used directly on
	 * java script site
	 * 
	 * @return
	 */
	public String getConfigurationJSON() {

		return DataConverter.convertToJson(configuration, chartType);

	}

	

}
