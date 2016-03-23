{
    afterUpdate : function ComboAfterUpdate(){
        var combo = JWic.$('$control.controlID')
            options = $control.buildJsonOptions()
            comboElm = document.getElementById('${control.controlID}');
        
        comboElm.dataStore = null;
		comboElm.loadCompleted = false;
		comboElm.changeNotification = $control.changeNotification;
		comboElm.openContentOnTextFocus = $control.comboBehavior.openContentOnTextboxFocus;
		comboElm.selectTextOnFocus = $control.comboBehavior.selectTextOnFocus;
		comboElm.keyDelayTime = $control.comboBehavior.keyDelayTime;
		comboElm.minSearchKeyLength = $control.comboBehavior.minSearchKeyLength;
		comboElm.cacheData = $control.comboBehavior.cacheData;
		comboElm.clientSideFilter = $control.comboBehavior.clientSideFilter;
		comboElm.autoPickFirstHit = $control.comboBehavior.autoPickFirstHit;
        JWic.mobile.Combo.initialize(combo, options);
    },
    
    destroy : function(element) {
		var combo = JWic.$('$control.controlID');
		JWic.mobile.Combo.destroy(combo);
	}	
}
