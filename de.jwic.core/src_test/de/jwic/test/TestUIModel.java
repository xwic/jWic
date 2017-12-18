/**
 * 
 */
package de.jwic.test;

import org.junit.Test;

/**
 * @author lippisch
 *
 */
public class TestUIModel {

	@Test
	public void test() {

		TestModel model = new TestModel();
		
		model.addUpdateListener(e -> System.out.println("Test Event"));
		
		model.addGenericListener(e -> System.out.println("Test Event :" + e.getMyEventId()));
				
		
		
		model.doSomething();

	}

}
