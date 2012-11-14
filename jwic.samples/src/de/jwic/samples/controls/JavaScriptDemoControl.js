{
	
	text : "$jwic.escapeJavaScript($control.someString)",
	
	beforeUpdate: function() {
		// might be used to clean up some stuff..
	},
	
	doUpdate: function(element) {
		var txt = this.text;
		var myElement = $('${control.controlID}');
		var myText = $('text_$control.controlID');
		if (myElement) {	// already exists.
			if (myText.innerHTML != txt) {
				myText.fade({
					afterFinish: function() {
						myText.update(txt);
						myText.appear();
				}});
			}
			myElement.morph('width: $!{control.width}px; height: $!{control.height}px;');
			return true;
		}
	},
	
	afterUpdate: function(element) {
		Effect.Appear('${control.controlID}');
	},
	
	destroy: function(element) {
		JWic.log("Test Control is destroyed.");
	}
}