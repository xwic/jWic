#set($fld = $control.getField("text"))
#set($fldKey = $control.getField("key"))
{
    afterUpdate : function SelectMenuAfterUpdate(){
    	var fldElm = document.getElementById('${fld.id}')
    		fldKeyElm = document.getElementById('${fldKey.id}')
	    	selectmenu = JWic.$('$control.controlID')
	        options = $control.buildJsonOptions()
	        selectmenuElm = document.getElementById('${control.controlID}');
	    
	    selectmenuElm.dataStore = null;
	    selectmenuElm.loadCompleted = false;
	    selectmenuElm.changeNotification = $control.changeNotification;
	    selectmenuElm.openContentOnTextFocus = $control.comboBehavior.openContentOnTextboxFocus;
		selectmenuElm.selectTextOnFocus = $control.comboBehavior.selectTextOnFocus;
		selectmenuElm.keyDelayTime = $control.comboBehavior.keyDelayTime;
		selectmenuElm.minSearchKeyLength = $control.comboBehavior.minSearchKeyLength;
		selectmenuElm.cacheData = $control.comboBehavior.cacheData;
		selectmenuElm.clientSideFilter = $control.comboBehavior.clientSideFilter;
		selectmenuElm.autoPickFirstHit = $control.comboBehavior.autoPickFirstHit;
		selectmenuElm.multiSelect = $control.multiSelect;
        JWic.mobile.SelectMenu.initialize(selectmenu, fldElm, fldKeyElm, options);
    },
	
	destroy : function(element) {
		var selectmenu = JWic.$('$control.controlID');
		JWic.mobile.SelectMenu.destroy(selectmenu);
	}
}
