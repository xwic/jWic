package de.jwic.demo.advanced;

import de.jwic.base.IControlContainer;
import de.jwic.controls.ErrorWarning;
import de.jwic.demo.DemoModule;

public class ErrorWariningDemoModule extends DemoModule {
	
	public ErrorWariningDemoModule() {
		this.setTitle("Error Warning Demo");
		this.setDescription("The Error Warning Control. Since we all have errors, we should also have an fancy error warning control to show them");
		this.setGroup("Advanced");
	}

	@Override
	public void createModule(IControlContainer container) {
		
		new ErrorWarningDemo(container);
		
	}

}
