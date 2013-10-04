{
	// Attach to events...
	afterUpdate: function(element) {
	#if($control.visible)
		var link = JWic.$('${control.controlID}'),
			infoMessage = "${control.infoMessage}" || "${control.title}";
		
		if (link) {
			JWic.controls.AnchorLink.initialize(link, "${control.controlID}", {
				#if($control.menu) menu : '$control.menu.controlID' #end
			});
			function status(what){
				return function(){
					window.status = what;
					return true;
				};
			};
			link.mouseover(status(infoMessage)).mouseout(status(""));
			
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