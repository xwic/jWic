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
import de.jwic.demo.advanced.CKEditorDemoModule;
import de.jwic.demo.advanced.ColumnSelectorDemoModule;
import de.jwic.demo.advanced.ComboDropDownDemoModule;
import de.jwic.demo.advanced.ComboLifeSearchDemoModule;
import de.jwic.demo.advanced.ErrorWariningDemoModule;
import de.jwic.demo.advanced.LazyTooltipDemoModule;
import de.jwic.demo.advanced.ProcessInfoDemoModule;
import de.jwic.demo.basics.ButtonDemoModule;
import de.jwic.demo.basics.CheckBoxDemoModule;
import de.jwic.demo.basics.CheckBoxGroupDemoModule;
import de.jwic.demo.basics.DatePickerDemoModule;
import de.jwic.demo.basics.FileUploadDemoModule;
import de.jwic.demo.basics.InputBoxDemoModule;
import de.jwic.demo.basics.LabelDemoModule;
import de.jwic.demo.basics.ListBoxDemoModule;
import de.jwic.demo.basics.MenuDemoModule;
import de.jwic.demo.basics.RadioGroupDemoModule;
import de.jwic.demo.basics.ToolBarDemoModule;
import de.jwic.demo.container.AccordionDemoModule;
import de.jwic.demo.container.GroupControlDemoModule;
import de.jwic.demo.container.ScrollableContainerDemoModule;
import de.jwic.demo.container.StackedContainerDemoModule;
import de.jwic.demo.container.TabStripDemoModule;
import de.jwic.demo.container.WindowDemoModule;
import de.jwic.demo.framework.AsyncRenderDemoModule;
import de.jwic.demo.framework.BlockOnWaitDemo;
import de.jwic.demo.tbv.BasicTBVDemoModule;
import de.jwic.demo.tbv.FileTreeDemoModule;
import de.jwic.demo.tbv.TableViewerDemoModule;

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
		modules.add(new DatePickerDemoModule());
		modules.add(new BlockOnWaitDemo());
		modules.add(new ButtonDemoModule());
		modules.add(new ComboDropDownDemoModule());
		modules.add(new ComboLifeSearchDemoModule());
		modules.add(new AsyncRenderDemoModule());
		modules.add(new TabStripDemoModule());
		modules.add(new AccordionDemoModule());
		modules.add(new CheckBoxDemoModule());
		modules.add(new CheckBoxGroupDemoModule());
		modules.add(new FileUploadDemoModule());
		modules.add(new GroupControlDemoModule());
		modules.add(new ListBoxDemoModule());
		modules.add(new RadioGroupDemoModule());
		modules.add(new ProcessInfoDemoModule());
		modules.add(new ScrollableContainerDemoModule());
		modules.add(new StackedContainerDemoModule());
		modules.add(new CKEditorDemoModule());
		modules.add(new ToolBarDemoModule());
		modules.add(new BasicTBVDemoModule());
		modules.add(new TableViewerDemoModule());
		modules.add(new WindowDemoModule());
		modules.add(new MenuDemoModule());
		modules.add(new ColumnSelectorDemoModule());
		modules.add(new FileTreeDemoModule());
		modules.add(new ErrorWariningDemoModule());
		modules.add(new LazyTooltipDemoModule());

		// Sort demos by group and title
		Collections.sort(modules);
		
		DemoPage demoPage = new DemoPage(container, modules);
		
		return demoPage;
		
	}

}
