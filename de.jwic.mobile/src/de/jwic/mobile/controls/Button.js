{
    afterUpdate : function ButtonAfterUpdate(){
        var button = JWic.$('ctrl_$control.controlID').find('.ui-btn'),
            options = $control.buildJsonOptions();
        Button.initialize(button, options);
    }
}
