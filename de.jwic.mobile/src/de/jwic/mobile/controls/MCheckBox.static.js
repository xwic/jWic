JWic.mobile.CheckBox = (function($, JWic){
    var clickHandler = function CheckBoxClickHandler(){
    	var cb = $(this);
    	console.warn(cb.data('fieldId'));
    	console.warn(JWic.$(cb.data('fieldId')));
    	JWic.$(cb.data('fieldId'))[0].value = cb[0].checked ? "1" : "";
        JWic.fireAction(cb.data('id'), 'clicked', '');
    };
    return {
       initialize : function CheckBoxInitialize(control, options, fieldId){
            control.data('id', options.controlID);
            control.data('fieldId', fieldId);
            control.checkboxradio({
                disabled : !options.enabled
            });
            control.on('click', clickHandler);
       }
    };
}(jQuery, JWic));
