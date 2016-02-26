/**
 * 
 */
package de.jwic.mobile.demos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.jwic.base.Control;
import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.combo.LifeSearchCombo;
import de.jwic.controls.mobile.MCombo;
import de.jwic.data.DataLabel;
import de.jwic.data.IBaseLabelProvider;
import de.jwic.demo.advanced.PartObject;
import de.jwic.demo.advanced.PartObjectContentProvider;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.mobile.MobileDemoModule;

/**
 * @author vedad
 *
 */
public final class ComboDemo extends MobileDemoModule {

	private List<PartObject> demoData = new ArrayList<PartObject>();

	public ComboDemo() {
		super("Combo Demo");

	}

	private void createDemoData() {

		String[] color = { "Red", "Green", "Blue", "White", "Black" };
		String[] type = { "Book", "Wheel", "Chair", "Computer" };
		String[] extension = { "Light", "Premium", "Express", "Master" };

		Random rnd = new Random(0l);

		for (int i = 0; i < 10000; i++) {
			PartObject po = new PartObject();
			String name = color[rnd.nextInt(color.length)] + " " + type[rnd.nextInt(type.length)] + " X" + (i % 10)
					+ "00 " + extension[rnd.nextInt(extension.length)];
			po.setName(name);
			po.setPartNumber(name.substring(0, 1) + "XX-" + i);
			demoData.add(po);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.mobile.MobileDemoModule#createPage(de.jwic.base.
	 * IControlContainer)
	 */
	@Override
	public Control createPage(IControlContainer controlContainer) {
		final ControlContainer container = new ControlContainer(controlContainer, "controlContainer");
		
		createDemoData();

		MCombo<PartObject> firstcombo = new MCombo<PartObject>(container, "firstcombo");
		firstcombo.setFilterReveal(true);
		firstcombo.setRemote(true);
		firstcombo.setFilterPlaceholder("Enter at least 3 characters of a part number or name");
		firstcombo.setBaseLabelProvider(new IBaseLabelProvider<PartObject>() {
			public DataLabel getBaseLabel(PartObject object) {
				return new DataLabel(object.getTitle());
			}
		});
		firstcombo.setWidth(400);
		firstcombo.getComboBehavior().setTransferFullObject(true);
		firstcombo.getComboBehavior().setMaxFetchRows(10);
		firstcombo.setContentProvider(new PartObjectContentProvider(demoData));

		return container;
	}

}
