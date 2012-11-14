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
 * de.jwic.maildemo.viewer.MailContentProvider
 * Created on 24.04.2007
 * $Id: MailContentProvider.java,v 1.2 2007/04/25 08:54:49 lordsam Exp $
 */
package de.jwic.maildemo.viewer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import de.jwic.ecolib.tableviewer.defaults.ListContentProvider;
import de.jwic.maildemo.api.IFolder;
import de.jwic.maildemo.api.IMail;

/**
 * Provides access to the list of mails.
 * @author Florian Lippisch
 */
public class MailContentProvider extends ListContentProvider {

	private IFolder folder = null;
	
	/**
	 * Construct an empty list.
	 *
	 */
	public MailContentProvider() {
		super(new ArrayList());
	}

	/**
	 * @return the folder
	 */
	public IFolder getFolder() {
		return folder;
	}

	/**
	 * @param folder the folder to set
	 */
	public void setFolder(IFolder folder) {
		this.folder = folder;
		if (folder != null) {
			data = folder.listMails();
		} else {
			throw new NullPointerException("folder must not be null");
		}
	}

	/* (non-Javadoc)
	 * @see de.jwic.ecolib.tableviewer.defaults.ListContentProvider#getUniqueKey(java.lang.Object)
	 */
	public String getUniqueKey(Object object) {
		IMail mail = (IMail)object;
		return mail.getUniqueID();
	}

	/**
	 * @param string
	 * @param up
	 */
	public void sortData(final String field, final boolean up) {
		
		Collections.sort(data, new Comparator() {
			/* (non-Javadoc)
			 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
			 */
			public int compare(Object o1, Object o2) {
				IMail m1 = (IMail)o1;
				IMail m2 = (IMail)o2;
				int result = 0;
				if (field.equals("attachment")) {
					if (m1.isContainsAttachments() != m2.isContainsAttachments()) {
						result = m1.isContainsAttachments() ? -1 : 1;
					}
				} else if (field.equals("subject")) {
					result = m1.getSubject().compareTo(m2.getSubject());
				} else if (field.equals("from")) {
					result = m1.getFrom().compareTo(m2.getFrom());
				} else if (field.equals("recieved")) {
					result = m1.getRecieved().compareTo(m2.getRecieved());
				} else if (field.equals("size")) {
					result = (int)(m1.getSize() -  m2.getSize());
				}
				return up ? result : -result;
			}
		});

		
	}
	
}
