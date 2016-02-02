{
    afterUpdate : function ButtonAfterUpdate(){
        var tabstrip = JWic.$('$control.controlID'),
            options = $control.buildJsonOptions();
        JWic.mobile.TabStrip.initialize(tabstrip, options);
    },
    
    destroy : function(element) {
		var tabstrip = JWic.$('$control.controlID');
		JWic.mobile.TabStrip.destroy(tabstrip);
	}
}