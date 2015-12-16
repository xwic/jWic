/*******************************************************************************
 * Copyright 2015 xWic group (http://www.xwic.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *  
 *******************************************************************************/

jQuery.noConflict();

/**
 * JWic defines the public API for JWic server/client communication.
 */
var JWic = {
	version :'5.0.0',
	debugMode : false,
	_logCount : 0,
	contextPath : "",
	cbSeq : 0,
		
	/**
	 * Indicates if the client is currently sending or waiting for an action
	 * request.
	 */
	isProcessing :false,
	
	commandQueue : [],
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
	handleResponse : function(ajaxResponse, callBack) {
	    var util = JWic.util,
	        response = jQuery.parseJSON(ajaxResponse.responseText),
	        requiredJS = response.requiredJS || [],
            updateables = response.updateables || [],
            scriptQueue = response.scriptQueue || [],
            that = this;

	    callBack = callBack || util.identity;

        var refresh = function refresh(message){
            if(message){
                alert(message);
            }
            jwicform.elements['__ctrlid'].value = '';
            jwicform.elements['__action'].value = 'refresh';
            jwicform.submit();
        }


		if (ajaxResponse.status == 0 && ajaxResponse.responseText == "") {
			refresh("The server did not respond to the request. Please check your network connectivity and try again.");
			return;
		}

		if (response.exception) {
			refresh("A server side exception occurred: " + response.exception + "\nHit ok to refresh.");
			return;
		}

        if (response.requireRedraw) {
            refresh();
            return;
        }

		if (response.ticket === undefined) {
		    return;
		}
		jwicform.elements['__ticket'].value = response.ticket;

        var asyncScripts = jQuery.map(requiredJS, util.loadLibAsync);

        util.whenAll(asyncScripts).then(function(){
            jQuery.each(updateables, function(idx, elm) {
                JWic.updateControl(elm);
            });
        }).then(function(){
            jQuery.each(scriptQueue, function(idx, line) {
                try {
                    eval(line);
                } catch (e) {
                    JWic.log("Error executing script queue: " + e + " for line " + line);
                }
            });
        }).then(function(){
            that.endRequest();
            try {
                callBack();
            } catch (e) {
                JWic.log("callBack function failed: ", e);
            }
        });


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

		var elem = jQuery("#click_blocker").css('background-image', "none");
		var msg = jQuery("#click_blocker_message");
		if (elem) {
			if (showBlocker) {
				JWic.cbSeq++;
				var $doc = jQuery(document);
				var docHeight = $doc.height();
				var docWidth = $doc.width();

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
				elem.css({
					'height': docHeight-4,
					'width' : '100%',
					'top' : 0,
					'left' : 0
				});
				if (msg) msg.show();
				window.setTimeout("JWic.showLongDelay(" + JWic.cbSeq + ")", 1000);
			} else {
				elem.hide();
				if (msg) msg.hide();
			}

		}
	},
	
	showLongDelay : function(seqNum) {
		if (JWic.isProcessing && JWic.cbSeq === seqNum) {
			var elem = jQuery("#click_blocker");
			JWic.log("show long delay blocker")
			elem.css('background', "url('"+JWic.contextPath+"/jwic/gfx/overlay.png') repeat");
		}
	},

	/**
	 * Returns informations about the window size and scrolling position.
	 */
	getWindowSize : function() {
		var myWidth = 0, myHeight = 0, scrollTop, scrollLeft;
		var type;
		var win =jQuery(document.body).css('overflow','hidden');
		myWidth = win.innerWidth();
		myHeight = jQuery(window).innerHeight();
		scrollLeft = win.scrollTop();
		scrollTop = win.scrollLeft();
		win.css('overflow','auto');
		return [ myWidth, myHeight, scrollLeft, scrollTop ];
	},
	
	/**
	 * Send an action request to the server, including the form values. The
	 * result contains the modified controls, which will then be updated on the
	 * page.
	 */
	fireAction : function(senderControl, actionName, actionParameter, callBack) {
		
		JWic.commandQueue.push({
			senderControl : senderControl,
			actionName : actionName,
			actionParameter : actionParameter,
			callBack : callBack
		});
		if (!JWic.isProcessing) {
			JWic._processNextAction()
		}
	},

	_processNextAction : function() {
		
		if (JWic.commandQueue.length == 0 || JWic.isProcessing) {
			return;
		}
		
		var cmd = JWic.commandQueue[0]; // take first in
		JWic.commandQueue.splice(0, 1);
		
		JWic.isProcessing = true;
		window.setTimeout(function(){

		    JWic.showClickBlocker(true);

		}, JWic.pleaseWaitDelayTime);

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
				JWic.handleResponse(jqXHR, cmd.callBack);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("An exception has occured processing the server response: " + errorThrown, "Error Notification.");
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
			var top = jwicform.elements['fld_' + ctrlId + '.top'].value,
			    left = jwicform.elements['fld_' + ctrlId + '.left'].value;
			pane.scrollTop = top;
			pane.scrollLeft = left;
			// log("Set scrolling for '" + paneId + "' to " + top + ", " +
			// left);
		} else {
			// log("WARNING: Can't fix scrolling for " + paneId);
		}

	},

	/**
	 * Add a new callback.
	 */
	addBeforeRequestCallback : function(controlId, callback) {
		JWic.beforeRequestCallbacks[controlId] = callback;
	},
	
	log : function() {
		if (console !== undefined && console.log !== undefined) {
		    console.log(Array.prototype.slice.call(arguments).join(' '));
		} 
		
		if (JWic.debugMode) {
			var elem = document.forms['jwicform'].elements['_debugLog'];
			elem.value = JWic._logCount + ":" + message + "\n" + elem.value;
			JWic._logCount++;
		}
	}


};


JWic.util = (function($, JWic){
    var createJWicActionHandler = function CreateJWicActionHandler(actionId){
        return function JWicActionHandler(){
            JWic.fireAction($(this).parent('span').data('id'), actionId);
        }
    }

    var loadLibAsync = (function(){
        var cache = {},
            first = document.getElementsByTagName('script')[0],
            parent = first.parentNode;

        return function _loadLibAsync(lib){
            var promise = new jQuery.Deferred(),
                callBack = function scriptCallback(){
                    JWic.log('async loaded lib', lib);
                    promise.resolve(lib);
                    cache[lib] = true;
                };

            if(cache[lib] === true){
                promise.resolve(lib);
                JWic.log('lib:', lib, 'already loaded');
                return promise;
            }

            var script = document.createElement('script');
            script.type = "text/javascript";
            script.src=  JWic.contextPath + "/cp/" + lib;
            script.async = true;

            if(document.attachEvent !== undefined){
                script.onreadystatechange = function(){
                    if ( this.readyState === 'loaded'){
                        callBack();
                    }
                }
            }else{
                script.onload = callBack;
            }
            parent.insertBefore(script, first);

            JWic.log('async loading lib', lib);
            return promise;

        }
    }());

    var whenAll = function whenAll(promises){
        return jQuery.when.apply(jQuery, promises);
    };

    var identity = function identity(x){
        return x;
    }
    /**
     *
     */
    var loadScripts = function loadScripts(libs, scripts, scriptEvalFunction){
        scriptEvalFunction = scriptEvalFunction || identity;
        return whenAll(jQuery.map(libs,loadLibAsync)).then(function(){

            for(var prop in scripts){
                if(scripts.hasOwnProperty(prop)){
                    var script = scriptEvalFunction(scripts[prop]);
                    if(script.afterUpdate){
                        script.afterUpdate(document.body);
                    }
                    if (script.destroy) {
                        JWic.destroyList.push({
                            key : prop,
                            destroy :script.destroy
                        });
                    }
                }

            }
            return true;
        });

    };

    var body = jQuery('body');
    body.hide();
    //load the initial scripts in the page
    jQuery(document).ready(function(){
        var scripts = JWic.initialScripts.scripts,
            libs = JWic.initialScripts.statics;

        loadScripts(libs, scripts).then(function(){
            //show the body and do some cleanup
            body.show();
            delete JWic.initialScripts;
            jQuery('script[data-name=initialScripts]').remove();
        })
    });

    return {
        loadScripts : loadScripts,
        loadLibAsync : loadLibAsync,
        identity : identity,
        whenAll : whenAll,

		clearSelection : function() {
			if(document.selection && document.selection.empty) {
				try { 
					document.selection.empty(); 
				} catch(ex) {} // can happen in IE8 if nothing is selected
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
		},
		
		/*
		 * Converts simple date format compatible string into jQuery compatible date format
		 */
		convertToJqueryDateFormat : function (localFormatString){
		 
		    //Year
		    if(localFormatString.search(/y{3,}/g) >=0){                 /* YYYY */
		        localFormatString = localFormatString.replace(/y{3,}/g,     "yy");
		    }else if(localFormatString.search(/y{2}/g) >=0){            /* YY   */
		        localFormatString = localFormatString.replace(/y{2}/g,      "y");
		    }
		 
		    //Month
		    if(localFormatString.search(/M{4,}/g) >=0){                 /* MMMM */
		        localFormatString = localFormatString.replace(/M{4,}/g,     "MM");
		    }else if(localFormatString.search(/M{3}/g) >=0){            /* MMM  */
		        localFormatString = localFormatString.replace(/M{3}/g,      "M");
		    }else if(localFormatString.search(/M{2}/g) >=0){            /* MM   */
		        localFormatString = localFormatString.replace(/M{2}/g,      "mm");
		    }else if(localFormatString.search(/M{1}/g) >=0){            /* M    */
		        localFormatString = localFormatString.replace(/M{1}/g,      "m");
		    }
		 
		    //Day
		    if(localFormatString.search(/D{2,}/g) >=0){                 /* DD   */
		        localFormatString = localFormatString.replace(/D{2,}/g,     "oo");
		    }else if(localFormatString.search(/D{1}/g) >=0){            /* D    */
		        localFormatString = localFormatString.replace(/D{1}/g,      "o");
		    }
		 
		    //Day of month
		    if(localFormatString.search(/E{4,}/g) >=0){                 /* EEEE */
		        localFormatString = localFormatString.replace(/E{4,}/g,     "DD");
		    }else if(localFormatString.search(/E{2,3}/g) >=0){          /* EEE  */
		        localFormatString = localFormatString.replace(/E{2,3}/g,    "D");
		    }
		    
		    // Millisecond
		    if(localFormatString.search(/S{1,}/g) >=0){                 /* S */
		        localFormatString = localFormatString.replace(/S{1,}/g,     "l");
		    }
		    
		    // AM-PM
		    if(localFormatString.search(/a{1,}/g) >=0){                 /* a */
		        localFormatString = localFormatString.replace(/a{1,}/g,     "TT");
		    }else if(localFormatString.search(/a{2,}/g) >=0){                 /* a */
		        localFormatString = localFormatString.replace(/a{2,}/g,     "TT");
		    }
		    
		    JWic.log(localFormatString);
		    return localFormatString;
		},
		/**
		 * Converts an array of elements of type 'A' into one single element of type 'A' based of of an operation
		 * 
		 * Ex: [1,2,3,4] with the + operation would result in the number 10
		 * 
		 * var theNumber10 = JWic.util.reduce([1,2,3,4],function(a,b){
		 * 		return a+b;
		 * });
		 * 
		 * 
		 * @param arr - the array to be reduced to 1 element
		 * @param cb - the reducer function
		 * @param [first] - the optional first element in the reduction (commonly the operation's neutral element ex: 0 for +, 1 for *, '' for str concat, or even f(x) = x for function composition), 
		 * 					if not passed the first elm in the array is used
		 * @returns
		 */
		reduce : function reduce(arr,cb,first){
			var i = 0, 
				l = arr.length;
			if(!first){
				first = arr[0];
				i = 1;
			}
			for(;i<l;i++){
				first =  cb(first,arr[i],i,arr);
			}
			return first;
		},
		/**
		 * Compose an array of function into 1 function
		 * ex:
		 * var f = function (x) {return x+1};
		 * var g = function (x) {return x + 2}
		 * 
		 * var fg = JWic.util.compose([f,g]);
		 * 
		 * fg(3) === 6 === f(g(3));
		 * 
		 */
		compose : function (array){
			return JWic.util.reduce(array, function(f,g){
				return function(x){
					return f(g(x));
				}
			}, null);
		},
		/**
		 * Convert an array of type A into an array of type B
		 * as determined by the callback function
		 * 
		 * ex:
		 * 
		 * var elms = ['elm1','elm2','elm3'] - an array of element id's
		 * 
		 * function byId (id){
		 * 		return document.getElementById(id);
		 * }
		 * 
		 * map(elms, byId) => will be an array of those elements.
		 * 
		 * 
		 * @param array
		 * @param callback
		 */
		map : function map (array, callback){
			var result = [], i, l;
			for(i=0,l = array.length;i<l;i++){
				result.push(callback(array[i],i));
			}
			return result;
		},
		clickHandler : createJWicActionHandler('click'),
        blurHandler : createJWicActionHandler('blur')
    };

}(jQuery, JWic));

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
