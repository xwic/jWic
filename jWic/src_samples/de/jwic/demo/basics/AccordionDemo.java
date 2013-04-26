package de.jwic.demo.basics;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.accordion.Accordion;
import de.jwic.controls.accordion.Panel;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

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
		final Accordion accordion = new Accordion(this, "accordion");
		Panel p1 = accordion.createPanel("Selection 1");
		new ButtonDemo(p1);
		
		Panel p2 = accordion.createPanel("Selection 2");
		new LabelDemo(p2);
		Panel p3 = accordion.createPanel("Selection 3");
		new ToolBarDemo(p3, null);
		
		Button btNext = new Button(this, "button");
		btNext.addSelectionListener(new SelectionListener() {
			
			@Override
			public void objectSelected(SelectionEvent event) {
				int nextIndex = (accordion.getActiveIndex() +1) % 3;
				accordion.setActiveIndex(nextIndex);
			}
		});
		
	}

}
