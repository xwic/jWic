{
    afterUpdate : function ButtonAfterUpdate(){
        var tabstrip = JWic.$('$control.controlID'),
            options = $control.buildJsonOptions();
        JWic.mobile.TabStrip.initialize(tabstrip, options, $control.getActiveIndex());
    },
    
    destroy : function(element) {
		var button = JWic.$('$control.controlID');
		JWic.mobile.TabStrip.destroy(button);
	}
}