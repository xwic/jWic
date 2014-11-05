{
    afterUpdate : function ButtonAfterUpdate(){
        var button = JWic.$('ctrl_$control.controlID').find('button'),
            options = $control.buildJsonOptions();
        JWic.mobile.Button.initialize(button, options);
    }
}
