{
	// Attach to events...
	afterUpdate: function(element) {
	#if($control.visible)
		var tblElm = jQuery('#tbl_'+JWic.util.JQryEscape('${control.controlID}'));
		var btn = jQuery('#btn_'+JWic.util.JQryEscape('${control.controlID}'));
		if (tblElm && btn) {
			tblElm.attr("_ctrlEnabled", "$control.isEnabled()");
			tblElm.attr("_confirmMsg", "$escape.escapeJavaScript($!control.confirmMsg)");
			JWic.controls.ButtonLegacy.initialize(tblElm, btn);
			#* Add IE6 fix *#
			#if(!$control.modal && $control.sessionContext.userAgent.isIE() && $control.sessionContext.userAgent.getMajorVersion() < 7)
				btn.width(1);
			#end
			#if($control.width > 0)
				btn.width("${control.width}");
			#end
		}
	#end
	}, 
	
	destroy: function(element) {
		jQuery('#tbl_'+JWic.util.JQryEscape('${control.controlID}'));
		var tblElm = jQuery('#tbl_'+JWic.util.JQryEscape('${control.controlID}'));
		var btn = jQuery('#btn_'+JWic.util.JQryEscape('${control.controlID}'));
		if (tblElm && btn) {
			JWic.controls.ButtonLegacy.destroy(tblElm, btn);
		}
	}
}