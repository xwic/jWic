{
    afterUpdate : function (){
    	var control = JWic.$('rad_${control.controlID}');
        options = $control.buildJsonOptions();
        console.log(control);
        JWic.mobile.RadioButton.initialize(jQuery(control), options);
    }
}
