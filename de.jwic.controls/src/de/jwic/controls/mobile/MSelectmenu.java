package de.jwic.controls.mobile;

import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.base.IncludeJsOption;
import de.jwic.controls.combo.DropDown;
import de.jwic.data.ISelectElement;

/**
 * Select Menu control. Works same as Drop Down control. The content may either be added directly, to be
 * stored in the control or it is provided by a custom IContentProvider.
 * 
 * @author vedad
 *
 */
public class MSelectmenu extends DropDown {

	private static final long serialVersionUID = 1L;
	
	private String closeText = "Close";
	private boolean corners = true;
	private boolean defaults = false;
	private boolean inline = false;
	private boolean mini = false;
	private boolean nativeMenu = true;
	private boolean preventFocusZoom = true;
	private boolean shadow = true;
	private Theme theme = null;
	private Theme dividerTheme = null;
	private Theme overlayTheme = null;
	private boolean hidePlaceholderMenuItems = false;
	private Icon icon = Icon.CARATD;
	private IconPos iconpos = IconPos.RIGHT;
	

	/**
	 * Constructs a new control instance and adds it to the specified container with the specified name. If the name is <code>null</code>, a
	 * unique name will be chosen by the container.
	 * 
	 * @param container
	 * @param name
	 */
	public MSelectmenu(IControlContainer container, String name) {
		super(container, name);
		setTemplateName(MSelectmenu.class.getName());
	}
	
	/**
	 * Returns all the elements of select menu	
	 */
	@IncludeJsOption
	public List<ISelectElement> getElements() {
		return elements;
	}


	/**
	 * Customizes the text of the close button which is helpful for translating this into different languages. 
	 * The close button is displayed as an icon-only button by default so the text isn't visible on-screen, 
	 * but is read by screen readers so this is an important accessibility feature.
	 */
	@IncludeJsOption
	public String getCloseText() {
		return closeText;
	}


	/**
	 * Customizes the text of the close button which is helpful for translating this into different languages. 
	 * The close button is displayed as an icon-only button by default so the text isn't visible on-screen, 
	 * but is read by screen readers so this is an important accessibility feature.
	 */
	public void setCloseText(String closeText) {
		if (closeText.equals(closeText))
			requireRedraw();
		this.closeText = closeText;
	}


	/**
	 * Applies the theme button border-radius to the select button if set to true.
	 */
	@IncludeJsOption
	public boolean isCorners() {
		return corners;
	}


	/**
	 * Applies the theme button border-radius to the select button if set to true.
	 */
	public void setCorners(boolean corners) {
		if (corners != this.corners)
			requireRedraw();
		this.corners = corners;
	}


	/**
	 * Seting this option to true indicates that other widgets options have default values and causes jQuery Mobile's widget autoenhancement code to omit the step 
	 * where it retrieves option values from data attributes. This can improve startup time.
	 */
	@IncludeJsOption
	public boolean isDefaults() {
		return defaults;
	}


	/**
	 * Seting this option to true indicates that other widgets options have default values and causes jQuery Mobile's widget autoenhancement code to omit the step 
	 * where it retrieves option values from data attributes. This can improve startup time.
	 */
	public void setDefaults(boolean defaults) {
		if (defaults != this.defaults)
			requireRedraw();
		this.defaults = defaults;
	}


	/**
	 * If set to true, this will make the select button act like an inline button so the width is determined by the button's text. 
	 * By default, this is null (false) so the select button is full width, regardless of the feedback content.
	 */
	@IncludeJsOption
	public boolean isInline() {
		return inline;
	}


	/**
	 * If set to true, this will make the select button act like an inline button so the width is determined by the button's text. 
	 * By default, this is null (false) so the select button is full width, regardless of the feedback content.
	 */
	public void setInline(boolean inline) {
		if (inline != this.inline)
			requireRedraw();
		this.inline = inline;
	}


	/**
	 * If set to true, this will display a more compact version of the selectmenu that uses less vertical height by applying the ui-mini class to the outermost element of the selectmenu widget.
	 */
	@IncludeJsOption
	public boolean isMini() {
		return mini;
	}


	/**
	 * If set to true, this will display a more compact version of the selectmenu that uses less vertical height by applying the ui-mini class to the outermost element of the selectmenu widget.
	 */
	public void setMini(boolean mini) {
		if (mini != this.mini)
			requireRedraw();
		this.mini = mini;
	}


	/**
	 * When set to true, clicking the custom-styled select menu will open the native select menu which is best for performance. 
	 * If set to false, the custom select menu style will be used instead of the native menu.
	 */
	@IncludeJsOption
	public boolean isNativeMenu() {
		return nativeMenu;
	}


	/**
	 * When set to true, clicking the custom-styled select menu will open the native select menu which is best for performance. 
	 * If set to false, the custom select menu style will be used instead of the native menu.
	 */
	public void setNativeMenu(boolean nativeMenu) {
		if (nativeMenu != this.nativeMenu)
			requireRedraw();
		this.nativeMenu = nativeMenu;
	}


	/**
	 * This option disables page zoom temporarily when a custom select is focused, which prevents iOS devices from zooming the page into the select. 
	 * By default, iOS often zooms into form controls, and the behavior is often unnecessary and intrusive in mobile-optimized layouts.
	 */
	@IncludeJsOption
	public boolean isPreventFocusZoom() {
		return preventFocusZoom;
	}


	/**
	 * This option disables page zoom temporarily when a custom select is focused, which prevents iOS devices from zooming the page into the select. 
	 * By default, iOS often zooms into form controls, and the behavior is often unnecessary and intrusive in mobile-optimized layouts.
	 */
	public void setPreventFocusZoom(boolean preventFocusZoom) {
		if (preventFocusZoom != this.preventFocusZoom)
			requireRedraw();
		this.preventFocusZoom = preventFocusZoom;
	}


	/**
	 * Applies the drop shadow style to the select button if set to true.
	 */
	@IncludeJsOption
	public boolean isShadow() {
		return shadow;
	}


	/**
	 * Applies the drop shadow style to the select button if set to true.
	 */
	public void setShadow(boolean shadow) {
		if (shadow != this.shadow)
			requireRedraw();
		this.shadow = shadow;
	}


	/**
	 * Sets the color scheme (swatch) for the selectmenu widget. It accepts a single letter from a-z that maps to the swatches included in your theme.
	 */
	@IncludeJsOption
	public Theme getTheme() {
		return theme;
	}


	/**
	 * Sets the color scheme (swatch) for the selectmenu widget. It accepts a single letter from a-z that maps to the swatches included in your theme.
	 */
	public void setTheme(Theme theme) {
		if (theme.equals(theme))
			requireRedraw();
		this.theme = theme;
	}


	/**
	 * Sets the color scheme (swatch) for the listview dividers that represent the optgroup headers. It accepts a single letter from a-z that maps to the swatches included in your theme.
	 */
	@IncludeJsOption
	public Theme getDividerTheme() {
		return dividerTheme;
	}


	/**
	 * Sets the color scheme (swatch) for the listview dividers that represent the optgroup headers. It accepts a single letter from a-z that maps to the swatches included in your theme.
	 */
	public void setDividerTheme(Theme dividerTheme) {
		if (dividerTheme.equals(dividerTheme))
			requireRedraw();
		this.dividerTheme = dividerTheme;
	}


	/**
	 * Sets the color of the overlay layer for the dialog-based custom select menus and the outer border of the smaller custom menus. 
	 * It accepts a single letter from a-z that maps to the swatches included in your theme. 
	 * By default, the content block colors for the overlay will be inherited from the parent of the select.
	 */
	@IncludeJsOption
	public Theme getOverlayTheme() {
		return overlayTheme;
	}


	/**
	 * Sets the color of the overlay layer for the dialog-based custom select menus and the outer border of the smaller custom menus. 
	 * It accepts a single letter from a-z that maps to the swatches included in your theme. 
	 * By default, the content block colors for the overlay will be inherited from the parent of the select.
	 */
	public void setOverlayTheme(Theme overlayTheme) {
		if (overlayTheme.equals(overlayTheme))
			requireRedraw();
		this.overlayTheme = overlayTheme;
	}


	/**
	 * Sets whether placeholder menu items are hidden. When true, the menu item used as the placeholder for the select menu widget will not appear in the list of choices.
	 */
	@IncludeJsOption
	public boolean isHidePlaceholderMenuItems() {
		return hidePlaceholderMenuItems;
	}


	/**
	 * Sets whether placeholder menu items are hidden. When true, the menu item used as the placeholder for the select menu widget will not appear in the list of choices.
	 */
	public void setHidePlaceholderMenuItems(boolean hidePlaceholderMenuItems) {
		if (hidePlaceholderMenuItems != this.hidePlaceholderMenuItems)
			requireRedraw();
		this.hidePlaceholderMenuItems = hidePlaceholderMenuItems;
	}


	/**
	 * Replaces the default icon "carat-d" with an icon from the icon set. Setting this attribute to "false" suppresses the icon.
	 */
	@IncludeJsOption
	public Icon getIcon() {
		return icon;
	}


	/**
	 * Replaces the default icon "carat-d" with an icon from the icon set. Setting this attribute to "false" suppresses the icon.
	 */
	public void setIcon(Icon icon) {
		if (icon.equals(icon))
			requireRedraw();
		this.icon = icon;
	}


	/**
	 * Position of the icon in the select button. Possible values: left, right, top, bottom, notext. The notext value will display the select as an icon-only button with no text feedback.
	 */
	@IncludeJsOption
	public IconPos getIconpos() {
		return iconpos;
	}


	/**
	 * Position of the icon in the select button. Possible values: left, right, top, bottom, notext. The notext value will display the select as an icon-only button with no text feedback.
	 */
	public void setIconpos(IconPos iconpos) {
		if (iconpos.equals(iconpos))
			requireRedraw();
		this.iconpos = iconpos;
	}

}
