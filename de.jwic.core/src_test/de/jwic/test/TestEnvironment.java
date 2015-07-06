/*******************************************************************************
 * Copyright 2015 xWic group (http://www.xwic.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *  
 *******************************************************************************/
package de.jwic.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

/**
 * Holds the environment for tests. It is basically a static pool of objects. 
 * The test environment settings are loaded from a property file that is loaded
 * from the Classpath using the ClassLoader. The default filename is 'testenv.properties'.
 * You can start a test using a different environment by using the java VM argument
 * <br>
 * <code>-Dtestenv.file=<i>filename</i></code><br>
 * Sample: <code>-Dtestenv.file=usertest.properties</code><br>
 *  
 * @version $Revision: 1.2 $
 * @author Florian Lippisch
 */
public class TestEnvironment {
	
	private static final String SYS_PROPERTY_TESTENVFILE = "testenv.file";
	private static final String PROPERTY_LOG4J_PROPERTIES = "log4j.properties";
	private static final String TESTENV_PROPERTIES_FILE = "testenv.properties";
	private static TestEnvironment environment = null;
	
	protected Log log = null;
	
	private HashMap<Object, Object> objects = new HashMap<Object, Object>();
	private Properties properties = null;
	/**
	 * Private contsructor -> Singleton pattern.
	 */
	private TestEnvironment() {
		super();
		
		String filename = System.getProperty(SYS_PROPERTY_TESTENVFILE, TESTENV_PROPERTIES_FILE);
		properties = new Properties();
		try {
			InputStream inp = getClass().getClassLoader().getResourceAsStream(filename);
			if (inp != null) {
				properties.load(inp);
			} else {
				// log-system is not initialized yet!
				System.out.println("TestEnvironment configuration file not found (" + filename + ")");
			}
		} catch (IOException e) {
			// logger is not yet available
			throw new RuntimeException("Error initializing TestEnvironment: " + e);
		}
		
		// setup Log4j
		String logProperties = properties.getProperty(PROPERTY_LOG4J_PROPERTIES);
		if (logProperties != null) {
			PropertyConfigurator.configureAndWatch(new File(logProperties).getAbsolutePath(), 60000L);
		} else {
			BasicConfigurator.configure();
		}
		log = LogFactory.getLog(getClass());
		log.info("Testenvironment loaded from file " + filename);
	}

	/**
	 * Returns the TestEnvironment (singleton).
	 * @return
	 */
	public static TestEnvironment getTestEnvironment() {
		if (environment == null) {
			environment = new TestEnvironment();
		}
		return environment;
	}
	
	/**
	 * Returns an object to the specified key.
	 * @param key
	 * @return
	 */
	public Object get(Object key) {
		return objects.get(key);
	}
	
	/**
	 * Stores an object to the specified key.
	 * @param key
	 * @param value
	 */
	public void put(Object key, Object value) {
		objects.put(key, value);
	}
	/**
	 * Returns a property setting.
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
