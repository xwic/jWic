(function(jQuery){
	jQuery.datepicker.regional['de'] = {
		monthNames: ['Januar','Februar','M�rz','April','Mai','Juni',
		     		'Juli','August','September','Oktober','November','Dezember'],
		     		monthNamesShort: ['Jan','Feb','M�r','Apr','Mai','Jun',
		     		'Jul','Aug','Sep','Okt','Nov','Dez'],
		     		dayNames: ['Sonntag','Montag','Dienstag','Mittwoch','Donnerstag','Freitag','Samstag'],
		     		dayNamesShort: ['So','Mo','Di','Mi','Do','Fr','Sa'],
		     		dayNamesMin: ['So','Mo','Di','Mi','Do','Fr','Sa'],
		     		dateFormat: 'dd.mm.yy', firstDay: 1,
		     		renderer: jQuery.datepicker.defaultRenderer,
		     		prevText: '&#x3c;zur�ck', prevStatus: 'letzten Monat zeigen',
		     		prevJumpText: '&#x3c;&#x3c;', prevJumpStatus: '',
		     		nextText: 'Vor&#x3e;', nextStatus: 'n�chsten Monat zeigen',
		     		nextJumpText: '&#x3e;&#x3e;', nextJumpStatus: '',
		     		currentText: 'heute', currentStatus: '',
		     		todayText: 'heute', todayStatus: '',
		     		clearText: 'l�schen', clearStatus: 'aktuelles Datum l�schen',
		     		closeText: 'schlie�en', closeStatus: 'ohne �nderungen schlie�en',
		     		yearStatus: 'anderes Jahr anzeigen', monthStatus: 'anderen Monat anzeigen',
		     		weekText: 'Wo', weekStatus: 'Woche des Monats',
		     		dayStatus: 'W�hle D, M d', defaultStatus: 'W�hle ein Datum',
		     		isRTL: false
		 		};
})(jQuery);