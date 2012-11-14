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
 * de.jwic.sourceviewer.model.reader.XmlModelReader
 * Created on 25.04.2007
 * $Id: XmlWorkspaceReader.java,v 1.1 2007/04/26 16:08:53 lordsam Exp $
 */
package de.jwic.sourceviewer.model.reader;

import java.io.File;
import java.io.IOException;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import de.jwic.sourceviewer.model.Folder;
import de.jwic.sourceviewer.model.Group;
import de.jwic.sourceviewer.model.SourcePackage;
import de.jwic.sourceviewer.model.Project;
import de.jwic.sourceviewer.model.SourceFolder;
import de.jwic.sourceviewer.model.Workspace;

/**
 *
 * @author Florian Lippisch
 */
public class XmlWorkspaceReader {

	public static Workspace readFromFile(File file) throws SAXException, IOException {
		
		Digester digester = new Digester();
		digester.setValidating(false);
		digester.addObjectCreate("workspace", Workspace.class.getName());
		digester.addSetProperties("workspace");
		digester.addCallMethod("workspace/filter/name", "addFilter", 0);
		
		digester.addObjectCreate("workspace/group", Group.class.getName());
		digester.addSetProperties("workspace/group");
		digester.addSetNext("workspace/group", "addGroup", Group.class.getName());
		
		digester.addObjectCreate("workspace/group/project", Project.class.getName());
		digester.addSetProperties("workspace/group/project");
		digester.addSetNext("workspace/group/project", "addProject", Project.class.getName());

		digester.addObjectCreate("workspace/group/project/source", SourceFolder.class.getName());
		digester.addSetProperties("workspace/group/project/source");
		digester.addSetNext("workspace/group/project/source", "addSourceFolder", SourceFolder.class.getName());

		digester.addObjectCreate("workspace/group/project/source/package", SourcePackage.class.getName());
		digester.addSetProperties("workspace/group/project/source/package");
		digester.addSetNext("workspace/group/project/source/package", "addPackage", SourcePackage.class.getName());
		digester.addCallMethod("workspace/group/project/source/package", "setName", 0);

		digester.addObjectCreate("workspace/group/project/folder", Folder.class.getName());
		digester.addSetProperties("workspace/group/project/folder");
		digester.addSetNext("workspace/group/project/folder", "addFolder", Folder.class.getName());
		
		Workspace ws = (Workspace)digester.parse(file);
		
		return ws;
		
	}
	
}
