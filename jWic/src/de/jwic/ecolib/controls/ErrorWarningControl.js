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
			Effect.SlideDown('${control.controlID}'); 
			#if($control.autoClose && $control.autoCloseDelay != 0)
			window.setTimeout("Effect.SlideUp('${control.controlID}')", $control.autoCloseDelay);
			#end
		#end
	}
}