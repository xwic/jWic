package de.jwic.samples.controls;

import java.text.DateFormat;
import java.util.Date;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.InputBoxControl;
import de.jwic.controls.ListBoxControl;
import de.jwic.controls.NumericInputControl;
import de.jwic.controls.NumericInputControl.ThousandSeparator;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;
import de.jwic.samples.controls.propeditor.PropertyEditorView;

public class NumericInputControlDemo extends ControlContainer {

	private NumericInputControl inputbox;
	private ListBoxControl eventLog;
	
	private class EventLogListener implements ValueChangedListener {
		/* (non-Javadoc)
		 * @see de.jwic.events.ValueChangedListener#valueChanged(de.jwic.events.ValueChangedEvent)
		 */
		public void valueChanged(ValueChangedEvent event) {
			
			DateFormat df = DateFormat.getDateTimeInstance();
			String eventInfo = df.format(new Date()) + ": valueChanged - old value: '" + event.getOldValue() + "' new value: '" + event.getNewValue() + "'";
			
			eventLog.addElement(eventInfo);
		}
	}

	public NumericInputControlDemo(IControlContainer container) {
		super(container);
		
		inputbox = new NumericInputControl(this, "inputbox");
		inputbox.setText("");
		inputbox.setThousandSeparator(ThousandSeparator.SPACE);
		inputbox.setEmptyInfoText("Enter some number here.");
		
		final PropertyEditorView propEditor = new PropertyEditorView(this, "propEditor");
		
		
		inputbox.addValueChangedListener(new EventLogListener());
		
		final InputBoxControl inpRawNumber = new InputBoxControl(this, "inpRaw");
		inpRawNumber.setText("0");
		
		Button b = new Button(this, "Button");
		b.setTitle("Set value");
		b.addSelectionListener(new SelectionListener() {
			
			@Override
			public void objectSelected(SelectionEvent event) {
				inputbox.setNumber(Double.parseDouble(inpRawNumber.getText()));
			}
		});
		
		
		b = new Button(this, "Button2");
		b.setTitle("Get value");
		b.addSelectionListener(new SelectionListener() {
			
			@Override
			public void objectSelected(SelectionEvent event) {
				inpRawNumber.setText(inputbox.getNumber().toString());
			}
		});
		
	
		propEditor.setBean(inputbox);
		
		eventLog = new ListBoxControl(this, "eventLog");
		eventLog.setSize(8);
		
	}
	
}
