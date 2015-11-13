package de.jwic.controls.chart.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import de.jwic.base.Control;
import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.chart.api.configuration.GlobalChartConfiguration;
import de.jwic.controls.chart.api.exception.ChartInconsistencyException;
import de.jwic.controls.chart.impl.util.DatenConverter;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 19.10.2015
 */
@JavaScriptSupport
public abstract class Chart<M extends ChartModel> extends Control {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8011236594229480026L;
	protected Field content;
	private int width = 800;
	private int height = 800;
	private boolean enabled = true;
	private ChartType chartType;
	private M model;
	private GlobalChartConfiguration global = new GlobalChartConfiguration();
	protected List<SelectionListener> elementClickListeners = null;
	private List<ElementSelectedListener> elementSelectedListeners;

	public Chart(IControlContainer container, String name, ChartType type,
			M model) {
		super(container, name);
		content = new Field(this, "chartContent");
		setTemplateName(Chart.class.getName());
		this.chartType = type;
		this.model = model;
		model.setChart(this);

	}

	public void actionClick(String param) {
		if (elementClickListeners != null) {
			SelectionEvent e = new SelectionEvent(param, false);
			for (Iterator<SelectionListener> it = elementClickListeners
					.iterator(); it.hasNext();) {
				SelectionListener osl = it.next();
				osl.objectSelected(e);
			}
		}
		System.out.println(param);
	}

	public void actionSelect(String param) {
		if (elementSelectedListeners != null) {
			ElementSelectedEvent e = new ElementSelectedEvent(this, param);
			for (Iterator<ElementSelectedListener> it = elementSelectedListeners
					.iterator(); it.hasNext();) {
				ElementSelectedListener osl = it.next();
				osl.elementSelected(e);
			}
		}
		System.out.println(param);
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

	public M getModel() {
		return model;
	}

	public void setModel(M model) {
		this.model = model;
		requireRedraw();
	}

	public Field getContent() {
		return content;
	}

	public void setContent(Field content) {
		this.content = content;
	}

	public String getGlobal() {

		try {
			return DatenConverter.convertToJson(global);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "{}";

	}

	public GlobalChartConfiguration getGlobalConfiguration() {
		return global;
	}

	public void setGlobal(GlobalChartConfiguration global) {
		this.global = global;
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

	@IncludeJsOption(jsPropertyName = "legendTemplate")
	public abstract String getLegendTemplate();

	public abstract void setLegendTemplate(String legendTemplate);

}
