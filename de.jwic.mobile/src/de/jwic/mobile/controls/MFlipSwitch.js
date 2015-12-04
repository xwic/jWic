{
    afterUpdate : function(){
        var control = JWic.$('ctrl_$control.controlID').find('input');
        JWic.mobile.FlipSwitch.initialize(control, $control.buildJsonOptions());
    }
}
