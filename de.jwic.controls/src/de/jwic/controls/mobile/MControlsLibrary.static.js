/*
 * This library contains all the static functions required by the controls. Instead of dynamically loading
 * a resource for each control when it is used, all functions are bundled in this file. This makes the loading
 * faster, but requires the file to be included in the include.header.vtl file in the respective directory
 * like the below:
 *
 * <script src="$contextPath/cp/de/jwic/mobile/controls/MControlsLibrary.static.js"></script>
 */

JWic.mobile = {

	/**
	 * Button helper methods.
	 */
	Button : {
		initialize : function(control, options) {
			control.data('id', options.controlID);
			if (options.enabled) {
				var clickHandler = function ButtonClickHandler() {
					JWic.fireAction(jQuery(this).data('id'), 'click');
				};
				control.on('click', clickHandler);
			}
			control.button({
				disabled : !options.enabled,
				corners : options.corners,
				mini : options.mini,
				shadow : options.shadow,
				inline : options.inline,
				iconpos : options.iconpos,
				theme : options.theme,
				wrapperClass : options.wrapperClass,
				icon : options.icon
			});
		},

		destroy : function(control) {
			control.destroy();
		}
	},

	/**
	 * CheckBox helper methods
	 */
	CheckBox : {
		initialize : function(control, options, fieldId) {
			var clickHandler = function(evnt) {
				var cb = jQuery(this);
				var field = JWic.$("chkVal_" + cb.data('fieldId'));
				if (field.length > 0) {
					field[0].value = cb[0].checked ? "1" : "";
					if (cb.data('options').changeNotification) {
						JWic.fireAction(cb.data('id'), 'clicked', '');
					}
				} else {
					JWic
							.log("WARN: Cannot find hidden field for checkbox value 'chkVal_"
									+ cb.data('fieldId'));
				}
				return true;
			};
			control.data('id', options.controlID);
			control.data('fieldId', fieldId);
			control.data('options', options);
			control.checkboxradio({
				disabled : !options.enabled,
				mini : options.mini,
				iconpos : options.iconpos,
				wrapperClass : options.wrapperClass
			});
			control.on('change', clickHandler);

		}
	},
	/**
	 * CheckBox helper methods
	 */
	RadioButton : {
		initialize : function(control, options) {
			control.data('id', options.controlID);
			control.checkboxradio({
				disabled : !options.enabled,
				mini : options.mini,
				iconpos : options.iconpos,
				wrapperClass : options.wrapperClass
			});
		}
	},
	/**
	 * InputBox helper methods
	 */
	InputBox : {
		initialize : function(control, options) {
			control.textinput({
				autogrow : options.autogrow,
				clearBtn : options.clearBtn,
				clearBtnText : options.clearBtnText,
				corners : options.corners,
				keyupTimeoutBuffer : options.keyupTimeoutBuffer,
				mini : options.mini,
				preventFocusZoom : options.preventFocusZoom,
				theme : options.theme,
				wrapperClass : options.wrapperClass
			});
		},

		destroy : function(control) {
			control.destroy();
		}
	},
	/**
	 * FlipSwitch helper methods
	 */
	FlipSwitch : {
		initialize : function(control, options, fieldId) {
			var clickHandler = function(evnt) {
				var cb = jQuery(this);
				var field = JWic.$("chkVal_" + cb.data('fieldId'));
				if (field.length > 0) {
					field[0].value = cb[0].checked ? "1" : "";
					if (cb.data('options').changeNotification) {
						JWic.fireAction(cb.data('id'), 'clicked', '');
					}
				} else {
					JWic
							.log("WARN: Cannot find hidden field for checkbox value 'chkVal_"
									+ cb.data('fieldId'));
				}
				return true;
			};
			control.data('id', options.controlID);
			control.data('fieldId', fieldId);
			control.data('options', options);
			control.flipswitch({
				disabled : !options.enabled
			});
			control.on('change', clickHandler);
		}
	},
	/**
	 * Combo helper methods.
	 */
	Combo : {
		initialize : function(control, options) {
			var comboBox = document.getElementById(options.controlID);

			control.filterable({
				disabled : !options.enabled,
				children : options.children,
				elements : options.elements,
				defaults : options.defaults,
				enhanced : options.enhanced,
				filter : options.filter,
				filterReveal : options.filterReveal,
				input : options.input,
				filterPlaceholder : options.filterPlaceholder,
				autodividers : options.autodividers,
				hideDividers : options.hideDividers,
				inset : options.inset,
				splitIcon : options.splitIcon,
				icon : options.icon,
				dividerTheme : options.dividerTheme,
				filterTheme : options.filterTheme,
				splitTheme : options.splitTheme,
				theme : options.theme
			});

			var filterHandler = function(e, data) {
				var $ul = jQuery(this), $input = data.input, value = $input
						.val(), _requestIndexCall = 0;
				$ul.html("");
				if (value && value.length >= comboBox.minSearchKeyLength) {
					$ul
							.html("<li><div class='ui-loader'><span class='ui-icon ui-icon-loading'></span></div></li>");
					$ul.filterable("refresh");
					var param = {};
					param["action"] = "load";
					param["filter"] = value;
					_requestIndexCall++;
					var myIndex = _requestIndexCall;
					JWic
							.resourceRequest(
									options.controlID,
									function(ajaxResponse) {
										try {
											if (myIndex == _requestIndexCall) {
												JWic.mobile.Combo
														._handleResponse(
																ajaxResponse,
																$ul);
											} else {
												JWic
														.log("Ignored AjaxResponse due to invalid request index.");
											}
										} catch (x) {
											alert(x);
										}
									}, param);
				}
			};

			var liClickHandler = function() {
				var clickedItem = this;
				var items = document.getElementsByTagName("label");
				if (!clickedItem.classList.contains("ui-checkbox-on")) {
					for (i = 0; i < items.length; i++) {
						if (items[i].classList.contains("ui-checkbox-on")) {
							items[i].classList.remove("ui-checkbox-on");
							items[i].classList.add("ui-checkbox-off");
							break;
						}
					}
					clickedItem.classList.remove("ui-checkbox-off");
					clickedItem.classList.add("ui-checkbox-on");
				} else {
					clickedItem.classList.remove("ui-checkbox-on");
					clickedItem.classList.add("ui-checkbox-off");
				}
			};

			if (!comboBox.clientSideFilter) {
				control.on("filterablebeforefilter", filterHandler);
				control.on("click", "LABEL", liClickHandler);
			}
			;

		},
		_handleResponse : function(ajaxResponse, $ul) {
			var html = "";
			var response = jQuery.parseJSON(ajaxResponse.responseText);
			var size = response.data.length;
			var comboBox = document.getElementById(response.controlId);
			comboBox.dataStore = [];
			html += "<div class=\"ui-controlgroup-controls\">";
			jQuery
					.each(
							response.data,
							function(i, val) {
								comboBox.dataStore.push(val);
								if (i == size)
									html += "<div class=\"ui-checkbox\">"
											+ "<label for=\""
											+ val.title
											+ "\" class=\"ui-btn ui-corner-all ui-btn-inherit ui-btn-icon-left ui-checkbox-off ui-last-child\">"
											+ val.title + "</label>"
											+ "<input type=\"checkbox\" id=\""
											+ val.title + "\"></div>";
								else if (i == 0)
									html += "<div class=\"ui-checkbox\">"
											+ "<label for=\""
											+ val.title
											+ "\" class=\"ui-btn ui-corner-all ui-btn-inherit ui-btn-icon-left ui-checkbox-off\">"
											+ val.title + "</label>"
											+ "<input type=\"checkbox\" id=\""
											+ val.title + "\"></div>";
								else
									html += "<div class=\"ui-checkbox\">"
											+ "<label for=\""
											+ val.title
											+ "\" class=\"ui-btn ui-corner-all ui-btn-inherit ui-btn-icon-left ui-checkbox-off ui-first-child\">"
											+ val.title + "</label>"
											+ "<input type=\"checkbox\" id=\""
											+ val.title + "\"></div>";
							});
			html += "</div>";
			$ul.html(html);
			$ul.trigger("updatelayout");
		},
		/**
		 * Make a selection. Toggles selection if combo box is in multi select
		 * mode.
		 */
		selectElement: function(controlId, title, key, isSelected) {
			var comboBox = document.getElementById(controlId);
			
			if (comboBox) {
				comboBox.suggestedObject = null;
				var changed = false;
				
				if (comboBox.multiSelect) {
					if (jQuery(comboBox.jComboField).val().length <1) {
						jQuery(comboBox.jComboField).val("");
						jQuery(comboBox.jComboKey).val("");
					}
					if (!isSelected) {
						// add to selection;;
						var sep = "";
						if (jQuery(comboBox.jComboKey).val().length > 0) {
							sep = ";"
						}
						jQuery(comboBox.jComboField).val(jQuery(comboBox.jComboField).val() + sep + title);
						jQuery(comboBox.jComboKey).val(jQuery(comboBox.jComboKey).val() + sep + key);
					} else {
						// remove selected
						jQuery(comboBox.jComboField).val(JWic.util.removeElement(jQuery(comboBox.jComboField).val(), title));
						jQuery(comboBox.jComboKey).val(JWic.util.removeElement(jQuery(comboBox.jComboKey).val(), key));
					}
					changed = true;
				} else {
					comboBox.jComboField.value = title;
					if (comboBox.jComboKey.value != key) {
						comboBox.jComboKey.value = key;
						changed = true;
					}
				}
				
				if (comboBox.changeNotification && changed) {
					JWic.fireAction(controlId, 'elementSelected', key);
				}
				return changed;
			}
			return false;
		},
		/**
		 * Handle the selection by key.
		 */
		handleSelection : function(controlId, fldKeyId) {
			var isSelected = true;
			var comboBox = document.getElementById(controlId);
			var fldKey = document.getElementById(fldKeyId);
			var keyArray = fldKey.value.split(';').filter(function(el) {return el.length != 0});
			var key = "";
			var title = "";
			
			if (comboBox.multiSelect) {
				if (jQuery(comboBox).last().val()) {
					if (keyArray.length > jQuery(comboBox).last().val().length) {
						//item has been deselected
						for (var i = 0; i < keyArray.length; i++) {
							var foundDeselected = true;
							for (var j = 0; j < jQuery(comboBox).last().val().length; j++) {
								var jqElm =  jQuery(comboBox).last().val()[j];
								if(keyArray[i] == jqElm) {
									foundDeselected = false;
									break;
								}
							}
							if (foundDeselected) {
								key = keyArray[i];
								break;
							}
						}
					} else if (keyArray.length < jQuery(comboBox).last().val().length) {
						//item has been selected
						isSelected = false;
						for (var i = 0; i < jQuery(comboBox).last().val().length; i++) {
							var foundSelected = true;
							var jqElm =  jQuery(comboBox).last().val()[i];
							for (var j = 0; j < keyArray.length; j++) {
								if(keyArray[j] == jqElm) {
									foundSelected = false;
									break;
								}
							}
							if (foundSelected) {
								key = jqElm;
								break;
							}
						}
					}
				} else {
					key = keyArray[0];
				}
			} else {
				//it is not multiselect, item has been selected
				isSelected = false;
				key = jQuery(comboBox).last().val();
			}
			for (var index = 0, len = comboBox.dataStore.length; index < len; ++index) {
				var item = comboBox.dataStore[index];
				if (item.value == key) {
					title = item.text;
					break;
				}
			}
			JWic.mobile.Combo.selectElement(controlId, title, key, isSelected);
			JWic.log("Combo: handleSelection(" + controlId + ", '" + key + "') -> title: " + title);
			
		},
		destroy : function(control) {
			control.destroy();
		}
	},
	/**
	 * TabStrip helper methods.
	 */
	TabStrip : {
		internalActivate : false,
		selectedTabLink : null,

		initialize : function(tabStrip, options, activeIndex) {
			tabStrip.tabs({
				beforeActivate : JWic.mobile.TabStrip.activateHandler,
				active : activeIndex,
				collapsible : options.collapsible,
				hide : options.hide,
				show : options.show,
				heightStyle : options.heightStyle,
				counter : options.counter
			});
			tabStrip[0].children[0].firstElementChild.children[activeIndex].children[0].classList
					.add("ui-btn-active");
			selectedTabLink = tabStrip[0].children[0].firstElementChild.children[activeIndex].children[0];
		},

		activateHandler : function(event, ui) {
			if (JWic.mobile.TabStrip.internalActivate) {
				return;
			}
			if (ui.newPanel) {
				var tabStripId = ui.newPanel.attr("jwicTabStripId"), tabName = ui.newPanel
						.attr("jwicTabName"), oldTabName = ui.oldPanel
						.attr("jwicTabName"), oldH = ui.oldPanel.height();

				// find index of new panel
				var widget = JWic.$(tabStripId).tabs("widget");
				var newPanelIdx = -1;
				var tabs = widget.find("div.ui-tabs-panel");
				var count = 0;
				for (var i = 0; i < tabs.length; i++) {
					if (jQuery(tabs[i]).attr("jwicTabStripId") == tabStripId) {
						if (jQuery(tabs[i]).attr("jwicTabName") == tabName) {
							newPanelIdx = count;
							break;
						}
						count++;
					}
				}

				if (selectedTabLink) {
					selectedTabLink.classList.remove("ui-btn-active");
					selectedTab = null;
				}

				JWic.fireAction(tabStripId, "activateTab", tabName, function() {
					ui.oldPanel.html("<span id=\"ctrl_" + tabStripId + "."
							+ oldTabName + "\"><div style=\"height: " + oldH
							+ "px;\"></div></span>");
					JWic.mobile.TabStrip.activate(tabStripId, newPanelIdx);
				});

				event.preventDefault();
			}
		},

		activate : function(controlId, panelIdx) {
			var tabStrip = JWic.$(controlId);
			JWic.mobile.TabStrip.internalActivate = true;
			tabStrip.tabs("option", "active", panelIdx);
			tabStrip.tabs("refresh");
			JWic.mobile.TabStrip.internalActivate = false;
		},

		destroy : function(control) {
			control.destroy();
		}
	},
	/**
	 * SelectMenu helper methods.
	 */
	SelectMenu : {
		initialize : function(control, fldElm, fldKeyElm, options) {
			var selectmenu = document.getElementById(options.controlID);
			control.selectmenu({
				disabled : options.enabled,
				corners : options.corners,
				mini : options.mini,
				shadow : options.shadow,
				inline : options.inline,
				iconpos : options.iconpos,
				theme : options.theme,
				icon : options.icon,
				closeText : options.closeText,
				nativeMenu : options.nativeMenu,
				preventFocusZoom : options.preventFocusZoom,
				dividerTheme : options.dividerTheme,
				overlayTheme : options.overlayTheme,
				hidePlaceholderMenuItems : options.hidePlaceholderMenuItems,
				elements : options.elements
			});
			selectmenu.dataStore = [];
			selectmenu.jComboField = fldElm;
			selectmenu.jComboKey = fldKeyElm;
			jQuery.each(selectmenu.children, function(key, value) {
				selectmenu.dataStore.push(value);
			});
		},
		destroy : function(control) {
			control.destroy();
		}
	},
	TableViewer : {
		
		/**
		 * Initialize the TableViewer.
		 */
		initialize : function(tableviewer, controlID, options) {
			tableviewer.table({
				defaults : options.defaults,
				disabled : options.disabled,
				columnBtnText : options.columnBtnText 
			});
		},
		
		/**
		 * Activated by onClick event on a row.
		 */
		clickRow : function(tblRow, e, dblClick, callBack) {
		
			if (!e) e = window.event;
			if (typeof dblClick == 'undefined') {
				dblClick = false;
			} else if (dblClick) {
				JWic.util.clearSelection();
			}
			var rowKey = tblRow.attributes.getNamedItem("tbvRowKey").value;
			var tableNode = tblRow.parentNode.parentNode;
			var attributes = tableNode.attributes;
			var tbvCtrlId = attributes.getNamedItem("tbvctrlid").value;
			var tbvSelKey = attributes.getNamedItem("tbvSelKey").value;		
			var tbvSelMode = attributes.getNamedItem("tbvSelMode").value;
			var table = JWic.$(tbvCtrlId);
			
			JWic.log("JWic.mobile.TableViewer.ClickRow: " + tbvSelMode + "; " + tbvSelKey);
			if (tbvSelMode == "multi") {
				JWic.mobile.TableViewer.flipRowSelection(tblRow);
			} else if (tbvSelMode == "single") {
				if (tbvSelKey != rowKey) {
					// deselect old row
					if (tbvSelKey != "") {
						// find selected row and deselect it.
						for (var rowNr = 0; rowNr < tableNode.rows.length; rowNr++)  {
							var trElm = tableNode.rows[rowNr];
							var trKeyItem = trElm.attributes.getNamedItem("tbvRowKey");
							if (trKeyItem != null && trKeyItem.value == tbvSelKey) {
								JWic.mobile.TableViewer.flipRowSelection(trElm);
								break;
							}
						}
					}
					// select
					JWic.mobile.TableViewer.flipRowSelection(tblRow); // select row
					tableNode.attributes.getNamedItem("tbvSelKey").value = rowKey;
				}
			}
			table.enhanceWithin();
		
			// notify control
			JWic.fireAction(tbvCtrlId, dblClick ? 'dblClick' : 'selection', rowKey, callBack);
		
		},
		flipRowSelection : function(tblRow) {
			if (tblRow.className == "selected") {
				tblRow.className = "";
			} else if (tblRow.className == "" || tblRow.className == "undefined") {
				tblRow.className = "selected";
			} else if (tblRow.className == "lastRow") {
				tblRow.className = "lastRowselected";
			} else if (tblRow.className == "lastRowselected") {
				tblRow.className = "lastRow";
			}
		}
	},/**
	 * MListBox helper methods.
	 */
	ListBox : {
		initialize : function(control, options) {
			control.selectmenu({
				disabled : !options.enabled,
				corners : options.corners,
				mini : options.mini,
				shadow : options.shadow,
				inline : options.inline,
				iconpos : options.iconpos,
				theme : options.theme,
				icon : options.icon,
				closeText : options.closeText,
				nativeMenu : options.nativeMenu,
				preventFocusZoom : options.preventFocusZoom,
				dividerTheme : options.dividerTheme,
				overlayTheme : options.overlayTheme,
				hidePlaceholderMenuItems : options.hidePlaceholderMenuItems,
				elements : options.elements
			});
		},
		destroy : function(control) {
			control.destroy();
		}
	},
	
	/**
	 * MCheckBoxGroup helper methods.
	 */
	CheckBoxGroup : {
		initialize : function(control, options) {
			if (control){
				control.controlgroup({
					disabled : !options.enabled,
					defaults: options.defaults,
					excludeInvisible: options.excludeInvisible,
					corners : options.corners,
					mini : options.mini,
					shadow : options.shadow,
					theme : options.theme,
					type  : options.type
				});
			}
		},
		
		handleChange : function (controlId, field, element) {
			var field = JWic.$(field);
			field.val(element.checked ? element.value : "");
		},

		destroy : function(control) {
			control.destroy();
		}
	},
	
	/**
	 * MPopup helper methods.
	 */
	Popup : {
		initialize : function(control, options) {
			if (control) {
				control.popup({
					disabled : options.disabled,
					defaults: options.defaults,
					corners: options.corners,
					dismissible : options.dismissible,
					history : options.history,
					shadow : options.shadow,
					theme : options.theme,
					tolerance : options.tolerance,
					transition : options.transition,
					overlayTheme : options.overlayTheme,
					positionTo  : options.positionTo
				});
			}
		},

		destroy : function(control) {
			control.destroy();
		}
	}
	
};
