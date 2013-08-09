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
			timeoutTimer;
		#if($control.visible) 
			me.slideDown(); 
			#if($control.autoClose && $control.autoCloseDelay != 0)
			timeoutTimer = window.setTimeout("jQuery('#'+JWic.util.JQryEscape('${control.controlID}')).slideUp()", $control.autoCloseDelay);
			#end
		#end
		
		me.find('.closeBtn').click(function (){
			me.slideUp();
			if(timeoutTimer){
				window.clearTimeout(timeoutTimer);
			}
		});
		
	}
}