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
			
				function setMinMax(minMax,other){
					return function(){
						other.datetimepicker('option',minMax,jQuery(this).datetimepicker('getDate'));
					}
				}
			
				function setDates(slave,check){
					return function(){
						var startDate = jQuery(this).datetimepicker('getDate');
						var endDate = slave.datetimepicker('getDate');
						
						endDate = endDate || new Date(Number.MAX_VALUE); // it either this or the end of time its self (to avoid null check :) not a fan of null checking)
						
						console.warn(startDate,endDate);
						
						var date = new Date(check(startDate.getTime(),endDate.getTime()));
						
						slave.datetimepicker('setDate', date);							
					};
				}
				
				
				var setDatesForMaster = setDates(datetimepicker, Math.max);
				var setDatesForSlave = setDates(masterDateTextBox, Math.min);
				
				var setMaxMaster = setMinMax('maxDate',masterDateTextBox);
				var setMinSlave = setMinMax('minDate',datetimepicker);
				
				masterDateTextBox.data('onSelectListener').push(setDatesForMaster);
				masterDateTextBox.data('onCloseListener').push(setMinSlave);
				
				datetimepicker.data('onSelectListener').push(setDatesForSlave);
				datetimepicker.data('onCloseListener').push(setMaxMaster);
				
				setMaxMaster.call(datetimepicker);//do the initial setting of stuff for the refresh
				setMinSlave.call(masterDateTextBox);//here to 
				
			#end
		}
			
	},
	
	destroy: function(element) {
		
	}

}