/**
 * 
 */
package de.jwic.demo.wizard;

/**
 * Simple listener pattern (without event object) for the list to listen to actions triggered
 * by the editor itself.
 * 
 * @author lippisch
 */
public interface IPageEditorControlObserver {

	public void onDeletion(PageEditorControl control);
	
}
