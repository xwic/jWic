{
    afterUpdate : function CheckBoxAfterUpdate (){
        var control = JWic.$('$control.controlID'),
            options = $control.buildJsonOptions();
        CheckBox.initialize(control, options);
    }
}
