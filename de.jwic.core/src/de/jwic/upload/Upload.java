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

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * Standard Upload 
 */

public class Upload {

    private static final int DEFAULT_MAX_POST_SIZE = 1024 * 1024; // 1 Meg
    private static final int DEFAULT_MEMORY_USAGE = 1 * 1024 * 1024; // 1 Meg
    private static final String FIELD_PREFIX = "FLD";
    private HashMap<String, String> htFieldValue = null;
    @SuppressWarnings("rawtypes")
	private HashMap htFields = null;
    private HashMap<String, UploadFile> htFiles = null;
    private HashMap<String, List<String>> htParams = null;
    private static final String FIELD_ORGFILENAME_SUFFIX = "@@ORGFILENAME";
    private boolean lostConnection = false;
	/**
	 * Standard Upload
	 * @param req javax.servlet.http.HttpServletRequest
	 * @param sDirSave java.lang.String Zielverzeichnis
	 * @param sFileName java.lang.String Zielname der Datei
	 * @param maxPostSize int max. Dateigrosse fur die ein Upload getatigt werden soll.
	 * @exception java.io.IOException
	 */
	 
	public Upload(HttpServletRequest req) throws IOException {
		this(req, ".", DEFAULT_MAX_POST_SIZE, DEFAULT_MEMORY_USAGE);
	}
	/**
	 * Standard Upload
	 * @param req javax.servlet.http.HttpServletRequest
	 * @param sDirSave java.lang.String Zielverzeichnis
	 * @param sFileName java.lang.String Zielname der Datei
	 * @param maxPostSize int max. Dateigrosse fur die ein Upload getatigt werden soll.
	 * @exception java.io.IOException
	 */
	 
	public Upload(HttpServletRequest req,  long maxPostSize) throws IOException {
		this(req, ".", maxPostSize, DEFAULT_MEMORY_USAGE);
	}
	/**
	 * Standard Upload
	 * @param req javax.servlet.http.HttpServletRequest
	 * @param sDirSave java.lang.String
	 * @exception java.io.IOException
	 * @deprecated
	 */
	public Upload(HttpServletRequest req, String sDirSave)throws IOException {
	
		this(req, sDirSave, DEFAULT_MAX_POST_SIZE);
		
	}
	/**
	 * Standard Upload
	 * @param req javax.servlet.http.HttpServletRequest
	 * @param sDirSave java.lang.String Zielverzeichnis
	 * @param maxPostSize int max. Dateigrosse fur die ein Upload getatigt werden soll.
	 * @exception java.io.IOException
	 * @deprecated
	 */
	public Upload(HttpServletRequest req, String sDirSave, long maxPostSize)	throws IOException {
	
		this(req, sDirSave, null, maxPostSize);
	}
	/**
	 * Standard Upload
	 * @param req javax.servlet.http.HttpServletRequest
	 * @param sDirSave java.lang.String Tempor�res Arbeitsverzeichnis
	 * @param maxPostSize int max. Dateigrosse fur die ein Upload getatigt werden soll.
	 * @param memoryUsage int max. Speichernutzung, bei uberschreiten wird eine temp. Datei erstellt
	 * @exception java.io.IOException
	 */
	 
	@SuppressWarnings("rawtypes")
	public Upload(HttpServletRequest req, String sDirSave, long maxPostSize, int memoryUsage) throws IOException {
	
		String FieldName = null;
		String FieldValue = null;
		
		
		// Allgemeine �berpr�fungen
		if (req == null)
			throw new IllegalArgumentException("request cannot be null");
		if (sDirSave == null)
			throw new IllegalArgumentException("saveDirectory cannot be null");
		if (maxPostSize <= 0) 
			throw new IllegalArgumentException("maxPostSize must be positive");
		if (memoryUsage <= 0) 
			throw new IllegalArgumentException("memoryUsage must be positive");
	
		// Save Directory generieren
		File fDir = new File(sDirSave);
	
		// uberprufen ob File ein Directory ist
		if (!fDir.isDirectory()) fDir.mkdirs();
	
		// uberprufen ob in Directory geschrieben werden kann
		if (!fDir.canWrite())
			throw new IOException("Not writable: " + sDirSave);
	
		//InputStream parsen
		ContentParser parser = new ContentParser(req, maxPostSize);
		Content content = null;
	
		//HashMap fur Felder und Inhalt definieren
		htFieldValue = new HashMap<String, String>();
		htFields = new HashMap();
		htFiles = new HashMap<String, UploadFile>();
		htParams = new HashMap<String, List<String>>();
	
		while ((content = parser.readNextContent()) != null) {
			if (content.isFile()) {
				String fileName = content.getFileName();
				if (fileName != null) {
					FieldName = content.getFieldName();
					htFieldValue.put(FieldName.substring(FIELD_PREFIX.length()) + FIELD_ORGFILENAME_SUFFIX, fileName);
	
					UploadFile upFile = new UploadFile();
					upFile.setPath(sDirSave);
					upFile.setName(fileName);
					File tmpFile = write(upFile.getOutputStream(), content, memoryUsage);
	
					if (tmpFile != null) {
						upFile.setTmpFile(tmpFile);
						if (lostConnection) tmpFile.delete();
					}
	
					htFiles.put(content.getFieldName(), upFile);
					
					FieldValue = fileName;
				}				
			}else{
				FieldValue = getValue(content);
			}
	
			if (!content.isFile()) {
				if(!htParams.containsKey(content.getFieldName())){
					htParams.put(content.getFieldName(), new LinkedList<String>());
				}
				htParams.get(content.getFieldName()).add(FieldValue);
			}
		}
	}
	/**
	 * Standard Upload
	 * @param req javax.servlet.http.HttpServletRequest
	 * @param sDirSave java.lang.String Zielverzeichnis
	 * @param sFileName java.lang.String Zielname der Datei
	 * @exception java.io.IOException
	 * @deprecated
	 */
	public Upload(HttpServletRequest req, String sDirSave, String sFileName) throws IOException {
	
		this(req, sDirSave, sFileName, DEFAULT_MAX_POST_SIZE);
	}
	/**
	 * Standard Upload
	 * @param req javax.servlet.http.HttpServletRequest
	 * @param sDirSave java.lang.String Zielverzeichnis
	 * @param sFileName java.lang.String Zielname der Datei
	 * @param maxPostSize int max. Dateigr��e f�r die ein Upload get�tigt werden soll.
	 * @exception java.io.IOException
	 * @deprecated
	 */
	 
	@SuppressWarnings("rawtypes")
	public Upload(HttpServletRequest req, String sDirSave, String sFileName, long maxPostSize) throws IOException {
	
		String FieldName = null;
		String FieldValue = null;
		
		
		// Allgemeine uberprufungen
		if (req == null)
			throw new IllegalArgumentException("request cannot be null");
		if (sDirSave == null)
			throw new IllegalArgumentException("saveDirectory cannot be null");
		if (maxPostSize <= 0) {
			throw new IllegalArgumentException("maxPostSize must be positive");
		}
	
		// Save Directory generieren
		File fDir = new File(sDirSave);
	
		// uberprufen ob File ein Directory ist
		if (!fDir.isDirectory()) fDir.mkdirs();
	
		// uberprufen ob in Directory geschrieben werden kann
		if (!fDir.canWrite())
			throw new IOException("Not writable: " + sDirSave);
	
		//InputStream parsen
		ContentParser parser = new ContentParser(req, maxPostSize);
		Content content = null;
	
		//HashMap fur Felder und Inhalt definieren
		htFieldValue = new HashMap<String, String>();
		htFields = new HashMap();
		htParams = new HashMap<String, List<String>>();
	
		while ((content = parser.readNextContent()) != null) {
			if (content.isFile()) {
				String fileName = content.getFileName();
				if (fileName != null) {
					FieldName = content.getFieldName().toUpperCase();
					htFieldValue.put(FieldName.substring(FIELD_PREFIX.length()) + FIELD_ORGFILENAME_SUFFIX, fileName);
					if (sFileName != null) 
						fileName = sFileName + fileName.substring(fileName.lastIndexOf("."));
					writeTo(fDir, content, fileName);
					FieldValue = fileName;
				}				
			}else{
				FieldValue = getValue(content);
			}
	
			if (!content.isFile()) {
				if(!htParams.containsKey(content.getFieldName())){
					htParams.put(content.getFieldName(), new LinkedList<String>());
				}
				htParams.get(content.getFieldName()).add(FieldValue);
			}
		}
	}
	/**
	 * Sollte neben dem Dateiupload noch weitere Felder in dem HTML - Formular
	 * gewesen sein sollen, dann konnen Sie uber diese Methode auf die Felder
	 * und deren Inhalte zugreifen.
	 * @param sFieldName java.lang.String
	 */
	public double getFieldDouble(String sFieldName) {
	
		String value = htFieldValue.get(sFieldName.toUpperCase());
		if (value != null) return new Double(value).doubleValue();
		return 0;
		
	}
	/**
	 * Sollte neben dem Dateiupload noch weitere Felder in dem HTML - Formular
	 * gewesen sein sollen, dann konnen Sie uber diese Methode auf die Felder
	 * und deren Inhalte zugreifen.
	 * @param sFieldName java.lang.String
	 */
	public int getFieldInteger(String sFieldName) {
	
		String value = htFieldValue.get(sFieldName.toUpperCase());
		if (value != null) return new Integer(value).intValue();
		return 0;
		
	}
	/**
	 * Feld Datentyp
	 * Erstellungsdatum: (08.03.01 19:41:46)
	 * @param sFieldName java.lang.String
	 */ 
	@SuppressWarnings("rawtypes")
	public Map getFields() {
		return htFields;
	}
	/**
	 * Gibt die parameter zuruck welche nicht als Felder eingelesen wurden.
	 */
	public Map<String, List<String>> getParams() {
		return htParams;
	}
	/**
	 * Sollte neben dem Dateiupload noch weitere Felder in dem HTML - Formular
	 * gewesen sein sollen, dann konnen Sie uber diese Methode auf die Felder
	 * und deren Inhalte zugreifen.
	 * @param sFieldName java.lang.String
	 */
	public String getFieldString(String sFieldName) {
	
		return htFieldValue.get(sFieldName.toUpperCase());
		
	}
	
	/**
	 * Liest den Inhalt eines Feldes aus.
	 * Erstellungsdatum: (08.03.01 19:41:46)
	 */
	 
	public Map<String, UploadFile> getFiles() {
		return htFiles;
	}
	/**
	 * Gibt den Namen der Datei an.
	 * Erstellungsdatum: (08.03.01 19:41:46)
	 * @param sFieldName java.lang.String
	 */ 
	public String getOrgFileName(String sFieldName) {
		return getFieldString(sFieldName + FIELD_ORGFILENAME_SUFFIX);
	}
	/**
	 * Liest den Inhalt eines Feldes aus.
	 * Erstellungsdatum: (08.03.01 19:41:46)
	 */
	 
	private String getValue(Content content) throws IOException {
		
		ByteArrayOutputStream baOutStream = null;
		byte[] buf = new byte[128];
		int read = 0;
	
		baOutStream = new ByteArrayOutputStream(512);
		
		while ((read = content.read(buf)) != -1) {
		  baOutStream.write(buf, 0, read);
		}
		
		content.close();
		baOutStream.close();
		
		// save it for later
		return baOutStream.toString("UTF-8");
	}
	/**
	 * Sollte neben dem Dateiupload noch weitere Felder in dem HTML - Formular
	 * gewesen sein sollen, dann konnen Sie uber diese Methode auf die Felder
	 * und deren Inhalte zugreifen.
	 * @param sFieldName java.lang.String
	 */
	public void setFieldString(String sFieldName, String sValue) {
	
		htFieldValue.put(sFieldName, sValue);
		
	}
	/**
	 * Schreibt die Datei in den Outputstream
	 * Erstellungsdatum: (08.03.01 19:41:46)
	 * @param out java.io.OutputStream
	 */   
	
	private long write(OutputStream out, Content content) throws IOException {
		
		long size=0;
		int read;
		byte[] buf = new byte[64 * 1024];
		while((read = content.read(buf)) != -1) {
		  out.write(buf, 0, read);
		  size += read;
		}
		return size;
	  }    
	/**
	 * Schreibt die Datei in den Outputstream
	 * Erstellungsdatum: (08.03.01 19:41:46)
	 * @param out java.io.OutputStream
	 */   
	
	private File write(OutputStream out, Content content, int memoryUsage) throws IOException {
		
		File tmpFile = null;
		long size=0;
		int read=0;
		byte[] buf = new byte[memoryUsage];
	
		try {
			try {	
				while((read = content.read(buf)) != -1) {
				  	size += read;
					if (size >= memoryUsage) {
						// store it in a new temporary file since max memory usage is reached
						if (tmpFile == null) {
							tmpFile = File.createTempFile("UPLOAD",".FILE");
							out = new FileOutputStream(tmpFile);
						}
					}
				  out.write(buf, 0, read);
				}
			} catch (java.net.SocketException se) {
				// server lost client
				lostConnection = true;
			}
	
		} finally {
			if (tmpFile != null) {
				out.flush();
				out.close();
			}
		}
		return tmpFile;
	}    
	/**
	 * Schreibt die Datei in das Zielverzeichnis
	 * Erstellungsdatum: (08.03.01 19:41:46)
	 * @param fileOrDirectory java.io.File
	 * @param sFieldName java.lang.String
	 */ 
	private File writeTo(File fileOrDirectory, Content content, String fileName) throws IOException {
				
		OutputStream fileOut = null;
		File file = null;
	
		try{
		    if (fileName != null) {
			
				if (fileOrDirectory.isDirectory()) {
			 		file = new File(fileOrDirectory, fileName);
				} else {
			  		file = fileOrDirectory;
				}
			
			fileOut = new BufferedOutputStream(new FileOutputStream(file));
			write(fileOut, content);
			
		    }
		}
			
		finally {
		  if (fileOut != null) fileOut.close();
		}	
	
		return file;
		
	}
}
