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
package de.jwic.maildemo.viewer;

import java.text.DateFormat;
import java.util.Locale;

import de.jwic.controls.tableviewer.CellLabel;
import de.jwic.controls.tableviewer.ITableLabelProvider;
import de.jwic.controls.tableviewer.RowContext;
import de.jwic.controls.tableviewer.TableColumn;
import de.jwic.maildemo.api.IMail;
import de.jwic.maildemo.resources.SharedImages;

/**
 *
 * @author Florian Lippisch
 */
public class MailLabelProvider implements ITableLabelProvider {

	private DateFormat df;

	/**
	 * Constructor.
	 * @param locale
	 */
	public MailLabelProvider(Locale locale) {
		df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, locale);
	}
	
	/* (non-Javadoc)
	 * @see de.jwic.ecolib.tableviewer.ITableLabelProvider#getCellLabel(java.lang.Object, de.jwic.ecolib.tableviewer.TableColumn, de.jwic.ecolib.tableviewer.RowContext)
	 */
	public CellLabel getCellLabel(Object row, TableColumn column, RowContext rowContext) {
		
		IMail mail = (IMail)row;
		String colID = (String)column.getUserObject(); 
		
		CellLabel cell = new CellLabel("");
		if (colID.equals("attachment")) {
			cell.image = mail.isContainsAttachments() ? SharedImages.ICON_ATTACHMENT : null;
		} else if (colID.equals("subject")) {
			cell.text = mail.getSubject();
		} else if (colID.equals("from")) {
			String from = mail.getFrom();
			int idx = from.indexOf('<');
			if (idx != -1) {
				from.substring(0, idx);
			}
			cell.text = from;
		} else if (colID.equals("recieved")) {
			cell.text = df.format(mail.getRecieved());
		} else if (colID.equals("size")) {
			
			long size = mail.getSize();
			if (size > 5120) {
				size = size / 1024;
				if (size > 2048) {
					size = size / 1024;
					cell.text = size + " MB";
				} else {
					cell.text = size + " KB";
				}
			} else {
				cell.text = size + " byte";
			}
			
		}
		
		return cell;
	}

}
