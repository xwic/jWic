{
    afterUpdate : function ButtonAfterUpdate(){
        var button = JWic.$('ctrl_$control.controlID').find('input'),
            options = $control.buildJsonOptions();
        JWic.mobile.Button.initialize(button, options);
    }
}
