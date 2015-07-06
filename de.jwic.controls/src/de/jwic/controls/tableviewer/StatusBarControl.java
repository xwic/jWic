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

package de.jwic.controls.tableviewer;

import java.text.MessageFormat;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.IHaveEnabled;
import de.jwic.controls.ListBox;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;

/**
 * Created on Apr 5, 2007
 * @author jbornema
 */
public class StatusBarControl extends ControlContainer implements IHaveEnabled {
	private static final long serialVersionUID = 1L;
	private ListBox lbcMaxLines;
	private PagingControl ctrlPaging;

	private boolean enabled = true;
	
	public StatusBarControl(IControlContainer container, TableModel model, boolean showAllInRangeSelector) {
		this(container, null, model, showAllInRangeSelector);
	}

	public StatusBarControl(final IControlContainer container, String name, final TableModel model, boolean showAllInRangeSelector) {
		super(container, name);

		// add pagingControl
		ctrlPaging = new PagingControl(this, "paging", model);
		
		// add MaxLines control
		lbcMaxLines = new ListBox(this, "lbcMaxLines");
		lbcMaxLines.addElementSelectedListener(new ElementSelectedListener() {
			private static final long serialVersionUID = 1L;
			public void elementSelected(ElementSelectedEvent event) {
				String maxLineSelection = lbcMaxLines.getSelectedKey();
				if(maxLineSelection != null && maxLineSelection.length() > 0)
					model.setMaxLines(Integer.parseInt(maxLineSelection));
			}
		});
		lbcMaxLines.setChangeNotification(true);
		
		// add tableModelListener to flag the control "requireRedraw" when
		// the range has been modified.
		model.addTableModelListener(new TableModelAdapter() {
			private static final long serialVersionUID = 1L;
			public void rangeUpdated(TableModelEvent event) {
				container.setRequireRedraw(true);
				String key = Integer.toString(model.getRange().getMax());
				if (!key.equals(lbcMaxLines.getSelectedKey())) {
					lbcMaxLines.setSelectedKey(key);
				}
			}
		});

		this.populateSelectionCombo(showAllInRangeSelector);
	}

	/**
	 * @return the PagingControl
	 */
	public PagingControl getPagingControl() {
		return ctrlPaging;
	}
	
	/**
	 * @return the MaxLinesControl
	 */
	public ListBox getMaxLinesControl() {
		return lbcMaxLines;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IHaveEnabled#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IHaveEnabled#setEnabled(boolean)
	 */
	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		lbcMaxLines.setEnabled(enabled);
		ctrlPaging.setEnabled(enabled);
	}

	void populateSelectionCombo(boolean showAllInRangeSelector) {
		lbcMaxLines.clear();
		lbcMaxLines.addElement("- Auto -", "0");
		// add elements
		int[] choices = {5, 10, 15, 25, 50, 100, 200, 500};
		String msg = "{0} rows per page";
		for (int choice : choices) {
			lbcMaxLines.addElement(
					MessageFormat.format(
							msg,
							choice),
					Integer.toString(choice));
		}
		if(showAllInRangeSelector) {
			lbcMaxLines.addElement("- All -", "-1");
		}
	}
}
