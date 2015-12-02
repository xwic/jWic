/**
 * 
 */
package de.jwic.demo.model;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.demo.model.ColorModel.ColorElm;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * Can change a color value.
 * @author lippisch
 */
public class ColorChanger extends ControlContainer {

	private ColorModel model;
	private ColorElm elm;

	/**
	 * @param container
	 * @param name
	 */
	public ColorChanger(IControlContainer container, String name, ColorModel.ColorElm elm, ColorModel model) {
		super(container, name);
		this.elm = elm;
		this.model = model;

		Button btPlus = new Button(this, "plus");
		btPlus.setTitle("+");
		btPlus.addSelectionListener(new SelectionListener() {
			
			@Override
			public void objectSelected(SelectionEvent event) {
				onPlus();
			}
		});

		Button btMinus = new Button(this, "minus");
		btMinus.setTitle("-");
		btMinus.addSelectionListener(new SelectionListener() {
			
			@Override
			public void objectSelected(SelectionEvent event) {
				onMinus();
			}
		});
		
	}

	/**
	 * Returns the name of the element this changer is changing.
	 * @return
	 */
	public String getElementName() {
		return elm.name();
	}
	
	/**
	 * 
	 */
	protected void onMinus() {
		
		model.changeColor(elm, -1);
		
	}

	/**
	 * 
	 */
	protected void onPlus() {
	
		model.changeColor(elm, 10);
		
	}

}
