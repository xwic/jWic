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
			//Event.observe(inpElm, "focus", JWic.controls.InputBoxControl.focusHandler);
			//Event.observe(inpElm, "blur", JWic.controls.InputBoxControl.lostFocusHandler);
			
			if (inpElm.attr("xListenKeyCode") != 0) {
				inpElm.bind("keyup", JWic.controls.InputBoxControl.keyHandler);
				//Event.observe(inpElm, "keyup", JWic.controls.InputBoxControl.keyHandler);
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
			
			//Event.stopObserving(inpElm, "focus", JWic.controls.InputBoxControl.focusHandler);
			//Event.stopObserving(inpElm, "blur", JWic.controls.InputBoxControl.lostFocusHandler);
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
	
	/**
	 * Window control script extensions.
	 */
	Window : {
		
		
		_windows : new Hash(),
		
		
		getWindow: function(elmId){
			var win = JWic.controls.Window._windows[elmId];
			if(!win){
				win = jQuery('#'+JQryEscape(elmId)).dialog();
				win.dialog('destroy').remove();
				win.dialog();				
				jQuery('#'+JQryEscape(elmId)).parent().appendTo(jQuery("#jwicform"));
				JWic.controls.Window._windows[elmId] = win;
			}
			return win;
		},
		
		initialize : function(elmId){
		},
		
		updateHandler : function(controlId) {
			var win = jQuery('#'+JQryEscape(controlId));
			if (win) {
				//var size = win.getSize();
				var field = jQuery("fld_" + JQryEscape(controlId));
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
			jQuery("win_" + JQryEscape(controlId)+ "_blocker");
			var blocker = jQuery("win_" + JQryEscape(controlId)+ "_blocker");
			var source =jQuery("win_" + JQryEscape(controlId)); // get the window
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
			var comboBox = $(controlId);
			var iconElm = $(controlId + "_open");
			
			comboBox.jComboField = inpElm;
			comboBox.jComboKey = $("fld_" + controlId + ".key");
			comboBox.dataFilter = JWic.controls.Combo.StringDataFilter;
			comboBox.keyDelayTime = 100;
			Event.observe(inpElm, "focus", JWic.controls.Combo.focusHandler);
			Event.observe(inpElm, "blur", JWic.controls.Combo.lostFocusHandler);
			Event.observe(inpElm, "click", JWic.controls.Combo.textClickHandler);
			Event.observe(inpElm, "keyup", JWic.controls.Combo.textKeyPressedHandler);
			
			// adjust sizes
			var totalWidth = comboBox.getLayout().get('width');
			if (totalWidth == 0) { // seems like the control is not yet displayed - take from style.
				var s = comboBox.getStyle("width");
				totalWidth = parseInt(s);
			}
			var iconWidth = 0;
			if (iconElm) {
				iconWidth = iconElm.getLayout().get('width');
				if (iconWidth == 0) { // assume default width
					iconWidth = 20;
				}
			}
			var inpWidth = iconElm ? totalWidth - iconWidth - 7 : totalWidth - 3;
			if (Prototype.Browser.IE) {
				inpWidth -= 4;
			}
			inpElm.style.width = inpWidth + "px";
			
			if (inpElm.readAttribute("xEmptyInfoText")) {
				if(inpElm.readAttribute("xIsEmpty") == "true" && 
					(inpElm.value == inpElm.readAttribute("xEmptyInfoText") || inpElm.value == "")) {
					inpElm.addClassName("x-empty");
					inpElm.value = inpElm.readAttribute("xEmptyInfoText");
				} else {
					inpElm.writeAttribute("xIsEmpty", "false");
					inpElm.removeClassName("x-empty");
				}
			}
			
			// override the getValue() method to "fix" the serialization
			inpElm.getValue = function() {
				if (this.readAttribute("xEmptyInfoText") && this.readAttribute("xIsEmpty") == "true") {
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
			Event.stopObserving(inpElm, "focus", JWic.controls.InputBoxControl.focusHandler);
			Event.stopObserving(inpElm, "blur", JWic.controls.InputBoxControl.lostFocusHandler);
			Event.stopObserving(inpElm, "click", JWic.controls.Combo.textClickHandler);
			Event.stopObserving(inpElm, "keyup", JWic.controls.Combo.textKeyPressedHandler);
			var comboBox = $(controlId);
			var winId = controlId + "_contentBox";
			var win = Windows.getWindow(winId);
			if (win) {
				win.destroy();
			}
		},
		
		/**
		 * Invoked on KeyUp to handle user input.
		 */
		textKeyPressedHandler : function(e) {
			var ctrlId = this.readAttribute("j-controlId");
			if (ctrlId) {
				JWic.log("key pressed: " + e.keyCode + " --");
				var comboBox = $(ctrlId);
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
				var comboBox = $(ctrlId);

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

		selectRow : function(ctrlId, newSelection) {
			JWic.log("selectRow: " + newSelection);
			var comboBox = $(ctrlId);
			if (newSelection >= 0 && newSelection < comboBox.dataItems.length) {
				var newItem = comboBox.dataItems[newSelection];
				comboBox.contentRenderer.updateSelection(ctrlId, newSelection);
				comboBox.selectionIdx = newSelection;
				JWic.controls.Combo.selectElement(ctrlId, newItem.title, newItem.key, false, true);
			}
		},
		
		finishSelection : function (ctrlId, noSelection) {
			JWic.log("finished Selection");
			var comboBox = $(ctrlId);
			var fld = comboBox.jComboField;
			var changed = false;
			if (comboBox && comboBox.suggestedObject) {

				changed = JWic.controls.Combo.selectElement(ctrlId, comboBox.suggestedObject.title, comboBox.suggestedObject.key, noSelection);
				comboBox.suggestedObject = null;
				
			} else if ((fld.value == "" || (fld.readAttribute("xEmptyInfoText") != null && fld.readAttribute("xIsEmpty") == "true")) && comboBox.jComboKey.value != "") { // a key is still selected but the entry was deleted
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
					comboElm.addClassName("x-error");
					comboElm.jComboKey.value = "";
				}
			} else {
				JWic.log("SearchSuggestion : obj.key=" + obj.key);
				comboElm.removeClassName("x-error");
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
			var ctrlId = this.readAttribute("j-controlId");
			if (ctrlId) {
				var box = $(ctrlId);
				if (box && box.openContentOnTextFocus && ctrlId != JWic.controls.Combo._activeComboContentBox) {
					JWic.controls.Combo.openContentBox(ctrlId);
				}
			}
		},
		
		/**
		 * Invoked when the focus is received.
		 */
		focusHandler : function(e) {
			var ctrlId = this.readAttribute("j-controlId");
			if (ctrlId) {
				JWic.log("focusHandler: received Focus");
				var box = $(ctrlId);
				if (box) {
					box.addClassName("x-focus");
					if (box.openContentOnTextFocus && ctrlId != JWic.controls.Combo._activeComboContentBox) {
						JWic.controls.Combo.openContentBox(ctrlId);
					}
					if (box.selectTextOnFocus && this.value.length != 0) {
						this.select();
					}
						
				}
			}
			if (this.readAttribute("xEmptyInfoText")) {
				if (this.readAttribute("xIsEmpty") == "true") {
					this.value = "";
					this.removeClassName("x-empty");
				} 
			}

		},
		/**
		 * Invoked when the focus is lost.
		 */
		lostFocusHandler : function() {
			JWic.log("Combo.lostFocusHandler");
			var ctrlId = this.readAttribute("j-controlId");
			if (ctrlId) {
				var box = $(ctrlId);
				if (box) {
					box.removeClassName("x-focus");
					box.applyFilter = false;
					// delay lostFocusCheck in case it was due to a selection click.
					JWic.controls.Combo._lostFocusClose = true;
					window.setTimeout("JWic.controls.Combo.finishSelection('" + ctrlId + "', true);", 300);
				}
			}
			if (this.readAttribute("xEmptyInfoText")) {
				if (this.value == "") { // still empty
					this.addClassName("x-empty");
					this.value = this.readAttribute("xEmptyInfoText");
					this.writeAttribute("xIsEmpty", "true");
				} else {
					this.writeAttribute("xIsEmpty", "false");
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
			
			var comboBox = $(controlId);
			var winId = "j-combo_contentBox";
			var win = Windows.getWindow(winId);
			var boxWidth = comboBox.getWidth();
			var boxLoc = comboBox.cumulativeOffset();
			if (!win) {
				win = new Window({
					className : "j-combo-content",
					closable : false,
					draggable : false,
					minimizable : false,
					maximizable : false,
					width: boxWidth - 3,
					showEffect : Element.show,
					height: 200,
					title : "",
					left: boxLoc.left + 1,
					top: boxLoc.top + comboBox.getHeight(),
					id : winId,
					parent : $("jwicform"),
					onResize : JWic.controls.Combo.resizeHandler,
					onMove : JWic.controls.Combo.moveHandler
				});
				win.show();
				
				if (comboBox.adjustIEBlockerId) {
					JWic.controls.Window.adjustIEBlockerToWin($(comboBox.adjustIEBlockerId), $(winId));		
				}
				
				JWic.controls.Combo._openTime = new Date().getTime();
				JWic.controls.Combo._activeComboContentBox = controlId;
				Event.observe(document, "click", JWic.controls.Combo.closeBoxDocumentHandler);
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
				var comboBox = $(JWic.controls.Combo._activeComboContentBox);
				if (comboBox.adjustIEBlockerId) {
					JWic.controls.Window.adjustIEBlockerToWin($(comboBox.adjustIEBlockerId), $("j-combo_contentBox"));	
				}
			}
		},
		/**
		 * Invoked when the box is mvoed.
		 */
		moveHandler : function(e) {
			if (JWic.controls.Combo._activeComboContentBox) {
				var comboBox = $(JWic.controls.Combo._activeComboContentBox);
				if (comboBox.adjustIEBlockerId) {
					JWic.controls.Window.adjustIEBlockerToWin($(comboBox.adjustIEBlockerId), $("j-combo_contentBox"));	
				}
			}
		},
		
		/**
		 * Close active window.
		 */
		closeActiveContentBox : function() {
			if (JWic.controls.Combo._activeComboContentBox) {
				JWic.log("closing ActiveContentBox");
				var comboBox = $(JWic.controls.Combo._activeComboContentBox);
				var winId = "j-combo_contentBox";
				var win = Windows.getWindow(winId);
				if (win) {
					win.destroy();
				}
				if (comboBox.adjustIEBlockerId) {
					$(comboBox.adjustIEBlockerId).setStyle({ display : 'none' });		
				}
				//comboBox.applyFilter = false; // clear filter
				//comboBox.dataLoader.prepareData(ctrlId);
				JWic.controls.Combo._closeTime = new Date().getTime();
				JWic.controls.Combo._closeControlId = JWic.controls.Combo._activeComboContentBox; 

				JWic.controls.Combo._activeComboContentBox = null;
				Event.stopObserving(document, "click", JWic.controls.Combo.closeBoxDocumentHandler);
			}
		},
		
		/**
		 * Listens to click events on the document.
		 */
		closeBoxDocumentHandler : function(e) {
			if (JWic.controls.Combo._activeComboContentBox) {
				var tpl = e.findElement("#j-combo_contentBox");
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
			var comboBox = $(controlId);
			if (comboBox) {
				comboBox.suggestedObject = null;
				comboBox.removeClassName("x-error");
				var changed = false;
				if (comboBox.multiSelect) {
					if (comboBox.jComboField.readAttribute("xIsEmpty") == "true") {
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
				if (comboBox.jComboField.readAttribute("xEmptyInfoText")) {
					if (comboBox.jComboKey.value != "") {
						comboBox.jComboField.writeAttribute("xIsEmpty", "false");
						comboBox.jComboField.removeClassName("x-empty");
					} else {
						comboBox.jComboField.writeAttribute("xIsEmpty", "true");
						comboBox.jComboField.addClassName("x-empty");
						comboBox.jComboField.value = comboBox.jComboField.readAttribute("xEmptyInfoText");
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
			var comboBox = $(combo);
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
				var winId = "j-combo_contentBox";
				var win = Windows.getWindow(winId);
				if (win) {
					win.getContent().update("Loading...");
					var comboBox = $(controlId);
					var param = new Hash();
					param.set("action", "load");
					if (comboBox.applyFilter && comboBox.dataFilterValue) {
						param.set("filter", comboBox.dataFilterValue);
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
				var comboBox = $(controlId);
				if (comboBox.dataStore) {
					comboBox.dataItems = new Array();
					if (comboBox.clientSideFilter) {
						comboBox.dataStore.each(function(obj) {
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
				var response = ajaxResponse.responseText.evalJSON(true);
				if (response.controlId && response.controlId == JWic.controls.Combo._activeComboContentBox) {
					var winId = "j-combo_contentBox";
					var win = Windows.getWindow(winId);
					if (win) {
						var comboBox = $(response.controlId);
						comboBox.dataStore = $A(response.data);
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
				var comboBox = $(controlId);
				var winId = "j-combo_contentBox";
				var win = Windows.getWindow(winId);
				if (win && controlId == JWic.controls.Combo._activeComboContentBox) {
					// in case the content is re-drawn, we remove any pre-existing listeners...
					Event.stopObserving(win.getContent(), "mouseover", JWic.controls.Combo.ComboElementListRenderer.mouseOverHandler);
					Event.stopObserving(win.getContent(), "mouseout", JWic.controls.Combo.ComboElementListRenderer.mouseOutHandler);
					var code = "";
					var idx = 0;
					var first = true;
					var currentKey = comboBox.jComboKey.value;
					comboBox.selectionIdx = null;
					comboBox.dataItems.each(function(obj) {
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
					win.getContent().update(code);
					Event.observe(win.getContent(), "mouseover", JWic.controls.Combo.ComboElementListRenderer.mouseOverHandler);
					Event.observe(win.getContent(), "mouseout", JWic.controls.Combo.ComboElementListRenderer.mouseOutHandler);
				}
			},
			
			/**
			 * Update selection entry by index..
			 */
			updateSelection : function(ctrlId, newSelection) {
				var comboBox = $(ctrlId);
				var winId = "j-combo_contentBox";
				var win = Windows.getWindow(winId);
				if (win && ctrlId == JWic.controls.Combo._activeComboContentBox) {
					// clear selection
					win.getContent().select("div[comboElement].selected").each(function(obj) {
						obj.removeClassName("selected");
					});
					var boxSize = win.getContent().getDimensions();
					var boxScrollTop = win.getContent().scrollTop;
					win.getContent().select("div[comboElement=" + newSelection + "]").each(function(obj) {
						obj.addClassName("selected");
						var top = obj.positionedOffset().top;
						var elmHeight = obj.getDimensions().height;
						if (top < boxScrollTop) {
							win.getContent().scrollTop = top;
						} else if ((top + elmHeight) > (boxSize.height + boxScrollTop)) {
							win.getContent().scrollTop = top - boxSize.height + elmHeight;
						}
						JWic.log("viewPort: " + obj.positionedOffset().top);
					});
				}				
			},
			
			/**
			 * Handle mouse over on content box.
			 */
			mouseOverHandler : function(e) {
				var elm = e.findElement("div[comboElement]");
				if (elm) {
					elm.addClassName("hover");
				}
			},
			/**
			 * Handle mouse out on content box.
			 */
			mouseOutHandler : function(e) {
				var elm = e.findElement("div[comboElement]");
				if (elm) {
					elm.removeClassName("hover");
				}
				
			},
			/**
			 * Handle the selection by key.
			 */
			handleSelection : function(controlId, key) {
				// find the element by key
				var comboBox = $(controlId);
				var title = key;
				var isSelected = JWic.controls.Combo.isSelected(comboBox, key);
				for (var index = 0, len = comboBox.dataStore.length; index < len; ++index) {
					var item = comboBox.dataStore[index];
					if (item.key == key) {
						title = item.title;
						var cbc = $("cbc_" + controlId + "." + index);
						if (comboBox.multiSelect && cbc) {
							cbc.checked = !isSelected;
						}
						break;
					}
				}
				comboBox.suggestedElement = null;
				JWic.controls.Combo.selectElement(controlId, title, key);
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
					var objTitle = object.title.strip().toLowerCase()
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
			if("true" == tblElm.attr("_ctrlEnabled")) {
				 //$(document).bind("mousemove scroll click focus blur keypress", doStuff);
				tblElm.bind('mouseover', JWic.controls.Button.mouseOverHandler);
				tblElm.bind('mouseout', JWic.controls.Button.mouseOutHandler);
				tblElm.bind('click', JWic.controls.Button.clickHandler);
				btnElm.bind('click', JWic.controls.Button.clickHandler);
				
//				Event.observe(tblElm, "mouseover", JWic.controls.Button.mouseOverHandler);
//				Event.observe(tblElm, "mouseout", JWic.controls.Button.mouseOutHandler);
//				Event.observe(tblElm, "click", JWic.controls.Button.clickHandler);
//				Event.observe(btnElm, "click", JWic.controls.Button.clickHandler);
			}
		},
		
		destroy : function(tblElm, btnElm) {
			if("true" == tblElm.attr("_ctrlEnabled")) {
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
			jQuery(e.target).addClass("j-hover");
		},
		/**
		 * Invoked when the focus is lost.
		 */
		mouseOutHandler : function(e) {
			jQuery(e.target).removeClass("j-hover");
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
			var tree = $(elm);
			JWic.controls.Tree.loadData(tree, "");
		},
		
		/**
		 * Clean up..
		 */
		destroy : function(elm) {
			
		},
		
		loadData : function(tr, parent) {
			var tree = $(tr);

			var param = new Hash();
			param.set("action", "load");
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
		
			var response = ajaxResponse.responseText.evalJSON(true);
			if (response.controlId) {
				var tree = $(response.controlId);
				tree.dataStore = $A(response.data);
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
			tree.update(code);
			
		},
		
		isExpanded : function(tree, key) {
			return false;
		}
		
	}

}

/** 
 * The following code patches a problem with the PWC library where child elements are not
 * properly detected - and re-enabled.
 */
WindowUtilities._showSelect = function (id) {
	if (Prototype.Browser.IE) {
		
		$$('select').each(function(element) {
		  // Why?? Ask IE
		  var myPath = "";
		  var isChild = false;
		  if (id == null || id == "") {
			  isChild = true;
		  } else {
			  var x = element;
			  while (!(x == null || x == document.body)) {
				  if (x.id && x.id == id) {
					  isChild = true;
					  break;
				  }
				  x = x.parentNode;
			  }
		  }
		  if (isChild) {
			  if (WindowUtilities.isDefined(element.oldVisibility)) {
			  try {
			    element.style.visibility = element.oldVisibility;
			  } catch(e) {
			    element.style.visibility = "visible";
			  }
			  element.oldVisibility = null;
	        } else {
			  if (element.style.visibility) {
				  element.style.visibility = "visible";
	          }
	        }
		  }
   });
	}	
};
