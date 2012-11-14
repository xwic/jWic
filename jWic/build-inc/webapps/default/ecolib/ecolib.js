
JWic.ecolib = {};
JWic.ecolib.controls = {

		ProcessInfo : {
			
			/**
			 * Request a status update
			 */
			updateContent : function(controlId) {
		
					var ctrl = $("pi_" + controlId);
					if (ctrl && !ctrl.requestPending) {
						ctrl.requestPending = true;
						JWic.resourceRequest(controlId, function(ajaxResponse) {
							try {
								//JWic.log("update Refresh " + controlId);
								JWic.ecolib.controls.ProcessInfo.handleResponse(controlId, ajaxResponse);
							} catch (x) {
								// the control was probably removed. Force a regular refresh
								JWic.fireAction('', 'refresh', '');
							}
						});
					}
					
			},
			
			/**
			 * Handle the response from the server and render the status.
			 */
			handleResponse : function(controlId, resp) {
				var data = resp.responseText.evalJSON(true);
				var container = $("pi_" + controlId);
				
				if (container) { // view container might have been removed in the meantime
					if (data.monitor) {
						var m = data.monitor;
						var piLabel = $("pi_label_" + controlId);
						var piProg = $("pi_progress_" + controlId);
						var piProgBar = $("pi_progressbar_" + controlId);
						var piVal = $("pi_values_" + controlId);
						if (piLabel) {
							piLabel.update(m.infoText);
						}
						if (piVal) {
							if (m.max != 0) {
								piVal.update(m.value + " / " + m.max);
							} else {
								piVal.update(m.value);
							}
						}
						if (piProg && m.max != 0) {
							var w = piProg.getWidth() - 6;
							var total = m.max - m.min;
							var pos = m.value - m.min;
							var pr = pos / total * w;
							if (!piProgBar.hasClassName("progressBarActive")) {
								piProgBar.className = "progressBarActive";
							}
							piProgBar.setStyle( {"width" : Math.abs(pr) + "px"});
						}
						
					}
	
					container.requestPending = false;
					if (data.active) {
						window.setTimeout("JWic.ecolib.controls.ProcessInfo.updateContent('" + controlId + "')", 500);
					}
					if (data.globalRefresh) {
						JWic.fireAction('', 'refresh', '');
					}
				}
				
			}
		}

}