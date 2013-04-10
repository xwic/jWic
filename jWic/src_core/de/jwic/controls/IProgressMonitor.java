package de.jwic.controls;

import de.jwic.events.ValueChangedListener;

/**
 * Used to monitor the progress of an operation. Monitors are used by controls like the
 * ProgressMonitor to visualise the progress of an operation.
 *  
 * @author Lippisch
 */
public interface IProgressMonitor {

	/**
	 * Add a ValueChangedListener. The listener must be invoked when the
	 * value has been changed.
	 * @param listener
	 */
	public void addValueChangedListener(ValueChangedListener listener);
	
	/**
	 * Removes the specified listener.
	 * @param listener
	 */
	public void removeValueChangedListener(ValueChangedListener listener);
	
	/**
	 * Returns the maximum value that the monitor can reach.
	 * @return
	 */
	public int getMaximum();
	
	/**
	 * Returns the minium value that the progress starts with.
	 * @return
	 */
	public int getMinimum();
	
	/**
	 * Returns the current progress value.
	 * @return
	 */
	public int getValue();
	
	/**
	 * Returns an info text.
	 * @return
	 */
	public String getInfoText();
	
}
