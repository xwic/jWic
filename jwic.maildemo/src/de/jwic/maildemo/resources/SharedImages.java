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
 * de.jwic.maildemo.resources.SharedImages
 * Created on 25.04.2007
 * $Id: SharedImages.java,v 1.2 2007/04/25 09:39:40 lordsam Exp $
 */
package de.jwic.maildemo.resources;

import de.jwic.base.ImageRef;

/**
 * Provides static access to shared ImageRef objects. 
 * @author Florian Lippisch
 */
public class SharedImages {

	/** Attachment Icon. */
	public final static ImageRef ICON_ATTACHMENT = new ImageRef(SharedImages.class.getPackage(), "attachment.gif");
	public final static ImageRef ICON_NEW = new ImageRef(SharedImages.class.getPackage(), "new.gif");
	public final static ImageRef ICON_DELETE = new ImageRef(SharedImages.class.getPackage(), "delete.gif");
	public final static ImageRef ICON_DELETE_DISABLED = new ImageRef(SharedImages.class.getPackage(), "delete_i.gif");
	public final static ImageRef ICON_FORWARD = new ImageRef(SharedImages.class.getPackage(), "forward.gif");
	public final static ImageRef ICON_FORWARD_DISABLED = new ImageRef(SharedImages.class.getPackage(), "forward_i.gif");
	public final static ImageRef ICON_REPLY = new ImageRef(SharedImages.class.getPackage(), "reply.gif");
	public final static ImageRef ICON_REPLY_DISABLED = new ImageRef(SharedImages.class.getPackage(), "reply_i.gif");

}
