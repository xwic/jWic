/**
 * This is a basic construct of control JavaScript snippets.
 */
 
{
	/**
	 * Invoked after the DOM element was updated. This function is NOT updated if
	 * the custom doUpdate function returned true.
	 */
	afterUpdate: function(element) {
		#if($control.visible) 
			jQuery('#'+JWic.util.JQryEscape('${control.controlID}')).slideDown(); 
			#if($control.autoClose && $control.autoCloseDelay != 0)
			window.setTimeout("jQuery('#'+JWic.util.JQryEscape('${control.controlID}')).slideUp()", $control.autoCloseDelay);
			#end
		#end
	}
}