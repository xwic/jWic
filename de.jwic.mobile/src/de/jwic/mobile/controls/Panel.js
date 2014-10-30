{
    afterUpdate : function PanelAfterUpdate(){
        var control = JWic.$('ctrl_$control.controlID'),
            options = $control.buildJsonOptions();
        Panel.initialize(control, options);

    },
    destroy : function PanelDestroy(){
      var control = JWic.$('ctrl_$control.controlID')
      Panel.destroy(control, $control.buildJsonOptions());
    }
}
