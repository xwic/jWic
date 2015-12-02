/**
 * 
 */
package de.jwic.demo.wizard;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.wizard.Wizard;
import de.jwic.controls.wizard.WizardContainer;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * Page to open a wizard in different containers.
 * @author lippisch
 */
public class WizardDemoControl extends ControlContainer {

	private ControlContainer wizContainer;

	/**
	 * @param container
	 * @param name
	 */
	public WizardDemoControl(IControlContainer container, String name) {
		super(container, name);

		
		Button btOpen = new Button(this, "btOpen");
		btOpen.setTitle("Open Wizard as a whole new page");
		btOpen.addSelectionListener(new SelectionListener() {
			@Override
			public void objectSelected(SelectionEvent event) {
				openWizard();
			}
		});

		Button btOpenInner = new Button(this, "btOpenInner");
		btOpenInner.setTitle("Open Wizard on this page");
		btOpenInner.addSelectionListener(new SelectionListener() {
			@Override
			public void objectSelected(SelectionEvent event) {
				openWizardInside();
			}
		});

		
		// This container is required to place the wizard into the same part of the page
		wizContainer = new ControlContainer(this, "wizContainer");
		
	}
	
	/**
	 * Setup the initial configuration and instantiate the wizard.
	 * @return
	 */
	private Wizard createWizard() {
		
		WizardGeneratorModel model = new WizardGeneratorModel();
		model.setTitle("Demo Wizard");
		model.setPackageName("de.jwic.samples.wizard.generator");
		model.setWizardClass("DemoWizard");
		model.setModelClass("DemoWizardModel");
		
		WizardPageConfig page = new WizardPageConfig();
		page.setClassName("FirstWizardPage");
		page.setTitle("First Page");
		page.setSubTitle("This is the first page. Enter something smart!");
		
		model.addPage(page);
		
		return new WizardGeneratorWizard(model);
	}

	/**
	 * 
	 */
	protected void openWizard() {

		WizardContainer container = new WizardContainer(createWizard(), this);
		container.openAsPage();
		
	}
	
	/**
	 * 
	 */
	protected void openWizardInside() {

		WizardContainer container = new WizardContainer(createWizard(), this);
		container.openInContainer(wizContainer, "wiz");
		
	}

}
