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
 * de.jwic.controls.Tree
 * Created on Apr 16, 2010
 * $Id: Tree.java,v 1.1 2010/04/22 16:00:15 lordsam Exp $
 */
package de.jwic.controls;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONWriter;

import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.base.Range;
import de.jwic.data.IBaseLabelProvider;
import de.jwic.data.IContentProvider;
import de.jwic.data.DataLabel;
import de.jwic.json.JsonResourceControl;

/**
 * Displays a tree like structure in a scrollable container.
 * 
 * @author Lippisch
 */
@JavaScriptSupport
public class Tree<A> extends JsonResourceControl {
	private static final long serialVersionUID = 1L;
	private IContentProvider<A> contentProvider = null;
	private IBaseLabelProvider<A> labelProvider = null;
	
	private String cssClass = "j-tree";
	private boolean enabled = true;
	private int width = 250;
	private int height = 0; // auto
	
	// fields used to store scrolling position
	private Field fldTop;  
	private Field fldLeft;
	
	/**
	 * @param container
	 * @param name
	 */
	public Tree(IControlContainer container, String name) {
		super(container, name);

		fldTop = new Field(this, "top");
		fldLeft = new Field(this, "left");
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.json.JsonResourceControl#handleJSONResponse(javax.servlet.http.HttpServletRequest, org.json.JSONWriter)
	 */
	@Override
	public void handleJSONResponse(HttpServletRequest req, JSONWriter out) throws JSONException {

		Range range = new Range();
		
		out.object();
		out.key("controlId").value(getControlID());
		
		out.key("data");
		out.array();
		
		for (Iterator<A> it = contentProvider.getContentIterator(range); it.hasNext(); ) {
			A obj = it.next();
			out.object();
			out.key("key").value(contentProvider.getUniqueKey(obj));
			if (labelProvider != null) {
				DataLabel label = labelProvider.getBaseLabel(obj);
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
			out.endObject();

		}
		
		
		out.endArray();
		
		out.endObject();

		
	}

	/**
	 * @return the contentProvider
	 */
	public IContentProvider<A> getContentProvider() {
		return contentProvider;
	}

	/**
	 * @param contentProvider the contentProvider to set
	 */
	public void setContentProvider(IContentProvider<A> contentProvider) {
		this.contentProvider = contentProvider;
	}

	/**
	 * @return the labelProvider
	 */
	public IBaseLabelProvider<A> getLabelProvider() {
		return labelProvider;
	}

	/**
	 * @param labelProvider the labelProvider to set
	 */
	public void setLabelProvider(IBaseLabelProvider<A> labelProvider) {
		this.labelProvider = labelProvider;
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
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Returns the top scrolling position.
	 * @return
	 */
	public int getTop() {
		String s = fldTop.getValue();
		if (s == null || s.isEmpty()) {
			return 0;
		}
		return Integer.parseInt(fldTop.getValue());
	}

	/**
	 * Return the left scrolling position.
	 * @return
	 */
	public int getLeft() {
		String s = fldTop.getValue();
		if (s == null || s.isEmpty()) {
			return 0;
		}
		return Integer.parseInt(fldLeft.getValue());
	}
	
}
