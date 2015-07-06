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
package de.jwic.base;

/**
 * Abstract implementation of the IApplication interface.
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public abstract class Application implements IApplication {
	private static final long serialVersionUID = 1L;
	
	protected SessionContext sessionContext = null;
	
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IApplication#createRootControl(de.jwic.base.IControlContainer)
	 */
	public abstract Control createRootControl(IControlContainer container);
	
	/**
	 * Returns the sessionContext.
	 * @return
	 */
	protected SessionContext getSessionContext() {
		return sessionContext;
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.base.IApplication#initialize(de.jwic.base.SessionContext)
	 */
	public void initialize(SessionContext context) {
		this.sessionContext = context;
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IApplication#postDestroy()
	 */
	public void postDestroy() {
		// do nothing

	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IApplication#preDestroy()
	 */
	public void preDestroy() {
		// do nothing

	}

}
