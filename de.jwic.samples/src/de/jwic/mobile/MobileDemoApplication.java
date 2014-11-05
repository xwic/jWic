package de.jwic.mobile;

import de.jwic.base.Application;
import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.mobile.demos.AlexDemo;
import de.jwic.mobile.demos.ButtonDemo;
import de.jwic.mobile.demos.CheckBoxDemo;
import de.jwic.mobile.demos.DatePickerDemo;
import de.jwic.mobile.demos.InputDemo;
import de.jwic.mobile.demos.RadioButtonDemo;
import de.jwic.mobile.demos.ToggleableGroupsDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boogie on 10/27/14.
 */
public class MobileDemoApplication extends Application {

	@Override
	public Control createRootControl(IControlContainer container) {
		final List<MobileDemoModule> mobileDemoModules = new ArrayList<MobileDemoModule>();

		mobileDemoModules.add(new ButtonDemo());
		mobileDemoModules.add(new InputDemo());
		mobileDemoModules.add(new CheckBoxDemo());
		mobileDemoModules.add(new RadioButtonDemo());
		mobileDemoModules.add(new DatePickerDemo());
		mobileDemoModules.add(new ToggleableGroupsDemo());
		mobileDemoModules.add(new AlexDemo());


		return new MobileDemoMPage(container, "demoPage", mobileDemoModules);
	}
}
