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

import java.io.*;

/**
 * Stellt eine empf�ngene Datei der Upload-Klasse da.
 * 
 * Erstellungsdatum: (29.01.2002 09:09:12)
 * @author Jens Bornemann
 */
public class UploadFile {
	private ByteArrayOutputStream buffer = null;
	private String name = null;
	private String path = null;
	private java.io.File tmpFile = null;
/**
 * UploadFile - Stellt eine empfangene Datei der Upload-Klasse da.
 */
public UploadFile() {
	super();
}
/**
 * Raumt das Object weg und eine eventuell erstellte Tmp-Datei.
 * Erstellungsdatum: (16.01.2003 17:58:00)
 */
public void destroy() {

	if (tmpFile != null) {
		tmpFile.delete();
		tmpFile = null;
	}
	
}
/**
 * Code to perform when this object is garbage collected.
 * 
 * Any exception thrown by a finalize method causes the finalization to
 * halt. But otherwise, it is ignored.
 */
protected void finalize() throws Throwable {

	destroy();	
	super.finalize();
}
/**
 *
 * Erstellungsdatum: (27.05.2002 11:39:23)
 * @return java.lang.String
 * @param fileName java.lang.String
 */
private String findNewFileName(int number) {
	String newFileName = getNameWithoutExtension() + "(" + Integer.toString(number) + ")." + getExtension();
	File file = new File(getAbsolutePath());
	File newFile = new File(file.getParent(), newFileName);
	if (newFile.isFile() || newFile.isDirectory()) {
		return findNewFileName(++number);
	}
	return newFileName;
}
/**
 * Absolute Dateipfad
 * Erstellungsdatum: (29.01.2002 11:27:50)
 * @return java.lang.String
 */
private String getAbsolutePath() {
	if (path == null || name == null) return null;
	String sep = File.separator;
	if (path.endsWith(sep) || name.startsWith(sep)) {
		sep = "";
	}
	return path + sep + name;
}
/**
 * Returns file extension of the uploaded file.
 * Erstellungsdatum: (27.05.2002 11:42:56)
 * @return java.lang.String
 */
public String getExtension() {
	int dot = name.lastIndexOf(".");
	if (dot != -1) {
		return name.substring(dot + 1);
	}
	return "";
}
/**
 * Dateiname mit Dateiendung
 * Erstellungsdatum: (29.01.2002 11:23:32)
 * @return java.lang.String
 */
public String getName() {
	return name;
}
/**
 *
 * Erstellungsdatum: (27.05.2002 11:42:56)
 * @return java.lang.String
 */
private String getNameWithoutExtension() {
	int dot = name.lastIndexOf(".");
	if (dot != -1) return name.substring(0,dot);
	return name;
}
/**
 *
 * Erstellungsdatum: (29.01.2002 09:11:45)
 * @return int
 */
OutputStream getOutputStream() {
	if (buffer == null) buffer = new ByteArrayOutputStream();
	return buffer;
}
/**
 * Returns an InputStream of the uploaded file.
 *
 * @return InputStream
 */
public InputStream getInputStream() throws IOException {
	if (tmpFile != null) {
		// Daten aus Tmp-Datei lesen
		FileInputStream fis = new FileInputStream(tmpFile);
		return fis;
	} else if (buffer != null) {
		// Daten aus Speicher lesen
		ByteArrayInputStream bis = new ByteArrayInputStream(buffer.toByteArray());
		return bis;
	} else {
		// es gibt keine Daten
		return new ByteArrayInputStream(new byte[0]);
	}
}
/**
 *
 * Erstellungsdatum: (04.02.2002 15:45:28)
 * @return java.io.File
 */
java.io.File getTmpFile() {
	return tmpFile;
}
/**
 * Lange der Datei
 * Bei fehlerhaftem Empfang -1
 * Erstellungsdatum: (29.01.2002 09:11:45)
 * @return int
 */
public long length() {
	if (tmpFile != null) {
		return (int)tmpFile.length();
	} 
	if (buffer != null) {
		return buffer.size();
	}
	return -1;
}
/**
 * Setzen des Dateinamens
 * Erstellungsdatum: (29.01.2002 11:23:32)
 * @param newName java.lang.String
 */
void setName(String newName) {
	name = newName;
}
/**
 * Setzen des Pfades zur Datei
 * Erstellungsdatum: (29.01.2002 11:27:50)
 * @param newPath java.lang.String
 */
boolean setPath(String newPath) {
	boolean ret = true;

	File fPath = new File(newPath);
	if (!fPath.isDirectory()) ret = fPath.mkdirs();

	path = fPath.getAbsolutePath();
	
	return ret;
}
/**
 *
 * Erstellungsdatum: (04.02.2002 15:45:28)
 * @param newTmpFile java.io.File
 */
void setTmpFile(java.io.File newTmpFile) {
	tmpFile = newTmpFile;
}
/**
 * Speichern der Datei im angegeben Pfad unter angegebenem Dateiname
 *
 * Wurde kein Verzeichnis angegeben, ist das Verzeichnis wie in der Upload-Klasse definiert.
 * Wurde kein Dateiname angegeben, wird dieser aus dem Servlet-InputStream ubernommen.
 * Existiert eine Datei oder ein Verzeichniss unter dem Namen schon, wird ein "(1...x)" angehangt.
 * Erstellungsdatum: (29.01.2002 09:11:25)
 * Ruckgabe die geschriebenen Bytes, oder -1 wenn Verbindung zum Client verloren ging
 * @return int
 */
int write() throws IOException {
	int written = -1;

	FileInputStream fis = null;
	File testFile = new File(getAbsolutePath());
	if (testFile.isFile() || testFile.isDirectory()) setName(findNewFileName(1));

	FileOutputStream fos = new FileOutputStream(getAbsolutePath());

	try {
		if (tmpFile == null) {
			fos.write(buffer.toByteArray());
			written = buffer.size();
		} else {
			fis = new FileInputStream(tmpFile);

			byte buf[] = new byte[64 * 1024];
			written = 0;
			int read = 0;
			while ( (read = fis.read(buf)) != -1) {
				fos.write(buf,0,read);
				written += read;
			}
		}
	} finally {
		if (fis != null) fis.close();
		if (fos != null) {
			fos.flush();
			fos.close();
		}
		destroy();
	}

	return written;
}
/**
 * Speichern der Datei unter 'absolutePath', ruft write() auf
 * Ruckgabe die geschriebenen Bytes, oder -1 wenn Verbindung zum Client verloren ging
 * Erstellungsdatum: (29.01.2002 09:11:25)
 * @return int
 */
int write(String absolutePath) throws IOException {
	File pDir = new File(absolutePath);
	setPath(pDir.getParentFile().getPath());
	setName(pDir.getName());

	return write();
}
/**
 * Speichern der Datei unter 'path' als 'name', ruft setPath(path), setName(name) und write() auf
 * Ruckgabe die geschriebenen Bytes, oder -1 wenn Verbindung zum Client verloren ging
 * Erstellungsdatum: (29.01.2002 09:11:25)
 * @return int
 */
int write(String sPath, String sName) throws IOException {
	setPath(sPath);
	setName(sName);

	return write();
}
}
