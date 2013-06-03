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
		
		var datepicker = jQuery( "#" + id ).datepicker($control.buildJsonOptions());
		#if($control.isIconTriggered())
			datepicker.datepicker("option",	"showOn", "button");
			datepicker.datepicker("option", "buttonImage", _contextPath+'/jwic/calendar/calendar.gif');
			datepicker.datepicker("option",	"buttonImageOnly", true);
			});
		#end
		
		datepicker.datepicker("option",region);		
		
		/*
		 * set datepicker date from java
		 */
		var timeStamp = '${control.currentTime}';
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
		 *  Datepicker change date handling
		 */
		datepicker.change(function(){
			var date = datepicker.datepicker('getDate');
			if(date!=null){
				var date_utc = new Date(date.getUTCFullYear(), date.getUTCMonth(), date.getUTCDate(),  date.getUTCHours(), date.getUTCMinutes(), date.getUTCSeconds());
				JWic.fireAction(this.id, 'datechanged', '' + date_utc.getTime());
			}else{
				nullDateNotifier();
			}
			
		});
		
		datepicker.keyup(nullDateNotifier);
		
		#if($control.readonly)
			datepicker.addClass("x-readonly");
		#end
		#if($control.flagAsError)
			datepicker.addClass("x-error");
		#end
	},
	
	destroy: function(element) {
		
	}

}