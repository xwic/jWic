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
package de.jwic.upload;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletInputStream;

/**
 * Parst den Inhalt einer Multipart / Data Form
 */

public class ContentParser {
  
  private ServletInputStream in;
  private String boundary;
  private byte[] buf = new byte[8 * 1024];
  private Content content;
  
/**
 * Parst den Inhalt einer Multipart/Data Form
 */ 
public ContentParser(HttpServletRequest req, long maxSize) throws IOException {

	// uberprufe Inhalt auf enctype="multipart/form-data"
	// Zugriff erfolgt auf zwei Wegen
	
	String sType = null;
	String sType1 = req.getHeader("Content-Type");
	String sType2 = req.getContentType();
	
	// Wenn einer von beiden Werten NULL ist, einfach den anderen nehmen.
	if (sType1 == null && sType2 != null) {
	  sType = sType2;
	}
	else if (sType2 == null && sType1 != null) {
	  sType = sType1;
	}	
	// Wenn keiner von beiden NULL ist, den langeren nehmen.
	else if (sType1 != null && sType2 != null) {
	  sType = (sType1.length() > sType2.length() ? sType1 : sType2);
	}

	// Wenn beide NULL sind handelt es sich vermutlich um kein enctype="multipart/form-date"
	if (sType == null || 
		!sType.toLowerCase().startsWith("multipart/form-data")) {
	  throw new IOException("Posted content type isn't multipart/form-data");
	}


	// uberprufen der Grosse des Inhaltes.
	int length = req.getContentLength();
	if (length > maxSize) {
	  throw new IOException("Posted content length of " + length + 
							" exceeds limit of " + maxSize);
	}

	// Ermitteln der Grenzlinie
	boundary = extractBoundary(sType);
	if (boundary == null) {
	  throw new IOException("Separation boundary was not specified");
	}

	in = req.getInputStream();
	
	// Erste Zeile einlesen
	String line = readLine();
	if (line == null) {
	  throw new IOException("Corrupt form data: premature ending");
	}

	// Ergebnis uberprufen ob boundary = Erstezeile
	if (!line.startsWith(boundary)) {
	  throw new IOException("Corrupt form data: no leading boundary: " +
							line + " != " + boundary);
	}
  }  
/**
 * Ermittelt die Grenzlinie die die einzelnen Felder begrenzt
 * Erstellungsdatum: (08.03.01 19:41:46)
 * @param line java.lang.String
 */   
private String extractBoundary(String line) {
	
	int index = line.lastIndexOf("boundary=");
	if (index == -1) {
	  return null;
	}
	
	String bound = line.substring(index + 9);
	if (bound.charAt(0) == '"') {
	  index = bound.lastIndexOf('"');
	  bound = bound.substring(1, index);
	}
	
	bound = "--" + bound;

	return bound;
  }        
/**
 * Gibt den ContentType zuruck
 * Erstellungsdatum: (08.03.01 19:41:46)
 * @param line java.lang.String
 */   
private String extractContentType(String line) throws IOException {
	String contentType = null;
	String origline = line;
	line = origline.toLowerCase();

	if (line.startsWith("content-type")) {
	  int start = line.indexOf(" ");
	  if (start == -1) {
		throw new IOException("Content type corrupt: " + origline);
	  }
	  contentType = line.substring(start + 1);
	}
	else if (line.length() != 0) {
	  throw new IOException("Malformed line after disposition: " + origline);
	}

	return contentType;
  }    
/**
 * Gibt die Inhaltsangaben zuruck
 * Erstellungsdatum: (08.03.01 19:41:46)
 * @param line java.lang.String
 */   
private String[] extractDispositionInfo(String line) throws IOException {
		 
	String[] retval = new String[4];
	
	String origline = line;
	line = origline.toLowerCase();

	// Lese "content disposition" und "form-data" ein.
	int start = line.indexOf("content-disposition: ");
	int end = line.indexOf(";");
	if (start == -1 || end == -1) {
	  throw new IOException("Content disposition corrupt: " + origline);
	}
	String disposition = line.substring(start + 21, end);
	if (!disposition.equals("form-data")) {
	  throw new IOException("Invalid content disposition: " + disposition);
	}

	// Lese "name" ein.
	start = line.indexOf("name=\"", end);
	end = line.indexOf("\"", start + 7);
	if (start == -1 || end == -1) {
	  throw new IOException("Content disposition corrupt: " + origline);
	}
	String name = origline.substring(start + 6, end);

	// Lese "filename" ein, wenn vorhanden.
	String filename = null;
	String origname = null;
	start = line.indexOf("filename=\"", end + 2);
	end = line.indexOf("\"", start + 10);
	if (start != -1 && end != -1) {
	  filename = origline.substring(start + 10, end);
	  origname = filename;
	  int slash =
		Math.max(filename.lastIndexOf('/'), filename.lastIndexOf('\\'));
	  if (slash > -1) {
		filename = filename.substring(slash + 1);
	  }
	}

	//Bef�lle R�ckgabe Array
	retval[0] = disposition;
	retval[1] = name;
	retval[2] = filename;
	retval[3] = origname;
	return retval;
  }  
/**
 * Liest die nachste Zeile ein
 * Erstellungsdatum: (08.03.01 19:41:46)
 * @return java.lang.String
 */   
private String readLine() throws IOException {
	  
	StringBuffer sbuf = new StringBuffer();
	int result;
	//String line;
	
	do {
	  result = in.readLine(buf, 0, buf.length);
	  if (result != -1) {
		  sbuf.append(new String(buf, 0, result, "ISO-8859-1"));
	  }
	  
	} while (result == buf.length);

	if (sbuf.length() == 0) {
	  return null;
	}

	sbuf.setLength(sbuf.length() - 2);  // die letzten beiden Zeichen abschneiden \r\n
	return sbuf.toString();
  }  
/**
 * Gibt den nachsten Inhalt / Feld zuruck
 * Erstellungsdatum: (08.03.01 19:41:46)
 * @exception java.io.IOException
 */   

public Content readNextContent() throws IOException {
	
	if (content != null) {
	  content.close();
	  content = null;
	}  
	  	
	Vector<String> headers = new Vector<String>();

	String line = readLine();
	if (line == null) {
		return null;
	}else if (line.length() == 0) {
		return null;
	}
	
	headers.addElement(line);
	
	while ((line = readLine()) != null && (line.length() > 0)) {
	  headers.addElement(line);
	}

	if (line == null) {
		return null;
	}

	String name = null;
	String filename = null;
	//String origname = null;
	//String contentType = "text/plain";  // rfc1867 says this is the default

	//Content-Disposition und Content-Type auswerten
	Enumeration<String> en = headers.elements();
	while (en.hasMoreElements()) {
	  String headerline = en.nextElement();
	  if (headerline.toLowerCase().startsWith("content-disposition:")) {
		String[] dispInfo = extractDispositionInfo(headerline);
		name = dispInfo[1];
		filename = dispInfo[2];
		//origname = dispInfo[3];
	  }
	  else if (headerline.toLowerCase().startsWith("content-type:")) {
		String type = extractContentType(headerline);
		if (type != null) {
		  //contentType = type;
		}
	  }
	}

	// Inhalt einlesen
	if (filename == null) {
	   content = new Content(in, boundary, filename, name);	
	}else {
	  if (filename.equals("")) filename = null;
 	  content = new Content(in, boundary, filename, name);
	}
	return content;
  }    
}
