{
	// Attach to events...
	afterUpdate: function(element) {
		
		var seqNumFld = $('seqnum_${control.controlID}');
		var param = new Hash();
		param.set("seqNum", seqNumFld.value);
		
		JWic.resourceRequest('$control.controlID', function(ajaxResponse) {
			try {
				JWic.log("AsyncRenderingStart ");
				var elm = ajaxResponse.responseText.evalJSON(true);
				
				var seqNumFld = $('seqnum_${control.controlID}');
				if (seqNumFld.value != elm.seqNum) {
					JWic.log("Invalid seqNum - skipping update (" + seqNumFld.value + "; received: " + elm.seqNum + ")");
					return;
				}
								
				var control = $('arc_${control.controlID}');
				var scripts = new Array();
				if (elm.scripts) {
					for ( var i = 0; i < elm.scripts.length; i++) {
						scripts.push({
							key: elm.scripts[i].controlId, 
							script: eval('(' + elm.scripts[i].script + ')')
						});
					}
				}

				if (control) {
					// call beforeUpdate in scripts
					for ( var i = 0; i < scripts.length; i++) {
						if (scripts[i].script.beforeUpdate) {
							scripts[i].script.beforeUpdate(control);
						}
					}
					var doReplace = true;
					for ( var i = 0; i < scripts.length; i++) {
						if (scripts[i].script.doUpdate) {
							if (scripts[i].script.doUpdate(control)) {
								doReplace = false;
								break;
							}
						}
					}
					if (doReplace) { // replace the DOM element with the
										// rendered snippit received

						// call destroy handler and remove them
						var deLst = JWicInternal.destroyList;
						for ( var i = deLst.length - 1; i >= 0; i--) {
							if (deLst[i] && (deLst[i].key == elm.key || deLst[i].key.startsWith(elm.key + "."))) {
								JWic.log("Destroy: " + deLst[i].key + " because of " + elm.key);
								deLst[i].destroy(control);
								deLst.splice(i, 1);
							}
						}
						// remove any beforeUpdateCallbacks
						
						var allKeys = JWicInternal.beforeRequestCallbacks.keys().clone();
						allKeys.each(function(key) {
							if (key.startsWith(elm.key)) {
								JWicInternal.beforeRequestCallbacks.unset(key);
							}
						});
						
						// register destroy handler
						for ( var i = 0; i < scripts.length; i++) {
							if (scripts[i].script.destroy) {
								deLst.push( {
									key :scripts[i].key,
									destroy :scripts[i].script.destroy
								});
							}
						}

						control.replace(elm.html);

						// call afterUpdate in scripts
						for ( var i = 0; i < scripts.length; i++) {
							if (scripts[i].script.afterUpdate) {
								scripts[i].script.afterUpdate(control);
							}
						}
					}
				}

			} catch (x) {
				JWic.log("Error in AsyncRenderStart: " + x);
			}
		}, param);
		
	}, 
	
	destroy: function(element) {
		
	}
}