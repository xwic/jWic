/**
 * This is a basic construct of control JavaScript snippets.
 */
 
{
	content : "$jwic.escapeJavaScript($control.getField("content").value)",
	editorCfg : {
		#if($control.customToolBar && $control.customToolBar.size() != 0) #* Render Icons *#
			toolbar : [
			 	#set($first = 1)
			 	#foreach($bar in $control.customToolBar)
			 		#if($first == 1) #set($first = 0) #else , #end
			 		#if($bar.isForceOnNewLine())
			 			'/', 
			 		#end
			 		[
			 		 	#set($firstButton = 1)
			 		 	#foreach($button in $bar.buttons)
			 		 		#if ($firstButton == 1) #set($firstButton = 0) #else , #end
			 		 		'$!button'
			 		 	#end
			 		 ]
			 	#end
			 ]
		#else
		toolbar : "$jwic.escapeJavaScript($control.toolBarName)"
		#end
		, height: $control.height
		#if($control.width != -1), width: $control.width #end
	},
	/**
	 * Invoked before the element is updated.
	 */ 
	beforeUpdate: function() {
		
	},
	
	/**
	 * Invoked when the element needs to be updated. If this function returns
	 * false, the existing HTML element is replaced by the rendered part that
	 * comes from the server. If the script is doing the update, it should return
	 * true, to prevent the update.
	 */
	doUpdate: function(element) {
		
	#if($control.fullRedraw)
		#set($control.fullRedraw = false)
	#else 
		var field = jQuery(document.getElementById('${control.controlID}_content'));
	
		if (field.length === 0) { // if the field does not exist, the element needs to be created regulary. (jQuery objects are never null, but if the selection returned null the length prop is 0)
			field.val(this.content);
			JWic.log("doUpdate");
			if (typeof CKEDITOR !== undefined) {
				var editor = CKEDITOR.instances["$control.controlID"];
				if (editor && $control.enabled) {	// update content
					JWic.log("update content");
					editor.setData(this.content);
					JWic.log("AJAX Update - skipping replace.");
					return true;
					
				} else if (editor && !$control.enabled) {	// disable
					JWic.log("disable");
					editor.destroy();
					field.value = this.content;
					var elm = jQuery(document.getElementById('${control.controlID}'));
					elm.html(this.content);
					return true;
					
				} else if (!editor && $control.enabled) {	// enable
					JWic.log("enable it (" + this.content + ")");
					var elm = document.getElementById('${control.controlID}');
					elm.html("");
					var editor =  CKEDITOR.replace(elm[0], this.editorCfg);
					editor.setData(this.content);
					JWic.addBeforeRequestCallback("$control.controlID", function() {
						var editInstance = CKEDITOR.instances["$control.controlID"];
						if (editInstance) {
							field.value = editInstance.getData();
						}
					});
					
					return true;
				} else if (!editor && !$control.enabled) {
					JWic.log("update content of disabled element");
					
					var elm = jQuery(document.getElementById('${control.controlID}'));
					if (elm) {
						elm.text(this.content);
						return true;
					}
				}
			}
		}
	#end
		return false;
	},
	/**
	 * Invoked after the DOM element was updated. This function is NOT updated if
	 * the custom doUpdate function returned true.
	 */
	afterUpdate: function(element) {
		
		var elm = jQuery(document.getElementById('${control.controlID}'));
		var field = jQuery(document.getElementById('${control.controlID}_content'));
		field.value = this.content;
		if (typeof CKEDITOR == "undefined") {
			elm.text("<p>The CKEditor JavaScript library is not available. The content can not be edited.</p>" + field.value);
		} else {
			#if($control.enabled)
				elm.html(this.content);
			
				var editor =  CKEDITOR.replace(elm[0], this.editorCfg);
				editor.setData(this.content);
				
				JWic.addBeforeRequestCallback("$control.controlID", function() {
					var editInstance = CKEDITOR.instances["$control.controlID"];
					if (editInstance) {
						field.val(editInstance.getData());
					}
				});
			#else
				elm.text(this.content);
			#end
		}
		
	},
	
	/**
	 * Invoked when the existing element is removed from the DOM tree.
	 */
	destroy : function(element) {
		var editor = CKEDITOR.instances["$control.controlID"];
		if (editor) {
			JWic.log("Destroy editor instance $control.controlID");
			editor.destroy();
		}
	}
}