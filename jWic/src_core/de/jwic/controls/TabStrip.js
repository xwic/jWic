{
	// Attach to events...
	afterUpdate: function(element) {
	#if($control.visible)
		var tabStrip = jQuery('#'+JQryEscape('${control.controlID}'));
		if (tabStrip) {
			JWic.controls.TabStrip.initialize(tabStrip, "${control.controlID}", $control.getActiveIndex());
		}
	#end
	}
}