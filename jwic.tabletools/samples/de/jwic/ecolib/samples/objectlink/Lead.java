/*
 * Copyright 2005-2007 jWic group (http://www.jwic.de)
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
 * de.jwic.ecolib.samples.objectlink.Lead
 * Created on 12.04.2007
 * $Id: Lead.java,v 1.1 2007/04/25 09:59:15 lordsam Exp $
 */
package de.jwic.ecolib.samples.objectlink;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Simple object used to demonstrate the ObjectLinkTool.
 * 
 * @author Florian Lippisch
 */
public class Lead implements Serializable {

	public String id = null;
	public String name = null;
	public String area = null;
	public Date firstContact = null;
	
	/**
	 * @param id
	 * @param name
	 * @param area
	 */
	public Lead(String id, String name, String area, Date contact) {
		super();
		this.id = id;
		this.name = name;
		this.area = area;
		this.firstContact = contact;
	}
	
	/**
	 * @param id
	 * @param name
	 * @param area
	 * @throws ParseException 
	 */
	public Lead(String id, String name, String area, String contactDate) throws ParseException {
		super();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		this.id = id;
		this.name = name;
		this.area = area;
		this.firstContact = df.parse(contactDate);
	}
	
}
