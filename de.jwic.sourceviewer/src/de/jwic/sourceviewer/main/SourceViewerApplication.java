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
package de.jwic.sourceviewer.main;

import java.io.File;

import de.jwic.base.Application;
import de.jwic.base.Control;
import de.jwic.base.IControlContainer;
import de.jwic.base.JWicRuntime;
import de.jwic.sourceviewer.model.Workspace;
import de.jwic.sourceviewer.model.reader.XmlWorkspaceReader;

/**
 *
 * @author Florian Lippisch
 */
public class SourceViewerApplication extends Application {

	private static Workspace workspace = null;
	
	/* (non-Javadoc)
	 * @see de.jwic.base.Application#createRootControl(de.jwic.base.IControlContainer)
	 */
	public Control createRootControl(IControlContainer container) {
		
		getSessionContext().setExitURL("byebye.html");
		SplashControl splash = new SplashControl(container) {
			/* (non-Javadoc)
			 * @see de.jwic.sourceviewer.main.SplashControl#actionLoad()
			 */
			public void performLoad() {
				SVModel model = new SVModel();
				model.setWorkspace(getWorkspace());
				
				SourceViewerPage page = new SourceViewerPage(getSessionContext(), model);
				getSessionContext().pushTopControl(page);
				destroy();
			}
		};
		
		
		return splash;
	}

	/**
	 * Creates a workspace object. This should get optimized by keeping a single
	 * instance of the "workspace" in memory.
	 * @return
	 */
	public synchronized Workspace getWorkspace() {
		
		if (workspace == null) {
		
			File root = new File(JWicRuntime.getJWicRuntime().getRootPath());
			try {
				workspace = XmlWorkspaceReader.readFromFile(new File(root, "WEB-INF/workspace.xml"));
				workspace.build(root);
			} catch (Exception e) {
				throw new RuntimeException("Error loading workspace content: " + e, e);
			}
			
		}

		return workspace;
	}
	
}
