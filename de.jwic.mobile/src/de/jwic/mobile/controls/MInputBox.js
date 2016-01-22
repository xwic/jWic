{
    afterUpdate : function (){
        var control = JWic.$('$control.controlID'),
            options = $control.buildJsonOptions();
        JWic.mobile.InputBox.initialize(control, options);
    }
}
