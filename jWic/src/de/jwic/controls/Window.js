
{
		
	doUpdate: function(){		
		return false;
	},
	
	/**
	 * Initialize the window if it does not already exist.
	 */
	afterUpdate: function(element) {
		
		var win = jQuery('#'+JQryEscape('win_${control.controlID}_div')).dialog();	
		
		win.dialog('option',{
			autoOpen: false,
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

		#if($control.isMaximizable())
			addMaximizeToDialog(win);
		#end
		#if($control.isMinimizable())
			addMinimizeToDialog(win);
		#end

		win.parent().appendTo(jQuery("#jwicform"));		
		win.dialog('open');	

		
		function addMinimizeToDialog(dialog){
			if(dialog !== undefined){
				dialog.bind('dialogresize',function(){
					jQuery.data(dialog,'width',dialog.parent().width());
					dialog.parent().css('height', 'auto');
					if(!dialog.is(':visible')){
						jQuery.data(dialog,'isMinimized',false);
						jQuery.data(dialog,'isMaximized',false);
						jQuery.data(dialog,'originalPosition',dialog.parent().offset());
						dialog.parent().css('width',jQuery.data(dialog,'width'));	
						dialog.show();	
						dialog.css('width','auto');
					}
				});
				
				var titlebar = dialog.parents('.ui-dialog').find('.ui-dialog-titlebar');
				//minimize
				jQuery('<a href="#" id="'+dialog.attr('id')+'_minimize" role="button" class="ui-corner-all ui-dialog-titlebar-close"><span class="ui-icon ui-icon-minusthick">minimize</span></a>')
					.appendTo(titlebar)
					.mouseover(function(){
						jQuery(this).addClass('ui-state-hover');
					})
					.mouseout(function(){
						jQuery(this).removeClass('ui-state-hover');
					})
					.click(function() {
						(function(){
							var dialogParent = dialog.parent();					
							dialogParent.css('overflow','hidden');
							if(jQuery.data(dialog,'isMaximized')){
								jQuery.data(dialog,'isMaximized',false);
								dialogParent.offset(jQuery.data(dialog,'originalPosition'));
								dialogParent.css('width',jQuery.data(dialog,'width'));	
								dialogParent.css('height', 'auto');
							}
							
							if(!jQuery.data(dialog,'isMinimized')){
								jQuery.data(dialog,'isMinimized',true);
								jQuery.data(dialog,'width',dialogParent.width());					
								dialog.hide();
								
							}else{
								jQuery.data(dialog,'isMinimized',false);
								dialogParent.css('width',jQuery.data(dialog,'width'));	
								dialog.show();				
							}
						})();
						dialog.trigger({type:'minimize',source:dialog});
					});
				
			}
		}
		
		function addMaximizeToDialog(dialog){
			if(dialog!==undefined){
				
				dialog.bind('dialogresize',function(){
					jQuery.data(dialog,'width',dialog.parent().width());
					dialog.parent().css('height', 'auto');
					if(!dialog.is(':visible')){
						jQuery.data(dialog,'isMinimized',false);
						jQuery.data(dialog,'isMaximized',false);
						jQuery.data(dialog,'originalPosition',dialog.parent().offset());
						dialog.parent().css('width',jQuery.data(dialog,'width'));	
						dialog.show();	
						dialog.css('width','auto');
					}
				});
				
				
				var titlebar = dialog.parents('.ui-dialog').find('.ui-dialog-titlebar');			
				jQuery('<a href="#" id="'+dialog.attr('id')+'_maximize" role="button" class="ui-corner-all ui-dialog-titlebar-close"><span class="ui-icon ui-icon-newwin">maximize</span></a>')
				.appendTo(titlebar)
				.mouseover(function(){
					jQuery(this).addClass('ui-state-hover');
				})
				.mouseout(function(){
					jQuery(this).removeClass('ui-state-hover');
				})
				.click(function() {
					(function(){
						var dialogParent = dialog.parent();
						dialogParent.css('overflow','hidden');
						if(jQuery.data(dialog,'isMinimized')){
							jQuery.data(dialog,'isMinimized',false);	
							dialogParent.css('width',jQuery.data(dialog,'width'));	
							dialog.show();
							dialog.css('width','auto');
						}
						
						if(!jQuery.data(dialog,'isMaximized')){				
							jQuery.data(dialog,'isMaximized',true);
							jQuery.data(dialog,'originalPosition',dialogParent.offset());
							jQuery.data(dialog,'width',dialogParent.width());
							
							dialogParent.css('width',jQuery(window).width());
							dialogParent.css('height',jQuery(window).height());
							dialogParent.offset({top:0,left:0});
							dialog.css('width','auto');
						}else{
							jQuery.data(dialog,'isMaximized',false);
							dialogParent.offset(jQuery.data(dialog,'originalPosition'));					
							dialogParent.css('width',jQuery.data(dialog,'width'));
							dialogParent.css('height', 'auto');
						}
						
					})();
					
					dialog.trigger({type:'maximize',source:dialog})
				});
				
			}
		}
		
	
	},
	
	/**
	 * Destroy the window if it still exists.
	 */
	destroy : function(element) {
		var win = jQuery('#'+JQryEscape('win_${control.controlID}_div'));	
		win.dialog('destroy').remove();
		
	}
}