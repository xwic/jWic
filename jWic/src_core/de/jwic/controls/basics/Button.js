{
	// Attach to events...
	afterUpdate: function(element) {
	#if($control.visible)
		var btn = jQuery('#btn_'+JQryEscape('${control.controlID}'));
		if (btn) {
			JWic.controls.basics.Button.initialize(btn, "${control.controlID}");
			btn.attr("_ctrlEnabled", "$control.isEnabled()");
			btn.attr("_confirmMsg", "$escape.escapeJavaScript($!control.confirmMsg)");
			#if($control.width > 0)
				btn.width("${control.width}");
			#end
			#if($control.height > 0)
				btn.height("${control.height}");
			#end
			#if($control.tooltip != "")
				btn.tooltip({
					position: {
						my: "left top",
						at: "center bottom",
						}});
			#end
		}
	#end
	}
}