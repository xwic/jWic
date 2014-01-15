/*
 * Copyright 2005 jWic group (http://www.jwic.de)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * de.jwic.web.IApplicationSetupProvider
 * Created on 09.11.2005
 * $Id: IApplicationSetupProvider.java,v 1.1 2006/01/16 08:31:13 lordsam Exp $
 */
package de.jwic.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import de.jwic.base.IApplicationSetup;

/**
 * Provides an IApplicationSetup object from a request object. 
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.1 $
 */
public interface IApplicationSetupProvider {

	public IApplicationSetup createApplicationSetup(HttpServletRequest request) throws IOException; 
	
}
