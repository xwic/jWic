
{
	/**
	 * Invoked before the element is updated.
	 */ 
	beforeUpdate: function() {
		
	},
	
	/**
	 * Invoked when the element needs to be updated. If this function returns
	 * false, the existing HTML element is replaced by the rendered part that
	 * comes from the server. If the script is doing the update, it should return
	 * true, to prevent the update.
	 */
	doUpdate: function(element) {
		return false;
	},
	/**
	 * Invoked after the DOM element was updated. This function is NOT updated if
	 * the custom doUpdate function returned true.
	 */
	afterUpdate: function(element) {
		JWic.controls.ColumnSelector.initialize('${control.controlID}', {
			hideDescription : $control.hideDescription,
			immediateUpdate : $control.immediateUpdate
		});
	},
	
	/**
	 * Invoked when the existing element is removed from the DOM tree.
	 */
	destroy : function(element) {
		JWic.controls.ColumnSelector.destroy('${control.controlID}');
	}
}