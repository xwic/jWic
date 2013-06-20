{
	// Attach to events...
	afterUpdate: function(element) {
	#if($control.visible)
		var btn = JWic.$('btn_${control.controlID}');
		if (btn) {
			JWic.controls.Button.initialize(btn, "${control.controlID}", {
				#if($control.menu) menu : '$control.menu.controlID' #end
			});
			btn.attr("_ctrlEnabled", "$control.isEnabled()");
			btn.attr("_confirmMsg", "$escape.escapeJavaScript($!control.confirmMsg)");
			#if($control.width > 0)
				btn.width("${control.width}");
			#end
			#if($control.height > 0)
				btn.height("${control.height}");
			#end
			#if($control.tooltip != "")
				btn.tooltip({
					position: {
						my: "left top",
						at: "center bottom"
						}});
			#end
			#if($control.hasIcon())
				btn.removeClass('ui-button-text-only').removeClass('ui-button-text-icon-secondary');
				if(btn.text()===''){
					btn.addClass('ui-button-icon-only');
				}else{
					btn.addClass('ui-button-text-icon');
				}
			btn.append('<span class="j-button-icon"><img border="0" src="$control.icon.path"></span>');
			#end
			if(btn.find('.ui-icon.ui-icon-triangle-1-s').length!==0){
				btn.removeClass('ui-button-text-only').removeClass('ui-button-icon-only').removeClass('ui-button-text-icon').addClass('ui-button-text-icons');
			};
			
			
			
		}
	#end
	}
}