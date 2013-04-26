{
	afterUpdate: function(element) {
		JWic.controls.Menu.initialize("${control.controlID}", {
			hidden : $control.hidden
		})
	},
	destroy : function(element) {
		JWic.controls.Menu.destroy("$control.controlID");
	}
}