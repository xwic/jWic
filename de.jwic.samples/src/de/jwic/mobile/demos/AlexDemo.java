package de.jwic.mobile.demos;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.mobile.MobileDemoModule;
import de.jwic.common.togglable.ToggleableGroup;
import de.jwic.common.visible.VisibilityToggleable;
import de.jwic.mobile.controls.MCheckBox;
import de.jwic.mobile.controls.MFieldSetLayout;
import de.jwic.mobile.controls.MInputBox;

/**
 * Created by boogie on 11/4/14.
 */
public class AlexDemo extends MobileDemoModule {

	public AlexDemo() {
		super("Alex Demo");
	}

	@Override
	public Control createPage(IControlContainer controlContainer) {
		final ControlContainer container = new ControlContainer(controlContainer);

		final MCheckBox showHide = new MCheckBox(container, "checkbox");
		showHide.setText("Show/Hide");

		final MFieldSetLayout yesNoFieldSet = new MFieldSetLayout(container, "yesNoFieldSet");


		final MCheckBox yes = new MCheckBox(yesNoFieldSet, "yes");
		yes.setText("Yes");
		final MCheckBox no = new MCheckBox(yesNoFieldSet, "no");
		no.setText("No");

		final MInputBox input = new MInputBox(container, "input");

		final VisibilityToggleable visibilityToggleable = new VisibilityToggleable(yesNoFieldSet);
		ToggleableGroup.alternating(showHide, visibilityToggleable);

		final ToggleableGroup yesNoGroup = ToggleableGroup.unique(yes, no);
		final VisibilityToggleable inputVisibilityToggler = new VisibilityToggleable(input);
		ToggleableGroup.alternating(yesNoGroup, inputVisibilityToggler);

		return container;
	}
}
