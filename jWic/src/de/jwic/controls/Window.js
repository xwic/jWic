
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
			cache:false,
			
			
		});
		
		console.log(win.dialog('option'));
//		minimizable : #if($control.minimizable) true, #else false,#end
//		maximizable : #if($control.maximizable) true, #else false, #end
		
		win.dialog('open');		
//		win.bind('updateIcons',function(event){
//			var dialog = jQuery(this);
//			var visible = dialog.is(':visible');
//			
//			if(!visible){				
//				jQuery('#'+JQryEscape('${control.controlID}')+'_maximize').find('span').removeClass('ui-icon-newwin');
//				jQuery('#'+JQryEscape('${control.controlID}')+'_maximize').find('span').addClass('ui-icon-extlink');
//				jQuery('#'+JQryEscape('${control.controlID}')+'_minimize').find('span').removeClass('ui-icon-minusthick')
//				jQuery('#'+JQryEscape('${control.controlID}')+'_minimize').find('span').addClass('ui-icon-extlink')
//				
//			}else{
//				jQuery('#'+JQryEscape('${control.controlID}')+'_maximize').find('span').addClass('ui-icon-newwin')
//				jQuery('#'+JQryEscape('${control.controlID}')+'_maximize').find('span').removeClass('ui-icon-extlink')
//				jQuery('#'+JQryEscape('${control.controlID}')+'_minimize').find('span').addClass('ui-icon-minusthick')
//				jQuery('#'+JQryEscape('${control.controlID}')+'_minimize').find('span').removeClass('ui-icon-extlink')
//				
//			}
//		});
		
		var titlebar = win.parents('.ui-dialog').find('.ui-dialog-titlebar');	
		//minimize
		jQuery('<a href="#" id="${control.controlID}_minimize" role="button" class="ui-corner-all ui-dialog ui-dialog-titlebar-min"><span class="ui-icon ui-icon-minusthick">minimize</span></button>')
			.appendTo(titlebar)
			.mouseover(function(){
				jQuery(this).addClass('ui-state-hover');
			})
			.mouseout(function(){
				jQuery(this).removeClass('ui-state-hover');
			})
			.click(function() {
				if(jQuery.data(win,'isMaximized')){
					jQuery.data(win,'isMaximized',false);
					var dialogParent = win.parent();					
					var width  = jQuery.data(win,'originalSize').width;
					var height = jQuery.data(win,'originalSize').height;					 
					dialogParent.offset(jQuery.data(win,'originalPosition'));					
					dialogParent.width(width);
					dialogParent.height(height);
				}
				
				if(!jQuery.data(win,'isMinimized')){
					var dialogParent = win.parent();						
					jQuery.data(win,'isMinimized',true);					
					jQuery.data(win,'minOriginalSize',{width:dialogParent.width(),height:dialogParent.height()});
					
					var titlebar = dialogParent.find('.ui-dialog-titlebar');
					var dialogPadding = (dialogParent.outerHeight(true) - dialogParent.innerHeight());
					
					dialogParent.width(titlebar.width()+dialogPadding);
					dialogParent.height(titlebar.height()+dialogPadding);
					win.hide();
					
				}else{
					var dialogParent = win.parent();						
					jQuery.data(win,'isMinimized',false);
					dialogParent.width(jQuery.data(win,'minOriginalSize').width);
					dialogParent.height(jQuery.data(win,'minOriginalSize').height);
					win.show();
					
				}
				
//				win.trigger({type:'updateIcons',action:'minimize'});
				//custom event for minimize
				win.trigger({type:'minimize'});
			});

		
		//maximize
		jQuery('<a href="#" id="${control.controlID}_maximize" role="button" class="ui-corner-all ui-dialog ui-dialog-titlebar-max"><span class="ui-icon ui-icon-newwin">maximize</span></button>')
		.appendTo(titlebar)
		.mouseover(function(){
			jQuery(this).addClass('ui-state-hover');
		})
		.mouseout(function(){
			jQuery(this).removeClass('ui-state-hover');
		})
		.click(function() {
			(function(){
				if(!jQuery.data(win,'isMaximized')){				
					jQuery.data(win,'isMaximized',true);
					var dialogParent = win.parent();
					jQuery.data(win,'maxOriginalSize',{width: dialogParent.width(),height: dialogParent.height()});
					jQuery.data(win,'originalPosition',dialogParent.offset());
					
					dialogParent.width(jQuery(window).width());
					dialogParent.height(jQuery(window).height());
					dialogParent.offset({top:0,left:0});
					
				}else{
					jQuery.data(win,'isMaximized',false);
					var dialogParent = win.parent();					
					var width  = jQuery.data(win,'maxOriginalSize').width;
					var height = jQuery.data(win,'maxOriginalSize').height;					 
					dialogParent.offset(jQuery.data(win,'originalPosition'));					
					dialogParent.width(width);
					dialogParent.height(height);
				}
				if(jQuery.data(win,'isMinimized')){
					var dialogParent = win.parent();						
					jQuery.data(win,'isMinimized',false);
					dialogParent.width(jQuery.data(win,'minOriginalSize').width);
					dialogParent.height(jQuery.data(win,'minOriginalSize').height);
					win.show();
				}
				
			})();
						
//			win.trigger({type:'updateIcons',action:'maximize'});
			//custom event for maximize
			win.trigger({type:'maximize'})
		});
	
	},
	
	/**
	 * Destroy the window if it still exists.
	 */
	destroy : function(element) {
		var win = JWic.controls.Window.getWindow('win_${control.controlID}_div');	
		win.dialog('close');
		
	}
}