{
    afterUpdate : function(){
        var control = JWic.$('$control.controlID');
        JWic.mobile.FlipSwitch.initialize(control, $control.buildJsonOptions(), '$control.getField("value").getId()');
    }
}
