
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
		//Position.includeScrollOffsets = true;
		var sorts = jQuery('#lst_'+JQryEscape('${control.controlID}'));
		sorts.sortable({
			update : function() {
				var fld = jQuery('#'+JQryEscape('rowOrder_${control.getControlID()}')).get(0);
				if (fld) {
					var s = "";
					jQuery('#lst_' + JQryEscape('$control.controlID')).find('div.j-colRow').each(function(i,item) {
						s += jQuery(item).attr("jColId") + ";";
					});
					fld.value = s;
					#if($control.immediateUpdate)
						JWic.fireAction('$control.controlID', 'orderUpdated', '');
					#end
				}
			},
			scroll : true
			
		});
		sorts.sortable("enable");
//		Sortable.create('$control.controlID', {
//			tag: 'div',
//			only: 'j-colRow',
//			scroll: '$control.controlID',
//			onUpdate: function() {
//				var fld = jQuery('#'+JQryEscape('rowOrder_${control.getControlID()}')).get(0);
//				if (fld) {
//					var s = "";
//					jQuery('#' + JQryEscape('$control.controlID')).find('div.j-colRow').each(function(i,item) {
//						s += jQuery(item).attr("jColId") + ";";
//					});
//					fld.value = s;
//					#if($control.immediateUpdate)
//						JWic.fireAction('$control.controlID', 'orderUpdated', '');
//					#end
//				}
//			}
//		});
	},
	
	/**
	 * Invoked when the existing element is removed from the DOM tree.
	 */
	destroy : function(element) {
//		var sorts = jQuery('#lst_'+JQryEscape('${control.controlID}'));
//		if(sorts)
//			sorts.destroy();
	}
}