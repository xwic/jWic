{
	// Attach to events...
	afterUpdate: function(element) {
	#if($control.visible)
		var btn = JWic.$('btn_${control.controlID}');
		if (btn) {
			JWic.controls.Button.initialize(btn, "${control.controlID}", {
				#if($control.menu) menu : '$control.menu.controlID' #end
			});
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