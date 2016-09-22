{
    afterUpdate : function ListBoxAfterUpdate(){
        var listbox = JWic.$('$control.controlID'),
            options = $control.buildJsonOptions();
        JWic.mobile.ListBox.initialize(listbox, options);
    },
	
	destroy : function(element) {
		var listbox = JWic.$('$control.controlID');
		JWic.mobile.ListBox.destroy(listbox);
	}
}
