{
	
	afterUpdate:function(elm){
		#if($control.visible)
			var accordion = jQuery('#'+JWic.util.JQryEscape('${control.controlID}'))
			if (accordion) {
				JWic.controls.Accordion.initialize(accordion, "${control.controlID}", $control.buildJsonOptions());
			}
		#end
	}

}