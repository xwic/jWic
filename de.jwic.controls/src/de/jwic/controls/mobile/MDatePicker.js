{
    afterUpdate : function DatePickerAfterUpdate(){
        var control = JWic.$('ctrl_$control.controlID'),
            options = $control.buildJsonOptions();
        JWic.mobile.DatePicker.initialize(control, options);
    },
    destroy : function DatePickerDestroy (){
        JWic.mobile.DatePicker.destroy(JWic.$('ctrl_$control.controlID').find('input'));
    }
}
