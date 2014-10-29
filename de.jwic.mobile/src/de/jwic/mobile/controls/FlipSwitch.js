{
    afterUpdate : function(){
        var control = JWic.$('ctrl_$control.controlID').find('input');
        FlipSwitch.initialize(control, $control.buildJsonOptions());
    }
}
