{
	// Attach to events...
	afterUpdate: function(element) {
		
		var inpElm = jQuery('#'+JWic.util.JQryEscape("${control.controlID}"));
		if (inpElm) {
			JWic.controls.InputBoxControl.initialize(inpElm);
			#if($control.updateOnBlur)				
				inpElm.bind('blur',function() {
						setTimeout(function(){
							JWic.fireAction('$control.controlID', 'onBlur', '');
							return false;
						});
					}
				);
			#end
			#if($control.readonly)
				jQuery(inpElm).addClass("x-readonly");
			#end
			#if($control.flagAsError)
				jQuery(inpElm).addClass("x-error");
			#end
		}

	}, 
	
	destroy: function(element) {
		var inpElm =jQuery('#'+JWic.util.JQryEscape("${control.controlID}"));
		if (inpElm) {
			JWic.controls.InputBoxControl.destroy(inpElm);
		}
	}
}