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
	
	@IncludeJsOption
	public List<ISelectElement> getElements() {
		return super.elements;
	}


	/**
	 * @return the closeText
	 */
	@IncludeJsOption
	public String getCloseText() {
		return closeText;
	}


	/**
	 * @param closeText the closeText to set
	 */
	public void setCloseText(String closeText) {
		if (closeText.equals(closeText))
			requireRedraw();
		this.closeText = closeText;
	}


	/**
	 * @return the corners
	 */
	@IncludeJsOption
	public boolean isCorners() {
		return corners;
	}


	/**
	 * @param corners the corners to set
	 */
	public void setCorners(boolean corners) {
		if (corners != this.corners)
			requireRedraw();
		this.corners = corners;
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
	 * @return the inline
	 */
	@IncludeJsOption
	public boolean isInline() {
		return inline;
	}


	/**
	 * @param inline the inline to set
	 */
	public void setInline(boolean inline) {
		if (inline != this.inline)
			requireRedraw();
		this.inline = inline;
	}


	/**
	 * @return the mini
	 */
	@IncludeJsOption
	public boolean isMini() {
		return mini;
	}


	/**
	 * @param mini the mini to set
	 */
	public void setMini(boolean mini) {
		if (mini != this.mini)
			requireRedraw();
		this.mini = mini;
	}


	/**
	 * @return the nativeMenu
	 */
	@IncludeJsOption
	public boolean isNativeMenu() {
		return nativeMenu;
	}


	/**
	 * @param nativeMenu the nativeMenu to set
	 */
	public void setNativeMenu(boolean nativeMenu) {
		if (nativeMenu != this.nativeMenu)
			requireRedraw();
		this.nativeMenu = nativeMenu;
	}


	/**
	 * @return the preventFocusZoom
	 */
	@IncludeJsOption
	public boolean isPreventFocusZoom() {
		return preventFocusZoom;
	}


	/**
	 * @param preventFocusZoom the preventFocusZoom to set
	 */
	public void setPreventFocusZoom(boolean preventFocusZoom) {
		if (preventFocusZoom != this.preventFocusZoom)
			requireRedraw();
		this.preventFocusZoom = preventFocusZoom;
	}


	/**
	 * @return the shadow
	 */
	@IncludeJsOption
	public boolean isShadow() {
		return shadow;
	}


	/**
	 * @param shadow the shadow to set
	 */
	public void setShadow(boolean shadow) {
		if (shadow != this.shadow)
			requireRedraw();
		this.shadow = shadow;
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
	 * @return the overlayTheme
	 */
	@IncludeJsOption
	public Theme getOverlayTheme() {
		return overlayTheme;
	}


	/**
	 * @param overlayTheme the overlayTheme to set
	 */
	public void setOverlayTheme(Theme overlayTheme) {
		if (overlayTheme.equals(overlayTheme))
			requireRedraw();
		this.overlayTheme = overlayTheme;
	}


	/**
	 * @return the hidePlaceholderMenuItems
	 */
	@IncludeJsOption
	public boolean isHidePlaceholderMenuItems() {
		return hidePlaceholderMenuItems;
	}


	/**
	 * @param hidePlaceholderMenuItems the hidePlaceholderMenuItems to set
	 */
	public void setHidePlaceholderMenuItems(boolean hidePlaceholderMenuItems) {
		if (hidePlaceholderMenuItems != this.hidePlaceholderMenuItems)
			requireRedraw();
		this.hidePlaceholderMenuItems = hidePlaceholderMenuItems;
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
	 * @return the iconpos
	 */
	@IncludeJsOption
	public IconPos getIconpos() {
		return iconpos;
	}


	/**
	 * @param iconpos the iconpos to set
	 */
	public void setIconpos(IconPos iconpos) {
		if (iconpos.equals(iconpos))
			requireRedraw();
		this.iconpos = iconpos;
	}

}
