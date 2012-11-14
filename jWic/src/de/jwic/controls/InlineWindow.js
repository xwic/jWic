{
	afterUpdate: function(element) {
	
	#if($control.position.toString() == "CENTER_SCREEN")
		var win = $('elm_$control.controlID');
		
		var dim = win.getDimensions();
		var windowSize = document.viewport.getDimensions();
		
		win.style.position = 'absolute';
		
		var newTop = ((windowSize.height - dim.height) / 2);
		var newLeft = ((windowSize.width - dim.width) / 2);
		if (newTop < 0) {
			newTop = 0;
		}
		if (newLeft < 0) {
			newLeft = 0;
		}
		
		win.style.top = newTop + "px";
		win.style.left = newLeft + "px";  
	#end
	#if($control.position.toString() == "CENTER") 
		var win = $('elm_$control.controlID');
		win.style.marginLeft = "auto";
		win.style.marginRight = "auto";
	#end
	}
}