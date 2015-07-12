/**
 * 
 */
package de.jwic.demo.wizard;

import java.util.ArrayList;
import java.util.List;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.AnchorLink;
import de.jwic.controls.InputBox;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * Edits the properties of a WizardPageConfig.
 * @author lippisch
 */
public class PageEditorControl extends ControlContainer {

	private WizardPageConfig config;
	private int pageNumber;
	private InputBox ibClassName;
	private InputBox ibTitle;
	private InputBox ibSubTitle;
	
	private IPageEditorControlObserver observer = null; // allow only 1 listener.

	/**
	 * @param container
	 * @param name
	 */
	public PageEditorControl(IControlContainer container, String name, WizardPageConfig config, int pageNumber) {
		super(container, name);
		this.config = config;
		this.pageNumber = pageNumber;

		createControls();
		
	}

	
	
	/**
	 * 
	 */
	private void createControls() {

		AnchorLink btRemove = new AnchorLink(this, "btRemove");
		btRemove.setTitle("Remove");
		//btRemove.setIconEnabled(ImageLibrary.IMG_CROSS);
		btRemove.addSelectionListener(new SelectionListener() {
			@Override
			public void objectSelected(SelectionEvent event) {
				removeEntry();
			}
		});
		
		ibClassName = new InputBox(this, "ibClassName");
		ibClassName.setWidth(400);
		
		ibTitle = new InputBox(this, "ibTitle");
		ibTitle.setWidth(400);
		
		ibSubTitle = new InputBox(this, "ibSubtitle");
		ibSubTitle.setWidth(400);

		// apply values
		ibClassName.setText(config.getClassName());
		ibTitle.setText(config.getTitle());
		ibSubTitle.setText(config.getSubTitle());
		
	}

	/**
	 * 
	 */
	protected void removeEntry() {
		if (observer != null) {
			observer.onDeletion(this);
		}
	}



	/**
	 * @return the pageNumber
	 */
	public int getPageNumber() {
		return pageNumber;
	}



	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}



	/**
	 * @param listener the listener to set
	 */
	public void setObserver(IPageEditorControlObserver observer) {
		this.observer = observer;
	}



	/**
	 * Copies the values from the input fields into the configuration object and
	 * validates them.
	 * @return
	 */
	public List<String> validate() {
		
		List<String> errors = new ArrayList<String>();
		
		config.setClassName(ibClassName.getText());
		config.setTitle(ibTitle.getText());
		config.setSubTitle(ibSubTitle.getText());
		
		if (config.getClassName().trim().isEmpty()) {
			errors.add("Page " + getPageNumber() + ": Classname missing");
			ibClassName.setFlagAsError(true);
		} else {
			ibClassName.setFlagAsError(false);
		}
		
		return errors;
	}



	/**
	 * Returns the configuration.
	 * @return
	 */
	public WizardPageConfig getPageConfig() {
		// make sure to update..
		config.setClassName(ibClassName.getText());
		config.setTitle(ibTitle.getText());
		config.setSubTitle(ibSubTitle.getText());
		
		return config;
	}

}
