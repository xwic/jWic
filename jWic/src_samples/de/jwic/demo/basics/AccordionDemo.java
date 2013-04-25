package de.jwic.demo.basics;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.ecolib.accordion.Accordion;
import de.jwic.ecolib.accordion.Panel;

/**
 * Accordion Control Demo
 * @author dotto
 *
 */
public class AccordionDemo extends ControlContainer {


	public AccordionDemo(IControlContainer container, String name) {
		super(container, name);
		
		createControls();
	}

	private void createControls() {
		Accordion accordion = new Accordion(this, "accordion");
		Panel p1 = accordion.createPanel("Selection 1");
		new ButtonDemo(p1);
		
		Panel p2 = accordion.createPanel("Selection 2");
		new LabelDemo(p2);
		Panel p3 = accordion.createPanel("Selection 3");
		new ToolBarDemo(p3, null);
		
	}

}
