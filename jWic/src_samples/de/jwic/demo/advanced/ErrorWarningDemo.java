package de.jwic.demo.advanced;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.ErrorWarning;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

public class ErrorWarningDemo extends ControlContainer {
	private final ErrorWarning errorWarning;
	private final Button button;
	private final Button buttonClose;
	private final Button buttonHide;
	public ErrorWarningDemo(IControlContainer container) {
		this(container,null);
	}

	public ErrorWarningDemo(IControlContainer container, String name) {
		super(container, name);
		this.errorWarning = new ErrorWarning(this, "errorWarning");
		this.button = new Button(this,"clickMe");
		button.setTitle("Click To Show Error");
		this.buttonClose = new Button(this, "clickClose");
		this.buttonClose.setTitle("Click to Close the Error");
		
		this.buttonHide = new Button(this, "clickHide");
		this.buttonHide.setTitle("Click to Hide/Slide Up");
		
		init();
		
	}

	private void init() {
		this.button.addSelectionListener(new SelectionListener() {
			
			@Override
			public void objectSelected(SelectionEvent event) {
				errorWarning.showError("Some random error has occured, but thankfully you can show in a pretty way.");
			}
		});
		
		this.buttonClose.addSelectionListener(new SelectionListener() {
			
			@Override
			public void objectSelected(SelectionEvent event) {
				errorWarning.close();
			}
		});
		
		this.buttonHide.addSelectionListener(new SelectionListener() {
			
			@Override
			public void objectSelected(SelectionEvent event) {
				errorWarning.hide();
			}
		});
	}

}
