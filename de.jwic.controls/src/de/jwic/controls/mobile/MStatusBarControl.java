/**
 * 
 */
package de.jwic.controls.mobile;

import java.text.MessageFormat;

import de.jwic.base.IControlContainer;
import de.jwic.controls.tableviewer.StatusBarControl;
import de.jwic.controls.tableviewer.TableModel;
import de.jwic.controls.tableviewer.TableModelAdapter;
import de.jwic.controls.tableviewer.TableModelEvent;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;

/**
 * @author vedad
 *
 */
public class MStatusBarControl extends StatusBarControl {

	private static final long serialVersionUID = 8573929635757248360L;

	private MListBox mlbcMaxLines;
	private MPagingControl mctrlPaging;

	/**
	 * @param container
	 * @param model
	 * @param showAllInRangeSelector
	 */
	public MStatusBarControl(IControlContainer container, TableModel model, boolean showAllInRangeSelector) {
		this(container, null, model, showAllInRangeSelector);
	}

	/**
	 * @param container
	 * @param name
	 * @param model
	 * @param showAllInRangeSelector
	 */
	public MStatusBarControl(final IControlContainer container, final String name, final TableModel model,
			boolean showAllInRangeSelector) {
		super(container, name, model, showAllInRangeSelector);

		mctrlPaging = new MPagingControl(this, "mpaging", model);

		mlbcMaxLines = new MListBox(this, "mlbcMaxLines");
		mlbcMaxLines.addElementSelectedListener(new ElementSelectedListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void elementSelected(ElementSelectedEvent event) {
				String maxLineSelection = mlbcMaxLines.getSelectedKey();
				if (maxLineSelection != null && maxLineSelection.length() > 0)
					model.setMaxLines(Integer.parseInt(maxLineSelection));
			}
		});

		mlbcMaxLines.setChangeNotification(true);

		model.addTableModelListener(new TableModelAdapter() {
			private static final long serialVersionUID = 1L;

			public void rangeUpdated(TableModelEvent event) {
				container.setRequireRedraw(true);
				String key = Integer.toString(model.getRange().getMax());
				if (!key.equals(mlbcMaxLines.getSelectedKey())) {
					mlbcMaxLines.setSelectedKey(key);
				}
			}
		});

		this.populateSelection(showAllInRangeSelector);
	}

	/**
	 * @param showAllInRangeSelector
	 */
	void populateSelection(boolean showAllInRangeSelector) {
		mlbcMaxLines.clear();
		mlbcMaxLines.addElement("- Auto -", "0");
		// add elements
		int[] choices = { 5, 10, 15, 25, 50, 100, 200, 500 };
		String msg = "{0} rows per page";
		for (int choice : choices) {
			mlbcMaxLines.addElement(MessageFormat.format(msg, choice), Integer.toString(choice));
		}
		if (showAllInRangeSelector) {
			mlbcMaxLines.addElement("- All -", "-1");
		}
	}

	/**
	 * @return the mctrlPaging
	 */
	public MPagingControl getMctrlPaging() {
		return mctrlPaging;
	}

}
