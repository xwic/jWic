{
	
	afterUpdate: function(element){
		
		var inpElm = jQuery('#'+JWic.util.JQryEscape("${control.controlID}"));
		if (inpElm) {
			var datetimepicker = JWic.controls.DatePicker.initialize(inpElm,'${control.controlID}', '${control.locale.language}', '${control.dateFormat}',$control.buildJsonOptions(), '${control.currentTime}', '$control.field.id');
			#if($control.readonly)
				datetimepicker.addClass("x-readonly");
			#end
			#if($control.flagAsError)
				datetimepicker.addClass("x-error");
			#end
			
			#if($control.isIconTriggered())
				datepicker.datepicker("option",	"showOn", "button");
				datepicker.datepicker("option", "buttonImage", _contextPath+'/jwic/calendar/calendar.gif');
				datepicker.datepicker("option",	"buttonImageOnly", true);
				});
			#end
			

			#if($control.masterId)
//				var masterDateTextBox = jQuery('#' + JWic.util.JQryEscape('${control.masterId}'));
//				JWic.controls.DatePicker.masterSlave(masterDateTextBox,datetimepicker);
			#end
		}
		
	},
	
	destroy: function(element) {
		
	}

}