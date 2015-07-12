/**
 * 
 */
package de.jwic.demo.wizard;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.InputBox;
import de.jwic.controls.Label;
import de.jwic.controls.RadioGroup;
import de.jwic.controls.ScrollableContainer;
import de.jwic.controls.wizard.ValidationException;
import de.jwic.controls.wizard.WizardPage;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;

/**
 * This represents the last page of the wizard, that displays the generated files
 * and content.
 * 
 * This page is demonstrating another way on how to refresh the content when the
 * page is activated after the user went back and forth. It is overriding the 
 * activated method, to simply refresh the content whenever the page is activated
 * again.
 * 
 * @author lippisch
 */
public class SummaryPage extends WizardPage {

	private WizardGeneratorModel model;
	private RadioGroup fileSelector;
	private Label lbContent;

	/**
	 * @param model 
	 * 
	 */
	public SummaryPage(WizardGeneratorModel model) {
		this.model = model;
		setTitle("Summary");
		setSubTitle("View the content of the generated files.");
		
	}


	/* (non-Javadoc)
	 * @see de.jwic.controls.wizard.WizardPage#activated()
	 */
	@Override
	public void activated() {
		super.activated();
		
		model.generateFiles();
		
		fileSelector.clear();
		for (GeneratedFile file : model.getGeneratedFiles()) {
			fileSelector.addElement(file.getFilename());
		}
		
	}
	
	
	/* (non-Javadoc)
	 * @see de.jwic.controls.wizard.WizardPage#createControls(de.jwic.base.IControlContainer)
	 */
	@Override
	public void createControls(IControlContainer container) {

		ControlContainer content = new ControlContainer(container);
		content.setTemplateName(getClass().getName());
		
		fileSelector = new RadioGroup(content, "fileSelector");
		fileSelector.setColumns(3);
		fileSelector.addElementSelectedListener(new ElementSelectedListener() {
			@Override
			public void elementSelected(ElementSelectedEvent event) {
				onFileSelection();
			}
		});
		
		ScrollableContainer fileViewer = new ScrollableContainer(content, "fileViewer");
		fileViewer.setWidth("100%");
		fileViewer.setHeight("300px");
		
		lbContent = new Label(fileViewer, "content");
		lbContent.setStyle("font-family", "Courier");
		lbContent.setStyle("font-size", "10px");
		
		
	}
	
	/**
	 * 
	 */
	protected void onFileSelection() {
		
		lbContent.setText("");
		
		for (GeneratedFile file : model.getGeneratedFiles()) {
			if (fileSelector.getSelectedKey().equals(file.getFilename())) {
				lbContent.setText(file.getContent());
				break;
			}
		}
		
	}


	/* (non-Javadoc)
	 * @see de.jwic.controls.wizard.WizardPage#validate()
	 */
	@Override
	public boolean validate() throws ValidationException {
		return super.validate();
	}
}
