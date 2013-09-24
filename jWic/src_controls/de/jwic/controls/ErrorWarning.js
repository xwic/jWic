/**
 * This is a basic construct of control JavaScript snippets.
 */
 
{
	/**
	 * Invoked after the DOM element was updated. This function is NOT updated if
	 * the custom doUpdate function returned true.
	 */
	afterUpdate: function(element) {
		
		
		var me = jQuery('#'+JWic.util.JQryEscape('${control.controlID}')),
			timeoutTimer,
			options = $control.buildJsonOptions();
		
		function doHide(){
			JWic.fireAction('$control.controlID','doHide','');
		}
		#if($control.autoClose && $control.autoCloseDelay != 0)
			timeoutTimer = window.setTimeout(function(){
				if(timeoutTimer){
					window.clearTimeout(timeoutTimer);
				}
				me.slideUp(doHide);
			},options.autoCloseDelay);
			
		#end
		
		#if($control.visible && !$control.closed)
			me.slideDown();
		#else
			me.show();
		#end
		
		#if($control.closed && $control.visible)
			me.slideUp(doHide);
			if(timeoutTimer){
				window.clearTimeout(timeoutTimer);
			}
		#end
		
		me.find('.closeBtn').one('click',function(){
			JWic.fireAction('$control.controlID', 'doClose', '',function(){
				me.slideUp(function(){
					if(timeoutTimer){
						window.clearTimeout(timeoutTimer);
					}
					JWic.fireAction('$control.controlID','doHide','');
				});
				
			});
		});
		
	}
}