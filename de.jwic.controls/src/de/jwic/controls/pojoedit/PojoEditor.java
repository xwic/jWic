/**
 * 
 */
package de.jwic.controls.pojoedit;

import java.util.List;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControl;
import de.jwic.base.IControlContainer;

/**
 * Control that generates an editor form dynamically for any simple Pojo-style java object.
 * 
 * @author lippisch
 */
public class PojoEditor extends ControlContainer {

	private PojoEditorModel model;

	/**
	 * @param container
	 * @param name
	 */
	public PojoEditor(IControlContainer container, String name, PojoEditorModel model) {
		super(container, name);
		this.model = model;
		
		generateFieldControls();
	}

	/**
	 * Create a control for each field.
	 */
	private void generateFieldControls() {

		for (PojoField field : model.getFields()) {
			if (field.getFieldHandler() != null) {
				IControl control = field.getFieldHandler().createControl(this, field);
				field.setControl(control);
			}
		}
		
	}

	/**
	 * Returns the list of fields.
	 * @return
	 */
	public List<PojoField> getFields() {
		return model.getFields();
	}
	
	
	
}
