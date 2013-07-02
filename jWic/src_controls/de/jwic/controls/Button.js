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
				btn.removeClass('j-button-text-only').removeClass('j-button-text-icon-secondary').removeClass('ui-button-text-only');
				if(btn.text()===''){
					btn.addClass('j-button-icon-only');
				}else{
					btn.addClass('j-button-text-icon');
				}
			btn.append('<span class="j-button-icon"><img border="0" src="$control.icon.path"></span>');
			#end
			if(btn.find('.ui-icon.ui-icon-triangle-1-s').length!==0){
				btn.removeClass('j-button-text-only').removeClass('j-button-icon-only').removeClass('j-button-text-icon').addClass('j-button-text-icons');
			};
			
			
			
		}
	#end
	}
}