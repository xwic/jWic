JWic.mobile.SelectBox = (function($){
    var changeHandler = function(){
        JWic.fireAction($(this).data('id'), 'select', $(this).val())
    };

    return {
        initialize : function SelectBoxInitialize(control, options){
            var disabled = !options.enabled;
            control.data('id', options.controlID);

            control.select({
                nativeMenu : false,
                disabled : disabled
            });
            console.warn(disabled);
            if(!disabled){
                control.change(changeHandler);
            }
        }
    };
}(jQuery));
