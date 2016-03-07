/**
 * 
 */
package de.jwic.demo.pojoedit;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.pojoedit.PojoEditor;
import de.jwic.controls.pojoedit.PojoEditorException;
import de.jwic.controls.pojoedit.PojoEditorModel;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * @author lippisch
 *
 */
public class MyPojoEditor extends ControlContainer {

	private MyPojo myPojo;
	private PojoEditor pojoEditor;
	private PojoEditorModel editorModel;

	/**
	 * @param container
	 * @param name
	 */
	public MyPojoEditor(IControlContainer container, String name) {
		super(container, name);

		myPojo = new MyPojo();
		myPojo.setTitle("My Pojo");
		myPojo.setStatus(MyPojo.Status.COMPLETED);

		createContent();
		
	}

	/**
	 * 
	 */
	private void createContent() {

		try {
			editorModel = new PojoEditorModel(MyPojo.class);
			pojoEditor = new PojoEditor(this, "pojoEditor", editorModel);
			
			Button btSave = new Button(this, "btSave");
			btSave.setTitle("Save");
			btSave.addSelectionListener(new SelectionListener() {
				@Override
				public void objectSelected(SelectionEvent event) {
					performSave();
				}
			});

			editorModel.loadPojo(myPojo);

			
		} catch (PojoEditorException pe) {
			getSessionContext().notifyMessage("Something went wrong here: " + pe, "error");
			log.error(pe);
		}
	}

	/**
	 * 
	 */
	protected void performSave() {

		try {
			editorModel.savePojo(myPojo);
			getSessionContext().notifyMessage("Values have been saved...");
		} catch (PojoEditorException e) {
			log.error("Error saving pojo", e);
			getSessionContext().notifyMessage("Error saving pojo (" + e + ")", "error");
		}
		
	}

}
