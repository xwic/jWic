{
	doUpdate: function(element) {
		#if($control.visible)
			var progBar = jQuery('#pb_'+JQryEscape('${control.controlID}'));
			var msg = jQuery('#pb_msg_'+JQryEscape('${control.controlID}'));
			if (progBar) {
				#if($control.intermediate) 
					progBar.progressbar("value", false);
				#else
					progBar.progressbar("value", $control.getPercent());
				#end
				msg.update("$jwic.formatHtml($control.getInfoLabel())");
				return true;
				#if($control.autoRefresh)
					JWic.controls.ProgressBar.autoRefresh("${control.controlID}", progBar, $control.autoRefreshDelay);
				#end
			}
			return false
		#else
			return false;
		#end
	},
	
	// Attach to events...
	afterUpdate: function(element) {
		#if($control.visible)
			var progBar = jQuery('#pb_'+JQryEscape('${control.controlID}'));
			var msg = jQuery('#pb_msg_'+JQryEscape('${control.controlID}'));
			if (progBar) {
				progBar.progressbar({
					#if($control.intermediate)
						value : false
					#else
						value: $control.getPercent()
					#end
				})
				#if($control.autoRefresh)
					JWic.controls.ProgressBar.autoRefresh("${control.controlID}", progBar, $control.autoRefreshDelay);
				#end
			}
		#end
	}
}