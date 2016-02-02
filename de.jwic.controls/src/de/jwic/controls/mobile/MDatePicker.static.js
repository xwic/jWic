JWic.mobile.DatePicker = (function($, JWic, undefined){
    var blurHandler = function(){
        JWic.fireAction($(this).data('id'), 'close', $(this).val());
    };

    return {
        initialize : function DatePickerInitialize (control, options) {
            var input = control.find('input');
            input.data('id', options.controlID);
            input.attr('placeholder', options.dateFormat);

            input.datepicker({
                dateFormat : JWic.util.convertToJqueryDateFormat(options.dateFormat),
                onSelect : options.updateOnBlur ? blurHandler : undefined,
                disabled : !options.enabled
            });
        },
        destroy : function DatePickerDestroy(control){
            control.datepicker('destroy');
        }
    }
}(jQuery, JWic));
