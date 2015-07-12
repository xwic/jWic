/**
 * 
 */
package de.jwic.demo.wizard;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.Label;
import de.jwic.controls.wizard.ValidationException;
import de.jwic.controls.wizard.WizardPage;
import de.jwic.demo.ImageLibrary;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * This represents the second page of the wizard, to configure the WizardPages that
 * should be generated.
 * 
 * One 'sample' on this page shows how to update content on changes. In this case, a label 
 * is displaying the package name entered on the previous page. If the user clicks back 
 * and changes the package name, the label needs to be updated. This works because the page
 * is listening to model changes and updates the label whenever it changes.
 * 
 * @author lippisch
 */
public class PageConfigurationPage extends WizardPage {

	private final static String INFO_MESSAGE = "All new wizard pages will be created in the package <b>%s</b>";
	
	private WizardGeneratorModel model;
	private PageConfigListControl configList = null;
	private Label lbInfo = null;

	/**
	 * @param model 
	 * 
	 */
	public PageConfigurationPage(WizardGeneratorModel model) {
		this.model = model;
		setTitle("Page Configuration");
		setSubTitle("You can configure each page that should be generated.");
		
		// In order to update pages on model changes, register a listener to the model
		model.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				onModelUpdate(evt);
			}
		});
	}

	/**
	 * @param evt
	 */
	protected void onModelUpdate(PropertyChangeEvent evt) {
		
		// The actual controls on the page are first created when the page is activated
		// the first time. Therefore always check if the controls actually exist on
		// model events as the event may be triggered before the controls are even created.
		// An alternative solution would be to register only to the model after the controls
		// are created (in the createControls(..) method).
		if (lbInfo != null && evt.getPropertyName().equals("packageName")) { 
			lbInfo.setText(String.format(INFO_MESSAGE, model.getPackageName()));
		}
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.controls.wizard.WizardPage#createControls(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createControls(IControlContainer container) {

		ControlContainer content = new ControlContainer(container);
		content.setTemplateName(getClass().getName());
		
		Button btAddPage = new Button(content, "btAddPage");
		btAddPage.setTitle("Add Page");
		btAddPage.setIconEnabled(ImageLibrary.IMG_ADD);
		btAddPage.addSelectionListener(new SelectionListener() {
			@Override
			public void objectSelected(SelectionEvent event) {
				addPage();
			}
		});
		
		configList = new PageConfigListControl(content, "pageList");
		configList.setWidth("100%");
		configList.setHeight("250px");
		
		// load initial model values
		for (WizardPageConfig page : model.getPages()) {
			configList.addEditor(page);
		}
		
		lbInfo = new Label(content, "lbInfo");
		lbInfo.setText(String.format(INFO_MESSAGE, model.getPackageName()));
		
	}
	
	/**
	 * 
	 */
	protected void addPage() {

		WizardPageConfig config = new WizardPageConfig();
		configList.addEditor(config);
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.controls.wizard.WizardPage#validate()
	 */
	@Override
	public boolean validate() throws ValidationException {
		configList.validate();
		model.setPages(configList.getPageConfigs());
		
		return super.validate();
	}
}
