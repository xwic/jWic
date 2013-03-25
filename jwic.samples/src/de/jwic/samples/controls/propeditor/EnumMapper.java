package de.jwic.samples.controls.propeditor;

import de.jwic.base.Control;
import de.jwic.controls.ListBoxControl;

public class EnumMapper extends AbstractPropertyMapper implements IPropertyMapper {

	/* (non-Javadoc)
	 * @see de.jwic.samples.controls.propeditor.IPropertyMapper#getControlValue(de.jwic.base.Control)
	 */
	public Object getControlValue(Control control) {
		EnumListBoxControl<Enum<?>> lbc = (EnumListBoxControl<Enum<?>>)control;
		return lbc.getEnumObject();
	}

	/* (non-Javadoc)
	 * @see de.jwic.samples.controls.propeditor.IPropertyMapper#updateControlValue(de.jwic.base.Control, java.lang.Object)
	 */
	public void updateControlValue(Control control, Object value) {
		EnumListBoxControl<Enum<?>> lbc = (EnumListBoxControl<Enum<?>>)control;
		if(value != null){
			lbc.setSelectedKey(((Enum<?>)value).name());
		}else {
			lbc.setSelectedKey("");
		}
	}
}
