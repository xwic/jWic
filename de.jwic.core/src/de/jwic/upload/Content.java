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

import java.io.FilterInputStream;
import java.io.IOException;
import javax.servlet.ServletInputStream;

/**
 * Reprasentiert den Inhalt der Multipart / from-data
 */

public class Content extends FilterInputStream {

  private String boundary;
  private byte [] buf = new byte[64*1024];  // 64k
  private int count; 
  private int pos;
  private boolean eof;
  private String filename;
  private boolean isFile;  
  private String fieldname;
  
 
/**
 * Reprasentiert den Inhalt der Multipart / form-data
 * Erstellungsdatum: (08.03.01 09:01:38)
 * @param in javax.servlet.ServletInputStream
 * @param boundary java.lang.String Grenzlinie. Zeigt den Anfang des nachsten Feldes an
 * @param sFileName java.lang.String
 * @param sFieldName java.lang.String
 * @exception java.io.IOException
 */
 
public Content(ServletInputStream in, String boundary, String sFileName, String sFieldName) {
	super(in);
	this.boundary = boundary;
	this.isFile = (sFileName != null);
	this.filename = sFileName;
	this.fieldname = sFieldName;
	
  }        
/**
 * Dateiende erreicht?
 * @exception java.io.IOException
 */ 
public int available() throws IOException {
	int avail = (count - pos - 2) + in.available();
	return (avail < 0 ? 0 : avail);
  }    
/**
 * Datei solange einlesen bis Dateiende erreicht.
 * @exception java.io.IOException
 */ 
public void close() throws IOException {
	if (!eof) {
	  while (read(buf, 0, buf.length) != -1)
		; // nichts machen
	}
  }    
/**
 * Datei bis zur nachsten Grenzlinie einlesen
 * @exception java.io.IOException
 */

private void fill() throws IOException
  {
	if (eof)
	  return;
	
	if (count > 0)
	{
	  
	  if (count - pos == 2) {
		System.arraycopy(buf, pos, buf, 0, count - pos);
		count -= pos;
		pos = 0;
	  } else {
		throw new IllegalStateException("fill() detected illegal buffer state");
	  }
	}
	
	int read = 0;
	while (count + boundary.length() + 2 < buf.length) {
	  read = ((ServletInputStream)in).readLine(buf, count, buf.length - count);
	  if (read != -1) {
		if (read >= boundary.length()) {
		  eof = true;
		  for (int i=0; i < boundary.length(); i++) {
			if (boundary.charAt(i) != buf[count + i]) {
			  eof = false;
			  break;
			}
		  }
		  if (eof) {
			break;
		  }
		}
		count += read;
	  } else {
		  eof = true;
	  }
	}
  }    
/**
 * Gibt den Namen des aktuellen Feldes zuruck
 * @return java.lang.String
 */
public String getFieldName() {
	return fieldname;
}
/**
 * Gibt den Dateinamen zuruck
 */
public String getFileName() {
	return filename;
}
/**
 * Beinhaltete der aktuelle Abschnitt eine Datei?
 * @return boolen
 */
public boolean isFile() {
	return isFile;
}
  public int read() throws IOException {
	if (count - pos <= 2) {
	  fill();
	  if (count - pos <= 2) {
		return -1;
	  }
	}
	return buf[pos++] & 0xff;
  }
 /**
  * Daten Zeile fur Zeile, Byte fur Byte auslesen.
  * @exception java.io.IOException
  */ 
 public int read(byte b[], int off, int len) throws IOException
  {
	int total = 0;
	if (len == 0) {
	  return 0;
	}

	int avail = count - pos - 2;
	if (avail <= 0) {
	  fill();
	  avail = count - pos - 2;
	  if(avail <= 0) {
		return -1;
	  }
	}
	int copy = Math.min(len, avail);
	System.arraycopy(buf, pos, b, off, copy);
	pos += copy;
	total += copy;
	  
	while (total < len) {
	  fill();
	  avail = count - pos - 2;
	  if(avail <= 0) {
		return total;
	  }
	  copy = Math.min(len - total, avail);
	  System.arraycopy(buf, pos, b, off + total, copy);
	  pos += copy;
	  total += copy;
	}
	return total;
  }  
}
