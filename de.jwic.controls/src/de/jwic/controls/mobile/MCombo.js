{
    afterUpdate : function ComboAfterUpdate(){
        var combo = JWic.$('$control.controlID'),
            options = $control.buildJsonOptions();
        JWic.mobile.Combo.initialize(combo, options);
    },
    
    destroy : function(element) {
		var combo = JWic.$('$control.controlID');
		JWic.mobile.Combo.destroy(combo);
	}
}
