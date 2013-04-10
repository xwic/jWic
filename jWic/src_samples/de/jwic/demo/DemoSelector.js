{
	afterUpdate: function(element) {
		jQuery(element).find("#"+JQryEscape("${control.controlID}")).accordion({
			active : $control.getActiveGroupIndex()
		});
	}
}