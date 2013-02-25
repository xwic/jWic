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
		var id = JQryEscape('${control.controlID}');
		var datepicker = jQuery( "#" + id ).datepicker({
			changeMonth : ${control.isShowMonth()},			
			changeYear : ${control.isShowYear()},
			showWeek: ${control.isShowWeek()},
			
			#if($control.isIconTriggered())
				showOn: "button",
				buttonImage: _contextPath+'/jwic/calendar/calendar.gif',
				buttonImageOnly: true,
			#end

			numberOfMonths: ${control.numberOfMonths}
		});
		datepicker.datepicker("option",region);		
		
		/*
		 * set datepicker date from java
		 */
		var timeStamp = '${control.getDate().getTime()}';
		timeStamp = parseInt(timeStamp);
		if(!isNaN(timeStamp)){			
			var date = new Date(timeStamp);			
			datepicker.datepicker('setDate',date);
		}else{
			datepicker.datepicker('setDate',null);
		}
		
		
		function nullDateNotifier(e){			
			if(this.value == ''){
				JWic.fireAction(this.id, 'dateisempty', '');
			}
		}
		
		/*
		 *  AJAX stuff :D
		 */
		datepicker.change(function(){
			var date = datepicker.datepicker('getDate');
			if(date!=null){
				JWic.fireAction(this.id, 'datechanged', '' + date.getTime());
			}else{
				nullDateNotifier();
			}
			
		});
		
		datepicker.keyup(nullDateNotifier);
	},
	
	destroy: function(element) {
		
	}

}