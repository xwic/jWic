/**
 *
 */
package de.jwic.base;

/**
 * @author Alexandru Bledea
 * @since Dec 6, 2013
 */
public interface IHaveEnabled {

	/**
	 * @return Returns true if the element is enabled.
	 */
	public abstract boolean isEnabled();

	/**
	 * @param enabled set to true to enable the element.
	 */
	public abstract void setEnabled(boolean enabled);

}
