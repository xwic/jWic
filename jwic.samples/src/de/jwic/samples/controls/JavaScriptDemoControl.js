{
	
	text : "$jwic.escapeJavaScript($control.someString)",
	
	beforeUpdate: function() {
		// might be used to clean up some stuff..
	},
	
	doUpdate: function(element) {
		var txt = this.text;
		var myElement = jQuery('#'+JQryEscape('${control.controlID}'));
		var myText = jQuery('#'+JQryEscape('text_${control.controlID}'));
		if (myElement.get(0)) {	// already exists.
			if (myText.html() != txt) {
				myElement.fadeIn(600, function() {
					myText.html(txt);
					myText.show();
				});
			}
			myElement.animate({width: $!{control.width},
							   height: $!{control.height}});
			return true;
		}
	},
	
	afterUpdate: function(element) {
		jQuery('#'+JQryEscape('${control.controlID}')).show();
	},
	
	destroy: function(element) {
		JWic.log("Test Control is destroyed.");
	}
}