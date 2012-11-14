{
	
	afterUpdate: function(element){
		var id = JQryEscape('${control.controlID}')
		
		jQuery.datepicker.setDefaults( jQuery.datepicker.regional[ "" ] );
		
		jQuery("#"+id).addClass("x-readonly");
		
		//localize here :D
		jQuery( "#"+id ).datepicker( jQuery.datepicker.regional[ '${control.locale.country.toLowerCase()}' ] );
	
		//AJAX stuff :D
		jQuery("#"+id).change(function(){			
			JWic.fireAction(this.id, 'datechanged', '' + (jQuery("#"+id).datepicker('getDate').getTime()));
		})
	},
	
	destroy: function(element) {
	
	}

}