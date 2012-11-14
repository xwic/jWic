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
 * de.jwic.sourceviewer.viewer.impl.ImageViewer
 * Created on 27.04.2007
 * $Id: ImageViewer.java,v 1.4 2007/05/03 14:27:46 aroncotrau Exp $
 */
package de.jwic.sourceviewer.viewer.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.IResourceControl;
import de.jwic.sourceviewer.main.SVModel;
import de.jwic.sourceviewer.model.FileElement;
import de.jwic.sourceviewer.model.FileType;
import de.jwic.sourceviewer.model.NavigationElement;
import de.jwic.sourceviewer.viewer.IObjectViewer;

/**
 *
 * @author Florian Lippisch
 */
public class ImageViewer extends Control implements IObjectViewer, IResourceControl {

	private Set supportedFiles = new HashSet();
	private File file = null;
	
	/**
	 * @param container
	 * @param name
	 */
	public ImageViewer(IControlContainer container, String name) {
		super(container, name);
	
		supportedFiles.add(FileType.IMAGE);

	}

	/* (non-Javadoc)
	 * @see de.jwic.sourceviewer.viewer.IObjectViewer#isSupported(de.jwic.sourceviewer.model.NavigationElement)
	 */
	public boolean isSupported(NavigationElement element) {

		if (element instanceof FileElement) {
			FileElement file = (FileElement)element;
			
			if (supportedFiles.contains(file.getType())) {
				return true;
			}
			
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see de.jwic.sourceviewer.viewer.IObjectViewer#setNavigationElement(de.jwic.sourceviewer.model.NavigationElement)
	 */
	public void setNavigationElement(NavigationElement element) {
		if (element != null) {
			FileElement fileElm = (FileElement)element;
			file = fileElm.getFile();
		} else {
			file = null;
		}
		requireRedraw();
	}

	/**
	 * Returns the URL that calls the attachResource method.
	 * @return
	 */
	public String getImageURL() {
		
		return getSessionContext().getCallBackURL() + "&_resreq=1&controlId=" + getControlID() +
		"&rnd=" + System.currentTimeMillis();
		
	}

	/* (non-Javadoc)
	 * @see de.jwic.base.IResourceControl#attachResource(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void attachResource(HttpServletRequest req, HttpServletResponse res) throws IOException {

		if (file != null) {
			String filename = file.getName();
			
			res.setContentType("image/gif");
			res.setHeader ("Content-Disposition","attachment; filename=" + filename);
		  	res.setContentLength((int)file.length());
			
			OutputStream out = res.getOutputStream();
			InputStream in = new FileInputStream(file);
			
			//send the file as a stream of bytes
			try {
				byte[] buf = new byte[1024];
				int length = 0;

				while ((in != null) && ((length = in.read(buf)) != -1))
				{
					out.write(buf,0,length);
				}
			} catch (Exception e) {
				// error with server
				log.error("Error sending data to client (" + filename + ")", e);
			} finally {
				in.close();
				out.flush();
				out.close();
			}
		}
		
	}

	/**
	 * Returns true if an image file is present.
	 * @return
	 */
	public boolean isHasImage() {
		return file != null && file.exists() && file.isFile();
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	public void init(SVModel model) {
		// do nothing atm		
	}
}
