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

import java.io.FileInputStream;

import de.jwic.base.ConfigurationTool;
import de.jwic.base.JWicRuntime;

/**
 * Returns the JWicRuntime. Initializes the Runtime on first access. 
 * To initialize the runtime, you must specify the path to the jwic-setup.xml
 * file in the testenv file.
 * 
 * @version $Revision: 1.1 $
 * @author Florian Lippisch
 */
public class TestJWicRuntimeProvider {

	private static boolean initDone = false;
	
	/**
	 * Returns the ApplicationContext.
	 * @return
	 */
	public static JWicRuntime getJWicRuntime() {
		TestEnvironment env= TestEnvironment.getTestEnvironment();
		JWicRuntime rt = JWicRuntime.getJWicRuntime();

		if (!initDone) {
			String rootPath = env.getProperty("rootpath");
			ConfigurationTool.setRootPath(rootPath != null ? rootPath : ".");
			
			String jwfile = env.getProperty("jwic-setup.xml");
			if (jwfile == null) {
				throw new RuntimeException("property jwic-setup.xml not specified in testenv.properties.");
			}
			try {
				rt.setupRuntime(TestJWicRuntimeProvider.class.getClassLoader().getResourceAsStream(jwfile));
			} catch (Exception e) {
				throw new RuntimeException("Error initializing JWicRuntime:" + e);
			}
			initDone = true;
		}
		
		return rt;
	}
	
}
