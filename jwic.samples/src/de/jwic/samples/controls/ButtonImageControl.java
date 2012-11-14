/*
 * Copyright 2005 jWic group (http://www.jwic.de)
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
 * de.jwic.samples.controls.ButtonImageControl.java
 * created on Dec 4, 2005, 12:34:10 PM
 * $Id: ButtonImageControl.java,v 1.2 2008/09/17 15:20:20 lordsam Exp $
 */
package de.jwic.samples.controls;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.MemoryCacheImageOutputStream;

import com.sun.imageio.plugins.png.PNGImageWriter;

import de.jwic.base.IControlContainer;
import de.jwic.controls.ImageControl;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * @author jbornema
 *
 */
public class ButtonImageControl extends ImageControl {

	protected String title;
	protected boolean enabled;
	protected List<SelectionListener> selectedListeners = null;
	
	/**
	 * @param container
	 * @param name
	 */
	public ButtonImageControl(IControlContainer container, String name) {
		super(container, name);
		
		title = name;
		enabled = true;
	}

	/* (non-Javadoc)
	 * @see de.jwic.controls.ImageControl#getFilename()
	 */
	public String getFilename() {
		return getName() + ".jpg";
	}

	/* (non-Javadoc)
	 * @see de.jwic.controls.ImageControl#renderImage()
	 */
	public void renderImage() throws IOException {
		if (getImageOutputStream().size() > 0 && !isRequireRedraw()) {
			// render only if needed
			return;
		}
		
		BufferedImage bi = ImageIO.read(getClass().getResource("ButtonImageControl.png"));
		//BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bi.createGraphics();		

		setWidth(bi.getWidth());
		setHeight(bi.getHeight());

		// set rendering properties
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// begin drawing
		// 
		
		g2d.setColor(enabled ? Color.BLACK : Color.GRAY);
		int x = 10;
		int y = 15;
		
		g2d.drawString(title, x, y);
		
		// drawing finished
		g2d.dispose();
		
		getImageOutputStream().reset();
		
		ImageWriter imageWriter = new PNGImageWriter(null);
		ImageWriteParam param = imageWriter.getDefaultWriteParam();
		imageWriter.setOutput(new MemoryCacheImageOutputStream(getImageOutputStream()));
		imageWriter.write(null, new IIOImage(bi, null, null), param);
		imageWriter.dispose();
		
		setMimeType(MIME_TYPE_PNG);
		
		// add actions
		getShapes().clear();
		if (enabled) {
			addShape(SHAPE_TYPE_RECT, "0,0," + getWidth() + "," + getHeight(), getTitle(), createActionURL("", ""));
		}
	}

	public void actionPerformed(String actionId, String parameter) {
		click();
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
		setRequireRedraw(true);
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enable) {
		this.enabled = enable;
		setRequireRedraw(true);
	}

	/**
	 * Register a listener that will be notified when the anchor will be clicked.
	 * @param listener
	 */
	public void addSelectionListener(SelectionListener listener) {
		if (selectedListeners == null) {
			selectedListeners = new ArrayList<SelectionListener>();
		}
		selectedListeners.add(listener);
	}
	
	/**
	 * Called when the button was clicked by the user. If there are SelectionListeners 
	 * registerd they are notified.
	 */
	public void click() {
		// notify the listeners
		if (selectedListeners != null) {
			SelectionEvent se = new SelectionEvent(this);
			for (Iterator<SelectionListener> it = selectedListeners.iterator(); it.hasNext(); ) {
				SelectionListener osl = it.next();
				osl.objectSelected(se);
			}
		}
	}
}
