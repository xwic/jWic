package de.jwic.mobile.controls;

import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;

/**
 * Created by boogie on 10/28/14.
 */
@JavaScriptSupport(jsTemplate = "de.jwic.mobile.controls.TextInput")
public class TextArea extends TextInput {
	private int rows = 8;
	private int columns = 40;
	/**
	 * Constructs a new control instance and adds it to the specified
	 * container with the specified name. If the name is <code>null</code>,
	 * a unique name will be chosen by the container.
	 *
	 * @param container
	 * @param name
	 */
	public TextArea(IControlContainer container, String name) {
		super(container, name);
	}

	/**
	 *
	 * @return the number of rows this TextArea has
	 */
	@IncludeJsOption
	public int getRows() {
		return rows;
	}

	/**
	 *
	 * @param rows - the number of rows this TextArea will have
	 */
	public void setRows(int rows) {
		this.rows = rows;
		this.requireRedraw();
	}

	/**
	 *
	 * @return the number of columns this TextArea has
	 */
	@IncludeJsOption
	public int getColumns() {
		return columns;
	}

	/**
	 *
	 * @param columns - the number of columns this TextArea will have
	 */
	public void setColumns(int columns) {
		this.columns = columns;
		this.requireRedraw();
	}
}
