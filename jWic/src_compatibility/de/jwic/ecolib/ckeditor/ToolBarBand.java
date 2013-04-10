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
 * de.jwic.ecolib.ckeditor.ToolBarBand
 * Created on 10.05.2011
 * $Id: ToolBarBand.java,v 1.2 2011/06/02 12:31:06 lordsam Exp $
 */
package de.jwic.ecolib.ckeditor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Specifies a single band within a toolbar.
 * @author lippisch
 */
public class ToolBarBand implements Serializable {
	private static final long serialVersionUID = 1L;
	/** Default Clipboard Buttons */
	public final static ToolBarButton[] Default_Clipboard = {
				ToolBarButton.Copy,
				ToolBarButton.Cut,
				ToolBarButton.Paste,
				ToolBarButton.PasteText,
				ToolBarButton.PasteFromWord,
				ToolBarButton.Separator,
				ToolBarButton.Undo,
				ToolBarButton.Redo
	};
	
	/** Default BasicStyles */
	public final static ToolBarButton[] Default_BasicStyles = {
			ToolBarButton.Bold, 
			ToolBarButton.Italic, 
			ToolBarButton.Underline, 
			ToolBarButton.Strike, 
			ToolBarButton.Subscript, 
			ToolBarButton.Superscript, 
			ToolBarButton.Separator, 
			ToolBarButton.RemoveFormat
	};
	
	/** Default Paragrah buttons. */
	public final static ToolBarButton[] Default_Paragraph = {
			ToolBarButton.NumberedList, 
			ToolBarButton.BulletedList, 
			ToolBarButton.Separator, 
			ToolBarButton.Outdent, 
			ToolBarButton.Indent, 
			ToolBarButton.Separator, 
			ToolBarButton.Blockquote, 
			ToolBarButton.CreateDiv, 
			ToolBarButton.Separator, 
			ToolBarButton.JustifyLeft, 
			ToolBarButton.JustifyCenter, 
			ToolBarButton.JustifyRight, 
			ToolBarButton.JustifyBlock, 
			ToolBarButton.Separator, 
			ToolBarButton.BidiLtr, 
			ToolBarButton.BidiRtl 
	};
	
	/** Default Link Buttons */
	public final static ToolBarButton[] Default_Links = {
			ToolBarButton.Link,
			ToolBarButton.Unlink,
			ToolBarButton.Anchor
	};
	
	/** Default Style Buttons */
	public final static ToolBarButton[] Default_Styles = {
			ToolBarButton.Styles,
			ToolBarButton.Format,
			ToolBarButton.Font,
			ToolBarButton.FontSize
	};

	/** Default Link Buttons */
	public final static ToolBarButton[] Default_Colors = {
			ToolBarButton.TextColor,
			ToolBarButton.BGColor
	};

	
	private boolean forceOnNewLine = false;
	private List<ToolBarButton> buttons = new ArrayList<ToolBarButton>();
	
	/**
	 * Constructor.
	 */
	public ToolBarBand() {
		
	}
	
	/**
	 * Constructors a new ToolBarBand with the specified buttons.
	 * @param buttons
	 */
	public ToolBarBand(List<ToolBarButton> buttons) {
		super();
		this.buttons = buttons;
	}

	/**
	 * Construct a new ToolbarBand with the specified buttons.
	 * @param buttons
	 */
	public ToolBarBand(ToolBarButton... btns) {
		for (ToolBarButton button : btns) {
			buttons.add(button);
		}
	}

	/**
	 * Construct a new ToolbarBand with the specified buttons.
	 * @param buttons
	 */
	public ToolBarBand(boolean forceOnNewLine, ToolBarButton... btns) {
		this.forceOnNewLine = forceOnNewLine;
		for (ToolBarButton button : btns) {
			buttons.add(button);
		}
	}

	/**
	 * @return the forceOnNewLine
	 */
	public boolean isForceOnNewLine() {
		return forceOnNewLine;
	}
	/**
	 * @param forceOnNewLine the forceOnNewLine to set
	 */
	public void setForceOnNewLine(boolean forceOnNewLine) {
		this.forceOnNewLine = forceOnNewLine;
	}
	/**
	 * @return the buttons
	 */
	public List<ToolBarButton> getButtons() {
		return buttons;
	}
	/**
	 * @param buttons the buttons to set
	 */
	public void setButtons(List<ToolBarButton> buttons) {
		this.buttons = buttons;
	}
	
	/**
	 * Add a button.
	 * @param button
	 */
	public void addButton(ToolBarButton button) {
		buttons.add(button);
	}

	/**
	 * Remove a button.
	 * @param button
	 */
	public void removeButton(ToolBarButton button) {
		buttons.remove(button);
	}
	
}
