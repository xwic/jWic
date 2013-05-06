{
	
	afterUpdate: function(element){
		/*
		 * clone the region info
		 * so you can maintain language date but change date format only on this instance of the datepicker
		 */
		var region = jQuery.extend(true, {}, jQuery.datepicker.regional['${control.locale.language}']);
		/*
		 * set date format in needed
		 */
		#if($control.dateFormat != "noformat")
			region.dateFormat = '${control.dateFormat}'			
		#end
		
		/*
		 *	default back to English if selected region is undefined 
		 */
		if(region == undefined){
			region = jQuery.extend(true, {}, jQuery.datepicker.regional['en']);
			/*
			 * notify java control to default back to Locale.ENGLISH as well
			 */
			JWic.fireAction('${control.controlID}','localeNotFound','');
		}
		
		/*
		 * init the datepicker
		 */
		var id = JWic.util.JQryEscape('${control.controlID}');
		var datetimepicker = jQuery( "#" + id ).datetimepicker($control.buildJsonOptions());
		
		datetimepicker.datetimepicker("option",region);		
		
		/*
		 * set datepicker date from java
		 */
		var timeStamp = '${control.currentTime}';
		timeStamp = parseInt(timeStamp);
		if(!isNaN(timeStamp)){			
			var date = new Date(timeStamp);			
			datetimepicker.datetimepicker('setDate',date);
		}else{
			datetimepicker.datetimepicker('setDate',null);
		}
		
		
		function nullDateNotifier(e){			
			if(this.value == ''){
				JWic.fireAction(this.id, 'dateisempty', '');
			}
		}
		
		/*
		 *  AJAX stuff :D
		 */
		datetimepicker.change(function(){
			
			var date = datetimepicker.datetimepicker('getDate');
			if(date!=null){
				JWic.fireAction(this.id, 'datechanged', '' + date.getTime());
			}else{
				nullDateNotifier();
			}
			
		});
		
		datetimepicker.keyup(nullDateNotifier);
		
		#if($control.readonly)
			datetimepicker.addClass("x-readonly");
		#end
		#if($control.flagAsError)
			datetimepicker.addClass("x-error");
		#end
		
		var endDateTextBox = jQuery('#' + JWic.util.JQryEscape('${control.slaveId}'));
		
		datetimepicker.datetimepicker('option', 'onClose', function(dateText, inst) {
			if (endDateTextBox.val() != '') {
				var testStartDate = datetimepicker.datetimepicker('getDate');
				var testEndDate = endDateTextBox.datetimepicker('getDate');
				if (testStartDate > testEndDate)
					endDateTextBox.datetimepicker('setDate', testStartDate);
			}
			else {
				endDateTextBox.val(dateText);
			}
		});
		
		datetimepicker.datetimepicker('option', 'onSelect',  function (selectedDateTime){
			endDateTextBox.datetimepicker('option', 'minDate', datetimepicker.datetimepicker('getDate') );
		});
		
		endDateTextBox.datetimepicker('option', 'onClose', function(dateText, inst) {
			if (startDateTextBox.val() != '') {
				var testStartDate = datetimepicker.datetimepicker('getDate');
				var testEndDate = endDateTextBox.datetimepicker('getDate');
				if (testStartDate > testEndDate)
					datetimepicker.datetimepicker('setDate', testEndDate);
			}
			else {
				datetimepicker.val(dateText);
			}
		});
		
		endDateTextBox.datetimepicker('option', 'onSelect',  function (selectedDateTime){
			datetimepicker.datetimepicker('option', 'maxDate', endDateTextBox.datetimepicker('getDate') );
		});
		
	},
	
	destroy: function(element) {
		
	}

}