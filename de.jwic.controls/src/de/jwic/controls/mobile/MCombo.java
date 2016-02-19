package de.jwic.controls.mobile;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.combo.Combo;

/**
 * 
 * The MCombo offers the same functionality as the base Combo, but renders as a
 * basic HTML combo that inherits the default jQuery Mobile
 * 
 * @author vedad
 *
 */
@JavaScriptSupport
public class MCombo extends Combo<Object> {

	private static final long serialVersionUID = 1L;
	
//	private List<ISelectElement> elements = null;
	private boolean defaults = false;
	private boolean enhanced = false;
	private boolean enabled = true;
//	private boolean autodividers = false;
//	private boolean hideDividers = false;
//	private boolean inset = false;
//	private boolean filter = false;
	private boolean filterReveal = false;
//	private Icon splitIcon = Icon.CARATR;
//	private Icon icon = Icon.CARATR;
//	private Theme dividerTheme = null;
	private Theme filterTheme = null;
//	private Theme splitTheme = null;
//	private Theme theme = null;
	private String input = null;
	private String filterPlaceholder = "Filter Items...";
	private String children = null; 

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
	
//	/**
//	 * Add an element.
//	 * @param element
//	 */
//	public void addElement(ISelectElement element) {
//		if (elements == null) {
//			elements = new ArrayList<ISelectElement>();
//		}
//		elements.add(element);
//	}
//
//	/**
//	 * Add an element. The key will automatically be assigned.
//	 * @param title
//	 */
//	public ISelectElement addElement(String title) {
//		SelectElement elm = new SelectElement(title);
//		addElement(elm);
//		return elm;
//	}
//	
//	/**
//	 * Add the element with a custom key.
//	 * @param title
//	 * @param key
//	 */
//	public ISelectElement addElement(String title, String key) {
//		SelectElement elm = new SelectElement(title, key);
//		addElement(elm);
//		return elm;
//	}
//
//	/**
//	 * @return the elements
//	 */
//	@IncludeJsOption
//	public List<ISelectElement> getElements() {
//		return elements;
//	}
//
//	/**
//	 * @param elements the elements to set
//	 */
//	public void setElements(List<ISelectElement> elements) {
//		this.elements = elements;
//	}

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
	 * @return the enhanced
	 */
	@IncludeJsOption
	public boolean isEnhanced() {
		return enhanced;
	}

	/**
	 * @param enhanced the enhanced to set
	 */
	public void setEnhanced(boolean enhanced) {
		if (enhanced != this.enhanced)
			requireRedraw();
		this.enhanced = enhanced;
	}

	/**
	 * @return the filterReveal
	 */
	@IncludeJsOption
	public boolean isFilterReveal() {
		return filterReveal;
	}

	/**
	 * @param filterReveal the filterReveal to set
	 */
	public void setFilterReveal(boolean filterReveal) {
		if (filterReveal != this.filterReveal)
			requireRedraw();
		this.filterReveal = filterReveal;
	}

	/**
	 * @return the input
	 */
	@IncludeJsOption
	public String getInput() {
		return input;
	}

	/**
	 * @param input the input to set
	 */
	public void setInput(String input) {
		if (input.equals(this.input))
			requireRedraw();
		this.input = input;
	}

	/**
	 * @return the filterPlaceholder
	 */
	@IncludeJsOption
	public String getFilterPlaceholder() {
		return filterPlaceholder;
	}

	/**
	 * @param filterPlaceholder the filterPlaceholder to set
	 */
	public void setFilterPlaceholder(String filterPlaceholder) {
		if (filterPlaceholder.equals(this.filterPlaceholder))
			requireRedraw();
		this.filterPlaceholder = filterPlaceholder;
	}

	/**
	 * @return the enabled
	 */
	@IncludeJsOption
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		if (enabled != this.enabled)
			requireRedraw();
		this.enabled = enabled;
	}

//	/**
//	 * @return the autodividers
//	 */
//	@IncludeJsOption
//	public boolean isAutodividers() {
//		return autodividers;
//	}
//
//	/**
//	 * @param autodividers the autodividers to set
//	 */
//	public void setAutodividers(boolean autodividers) {
//		if (autodividers != this.autodividers)
//			requireRedraw();
//		this.autodividers = autodividers;
//	}
//
//	/**
//	 * @return the hideDividers
//	 */
//	@IncludeJsOption
//	public boolean isHideDividers() {
//		return hideDividers;
//	}
//
//	/**
//	 * @return the filter
//	 */
//	@IncludeJsOption
//	public boolean isFilter() {
//		return filter;
//	}
//
//	/**
//	 * @param filter the filter to set
//	 */
//	public void setFilter(boolean filter) {
//		if (filter != this.filter)
//			requireRedraw();
//		this.filter = filter;
//	}
//
//	/**
//	 * @param hideDividers the hideDividers to set
//	 */
//	public void setHideDividers(boolean hideDividers) {
//		if (hideDividers != this.hideDividers)
//			requireRedraw();
//		this.hideDividers = hideDividers;
//	}
//
//	/**
//	 * @return the inset
//	 */
//	@IncludeJsOption
//	public boolean isInset() {
//		return inset;
//	}
//
//	/**
//	 * @param inset the inset to set
//	 */
//	public void setInset(boolean inset) {
//		if (inset != this.inset)
//			requireRedraw();
//		this.inset = inset;
//	}
//
//	/**
//	 * @return the splitIcon
//	 */
//	@IncludeJsOption
//	public Icon getSplitIcon() {
//		return splitIcon;
//	}
//
//	/**
//	 * @param splitIcon the splitIcon to set
//	 */
//	public void setSplitIcon(Icon splitIcon) {
//		if (splitIcon.equals(this.splitIcon))
//			requireRedraw();
//		this.splitIcon = splitIcon;
//	}
//
//	/**
//	 * @return the icon
//	 */
//	@IncludeJsOption
//	public Icon getIcon() {
//		return icon;
//	}

	/**
	 * @return the children
	 */
	@IncludeJsOption
	public String getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(String children) {
		if (children.equals(this.children))
			requireRedraw();
		this.children = children;
	}

//	/**
//	 * @param icon the icon to set
//	 */
//	public void setIcon(Icon icon) {
//		if (icon.equals(this.icon))
//			requireRedraw();
//		this.icon = icon;
//	}
//
//	/**
//	 * @return the dividerTheme
//	 */
//	@IncludeJsOption
//	public Theme getDividerTheme() {
//		return dividerTheme;
//	}
//
//	/**
//	 * @param dividerTheme the dividerTheme to set
//	 */
//	public void setDividerTheme(Theme dividerTheme) {
//		if (dividerTheme.equals(this.dividerTheme))
//			requireRedraw();
//		this.dividerTheme = dividerTheme;
//	}
//
	/**
	 * @return the filterTheme
	 */
	@IncludeJsOption
	public Theme getFilterTheme() {
		return filterTheme;
	}

	/**
	 * @param filterTheme the filterTheme to set
	 */
	public void setFilterTheme(Theme filterTheme) {
		if (filterTheme.equals(this.filterTheme))
			requireRedraw();
		this.filterTheme = filterTheme;
	}
//
//	/**
//	 * @return the splitTheme
//	 */
//	@IncludeJsOption
//	public Theme getSplitTheme() {
//		return splitTheme;
//	}
//
//	/**
//	 * @param splitTheme the splitTheme to set
//	 */
//	public void setSplitTheme(Theme splitTheme) {
//		if (splitTheme.equals(this.splitTheme))
//			requireRedraw();
//		this.splitTheme = splitTheme;
//	}
//
//	/**
//	 * @return the theme
//	 */
//	@IncludeJsOption
//	public Theme getTheme() {
//		return theme;
//	}
//
//	/**
//	 * @param theme the theme to set
//	 */
//	public void setTheme(Theme theme) {
//		if (theme.equals(this.theme))
//			requireRedraw();
//		this.theme = theme;
//	}

}
