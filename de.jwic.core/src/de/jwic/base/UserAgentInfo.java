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

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

/**
 * Contains information about the user agent (browser). The type and version is
 * identified from the requests header informations. 
 * @author Florian Lippisch
 * @version $Revision: 1.6 $
 */
public class UserAgentInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public final static String TYPE_IE = "IE";
	public final static String TYPE_NS = "NS";
	public final static String TYPE_FIREFOX = "FIREFOX";
	public final static String TYPE_OPERA = "OPERA";
	public final static String TYPE_KONQUEROR = "KONQUEROR";
	public final static String TYPE_UNKNOWN = "UNKNOWN";
	public final static String TYPE_CHROME = "CHROME";
	
	private final static String IDENTIFIER_IE = "compatible; MSIE ";
	private final static String IDENTIFIER_FIREFOX = "Firefox/";
	private final static String IDENTIFIER_OPERA = "Opera/";
	private final static String IDENTIFIER_NETSCAPE = "Netscape/";
	private final static String IDENTIFIER_KONQUEROR = "Konqueror/";
	private final static String IDENTIFIER_GOOGLEBOT = "Googlebot";
	private final static String IDENTIFIER_CHROME = "Chrome/";
	
	private String type = TYPE_UNKNOWN;
	private String userAgentID = null;
	private String version = "";
	
	private boolean bot = false;
	
	private int clientWidth = 0;
	private int clientHeight = 0;
	private int clientTop = 0;
	private int clientLeft = 0;
	
	
	/**
	 * Construct a new UserAgentInfo based upon the header informations in the request.
	 * @param request
	 */
	public UserAgentInfo(HttpServletRequest request) {
		
		if (request != null) {
			userAgentID = request.getHeader("user-agent");
			if (userAgentID != null) {
				
				int idx = userAgentID.indexOf(IDENTIFIER_IE);
				if (idx != -1) {
					// its an IE
					setType(TYPE_IE);
					// identify the version
					int end = userAgentID.indexOf(";", idx + IDENTIFIER_IE.length());
					if (end != -1) {
						setVersion(userAgentID.substring(idx + IDENTIFIER_IE.length(), end));
					}
				} else if((idx = userAgentID.indexOf(IDENTIFIER_FIREFOX)) != -1) {
					// its a firefox
					setType(TYPE_FIREFOX);
					// identify the version
					setVersion(userAgentID.substring(idx + IDENTIFIER_FIREFOX.length()));
					
				} else if((idx = userAgentID.indexOf(IDENTIFIER_OPERA)) != -1) {
					// its a opera
					setType(TYPE_OPERA);
					// identify the version
					int end = userAgentID.indexOf(" ", idx + IDENTIFIER_OPERA.length());
					if (end != -1) {
						setVersion(userAgentID.substring(idx + IDENTIFIER_OPERA.length(), end));
					}
				} else if((idx = userAgentID.indexOf(IDENTIFIER_KONQUEROR)) != -1) {
					// its a opera
					setType(TYPE_KONQUEROR);
					// identify the version
					int end = userAgentID.indexOf(")", idx + IDENTIFIER_KONQUEROR.length());
					if (end != -1) {
						setVersion(userAgentID.substring(idx + IDENTIFIER_KONQUEROR.length(), end));
					}
				} else if((idx = userAgentID.indexOf(IDENTIFIER_NETSCAPE)) != -1) {
					// its a opera
					setType(TYPE_NS);
					// identify the version
					setVersion(userAgentID.substring(idx + IDENTIFIER_NETSCAPE.length()));
				
				} else if ((idx = userAgentID.indexOf(IDENTIFIER_CHROME)) != -1) {
					setType(TYPE_CHROME);
					String v = userAgentID.substring(idx + IDENTIFIER_CHROME.length());
					int space = v.indexOf(' '); 
					if (space != -1) {
						v = v.substring(0, space);
					}
					setVersion(v);
					
				} else if (userAgentID.indexOf(IDENTIFIER_GOOGLEBOT) != -1) {
					bot = true;
					setType("GOOGLEBOT");
	
				} else if (userAgentID.indexOf("NutchCVS") == 0) {
					bot = true;
	
				} else if (userAgentID.indexOf("msnbot") == 0) {
					bot = true;
					setType("MSNBOT");
	
				} else if (userAgentID.indexOf("Bot") != -1 || 
							userAgentID.indexOf("bot") != -1) {
					// an unknown bot
					bot = true;
					
				} else if (userAgentID.indexOf("Mozilla/4.76") == 0) {
					setType(TYPE_NS);
					setVersion("4.7");
				} 
			}
		}
	}
	
	/**
	 * Returns true if the user agent is an Internet Explorer.
	 * @return
	 */
	public boolean isIE() {
		return type.equals(TYPE_IE);
	}
	
	/**
	 * Returns true if the user agent is a Netscape.
	 * @return
	 */
	public boolean isNS() {
		return type.equals(TYPE_NS);
	}

	/**
	 * Returns true if the user agent is a Firefox.
	 * @return
	 */
	public boolean isFirefox() {
		return type.equals(TYPE_FIREFOX);
	}
	
	/**
	 * Returns true if the user agent is Opera.
	 * @return
	 */
	public boolean isOpera() {
		return type.equals(TYPE_OPERA);
	}
	
	/**
	 * Returns true if the user agent is Konqueror.
	 * @return
	 */
	public boolean isKonqueror() {
		return type.equals(TYPE_KONQUEROR);
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	private void setType(String type) {
		this.type = type;
	}

	/**
	 * @return Returns the userAgentID.
	 */
	public String getUserAgentID() {
		return userAgentID;
	}

	/**
	 * @return Returns the version.
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version The version to set.
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Returns a string representation of this user agent.
	 */
	public String toString() {
		return type + " " + version;
	}

	/**
	 * @return Returns the bot.
	 */
	public boolean isBot() {
		return bot;
	}

	/**
	 * @return the clientWidth
	 */
	public int getClientWidth() {
		return clientWidth;
	}

	/**
	 * @param clientWidth the clientWidth to set
	 */
	public void setClientWidth(int clientWidth) {
		this.clientWidth = clientWidth;
	}

	/**
	 * @return the clientHeight
	 */
	public int getClientHeight() {
		return clientHeight;
	}

	/**
	 * @param clientHeight the clientHeight to set
	 */
	public void setClientHeight(int clientHeight) {
		this.clientHeight = clientHeight;
	}

	/**
	 * @return the clientTop
	 */
	public int getClientTop() {
		return clientTop;
	}

	/**
	 * @param clientTop the clientTop to set
	 */
	public void setClientTop(int clientTop) {
		this.clientTop = clientTop;
	}

	/**
	 * @return the clientLeft
	 */
	public int getClientLeft() {
		return clientLeft;
	}

	/**
	 * @param clientLeft the clientLeft to set
	 */
	public void setClientLeft(int clientLeft) {
		this.clientLeft = clientLeft;
	}

	/**
	 * @param userAgentID the userAgentID to set
	 */
	public void setUserAgentID(String userAgentID) {
		this.userAgentID = userAgentID;
	}

	/**
	 * Returns the major version number. If no version number
	 * was detected, 0 is returned.
	 * @return
	 */
	public int getMajorVersion() {
		if (version != null) {
			try {
				int idx = version.indexOf('.');
				if (idx != -1) {
					return Integer.parseInt(version.substring(0, idx));
				}
				return Integer.parseInt(version);
			} catch (NumberFormatException nfe) {
				return 0;
			}
		}
		return 0;
	}
	
}
