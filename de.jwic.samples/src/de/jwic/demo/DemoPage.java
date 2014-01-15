/**
 * 
 */
package de.jwic.demo;

import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.base.Page;
import de.jwic.controls.ListBoxControl;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;

/**
 * Defines the layout of the page and acts as the root control.
 * 
 * @author lippisch
 */
public class DemoPage extends Page {

	private DemoModel model;

	/**
	 * @param container
	 * @param modules 
	 */
	public DemoPage(IControlContainer container, List<DemoModule> modules) {
		super(container);
		setTitle("Application Demo");
		
		createThemeSelector();

		model = new DemoModel(modules);
		new DemoSelector(this, "demoSelector", model);
		new DemoHost(this, "demoHost", model);
		
	}

	public void actionRestart() {
		getSessionContext().setExitURL("demo.xwic");
		getSessionContext().exit();
	}

	/**
	 * 
	 */
	private void createThemeSelector() {
		ListBoxControl lbTheme = new ListBoxControl(this, "lbTheme");
		lbTheme.addElement("Default Theme", "default");
		lbTheme.addElement("Start Theme", "start");
		lbTheme.addElement("UI Darkness", "ui-darkness");
		
		lbTheme.setSelectedKey("default");
		lbTheme.setChangeNotification(true);
		lbTheme.addElementSelectedListener(new ElementSelectedListener() {
			public void elementSelected(ElementSelectedEvent event) {
				onThemeChange((String)event.getElement());
			}
		});
		
	}

	/**
	 * @param theme
	 */
	protected void onThemeChange(String theme) {
		getSessionContext().setThemeName(theme);
		
	}


}
