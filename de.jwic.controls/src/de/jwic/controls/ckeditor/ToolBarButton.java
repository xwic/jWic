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
package de.jwic.controls.ckeditor;

/**
 * Possible ToolBar buttons.
 * @author lippisch
 */
public enum ToolBarButton {
		/** A vertical separator between two buttons */
		Separator {	
			@Override
			public String toString() {
				return "-";
			}
		},
		Source,Save,NewPage,Preview,Templates,
		Cut,Copy,Paste,PasteText,PasteFromWord,Print, SpellChecker, Scayt,
		Undo,Redo,Find,Replace,SelectAll,RemoveFormat,
	    Form, Checkbox, Radio, TextField, Textarea, Select, Button, ImageButton, HiddenField,
	    Bold,Italic,Underline,Strike,Subscript,Superscript,
	    NumberedList,BulletedList,Outdent,Indent,Blockquote,CreateDiv,
	    JustifyLeft,JustifyCenter,JustifyRight,JustifyBlock,
	    BidiLtr, BidiRtl,
	    Link,Unlink,Anchor,
	    Image,Flash,Table,HorizontalRule,Smiley,SpecialChar,PageBreak,Iframe,
	    Styles,Format,Font,FontSize,
	    TextColor,BGColor,
	    Maximize, ShowBlocks,About
}