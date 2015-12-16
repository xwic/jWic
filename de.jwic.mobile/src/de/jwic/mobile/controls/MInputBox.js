{
    afterUpdate : function InputBoxAfterUpdate(){
        var control = JWic.$('$control.controlID');
        JWic.mobile.TextInput.initialize(control, $control.buildJsonOptions());
    },
    destroy : function InputBoxDestroy(){
        var control = JWic.$('$control.controlID');
        JWic.mobile.TextInput.destroy(control);
    }
}
