package de.jwic.mobile.demos;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Label;
import de.jwic.mobile.MobileDemoModule;
import de.jwic.mobile.common.blurable.BlurListener;
import de.jwic.mobile.common.blurable.Blurable;
import de.jwic.mobile.controls.TextArea;
import de.jwic.mobile.controls.TextInput;

/**
 * Created by boogie on 10/29/14.
 */
public class InputDemo extends MobileDemoModule{

	public InputDemo() {
		super("Input Demo");
	}

	@Override
	public Control createPage(IControlContainer controlContainer) {
		ControlContainer container = new ControlContainer(controlContainer);
		final Label labelForTextInput = new Label(container, "labelForTextInput");
		labelForTextInput.setText("Basic Text Input");
		final TextInput textInput = new TextInput(container, "textInput");

		final Label labelForTextInputWithUpdateOnBlur = new Label(container, "labelForTextInputWithUpdateOnBlur");
		labelForTextInputWithUpdateOnBlur.setText("Text Input With Update on blur");
		final Label labelForTextInputWithUpdateOnBlur2 = new Label(container, "labelForTextInputWithUpdateOnBlur2");

		final TextInput textInputWithUpdateOnBlur = new TextInput(container, "textInputWithUpdateOnBlur");
		textInputWithUpdateOnBlur.setUpdateOnBlur(true);
		textInputWithUpdateOnBlur.addBlurListener(new BlurListener() {

			@Override
			public void onBlur(Blurable eventSource) {
				labelForTextInputWithUpdateOnBlur2.setText(textInputWithUpdateOnBlur.getText());
			}
		});

		final Label labelForTextArea = new Label(container, "labelForTextArea");
		labelForTextArea.setText("Multline TextArea");
		final TextArea multLineTextArea = new TextArea(container, "multLineTextArea");


		return container;
	}
}
