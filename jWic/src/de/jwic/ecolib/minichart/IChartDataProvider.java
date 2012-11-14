/**
 * 
 */
package de.jwic.ecolib.minichart;

/**
 * @author Florian Lippisch
 *
 */
public interface IChartDataProvider {

	/**
	 * Returns a number of values to be displayed.
	 * @param options
	 * @return
	 */
	public Number[] getChartValues(String options, int maxValues);
	
}
