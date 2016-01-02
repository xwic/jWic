package de.jwic.mobile.controls.mtable;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.common.properties.WithTextProperty;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 19.12.2015
 */
@JavaScriptSupport
public class MTable extends ControlContainer implements WithTextProperty {

	private String text;

	private MTableModel model;

	public MTable(IControlContainer container) {
		this(container, null);
	}

	public MTable(IControlContainer container, String name) {
		super(container, name);
		this.setTemplateName(MTable.class.getName());

	}

	@Override
	public void setText(String title) {
		this.text = title;

	}

	@Override
	public String getText() {
		return text;
	}

	public MTableModel getModel() {
		return model;
	}

	public void setModel(MTableModel model) {
		this.model = model;
	}

	public String render(MColumn name,Object row) {
		return model.getCellRenderer().getCell(name.getFieldName(), row);
	//	return row.toString();
	}
	
	@Override
	public final void actionPerformed(String actionId, String parameter) {
		if("click".equals(actionId)) {
			System.out.println(parameter);
		}
	}

}
