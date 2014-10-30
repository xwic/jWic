package de.jwic.mobile.demos;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Label;
import de.jwic.mobile.MobileDemoModule;
import de.jwic.mobile.common.togglable.ToggleableGroup;
import de.jwic.mobile.controls.FieldSetLayout;
import de.jwic.mobile.controls.RadioButton;

/**
 * Created by boogie on 10/30/14.
 */
public class RadioButtonDemo extends MobileDemoModule{

	public RadioButtonDemo() {
		super("Radio Demo");
	}

	@Override
	public Control createPage(IControlContainer controlContainer) {
		final ControlContainer container = new ControlContainer(controlContainer);
		final RadioButton radio = new RadioButton(container, "radio");
		radio.setText("Click The Radio!");

		new Label(container,"label").setText("Radio Group");

		final FieldSetLayout fieldSet = new FieldSetLayout(container, "fieldSet");

		final RadioButton radio1 = new RadioButton(fieldSet, "radio1");
		radio1.setText("Yes");
		final RadioButton radio2 = new RadioButton(fieldSet, "radio2");
		radio2.setText("No");
		final RadioButton radio3 = new RadioButton(fieldSet, "radio3");
		radio3.setText("Maybe");

		ToggleableGroup.of(radio1, radio2, radio3);


		return container;
	}
}
