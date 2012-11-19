package de.jwic.ecolib.controls.menu;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;

import de.jwic.base.ImageRef;
import de.jwic.ecolib.actions.IAction;

/**
 * 
 * @author Mark Frewin
 *
 */
public class MenuItem implements Serializable, IAction {

	private static final long serialVersionUID = -1474226144475320771L;
	
	public final static int STYLE_ACTION = 0;
	public final static int STYLE_CASCADE = 1;
	public final static int STYLE_SEPARATOR = 2;
	
	private int style = 0;
	private boolean enabled = true;
	private boolean visible = true;
	private ImageRef iconEnabled = null;
	private ImageRef iconDisabled = null;
	private String title = null;
	private Menu menu = null;
	private IAction action;

	private String id = null;
	private long uniqueId = (new Date()).getTime();
	private PropertyChangeSupport pceListeners = new PropertyChangeSupport(this);
	

	/**
	 * 
	 * @param menu
	 * @param style
	 */
	public MenuItem(Menu menu, int style) {
		menu.addMenuItem(this);
		this.style = style;		
	}
	
	/**
	 * 
	 * @param menu
	 * @param style
	 */
	public MenuItem(Menu menu, int style, String title) {
		this(menu, style);
		this.title = title;
	}

	/**
	 * 
	 * @param menu
	 * @param action
	 */
	public MenuItem(Menu menu, IAction action) {
		menu.addMenuItem(this);
		this.action = action;
		this.style = STYLE_ACTION;		
	}
	
	/**
	 * 
	 * @param menu
	 * @param action
	 */
	public MenuItem(Menu menu, IAction action, String title) {
		this(menu, action);
		this.title = title;
	}

	/**
	 * Getter for UniqueId.
	 * @return
	 */
	public long getUniqueId() {
		return uniqueId;
	}
	
	/**
	 * Set enabled state.
	 * @param enabled
	 */
	public void setEnabled(boolean enabled) {
		boolean oldValue = this.enabled;
		this.enabled = enabled;
		
		pceListeners.firePropertyChange("enabled", oldValue, enabled);
	}
	
	/**
	 * Set visibility state.
	 * @
	 * param visible
	 */
	public void setVisible(boolean visible) {
		boolean oldValue = this.visible;
		this.visible = visible;
		
		pceListeners.firePropertyChange("visible", oldValue, visible);
	}

	/**
	 * Getter for style.
	 * @return
	 */
	public int getStyle() {
		return style;
	}

	/**
	 * Set style.
	 * @param style
	 */
	public void setStyle(int style) {
		this.style = style;
	}

	/*
	 *  (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#getTitle()
	 */
	public String getTitle() {
		if (title == null) {
			if (action != null)
				return action.getTitle();
			else
				return "Untitled" + id;
		}

		return title;
	}

	/**
	 * Set title.
	 * @param title
	 */
	public void setTitle(String title) {		
		this.title = title;
	}

	/*
	 *  (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
		pceListeners.addPropertyChangeListener(listener);		
	}

	/*
	 *  (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
		pceListeners.removePropertyChangeListener(listener);
		
	}

	/*
	 *  (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#isVisible()
	 */
	public boolean isVisible() {		
		return visible;
	}

	/*
	 *  (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		if (action != null) {
			action.run();
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#isEnabled()
	 */
	public boolean isEnabled() {		
		return enabled;
	}

	/**
	 * 
	 * @param menu
	 */
	public void setMenu(Menu menu) {		
		this.menu = menu;
		menu.updateIds(id);
	}
	
	/**
	 * 
	 * @return
	 */
	public Menu getMenu(){
		return menu;
	}

	/**
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 
	 * @param id
	 */
	void setId(String id) {
		this.id = id;
		if (menu != null) {
			menu.updateIds(id);
		}
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#getIconEnabled()
	 */
	public ImageRef getIconEnabled() {
		return iconEnabled;
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#setIconEnabled(de.jwic.base.ImageRef)
	 */
	public void setIconEnabled(ImageRef iconEnabled) {
		this.iconEnabled = iconEnabled;
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#getIconDisabled()
	 */
	public ImageRef getIconDisabled() {
		if(iconDisabled == null){
			return iconEnabled;
		}
		
		return iconDisabled;
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#setIconDisabled(de.jwic.base.ImageRef)
	 */
	public void setIconDisabled(ImageRef iconDisabled) {
		this.iconDisabled = iconDisabled;
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#getTooltip()
	 */
	public String getTooltip() {
		return "";
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.actions.IAction#setTooltip(java.lang.String)
	 */
	public void setTooltip(String tooltip) {
	}
}
