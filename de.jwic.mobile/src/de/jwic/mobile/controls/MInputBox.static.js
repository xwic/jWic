JWic.mobile.TextInput = (function($, util){

    var blurHandler = function InputBoxBlurHandler(){
        JWic.fireAction($(this).data('id'), 'blur');
    };

    return {
        initialize : function(control, options){
            control.data('id', options.controlID);
            control.textinput({
                autogrow : false,
                disabled : !options.enabled
            });
            if(options.updateOnBlur){
                control.on('blur', blurHandler);
            }
            if(options.blurred){
                control.blur();
            }
        },
        destroy : function(control){
            control.textinput('destroy');
        }
    };

}(jQuery, JWic.util));
