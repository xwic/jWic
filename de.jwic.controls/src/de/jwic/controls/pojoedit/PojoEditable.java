/**
 * 
 */
package de.jwic.controls.pojoedit;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author lippisch
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface PojoEditable {
	/**
	 * Title displayed for the property. If omitted or empty, the property name will be used instead.
	 * @return
	 */
	String title() default "";	
	/**
	 * Set to true on String field that should be edited with a multi-line text field.
	 * @return
	 */
	boolean multiline() default false;
	/** 
	 * Specify a sort order of this field. Fields are sorted from highest to lowest value.
	 * If two fields have the same value, the title of the field is compared against
	 * each other, so that if order is omitted, the fields are sorted by the title. 
	 * @return
	 */
	int order() default 0;
	/** Set to true to ignore this field */
	boolean hide() default false;
}
