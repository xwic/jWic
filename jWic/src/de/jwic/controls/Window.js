
{
		
	doUpdate: function(){		
		return false;
	},
	
	/**
	 * Initialize the window if it does not already exist.
	 */
	afterUpdate: function(element) {
		
		var win = JWic.controls.Window.getWindow('win_${control.controlID}_div');	
		
		win.dialog('option',{
			#if($control.title) title: "$escape.escapeJavaScript($control.title)", #else title: '', #end
			#if($control.width != 0) width : $control.width, #end
			#if($control.height != 0) height : $control.height, #end
			#if($control.top != 0) top : $control.top, #end
			#if($control.left != 0) left : $control.left, #end
			#if(!$control.closable)
				closeOnEscape: false,
				open: function(event, ui) { jQuery(".ui-dialog-titlebar-close", ui.dialog || ui).hide(); },
			#end
			close: function () {
				JWic.fireAction("$control.controlID", "close", "");	
			},
	
			draggable: #if($control.draggable) true, #else false, #end
			resizable : #if($control.resizable) true, #else false, #end
			modal: #if($control.modal) true, #else false, #end
			cache:false
					
		});
		
			
		#if($control.isMinimizable())
			addMinimizeToDialog(win);
		#end
		#if($control.isMaximizable())
			addMaximizeToDialog(win);
		#end

		win.dialog('open');	
	
	},
	
	/**
	 * Destroy the window if it still exists.
	 */
	destroy : function(element) {
		var win = JWic.controls.Window.getWindow('win_${control.controlID}_div');	
		win.dialog('close');
		
	}
}