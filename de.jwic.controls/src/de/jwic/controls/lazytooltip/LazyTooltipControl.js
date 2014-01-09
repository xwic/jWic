{
	afterUpdate: function(){
		var options = $control.buildJsonOptions();
		options.controlId = '$control.controlID';
		JWic.controls.LazyTooltipControl.initialize(options);
	},
	destroy : function(){
		var options = $control.buildJsonOptions();
		options.controlId = '$control.controlID';
		JWic.controls.LazyTooltipControl.destroy(options);
	}
	

}
