{
    afterUpdate : function PopupAfterUpdate(){
        var popup = JWic.$('$control.controlID'),
            options = $control.buildJsonOptions();
        JWic.mobile.Popup.initialize(popup, options);
    },
	
	destroy : function(element) {
		var popup = JWic.$('$control.controlID');
		JWic.mobile.Popup.destroy(popup);
	}
}
