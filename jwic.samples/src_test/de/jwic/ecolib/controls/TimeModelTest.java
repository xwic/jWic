package de.jwic.ecolib.controls;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;
import de.jwic.ecolib.controls.time.TimeModel;

public class TimeModelTest extends TestCase {
	
	private TimeModel timeModel;
	
	public TimeModelTest (String name) {
		super(name);
	}
	
	protected void setUp() throws Exception {		
		super.setUp();
		timeModel = new TimeModel();
	}	
	
	public void testSetHour() {
		timeModel.setHour(10);
		Date time = timeModel.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		assertEquals(10, hour);
	}
	
	public void testSetMinute() {		
		
		try {
			timeModel.setHour(61);
			fail();
		} catch (Exception ve) {
			//expected
		}
		
	}
	
	public void testSetSecond() {
		timeModel.setHour(10);
		Date time = timeModel.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		assertEquals(10, hour);
	}
}
