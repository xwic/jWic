/**
 * 
 */
package de.jwic.demo.wizard;

import java.util.ArrayList;
import java.util.List;

import de.jwic.base.IControlContainer;
import de.jwic.controls.ScrollableContainer;
import de.jwic.controls.wizard.ValidationException;

/**
 * @author lippisch
 *
 */
public class PageConfigListControl extends ScrollableContainer implements IPageEditorControlObserver {

	private List<PageEditorControl> editors = new ArrayList<PageEditorControl>();
	
	/**
	 * @param container
	 * @param name
	 */
	public PageConfigListControl(IControlContainer container, String name) {
		super(container, name);

	}

	
	/**
	 * Add another page.
	 * @param config
	 */
	public void addEditor(WizardPageConfig config) {
		
		PageEditorControl editor = new PageEditorControl(this, null, config, editors.size() + 1);
		editor.setObserver(this);
		editors.add(editor);
		
	}
	
	/**
	 * @return the editors
	 */
	public List<PageEditorControl> getEditors() {
		return editors;
	}


	/* (non-Javadoc)
	 * @see de.jwic.demo.wizard.IPageEditorControlObserver#onDeletion(de.jwic.demo.wizard.PageEditorControl)
	 */
	@Override
	public void onDeletion(PageEditorControl control) {
		
		editors.remove(control);
		control.destroy(); // 
		// re-index
		int nr = 1;
		for (PageEditorControl ctrl : editors) {
			ctrl.setPageNumber(nr++);
		}
		
	}
	
	public void validate() throws ValidationException {
		
		List<String> allErrors = new ArrayList<String>();
		
		for (PageEditorControl editor : editors) {
			List<String> errors = editor.validate();
			allErrors.addAll(errors);
		}
		
		if (allErrors.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (String s : allErrors) {
				sb.append(s).append("<br>");
			}
			throw new ValidationException("Please resolve the following issues:<br>" + sb);
		}
		
	}
	
	/**
	 * Return the list of all configurations.
	 * @return
	 */
	public List<WizardPageConfig> getPageConfigs() {
		
		List<WizardPageConfig> list = new ArrayList<WizardPageConfig>();
		for (PageEditorControl editor : editors) {
			list.add(editor.getPageConfig());
		}
		return list;
		
	}

}
