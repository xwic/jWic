{
    afterUpdate : function InputBoxAfterUpdate(){
        var control = JWic.$('$control.controlID');
        TextInput.initialize(control, $control.buildJsonOptions());
    }
}
