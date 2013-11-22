package de.jwic.demo.advanced;

import de.jwic.base.IControlContainer;
import de.jwic.demo.DemoModule;

public class LazyTooltipDemoModule extends DemoModule {

	public LazyTooltipDemoModule() {
		this.setTitle("Lazy Tooltip Demo");
		this.setDescription("Fancy lazy loaded tooltips");
		this.setGroup("Advanced");
	}

	@Override
	public void createModule(IControlContainer container) {
		new LazyTooltipDemo(container);
	}

}
