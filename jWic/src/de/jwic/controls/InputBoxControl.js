{
	// Attach to events...
	afterUpdate: function(element) {
		
		var inpElm = $("${control.controlID}");
		if (inpElm) {
			JWic.controls.InputBoxControl.initialize(inpElm);
			#if($control.updateOnBlur)
				Event.observe(inpElm, "blur", function() {
					JWic.fireAction('$control.controlID', 'onBlur', '')
				});
			#end
			#if($control.readonly)
				inpElm.addClassName("x-readonly");
			#end
			#if($control.flagAsError)
				inpElm.addClassName("x-error");
			#end
		}

	}, 
	
	destroy: function(element) {
		var inpElm = $("${control.controlID}");
		if (inpElm) {
			JWic.controls.InputBoxControl.destroy(inpElm);
		}
	}
}