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
 * de.jwic.maildemo.api.impl.DemoDataSetup
 * Created on 23.04.2007
 * $Id: DemoDataSetup.java,v 1.5 2007/05/07 09:31:18 lordsam Exp $
 */
package de.jwic.maildemo.api.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import de.jwic.maildemo.api.IFolder;

/**
 * Used to create the DemoData. Might be replaced later on by
 * a file reader or something like this.
 * @author Florian Lippisch
 */
public class DemoDataSetup {

	private final static String[] MSGS = { 
		"Hello Demouser,\n\n" +
		"how are you doing? I hope you are fine. I am in need of the system documentation.\n" +
		"Could you please send it to me?\n" +
		"thx a lot!\n\n"+
		"Jo",
		
		"Dear Visitor,\n\n" +
		"you are currently viewing the MailClient demo application, written in jWic. " +
		"jWic is a light-weight UI framework for building web applications with the " +
		"flavor of rich-client applications. ",
		
		"The data displayed by this demo is generated randomly. However, the API used " +
		"by the UI elements is abstracted by interfaces, so that a real implementation " +
		"could be provided.\n\n" +
		"To exchange the API, someone could either implement a service oriented architecture, " +
		"with a service registry or a component container might be used. A simple configuration " +
		"mechanism is also a way to decouple this client from the backend.\n",
		
		"This sample text \n" +
		"uses \n a lot \n of \n rows \n to be \n displayed." +
		"\nIt allows\n you\n to\n see the\n scrollbar\n even on\n" +
		"larger\n resolutions.\n This is done by\n using the ScrollableContainer\n" +
		"that has a fixed width/height."
	};
	
	
	private final static String[] SUBJECTS = {
		"System Documentation",
		"RE: System Documentation",
		"Your Order",
		"Read This!",
		"Have a look on this page - realy funny",
		"Meeting notes for next monday...",
		"Have you seen jWic? You will like it!"
	};
	
	private final static String[] FROMS = {
		"someone@somewhere.jwic",
		"John Do <johndo@jwic.de>",
		"Mark Frewin <mark.frewin@pol-it.de>",
		"no-reply@demoshop.jwic",
		"Florian Lippisch <florian.lippisch@pol-it.de>"
	};
	
	
	public static List createMailList(int size) {
		
		List list = new ArrayList();
		
		String[] to = {"Demouser <demouser@jwic.de>"};
		
		DemoMail mail;
	
		// add sample mails
		Random rnd = new Random();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 18);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		for (int i = 0; i < size; i++) {
			DemoMessage msg = new DemoMessage(MSGS[rnd.nextInt(MSGS.length)]);
			
			mail = new DemoMail();
			mail.setUniqueID("ID" + i);
			mail.setSubject(SUBJECTS[rnd.nextInt(SUBJECTS.length)]);
			mail.setFrom(FROMS[rnd.nextInt(FROMS.length)]);
			mail.setSize(1444 + rnd.nextInt(3000));
			mail.setTo(to);
			mail.setMessage(msg);
			mail.setContainsAttachments(i % 3 == 0);
			
			if (mail.isContainsAttachments()) {
				mail.setSize(mail.getSize() + rnd.nextInt(3000000));
			}
			
			cal.add(Calendar.HOUR_OF_DAY, -1);
			cal.add(Calendar.MINUTE, -23);
			if (cal.get(Calendar.HOUR_OF_DAY) < 8 || i % 5 == 0) {
				cal.add(Calendar.DAY_OF_MONTH, -1);
				cal.set(Calendar.HOUR_OF_DAY, 17);
			}
			mail.setRecieved(cal.getTime());
			
			list.add(mail);
		}
		
		
		return list;
		
	}
	
	/**
	 * @param sizes the array to hold the sizes
	 * @return an Object[] that holds lists with demo mails
	 */
	public static Object[] createMailingList(int[] sizes) {
		List list = new ArrayList();
		
		for (int i = 0; i < sizes.length; i++) {
			list.add(createMailList(sizes[i]));
		}
		
		return list.toArray(new Object[list.size()]);
	}
	
	public static IFolder initRootNode() {
		int[] sizes = new int[] {30, 5, 5, 10, 7};
		IFolder rootFolder = new DemoFolder("root", new ArrayList());
		Object[] mails = createMailingList(sizes);
		
		DemoFolder inboxFolder = new DemoFolder("Inbox", (List) mails[0]);
		// add childs to the root folder
		rootFolder.addFolder(inboxFolder);
		inboxFolder.addFolder(new DemoFolder("jwic-users", (List) mails[1]));
		DemoFolder bcFolder = new DemoFolder("private", (List) mails[2]);
		inboxFolder.addFolder(bcFolder);
		bcFolder.addFolder(new DemoFolder("Funny Stuff", (List) mails[3]));
		
		rootFolder.addFolder(new DemoFolder("Outbox", (List) mails[4]));
		
		return rootFolder;
	}
}
