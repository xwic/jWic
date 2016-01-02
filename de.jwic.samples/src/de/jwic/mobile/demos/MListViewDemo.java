package de.jwic.mobile.demos;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.mobile.MobileDemoModule;
import de.jwic.mobile.controls.mlistview.MListView;

public class MListViewDemo extends MobileDemoModule {

	public MListViewDemo() {
		super("ListView Demo");
	}

	@Override
	public Control createPage(IControlContainer controlContainer) {
		final ControlContainer container = new ControlContainer(
				controlContainer);

		final MListView mListView = new MListView(container, "mListView");

		mListView.setText("mlist view example");

		return container;
	}
}