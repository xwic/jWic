/*
 * Copyright 2005 jWic Group (http://www.jwic.de)
 * $Id: TestJWicRuntimeProvider.java,v 1.1 2006/01/16 08:31:12 lordsam Exp $
 */
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
				rt.setupRuntime(new FileInputStream(jwfile));
			} catch (Exception e) {
				throw new RuntimeException("Error initializing JWicRuntime:" + e);
			}
			initDone = true;
		}
		
		return rt;
	}
	
}
