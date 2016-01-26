{
    afterUpdate : function ButtonAfterUpdate(){
        var button = JWic.$('$control.controlID'),
            options = $control.buildJsonOptions();
        JWic.mobile.Button.initialize(button, options);
    }
}
