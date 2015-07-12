/** 
 * 
 */
package de.jwic.demo.wizard;

import java.io.Serializable;

import de.jwic.base.SessionContext;
import de.jwic.controls.wizard.ValidationException;
import de.jwic.controls.wizard.Wizard;
import de.jwic.controls.wizard.WizardPage;

/**
 * A demo wizard that can generate the sources for a wizard.
 * 
 * This is the core wizard class, that Defines the pages and behavior.
 * 
 * @author lippisch
 */
public class WizardGeneratorWizard extends Wizard implements Serializable {

	private WizardGeneratorModel model;

	/**
	 * 
	 */
	public WizardGeneratorWizard(WizardGeneratorModel model) {
		
		this.model = model;
		setTitle("Wizard Generator");
		setHeight(450);
		setWidth(700);
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.controls.wizard.AbstractWizard#createWizardPages(de.jwic.base.SessionContext)
	 */
	@Override
	public WizardPage createWizardPages(SessionContext sessionContext) {
		
		addWizardPage(new WizardBasicsPage(model));
		addWizardPage(new PageConfigurationPage(model));
		addWizardPage(new SummaryPage(model));
		
		// We need to return the starting wizard page. Let's use the first page in the list by default...
		return getWizardPages().get(0);	
	}

	/* (non-Javadoc)
	 * @see de.jwic.controls.wizard.AbstractWizard#performFinish()
	 */
	@Override
	public boolean performFinish() throws ValidationException {
		
		return true;
	}

}
