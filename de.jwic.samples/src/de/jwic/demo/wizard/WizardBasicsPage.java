/**
 * 
 */
package de.jwic.demo.wizard;

import java.util.ArrayList;
import java.util.List;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.InputBox;
import de.jwic.controls.ListBox;
import de.jwic.controls.wizard.ValidationException;
import de.jwic.controls.wizard.WizardPage;

/**
 * Asks the user for some general input like the wizard name, title, etc..
 * @author lippisch
 */
public class WizardBasicsPage extends WizardPage {

	private WizardGeneratorModel model;
	private InputBox ibTitle;
	private InputBox ibPackage;
	private InputBox ibWizardClass;
	private InputBox ibModelClass;
	
	private ListBox lbWidth;
	private ListBox lbHeight;

	/**
	 * 
	 */
	public WizardBasicsPage(WizardGeneratorModel model) {
		this.model = model;
		
		setTitle("Basic Information");
		setSubTitle("Specify basic information such as the title of the Wizard");
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.controls.wizard.WizardPage#createControls(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createControls(IControlContainer container) {

		ControlContainer content = new ControlContainer(container, "base");
		content.setTemplateName(getClass().getName()); // use the VTL repspective for this class
		
		ibTitle = new InputBox(content, "ibTitle");
		ibTitle.setWidth(400);
		
		ibPackage = new InputBox(content, "ibPackage");
		ibPackage.setWidth(400);
		
		ibWizardClass = new InputBox(content, "ibWizardClass");
		ibWizardClass.setWidth(400);
		
		ibModelClass = new InputBox(content, "ibModelClass");
		ibModelClass.setWidth(400);
		
		lbWidth = new ListBox(content, "lbWidth");
		lbHeight = new ListBox(content, "lbHeight");

		for (int i = 0; i < 1000; i += 50) {
			String s = Integer.toString(i);
			lbWidth.addElement(s, s);
			lbHeight.addElement(s, s);
		}

		
		// apply data from model
		ibTitle.setText(model.getTitle());
		ibPackage.setText(model.getPackageName());
		ibWizardClass.setText(model.getWizardClass());
		ibModelClass.setText(model.getModelClass());
		lbWidth.selectedByKey(Integer.toString(model.getWizardWidth()));
		lbHeight.selectedByKey(Integer.toString(model.getWizardHeight()));
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.controls.wizard.WizardPage#validate()
	 */
	@Override
	public boolean validate() throws ValidationException {
		
		List<String> errors = new ArrayList<String>();
		checkEmpty(ibPackage, errors, "No package specified.");
		checkEmpty(ibWizardClass, errors, "No wizard classname specified.");
		checkEmpty(ibModelClass, errors, "No model classname specified.");
		
		
		if (!errors.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (String msg : errors) {
				sb.append(msg).append("<br>");
			}
			throw new ValidationException(sb.toString());
		}
		
		model.setTitle(ibTitle.getText());
		model.setPackageName(ibPackage.getText().trim());
		model.setWizardClass(ibWizardClass.getText().trim());
		model.setModelClass(ibModelClass.getText().trim());
		
		if (lbWidth.getSelectedKey() != null) {
			model.setWizardWidth(Integer.parseInt(lbWidth.getSelectedKey()));
		} else {
			model.setWizardWidth(0);
		}

		if (lbHeight.getSelectedKey() != null) {
			model.setWizardHeight(Integer.parseInt(lbHeight.getSelectedKey()));
		} else {
			model.setWizardHeight(0);
		}

		return true; // ok to proceed
	}

	/**
	 * @param ibPackage2
	 * @param string
	 */
	private void checkEmpty(InputBox ib, List<String> errors, String message) throws ValidationException {
		if (ib.getText().trim().isEmpty()) {
			ib.setFlagAsError(true);
			errors.add(message);
		} else {
			ib.setFlagAsError(false);
		}
	}
	
}
