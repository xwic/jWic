package de.jwic.mobile.demos;

import java.util.ArrayList;
import java.util.List;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.mobile.MobileDemoModule;
import de.jwic.mobile.controls.mlistview.ListViewElement;
import de.jwic.mobile.controls.mlistview.IListElementRenderer;
import de.jwic.mobile.controls.mlistview.MListView;
import de.jwic.mobile.controls.mlistview.MListViewDataModel;

public class MListViewDemo extends MobileDemoModule {

	public MListViewDemo() {
		super("ListView Demo");
	}

	@Override
	public Control createPage(IControlContainer controlContainer) {
		final ControlContainer container = new ControlContainer(controlContainer);

		final MListView<String> mListView = new MListView<String>(container, "mListView");
		mListView.setModel(createModel());
		mListView.setText("Mlist view example");

		return container;
	}

	private MListViewDataModel<String> createModel() {

		IListElementRenderer<String> renderer = new IListElementRenderer<String>() {

			@Override
			public String render(String element) {
				return element;
			}
		};
		MListViewDataModel<String> model = new MListViewDataModel<String>(renderer);
		model.setElements(createElements());

		return model;
	}

	private List<ListViewElement<String>> createElements() {
		List<ListViewElement<String>> list = new ArrayList<ListViewElement<String>>();
		list.add(createListViewElement("First item"));
		list.add(createListViewElement("Second item"));
		return list;
	}

	private ListViewElement<String> createListViewElement(String string) {
		ListViewElement<String> element = new ListViewElement<String>(string);
		element.addTextForFilter(string);

		return element;
	}
}