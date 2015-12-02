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
package de.jwic.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Loads a specified DTD file from the classpath.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class DTDEntityResolver implements EntityResolver {

	private String pubId;
	private String sysId;
	private String resourcePath;
	
	public DTDEntityResolver(String publicId, String systemId, String resourcePath) {
		this.pubId = publicId;
		this.sysId = systemId;
		this.resourcePath = resourcePath;
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.EntityResolver#resolveEntity(java.lang.String, java.lang.String)
	 */
	public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
		
        if ( pubId.equals( publicId ) || sysId.equals(systemId)) {
            InputStream in = getClass().getResourceAsStream(
                resourcePath
            );
            if (in == null) {
            	throw new FileNotFoundException("Resource '" + resourcePath + "' not found in classpath");
            }
            return new InputSource( in );
        }
        return null;
	}

}
