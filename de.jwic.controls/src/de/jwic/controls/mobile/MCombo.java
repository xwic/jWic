package de.jwic.controls.mobile;

import java.util.ArrayList;
import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.combo.Combo;
import de.jwic.data.ISelectElement;
import de.jwic.data.SelectElement;

/**
 * 
 * The MCombo offers the same functionality as the base Combo, but renders as a
 * basic HTML combo that inherits the default jQuery Mobile
 * 
 * @author vedad
 *
 */
@JavaScriptSupport
public class MCombo extends Combo<ISelectElement> {

	private static final long serialVersionUID = 1L;
	
	private boolean autodividers = false;
	private boolean defaults = false;
	private boolean hideDividers = false;
	private boolean inset = false;
	private Theme countTheme = null;
	private Theme dividerTheme = null;
	private Theme splitTheme = null;
	private Theme theme = null;
	private Icon icon = Icon.CARATR;
	private Icon splitIcon = Icon.CARATR;
	private List<ISelectElement> elements = null;
	/**
	 * Constructs a new control instance and adds it to the specified container
	 * with the specified name. If the name is <code>null</code>, a unique name
	 * will be chosen by the container.
	 * 
	 * @param container
	 * @param name
	 */
	public MCombo(IControlContainer container, String name) {
		super(container, name);
		setTemplateName(MCombo.class.getName());
	}
	
	/**
	 * Returns all the elements of select menu	
	 */
	@IncludeJsOption
	public List<ISelectElement> getElements() {
		return elements;
	}
	
	/**
	 * Add an element.
	 * @param element
	 */
	public void addElement(ISelectElement element) {
		if (elements == null) {
			elements = new ArrayList<ISelectElement>();
		}
		elements.add(element);
	}

	/**
	 * Add an element. The key will automatically be assigned.
	 * @param title
	 */
	public ISelectElement addElement(String title) {
		SelectElement elm = new SelectElement(title);
		addElement(elm);
		return elm;
	}
	
	/**
	 * Add the element with a custom key.
	 * @param title
	 * @param key
	 */
	public ISelectElement addElement(String title, String key) {
		SelectElement elm = new SelectElement(title, key);
		addElement(elm);
		return elm;
	}

	/**
	 * @return the autodividers
	 */
	@IncludeJsOption
	public boolean isAutodividers() {
		return autodividers;
	}

	/**
	 * @param autodividers the autodividers to set
	 */
	public void setAutodividers(boolean autodividers) {
		if (autodividers != this.autodividers)
			requireRedraw();
		this.autodividers = autodividers;
	}

	/**
	 * @return the defaults
	 */
	@IncludeJsOption
	public boolean isDefaults() {
		return defaults;
	}

	/**
	 * @param defaults the defaults to set
	 */
	public void setDefaults(boolean defaults) {
		if (defaults != this.defaults)
			requireRedraw();
		this.defaults = defaults;
	}

	/**
	 * @return the hideDividers
	 */
	@IncludeJsOption
	public boolean isHideDividers() {
		return hideDividers;
	}

	/**
	 * @param hideDividers the hideDividers to set
	 */
	public void setHideDividers(boolean hideDividers) {
		if (hideDividers != this.hideDividers)
			requireRedraw();
		this.hideDividers = hideDividers;
	}

	/**
	 * @return the inset
	 */
	@IncludeJsOption
	public boolean isInset() {
		return inset;
	}

	/**
	 * @param inset the inset to set
	 */
	public void setInset(boolean inset) {
		if (inset != this.inset)
			requireRedraw();
		this.inset = inset;
	}

	/**
	 * @return the countTheme
	 */
	@IncludeJsOption
	public Theme getCountTheme() {
		return countTheme;
	}

	/**
	 * @param countTheme the countTheme to set
	 */
	public void setCountTheme(Theme countTheme) {
		if (countTheme.equals(countTheme))
			requireRedraw();
		this.countTheme = countTheme;
	}

	/**
	 * @return the dividerTheme
	 */
	@IncludeJsOption
	public Theme getDividerTheme() {
		return dividerTheme;
	}

	/**
	 * @param dividerTheme the dividerTheme to set
	 */
	public void setDividerTheme(Theme dividerTheme) {
		if (dividerTheme.equals(dividerTheme))
			requireRedraw();
		this.dividerTheme = dividerTheme;
	}

	/**
	 * @return the splitTheme
	 */
	@IncludeJsOption
	public Theme getSplitTheme() {
		return splitTheme;
	}

	/**
	 * @param splitTheme the splitTheme to set
	 */
	public void setSplitTheme(Theme splitTheme) {
		if (splitTheme.equals(splitTheme))
			requireRedraw();
		this.splitTheme = splitTheme;
	}

	/**
	 * @return the theme
	 */
	@IncludeJsOption
	public Theme getTheme() {
		return theme;
	}

	/**
	 * @param theme the theme to set
	 */
	public void setTheme(Theme theme) {
		if (theme.equals(theme))
			requireRedraw();
		this.theme = theme;
	}

	/**
	 * @return the icon
	 */
	@IncludeJsOption
	public Icon getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(Icon icon) {
		if (icon.equals(icon))
			requireRedraw();
		this.icon = icon;
	}

	/**
	 * @return the splitIcon
	 */
	@IncludeJsOption
	public Icon getSplitIcon() {
		return splitIcon;
	}

	/**
	 * @param splitIcon the splitIcon to set
	 */
	public void setSplitIcon(Icon splitIcon) {
		if (splitIcon.equals(splitIcon))
			requireRedraw();
		this.splitIcon = splitIcon;
	}

}
