/*
 * Copyright 2005 jWic group (http://www.jwic.de)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * -------------------------------------------------------------------------
 * This file contains the core javascript functions for the client server
 * communication. It is a replacement for the previous jwic.js file.
 */

/**
 * JWic defines the public API for JWic server/client communication.
 */
var JWic = {
	version :'5.0.0',
	debugMode : false,
	_logCount : 0,
	contextPath : "",
	
	/**
	 * Indicates if the client is currently sending or waiting for an action
	 * request.
	 */
	isProcessing :false,
	
	commandQueue : [],
	
	/**
	 * List of static control libraries already loaded.
	 */
	loadedStaticLibs : [],

	/**
	 * The time in milliseconds before the please wait message appears.
	 */
	pleaseWaitDelayTime :500,
	
	lastResizeTime :0,

	/**
	 * List which contains all destroy functions for the existing controls.
	 */
	destroyList : [],

	/**
	 * List which contains an optional callback function per control that is
	 * invoked before a request is send to the server.
	 */
	beforeRequestCallbacks : {},
	
	/**
	 * JWic form element
	 */
	jwicform : null,

	/**
	 * Returns a jQuery encapsulated document element. The element is retrieved
	 * using document.getElementById and then extended with jQuery. This eliminates the
	 * need to escape the control ID when using jQuery's native $/jQuery function. 
	 */
	$ : function(id) {
		var elm = document.getElementById(id);
		if (elm) {
			return jQuery(elm);
		}
		return null;
	},
	
	/**
	 * Handle the response from an fireAction request.
	 */
	handleResponse : function(ajaxResponse) {
		//var jwicform = jQuery('#jwicform').get(0);

		if (ajaxResponse.status == 0 && ajaxResponse.responseText == "") {
			alert("The server did not respond to the request. Please check your network connectivity and try again.");
			jwicform.elements['__ctrlid'].value = '';
			jwicform.elements['__action'].value = 'refresh';
			jwicform.submit();
			return;
		}
		//var response = ajaxResponse.responseText.evalJSON(false);
		var response = jQuery.parseJSON(ajaxResponse.responseText);
		if (response.exception) {
			alert("A server side exception occured: " + response.exception + "\n"
					+ "Hit ok to refresh.");
			jwicform.elements['__ctrlid'].value = '';
			jwicform.elements['__action'].value = 'refresh';
			jwicform.submit();
			return;
		}

		if (typeof (response.ticket) != 'undefined') {
			// update ticket number
			jwicform.elements['__ticket'].value = response.ticket;

			if (response.requireRedraw) {
				// normaly submit the whole page, but clear action before
				jwicform.elements['__ctrlid'].value = '';
				jwicform.elements['__action'].value = 'refresh';
				jwicform.submit();
				return;
			}

			if (response.requiredJS) {
				jQuery.each(response.requiredJS, function(idx, elm) {
					JWic.requiresStaticLibrary(elm);
				});
			}
			
			if (response.updateables) {
				
				jQuery.each(response.updateables, function(idx, elm) {
					JWic.updateControl(elm);
				});
			}
			
			if (response.scriptQueue) {
				jQuery.each(response.scriptQueue, function(idx, line) {
					try {
						eval(line);
					} catch (e) {
						JWic.log("Error executing script queue: " + e + " for line " + line);
					}
				});
			}
			this.endRequest();
		}
	},

	/**
	 * Update a control on the document.
	 */
	updateControl : function(elm, control) {
		control = control ? control : document.getElementById("ctrl_" + elm.key);
		
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
					try {
						scripts[i].script.beforeUpdate(control);
					} catch (e) {
						JWic.log("Error in beforeUpdate: " + e + " (Control: " + elm.key + ")");
					}
				}
			}
			var doReplace = true;
			for ( var i = 0; i < scripts.length; i++) {
				if (scripts[i].script.doUpdate) {
					try {
						if (scripts[i].script.doUpdate(control)) {
							doReplace = false;
							break;
						}
					} catch (e) {
						JWic.log("Error in doUpdate: " + e + " (Control: " + elm.key + ")");
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
						try {
							deLst[i].destroy(control);
						} catch (e) {
							JWic.log("Error in destroy: " + e + " (Control: " + deLst[i].key + ")");
						}
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
						try {
							scripts[i].script.afterUpdate(control);
						} catch (e) {
							JWic.log("Error in afterUpdate: " + e + " (Control/Container: " + elm.key + ")");
						}
					}
				}
			}
		} else {
			// alert("A control with the ID '" + elm.key + "' does
			// not exist on the page and can not be updated.");
		}
	},
	
	/**
	 * Clears all inProcess flags and hides the please wait screen, if it is
	 * visible.
	 */
	endRequest : function() {
		JWic.isProcessing = false;
		if (JWic.commandQueue.length > 0) {
			JWic._processNextAction()
		}

		JWic.showClickBlocker(false);
	},
	
	/**
	 * Shows or hides the block clicker, so the user cannot click other link
	 * during the processing of the current click.
	 */
	showClickBlocker : function(showBlocker) {

		if (showBlocker && !JWic.isProcessing) {
			return;
		}

		var elem = jQuery("#click_blocker");
		var msg = jQuery("#click_blocker_message");
		if (elem) {
			if (showBlocker) {
				var $doc = jQuery(document);
				var docHeight = $doc.height();
				var docWidth = $doc.width();

				elem.css( {
					position : 'absolute',
					top : '0px',
					left : '0px',
					height : docHeight + 'px',
					width : docWidth + 'px',
					backgroundColor : '#a0a0a0',
					opacity : '0.3',
					filter : 'alpha(opacity=30)'
				});
				if (msg) {
					$win = jQuery(window);
					var nTop = (($win.height() - msg.height()) / 2) + $win.scrollTop();
					var nLeft = (($win.width() - msg.width()) / 2) + $win.scrollLeft();
					msg.css(
						{
							position : 'absolute',
							top: nTop + 'px',
							left: nLeft + 'px'
						}
					);
				}
			}
			if (showBlocker) {
				elem.show();
				if (msg) msg.show();
			} else {
				elem.hide();
				if (msg) msg.hide();
			}

		}
	},

	/**
	 * Returns informations about the window size and scrolling position.
	 */
	getWindowSize : function() {
		var myWidth = 0, myHeight = 0, scrollTop, scrollLeft;
		var type;
		if (typeof (window.innerWidth) == 'number') {
				// Non-IE
			myWidth = window.innerWidth;
			myHeight = window.innerHeight;
			scrollLeft = window.pageXOffset;
			scrollTop = window.pageYOffset;
		} else if (document.documentElement
				&& (document.documentElement.clientWidth || document.documentElement.clientHeight)) {
			// IE 6+ in 'standards compliant mode'
			myWidth = document.documentElement.clientWidth;
			myHeight = document.documentElement.clientHeight;
			scrollLeft = document.documentElement.scrollLeft;
			scrollTop = document.documentElement.scrollTop;
		} else if (document.body
				&& (document.body.clientWidth || document.body.clientHeight)) {
			// IE 4 compatible
			myWidth = document.body.clientWidth;
			myHeight = document.body.clientHeight;
			scrollLeft = document.body.scrollLeft;
			scrollTop = document.body.scrollTop;
		}
		return [ myWidth, myHeight, scrollLeft, scrollTop ];
	},
	
	/**
	 * Send an action request to the server, including the form values. The
	 * result contains the modified controls, which will then be updated on the
	 * page.
	 */
	fireAction : function(senderControl, actionName, actionParameter) {
		
		JWic.commandQueue.push({
			senderControl : senderControl,
			actionName : actionName,
			actionParameter : actionParameter
		});
		if (!JWic.isProcessing) {
			JWic._processNextAction()
		}
	},
	
	/**
	 * Check if the specified library was loaded or not. If it is not loaded
	 * yet, it will be added to the page. 
	 */
	requiresStaticLibrary : function(libraryName) {
		
		if (!JWic.loadedStaticLibs[libraryName]) {
			JWic.log("Loading static library " + libraryName);
			
			var elm = "<SCRIPT src=\"" + JWic.contextPath + "/cp/" + libraryName + "\"></SCRIPT>";
			
			jQuery("head").append(elm);
			
			JWic.loadedStaticLibs[libraryName] = true;
		} 
		
	},
	
	_processNextAction : function() {
		
		if (JWic.commandQueue.length == 0 || JWic.isProcessing) {
			return;
		}
		
		var cmd = JWic.commandQueue[0]; // take first in
		JWic.commandQueue.splice(0, 1);
		
		JWic.isProcessing = true;
		window.setTimeout("JWic.showClickBlocker(true)",
				JWic.pleaseWaitDelayTime);

		if (!jwicform) {
			// This might occure if the page is refreshed due to a timeout
			JWic.log("jwicform not found - command skipped");
			return;
		}
		jwicform.elements['__ctrlid'].value = cmd.senderControl;
		jwicform.elements['__action'].value = cmd.actionName;
		jwicform.elements['__acpara'].value = cmd.actionParameter;

		// collect system informations.
		var sysinfoXY = JWic.getWindowSize();
		var sysinfo = sysinfoXY[0] + ";" + sysinfoXY[1] + ";" + sysinfoXY[2]
				+ ";" + sysinfoXY[3];
		if (jwicform.elements['__sysinfo']) {
			jwicform.elements['__sysinfo'].value = sysinfo;
		}

		jQuery.each(JWic.beforeRequestCallbacks, function (key, item) {
			item();
		});
		
		// check for file attachments
	    for (var i = 0; i < jwicform.elements.length; i++) {
	        var element = jwicform.elements[i];
	        if (element.type == "file" && element.value != "") {
	        	// if a file-upload control is on the page that has a file assigned,
	        	// a real submit is required to transfer the file to the server.
	        	// make sure that the encoding type is multipart, before the data is submitted.
	        	jQuery(jwicform).trigger("beforeSubmit");
	        	jwicform.encoding = 'multipart/form-data';
	        	jwicform.submit();
	        	return;
	        }
	    }
	    jQuery(jwicform).trigger("beforeSerialization");	    
		var paramData = jQuery(jwicform).serialize();
		jQuery(jwicform).trigger("afterSerialization");

		paramData+="&_ajaxreq=1";
		paramData+="&_format=JSON";

		var url = document.location.href;
		var idx = url.indexOf('#');
		if (idx != -1) {
			url = url.substring(0, idx);
		}
		jQuery.ajax({
			url: url,
			type :'post',
			dataType: 'json',
			data : paramData,
			success : function(data, textStatus, jqXHR) {
		
				JWic.handleResponse(jqXHR);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(
						"An exception has occured processing the server response: "
								+ errorThrown, "Error Notification.");
				JWic.endRequest();
			}
		});
	},
	
	/**
	 * Opens a request to a specific control. The control must implement the
	 * IResourceControl interface to reply to the request.
	 */
	resourceRequest: function(controlId, callBack, parameter) {
		
		var paramData = {};
		
		if (jQuery.isPlainObject(parameter)) {
			jQuery.each(parameter, function(key, val){
				paramData[key] = val;
			});
			
		} else {
			paramData['parameter'] = parameter;
		}
		paramData['controlId'] = controlId;
		paramData['_resreq'] = '1';
		paramData['_msid'] = document.forms['jwicform'].elements['_msid'].value;

		var url = document.location.href;
		var idx = url.indexOf('#');
		if (idx != -1) {
			url = url.substring(0, idx);
		}
		
		jQuery.ajax({
			url: url,
			type :'post',
			data : paramData,
			success : function(data, textStatus, jqXHR) { callBack(jqXHR); } ,
			error : function(jqXHR, textStatus, errorThrown) {
				if (jqXHR.status == 404) { 
					callBack(jqXHR);
				} else {
					alert('resource request failed:' + jqXHR.status + " " + jqXHR.statusText);
				}
			}
		});
		
	},

	/**
	 * Restores the scrolling position of a scrollable container. This is
	 * required if the container was updated and the old position must be
	 * restored.
	 */
	restoreScrolling : function(ctrlId, paneId) {
		if (typeof paneId == "undefined") {
			paneId = "div_" + ctrlId;
		}
		var pane = document.getElementById(paneId);
		if (pane) {
			
			var top = jwicform.elements['fld_' + ctrlId + '.top'].value;
			var left = jwicform.elements['fld_' + ctrlId + '.left'].value;
			pane.scrollTop = top;
			pane.scrollLeft = left;
			// log("Set scrolling for '" + paneId + "' to " + top + ", " +
			// left);
		} else {
			// log("WARNING: Can't fix scrolling for " + paneId);
		}

	},
	/**
	 * Show a dialog with a message. Encapsulates the PWC library functions.
	 */
	alert : function(message, title) {
		//TODO: replace with jQuery
		Dialog.alert(message, {
			className :"alphacube",
			options :"",
			title :title ? title : ""
		});
	},
	
	/**
	 * Add a new callback.
	 */
	addBeforeRequestCallback : function(controlId, callback) {
		JWic.beforeRequestCallbacks[controlId] = callback;
	},
	
	log : function(message) {

		if (typeof console != "undefined") {
			console.log(message);
		} 
		
		if (JWic.debugMode) {
			var elem = document.forms['jwicform'].elements['_debugLog'];
			elem.value = JWic._logCount + ":" + message + "\n" + elem.value;
			JWic._logCount++;
		}
	}


};

JWic.util = {
		clearSelection : function() {
			if(document.selection && document.selection.empty) {
				document.selection.empty();
			} else if (window.getSelection) {
				var sel = window.getSelection();
				if(sel && sel.removeAllRanges) {
					sel.removeAllRanges() ;
				}
			}
		},
		
		removeElement: function(val, removeMe, seperator) {
			if (!seperator) seperator = ";";
			var x = val.split(seperator);
			var n = "";
			jQuery.each(x, function(key, elm) {
				if (elm != removeMe) {
					if (n.length != 0) {
						n += seperator;
					}
					n += elm;
				}
			});
			return n;
		},
		
		JQryEscape : function (str){
			return str.replace(/([;&,\.\+\*\~':"\!\^#$%@\[\]\(\)=>\|])/g, '\\$1');
		}		
}

/**
 * Common UI Functions.
 */
JWic.ui = {

		/**
		 * Displays a notification dialog on the top of the page.
		 */
		Notify : {
		
			/**
			 * Displays the message box.
			 */
			display : function (message, type, duration, delay) {
				
				duration = duration || 3000;
				type = type || "alert";

				var x = noty({
					text: message,
					timeout : duration,
					type : type
				});
				
				return;
				
			}
		}			
}
