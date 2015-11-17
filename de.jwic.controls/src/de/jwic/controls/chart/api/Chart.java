package de.jwic.controls.chart.api;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;

import de.jwic.base.Control;
import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.chart.api.configuration.ChartConfiguration;
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
public abstract class Chart<M extends ChartModel, L extends ChartConfiguration>
		extends Control {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8011236594229480026L;
	protected Field content;

	private ChartType chartType;
	private M model;
	protected List<SelectionListener> elementClickListeners = null;
	private List<ElementSelectedListener> elementSelectedListeners;
	private List<ActionListener> animationCompleteListeners;
	private List<ActionListener> animationInProgressListeners;
	private L localConfiguration;

	public Chart(IControlContainer container, String name, ChartType type,
			M model) {
		super(container, name);
		content = new Field(this, "chartContent");
		setTemplateName(Chart.class.getName());
		this.chartType = type;
		this.model = model;
		model.setChart(this);

	}

	public L getLocalChartConfiguration() {
		return localConfiguration;
	}

	protected void setLocalChartConfiguration(L configuration) {
		this.localConfiguration = configuration;
	}

	public void actionAnimationInProgress() {
		if (animationInProgressListeners != null) {
			for (Iterator<ActionListener> it = animationInProgressListeners
					.iterator(); it.hasNext();) {
				ActionListener osl = it.next();
				osl.actionPerformed(null);
			}
		}
	}

//	public void actionAnimationComplete() {
//		if (animationCompleteListeners != null) {
//			for (Iterator<ActionListener> it = animationCompleteListeners
//					.iterator(); it.hasNext();) {
//				ActionListener osl = it.next();
//				osl.actionPerformed(null);
//			}
//		}
//	}

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

	public String getConfigurationJSON() {
		try {
			try {
				return DatenConverter.convertToJson(localConfiguration, chartType);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

}
