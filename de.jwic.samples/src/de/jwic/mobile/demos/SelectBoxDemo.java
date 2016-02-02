package de.jwic.mobile.demos;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.mobile.MobileDemoModule;

/**
 * Created by boogie on 11/13/14.
 */
public class SelectBoxDemo extends MobileDemoModule{

	public SelectBoxDemo() {
		super("Select box Demo");
	}

	@Override
	public Control createPage(IControlContainer controlContainer) {
		/*final ControlContainer cc = new ControlContainer(controlContainer);
		final MSelectBox<String> selectBox = new MSelectBox<String>(cc, "selectBox", MSelectBox.<String>toStringLabelEvaluator());
		selectBox.addSelection("Foo");
		selectBox.addSelection("Bar");
		selectBox.addSelection("Baz");


		final Label label = new Label(cc, "label");
		final MButton btn = new MButton(cc,"btn");
		btn.addSelectionListener(new SelectionListener() {

			@Override
			public void objectSelected(SelectionEvent event) {
				final String selection = selectBox.getSelection();
				label.setText(selection);
			}
		});

		return cc;*/
		return null;
	}
}
