/**
 * 
 */
package de.jwic.controls.pojoedit;

import de.jwic.base.IControl;
import de.jwic.base.IControlContainer;

/**
 * Handles a single field on a pojo, creating the UI widget, reading and writing the value from/to the pojo.
 * 
 * @author lippisch
 */
public interface IFieldHandler<T extends IControl> {

	/**
	 * Returns true if this handler can handle the specified field.
	 * @param field
	 * @return
	 */
	public boolean accepts(PojoField field);
	
	/**
	 * Create the control for the field.
	 * @param container
	 * @param field
	 * @return
	 */
	public T createControl(IControlContainer container, PojoField field);
	
	/**
	 * Populate the control with the value from the property.
	 * @param control
	 * @param value
	 */
	public void loadValue(PojoField field, T control, Object value);
	
	/**
	 * Read the value from the control.
	 * @param control
	 * @return
	 */
	public Object readValue(PojoField field, T control);
	
}
