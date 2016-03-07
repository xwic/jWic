/**
 * 
 */
package de.jwic.controls.pojoedit;

import java.beans.PropertyDescriptor;

import de.jwic.base.IControl;

/**
 * A property on the pojo that is editable in a field.
 * @author lippisch
 */
public class PojoField implements Comparable<PojoField>{

	private PropertyDescriptor propertyDescriptor;
	private String title = null;
	private String name = null;
	
	private IControl control = null;
	private IFieldHandler<? extends IControl> fieldHandler = null;
	private PojoEditable annotation;
	
	/**
	 * @param pd
	 */
	public PojoField(PropertyDescriptor pd, PojoEditable annotation) {
		this.propertyDescriptor = pd;
		this.annotation = annotation;

		title = pd.getDisplayName();
		name = pd.getName();
		
		if (annotation != null) {
			if (annotation.title() != null && !annotation.title().isEmpty()) {
				title = annotation.title();
			}
		}
	}

	/**
	 * Returns the title of this field.
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the control
	 */
	public IControl getControl() {
		return control;
	}

	/**
	 * @param control the control to set
	 */
	public void setControl(IControl control) {
		this.control = control;
	}

	/**
	 * @return the mapper
	 */
	public IFieldHandler<? extends IControl> getFieldHandler() {
		return fieldHandler;
	}

	/**
	 * @param mapper the mapper to set
	 */
	public void setFieldHandler(IFieldHandler<? extends IControl> mapper) {
		this.fieldHandler = mapper;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the annotation
	 */
	public PojoEditable getAnnotation() {
		return annotation;
	}

	/**
	 * @return the propertyDescriptor
	 */
	public PropertyDescriptor getPropertyDescriptor() {
		return propertyDescriptor;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(PojoField o) {
		int myOrder = annotation != null ? annotation.order() : 0;
		int otherOrder = o.annotation != null ? o.annotation.order() : 0;
		
		if (myOrder == otherOrder) {
			// Sort by title if the sort order is the same.
			return this.title.compareTo(o.title);
		}
		if (myOrder < otherOrder) {
			return -1;
		} else {
			return 1;
		}
	}
	
}
