/**
 *
 */
package de.jwic.base;

/**
 * @author Alexandru Bledea
 * @since Dec 6, 2013
 */
public interface IHaveVisible {

	/**
	 * Returns true if the control should be rendered.
	 * @return boolean
	 */
	boolean isVisible();

	/**
	 * Set the visibility flag of this control.
	 * @param newVisible boolean
	 */
	void setVisible(final boolean newVisible);

}
