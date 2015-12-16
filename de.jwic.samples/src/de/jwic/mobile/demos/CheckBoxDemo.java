package de.jwic.mobile.demos;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Label;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.mobile.MobileDemoModule;
import de.jwic.mobile.controls.MButton;
import de.jwic.mobile.controls.MCheckBox;
import de.jwic.mobile.controls.MFlipSwitch;

/**
 * Created by boogie on 10/30/14.
 */
public class CheckBoxDemo extends MobileDemoModule{

	public CheckBoxDemo() {
		super("CheckBox Demo");
	}

	@Override
	public Control createPage(IControlContainer controlContainer) {
		final ControlContainer container = new ControlContainer(controlContainer);

		final Label label = new Label(container, "label");
		label.setText("CheckBox is not checked");

		final MCheckBox MCheckBox = new MCheckBox(container, "checkBox");
		MCheckBox.setText("Toggle Me!");
		MCheckBox.addSelectionListener(new SelectionListener() {

			@Override
			public void objectSelected(SelectionEvent event) {
				final boolean state = MCheckBox.isToggled();
				label.setText("CheckBox is " + (state ? "checked" : "not checked"));
			}
		});

		final MButton toggleFromJava = new MButton(container, "toggleFromJava");
		toggleFromJava.setText("Toggle From Java");
		toggleFromJava.addSelectionListener(new SelectionListener() {


			@Override
			public void objectSelected(SelectionEvent event) {
				MCheckBox.toggle();
			}
		});

		final Label label2 = new Label(container, "label2");
		label2.setText("A Flip Switch");
		new MFlipSwitch(container, "flipSwitch");


		return container;
	}
}
