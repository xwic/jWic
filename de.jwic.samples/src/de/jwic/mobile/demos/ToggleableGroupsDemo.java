package de.jwic.mobile.demos;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Label;
import de.jwic.mobile.MobileDemoModule;
import de.jwic.common.enableable.EnabledToggleable;
import de.jwic.common.togglable.NegativeToggleable;
import de.jwic.common.togglable.ToggleableGroup;
import de.jwic.common.visible.VisibilityToggleable;
import de.jwic.mobile.controls.MCheckBox;
import de.jwic.mobile.controls.MFieldSetLayout;
import de.jwic.mobile.controls.MRadioButton;

/**
 * Created by boogie on 11/4/14.
 */
public class ToggleableGroupsDemo extends MobileDemoModule{

	public ToggleableGroupsDemo() {
		super("Toggleable Groups Demo");
	}

	@Override
	public Control createPage(IControlContainer controlContainer) {
		final ControlContainer container = new ControlContainer(controlContainer, "toggleableGroupsContainer");
		//ui controls setup
		final Label label = new Label(container, "label");
		label.setText("Toggling the checkbox will control the group of other checkboxes to be shown or hidden");

		final MCheckBox firstMCheckBox = new MCheckBox(container, "firstCheckBox");
		firstMCheckBox.setToggled(false);
		firstMCheckBox.setText("Hide/Show The next group");

		final MFieldSetLayout checkBoxGroup = new MFieldSetLayout(container, "checkBoxGroup");
		final Label infoForCheckboxGroup = new Label(checkBoxGroup, "infoLabel");
		infoForCheckboxGroup.setText("Both Checkboxes need to be check to enabled the radios");
		final MCheckBox checkbox1 = new MCheckBox(checkBoxGroup, "checkbox1");
		checkbox1.setText("Check Me!");
		checkbox1.setToggled(true);
		final MCheckBox checkbox2 = new MCheckBox(checkBoxGroup, "checkbox2");
		checkbox2.setText("Check Me too!");

		final MFieldSetLayout radioGroup2 = new MFieldSetLayout(container, "radioGroup2");
		final MRadioButton radio1 = new MRadioButton(radioGroup2, "radio1");
		radio1.setText("Enable me!");
		final MRadioButton radio2 = new MRadioButton(radioGroup2, "radio2");
		radio2.setText("Or Enable me!");


		//groups setup
		//this can be as complex or as simple as you want

		//the VisibilityToggleable toggles by showing or hiding a Visible element (in this case a fieldSet)
		final VisibilityToggleable second = new VisibilityToggleable(checkBoxGroup);
		//we setup the visibility toggler and the first checkbox to be in sync
		//so that when one is active, the other one is not
		//since visibility is something that's not controllable from the UI
		//this basically means that when the checkbox is checked the the fieldSet is hidden
		ToggleableGroup.alternating(firstMCheckBox, second);


		//next we setup the radio group to be enabled or disabled based on the state of the two checkboxes
		//we want the radios to be enabled when both the checkboxes are checked and disabled otherwise
		//so we create an enabledToggler that enables/disables elements when toggled
		final EnabledToggleable enabledToggleable = new EnabledToggleable(radio1, radio2);
		//we also want a NegativeToggler in order to flip the toggle
		//if the state is set to true we want this toggler to be disabled
		//and if the state is false the the toggled should be enabled
		final NegativeToggleable negativeToggleable = new NegativeToggleable(enabledToggleable);

		//we setup a multi group for the 2 checkboxes
		//this group is also togglable and the isToggled() method will return true if all the elements in it
		//are toggled
		final ToggleableGroup groupOfCheckBoxes = ToggleableGroup.multiple(checkbox1, checkbox2);

		//lastly we need to group the groupOfCheckboxes and the radio container.
		//this we produce the effect that we want
		//so that when the checkboxes are both checked the radios will be enabled
		ToggleableGroup.alternating(groupOfCheckBoxes, negativeToggleable);

		//this is just so that the radios behave like radios
		//and allow only one of them to be selected at a time
		//in truth this can be anything (not necessarily a group)
		//but its good to know that this is also an option
		ToggleableGroup.unique(radio1, radio2);


		return container;
	}
}
