{
    afterUpdate : function SelectMenuAfterUpdate(){
        var selectmenu = JWic.$('$control.controlID'),
            options = $control.buildJsonOptions();
        JWic.mobile.SelectMenu.initialize(selectmenu, options);
    },
	
	destroy : function(element) {
		var selectmenu = JWic.$('$control.controlID');
		JWic.mobile.SelectMenu.destroy(selectmenu);
	}
}
