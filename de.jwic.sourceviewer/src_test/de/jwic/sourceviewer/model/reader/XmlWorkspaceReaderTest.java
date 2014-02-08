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
 * de.jwic.sourceviewer.model.reader.XmlWorkspaceReaderTest
 * Created on 25.04.2007
 * $Id: XmlWorkspaceReaderTest.java,v 1.1 2007/04/26 16:08:52 lordsam Exp $
 */
package de.jwic.sourceviewer.model.reader;

import java.io.File;

import org.apache.log4j.BasicConfigurator;

import de.jwic.sourceviewer.model.Group;
import de.jwic.sourceviewer.model.Project;
import de.jwic.sourceviewer.model.Workspace;

import junit.framework.TestCase;

/**
 *
 * @author Florian Lippisch
 */
public class XmlWorkspaceReaderTest extends TestCase {

	public void testRead() throws Exception {
		
		BasicConfigurator.configure();
		
		File file = new File("web_root/WEB-INF/workspace.xml");
		//File file = new File("web/sv/test.xml");
		assertTrue(file.exists());
		
		Workspace ws = XmlWorkspaceReader.readFromFile(file);
		
		assertNotNull(ws);
		
		Group group = (Group)ws.getChilds().get(0);
		assertEquals("Samples", group.getName());
		
		Project project = (Project)group.getChilds().get(0);
		assertNotNull(project);
		System.out.println(project.getName());
		
	}
	
}
