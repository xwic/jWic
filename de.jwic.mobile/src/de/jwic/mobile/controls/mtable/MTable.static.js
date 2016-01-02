JWic.mobile.MTable = (function($, JWic){
    var clickHandler = function MTableRowHandler(){
        JWic.fireAction($(this).data('id'), 'click');
    };
    return {
       initialize : function MTableInitialize(control, options){
            control.data('id', options.controlID);
            control.prop('checked', options.toggled);
            control.checkboxradio({
                disabled : !options.enabled
            });
            control.on('click', clickHandler);
       }
    };
}(jQuery, JWic));
