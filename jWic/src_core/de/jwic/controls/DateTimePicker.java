package de.jwic.controls;

import java.io.StringWriter;
import java.util.Date;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONWriter;

import de.jwic.base.IControlContainer;

/**
 * Date Time Picker Control
 * @author dotto
 *
 */
public class DateTimePicker extends DatePicker {

	private static final Logger log = Logger.getLogger(DateTimePicker.class);
	private boolean showHour = true;
	private boolean showMinute = true;
	private boolean showSecond = true;
	private boolean showMillisec = false;
	private boolean showTimezone = false;
	private Integer stepHour = null;
	private Integer stepMinute = null;
	private Integer stepSecond = null;
	private Integer stepMilliSec = null;
	private Integer hour = null;
	private Integer minute = null;
	private Integer second = null;
	private Integer millisec = null;
	private Integer hourMin = null;
	private Integer minuteMin = null;
	private Integer secondMin = null;
	private Integer millisecMin = null;
	private Integer hourMax = null;
	private Integer minuteMax = null;
	private Integer secondMax = null;
	private Integer millisecMax = null;
	private Boolean showButtonPanel = null;
	private Boolean timeOnly = null;
	private Boolean alwaysSetTime = null;
	private String separator = null;
	
	private ControlTpye controlType = ControlTpye.SELECT;
	
	/**
	 * @param container
	 */
	public DateTimePicker(IControlContainer container) {
		super(container);
	}
	
	
	/**
	 * @param container
	 * @param name
	 */
	public DateTimePicker(IControlContainer container, String name) {
		super(container, name);
	}


	/**
	 * @return the showHour
	 */
	public boolean isShowHour() {
		return showHour;
	}


	/**
	 * @param showHour the showHour to set
	 */
	public void setShowHour(boolean showHour) {
		this.showHour = showHour;
		requireRedraw();
	}


	/**
	 * @return the showMinute
	 */
	public boolean isShowMinute() {
		return showMinute;
	}


	/**
	 * @param showMinute the showMinute to set
	 */
	public void setShowMinute(boolean showMinute) {
		this.showMinute = showMinute;
		requireRedraw();
	}


	/**
	 * @return the showSecond
	 */
	public boolean isShowSecond() {
		return showSecond;
	}


	/**
	 * @param showSecond the showSecond to set
	 */
	public void setShowSecond(boolean showSecond) {
		this.showSecond = showSecond;
		requireRedraw();
	}


	/**
	 * @return the showMillisec
	 */
	public boolean isShowMillisec() {
		return showMillisec;
	}


	/**
	 * @param showMillisec the showMillisec to set
	 */
	public void setShowMillisec(boolean showMillisec) {
		this.showMillisec = showMillisec;
		requireRedraw();
	}


	/**
	 * @return the showTimezone
	 */
	public boolean isShowTimezone() {
		return showTimezone;
	}


	/**
	 * @param showTimezone the showTimezone to set
	 */
	public void setShowTimezone(boolean showTimezone) {
		this.showTimezone = showTimezone;
		requireRedraw();
	}


	/**
	 * @return the stepHour
	 */
	public Integer getStepHour() {
		return stepHour;
	}


	/**
	 * @param stepHour the stepHour to set
	 */
	public void setStepHour(Integer stepHour) {
		this.stepHour = stepHour;
		requireRedraw();
	}


	/**
	 * @return the stepMinute
	 */
	public Integer getStepMinute() {
		return stepMinute;
	}


	/**
	 * @param stepMinute the stepMinute to set
	 */
	public void setStepMinute(Integer stepMinute) {
		this.stepMinute = stepMinute;
		requireRedraw();
	}


	/**
	 * @return the stepSecond
	 */
	public Integer getStepSecond() {
		return stepSecond;
	}


	/**
	 * @param stepSecond the stepSecond to set
	 */
	public void setStepSecond(Integer stepSecond) {
		this.stepSecond = stepSecond;
		requireRedraw();
	}
	
	/**
	 * @return the controlType
	 */
	public ControlTpye getControlType() {
		return controlType;
	}


	/**
	 * @param controlType the controlType to set
	 */
	public void setControlType(ControlTpye controlType) {
		this.controlType = controlType;
		requireRedraw();
	}
	
	/**
	 * @return the stepMilliSec
	 */
	public Integer getStepMilliSec() {
		return stepMilliSec;
	}


	/**
	 * @param stepMilliSec the stepMilliSec to set
	 */
	public void setStepMilliSec(Integer stepMilliSec) {
		this.stepMilliSec = stepMilliSec;
		requireRedraw();
	}


	/**
	 * @return the hour
	 */
	public Integer getHour() {
		return hour;
	}


	/**
	 * @param hour the hour to set
	 */
	public void setHour(Integer hour) {
		this.hour = hour;
		requireRedraw();
	}


	/**
	 * @return the minute
	 */
	public Integer getMinute() {
		return minute;
	}


	/**
	 * @param minute the minute to set
	 */
	public void setMinute(Integer minute) {
		this.minute = minute;
		requireRedraw();
	}


	/**
	 * @return the second
	 */
	public Integer getSecond() {
		return second;
	}


	/**
	 * @param second the second to set
	 */
	public void setSecond(Integer second) {
		this.second = second;
		requireRedraw();
	}


	/**
	 * @return the millisec
	 */
	public Integer getMillisec() {
		return millisec;
	}


	/**
	 * @param millisec the millisec to set
	 */
	public void setMillisec(Integer millisec) {
		this.millisec = millisec;
		requireRedraw();
	}


	/**
	 * @return the hourMin
	 */
	public Integer getHourMin() {
		return hourMin;
	}


	/**
	 * @param hourMin the hourMin to set
	 */
	public void setHourMin(Integer hourMin) {
		this.hourMin = hourMin;
		requireRedraw();
	}


	/**
	 * @return the minuteMin
	 */
	public Integer getMinuteMin() {
		return minuteMin;
	}


	/**
	 * @param minuteMin the minuteMin to set
	 */
	public void setMinuteMin(Integer minuteMin) {
		this.minuteMin = minuteMin;
		requireRedraw();
	}


	/**
	 * @return the secondMin
	 */
	public Integer getSecondMin() {
		return secondMin;
	}


	/**
	 * @param secondMin the secondMin to set
	 */
	public void setSecondMin(Integer secondMin) {
		this.secondMin = secondMin;
		requireRedraw();
	}


	/**
	 * @return the millisecMin
	 */
	public Integer getMillisecMin() {
		return millisecMin;
	}


	/**
	 * @param millisecMin the millisecMin to set
	 */
	public void setMillisecMin(Integer millisecMin) {
		this.millisecMin = millisecMin;
		requireRedraw();
	}


	/**
	 * @return the hourMax
	 */
	public Integer getHourMax() {
		return hourMax;
	}


	/**
	 * @param hourMax the hourMax to set
	 */
	public void setHourMax(Integer hourMax) {
		this.hourMax = hourMax;
		requireRedraw();
	}


	/**
	 * @return the minuteMax
	 */
	public Integer getMinuteMax() {
		return minuteMax;
	}


	/**
	 * @param minuteMax the minuteMax to set
	 */
	public void setMinuteMax(Integer minuteMax) {
		this.minuteMax = minuteMax;
		requireRedraw();
	}


	/**
	 * @return the secondMax
	 */
	public Integer getSecondMax() {
		return secondMax;
	}


	/**
	 * @param secondMax the secondMax to set
	 */
	public void setSecondMax(Integer secondMax) {
		this.secondMax = secondMax;
		requireRedraw();
	}


	/**
	 * @return the millisecMax
	 */
	public Integer getMillisecMax() {
		return millisecMax;
	}


	/**
	 * @param millisecMax the millisecMax to set
	 */
	public void setMillisecMax(Integer millisecMax) {
		this.millisecMax = millisecMax;
		requireRedraw();
	}


	/**
	 * @return the showButtonPanel
	 */
	public Boolean getShowButtonPanel() {
		return showButtonPanel;
	}


	/**
	 * @param showButtonPanel the showButtonPanel to set
	 */
	public void setShowButtonPanel(Boolean showButtonPanel) {
		this.showButtonPanel = showButtonPanel;
		requireRedraw();
	}


	/**
	 * @return the timeOnly
	 */
	public Boolean getTimeOnly() {
		return timeOnly;
	}


	/**
	 * @param timeOnly the timeOnly to set
	 */
	public void setTimeOnly(Boolean timeOnly) {
		this.timeOnly = timeOnly;
		requireRedraw();
	}


	/**
	 * @return the alwaysSetTime
	 */
	public Boolean getAlwaysSetTime() {
		return alwaysSetTime;
	}


	/**
	 * @param alwaysSetTime the alwaysSetTime to set
	 */
	public void setAlwaysSetTime(Boolean alwaysSetTime) {
		this.alwaysSetTime = alwaysSetTime;
		requireRedraw();
	}


	/**
	 * @return the separator
	 */
	public String getSeparator() {
		return separator;
	}


	/**
	 * @param separator the separator to set
	 */
	public void setSeparator(String separator) {
		this.separator = separator;
		requireRedraw();
	}

	/**
	 *	
	 * @return options which are set as JSON Object.
	 */
	public String getOptions(){

		try{
			StringWriter sw = new StringWriter();
			JSONWriter writer = new JSONWriter(sw);
			writer.object();
			writer.key("controlType").value(getControlType().getCode());
			if(isIconTriggered()){
				writer.key("showOn").value("button");
				writer.key("buttonImage").value("_contextPath+'/jwic/calendar/calendar.gif'");
				writer.key("buttonImageOnly").value(true);
			}
			writer.key("showHour").value(isShowHour());
			writer.key("showMinute").value(isShowMinute());
			writer.key("showSecond").value(isShowSecond());
			writer.key("showMillisec").value(isShowMillisec());
			writer.key("showTimezone").value(isShowTimezone());
			if(getStepHour() != null)
				writer.key("stepHour").value(getStepHour());
			if(getStepMinute() != null)
				writer.key("stepMinute").value(getStepMinute());
			if(getStepHour() != null)
				writer.key("stepSecond").value(getStepSecond());
			if(getStepMilliSec() != null)
				writer.key("stepMilliSec").value(getStepMilliSec());
			if(getHour() != null)
				writer.key("hour").value(getHour());
			if(getMinute() != null)
				writer.key("minute").value(getMinute());
			if(getSecond() != null)
				writer.key("second").value(getSecond());
			if(getMillisec() != null)
				writer.key("millisec").value(getMillisec());
			if(getHourMin() != null)
				writer.key("hourMin").value(getHourMin());
			if(getHourMin() != null)
				writer.key("hourMax").value(getHourMax());
			if(getMinuteMin() != null)
				writer.key("minuteMin").value(getMinuteMin());
			if(getMinuteMax() != null)
				writer.key("minuteMax").value(getMinuteMax());
			if(getSecondMin() != null)
				writer.key("secondMin").value(getSecondMin());
			if(getSecondMax() != null)
				writer.key("secondMax").value(getSecondMax());
			if(getMillisecMin() != null)
				writer.key("millisecMin").value(getMillisecMin());
			if(getMillisecMax() != null)
				writer.key("millisecMax").value(getMillisecMax());
			if(getShowButtonPanel() != null)
				writer.key("showButtonPanel").value(getShowButtonPanel());
			if(getTimeOnly() != null)
				writer.key("timeOnly").value(getTimeOnly());
			if(getAlwaysSetTime() != null)
				writer.key("alwaysSetTime").value(getAlwaysSetTime());
			if(getSeparator() != null)
				writer.key("separator").value(getSeparator());
			
			writer.endObject();
			return sw.toString();
		}catch (JSONException e) {
			throw new RuntimeException("Error while configuring NumberInputControl");
		}
	}
	
	/**
	 * Whether to use 'slider' or 'select'
	 * @author dotto
	 *
	 */
	public enum ControlTpye {
		SLIDER("slider"), SELECT("select");
		
		private String code;
		 
		 private ControlTpye(String c) {
		   code = c;
		 }
		 
		 public String getCode() {
		   return code;
		 }
		 
		 @Override
		public String toString() {
			return getCode();
		}
	}
}
