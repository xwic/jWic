package de.jwic.mobile.demos;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Label;
import de.jwic.controls.InputBox.InputType;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;
import de.jwic.mobile.MobileDemoModule;
import de.jwic.mobile.controls.MInputBox;

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
		
		final MInputBox textInput = new MInputBox(container, "textInput");
		textInput.setText("Hi Lea");

		final Label lblEmail = new Label(container);
		lblEmail.setText("Email");
		
		final MInputBox textEmail = new MInputBox(container, "textEmail");
		textEmail.setType(InputType.EMAIL);

		
		final Label labelForTextInputWithUpdateOnBlur = new Label(container, "labelForTextInputWithUpdateOnBlur");
		labelForTextInputWithUpdateOnBlur.setText("Text Input With Update on blur");
		final Label labelForTextInputWithUpdateOnBlur2 = new Label(container, "labelForTextInputWithUpdateOnBlur2");

		final MInputBox textInputWithUpdateOnBlur = new MInputBox(container, "textInputWithUpdateOnBlur");
		textInputWithUpdateOnBlur.setUpdateOnBlur(true);
		textInputWithUpdateOnBlur.addValueChangedListener(new ValueChangedListener() {
			
			@Override
			public void valueChanged(ValueChangedEvent event) {
				labelForTextInputWithUpdateOnBlur2.setText(textInputWithUpdateOnBlur.getText());
			}
		});

		final Label labelForTextArea = new Label(container, "labelForTextArea");
		labelForTextArea.setText("Multline TextArea");
		
		MInputBox multiLine = new MInputBox(container, "multiline");
		multiLine.setMultiLine(true);
		multiLine.setRows(5);


		return container;
	}
}
