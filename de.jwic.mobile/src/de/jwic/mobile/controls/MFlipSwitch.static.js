JWic.mobile.FlipSwitch = (function($, JWic){
    var clickHandler = function FlipSwitchClickHandler(){
        JWic.fireAction($(this).data('id'), 'click');
    };
    return {
        initialize : function FlipSwitchInitialize(control, options){
            control.flipswitch({
                onText: options.onText,
                offText: options.offText,
                defaults: true,
                disabled :!options.enabled
            });

            control.data('id', options.controlID);
            control.on('change', clickHandler);
        }
    }
}(jQuery, JWic));
