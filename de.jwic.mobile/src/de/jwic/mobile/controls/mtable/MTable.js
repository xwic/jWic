{
    afterUpdate : function MTableAfterUpdate (){
        var control = JWic.$('$control.controlID'),
            options = $control.buildJsonOptions();
        JWic.mobile.MTable.initialize(control, options);
    }
}
