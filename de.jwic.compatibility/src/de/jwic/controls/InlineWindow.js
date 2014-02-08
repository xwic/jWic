{
	afterUpdate: function(element) {
	
	#if($control.position.toString() == "CENTER_SCREEN")
		var win = jQuery('#' + JWic.util.JQryEscape('elm_$control.controlID'));
		
		var windowSize = JWic.getWindowSize();
		
		
		var newTop = ((windowSize[1] - win.height()) / 2);
		var newLeft = ((windowSize[0] - win.width()) / 2);
		if (newTop < 0) {
			newTop = 0;
		}
		if (newLeft < 0) {
			newLeft = 0;
		}
		JWic.log(newTop + ", "+ newLeft + ", " + win.height());
		win.css({
			position:  "absolute",
			top : newTop + "px",
			left: newLeft + "px"
		});
	#end
	#if($control.position.toString() == "CENTER") 
		var win = jQuery('#' + JWic.util.JQryEscape('elm_$control.controlID'));
		win.css("marginLeft", "auto");
		win.css("marginRight", "auto");
	#end
	}
}