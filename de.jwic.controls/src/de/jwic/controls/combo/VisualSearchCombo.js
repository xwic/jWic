{
	afterUpdate: function(element) {
		var comboElm = document.getElementById('${control.controlID}');
		if (comboElm) {
			comboElm.dataLoader = $control.comboBehavior.dataLoaderJSClass;
			comboElm.contentRenderer = $control.comboBehavior.contentRendererJSClass;
			comboElm.labelProvider = $control.comboBehavior.labelProviderJSClass;
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
			
			JWic.controls.VisualSearchCombo.initialize("$control.controlID", $control.comboBehavior.dataFilter);
		}
	},
	
	/**
	 * ..
	 */
	destroy : function(element) {
		var inpElm = document.getElementById("$control.controlID");
		if (inpElm) {
			JWic.controls.VisualSearchCombo.destroy("$control.controlID");
		}

	}
}