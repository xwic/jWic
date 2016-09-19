/**
 * 
 */
package de.jwic.controls.combo;

import java.io.Serializable;
import java.util.List;

/**
 * @author vedad
 *
 */
public interface IVisualSearch<A> extends Serializable {
	
	/**
	 * @return the key
	 */
	public String getKey();

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key);

	/**
	 * @return the value
	 */
	public String getValue();

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value);

	/**
	 * @return the children
	 */
	public List<A> getChildren();

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(List<A> children);

	/**
	 * @param child
	 */
	public void addChild(A child);

}
