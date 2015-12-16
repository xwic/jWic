package de.jwic.mobile.demos;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.common.togglable.ToggleableGroup;
import de.jwic.controls.Label;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.mobile.MobileDemoModule;
import de.jwic.mobile.controls.MButton;
import de.jwic.mobile.controls.MFieldSetLayout;
import de.jwic.mobile.controls.MRadioButton;

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
		final MRadioButton radio = new MRadioButton(container, "radio");
		radio.setText("Click The Radio!");

		new Label(container,"label").setText("Radio Group");

		final MFieldSetLayout fieldSet = new MFieldSetLayout(container, "fieldSet");

		final MRadioButton radio1 = new MRadioButton(fieldSet, "radio1");
		radio1.setText("Yes");
		final MRadioButton radio2 = new MRadioButton(fieldSet, "radio2");
		radio2.setText("No");
		final MRadioButton radio3 = new MRadioButton(fieldSet, "radio3");
		radio3.setText("Maybe");

		final ToggleableGroup of = ToggleableGroup.unique(radio1, radio2, radio3);


		final MButton toggleGroup = new MButton(container, "toggleGroup");
		toggleGroup.setText("Cycle up");
		toggleGroup.addSelectionListener(new SelectionListener() {

			@Override
			public void objectSelected(SelectionEvent event) {
				of.setToggled(false);
			}
		});
		final MButton toggleGroup2 = new MButton(container, "toggleGroup2");
		toggleGroup2.setText("Cycle down");
		toggleGroup2.addSelectionListener(new SelectionListener() {

			@Override
			public void objectSelected(SelectionEvent event) {
				of.setToggled(true);
			}
		});

		return container;
	}
}
