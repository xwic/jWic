JWic.mobile.Button = (function($, util){
    var clickHandler = function ButtonClickHandler(){
        JWic.fireAction($(this).data('id'), 'click');
    };

    return {
        initialize : function(control, options){
            control.data('id', options.controlID);

            if(options.enabled){
                control.on('click', clickHandler);
            }
        }
    }
}(jQuery, JWic));
