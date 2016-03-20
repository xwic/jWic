/*******************************************************************************
 * Copyright 2015 xWic group (http://www.xwic.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *  
 *******************************************************************************/
package de.jwic.demo.basics;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.DateTimePicker;
import de.jwic.controls.InputBox;
import de.jwic.controls.ListBox;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;
import de.jwic.samples.controls.propeditor.PropertyEditorView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

/**
 * Contains a list of TimeZones.
 * @author Daniel Otto, Florian Lippisch
 */
public class TimeZoneSelectionCombo extends ListBox {

	private String emptySelectionKey;
	private String emptySelectionTitle;

	/**
	 * @param container
	 * @param name
	 */
	public TimeZoneSelectionCombo(IControlContainer container, String name) {
		this(container, name, "", "", true);
	}

	/**
	 * @param container
	 * @param name
	 * @param emptySelectionTitle
	 * @param emptySelectionKey
	 */
	public TimeZoneSelectionCombo(IControlContainer container, String name, String emptySelectionTitle, String emptySelectionKey, boolean populateAll) {
		super(container, name);

		setTemplateName(ListBox.class.getName());

		this.emptySelectionKey = emptySelectionKey;
		this.emptySelectionTitle = emptySelectionTitle;

		if (populateAll) {
			populateAllTimeZones();
		}
	}

	/**
	 *
	 */
	public void populateAllTimeZones() {
		clear();

		List<String> names = new ArrayList<String>();

		for (String tzId : TimeZone.getAvailableIDs()) {
			names.add(tzId);
		}

		Collections.sort(names);

		addElement(emptySelectionTitle, emptySelectionKey);

		for (String tzId : names) {
			addElement(tzId);
		}

		requireRedraw();
	}

	/**
	 * @param timezones
	 */
	public void populateTimeZones(List<TimeZone> timezones) {
		clear();

		addElement(emptySelectionTitle, emptySelectionKey);

		for (TimeZone timeZone : timezones) {
			addElement(timeZone.getID());
		}

		requireRedraw();
	}
}
