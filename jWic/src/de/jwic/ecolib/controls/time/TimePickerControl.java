package de.jwic.ecolib.controls.time;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import de.jwic.base.ControlContainer;
import de.jwic.base.IControlContainer;
import de.jwic.controls.ListBoxControl;
import de.jwic.events.ElementSelectedEvent;
import de.jwic.events.ElementSelectedListener;
import de.jwic.events.ValueChangedEvent;
import de.jwic.events.ValueChangedListener;
import de.jwic.util.SerObservable;
import de.jwic.util.SerObserver;

/**
 * Allows users to set a time using hour, minute and second settings.
 * 
 *  Created on 21.09.2006
 * @author Mark Frewin
 */
public class TimePickerControl extends ControlContainer implements SerObserver{

	private static final long serialVersionUID = -5414170260784468396L;
	private TimeModel timeModel;
	private ListBoxControl lbcHour;
	private ListBoxControl lbcMinute;
	private ListBoxControl lbcQuarterMinute;
	private ListBoxControl lbcHalfMinute;
	private ListBoxControl lbcSecond;
	private ListBoxControl lbcAmPm;
	private List<ValueChangedListener> vclisteners;
	private String hint;

	private boolean showSeconds = true;
	private boolean showQuartersOnly = false;
	private boolean showHalvesOnly = false;
	private boolean amPmMode = false;
	
	
	/**
	 * @param container
	 */
	public TimePickerControl(IControlContainer container) {
		this(container, null, false);
	}

	/**
	 * @param container
	 * @param name
	 */
	public TimePickerControl(IControlContainer container, String name) {
		this(container, name, false);
	}

	/**
	 * @param container
	 * @param name
	 * @param amPmMode
	 */
	public TimePickerControl(IControlContainer container, String name, boolean amPmMode) {
		super(container, name);
		
		this.amPmMode = amPmMode;
		
		initialise();
	}
	
	/**
	 * Initialise controls.
	 *
	 */
	private void initialise() {
		hint = "";
		Locale locale = getSessionContext().getLocale();
		timeModel = new TimeModel();
		timeModel.setLocale(locale);
		timeModel.setTimeZone(getSessionContext().getTimeZone());
		timeModel.addObserver(this);
		
		lbcHour = new ListBoxControl(this, "lbcHour");
		int firstHour = amPmMode ? 1 : 0;
		int lastHour = amPmMode ? 12 : 23; 
		for (int i=firstHour; i <= lastHour; i++) {
			if (i < 10 && !amPmMode) {
				lbcHour.addElement("0" + i, Integer.toString(i));
			} else {
				lbcHour.addElement("" + i);
			}
		}
		
		lbcAmPm = new ListBoxControl(this, "lbcAmPm");
		lbcAmPm.addElement("am", "a");
		lbcAmPm.addElement("pm", "p");
		
		// set the hour after both combos are created
		setHourOnCombo();
		
		// add the listeners only after setting the hour		
		lbcHour.setChangeNotification(true);
		lbcHour.addElementSelectedListener(new ElementSelectedListener() {
			private static final long serialVersionUID = 1796523919940764809L;
			public void elementSelected(ElementSelectedEvent event) {
				setHourOnModel();
			}			
		});
		
		lbcAmPm.setChangeNotification(true);
		lbcAmPm.addElementSelectedListener(new ElementSelectedListener() {			
			private static final long serialVersionUID = -56543833632223228L;
			public void elementSelected(ElementSelectedEvent event) {
				setHourOnModel();
			}			
		});
		
		lbcMinute = new ListBoxControl(this, "lbcMinute");
		for (int i=0; i < 60; i++) {
			if (i < 10) {
				lbcMinute.addElement("0" + i, Integer.toString(i));
			} else {
				lbcMinute.addElement("" + i);
			}
		}
		lbcMinute.setSelectedKey("" + timeModel.getMinute());
		lbcMinute.setChangeNotification(true);
		lbcMinute.addElementSelectedListener(new ElementSelectedListener() {
			private static final long serialVersionUID = -434350747229912731L;
			public void elementSelected(ElementSelectedEvent event) {
				timeModel.setMinute(Integer.parseInt((String)event.getElement()));
			}			
		});				
		
		//control for quarter minutes
		lbcQuarterMinute = new ListBoxControl(this, "lbcQuarterMinute");
		lbcQuarterMinute.addElement("00", "00");
		lbcQuarterMinute.addElement("15", "15");
		lbcQuarterMinute.addElement("30", "30");
		lbcQuarterMinute.addElement("45", "45");		
		lbcQuarterMinute.setSelectedKey("00");
		lbcQuarterMinute.setChangeNotification(true);
		lbcQuarterMinute.addElementSelectedListener(new ElementSelectedListener() {
			private static final long serialVersionUID = 589949554939553458L;

			public void elementSelected(ElementSelectedEvent event) {
				timeModel.setMinute(Integer.parseInt((String)event.getElement()));
			}			
		});				
		lbcQuarterMinute.setVisible(showQuartersOnly);
		
		//control for half minutes
		lbcHalfMinute = new ListBoxControl(this, "lbcHalfMinute");
		lbcHalfMinute.addElement("00", "00");
		lbcHalfMinute.addElement("30", "30");
		lbcHalfMinute.setSelectedKey("00");
		lbcHalfMinute.setChangeNotification(true);
		lbcHalfMinute.addElementSelectedListener(new ElementSelectedListener() {
			private static final long serialVersionUID = 589949554939553458L;

			public void elementSelected(ElementSelectedEvent event) {
				timeModel.setMinute(Integer.parseInt((String)event.getElement()));
			}			
		});				
		lbcHalfMinute.setVisible(showHalvesOnly);
		
		lbcSecond = new ListBoxControl(this, "lbcSecond");		
		for (int i=0; i < 60; i++) {
			if (i < 10) {
				lbcSecond.addElement("0" + i, Integer.toString(i));
			} else {
				lbcSecond.addElement("" + i);
			}
		}
		lbcSecond.setSelectedKey("" + timeModel.getSecond());
		lbcSecond.setChangeNotification(true);
		lbcSecond.addElementSelectedListener(new ElementSelectedListener() {			
			private static final long serialVersionUID = -5654383363446323228L;
			public void elementSelected(ElementSelectedEvent event) {
				timeModel.setSecond(Integer.parseInt((String)event.getElement()));
			}			
		});
		
		lbcHour.setCssClass("inline");
		lbcAmPm.setCssClass("inline");
		lbcMinute.setCssClass("inline");
		lbcQuarterMinute.setCssClass("inline");
		lbcHalfMinute.setCssClass("inline");
		lbcSecond.setCssClass("inline");
	}
	
	/**
	 * 
	 */
	private void setHourOnModel() {
		int hour = Integer.parseInt(lbcHour.getSelectedKey());
		
		if (amPmMode) {
			boolean am = "a".equals(lbcAmPm.getSelectedKey());  
			
			if (am) {
				if (hour == 12) {
					hour = 0;
				}
			} else if (hour != 12) {
				hour += 12;
			}
		}
		
		timeModel.setHour(hour);
	}

	/**
	 * 
	 */
	private void setHourOnCombo() {
		boolean pm = false;
		int hour = timeModel.getHour();
		if (amPmMode) {
			if (hour > 12) {
				hour = hour - 12;
				pm = true;
			} else if (hour == 12) {
				pm = true;
			} else if (hour == 0) {
				hour = 12;
			}
		}
		
		if (!lbcHour.getSelectedKey().equals("" + hour)) {
			lbcHour.setSelectedKey("" + hour);		
		}
		
		if (amPmMode) {
			String key = pm ? "p" : "a";
			if (!lbcAmPm.getSelectedKey().equals(key)) {
				lbcAmPm.setSelectedKey(key);
			}
		}
	}
	
	/**
	 * Change the timezone.
	 * @param timeZone
	 */
	public void setTimeZone(TimeZone timeZone) {
		timeModel.setTimeZone(timeZone);
	}

	/**
	 * Returns the time as localise time string.
	 * 
	 * @return
	 */
	public String getFormattedTime() {
		return timeModel.getFormattedTime();		
	}
	
	/**
	 * Setter for time.
	 * 
	 * @param time
	 */
	public void setTime(Date time) {
		if (!showSeconds && time != null) {
			Calendar temp = Calendar.getInstance();
			temp.setTime(time);
			temp.set(Calendar.SECOND, 0);
			temp.set(Calendar.MILLISECOND, 0);
			if (showQuartersOnly) {
				int min = temp.get(Calendar.MINUTE);
				if (min > 14) {
					if (min > 29) {
						if (min > 44) {
							min = 45;
						} else {
							min = 30;
						}
					} else {
						min = 15;
					}
				} else {
					min = 0;
				}
				temp.set(Calendar.MINUTE, min);
			} else if (showHalvesOnly) {
				int min = temp.get(Calendar.MINUTE);
				if (min > 29) {
					min = 30;
				} else {
					min = 0;
				}
				temp.set(Calendar.MINUTE, min);
			}
			time = temp.getTime();
		}
		
		timeModel.setTime(time);
	}

	/**
	 * Allows other controls to register as listeners
	 * for events are fired from the TimePicker control.
	 * 
	 * @param listener
	 */
	public void addValueChangedListener(ValueChangedListener listener) {
		if (vclisteners == null)
			vclisteners = new ArrayList<ValueChangedListener>();
		
		vclisteners.add(listener);
		
	}
	
	/**
	 * Allows controls that are registered as listeners to unregister themselves.
	 * 
	 * @param listener
	 */
	public void removeValueChangedListener(ValueChangedListener listener) {
		if (vclisteners != null)
			vclisteners.remove(listener);		
	}
	
	/**
	 * Notifies all registered listeners when the valueChanged event is triggered.
	 *
	 */
	private void notifyValueChangedListeners() {
		if (vclisteners != null) {
			ValueChangedEvent event = new ValueChangedEvent(this);
			for (Iterator<ValueChangedListener> it = vclisteners.iterator(); it.hasNext();){
				ValueChangedListener vclistener = it.next();
				vclistener.valueChanged(event);
			}
		}
		
	}

	/*
	 *  (non-Javadoc)
	 * @see de.jwic.util.SerObserver#update(de.jwic.util.SerObservable, java.lang.Object)
	 */
	public void update(SerObservable o, Object arg) {
		
		setHourOnCombo();
		
		if (!lbcMinute.getSelectedKey().equals("" + timeModel.getMinute())) {
			lbcMinute.setSelectedKey("" + timeModel.getMinute());		
		}
		if (isShowQuartersOnly() && !lbcQuarterMinute.getSelectedKey().equals("" + timeModel.getMinute())) {
			int minutes = timeModel.getMinute();
			
			//select next quarter
			if (minutes > 0 && minutes <= 15) {
				lbcQuarterMinute.setSelectedKey("15");		
			}
			else if (minutes > 15 && minutes <= 30) {
				lbcQuarterMinute.setSelectedKey("30");		
			}
			else if (minutes > 30 && minutes <= 45) {
				lbcQuarterMinute.setSelectedKey("45");		
			}
			else {
				lbcQuarterMinute.setSelectedKey("00");
			}
		}
		if (isShowHalvesOnly() && !lbcHalfMinute.getSelectedKey().equals("" + timeModel.getMinute())) {
			int minutes = timeModel.getMinute();
			
			//select next half
			if (minutes > 0 && minutes <= 30) {
				lbcHalfMinute.setSelectedKey("30");		
			} else {
				lbcHalfMinute.setSelectedKey("00");
			}
		}
		
		
		if (!lbcSecond.getSelectedKey().equals("" + timeModel.getSecond())) {
			lbcSecond.setSelectedKey("" + timeModel.getSecond());		
		}
		
		notifyValueChangedListeners();
	}
	
	/**
	 * Flag to show/hide seconds in display.
	 * 
	 * @param flag
	 */
	public void setShowSeconds(boolean flag){
		this.showSeconds = flag;
		if (showSeconds) {
			lbcSecond.setVisible(true);					
		} else {
			lbcSecond.setVisible(false);			
		}
		requireRedraw();
	}
	
	/**
	 * Setter for locale.
	 * 
	 * @param locale
	 */
	public void setLocale(Locale locale) {
		timeModel.setLocale(locale);
		notifyValueChangedListeners();
	}
	
	/**
	 * Returns currently set locale.
	 * 
	 * @return
	 */
	public Locale getLocale() {
		return timeModel.getLocale();
	}
	
	/**
	 * Returns a DateFomatter based on the current DateFormat style and locale.
	 * 
	 * @return
	 */
	public DateFormat getDateFormatter() {
		return timeModel.getDateFormatter();
	}
	
	/**
	 * Returns the current DateFormat style. 
	 * @return
	 */
	public int getDateFormatStyle() {
		return timeModel.getDateFormatStyle();
	}
	
	/**
	 * Setter for the hint text that display next to the control.
	 * 
	 * @param hint
	 */
	public void setHint(String hint) {
		this.hint = hint;
		setRequireRedraw(true);
	}

	/**
	 * Determines whether the second field will be displayed.
	 * 
	 * @return
	 */
	public boolean isShowSeconds() {
		return showSeconds;
	}

	/**
	 * Returns the time represented by this control.
	 * 
	 * @return
	 */
	public Date getTime() {
		return timeModel.getTime();
	}
	
	/**
	 * Setter for DateFormat style.
	 * 
	 * @param style
	 */
	public void setDateFormatStyle(int style) {
		timeModel.setDateFormatStyle(style);
		if (style == DateFormat.SHORT)
			setShowSeconds(false);
		else
			setShowSeconds(true);
		
		notifyValueChangedListeners();
	}

	/**
	 * Returns the current Hint text.
	 * 
	 * @return
	 */
	public String getHint() {
		return hint;
	}

	/**
	 * 
	 * @return the selected minute.
	 */
	public int getMinute() {
		return timeModel.getMinute();
	}
	
	/**
	 * 
	 * @return the selected second
	 */
	public int getSecond() {
		return timeModel.getSecond();
	}
	
	/**
	 * 
	 * @return the selected hour
	 */
	public int getHour() {
		return timeModel.getHour();
	}
	
	/**
	 * Set the controls readonly or not.
	 * 
	 * @param readonly
	 */
	public void setReadOnly(boolean readonly) {
		lbcHour.setEnabled(!readonly);
		lbcAmPm.setEnabled(!readonly);
		lbcMinute.setEnabled(!readonly);
		lbcQuarterMinute.setEnabled(!readonly);
		lbcHalfMinute.setEnabled(!readonly);
		lbcSecond.setEnabled(!readonly);
	}

	/**
	 * @return the showQuartersOnly
	 */
	public boolean isShowQuartersOnly() {
		return showQuartersOnly;
	}

	/**
	 * @param showQuartersOnly the showQuartersOnly to set
	 */
	public void setShowQuartersOnly(boolean showQuartersOnly) {
		if (showQuartersOnly) {
			setShowHalvesOnly(false);
			setShowSeconds(false);
		}
		this.showQuartersOnly = showQuartersOnly;
		lbcQuarterMinute.setVisible(showQuartersOnly);
		requireRedraw();
	}

	/**
	 * @return the showHalvesOnly
	 */
	public boolean isShowHalvesOnly() {
		return showHalvesOnly;
	}

	/**
	 * @param showHalvesOnly the showHalvesOnly to set
	 */
	public void setShowHalvesOnly(boolean showHalvesOnly) {
		if (showHalvesOnly) {
			setShowQuartersOnly(false);
			setShowSeconds(false);
		}
		this.showHalvesOnly = showHalvesOnly;
		lbcHalfMinute.setVisible(showHalvesOnly);
		requireRedraw();
	}

	/**
	 * @return the amPmMode
	 */
	public boolean isAmPmMode() {
		return amPmMode;
	}
}
