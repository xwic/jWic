package de.jwic.controls.chart.api;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import static java.lang.annotation.ElementType.*;

/**
 * 
 * @author Karolina Marek (karolina-marek.eu)
 *
 * @date 14.11.2015
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ FIELD })
public @interface JsonChartName {
	String bar();

	String circle();

	String line();

	String radar();

	String polar();
	
	String dateTime();
	
	String stacked();
	
	String overlay();

}
