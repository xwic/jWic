package de.jwic.mobile.demos;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Label;
import de.jwic.mobile.MobileDemoModule;
import de.jwic.mobile.common.clickable.ClickListener;
import de.jwic.mobile.common.clickable.Clickable;
import de.jwic.mobile.controls.Button;
import de.jwic.mobile.controls.CheckBox;
import de.jwic.mobile.controls.FlipSwitch;

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

		final CheckBox checkBox = new CheckBox(container, "checkBox");
		checkBox.setText("Toggle Me!");
		checkBox.addClickListener(new ClickListener() {

			@Override
			public void onClick(Clickable source) {
				final boolean state = checkBox.isToggled();
				label.setText("CheckBox is "+ (state ? "checked" : "not checked"));
			}
		});

		final Button toggleFromJava = new Button(container, "toggleFromJava");
		toggleFromJava.setText("Toggle From Java");
		toggleFromJava.addClickListener(new ClickListener() {

			@Override
			public void onClick(Clickable source) {
				checkBox.toggle();
			}
		});

		final Label label2 = new Label(container, "label2");
		label2.setText("A Flip Switch");
		new FlipSwitch(container, "flipSwitch");


		return container;
	}
}
