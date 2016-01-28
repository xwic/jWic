{
    afterUpdate : function (){
        var control = JWic.$('$control.controlID'),
            options = $control.buildJsonOptions();
        JWic.mobile.InputBox.initialize(control, options);
    },
    
	destroy : function(element) {
		var button = JWic.$('$control.controlID');
		JWic.mobile.InputBox.destroy(button);
	}
}
