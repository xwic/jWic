package de.jwic.controls.mobile;

import de.jwic.base.IControlContainer;
import de.jwic.base.JavaScriptSupport;
import de.jwic.controls.DatePicker;

/**
 * Created by boogie on 10/31/14.
 */
@JavaScriptSupport
public class MDatePicker extends DatePicker {

	/*
	private static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";
	private static final String DATE = "date";

	private final PropertiesHandler propertiesHandler;
	private final EnableableHandler enableableHandler;
	private final BlurHandler blurHandler;

	private final Field field;
	private String dateFormat;*/

	/**
	 *
	 * @param container
	 * @param name
	 */
	public MDatePicker(IControlContainer container, String name) {
		super(container, name);
		/*this.propertiesHandler = new PropertiesHandler(this);
		this.enableableHandler = new EnableableHandler(this);
		this.blurHandler = new BlurHandler<MDatePicker>(this);

		this.field = new Field(this, "textField");
		this.field.addValueChangedListener(new DatePickerFieldValueChangedListener(this));

		this.dateFormat = DEFAULT_DATE_FORMAT;*/
	}

	/*
	@Override
	public void enable() {
		enableableHandler.enable();
	}

	@Override
	public void disable() {
		enableableHandler.disable();
	}

	@Override
	@IncludeJsOption
	public boolean isEnabled() {
		return enableableHandler.isEnabled();
	}

	@Override
	public void setEnabled(boolean enabled) {
		enableableHandler.setEnabled(enabled);
	}

	@Override
	public void removePropertyChangedListener(PropertyChangedListener listener) {
		propertiesHandler.removePropertyChangedListener(listener);
	}

	@Override
	public void addPropertyChangedListener(PropertyChangedListener listener) {
		propertiesHandler.addPropertyChangedListener(listener);
	}

	@Override
	public void show() {
		this.setVisible(true);
	}

	@Override
	public void hide() {
		this.setVisible(false);
	}

	public Date getDate() {
		return propertiesHandler.getProperty(DATE, Date.class);
	}

	public void setDate(Date date) {
		field.setValue(format(date));
		this.propertiesHandler.setProperty(DATE, date);
	}


	@Override
	public final void actionPerformed(String actionId, String parameter) {
		if(!isEnabled()){
			return;
		}
		if("close".equals(actionId) && isUpdateOnBlur()){
			this.field.setValue(parameter);

			Date parse;
			try {
				parse = parse(parameter);
			} catch (ParseException e) {
				parse = null;
				this.field.setValue("");
			}
			this.propertiesHandler.setPropertyNoRedraw(DATE, parse);
			blurHandler.blurNoRedraw();
		}
	}

	@Override
	public void blur() {
		blurHandler.blur();
	}

	@Override
	public void addBlurListener(BlurListener listener) {
		blurHandler.addBlurListener(listener);
	}

	@Override
	public void removeBlurListener(BlurListener listener) {
		blurHandler.removeBlurListener(listener);
	}

	@Override
	@IncludeJsOption
	public boolean isUpdateOnBlur() {
		return blurHandler.isUpdateOnBlur();
	}

	@Override
	public void setUpdateOnBlur(boolean updateOnBlur) {
		blurHandler.setUpdateOnBlur(updateOnBlur);
	}

	@IncludeJsOption
	boolean isBlurred() {
		return blurHandler.isBlurred();
	}

	@IncludeJsOption
	String getDateValue(){
		return this.field.getValue();
	}

	public void setDateFormat(String format){
		if(format == null){
			this.dateFormat = DEFAULT_DATE_FORMAT;
			return;
		}
		this.dateFormat = format;
	}

	@IncludeJsOption
	public String getDateFormat(){
		return this.dateFormat;
	}

	@IncludeJsOption
	public String getFormatedDate(){
		final Date date = this.getDate();
		return date == null ? "" : format(date);
	}


	private String format(Date date) {
		return new SimpleDateFormat(this.dateFormat).format(date);
	}
	private Date parse(String value) throws ParseException {
		return new SimpleDateFormat(this.dateFormat).parse(value);
	}

	private static final class DatePickerFieldValueChangedListener implements ValueChangedListener{
		private final MDatePicker MDatePicker;

		private DatePickerFieldValueChangedListener(MDatePicker MDatePicker) {
			this.MDatePicker = MDatePicker;
		}

		@Override
		public void valueChanged(ValueChangedEvent event) {
			final Field field1 = MDatePicker.field;
			final String value = field1.getValue();

			Date parse;
			try {
				parse = MDatePicker.parse(value);
			} catch (ParseException e) {
				parse = null;
			}
			MDatePicker.propertiesHandler.setPropertyNoRedraw(DATE, parse);

		}


	}*/
}
