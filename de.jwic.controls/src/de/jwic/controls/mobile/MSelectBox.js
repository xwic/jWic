{
    afterUpdate : function SelectBoxAfterUpdate(){
        var control = JWic.$('$control.controlID'),
            options = $control.buildJsonOptions();
        JWic.mobile.SelectBox.initialize(control, options);
    }
}
