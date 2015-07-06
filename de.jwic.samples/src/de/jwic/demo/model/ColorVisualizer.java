/**
 * 
 */
package de.jwic.demo.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;

/**
 * @author lippisch
 *
 */
public class ColorVisualizer extends Control {

	private ColorModel model;

	/**
	 * @param container
	 * @param name
	 */
	public ColorVisualizer(IControlContainer container, String name, ColorModel model) {
		super(container, name);
		this.model = model;
		
		model.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				onColorChange();
			}
		});
		
	}

	/**
	 * 
	 */
	protected void onColorChange() {
		requireRedraw();
	}

	/**
	 * Return the color.
	 * @return
	 */
	public String getHtmlColor() {
		return model.getHtmlColor();
	}
	
}
