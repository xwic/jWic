{
	// Attach to events...
	afterUpdate: function(element) {
	#if($control.visible)
		var tblElm = $("tbl_${control.controlID}");
		var btn = $("btn_${control.controlID}");
		if (tblElm && btn) {
			tblElm.writeAttribute("_ctrlEnabled", "$control.isEnabled()");
			tblElm.writeAttribute("_confirmMsg", "$escape.escapeJavaScript($!control.confirmMsg)");
			JWic.controls.Button.initialize(tblElm, btn);
			#* Add IE6 fix *#
			#if(!$control.modal && $control.sessionContext.userAgent.isIE() && $control.sessionContext.userAgent.getMajorVersion() < 7)
				btn.style.width = "1px";
			#end
			#if($control.width > 0)
				btn.style.width = "${control.width}px";
			#end
		}
	#end
	}, 
	
	destroy: function(element) {
		var tblElm = $("tbl_${control.controlID}");
		var btn = $("btn_${control.controlID}");
		if (tblElm && btn) {
			JWic.controls.Button.destroy(tblElm, btn);
		}
	}
}