{
	// Attach to events...
	afterUpdate: function(element) {
	#if($control.visible)
		#if ( $control.fileUploaded )
			var element = jQuery('#'+JWic.util.JQryEscape('clearfile_${control.controlID}'));
			if (element) {
				element.click(function() { JWic.fireAction('$control.controlID', 'discard', '')});
			}
		#else
			var esc = JWic.util.JQryEscape,
				element = jQuery('#'+esc('${control.controlID}'));
			console.warn(element);
			if (element) {
				JWic.controls.FileUpload.initialize(element, "${control.controlID}", {
					width : $control.width,
					label: jQuery('#'+esc("${control.controlID}_label")),
					button: jQuery('#'+esc("${control.controlID}_button"))
				});
			}
		#end
	#end
	}
}