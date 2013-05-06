{
	
	afterUpdate: function(element){
		var inpElm = jQuery('#'+JWic.util.JQryEscape("${control.controlID}"));
		if (inpElm) {
			var datetimepicker = JWic.controls.DateTimePicker.initialize(inpElm, '${control.controlID}', '${control.locale.language}', '${control.dateFormat}',$control.buildJsonOptions(), '${control.currentTime}');
			#if($control.readonly)
				datetimepicker.addClass("x-readonly");
			#end
			#if($control.flagAsError)
				datetimepicker.addClass("x-error");
			#end
			
			#if($control.slaveId)
				var endDateTextBox = jQuery('#' + JWic.util.JQryEscape('${control.slaveId}'));
			 	JWic.controls.DateTimePicker.masterSlave(datetimepicker, endDateTextBox);
			#end
		}
			
	},
	
	destroy: function(element) {
		
	}

}