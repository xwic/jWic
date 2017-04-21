{ //NummericInputControl.js
	
	afterUpdate: function(element) {
		
		var inpElm = jQuery('#'+JWic.util.JQryEscape("${control.controlID}"));
		var hidden = jQuery('#'+JWic.util.JQryEscape("${control.controlID}_field"));
		try {
			if (inpElm) {
				
				JWic.controls.NumericInputControl.initialize(inpElm, hidden, $control.buildJsonOptions());
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
		}catch(e){
			alert(e);
		}
	}, 
	
	destroy: function(element) {
		var inpElm =jQuery('#'+JWic.util.JQryEscape("${control.controlID}"));
		if (inpElm) {
			JWic.controls.NumericInputControl.destroy(inpElm);
		}
	}
}