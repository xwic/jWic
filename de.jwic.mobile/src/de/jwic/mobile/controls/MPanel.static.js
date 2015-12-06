JWic.mobile.Panel = (function($, JWic){

    return {
        initialize : function PanelInitialize(control, options){
             var button = control.find('#btn'),
                 panel = control.find('#panel');

             console.warn(options);

             panel.panel({
                open : function(){
                    JWic.fireAction(options.controlID, 'open')
                },
                close : function(){
                    JWic.fireAction(options.controlID, 'close')
                },
                animate : options.animate,
                position : options.position,
                display : options.display,
                disabled : !options.enabled
            });

            //console.warn(panel);
            var parent = panel.parent();
            //find the page that this panel belongs to
            //or just stop at the body
            while(parent[0] && parent[0] !== document.body && !parent.hasClass('ui-page')){
                parent = parent.parent();
            }
            panel.appendTo(parent);
            if(options.enabled){
                button.on('click', function(){
                    panel.panel('open');
                });
            }
            if(options.toggled){
                panel.panel('open');
            }
        },
        destroy : function PanelDestroy(control, options){
            control.find('panel').panel('destroy');
            control.find('button').unbind('click');
        }
    }
}(jQuery, JWic));
