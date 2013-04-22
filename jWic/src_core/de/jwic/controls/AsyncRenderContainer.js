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
				
				var seqNumFld = jQuery('seqnum_${control.controlID}');
				if (seqNumFld.value != elm.seqNum) {
					JWic.log("Invalid seqNum - skipping update (" + seqNumFld.value + "; received: " + elm.seqNum + ")");
					return;
				}
								
				//var control = jQuery('arc_${control.controlID}');
				var control = document.getElementById('arc_${control.controlID}');
				var scripts = [];
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
						var deLst = JWic.destroyList;
						for ( var i = deLst.length - 1; i >= 0; i--) {
							if (deLst[i] && (deLst[i].key == elm.key || deLst[i].key.indexOf(elm.key + ".") === 0)) {
								JWic.log("Destroy: " + deLst[i].key + " because of " + elm.key);
								deLst[i].destroy(control);
								deLst.splice(i, 1);
							}
						}
						// remove any beforeUpdateCallbacks
						var allKeys = [];
						jQuery.each(JWic.beforeRequestCallbacks, function(key, value) {
						      allKeys.push(key);
						});
						
						jQuery.each(allKeys, function(key, val) {
							
							if (val.indexOf(elm.key) === 0) {
								delete JWic.beforeRequestCallbacks[key];
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
						
						jQuery(control).replaceWith(elm.html);
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