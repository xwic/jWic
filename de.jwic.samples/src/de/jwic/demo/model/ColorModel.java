/**
 * 
 */
package de.jwic.demo.model;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import de.jwic.demo.model.ColorModel.ColorElm;

/**
 * @author lippisch
 *
 */
public class ColorModel {

	private Color color = new Color(0, 0, 0);
	private PropertyChangeSupport chgSupport;
	
	public enum ColorElm {
		RED,
		GREEN,
		BLUE
	}
	
	/**
	 * 
	 */
	public ColorModel() {
		chgSupport = new PropertyChangeSupport(this);
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		Color oldColor = this.color;
		this.color = color;
		chgSupport.firePropertyChange("color", oldColor, this.color);
	}

	/**
	 * Returns the color as an HTML String.
	 * @return
	 */
	public String getHtmlColor() {
		String sRed = Integer.toHexString(color.getRed());
		String sGreen = Integer.toHexString(color.getGreen());
		String sBlue = Integer.toHexString(color.getBlue());
		
		return "#" + (sRed.length() == 1 ? "0" + sRed : sRed) +
				(sGreen.length() == 1 ? "0" + sGreen : sGreen) +
				(sBlue.length() == 1 ? "0" + sBlue : sBlue);
	}

	/**
	 * @param elm
	 * @param i
	 */
	public void changeColor(ColorElm elm, int change) {

		int value = 0;
		switch (elm) {
		case BLUE:
			value = color.getBlue();
			break;
		case GREEN:
			value = color.getGreen();
			break;
		case RED:
			value = color.getRed();
			break;
		}
		
		value += change;
		if (value < 0) {
			value = 0;
		}
		if (value > 255) {
			value = 255;
		}

		Color newColor;
		switch (elm) {
		case BLUE:
			newColor = new Color(color.getRed(), color.getGreen(), value);
			break;
		case GREEN:
			newColor = new Color(color.getRed(), value, color.getBlue());
			break;
		case RED:
			newColor = new Color(value, color.getGreen(), color.getBlue());
			break;
		default:
				newColor = color;
		}
		
		setColor(newColor);
		
	}

	
	
	/**
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		chgSupport.addPropertyChangeListener(listener);
	}

	/**
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		chgSupport.removePropertyChangeListener(listener);
	}
	
	
}
