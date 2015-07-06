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

import java.util.Iterator;
import java.util.Properties;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Provides static methods used to read xml data.
 * 
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class XMLTool {

    /**       
     * Creates a utf8 byte array from the input int number.       
     * @param	input	input int number.       
     * @return	a utf8 byte array.       
     */
    public static final byte[] toUtf8(int input) 
    {
        byte output[];
        if (input <= 0x7F) 
        {
            output = new byte[1];
            int x = input;
            x = (x & 0x7F);
            output[0] = (byte)x;
        }
        else if (input >= 0x800) 
        {
            output = new byte[3];
            int x = input;
            int y = input;
            int z = input;
            x = ( (x & 0xF000) >>> 12 ) | 0xE0;
            y = ( (y & 0xFC0) >>> 6 ) | 0x80;
            z = (z & 0x3F) | 0x80;
            output[0] = (byte)x;
            output[1] = (byte)y;
            output[2] = (byte)z;
        }
        else
        {
            output = new byte[2];
            int x = input;
            int y = input;
            x = ( (x & 0x7C0) >>> 6 ) | 0xC0;
            y = (y & 0x3F) | 0x80;
            output[0] = (byte)x;
            output[1] = (byte)y;
        }
        return output;
    }
	/**       
	 * Creates a utf8 byte array from the input string.       
	 * @param	input	input string.       
	 * @return	a utf8 byte array.       
	 */
	public static final String toUtf8 (String input) 
	{
		StringBuffer output = new StringBuffer ( input.length() );
		for( int i = 0; i < input.length(); i++ ) 
		{
			output.append( new String( toUtf8( input.charAt(i) ) ) );
		}
		return output.toString();
	}

	
	/**
	 * Returns a <code>Properties</code> object from an <code>Node</code>. The
	 * node must have child elements as follows:
	 * <p><pre>
	 *   <prop key="propertyKey">value</prop>
	 * </pre></p>
	 * @param node
	 * @return
	 */
	public static Properties getProperties(Node node) {
		
		Properties p = new Properties();
		NodeList nl = node.getChildNodes();
		
		for (int i = 0; i < nl.getLength(); i++) {
			Node n = nl.item(i);
			if (n.getNodeName().equals("prop")) {
				Element e = (Element)n;
				p.setProperty(
					e.getAttribute("key"),
					getText(n)
				);
			}
		}
		
		return p;
	}
	
	/**
	 * Returns a <code>Properties</code> object from an <code>org.dom4j.Element</code>. The
	 * node must have child elements as follows:
	 * <p><pre>
	 *   <prop key="propertyKey">value</prop>
	 * </pre></p>
	 * @param node
	 * @return
	 */
	public static Properties getProperties(org.dom4j.Element dom4jElement) {
		
		Properties p = new Properties();
		for (Iterator<?> it = dom4jElement.elementIterator(); it.hasNext(); ) {
			org.dom4j.Element n = (org.dom4j.Element)it.next();
			if (n.getName().equals("prop")) {
				p.setProperty(
					n.attribute("key").getValue(),
					n.getText()
				);
			}
		}
		
		return p;
	}
	
	/**
	 * Returns the text (#text child) value of an element.
	 * @return java.lang.String
	 * @param node org.w3c.dom.Node
	 */
	public static String getText(Node node) {

		StringBuffer sb = new StringBuffer();
		NodeList children = node.getChildNodes();
		if ( children != null ) {
			int len = children.getLength();
			for ( int i = 0; i < len; i++ ) {
				sb.append(children.item(i).getNodeValue());	
			}
		}

		return sb.toString();
	}
	
}
