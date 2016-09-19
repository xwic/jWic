/**
 * 
 */
package de.jwic.demo.advanced;

import java.util.ArrayList;
import java.util.List;

import de.jwic.controls.combo.IVisualSearch;

/**
 * @author vedad
 *
 */
public class VisualSearchObject implements IVisualSearch<VisualSearchObject> {

	private static final long serialVersionUID = -5416678173664003358L;
	
	private String key;
	private String value;
	private List<VisualSearchObject> children = new ArrayList<VisualSearchObject>();

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the children
	 */
	public List<VisualSearchObject> getChildren() {
		return children;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(List<VisualSearchObject> children) {
		this.children = children;
	}

	/**
	 * @param child
	 */
	public void addChild(VisualSearchObject child) {
		this.children.add(child);
	}

}
