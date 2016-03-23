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
				icon : options.iconClass
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

			if (!comboElm.clientSideFilter) {
				control.on("filterablebeforefilter", filterHandler);
				control.on("click", "LABEL", liClickHandler);
			}
			;

		},
		_handleResponse : function(ajaxResponse, $ul) {
			var html = "";
			var response = jQuery.parseJSON(ajaxResponse.responseText);
			var size = response.data.length;
			html += "<div class=\"ui-controlgroup-controls\">";
			jQuery
					.each(
							response.data,
							function(i, val) {
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
		initialize : function(control, options) {
			control.selectmenu({
				disabled : options.enabled,
				corners : options.corners,
				mini : options.mini,
				shadow : options.shadow,
				inline : options.inline,
				iconpos : options.iconpos,
				theme : options.theme,
				icon : options.iconClass,
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
		}
	}
};
