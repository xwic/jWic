package de.jwic.mobile;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.mobile.common.clickable.ClickListener;
import de.jwic.mobile.common.clickable.Clickable;
import de.jwic.mobile.controls.Button;
import de.jwic.mobile.controls.Page;
import de.jwic.mobile.controls.Panel;

import java.util.List;

/**
 * Created by boogie on 10/29/14.
 */
public class MobileDemoPage extends Page{
	private Control currentPage;

	public MobileDemoPage(IControlContainer container, String name, final List<MobileDemoModule> modules) {
		super(container, name);
		this.setTemplateName(MobileDemoPage.class.getName());
		final ControlContainer contentContainer = new ControlContainer(this, "content");

		final Panel menu = new Panel(this.getHeaderContainer(), "menu");
		menu.setText("Open Menu");

		for(int i =0; i<modules.size(); i++){
			final Button button = new Button(menu, "module" + i);
			final MobileDemoModule module = modules.get(i);

			button.setText(module.getTitle());
			button.addClickListener(new ClickListener() {

				@Override
				public void onClick(Clickable source) {
					createPage(contentContainer, module);
					menu.setToggled(false);
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
