{
    afterUpdate : function ButtonAfterUpdate(){
        var combo = JWic.$('$control.controlID'),
            options = $control.buildJsonOptions();
        JWic.mobile.Combo.initialize(combo, options);
    }
}
