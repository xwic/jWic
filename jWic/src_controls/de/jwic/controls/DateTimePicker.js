{
	
	afterUpdate: function(element){
		var inpElm = jQuery('#'+JWic.util.JQryEscape("${control.controlID}"));
		if (inpElm) {
			var datetimepicker = JWic.controls.DateTimePicker.initialize(inpElm, '${control.controlID}', '${control.locale.language}', '${control.dateFormat}','${control.timeFormat}', $control.buildJsonOptions(), '${control.currentTime}', '${control.field.id}');
			#if($control.readonly)
				datetimepicker.addClass("x-readonly");
			#end
			#if($control.flagAsError)
				datetimepicker.addClass("x-error");
			#end
			
			#if($control.masterId)
				var masterDateTextBox = jQuery('#' + JWic.util.JQryEscape('${control.masterId}'));
			 	JWic.controls.DateTimePicker.masterSlave(masterDateTextBox, datetimepicker);
			#end
		}
			
	},
	
	destroy: function(element) {
		
	}

}