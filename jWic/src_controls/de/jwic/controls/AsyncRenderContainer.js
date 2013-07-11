{
	// Attach to events...
	afterUpdate: function(element) {
		
		var seqNumFld = jQuery('seqnum_${control.controlID}');
		var param = {};
		param["seqNum"] = seqNumFld.value;
		
		JWic.resourceRequest('$control.controlID', function(ajaxResponse) {
			try {
				JWic.log("AsyncRenderingStart ");
				var elm = jQuery.parseJSON(ajaxResponse.responseText);
				if(elm.fail){
					JWic.fireAction('$control.controlID','onFail','');//trigger fail callback on the backend
					JWic.log("AsyncRenderingFail");
					return;
				}
				
				
				var seqNumFld = jQuery('seqnum_${control.controlID}');
				if (seqNumFld.value != elm.seqNum) {
					JWic.log("Invalid seqNum - skipping update (" + seqNumFld.value + "; received: " + elm.seqNum + ")");
					return;
				}
								
				var control = document.getElementById('arc_${control.controlID}');
				JWic.updateControl(elm, control);
				#if($control.notifySuccess)
					JWic.fireAction('$control.controlID','onSuccess','');//trigger success callback on the backend
				#end
			} catch (x) {
				JWic.log("Error in AsyncRenderStart: " + x);
			}
		}, param);
		
	}, 
	
	destroy: function(element) {
		
	}
}