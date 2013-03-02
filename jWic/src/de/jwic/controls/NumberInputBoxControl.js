
{ //NumberInputBoxControl.js
	
	afterUpdate: function(element) {
		
		var inpElm = jQuery('#'+JQryEscape("${control.controlID}"));
		var hidden = jQuery('#'+JQryEscape("${control.controlID}_field"));
		var t = "$control.thousandsSeparator" ;
		var d = "$control.decimalSeparator" ;
		if (inpElm) {
			JWic.controls.NumberInputBoxControl.initialize(inpElm,hidden,{thousends:	t,decimals:d});
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
			JWic.controls.InputBoxControl.destroy(inpElm);
		}
	}
}