package de.jwic.demo.chart;

import de.jwic.base.IControlContainer;
import de.jwic.controls.Button;
import de.jwic.controls.InputBox;
import de.jwic.controls.LabelControl;
import de.jwic.controls.WindowControl;
import de.jwic.controls.dialogs.BasicDialog;
import de.jwic.controls.layout.TableLayoutContainer;
import de.jwic.events.SelectionEvent;
import de.jwic.events.SelectionListener;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 13.11.2015
 */
public class AddTableElementDialog extends BasicDialog {

	private InputBox ibLabel = null;
	private InputBox ibValue = null;
	private InputBox ibFillColor;
	private InputBox ibHighlightColor;

	/**
	 * @param parent
	 */
	public AddTableElementDialog(IControlContainer parent) {
		super(parent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jwic.ecolib.dialogs.BasicDialog#createControls(de.jwic.base.
	 * IControlContainer)
	 */
	protected void createControls(IControlContainer container) {
		WindowControl window = new WindowControl(container, "window");
		window.setWidth("650");
		window.setAlign("center");
		window.setTitle("Add table element...");

		TableLayoutContainer layout = new TableLayoutContainer(window);
		layout.setColumnCount(2);

		LabelControl taskLabel = new LabelControl(layout);
		taskLabel.setText("Table element  label: ");

		ibLabel = new InputBox(layout);

		LabelControl ownerLabel = new LabelControl(layout);
		ownerLabel.setText("Table element value: ");

		ibValue = new InputBox(layout);

		LabelControl fillColorLbl = new LabelControl(layout);
		fillColorLbl.setText("Table element fill color: ");

		ibFillColor = new InputBox(layout);

		LabelControl highlightColorLbl = new LabelControl(layout);
		highlightColorLbl.setText("Table element highlight color: ");

		ibHighlightColor = new InputBox(layout);

		Button abort = new Button(layout, "abort");
		abort.setTitle("Abort");
		abort.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				abort();
			}
		});

		Button finish = new Button(layout, "finish");
		finish.setTitle("Finish");
		finish.addSelectionListener(new SelectionListener() {
			public void objectSelected(SelectionEvent event) {
				finish();
			}
		});
	}

	/**
	 * @return the Demo task object
	 */
	public TableElement getTableElement() {

		TableElement tableElement = new TableElement(ibLabel.getText(), ibValue.getText());
		tableElement.setFillColor(ibFillColor.getText());
		tableElement.setHighlightColor(ibHighlightColor.getText());
		return tableElement;
	}
}
