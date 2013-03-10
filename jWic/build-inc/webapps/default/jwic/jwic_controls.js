/*
 * Copyright 2005-2010 jWic group (http://www.jwic.de)
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
 * This file contains the scripts used by the standard controls such as
 * InputBox etc. It should be included in the basic header if those controls
 * are used (which is in most of the cases).
 */

JWic.controls = {
	/**
	 * InputBoxControl script extensions.
	 */
	InputBoxControl : {
		/**
		 * Initialize a new control.
		 */
		initialize : function(inpElm) {
			inpElm.bind("focus", JWic.controls.InputBoxControl.focusHandler);
			inpElm.bind("blur", JWic.controls.InputBoxControl.lostFocusHandler);
			
			if (inpElm.attr("xListenKeyCode") != 0) {
				inpElm.bind("keyup", JWic.controls.InputBoxControl.keyHandler);
			}
			
			if (inpElm.attr("xEmptyInfoText")) {
				if(inpElm.attr("xIsEmpty") == "true" && 
					(inpElm.val() == inpElm.attr("xEmptyInfoText") || inpElm.val() == "")) {
					inpElm.addClass("x-empty");
					inpElm.val(inpElm.attr("xEmptyInfoText"));
				} else {
					inpElm.attr("xIsEmpty", "false");
					inpElm.removeClass("x-empty");
				}
			}
			
			// override the getValue() method to "fix" the serialization
			inpElm.getValue = function() {
				if (this.attr("xEmptyInfoText") && this.attr("xIsEmpty") == "true") {
					return "";
				} else {
					return this.value;
				}
			}
			
		},
		
		/**
		 * Clean up..
		 */
		destroy : function(inpElm) {
			inpElm.unbind("focus", JWic.controls.InputBoxControl.focusHandler);
			inpElm.unbind("blur", JWic.controls.InputBoxControl.lostFocusHandler);
			
			if (inpElm.attr("xListenKeyCode") != 0) {
				inpElm.unbind("keyup", JWic.controls.InputBoxControl.keyHandler);
			}
		},
		
		/**
		 * Invoked when the focus is received.
		 */
		focusHandler : function(e) {
			var elm =  jQuery(e.target);
			elm.addClass("x-focus");
			
			if (elm.attr("xEmptyInfoText")) {
				if (elm.attr("xIsEmpty") == "true") {
					elm.val('');
					elm.removeClass("x-empty");
					elm.attr("xIsEmpty", "false");
				} 
			}
			
		},
		/**
		 * Invoked when the focus is lost.
		 */
		lostFocusHandler : function(e) {
			var elm =  jQuery(e.target);
			
			elm.removeClass("x-focus");
			if (elm.attr("xEmptyInfoText")) {
				if (elm.val() == "") { // still empty
					elm.addClass("x-empty");
					elm.val(elm.attr("xEmptyInfoText"));
					elm.attr("xIsEmpty", "true");
				} else {
					elm.attr("xIsEmpty", "false");
				}
			}
		},
		
		keyHandler : function(e) {
			var elm =  jQuery(e.target);
			
			if (e.keyCode == elm.attr("xListenKeyCode")) {
				JWic.fireAction(elm.id, 'keyPressed', '' + e.keyCode);
			}
		}
		
	},
	/*
	 * NumberInputBoxControl.js
	 */ 
	NumberInputBoxControl:{
		initialize:function(inpElem,hidden,opt){
			var options = opt || {thousends:',',decimals:'.'};
			var numberData = '0.0';
			var thounsends = options.thousends;
			var decimal = options.decimals;
			
			
			
			
			function numberWithCommas(x) {
			    var parts = x.toString().split(decimal);
			    parts[0] = parts[0].replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1"+thounsends);
			    return parts.join(decimal);
			}
			function trimLeadZeros(s){
				if(s.substring(0,1)==='0' && s.substring(1,2)!=='.'){
					return trimLeadZeros(s.substring(1));
				}else{
					return s;
				}
			}
			inpElem.val(hidden.val()==='0' ? '':numberWithCommas(hidden.val()));
			
			inpElem.bind('input',function(e){
				var numberString = trimLeadZeros(inpElem.val().replace(new RegExp(thounsends,"g"), '')); 
				numberData = numberString ==='' ? '0' : numberString;
				hidden.val(numberData);
				inpElem.val(numberWithCommas(numberData));
				
			});
			inpElem.bind('keyup',function(event){
				//lets tell the back end 'bout this. shall we
				//also validate in case of copy+paste (no cross browser way to prevent that from happening that i know of)
				if(!isNaN(hidden.val())){
					JWic.fireAction(inpElem.attr('id'), 'keyPressed', '' + event.keyCode);
					inpElem.removeClass('ui-state-error');
				}else{
					inpElem.addClass('ui-state-error');
				}
				
			});
			//lets do the validations so only numbers and the separators get through
			inpElem.bind('keypress',function(event) {				
				if( ( event.which >= 48 && event.which <= 57 )|| event.which === 13){
					//is number: let it slide also submit the data
					return;
				}else{
		        	//is not number
					if(event.which === decimal.charCodeAt(0)){
		        		if(inpElem.val().toLowerCase().indexOf(decimal.toLowerCase()) >= 0 ){		        			
		        			event.preventDefault();
		        			return;
		        		}
		        	}else{
		        		event.preventDefault();
		        		return;
		        	}
		           
		            
		        }
		    });
		}
	},
	
	/**
	 * Window control script extensions.
	 */
	Window : {
		updateHandler : function(controlId) {
			var win = jQuery('#'+JQryEscape(controlId));
			if (win) {
				//var size = win.getSize();
				var field = jQuery("#fld_" + JQryEscape(controlId));
				if (field) { // assume that if one field exists, the others exist as well.
					field.width(win.width()); 
					field.height(win.height);
					field.offset(win.offset());
				}
			} else {
				alert("No Window with ID " + controlId);
			}
		},
		
		/**
		 * To fix the problem of overlaying SELECT (and other) elements in IE6, 
		 * it moves the below IFRAME to hide the select elements.
		 */
		adjustIEBlocker : function(controlId) {
			jQuery("#win_" + JQryEscape(controlId)+ "_blocker");
			var blocker = jQuery("#win_" + JQryEscape(controlId)+ "_blocker");
			var source =jQuery("#win_" + JQryEscape(controlId)); // get the window
			JWic.controls.Window.adjustIEBlockerToWin(blocker, source);
		},
		/**
		 * Move the blocker below the source window.
		 */
		adjustIEBlockerToWin : function(blocker, source) {
			if (blocker && source) {
				blocker.setStyle( {
					width: source.getWidth(),
					height: source.getHeight(),
					top: source.getStyle("top"),
					left: source.getStyle("left"),
					display: 'block'
				});
				blocker.setOpacity(0.0);
			}
		}
		
	},
	
	/**
	 * de.jwic.controls.combo.Combo functions.
	 */
	Combo : {
		
		/**
		 * Currently open combo content box.
		 */
		_activeComboContentBox : null,
		
		/** Time the box was opened/closed */
		_openTime : 0,
		_closeTime : 0,
		_closeControlId : null,
		_delayKeySearchIdx : 0,
		_delayControlId : null,
		_lostFocusClose : false,
		
		/**
		 * Initialize a new control.
		 */
		initialize : function(controlId, inpElm) {
			var escapedControlId = JQryEscape(controlId);
			var comboBox = jQuery("#" + escapedControlId).get(0);
			var iconElm = jQuery("#" + escapedControlId + "_open").get(0);
			
			comboBox.jComboField = inpElm;
			comboBox.jComboKey = jQuery("#fld_" + escapedControlId + "\\.key").get(0);
			comboBox.dataFilter = JWic.controls.Combo.StringDataFilter;
			comboBox.keyDelayTime = 100;
			
			var jInpElm = jQuery(inpElm);
			jInpElm.focus(JWic.controls.Combo.focusHandler);
			jInpElm.blur(JWic.controls.Combo.lostFocusHandler);
			jInpElm.click(JWic.controls.Combo.textClickHandler);
			jInpElm.keyup(JWic.controls.Combo.textKeyPressedHandler);
						
			// adjust sizes
			var totalWidth = jQuery(comboBox).width();
			if (totalWidth == 0) { // seems like the control is not yet displayed - take from style.
				var s = jQuery(comboBox).css("width");
				totalWidth = parseInt(s);
			}
			var iconWidth = 0;
			if (iconElm) {
				iconWidth = jQuery(iconElm).width();
				if (iconWidth == 0) { // assume default width
					iconWidth = 20;
				}
			}
			var inpWidth = iconElm ? totalWidth - iconWidth - 7 : totalWidth - 3;
			if (jQuery.browser.msie) {
				inpWidth -= 4;
			}
			inpElm.style.width = inpWidth + "px";
			
			if (jInpElm.attr("xEmptyInfoText")) {
				if(jInpElm.attr("xIsEmpty") == "true" && 
					(jInpElm.value == jInpElm.attr("xEmptyInfoText") || inpElm.value == "")) {
					jInpElm.addClass("x-empty");
					inpElm.value = jInpElm.attr("xEmptyInfoText");
				} else {
					jInpElm.attr("xIsEmpty", "false");
					jInpElm.removeClass("x-empty");
				}
			}
			
			// override the getValue() method to "fix" the serialization
			inpElm.getValue = function() {
				if (jQuery(this).attr("xEmptyInfoText") && jQuery(this).attr("xIsEmpty") == "true") {
					return "";
				} else {
					return this.value;
				}
			}			
		},
		
		/**
		 * Clean up..
		 */
		destroy : function(controlId, inpElm) {
			var jInpElem = jQuery(inpElm);
			jInpElem.unbind("focus", JWic.controls.InputBoxControl.focusHandler);
			jInpElem.unbind("blur", JWic.controls.InputBoxControl.lostFocusHandler);
			jInpElem.unbind("click", JWic.controls.Combo.textClickHandler);
			jInpElem.unbind("keyup", JWic.controls.Combo.textKeyPressedHandler);
			
		},
		
		/**
		 * Invoked on KeyUp to handle user input.
		 */
		textKeyPressedHandler : function(e) {
			var ctrlId = jQuery(this).attr("j-controlId");
			if (ctrlId) {
				JWic.log("key pressed: " + e.keyCode + " --");
				var comboBox = jQuery("#" + JQryEscape(ctrlId)).get(0);
				if (comboBox.multiSelect) { // behave differently if multiSelect is on
					// no actions yet -- might go for scrolling etc. via keyboard later on.
				} else {
					if (e.keyCode == 13) { // enter
						JWic.controls.Combo.finishSelection(ctrlId, false);
					} else if (e.keyCode == 38 || e.keyCode == 40) {
						// scroll up/down
						var isUp = (e.keyCode == 38);
						var newSelection = -1;
						if (comboBox.selectionIdx == null) { // nothing selected
							newSelection = isUp ? comboBox.visibleCount - 1 : 0;
						} else {
							newSelection = comboBox.selectionIdx + (isUp ? -1 : 1);
						}
						if (newSelection >= 0 && newSelection < comboBox.dataItems.length) {
							JWic.controls.Combo.selectRow(ctrlId, newSelection);
						}
					} else if (e.keyCode == 8 || e.keyCode >= 46) { // backspace or delete
						
						comboBox.pickFirstFinding = comboBox.autoPickFirstHit && !(e.keyCode == 8 || e.keyCode == 46);
						JWic.controls.Combo._delayKeySearchIdx++;
						var myStart = JWic.controls.Combo._delayKeySearchIdx;
						JWic.controls.Combo._delayControlId = ctrlId;
						window.setTimeout("JWic.controls.Combo.afterKeySearchStart(" + myStart + ");", comboBox.keyDelayTime);
						
					}
				}
			}
		},
		
		afterKeySearchStart : function(triggeredIndex) {
			if (triggeredIndex == JWic.controls.Combo._delayKeySearchIdx && JWic.controls.Combo._delayControlId != null) {
				var ctrlId = JWic.controls.Combo._delayControlId;
				var comboBox = jQuery("#" + JQryEscape(ctrlId)).get(0);

				JWic.controls.Combo._delayControlId = null; // clear
				
				JWic.log("dataFilterValue set to " + comboBox.dataFilterValue);
				comboBox.dataFilterValue = comboBox.jComboField.value;
				comboBox.applyFilter = true;
				if (comboBox.dataFilterValue.length >= comboBox.minSearchKeyLength) {
					if (JWic.controls.Combo._activeComboContentBox != ctrlId) {
						if (comboBox.loadCompleted) {
							comboBox.dataLoader.prepareData(ctrlId);
						}
						JWic.controls.Combo.openContentBox(ctrlId);
					} else {
						if (comboBox.loadCompleted) {
							comboBox.dataLoader.prepareData(ctrlId);
							comboBox.contentRenderer.renderData(ctrlId);
						} else {
							if (comboBox.dataLoader) {
								comboBox.dataLoader.initializeData(ctrlId);
							}
						}
					}
				}
			} else {
				JWic.log("Invalid triggerIndex - repeative event: "+ triggeredIndex + " - expected: " + JWic.controls.Combo._delayKeySearchIdx);
			}
		},

		/**
		* Called when selecting a row via keyboard.
		*/
		selectRow : function(ctrlId, newSelection) {
			JWic.log("selectRow: " + newSelection);
			var comboBox = jQuery("#" + JQryEscape(ctrlId)).get(0);
			if (newSelection >= 0 && newSelection < comboBox.dataItems.length) {
				var newItem = comboBox.dataItems[newSelection];
				comboBox.contentRenderer.updateSelection(ctrlId, newSelection);
				comboBox.selectionIdx = newSelection;
				//remember the highlighted element
				var winId = "j-combo_contentBox";
				var win = Windows.getWindow(winId);
				if (win) {
					//don't select the item, just highlight it.
					comboBox.suggestedObject = newItem;					
				} else {
					//select the item
					JWic.controls.Combo.selectElement(ctrlId, newItem.title, newItem.key, false);
				}
				
			}
		},
		
		/**
		* Called to submit a keyboard selection via enter press.
		*/
		finishSelection : function (ctrlId, noSelection) {
			JWic.log("finished Selection");
			var comboBox = jQuery("#" + JQryEscape(ctrlId)).get(0);
			var fld = comboBox.jComboField;
			var changed = false;
			if (comboBox && comboBox.suggestedObject) {
				//submit the highlighted element
				changed = JWic.controls.Combo.selectElement(ctrlId, comboBox.suggestedObject.title, comboBox.suggestedObject.key, noSelection);
				comboBox.suggestedObject = null;
				
			} else if ((fld.value == "" || (jQuery(fld).attr("xEmptyInfoText") != null && jQuery(fld).attr("xIsEmpty") == "true")) && comboBox.jComboKey.value != "") { // a key is still selected but the entry was deleted
				JWic.log("clear values");
				changed = JWic.controls.Combo.selectElement(ctrlId, "", "", noSelection);
			}
			if (changed && JWic.controls.Combo._lostFocusClose) {
				JWic.controls.Combo.closeActiveContentBox();
			}
			comboBox.applyFilter = false; // clear filter
			comboBox.dataLoader.prepareData(ctrlId);

		},
		
		/**
		 * Invoked on the first match that is found by the renderer during a filtered rendering.
		 */
		searchSuggestion : function(comboElm, obj) {
			comboElm.suggestedObject = obj;
			if (obj == null) {
				JWic.log("SearchSuggestion : NULL Object");
				if (!comboElm.allowFreeText) {
					jQuery(comboElm).addClass("x-error");
					comboElm.jComboKey.value = "";
				}
			} else {
				JWic.log("SearchSuggestion : obj.key=" + obj.key);
				jQuery(comboElm).removeClass("x-error");
				comboElm.jComboField.value = obj.title;				
				comboElm.jComboField.focus();
				comboElm.jComboField.select();
				 if(typeof comboElm.jComboField.selectionStart != 'undefined') {
					 comboElm.jComboField.selectionStart = comboElm.dataFilterValue.length;
				 } else if (typeof document.selection != 'undefined') {
					var range = document.selection.createRange();
					range.moveStart('character', comboElm.dataFilterValue.length);
					range.select();	 
				 }
			}
			 comboElm.pickFirstFinding = false;

		},
		
		/**
		 * Invoked when the user clicks into the textbox.
		 */
		textClickHandler : function(e) {
			JWic.log("textClickHandler: Use clicked on textbox.");
			var ctrlId = jQuery(this).attr("j-controlId");
			if (ctrlId) {
				var box = jQuery("#" + JQryEscape(ctrlId)).get(0);
				if (box && box.openContentOnTextFocus && ctrlId != JWic.controls.Combo._activeComboContentBox) {
					JWic.controls.Combo.openContentBox(ctrlId);
				}
			}
		},
		
		/**
		 * Invoked when the focus is received.
		 */
		focusHandler : function(e) {
			var ctrlId = jQuery(this).attr("j-controlId");
			if (ctrlId) {
				JWic.log("focusHandler: received Focus");
				var box = jQuery("#" + JQryEscape(ctrlId)).get(0);
				if (box) {
					jQuery(box).addClass("x-focus");
					if (box.openContentOnTextFocus && ctrlId != JWic.controls.Combo._activeComboContentBox) {
						JWic.controls.Combo.openContentBox(ctrlId);
					}
					if (box.selectTextOnFocus && this.value.length != 0) {
						this.select();
					}
						
				}
			}
			if (jQuery(this).attr("xEmptyInfoText")) {
				if (jQuery(this).attr("xIsEmpty") == "true") {
					this.value = "";
					jQuery(this).removeClass("x-empty");
				} 
			}
		},
		/**
		 * Invoked when the focus is lost.
		 */
		lostFocusHandler : function() {
			JWic.log("Combo.lostFocusHandler");
			var ctrlId = jQuery(this).attr("j-controlId");
			if (ctrlId) {
				var box = jQuery("#" + JQryEscape(ctrlId)).get(0);
				if (box) {
					jQuery(box).removeClass("x-focus");
					box.applyFilter = false;
					// delay lostFocusCheck in case it was due to a selection click.
					JWic.controls.Combo._lostFocusClose = true;
					//window.setTimeout("JWic.controls.Combo.finishSelection('" + ctrlId + "', true);", 300);
				}
			}
			if (jQuery(this).attr("xEmptyInfoText")) {
				if (this.value == "") { // still empty
					jQuery(this).addClass("x-empty");
					this.value = jQuery(this).attr("xEmptyInfoText");
					jQuery(this).attr("xIsEmpty", "true");
				} else {
					jQuery(this).attr("xIsEmpty", "false");
				}
			}
		},
		/**
		 * Open the content window..
		 */
		openContentBox : function(controlId) {
			JWic.log("openContentBox");
			if (JWic.controls.Combo._activeComboContentBox) {
				if (JWic.controls.Combo._activeComboContentBox == controlId) {
					JWic.controls.Combo.closeActiveContentBox();
					return; // do not re-open it.
				} else {
					JWic.controls.Combo.closeActiveContentBox();
				}
			}

			if (JWic.controls.Combo._closeControlId == controlId) {
				var age = new Date().getTime() - JWic.controls.Combo._closeTime;
				if (age < 100) {
					return; //prevent re-open on immidiate re-focus event.
				}
			}
			
			var comboBox = jQuery("#" + JQryEscape(controlId)).get(0);
			var winId = "j-combo_contentBox";
			
			var boxWidth = jQuery(comboBox).width();
			var boxLoc = jQuery(comboBox).offset();
			var comboBoxWin = jQuery("#win_" + JQryEscape(controlId));
			if (!comboBoxWin.wasInit) {

				comboBoxWin.dialog({
					
					dialogClass : "j-combo-content",
					resizable: false,
					height: 200,
					width: boxWidth - 3,
					position : [boxLoc.left + 1, boxLoc.top + jQuery(comboBox).height()]
				});
				
				/*
				 * Haven't included resize and move event, when switching to jQuery.
				 * 
				 * onResize : JWic.controls.Combo.resizeHandler,
				 * onMove : JWic.controls.Combo.moveHandler
				 */
				
				comboBoxWin.wasInit = true;
				jQuery(".ui-dialog-titlebar").hide();
				
				if (comboBox.adjustIEBlockerId) {
					JWic.controls.Window.adjustIEBlockerToWin(jQuery("#"+JQryEscape(comboBox.adjustIEBlockerId)).get(0), jQuery("#"+JQryEscape(winId)).get(0));		
				}
				
				JWic.controls.Combo._openTime = new Date().getTime();
				JWic.controls.Combo._activeComboContentBox = controlId;
				jQuery(document).bind("click", JWic.controls.Combo.closeBoxDocumentHandler);
				if (comboBox.loadCompleted) {
					comboBox.contentRenderer.renderData(controlId);
				} else {
					if (comboBox.dataLoader) {
						comboBox.dataLoader.initializeData(controlId);
					} else {
						alert("DataLoader is not specified/found");
					}
				}
			}
			
		},
		/**
		 * Invoked when the box is resized.
		 */
		resizeHandler : function(e) {			
			if (JWic.controls.Combo._activeComboContentBox) {
				var comboBox = jQuery("#"+JQryEscape(JWic.controls.Combo._activeComboContentBox)).get(0);
				if (comboBox.adjustIEBlockerId) {
					JWic.controls.Window.adjustIEBlockerToWin(jQuery("#"+JQryEscape(comboBox.adjustIEBlockerId)).get(0), jQuery("#j-combo_contentBox").get(0));	
				}
			}
		},
		/**
		 * Invoked when the box is mvoed.
		 */
		moveHandler : function(e) {
			if (JWic.controls.Combo._activeComboContentBox) {
				var comboBox = jQuery("#"+JQryEscape(JWic.controls.Combo._activeComboContentBox)).get(0);
				if (comboBox.adjustIEBlockerId) {
					JWic.controls.Window.adjustIEBlockerToWin(jQuery("#"+JQryEscape(comboBox.adjustIEBlockerId)).get(0), jQuery("#j-combo_contentBox").get(0));	
				}
			}
		},
		
		/**
		 * Close active window.
		 */
		closeActiveContentBox : function() {
			if (JWic.controls.Combo._activeComboContentBox) {
				JWic.log("closing ActiveContentBox");
				var comboBox = jQuery("#"+JQryEscape(JWic.controls.Combo._activeComboContentBox)).get(0);
				var comboBoxWin = jQuery("#win_"+JQryEscape(JWic.controls.Combo._activeComboContentBox));
				comboBoxWin.dialog("close");
				if (comboBox.adjustIEBlockerId) {
					jQuery("#"+JQryEscape(comboBox.adjustIEBlockerId)).css("display","none");		
				}
				//comboBox.applyFilter = false; // clear filter
				//comboBox.dataLoader.prepareData(ctrlId);
				JWic.controls.Combo._closeTime = new Date().getTime();
				JWic.controls.Combo._closeControlId = JWic.controls.Combo._activeComboContentBox; 

				JWic.controls.Combo._activeComboContentBox = null;
				jQuery(document).unbind("click", JWic.controls.Combo.closeBoxDocumentHandler);
			}
		},
		
		/**
		 * Listens to click events on the document.
		 */
		closeBoxDocumentHandler : function(e) {
			if (JWic.controls.Combo._activeComboContentBox) {
				var tpl = jQuery(e.target).closest("#j-combo_contentBox");
				//var tpl = e.findElement("#j-combo_contentBox");
				if (!tpl) { // user clicked outside the content box -> close it.
					//JWic.log("Clicked outside of combo box");
					var age = new Date().getTime() - JWic.controls.Combo._openTime;
					if (age > 300) { // to avoid miss-clicks, ignore 300ms
						JWic.log("Closing ContentBox to do outside click..");
						JWic.controls.Combo.closeActiveContentBox();
					}
				} else {
					//JWic.log("Clicked inside of combo box.");
					JWic.controls.Combo._lostFocusClose = false;
				}
			}
		},
		
		/**
		 * Make a selection. Toggles selection if combo box is in multi select mode.
		 */
		selectElement: function(controlId, title, key, noSelection, keepBoxOpen) {
			var comboBox = jQuery("#"+JQryEscape(controlId)).get(0);
			if (comboBox) {
				comboBox.suggestedObject = null;
				jQuery(comboBox).removeClass("x-error");
				var changed = false;
				if (comboBox.multiSelect) {
					if (jQuery(comboBox.jComboField).attr("xIsEmpty") == "true") {
						comboBox.jComboField.value = "";
						comboBox.jComboKey.value = "";
					}
					if (JWic.controls.Combo.isSelected(comboBox, key)) {
						// remove selected
						comboBox.jComboField.value = JWic.util.removeElement(comboBox.jComboField.value, title);
						comboBox.jComboKey.value = JWic.util.removeElement(comboBox.jComboKey.value, key);
					} else {
						// add to selection;
						var sep = "";
						if (comboBox.jComboKey.value.length != 0) {
							sep = ";"
						}
						comboBox.jComboField.value += sep + title;
						comboBox.jComboKey.value += sep + key;
					}
					changed = true;
				} else {
					comboBox.jComboField.value = title;
					if (comboBox.jComboKey.value != key) {
						comboBox.jComboKey.value = key;
						changed = true;
					}
				}
				if (jQuery(comboBox.jComboField).attr("xEmptyInfoText")) {
					if (comboBox.jComboKey.value != "") {
						jQuery(comboBox.jComboField).attr("xIsEmpty", "false");
						jQuery(comboBox.jComboField).removeClass("x-empty");
					} else {
						jQuery(comboBox.jComboField).attr("xIsEmpty", "true");
						jQuery(comboBox.jComboField).addClass("x-empty");
						comboBox.jComboField.value = jQuery(comboBox.jComboField).attr("xEmptyInfoText");
					}
				}
				if (!noSelection) {					
					comboBox.jComboField.select();
				}
				if (!comboBox.multiSelect && !keepBoxOpen) {
					JWic.controls.Combo.closeActiveContentBox();
				}
				if (comboBox.changeNotification && changed) {
					JWic.fireAction(controlId, 'elementSelected', key);
				}
				return changed;
			}
			return false;
		},
		
		/**
		 * Checks if the specified key is selected.
		 */
		isSelected: function(combo, key) {
			var comboBox = combo;
			if (comboBox && comboBox.jComboKey.value != "") {
				var keys = comboBox.jComboKey.value.split(";");
				for (var a = 0; a < keys.length; a++) {
					if (keys[a] == key) {
						return true;
					}
				}
			}
			return false;
		},
		
		/**
		 * Loads the data from the control.
		 */
		BeanLoader : {
			_requestIndexCall : 0,
			initializeData : function(controlId) {
			JWic.log("BeanLoader.initializeData(..)");
			var comboBoxWin = jQuery("#win_" + JQryEscape(controlId));
				if (comboBoxWin) {
					comboBoxWin.text("Loading...");
					var comboBox = jQuery('#'+JQryEscape(controlId)).get(0);
					var param = {};
					param["action"] = "load";
					if (comboBox.applyFilter && comboBox.dataFilterValue) {
						param["filter"] = comboBox.dataFilterValue;
					}
					JWic.controls.Combo.BeanLoader._requestIndexCall++;
					var myIndex = JWic.controls.Combo.BeanLoader._requestIndexCall 
					JWic.resourceRequest(controlId, function(ajaxResponse) {
						try {
							JWic.log("request answer: " + myIndex);
							if (myIndex == JWic.controls.Combo.BeanLoader._requestIndexCall) {
								JWic.controls.Combo.BeanLoader._handleResponse(ajaxResponse);
							} else {
								JWic.log("Ignored AjaxResponse due to invalid request index."); // DEBUG
							}
						} catch (x) {
							alert(x);
						}
					}, param);
				}
			},
			
			/** 
			 * Invoked before rendering - used to apply filters, etc.. 
			 */
			prepareData : function(controlId) {
				JWic.log("BeanLoader.prepareData(..)");
				var comboBox = jQuery('#' + JQryEscape(controlId)).get(0);
				if (comboBox.dataStore) {
					comboBox.dataItems = new Array();
					if (comboBox.clientSideFilter) {
						jQuery(comboBox.dataStore).each(function(i,obj) {
							var title = obj.title;
							if (!comboBox.applyFilter || (comboBox.dataFilter && comboBox.dataFilter.match(comboBox, obj))) {
								comboBox.dataItems.push(obj);
							}
						});
					} else {
						comboBox.dataItems = comboBox.dataStore;
					}
				}
			},
			
			_handleResponse : function(ajaxResponse) {
				JWic.log("BeanLoader._handleResponse(..)");
				var response = jQuery.parseJSON(ajaxResponse.responseText);
				if (response.controlId && response.controlId == JWic.controls.Combo._activeComboContentBox) {
					var comboBoxWin = jQuery("#win_" + JQryEscape(response.controlId));
					if (comboBoxWin) {
						var comboBox = jQuery('#' + JQryEscape(response.controlId)).get(0);
						comboBox.dataStore = [];
						jQuery.each(response.data, function(key, value) {
							comboBox.dataStore.push(value);
						});
						JWic.controls.Combo.BeanLoader.prepareData(response.controlId);
						comboBox.loadCompleted = comboBox.cacheData; // only set load to complete if cacheData behavior is on
						comboBox.contentRenderer.renderData(response.controlId);
					}
				}
			}
		},
		
		ComboElementLabelProvider : {
			getLabel : function(obj) {
				return obj.title;
			}
		},
		/**
		 * Render IComboElement controls as a list.
		 */
		ComboElementListRenderer : {
			renderData : function(controlId) {
				JWic.log("ComboElementListRenderer.renderData(..)");
				var comboBox = jQuery('#'+JQryEscape(controlId)).get(0);
				var comboBoxWin = jQuery("#win_" + JQryEscape(controlId));
				if (comboBoxWin && controlId == JWic.controls.Combo._activeComboContentBox) {
					// in case the content is re-drawn, we remove any pre-existing listeners...
					jQuery(comboBoxWin).unbind("mouseover", JWic.controls.Combo.ComboElementListRenderer.mouseOverHandler)
					jQuery(comboBoxWin).unbind("mouseout", JWic.controls.Combo.ComboElementListRenderer.mouseOutHandler)
					var code = "";
					var idx = 0;
					var first = true;
					var currentKey = comboBox.jComboKey.value;
					comboBox.selectionIdx = null;
					jQuery(comboBox.dataItems).each(function(i,obj) {
						var content = comboBox.labelProvider.getLabel(obj);
						var extraClasses = "";
						if (comboBox.pickFirstFinding && first) {
							extraClasses += " selected";
							JWic.controls.Combo.searchSuggestion(comboBox, obj);
							comboBox.selectionIdx = 0;
						} else if (!comboBox.pickFirstFinding && currentKey == obj.key) {
							extraClasses += " selected";
							comboBox.selectionIdx = idx;
						}
						if (first) {
							first = false;
						}
						var imgSrc = comboBox.defaultImage;
						if (obj.image) {
							imgSrc = obj.image;
						}
						if (imgSrc) {
							content = imgSrc.imgTag + content; //"<IMG src=\"" + imgSrc + "\" border=\"0\" align=\"absmiddle\"/>" + content;
						}
						var action = "JWic.controls.Combo.ComboElementListRenderer.handleSelection('" + controlId + "', '" + obj.key + "');";
						if (comboBox.multiSelect) {
							code += "<input ";
							if (JWic.controls.Combo.isSelected(comboBox, obj.key)) {
								code += "checked";
							}
							code += " id='cbc_" + controlId + "." + idx + "' type=\"checkbox\" class=\"j-combo-chkbox\" onClick=\"" + action + "\">";
						}
						code += '<div comboElement="' + idx + '" onClick="' + action + 'return false;" class="j-combo_element ' + extraClasses + '">';
						code += content;
						code += '</div>';
						
						idx++;
					});
					comboBox.visibleCount = idx;
					if (first && comboBox.pickFirstFinding) { // no entry was found at all
						JWic.controls.Combo.searchSuggestion(comboBox, null);
					}
					jQuery(comboBoxWin).html(code);
					jQuery(comboBoxWin).bind("mouseover", JWic.controls.Combo.ComboElementListRenderer.mouseOverHandler);
					jQuery(comboBoxWin).bind("mouseout", JWic.controls.Combo.ComboElementListRenderer.mouseOutHandler);
				}
			},
			
			/**
			 * Update selection entry by index..
			 */
			updateSelection : function(ctrlId, newSelection) {
				var comboBox = jQuery("#"+ JQryEscape(ctrlId)).get(0);
				var comboBoxWin = jQuery("#win_" + JQryEscape(ctrlId));
				if (comboBoxWin && ctrlId == JWic.controls.Combo._activeComboContentBox) {
					// clear selection
					jQuery(comboBoxWin).find("div[comboElement].selected").each(function(i,obj) {
						jQuery(obj).removeClass("selected");
					});
		
					var height = jQuery(comboBoxWin).height();
					var boxScrollTop = win.getContent().scrollTop;
					jQuery(comboBoxWin).find("div[comboElement=" + newSelection + "]").each(function(i,obj) {
						obj=jQuery(obj);
						obj.addClass("selected");
						var top = obj.position().top;
						var elmHeight = obj.height();
						if (top < boxScrollTop) {
							comboBoxWin.scrollTop = top;
						} else if ((top + elmHeight) > (height + boxScrollTop)) {
							comboBoxWin.scrollTop = top - height + elmHeight;
						}
						JWic.log("viewPort: " + obj.position().top);
					});
				}				
			},
			
			/**
			 * Handle mouse over on content box.
			 */
			mouseOverHandler : function(e) {
				var elm = jQuery(e.target).closest("div[comboElement]");				
				if (elm) {
					elm.addClass("hover");
				}
			},
			/**
			 * Handle mouse out on content box.
			 */
			mouseOutHandler : function(e) {
				var elm = jQuery(e.target).closest("div[comboElement]");
				if (elm) {
					elm.removeClass("hover");
				}
				
			},
			/**
			 * Handle the selection by key.
			 */
			handleSelection : function(controlId, key) {
				// find the element by key
				var comboBox = jQuery("#"+JQryEscape(controlId)).get(0);
				var title = key;
				var isSelected = JWic.controls.Combo.isSelected(comboBox, key);
				for (var index = 0, len = comboBox.dataStore.length; index < len; ++index) {
					var item = comboBox.dataStore[index];
					if (item.key == key) {
						title = item.title;
						var cbc = jQuery("#cbc_" + JQryEscape(controlId) + "\\." + index).get(0);
						if (comboBox.multiSelect && cbc) {
							cbc.checked = !isSelected;
						}
						break;
					}
				}
				comboBox.suggestedElement = null;
				var keepComboOpen = comboBox.multiselect; 
				JWic.controls.Combo.selectElement(controlId, title, key, keepComboOpen);
				JWic.log("Combo: handleSelection(" + controlId + ", '" + key + "') -> title: " + title);
			}
		
		},
		
		/**
		 * Default data filter.
		 */
		StringDataFilter : {
			match : function(comboBox, object) {
				if (comboBox.dataFilterValue) {
					var value = comboBox.dataFilterValue.toLowerCase();
					var objTitle = jQuery.trim(object.title).toLowerCase();
					return objTitle.startsWith(value);
				}
				return true;
			}
		}


	},
	
	/**
	 * Button control.
	 */
	Button : {
		
		initialize : function(tblElm, btnElm) {
			if(tblElm.attr && "true" == tblElm.attr("_ctrlEnabled")) {
				tblElm.bind('mouseover', JWic.controls.Button.mouseOverHandler);
				tblElm.bind('mouseout', JWic.controls.Button.mouseOutHandler);
				tblElm.bind('click', JWic.controls.Button.clickHandler);
				btnElm.bind('click', JWic.controls.Button.clickHandler);
			}
		},
		
		destroy : function(tblElm, btnElm) {
			if(tblElm.attr && "true" == tblElm.attr("_ctrlEnabled")) {
				tblElm.unbind("mouseover", JWic.controls.Button.mouseOverHandler);
				tblElm.unbind("mouseout", JWic.controls.Button.mouseOutHandler);
				tblElm.unbind("click", JWic.controls.Button.clickHandler);
				btnElm.unbind("click", JWic.controls.Button.clickHandler);
			}
		},
		/**
		 * Invoked when the button is clicked.
		 */
		clickHandler : function(e) {
			e.stopPropagation();
			var elm = jQuery(e.target);
			while (!elm.attr('id') || elm.attr('id').indexOf('tbl_') != 0) {
				elm = jQuery(elm).parent();
			}
			var ctrlId = elm.attr('id').substring(4);
			var msg = elm.attr("_confirmMsg");
			if (msg && msg != "") {
				if (!confirm(msg)) {
					return false;
				}
			}
			JWic.fireAction(ctrlId, 'click', '');
		},
		/**
		 * Invoked when the focus is received.
		 */
		mouseOverHandler : function(e) {
			jQuery(this).addClass("j-hover");
		},
		/**
		 * Invoked when the focus is lost.
		 */
		mouseOutHandler : function(e) {
			jQuery(this).removeClass("j-hover");
		}
	},
	
	/**
	 * Tree Control Scripts.
	 */
	Tree : {
		_requestIndexCall : 0,
		
		/**
		 * Initialize a new control.
		 */
		initialize : function(elm) {
			var tree = jQuery("#" + JQryEscape(elm)).get(0);
			JWic.controls.Tree.loadData(tree, "");
		},
		
		/**
		 * Clean up..
		 */
		destroy : function(elm) {
			
		},
		
		loadData : function(tr, parent) {
			var tree = tr;

			var param = {};
			param["action"] = "load";
			JWic.controls.Tree._requestIndexCall++;
			var myIndex = JWic.controls.Tree._requestIndexCall 
			JWic.resourceRequest(tree.id, function(ajaxResponse) {
				try {
					JWic.log("request answer: " + myIndex);
					if (myIndex == JWic.controls.Tree._requestIndexCall) {
						JWic.controls.Tree._handleResponse(ajaxResponse, parent);
					} else {
						JWic.log("Ignored AjaxResponse due to invalid request index."); // DEBUG
					}
				} catch (x) {
					alert(x);
				}
			}, param);
		},
		
		_handleResponse : function(ajaxResponse, parent) {
		
			var response = jQuery.parseJSON(ajaxResponse.responseText);
			if (response.controlId) {
				var tree = jQuery("#"  + JQryEscape(response.controlId)).get(0);
				tree.dataStore = []; //$A(response.data);
				jQuery.each(response.data, function(key, value) {
					tree.dataStore.push(value);
				});
			}
		
			JWic.controls.Tree._renderTreeNodes(tree, parent);
		},
		
		_renderTreeNodes : function(tree, parent) {
		
			var code = "";
			if (tree.dataStore) {
				for (var idx = 0, len = tree.dataStore.length; idx < len; ++idx) {
					var elm = tree.dataStore[idx];
					code += "<div class=\"" + tree.className + "-elm\">"
					var nodeType;
					if (elm.children) {
						if (JWic.controls.Tree.isExpanded(tree, elm.key)) {
							nodeType = "expanded";
						} else {
							nodeType = "collapsed";
						}
					} else {
						nodeType = "empty";
					}
					code += "<div class=\"" + tree.className + "-nodeBtn j-tree-" + nodeType + "\"></div>"
					code += elm.title;
					code += "</div>"
				}
			}
			jQuery(tree).html(code);
			
		},
		
		isExpanded : function(tree, key) {
			return false;
		}
		
	}

}

