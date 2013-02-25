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
 * ------------------------------------------------------------------------
 * This file contains some functions to enhance compatibility with previous
 * jWic versions.
 */

	var debugMode = false;
	var jwicObj = "";
    var _logCount = 0;
    var _ajaxProcessing = false;

	function log(message) {
		if (debugMode) {
			var elem = document.forms['jwicform'].elements['_debugLog'];
			elem.value = _logCount + ":" + message + "\n" + elem.value;
			_logCount++;
		}
	}

	
	function WindowSize() {
		return JWicInternal.getWindowSize();
	}	

	/**
	 * Notifies a control that the user triggered a specific action.
	 *
	 * @param srcCtrl - the source control id
	 * @param sAction - the action that happened
	 * @param sParam - the parameters of the action
	 * @param trueSubmit - if the value is 'true', the page is POSTed to the server, 
	 *                     no matter if ajax updates are enabled
	 */
	function jWic_FireAction(srcCtrl, sAction, sParam, trueSubmit) {
		
		if (trueSubmit) {
			jQuery('#jwicform').get(0).submit();
		} else { 
			JWic.fireAction(srcCtrl, sAction, sParam);
		}
	}

	
	/**
	 * Fix the scrolling of the ScrollableContainer.
	 */
	function jWic_fixScrolling(ctrlId, paneId) {
		JWic.restoreScrolling(ctrlId, paneId);
	}
	
	/**
	 * jWic constrictor
	 */
	function jWicBase() {
		this.fireAction = jWic_FireAction;
		this.fixScrolling = jWic_fixScrolling;
		// array of submit listeners
		// this.addSubmitListener = jWic_addSubmitListener;
	}

	/**
	 * Access to the jWic JavaScript objects.
	 */
	function jWic() {
		return jwicObj;
	}

	/**
	 * This function must be called from within the page file inside the BODY tag!
	 */
	function jWicInit() {
		try {
			jwicObj = new jWicBase();
			//jwicObj.init(document);
		} catch (e) {
			// reload parent, for solving strange IE6 SP2 reload problem
			// infinity loop if jWicBase() contains errors ...
			parent.location.replace(parent.location.href);
		}
	}

	jwicObj = new jWicBase();
	if (jWicCalendar) {
		jwicObj.calendar = new jWicCalendar();
	}