{
    afterUpdate : function CheckBoxGroupAfterUpdate(){
        var chkboxgroup = JWic.$('$control.controlID'),
            options = $control.buildJsonOptions();
        JWic.mobile.CheckBoxGroup.initialize(chkboxgroup, options);
    },
	
	destroy : function(element) {
		var chkboxgroup = JWic.$('$control.controlID');
		JWic.mobile.CheckBoxGroup.destroy(chkboxgroup);
	}
}
