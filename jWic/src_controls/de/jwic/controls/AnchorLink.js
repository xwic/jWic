{
	// Attach to events...
	afterUpdate: function(element) {
	#if($control.visible)
		var link = JWic.$('${control.controlID}');
		if (link) {
			JWic.controls.AnchorLink.initialize(link, "${control.controlID}", {
				#if($control.menu) menu : '$control.menu.controlID' #end
			});
			#if($control.tooltip != "")
				link.tooltip({
					position: {
						my: "left top",
						at: "center bottom"
						}});
			#end
		}
	#end
	}
}