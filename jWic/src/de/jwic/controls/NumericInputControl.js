{ //NummericInputControl.js
	
	afterUpdate: function(element) {
		
		var inpElm = jQuery('#'+JQryEscape("${control.controlID}"));
		var hidden = jQuery('#'+JQryEscape("${control.controlID}_field"));
		
		if (inpElm) {
			
			JWic.controls.NumericInputControl.initialize(inpElm, $control.options);
			inpElm.autoNumeric('set', #if($control.number)$control.number#else '' #end);
			#if($control.updateOnBlur)
				inpElm.bind('blur',function() {
						JWic.fireAction('$control.controlID', 'onBlur', '');
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
		var inpElm =jQuery('#'+JQryEscape("${control.controlID}"));
		if (inpElm) {
			JWic.controls.NumericInputControl.destroy(inpElm);
		}
	}
}