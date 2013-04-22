{
	afterUpdate: function(element) {
		jQuery(element).find("#"+JWic.util.JQryEscape("${control.controlID}")).accordion({
			active : $control.getActiveGroupIndex()
		});
	}
}