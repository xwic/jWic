/*
 * Copyright 2005-2007 jWic group (http://www.jwic.de)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * de.jwic.controls.Combo
 * Created on Jan 7, 2010
 * $Id: Combo.java,v 1.9 2011/12/06 12:31:49 adrianionescu12 Exp $
 */
package de.jwic.controls.combo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONWriter;

import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.base.ImageRef;
import de.jwic.base.JavaScriptSupport;
import de.jwic.data.IBaseLabelProvider;
import de.jwic.data.IContentProvider;
import de.jwic.data.DataLabel;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.ValueChangedListener;
import de.jwic.json.BeanToJsonSerializer;
import de.jwic.json.IObjectToJsonSerializer;
import de.jwic.json.JsonResourceControl;

/**
 * Displays an InputBox with a selection. Acts as the base control for a set
 * of various, interactive input/selection controls.
 * 
 * @author lippisch
 */
@JavaScriptSupport
public class Combo<A> extends JsonResourceControl {

	private static final long serialVersionUID = 4L;

	protected String cssClass = "j-combo";
	protected String emptyInfoText = null;
	protected int width = 150;
	
	protected boolean flagAsError = false;
	protected boolean enabled = true;
	protected boolean changeNotification = true;
	protected boolean multiSelect = false;
	protected Field textField;
	protected Field keyField;
	protected ImageRef defaultImage = null;
	
	protected List<ElementSelectedListener> listeners = new ArrayList<ElementSelectedListener>();
	
	protected IContentProvider<A> contentProvider = null;
	protected IBaseLabelProvider<A> baseLabelProvider = null;
	
	protected IObjectToJsonSerializer objectSerializer = new BeanToJsonSerializer();
	protected ComboBehavior comboBehavior = new ComboBehavior();

	/**
	 * @param container
	 * @param name
	 */
	public Combo(IControlContainer container, String name) {
		super(container, name);
		setTemplateName(Combo.class.getName()); // fix to combo templates
		textField = new Field(this, "text");
		keyField = new Field(this, "key");
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.Control#actionPerformed(java.lang.String, java.lang.String)
	 */
	@Override
	public void actionPerformed(String actionId, String parameter) {
		
		if ("elementSelected".equals(actionId)) {
			fireElementSelectedEvent(new ElementSelectedEvent(this, getSelectedElement()));
		} else {
			super.actionPerformed(actionId, parameter);
		}
	}
	
	/**
	 * Add ElementSelectedListener.
	 * @param listener
	 */
	public void addElementSelectedListener(ElementSelectedListener listener) {
		listeners.add(listener);
	}

	/**
	 * Remove ElementSelectedListener.
	 * @param listener
	 */
	public void removeElementSelectedListener(ElementSelectedListener listener) {
		listeners.remove(listener);
	}

	/**
	 * Add a listener to the text field. Is triggered if the text was modified.
	 * @param listener
	 */
	public void addTextValueChangedListener(ValueChangedListener listener) {
		textField.addValueChangedListener(listener);
	}

	/**
	 * Remove a previous added listener from the text field.
	 * @param listener
	 */
	public void removeTextValueChangedListener(ValueChangedListener listener) {
		textField.removeValueChangedListener(listener);
	}
	
	/**
	 * Fire the element selected event.
	 * @param event
	 */
	protected void fireElementSelectedEvent(ElementSelectedEvent event) {
		ElementSelectedListener[] ls = new ElementSelectedListener[listeners.size()];
		ls = listeners.toArray(ls);
		for (ElementSelectedListener listener : ls) {
			listener.elementSelected(event);
		}
	}
	
	/**
	 * Returns the key of the element selected.
	 * @return
	 */
	public String getSelectedKey() {
		return keyField.getValue();
	}
	
	/**
	 * Returns the text entered/selected.
	 * @return
	 */
	public String getText() {
		return textField.getValue();
	}
	
	/**
	 * Returns the selected element.
	 * @return
	 */
	public A getSelectedElement() {
		String key = getSelectedKey();
		if (key != null && !key.isEmpty()) {
			return contentProvider.getObjectFromKey(key);
		}
		return null;
	}
	
	/**
	 * Set the selected element. 
	 * @param elm
	 */
	public void setSelectedElement(A elm) {
		if (elm != null) {
			setSelectedKey(contentProvider.getUniqueKey(elm));
			if (baseLabelProvider != null) {
				setText(baseLabelProvider.getBaseLabel(elm).text);
			}
		} else {
			setSelectedKey(null);
			setText("");
		}
		
		fireElementSelectedEvent(new ElementSelectedEvent(this, getSelectedElement()));
	}
	
	/**
	 * Set the selected key.
	 * @param key
	 */
	public void setSelectedKey(String key) {
		keyField.setValue(key);
		requireRedraw();
	}
	
	/**
	 * Set the text being displayed. Please notice that this is not 
	 * changing the selected Key itself, only what is displayed in the box.
	 * @return
	 */
	public void setText(String textValue) {
		textField.setValue(textValue);
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.controls.combo.JsonResourceControl#handleJSONResponse(javax.servlet.http.HttpServletRequest, org.json.JSONWriter)
	 */
	@Override
	public void handleJSONResponse(HttpServletRequest req, JSONWriter out) throws JSONException {

		FilteredRange range = new FilteredRange();
		range.setMax(comboBehavior.getMaxFetchRows());
		
		String action = req.getParameter("action");
		if (action == null) {
			action = "load";
		}
		
		range.setFilter(req.getParameter("filter"));
		
		out.object();
		out.key("controlId").value(getControlID());
		
		out.key("data");
		out.array();
		
		if ("load".equals(action)) {
			if (contentProvider != null) {
				for (Iterator<A> it = contentProvider.getContentIterator(range); it.hasNext(); ) {
					A obj = it.next();
					out.object();
					out.key("key").value(contentProvider.getUniqueKey(obj));
					if (baseLabelProvider != null) {
						DataLabel label = baseLabelProvider.getBaseLabel(obj);
						out.key("title").value(label.text);
						if (label.image != null) {
							out.key("image");
							out.object();
							out.key("path");
							out.value(label.image.getPath());
							out.key("imgTag");
							out.value(label.image.toImgTag());
							out.key("width");
							out.value(label.image.getWidth());
							out.key("height");
							out.value(label.image.getHeight());
							out.endObject();

						}
					}
					if (contentProvider.hasChildren(obj)) {
						out.key("children").value(Boolean.TRUE);
					}
					if (comboBehavior.isTransferFullObject() && objectSerializer != null) {
						out.key("object");
						objectSerializer.serialize(obj, out);
					}
					out.endObject();
				}
			}
		}
		
		out.endArray();
		out.endObject();
	}

	/**
	 * @return the emptyInfoText
	 */
	public String getEmptyInfoText() {
		return emptyInfoText;
	}
	
	/**
	 * Set a text that is displayed when the text field is empty. If empty, the
	 * css class 'empty' is added to indicate visually that the field is empty.
	 * @param emptyInfoText the emptyInfoText to set
	 */
	public void setEmptyInfoText(String emptyInfoText) {
		this.emptyInfoText = emptyInfoText;
	}
	/**
	 * @return the flagAsError
	 */
	public boolean isFlagAsError() {
		return flagAsError;
	}
	/**
	 * @param flagAsError the flagAsError to set
	 */
	public void setFlagAsError(boolean flagAsError) {
		if (this.flagAsError != flagAsError) {
			this.flagAsError = flagAsError;
			requireRedraw();
		}
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		requireRedraw();
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		if (this.width != width) {
			this.width = width;
			requireRedraw();
		}
	}

	/**
	 * @return the cssClass
	 */
	public String getCssClass() {
		return cssClass;
	}

	/**
	 * @param cssClass the cssClass to set
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
		requireRedraw();
	}

	/**
	 * @return the contentProvider
	 */
	public IContentProvider<?> getContentProvider() {
		return contentProvider;
	}

	/**
	 * @param contentProvider the contentProvider to set
	 */
	public void setContentProvider(IContentProvider<A> contentProvider) {
		this.contentProvider = contentProvider;
	}

	/**
	 * @return the objectSerializer
	 */
	public IObjectToJsonSerializer getObjectSerializer() {
		return objectSerializer;
	}
	/**
	 * @param objectSerializer the objectSerializer to set
	 */
	public void setObjectSerializer(IObjectToJsonSerializer objectSerializer) {
		this.objectSerializer = objectSerializer;
	}
	/**
	 * @return the changeNotification
	 */
	public boolean isChangeNotification() {
		return changeNotification;
	}
	/**
	 * @param changeNotification the changeNotification to set
	 */
	public void setChangeNotification(boolean changeNotification) {
		this.changeNotification = changeNotification;
	}

	/**
	 * Returns the internal combo box behavior. Only modify if you really know what you are doing!
	 * @return the comboBehavior
	 */
	public ComboBehavior getComboBehavior() {
		return comboBehavior;
	}

	/**
	 * @return the defaultImage
	 */
	public ImageRef getDefaultImage() {
		return defaultImage;
	}

	/**
	 * Set the image that should be rendered if no image is defined on the element.
	 * If the value is null, the image is not rendered at all. 
	 * This can be set to an empty image to prevent missing indents. Sample:
	 * <code>setDefaultImage(new ImageRef("/jwic/gfx/clear.gif", 16, 16));</code>
	 * @param defaultImage the defaultImage to set
	 */
	public void setDefaultImage(ImageRef defaultImage) {
		this.defaultImage = defaultImage;
	}

	/**
	 * @return the multiSelect
	 */
	public boolean isMultiSelect() {
		return multiSelect;
	}

	/**
	 * @param multiSelect the multiSelect to set
	 */
	public void setMultiSelect(boolean multiSelect) {
		this.multiSelect = multiSelect;
	}

	/**
	 * @return the baseLabelProvider
	 */
	public IBaseLabelProvider<A> getBaseLabelProvider() {
		return baseLabelProvider;
	}

	/**
	 * @param baseLabelProvider the baseLabelProvider to set
	 */
	public void setBaseLabelProvider(IBaseLabelProvider<A> baseLabelProvider) {
		this.baseLabelProvider = baseLabelProvider;
	}

}
