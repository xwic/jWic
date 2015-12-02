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
package de.jwic.samples.sample3;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.InputBox;
import de.jwic.controls.LabelControl;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.util.SerObservable;
import de.jwic.util.SerObserver;

/**
 * @author Florian Lippisch
 * @version $Revision: 1.4 $
 */
public class TemperatureGUI extends ControlContainer implements SerObserver {

	private TemperatureModel model = null;
	private InputBox display = null;
	
	private ValueStrategy strategy = null;
	
	/**
	 * Construct a new TemperatureGUI.
	 * @param model
	 * @param strategy
	 */
	public TemperatureGUI(IControlContainer container, TemperatureModel model, ValueStrategy strategy) {
		super(container);
		this.model = model;
		this.strategy = strategy;

		LabelControl label = new LabelControl(this, "label");
		label.setText("Temperature (" + strategy.getName() + ")");
		
		display = new InputBox(this, "display");
		display.setEnabled(false);
		
		Button btInc = new Button(this, "btInc");
		btInc.setTitle("Increase");
		btInc.addSelectionListener(new IncreaseController());
		
		Button btDecr = new Button(this, "btDecr");
		btDecr.setTitle("Decrease");
		btDecr.addSelectionListener(new DecreaseController());
		
		model.addObserver(this);
		
		updateDisplay();
		
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(SerObservable o, Object arg) {
		updateDisplay();

	}

	/**
	 * Update the display field with the current value of the model.
	 */
	private void updateDisplay() {
		
		display.setText(strategy.getFormated());
		
	}

	/**
	 * @return Returns the model.
	 */
	public TemperatureModel getModel() {
		return model;
	}
	/**
	 * @param model The model to set.
	 */
	public void setModel(TemperatureModel model) {
		if (this.model != null) {
			this.model.deleteObserver(this);
		}
		this.model = model;
		this.model.addObserver(this);
	}
	
	/**
	 * Controller that increases the temperature.
	 * @author Florian Lippisch
	 * @version $Revision: 1.4 $
	 */
	class IncreaseController implements SelectionListener {
		public void objectSelected(SelectionEvent event) {
			strategy.increase();
		}
	}

	/**
	 * Controller that increases the temperature.
	 * @author Florian Lippisch
	 * @version $Revision: 1.4 $
	 */
	class DecreaseController implements SelectionListener {
		public void objectSelected(SelectionEvent event) {
			strategy.decrease();
		}
	}

	
}
