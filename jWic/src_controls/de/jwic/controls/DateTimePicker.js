{
	
	afterUpdate: function(element){
		var inpElm = jQuery('#'+JWic.util.JQryEscape("${control.controlID}"));
		var options = $control.buildJsonOptions(); 
		if (inpElm) {
			var datetimepicker = JWic.controls.DateTimePicker.initialize(inpElm, '${control.controlID}', '${control.locale.language}', '${control.dateFormat}','${control.timeFormat}', options, '${control.currentTime}', '${control.field.id}');
			#if($control.readonly)
				
				datetimepicker.addClass("x-readonly");
			#end
			#if($control.flagAsError)
				datetimepicker.addClass("x-error");
			#end
			
			#if($control.masterId)
//				var masterDateTextBox = jQuery('#' + JWic.util.JQryEscape('${control.masterId}'));
//				JWic.controls.DatePicker.masterSlave(masterDateTextBox,datetimepicker);
			#end
//			This features is not 100% done yet
//			setTimeout(function(){
//				inpElm.datetimepicker(options.open ? 'show':'hide');
//			},0);//small delay
		}
		
	},
	
	
	
	destroy: function(element) {
		
	}

}