/*
 * Created on 07.11.2004
 */
package de.jwic.controls;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;


/**
 * This control renders a listbox using the HTML select tag.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.7 $
 */
@JavaScriptSupport
public class ListBoxControl extends ListControl {

	private static final long serialVersionUID = 2L;
	
	private boolean multiple = false;
	private String confirmMsg = "";
	protected int size = 1;
	
	protected List<SelectionListener> listeners = null;
	
	/**
	 * @param container
	 */
	public ListBoxControl(IControlContainer container) {
		this(container, null);
	}
	/**
	 * @param container
	 * @param name
	 */
	public ListBoxControl(IControlContainer container, String name) {
		super(container, name);
		setCssClass("default_listbox");
	}

	/**
	 * Register a listener that will be notified when the listbox has been
	 * selected (i.e. doubleClick). The event is only triggered if the
	 * selectionMode property is not NO_SELECTION.
	 * @param listener
	 */
	public void addSelectionListener(SelectionListener listener) {
		if (listeners == null) {
			listeners = new ArrayList<SelectionListener>();
		}
		listeners.add(listener);
	}
	
	/**
	 * Removes the specified listener.
	 * @param listener
	 */
	public void removeSelectionListener(SelectionListener listener) {
		if (listeners != null) {
			listeners.remove(listener);
		}
	}
	
	/**
	 * Send the element selected event to the registered listeners.
	 * Defaults dblClick to false.
	 */
	protected void sendSelectionEvent() {
		sendSelectionEvent(false);
	}
	
	/**
	 * Send the element selected event to the registerd listeners.
	 * @param dblClick
	 */
	protected void sendSelectionEvent(boolean dblClick) {
		
		if (listeners != null) {
			SelectionEvent e = new SelectionEvent(this, dblClick);
			for (Iterator<SelectionListener> it = listeners.iterator(); it.hasNext(); ) {
				SelectionListener osl = it.next();
				osl.objectSelected(e);
			}
		}

	}
	
	/**
	 * The action method that handles if the user selects the control, depending on
	 * how the selectionMode is set. 
	 */
	public void actionSelected(String type) {
		sendSelectionEvent("dblClick".equals(type));
	}

	/**
	 * @return Returns the multiple.
	 */
	public boolean isMultiple() {
	    return multiple;
	}
	/**
	 * @param multiple The multiple to set.
	 */
	public void setMultiple(boolean multiple) {
		if (this.multiple && !multiple) {
			// changed from multi-selection to single selection
			if (field.getValues().length > 1) { 
				// if more then one entry is selected, select the first one
				field.setValue(field.getValues()[0]);
			}
		}
	    this.multiple = multiple;
	    
	    requireRedraw();
	}
	/**
	 * @return Returns the confirmMsg.
	 */
	public String getConfirmMsg() {
		return confirmMsg;
	}
	/**
	 * If the confirm message is not an empty string, the user will be prompted with a  
	 * message box and the specified message. He may then answer 'ok' or 'cancel'. If the
	 * user presses ok, the change will be done. Otherwise the old value is restored 
	 * and no event happens.
	 * 
	 * @param confirmMsg The confirmMsg to set.
	 */
	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
		requireRedraw();
	}
	
	/**
	 * @return Returns the size.
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Set the size of the control. A value of 1 will display a combo-box, other
	 * values display a list. 
	 *
	 * @param size The size to set.
	 */
	public void setSize(int size) {
		this.size = size;
		requireRedraw();
	}

}
