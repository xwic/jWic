/*
 * Created on 30.11.2004
 */
package de.jwic.controls;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.base.ValueChangedQueue;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;


/**
 * CheckboxControl creates one or more checkboxes that can be multi selected.
 * 
 * $Id: CheckboxControl.java,v 1.9 2008/09/17 15:19:46 lordsam Exp $
 * @version $Revision: 1.9 $
 * @author JBornemann
 */
public class CheckboxControl extends ListControl {
	
	private static final long serialVersionUID = 2L;
	
	private int columns = 0;

	public final static String FIELD_ELEMENT = "ELEMENT";
	protected boolean changed = false;
	protected String keyList = null;
	protected String lastSelection = "";
	
	protected Map<String, Field> fieldKeyMap = new HashMap<String, Field>();
	private ValueChangedListener valueChangedListener = null;
	
	
	/**
	 * @param container
	 */
	public CheckboxControl(IControlContainer container) {
		super(container, null);
		init();
	}
	/**
	 * @param container
	 * @param name
	 */
	public CheckboxControl(IControlContainer container, String name) {
		super(container, name);
		init();
	}
	/* (non-Javadoc)
	 * @see de.jwic.controls.ListControl#init()
	 */
	private void init() {
		
		valueChangedListener = new ValueChangedListener() {
			private static final long serialVersionUID = 1L;
			public void valueChanged(ValueChangedEvent event) {
				// setting an empty string to replace 'null' does not invoke a selection.
				if (!(event.getOldValues() == null && "".equals(event.getNewValue()))) { 
					changed = true;
					String selection = getSelectedKey();
					if (!selection.equals(lastSelection)) {
						lastSelection = selection;
						sendElementSelectedEvent();
					}
				}
			}
		};
		// remove the default field and recreate it to remove the listener.

		String name = field.getName();
		field.destroy();
		field = new Field(this, name);
		field.setValue("");
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.controls.ListControl#addElement(java.lang.String, java.lang.String)
	 */
	public ListEntry addElement(String title, String key) {
		
		if (fieldKeyMap.containsKey(key)) {
			throw new IllegalArgumentException("Dupplicate key: " + key);
		}
		
		Field fld = new Field(this);
		fld.addValueChangedListener(valueChangedListener);
		fieldKeyMap.put(key, fld);
		
		return super.addElement(title, key);
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.controls.ListControl#clear()
	 */
	public void clear() {
		// remove all fields
		for (Iterator<Field> it = fieldKeyMap.values().iterator(); it.hasNext(); ) {
			removeField(it.next());
		}
		fieldKeyMap.clear();
		changed = true;
		sendElementSelectedEvent();
		super.clear();
	}
	
	/**
	 * Returns the field associated with the key.
	 */
	public Field getFieldByKey(String key) {
		return fieldKeyMap.get(key);
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.controls.ListControl#setSelectedKey(java.lang.String)
	 */
	public void setSelectedKey(String newKey) {
		// deselect all
		// FLI: Use batch update to prevent listener invocation
		ValueChangedQueue queue = new ValueChangedQueue();
		for (Iterator<ListEntry> it = getElements().iterator(); it.hasNext();) {
			String key = it.next().getKey();
			Field fld = getFieldByKey(key);
			if (fld != null) {
				fld.batchUpdate(new String[] {""}, queue);
			}
		}
		if (!(newKey == null || newKey.length() == 0)) {
			// regular selection
			StringTokenizer st = new StringTokenizer(newKey, ";");
			while(st.hasMoreElements()){
				String key = st.nextToken();
				Field fld = getFieldByKey(key);
				if (fld != null) {
					fld.batchUpdate(new String[] {key}, queue);
				}
			}
		}
		// now send the event
		changed = true;
		lastSelection = newKey;
		sendElementSelectedEvent();
		requireRedraw();
	}
	/* (non-Javadoc)
	 * @see de.jwic.controls.ListControl#getSelectedKey()
	 */
	public String getSelectedKey() {
		if (keyList == null || changed) {
			String key = "";
			for (Iterator<ListEntry> it = getElements().iterator(); it.hasNext();) {
				ListEntry entry = it.next();
				Field fld = getFieldByKey(entry.key);
				if (fld != null && entry.key.equals(fld.getValue())) {
					// element is selected
					if (key.length() > 0) {
						key += ";";
					}
					key += entry.key;
				}
			}
			keyList = key;
			changed = false;
		}
		return keyList;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.controls.ListControl#getSelectedKeys()
	 */
	public String[] getSelectedKeys() {
		
		String[] all = new String[getElements().size()];
		int count = 0;
		for (Iterator<ListEntry> it = getElements().iterator(); it.hasNext();) {
			ListEntry entry = it.next();
			Field fld = getFieldByKey(entry.key);
			if (fld != null && entry.key.equals(fld.getValue())) {
				// element is selected
				all[count++] = entry.key;
			}
		}
		String[] result = new String[count];
		if (count > 0) {
			System.arraycopy(all, 0, result, 0, count);
		}
		return result;
		
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.controls.ListControl#removeElement(java.lang.String)
	 */
	public int removeElement(String key) {
		
		boolean selectionChanged = false;
		for (Iterator<String> it = fieldKeyMap.keySet().iterator(); it.hasNext(); ) {
			String fKey = it.next();
			if (fKey.equals(key)) {
				Field f = fieldKeyMap.get(fKey);
				if (f.getValue().equals(fKey)) { // element was selectecd
					selectionChanged = true;
				}
				it.remove();
			}
		}
		changed = true;
		if (selectionChanged) {
			sendElementSelectedEvent();
		}
		requireRedraw();
		return super.removeElement(key);
	}
	
	/**
	 * Used by the velocity template to determine if a new line is required.
	 * @param count
	 * @return
	 */
	public boolean isDoBreak(int count) {
		return columns != 0 && count % columns == 0;
	}
	
	/**
	 * @return Returns the columns.
	 */
	public int getColumns() {
		return columns;
	}
	/**
	 * @param columns The columns to set.
	 */
	public void setColumns(int columns) {
		this.columns = columns;
		requireRedraw();
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.controls.ListControl#isKeySelected(java.lang.String)
	 */
	public boolean isKeySelected(String key) {
		Field fld = getFieldByKey(key);
		boolean selected = fld != null && key.equals(fld.getValue());
		return selected;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.controls.ListControl#getForceFocusElement()
	 */
	public String getForceFocusElement() {
		if (getElements().size() == 0) {
			return null;
		}
		String[] selected = getSelectedKeys();
		String key = getElements().iterator().next().key;
		
		if (selected != null && selected.length > 0) {
			key = selected[0];
		}
		Field fld = getFieldByKey(key);
		return fld != null ? "chk_" + getControlID() + fld.getName() : null; 
	}
}
