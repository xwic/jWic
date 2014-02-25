#set($fld = $control.getField("text"))
{
	beforeUpdate: function() {},
	doUpdate: function(element) {
		//return false;
	},
	/**
	 * Initialize
	 */
	
	afterUpdate: function(element) {
	#if($control.visible)
		var inpElm = document.getElementById('${fld.id}');
		var comboElm = document.getElementById('${control.controlID}');
		if (inpElm) {
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
			#if($control.multiSelect)
				comboElm.multiSelect = $control.multiSelect;
				##inpElm.readOnly = true;
			#end
			#if($control.defaultImage)
				comboElm.defaultImage = {
					path : "$control.defaultImage.getPath()",
					imgTag : "$escape.escapeJavaScript($control.defaultImage.toImgTag())",
					width : $control.defaultImage.width,
					height: $control.defaultImage.height
				};
			#else 
				comboElm.defaultImage = null; 
			#end
			#if(!$control.comboBehavior.textEditable)
				inpElm.readOnly = true;
			#end
			#if($control.sessionContext.userAgent.isIE() && $control.sessionContext.userAgent.getMajorVersion() < 7)
				comboElm.adjustIEBlockerId = "win_${control.controlID}_blocker";
			#end
			#if($control.readonly)
				jQuery(inpElm).addClass("x-readonly");
			#end
			#if($control.flagAsError)
				jQuery(comboElm).addClass("x-error");
			#end
			JWic.controls.Combo.initialize("$control.controlID", inpElm, $control.comboBehavior.dataFilter);
		}
		

	#end
	},
	
	/**
	 * ..
	 */
	destroy : function(element) {
		var inpElm =document.getElementById("${fld.id}");
		if (inpElm) {
			JWic.controls.Combo.destroy("$control.controlID", inpElm);
		}

	}
}