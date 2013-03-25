{ //NummericInputControl.js
	
	afterUpdate: function(element) {
		
		var inpElm = jQuery('#'+JQryEscape("${control.controlID}"));
		var hidden = jQuery('#'+JQryEscape("${control.controlID}_field"));
		try {
			if (inpElm) {
				
				JWic.controls.NumericInputControl.initialize(inpElm, hidden, $control.options);
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
		}catch(e){
			alert(e);
		}
	}, 
	
	destroy: function(element) {
		var inpElm =jQuery('#'+JQryEscape("${control.controlID}"));
		if (inpElm) {
			JWic.controls.NumericInputControl.destroy(inpElm);
		}
	}
}