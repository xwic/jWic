package de.jwic.ecolib.ckeditor;

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