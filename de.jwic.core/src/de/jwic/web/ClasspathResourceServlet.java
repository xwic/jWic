/*
 * Copyright 2005 jWic Group (http://www.jwic.de)
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
 * de.jwic.web.ClasspathResourceServlet
 * Created on 14.04.2005
 * $Id: ClasspathResourceServlet.java,v 1.3 2006/08/14 09:34:59 lordsam Exp $
 */
package de.jwic.web;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Returns a file resource from the classpath. The servlet must be
 * mapped to the path /cp/ to function properly.
 * @author Florian Lippisch
 * @version $Revision: 1.3 $
 */
public class ClasspathResourceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected final Log log = LogFactory.getLog(getClass());
    
    private String[] allowedTypes =  { ".gif", ".jpeg", ".js", ".css", ".png" };
    private String servletId = "/cp/";
	private long startup = 0;
	
	/**
	 * Default constructor.
	 *
	 */
	public ClasspathResourceServlet() {
		startup = System.currentTimeMillis();
	}
	
	protected long getLastModified(HttpServletRequest arg0) {
		return startup;
		
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String fileLocation = getFileLocation(req);
		String fileName;
		if (fileLocation != null) {
			fileName = getFileName(fileLocation);
		} else {
			fileName = null;
		}
		
		if (fileName == null) {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		if (!isAllowedType(fileName)) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		
		// prepare the dowload
		InputStream in = null;
		try{

			// get the file data as an InputStream
			in = getClass().getClassLoader().getResourceAsStream(fileLocation);
			
		}catch(Exception e){
			in = null;
			log.error("Error reading resource " + fileLocation, e);
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
			return;
		}
		
		if (in == null) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		String mimeType = getServletContext().getMimeType(fileName);
		if (mimeType == null) {
			mimeType = "application/x-msdownload";
		}
		// set the response header
		
		
	  	res.setContentType(mimeType);
		res.setHeader ("Content-Disposition","filename="+fileName+";");
		res.setHeader ("Cache-Control", "max-age=" + (60*60*24));	// 24 hours
	  	res.setContentLength(in != null ? in.available() : 0);

		ServletOutputStream out = res.getOutputStream();

		//send the file as a stream of bytes
		int transfered = 0;
		try {
			byte[] buf = new byte[1024];
			int length = 0;

			while ((in != null) && ((length = in.read(buf)) != -1))
			{
				try {
					out.write(buf,0,length);
				} catch (Exception e1) {
					// error with client
					log.error("Error sending data to client", e1);
					return;
				}
				transfered += length;
				// for debugging !!!
				//long start = System.currentTimeMillis();
				//while (System.currentTimeMillis()-start<1000) {}
			}
		} catch (Exception e2) {
			// error with server
			log.error("Error reading data", e2);
			return;
		} finally {
			in.close();
			out.flush();
			out.close();
		}

		log.debug("Access to ClasspathResource '" + fileLocation + "'");
		
	}

	/**
	 * @param fileName
	 * @return
	 */
	private boolean isAllowedType(String fileName) {
		
		int pos = fileName.lastIndexOf('.');
		String suffix = ".";
		if (pos != -1) {
			suffix = fileName.substring(pos);
		}
		
		for (int i = 0; i < allowedTypes.length; i++) {
			if (allowedTypes[i].equals(suffix)) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * @param req
	 * @return
	 */
	private String getFileLocation(HttpServletRequest req) {
		
		String uri = req.getRequestURI();
		int i = uri.indexOf(servletId);
		if (i != -1) {
			return uri.substring(i + servletId.length());
		}
		return null;
		
	}
	/**
	 * Returns the filename in the filePath.
	 * @param filePath
	 * @return
	 */
	private String getFileName(String filePath) {
		
		int i = filePath.lastIndexOf('/');
		if (i != -1) {
			return filePath.substring(i + 1);
		}
		return null;
	}
	
}
