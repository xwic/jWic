package de.jwic.mobile.demos;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Label;
import de.jwic.controls.mobile.IconPos;
import de.jwic.controls.mobile.MButton;
import de.jwic.controls.mobile.MRadioButton;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.mobile.MobileDemoModule;

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


		Label lbInfo = new Label(container, "lbInfo");
		lbInfo.setText("Single Radio-Buttons:");
		
		final MRadioButton radio1 = new MRadioButton(container, "radio1");
		radio1.setTitle("Option A");
		radio1.setMini(true);
		
		new Label(container).setText("Bla");

		MRadioButton radio2 = new MRadioButton(container, "radio2", radio1);
		radio2.setTitle("Option B");
		radio2.setIconpos(IconPos.RIGHT);
		
		
		MButton btToggle = new MButton(container, "btToggle");
		btToggle.setTitle("Enable/Disable A");
		btToggle.addSelectionListener(new SelectionListener() {
			@Override
			public void objectSelected(SelectionEvent event) {
				radio1.setEnabled(!radio1.isEnabled());
			}
		});
		
		return container;
	}
}
