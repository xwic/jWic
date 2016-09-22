/**
 * 
 */
package de.jwic.mobile.demos;

import org.w3c.dom.ls.LSInput;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Label;
import de.jwic.controls.mobile.Icon;
import de.jwic.controls.mobile.IconPos;
import de.jwic.controls.mobile.MButton;
import de.jwic.controls.mobile.MListBox;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.mobile.MobileDemoModule;

/**
 * @author vedad
 *
 */
public class ListBoxDemo extends MobileDemoModule {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ListBoxDemo() {
		super("ListBox Demo");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.mobile.MobileDemoModule#createPage(de.jwic.base.
	 * IControlContainer)
	 */
	@Override
	public Control createPage(IControlContainer controlContainer) {
		final ControlContainer container = new ControlContainer(controlContainer, "listboxContainer");

		final MListBox listbox = new MListBox(container, "listbox");
		listbox.addElement("First One", "elm1");
		listbox.addElement("Another One", "elm2");
		listbox.addElement("Last One", "elm3");
		
		final Label iconLbl = new Label(container, "iconLbl");
		iconLbl.setText("Icon:" + listbox.getIcon().getCode());
		
		MButton btnIcon = new MButton(container, "iconBtn");
		btnIcon.setTitle("Change ListBox Icon");
		btnIcon.setInline(true);
		btnIcon.setMini(true);
		btnIcon.addSelectionListener(new SelectionListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void objectSelected(SelectionEvent event) {
				if (listbox.getIcon().equals(Icon.CARATD)){
					listbox.setIcon(Icon.ARROWD);
					iconLbl.setText("Changed Icon! New Icon: " + listbox.getIcon().getCode());
				} else {
					listbox.setIcon(Icon.CARATD);
					iconLbl.setText("Changed Icon! New Icon: " + listbox.getIcon().getCode());
				}
			}
		});
		
		final Label iconposLbl = new Label(container, "iconposLbl");
		iconposLbl.setText("Icon Position: " + listbox.getIconpos().getCode());
		
		MButton btnIconpos = new MButton(container, "iconposBtn");
		btnIconpos.setTitle("Change ListBox Icon Position");
		btnIconpos.setInline(true);
		btnIconpos.setMini(true);
		btnIconpos.addSelectionListener(new SelectionListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void objectSelected(SelectionEvent event) {
				if (listbox.getIconpos().equals(IconPos.RIGHT)) {
					listbox.setIconpos(IconPos.LEFT);
					iconposLbl.setText("Changed Icon Position! New Icon Position: " + listbox.getIconpos().getCode());
				} else {
					listbox.setIconpos(IconPos.RIGHT);
					iconposLbl.setText("Changed Icon Position! New Icon Position: " + listbox.getIconpos().getCode());
				}
			}
		});

		return container;
	}

}
