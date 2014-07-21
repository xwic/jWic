/**
 * This is a basic construct of control JavaScript snippets.
 */
 
{
	/**
	 * Invoked before the element is updated.
	 */ 
	beforeUpdate: function() {
		//BasicControlScript.beforeUpdate(element, $control.buildJsonOptions()); //see BasicControlScript.static.js
	},
	
	/**
	 * Invoked when the element needs to be updated. If this function returns
	 * false, the existing HTML element is replaced by the rendered part that
	 * comes from the server. If the script is doing the update, it should return
	 * true, to prevent the update.
	 */
	doUpdate: function(element) {
		return false;;//or optionally return BasicControlScript.doUpdate(element, $control.buildJsonOptions()); //see BasicControlScript.static.js
	},
	/**
	 * Invoked after the DOM element was updated. This function is NOT updated if
	 * the custom doUpdate function returned true.
	 */
	afterUpdate: function(element) {
		//BasicControlScript.afterUpdate(element, $control.buildJsonOptions()); //see BasicControlScript.static.js
	},
	
	/**
	 * Invoked when the existing element is removed from the DOM tree.
	 */
	destroy : function(element) {
		//BasicControlScript.destroy(element, $control.buildJsonOptions()); //see BasicControlScript.static.js
	}
}