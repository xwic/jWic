package de.jwic.mobile.demos;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Label;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.mobile.MobileDemoModule;
import de.jwic.mobile.controls.MButton;

/**
 * Created by boogie on 10/29/14.
 */
public final class ButtonDemo extends MobileDemoModule{

	public ButtonDemo() {
		super("Button Demo");
	}

	@Override
	public Control createPage(IControlContainer controlContainer) {
		final ControlContainer container = new ControlContainer(controlContainer, "buttonContainer");
		final Label label = new Label(container, "label");
		label.setText("Click the button");
		final MButton MButton = new MButton(container, "button");
		MButton.setText("Click me");
		MButton.addSelectionListener(new SelectionListener() {

			@Override
			public void objectSelected(SelectionEvent event) {
				label.setText("Thx for clicking the button");
			}
		});

		final MButton disabled = new MButton(container, "disabled");
		disabled.disable();
		disabled.setText("I'm Disabled :(");
		return container;
	}
}
