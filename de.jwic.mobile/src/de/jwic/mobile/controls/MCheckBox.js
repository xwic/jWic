#set($field = $control.getField("value"))
{
    afterUpdate : function CheckBoxAfterUpdate (){
        var control = JWic.$('$control.controlID'),
            options = $control.buildJsonOptions();
        JWic.mobile.CheckBox.initialize(control, options, '$field.id');
    }
}
