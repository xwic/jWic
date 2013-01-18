/**
 * 
 */
package de.jwic.ecolib.minichart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.imageio.IIOImage;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.imageio.plugins.png.PNGImageWriter;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.IResourceControl;
import de.jwic.controls.ImageControl;

/**
 * @author Florian Lippisch
 *
 */
public class MiniChart extends Control implements IResourceControl {
	private static final long serialVersionUID = 1L;
	private IChartDataProvider dataProvider = null;
	private int width = 48;
	private int height = 16;
	private int maxValues = 12;
	
	private MiniChartStyle chartStyle = new MiniChartStyle();
	
	/**
	 * @param container
	 * @param name
	 */
	public MiniChart(IControlContainer container, String name) {
		super(container, name);
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IResourceControl#attachResource(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void attachResource(HttpServletRequest req, HttpServletResponse res) throws IOException {

		String options = req.getParameter("options");
		
		res.setHeader ("Content-Disposition","filename=" + getFilename());
		res.setHeader ("Expires", "0");
		res.setHeader ("Cache-Control", "no-cache");
		res.setHeader ("Pragma", "no-cache");
		res.setContentType(ImageControl.MIME_TYPE_PNG);

		// render image
		ByteArrayOutputStream imageOutputStream  = new ByteArrayOutputStream();
		renderChart(options, imageOutputStream);
		
		imageOutputStream.writeTo(res.getOutputStream());
		
		res.getOutputStream().close();
	}

	/**
	 * @param options
	 * @param imageOutputStream
	 */
	private void renderChart(String options, ByteArrayOutputStream imageOutputStream) {
	
		BufferedImage bi = new BufferedImage(width, height, 
				chartStyle.isTransparentBackground() ? 
						BufferedImage.TYPE_INT_ARGB :
						BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g2d = bi.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setBackground(chartStyle.getBackgroundColor());
		if (!chartStyle.isTransparentBackground()) {
			g2d.clearRect(0, 0, width, height);
		}

		Color fgColor = chartStyle.getDefaultFgColor();
		g2d.setColor(fgColor);
		g2d.setBackground(fgColor);
		g2d.setStroke(new BasicStroke(1));

		if (dataProvider != null) {
			Number[] values = dataProvider.getChartValues(options, maxValues);
			
			if (values != null && values.length > 0) {
				// evaluate max and min values
				double min = values[0].doubleValue();
				double max = values[0].doubleValue();
				
				for (int i = 0; i < values.length; i++) {
					min = Math.min(min, values[i].doubleValue());
					max = Math.max(max, values[i].doubleValue());
				}
				
				if (chartStyle.isUseFixedBaseValue()) {
					if (min > 0) {
						min = 0;
					}
				}
				
				double diff = max - min;
				
				// draw values
				double factor = diff / ((double)height);
				int baseLine = height - ((int)((-min) / factor));
				int barWidth = (width - values.length + 1) / values.length;

				if (chartStyle.isDrawBaseLine()) {
					g2d.setColor(chartStyle.getBaseLineColor());
					g2d.drawLine(0, baseLine - 1, width, baseLine - 1);
				}
				
				int x = 0;
				for (int i = 0; i < values.length; i++) {
					
					int barHeight = (int)(values[i].doubleValue() / factor);
					if (barHeight >= 0) {
						int y = baseLine - barHeight;
						g2d.setColor(chartStyle.getDefaultFgColor());
						g2d.fillRect(x, y, barWidth, barHeight);
					} else {
						int y = baseLine;
						g2d.setColor(chartStyle.getNegativeFgColor());
						g2d.fillRect(x, y, barWidth, -barHeight);
					}
						
					x += barWidth + 1;
				}
				
				
			} else {			
				g2d.drawLine(0, height, width, height); // just draw baseline
			}
			
		}
	
		ImageWriter imageWriter = new PNGImageWriter(null);
		ImageWriteParam param = imageWriter.getDefaultWriteParam();
		imageWriter.setOutput(new MemoryCacheImageOutputStream(imageOutputStream));
		try {
			imageWriter.write(null, new IIOImage(bi, null, null), param);
		} catch (IOException e) {
			log.error("Error rendering image!");
		}
		imageWriter.dispose();

		
	}
	
	/**
	 * Returns the image URL.
	 * @return
	 */
	public String getImageURL() {
		return getSessionContext().getCallBackURL() + "&_resreq=1&controlId=" + getControlID();
	}

	/**
	 * Returns the image URL.
	 * @return
	 */
	public String getImageURL(String options) {
		if (options != null) {
			try {
				return getImageURL() + "&options=" + URLEncoder.encode(options, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				log.error("Error encoding options: " + e, e);
				return getImageURL();
			}
		} else {
			return getImageURL();
		}
	}

	/**
	 * @return
	 */
	private String getFilename() {
		return "minichart.png";
	}

	/**
	 * @return the dataProvider
	 */
	public IChartDataProvider getDataProvider() {
		return dataProvider;
	}

	/**
	 * @param dataProvider the dataProvider to set
	 */
	public void setDataProvider(IChartDataProvider dataProvider) {
		this.dataProvider = dataProvider;
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
	 * @return the maxValues
	 */
	public int getMaxValues() {
		return maxValues;
	}

	/**
	 * @param maxValues the maxValues to set
	 */
	public void setMaxValues(int maxValues) {
		this.maxValues = maxValues;
	}

	/**
	 * @return the chartStyle
	 */
	public MiniChartStyle getChartStyle() {
		return chartStyle;
	}

	/**
	 * @param chartStyle the chartStyle to set
	 */
	public void setChartStyle(MiniChartStyle chartStyle) {
		this.chartStyle = chartStyle;
	}

}
