{
	// Attach to events...
	afterUpdate: function(element) {
	#if($control.visible)
		var btn = JWic.$('btn_${control.controlID}');
		if (btn) {
			JWic.controls.Button.initialize(btn, "${control.controlID}",$control.buildJsonOptions());
		}
	#end
	}
}