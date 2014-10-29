package de.jwic.mobile;

import de.jwic.base.Application;
import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Label;
import de.jwic.mobile.common.blurable.BlurListener;
import de.jwic.mobile.common.blurable.Blurable;
import de.jwic.mobile.common.clickable.ClickListener;
import de.jwic.mobile.common.clickable.Clickable;
import de.jwic.mobile.common.properties.PropertyChangedListener;
import de.jwic.mobile.controls.Button;
import de.jwic.mobile.controls.FlipSwitch;
import de.jwic.mobile.controls.Page;
import de.jwic.mobile.controls.TextArea;
import de.jwic.mobile.controls.TextInput;
import de.jwic.mobile.demos.ButtonDemo;
import de.jwic.mobile.demos.InputDemo;

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

		return new MobileDemoPage(container, "demoPage", mobileDemoModules);
	}
}
