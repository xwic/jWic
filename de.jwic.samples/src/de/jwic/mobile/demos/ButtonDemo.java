package de.jwic.mobile.demos;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Label;
import de.jwic.mobile.MobileDemoModule;
import de.jwic.mobile.common.clickable.ClickListener;
import de.jwic.mobile.common.clickable.Clickable;
import de.jwic.mobile.controls.Button;

/**
 * Created by boogie on 10/29/14.
 */
public final class ButtonDemo extends MobileDemoModule{

	public ButtonDemo() {
		super("Button Demo");
	}

	@Override
	public Control createPage(IControlContainer controlContainer) {
		ControlContainer container = new ControlContainer(controlContainer);
		final Label label = new Label(container, "label");
		label.setText("Click the button");
		final Button button = new Button(container, "button");
		button.setText("Click me");
		button.addClickListener(new ClickListener() {

			@Override
			public void onClick(Clickable source) {
				label.setText("Thx for clicking the button");
			}
		});
		return container;
	}
}
