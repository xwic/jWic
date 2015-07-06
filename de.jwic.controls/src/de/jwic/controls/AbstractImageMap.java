/*******************************************************************************
 * Copyright 2015 xWic group (http://www.xwic.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *  
 *******************************************************************************/
package de.jwic.controls;

/**
 * @author Bornemann
 *
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.IResourceControl;

/**
 * Default IImageControl implementation that support linked map.
 * ImageControl.vtl uses jWic ajax update listener to replace the image without flicker.
 * 
 * IE 6 has a bug when working with img tag using a map:
 * An update to the map without updating its img also crashes the IE6!
 * 
 * @author jbornema
 *
 */
public abstract class AbstractImageMap extends ControlContainer implements IResourceControl {
	private static final long serialVersionUID = 1L;
	/**
	 * Shap represents a html area for maps. Supported types are
	 * SHAPE_TYPE_RECT, SHAPE_TYPE_CIRCLE and SHAPE_TYPE_POLY.
	 * Coords are comma separated, e.g. "x,y,r" for circles.
	 */
	public class Shape implements Serializable {
		private static final long serialVersionUID = 1L;
		String type = null;
		String coords = null;
		String url = null;
		String title = null;
		Shape(String type, String coords, String title, String url) {
			this.type = type;
			this.coords = coords;
			this.url = url;
			this.title = title;
		}
		public String getType() {
			return type;
		}
		public String getCoords() {
			return coords;
		}
		public String getURL() {
			return url;
		}
		public String getTitle() {
			return title;
		}
	};
	
	public final static String MIME_TYPE_PNG = "image/png";
	public final static String MIME_TYPE_JPG = "image/jpg";
	public final static String MIME_TYPE_GIF = "image/gif";

	public final static String SHAPE_TYPE_RECT = "rect";
	public final static String SHAPE_TYPE_CIRCLE = "circle";
	public final static String SHAPE_TYPE_POLY = "poly";

	protected List<Shape> shapes;
	private transient ByteArrayOutputStream imageOutputStream;
	protected String mimeType;
	protected int width;
	protected int height;
	protected String infoMessage;
	protected boolean createUniqueURL;
	protected boolean hidden;
	
	/**
	 * @param container
	 * @param name
	 */
	public AbstractImageMap(IControlContainer container, String name) {
		super(container, name);
		
		// init variables
		shapes = null;
		mimeType = MIME_TYPE_JPG;
		width = -1;
		height = -1;
		infoMessage = null;
		createUniqueURL = false;
		hidden = false;
		
		// set default template
		setTemplateName(AbstractImageMap.class.getName());
	}

	/**
	 * The filename is used in the servlet response and in
	 * the html rendering.
	 * @return The filename for the created image.
	 */
	public abstract String getFilename();

	/**
	 * Return the ByteArrayOutputStream renderImage() must write the binary
	 * image data into. Should call reset() to it before creating the image
	 * data again.
	 * @return
	 */
	public ByteArrayOutputStream getImageOutputStream() {
		if (imageOutputStream == null) {
			imageOutputStream  = new ByteArrayOutputStream();
		}
		return imageOutputStream;
	}
	/**
	 * Renders the image data to imageOutputStream.
	 * Don't forget to set the appropiate mime type and call
	 * ByteArrayOutputStream.reset() before generating the image data again. 
	 * @throws IOException
	 */
	public abstract void renderImage() throws IOException;
	
	/**
	 * Renders the image data to the writer.
	 * @param writer
	 * @return Mime encoding type like "image/jpg" or "image/gif"
	 * @throws IOException
	 */
	public String renderImage(OutputStream out) throws IOException {
		ByteArrayOutputStream data = getImageOutputStream();
		if (imageOutputStream.size() == 0) {
			// ups, no output? renderImage() not called?
			renderImage();
		}
		data.writeTo(out);
		return mimeType;
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IResourceControl#attachResource(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void attachResource(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String type = renderImage(res.getOutputStream());
		res.setContentType(type);
		res.setHeader ("Content-Disposition","filename=" + getFilename());
		/*res.setHeader("Expires", "0");
		res.setHeader ("Cache-Control", "no-cache");
		res.setHeader ("Pragma", "no-cache");*/
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IControl#actionPerformed(java.lang.String, java.lang.String)
	 */
	public void actionPerformed(String actionId, String parameter) {
		// no action implemented
	}
	
	/**
	 * Return Collection of Shape objects that had been added with addShape(...).
	 * @return
	 */
	public List<Shape> getShapes() {
		if (shapes == null) {
			shapes = new ArrayList<Shape>();
		}
		return shapes;
	}
	
	/**
	 * Adds a shape of type SHAPE_TYPE_RECT, ..._CIRLCE or ..._POLY with url and title.
	 * If url is null no "clickable" link is added.
	 * Depending on the shape type the coords follow different syntax:
	 * SHAPE_TYPE_RECT:   "x1,y1,x2,y2"
	 * SHAPE_TYPE_CIRCLE: "x,y,r"
	 * SHAPE_TYPE_POLY:   "x1,y1[,x2,y2[,...]]"
	 * @param type
	 * @param coords
	 * @param url
	 * @param title
	 */
	public void addShape(String type, String coords, String title, String url) {
		// add new Shapes always at top, so last added Shape is rendered first
		getShapes().add(0, new Shape(type, coords, title, url));
	}
	
	/**
	 * Same as addShape(type, coords, title, null)
	 * @param type
	 * @param coords
	 * @param title
	 */
	public void addShape(String type, String coords, String title) {
		addShape(type, coords, title, null);
	}
	
	/**
	 * Return the mime type for this image, like MIME_TYPE_JPG.
	 * @return
	 */
	public String getMimeType() {
		return mimeType;
	}
	
	/**
	 * Set the mime type for this image, like MIME_TYPE_JPG.
	 * @param mimeType
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IControl#setRequireRedraw(boolean)
	 */
	public void setRequireRedraw(boolean requireRedraw) {
		super.setRequireRedraw(requireRedraw);
		if (requireRedraw) {
			// clear image data
			getImageOutputStream().reset();
		}
	}
	
	/**
	 * Return the image width in pixel.
	 * @return
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Set the image width in pixel.
	 * @param width
	 */
	public void setWidth(int width) {
		if (this.width != width) {
			setRequireRedraw(true);
		}
		this.width = width;
	}
	
	/**
	 * Return the image height in pixel.
	 * @return
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Set the image height in pixel.
	 * @param height
	 */
	public void setHeight(int height) {
		if (this.height != height) {
			setRequireRedraw(true);
		}
		this.height = height;
	}

	/**
	 * Create a URL to this image.
	 * Current milliseconds is part of that URL is createUniqueURL is set.
	 * @return
	 */
	public String createImageURL() {
		return getSessionContext().getCallBackURL() + "&_resreq=1&controlId=" + AbstractImageMap.this.getControlID() + (createUniqueURL ? "&time=" + System.currentTimeMillis() + "&" : "&") + getFilename();
	}
	
	/**
	 * Return the info message that is used for img title attribute and is
	 * also displayed in window.status.
	 * @return
	 */
	public String getInfoMessage() {
		return infoMessage;
	}
	
	/**
	 * Set the info message that is used for img title attribute and is
	 * also displayed in window.status.
	 * @param infoMessage
	 */
	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
	}
	
	/**
	 * Return true if on every createUniqueURL() a new URL is created.
	 * Default is false.
	 * @return
	 */
	public boolean isCreateUniqueURL() {
		return createUniqueURL;
	}
	
	/**
	 * Set if on every createUniqueURL() a new URL is created.
	 * Default is false.
	 * @param createUniqueURL
	 */
	public void setCreateUniqueURL(boolean createUniqueURL) {
		this.createUniqueURL = createUniqueURL;
	}

	/**
	 * Return true if control should be rendered with no content.
	 * Used to render the JavaScript if control should be invisible.
	 * Should be used instead of visible attribute.
	 * @return
	 */
	public boolean isHidden() {
		return hidden;
	}
	
	/**
	 * Set if control should be rendered with no content.
	 * Used to render the JavaScript if control should be invisible.
	 * Should be used instead of visible attribute.
	 * @param hidden
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
		setRequireRedraw(true);
	}
}