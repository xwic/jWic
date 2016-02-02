{
    afterUpdate : function ButtonAfterUpdate(){
        var button = JWic.$('$control.controlID'),
            options = $control.buildJsonOptions();
        JWic.mobile.Button.initialize(button, options);
    },
	
	/**
	 * Invoked when the existing element is removed from the DOM tree.
	 */
	destroy : function(element) {
		var button = JWic.$('$control.controlID');
		JWic.mobile.Button.destroy(button);
	}
}
