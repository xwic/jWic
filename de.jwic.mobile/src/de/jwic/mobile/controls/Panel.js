{
    afterUpdate : function PanelAfterUpdate(){
        var button = JWic.$('btn_$control.controlID'),
            panel = JWic.$('panel_$control.controlID'),
            options = $control.buildJsonOptions();
        panel.panel({
            open : function(){
                JWic.fireAction('$control.controlID', 'open')
            },
            close : function(){
                JWic.fireAction('$control.controlID', 'close')
            },
            animate : false
        });
        var parent = panel.parent();
        //find the page that this panel belongs to
        //or just stop at the body
        while(parent[0] && parent[0] !== document.body && !parent.hasClass('ui-page')){
            parent = parent.parent();
        }
        panel.appendTo(parent);
        button.on('click', function(){
            panel.panel('open');
        });

        if(options.state){
            panel.panel('open');
        }

    },
    destroy : function PanelDestroy(){
      var panel = JWic.$('panel_$control.controlID');
      panel.remove();
    }
}
