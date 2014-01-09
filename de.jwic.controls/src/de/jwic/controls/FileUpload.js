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
			var element = jQuery('#'+JWic.util.JQryEscape('${control.controlID}'));
			if (element) {
				JWic.controls.FileUpload.initialize(element, "${control.controlID}", {
					width : $control.width === 0 ? 'auto' : $control.width,
					filename: jQuery('#'+JWic.util.JQryEscape('${control.controlID}_filename'))
				});
			}
		#end
	#end
	}
}