var CheckBox = (function($, JWic){
    var clickHandler = function CheckBoxClickHandler(){
        JWic.fireAction($(this).data('id'), 'click');
    };
    return {
       initialize : function CheckBoxInitialize(control, options){
            control.data('id', options.controlID);
            control.prop('checked', options.toggled);
            control.checkboxradio({
                disabled : !options.enabled
            });
            control.on('click', clickHandler);
       }
    };
}(jQuery, JWic));
