{
	
	afterUpdate: function(element){
		
		var region = jQuery.datepicker.regional['${control.locale.language}'];
		
		if(region == undefined){
			jQuery.datepicker.setDefaults(jQuery.datepicker.regional['']);
			region = jQuery.datepicker.regional[''];
			JWic.fireAction('${control.controlID}','localeNotFound','');
		}else{
			jQuery.datepicker.setDefaults(region);			
		}
		
		var id = JQryEscape('${control.controlID}');
		var datepicker = jQuery( "#" + id ).datepicker({
			changeMonth : ${control.isShowMonth()},
			changeYear : ${control.isShowYear()}
		});
		
		
		
		// localize here :D
		
		datepicker.datepicker( "option",region);
		
		datepicker.addClass("x-readonly");
		
		JWic.fireAction('${control.controlID}','dateformat',''+datepicker.datepicker( "option", "dateFormat" ));

		// AJAX stuff :D
		datepicker.change(function(){
			JWic.fireAction(this.id, 'datechanged', '' + datepicker.datepicker('getDate').getTime());
		});
	},
	
	destroy: function(element) {
				
	}

}