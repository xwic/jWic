{
	afterUpdate: function(element) {
	
	#if($control.position.toString() == "CENTER_SCREEN")
		var win = jQuery('#' + JQryEscape('elm_$control.controlID'));
		
		var windowSize = JWicInternal.getWindowSize();
		
		
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
		var win = $('elm_$control.controlID');
		win.style.marginLeft = "auto";
		win.style.marginRight = "auto";
	#end
	}
}