package de.jwic.mobile;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.mobile.MButton;
import de.jwic.controls.mobile.MPage;
import de.jwic.controls.mobile.MPanel;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

import java.util.List;

/**
 * Created by boogie on 10/29/14.
 */
public class MobileDemoPage extends MPage {
	private Control currentPage;

	public MobileDemoPage(IControlContainer container, String name, final List<MobileDemoModule> modules) {
		super(container, name);
		this.setTemplateName(MobileDemoPage.class.getName());
		final ControlContainer contentContainer = new ControlContainer(this, "content");

		final MPanel menu = new MPanel(this.getHeaderContainer(), "menu");
		menu.setTitle("Open Menu");
		//menu.setPosition(MPanel.Position.RIGHT);
		menu.setDisplay(MPanel.Display.OVERLAY);

		for(int i =0; i<modules.size(); i++){
			final MButton MButton = new MButton(menu, "module" + i);
			final MobileDemoModule module = modules.get(i);

			MButton.setTitle(module.getTitle());
			MButton.addSelectionListener(new SelectionListener() {

				@Override
				public void objectSelected(SelectionEvent event) {
					createPage(contentContainer, module);
					menu.close();
				}
			});
		}

		createPage(contentContainer, modules.get(0));
	}

	private void createPage(ControlContainer contentContainer, MobileDemoModule module) {
		if(currentPage != null){
			contentContainer.removeControl(currentPage.getName());
		}
		currentPage = module.createPage(contentContainer);
		setText(module.getTitle());
	}

}
