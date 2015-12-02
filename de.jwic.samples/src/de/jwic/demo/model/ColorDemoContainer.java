/**
 * 
 */
package de.jwic.demo.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.InputBox;

/**
 * @author lippisch
 *
 */
public class ColorDemoContainer extends ControlContainer {

	private ColorModel model;
	private InputBox inpHtmlColor;

	/**
	 * @param container
	 */
	public ColorDemoContainer(IControlContainer container) {
		super(container);

		
		model = new ColorModel();
		
		inpHtmlColor = new InputBox(this, "inpHtmlColor");
		inpHtmlColor.setText(model.getHtmlColor());;
		inpHtmlColor.setReadonly(true);
		
		new ColorChanger(this, "chRed", ColorModel.ColorElm.RED, model);
		new ColorChanger(this, "chGreen", ColorModel.ColorElm.GREEN, model);
		new ColorChanger(this, "chBlue", ColorModel.ColorElm.BLUE, model);
	
		new ColorVisualizer(this, "visualizer", model);
		
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
		inpHtmlColor.setText(model.getHtmlColor());
		
	}


}
