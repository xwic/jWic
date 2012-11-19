package de.jwic.ecolib.controls.divwin;

import de.jwic.base.ControlContainer;
import de.jwic.base.Field;
import de.jwic.base.IControlContainer;
import de.jwic.base.IOuterLayout;

/**
 * A container that is displayed in a div layer with several designs.</p>
 * 
 * <p>The implementation is based upon the JavaScript window implementation 
 * Prototype Window Class (http://prototype-window.xilinus.com).
 * 
 * @author Mohammed Ataulla
 * @author Florian Lippisch
 */
public class DivPopupWindow extends ControlContainer implements IOuterLayout {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title = "";
	// fields must be used to store the location,
	// because the client window is then able to update the
	// values if the location changes (i.e. user moves the window).
    private Field  height;
    private Field  width;
    private Field  top;
    private Field  left;
    private boolean isModal = true;
    private String cssClass = "mac_os_x";
    private boolean hidden = true;
    
    /**
     * Default Constructor
     * 
     * @param container control's container
     * @param name the control's name
     */
    public DivPopupWindow(IControlContainer container, String name) {
        super(container, name);
        init();
    }
    /**
     * Constructor
     * 
     * @param container control's container
     */
    public DivPopupWindow(IControlContainer container) {
        super(container);
        init();
    }
    /**
     * Initializes the basic properties. 
     *
     */
    private void init() {
		setRendererId("jwic.renderer.OuterContainer");
        title = "";
        
        width = new Field(this, "width");
        height = new Field(this, "height");
        top = new Field(this, "top");
        left = new Field(this, "left");
        
        setWidth(350);
        setHeight(100);
        setTop(-1); // means that the window is centered
        setLeft(-1); // means that the window is centered
    }
    /**
     * Sets the title of the window
     * 
     * @param title the title to be set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Gets the title of the window
     * 
     * @return the title of the window
     */
    public String getTitle() {
        return title;
    }
    /**
     * Sets the height of the window
     * 
     * @param height the height to be set in pixels.
     */
    public void setHeight(int height) {
        this.height.setValue(Integer.toString(height));
    }
    /**
     * Gets the height of the window
     * 
     * @return the height of the window
     */
    public int getHeight() {
    	try {
    		return Integer.parseInt(height.getValue());
    	} catch (NumberFormatException nfe) {
    		return 0;
    	}
    }
    /**
     * Sets the width of the window
     * 
     * @param width the width to be set in pixels
     */
    public void setWidth(int width) {
        this.width.setValue(Integer.toString(width));
    }
    /**
     * Gets the width of the window
     * 
     * @return the width of the window
     */
    public int getWidth() {
    	try {
	        return Integer.parseInt(width.getValue());
		} catch (NumberFormatException nfe) {
			return 0;
		}
    }
	/*
	 *  (non-Javadoc)
	 * @see com.ml.ct.nimble.wtk.control.Control#actionPerformed(java.lang.String, java.lang.String)
	 */
    public void actionPerformed(String actionId, String parameter) {
        if ("close".equals(actionId)) {
            // the popup has been closed using the "close" action
        	hidden = true;
        }
    }
    /*
     *  (non-Javadoc)
     * @see com.ml.ct.nimble.wtk.control.Control#getTemplateName()
     */
	public String getTemplateName() {
		if (super.getTemplateName().equals(DivPopupWindow.class.getName())) {
			return null;
		}
		return super.getTemplateName();
	}

	/*
	 *  (non-Javadoc)
	 * @see com.ml.ct.nimble.wtk.control.OuterLayout#getOuterTemplateName()
	 */
    public String getOuterTemplateName() {
        return DivPopupWindow.class.getName();
    }
    
    /**
     * Whether other components in the back can be accessed.
     * @return Returns the isModal.
     */
    public boolean isModal() {
        return isModal;
    }
    /**
     * Sets whether other components in the back can be accessed.
     * 
     * @param isModal The isModal to set.
     */
    public void setModal(boolean isModal) {
        this.isModal = isModal;
    }

    /**
	 * @return the hidden
	 */
	public boolean isHidden() {
		return hidden;
	}
	
	/**
	 * Show the popup.
	 *
	 */
	public void show() {
		if (hidden) {
			hidden = false;
			requireRedraw();
		}
	}
	
	/**
	 * Hide the popup.
	 */
	public void hide() {
		if (!hidden) {
			hidden = true;
			requireRedraw();
		}
	}
	/**
	 * @return the cssClass
	 */
	public String getCssClass() {
		return cssClass;
	}
	/**
	 * @param cssClass the cssClass to set
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	/**
	 * @return the left
	 */
	public int getLeft() {
		try {
			return Integer.parseInt(left.getValue());
    	} catch (NumberFormatException nfe) {
    		return 0;
    	}
	}
	/**
	 * @param left the left to set
	 */
	public void setLeft(int left) {
		this.left.setValue(Integer.toString(left));
	}
	/**
	 * @return the top
	 */
	public int getTop() {
		try {
			return Integer.parseInt(top.getValue());
    	} catch (NumberFormatException nfe) {
    		return 0;
    	}
	}
	/**
	 * @param top the top to set
	 */
	public void setTop(int top) {
		this.top.setValue(Integer.toString(top));
	}
	
}
