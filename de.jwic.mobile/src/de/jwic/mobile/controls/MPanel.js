{
    afterUpdate : function PanelAfterUpdate(){
        var control = JWic.$('ctrl_$control.controlID'),
            options = $control.buildJsonOptions();
        JWic.mobile.Panel.initialize(control, options);

    },
    destroy : function PanelDestroy(){
        var control = JWic.$('ctrl_$control.controlID')
        JWic.mobile.Panel.destroy(control, $control.buildJsonOptions());
    }
}
