/**
 * 
 */
package de.jwic.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.jwic.base.Application;
import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.demo.advanced.ComboDropDownDemoModule;
import de.jwic.demo.advanced.ComboLifeSearchDemoModule;
import de.jwic.demo.basics.ButtonDemoModule;
import de.jwic.demo.basics.InputBoxDemoModule;
import de.jwic.demo.basics.LabelDemoModule;
import de.jwic.demo.basics.TabStripDemoModule;
import de.jwic.demo.framework.AsyncRenderDemoModule;
import de.jwic.demo.framework.BlockOnWaitDemo;

/**
 * Demonstrates jWic controls. Creates the root control of the application.
 * 
 * @author lippisch
 */
public class DemoApplication extends Application {

	/**
	 * 
	 */
	public DemoApplication() {

	}

	/* (non-Javadoc)
	 * @see de.jwic.base.Application#createRootControl(de.jwic.base.IControlContainer)
	 */
	@Override
	public Control createRootControl(IControlContainer container) {

		List<DemoModule> modules = new ArrayList<DemoModule>();
		modules.add(new LabelDemoModule());
		modules.add(new InputBoxDemoModule());
		modules.add(new BlockOnWaitDemo());
		modules.add(new ButtonDemoModule());
		modules.add(new ComboDropDownDemoModule());
		modules.add(new ComboLifeSearchDemoModule());
		modules.add(new AsyncRenderDemoModule());
		modules.add(new TabStripDemoModule());
		
		// Sort demos by group and title
		Collections.sort(modules);
		
		DemoPage demoPage = new DemoPage(container, modules);
		
		return demoPage;
		
	}

}
