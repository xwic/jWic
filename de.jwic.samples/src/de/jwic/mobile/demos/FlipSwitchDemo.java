package de.jwic.mobile.demos;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Label;
import de.jwic.controls.mobile.MButton;
import de.jwic.controls.mobile.MFlipSwitch;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;
import de.jwic.mobile.MobileDemoModule;

/**
 * Created by boogie on 10/30/14.
 */
public class FlipSwitchDemo extends MobileDemoModule{

	private Label label;

	public FlipSwitchDemo() {
		super("FlipSwitch Demo");
	}

	@Override
	public Control createPage(IControlContainer controlContainer) {
		final ControlContainer container = new ControlContainer(controlContainer);


		final MFlipSwitch checkBox = new MFlipSwitch(container, "checkBox");
		checkBox.addValueChangedListener(new ValueChangedListener() {
			
			@Override
			public void valueChanged(ValueChangedEvent event) {
				final boolean state = checkBox.isChecked();
				label.setText("FlipSwitch is " + (state ? "checked" : "not checked"));
			}
		});

		label = new Label(container, "label");
		label.setText("");

		
		final MButton toggleFromJava = new MButton(container, "toggleFromJava");
		toggleFromJava.setTitle("Toggle From Java");
		toggleFromJava.addSelectionListener(new SelectionListener() {
			
			@Override
			public void objectSelected(SelectionEvent event) {
				System.out.println("Toggle?");
				checkBox.setChecked(!checkBox.isChecked());
			}
		});

		/*
		final Label label2 = new Label(container, "label2");
		label2.setText("A Flip Switch");
		new MFlipSwitch(container, "flipSwitch");
		*/

		return container;
	}
}
