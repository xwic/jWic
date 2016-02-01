/**
 * 
 */
package de.jwic.demo.chartdb;

import de.jwic.util.SerObservable;
import de.jwic.util.Util;

/**
 * The model behind the Dashboard page connects all
 * chart views with each other, allowing "global" filters
 * and events to be handled.
 * 
 * @author lippisch
 */
public class DashboardModel extends SerObservable {

	private String year;
	private DataProvider dataProvider;
	
	/**
	 * 
	 */
	public DashboardModel() {

		dataProvider = new DataProvider();
		// set defaults
		year = "2016";
		
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		if (!Util.equals(this.year, year)) {
			this.year = year;
			setChanged();
			notifyObservers();
		}
	}

	/**
	 * @return the dataProvider
	 */
	public DataProvider getDataProvider() {
		return dataProvider;
	}

}
