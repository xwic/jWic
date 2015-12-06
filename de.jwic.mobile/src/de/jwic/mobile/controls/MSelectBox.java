package de.jwic.mobile.controls;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;
import de.jwic.common.enableable.Enableable;
import de.jwic.common.enableable.EnableableHandler;
import de.jwic.common.properties.PropertiesHandler;
import de.jwic.common.properties.PropertyChangedListener;
import de.jwic.common.properties.PropertyObservable;
import de.jwic.common.selectable.Selectable;
import de.jwic.common.selectable.SelectionHandler;
import de.jwic.common.visible.Visible;
import de.jwic.events.SelectionListener;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by boogie on 11/11/14.
 */
@JavaScriptSupport
public class MSelectBox<T> extends Control implements Selectable, Visible, Enableable, PropertyObservable{
	private final EnableableHandler enableableHandler;
	private final SelectionHandler selectionHandler;
	private final PropertiesHandler propertiesHandler;
	private final Map<String, T> possibleSelections;
	private final LabelEvaluator<T> evaluator;

	private T currentSelection;
	/**
	 * Constructs a new control instance and adds it to the specified
	 * container with the specified name. If the name is <code>null</code>,
	 * a unique name will be chosen by the container.
	 *
	 * @param container
	 * @param name
	 */
	public MSelectBox(IControlContainer container, String name, LabelEvaluator<T> evaluator) {
		super(container, name);
		this.enableableHandler = new EnableableHandler(this);
		this.selectionHandler = new SelectionHandler<MSelectBox>(this);
		this.propertiesHandler = new PropertiesHandler(this);
		this.possibleSelections = new LinkedHashMap<String, T>();

		this.evaluator = evaluator;
	}

	@Override
	public void actionPerformed(String actionId, String parameter) {
		if(!this.isEnabled()){
			return;
		}
		if("select".equals(actionId)){
			setSelectionNoRedraw(this.possibleSelections.get(parameter));
		}
	}

	private void setSelectionNoRedraw(T selection) {
		this.currentSelection = selection;
		this.propertiesHandler.setPropertyNoRedraw("selection", currentSelection);
		this.selectionHandler.select();
	}

	public T getSelection(){
		return this.currentSelection;
	}

	public void addSelection(T selection){
		this.possibleSelections.put(this.evaluator.evaluate(selection), selection);
		this.currentSelection = selection;
		this.requireRedraw();
	}

	public void removeSelection(T selection){
		this.possibleSelections.remove(this.evaluator.evaluate(selection));
	}

	public void setSelection(T selection){
		setSelectionNoRedraw(selection);
		this.requireRedraw();
	}

	@Override
	public void enable() {
		enableableHandler.enable();
	}

	@Override
	public void disable() {
		enableableHandler.disable();
	}

	@Override
	@IncludeJsOption
	public boolean isEnabled() {
		return enableableHandler.isEnabled();
	}

	@Override
	public void setEnabled(boolean enabled) {
		enableableHandler.setEnabled(enabled);
	}

	@Override
	public void addPropertyChangedListener(PropertyChangedListener listener) {
		this.propertiesHandler.addPropertyChangedListener(listener);
	}

	@Override
	public void removePropertyChangedListener(PropertyChangedListener listener) {
		this.propertiesHandler.removePropertyChangedListener(listener);
	}

	@Override
	public void select() {
		this.selectionHandler.select();
	}

	@Override
	public void addSelectionListener(SelectionListener listener) {
		this.selectionHandler.addSelectionListener(listener);
	}

	@Override
	public void removeSelectionListener(SelectionListener listener) {
		this.selectionHandler.removeSelectionListener(listener);
	}

	@IncludeJsOption
	public final Set<String> getSelectionSet(){
		return Collections.unmodifiableSet(this.possibleSelections.keySet());
	}

	@IncludeJsOption
	public final String getCurrentSelectionString(){
		return this.currentSelection == null ? "" : this.evaluator.evaluate(this.currentSelection);
	}

	public static interface LabelEvaluator<T>{
		String evaluate(T obj);
	}

	@SuppressWarnings("unchecked")
	public static <T> LabelEvaluator<T> toStringLabelEvaluator(){
		return (LabelEvaluator<T>) ToStringEvaluator.INSTANCE;
	}

	private static enum ToStringEvaluator implements LabelEvaluator<Object>{
		INSTANCE;
		@Override
		public String evaluate(Object obj) {
			return obj == null ? "" : obj.toString();
		}
	}
}
