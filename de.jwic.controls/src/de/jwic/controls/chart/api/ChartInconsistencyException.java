package de.jwic.controls.chart.api;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 03.11.2015
 */
public class ChartInconsistencyException extends Exception {

	/**
	 * checked exception thrown when chart configuration or dataset is not
	 * correctly defined
	 * 
	 * @param message
	 */
	public ChartInconsistencyException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6041812207624820703L;

}
