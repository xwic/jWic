{
	// store the selection so that it can be restored on "cancle".
	afterUpdate: function(element) {
		#set($field = $control.getField())
		var field = document.forms['jwicform'].elements['$field.id']; 
		if (field) {
			field._oldIdx = field.selectedIndex;
		}
	}
}