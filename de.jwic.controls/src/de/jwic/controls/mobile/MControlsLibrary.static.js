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
			control.combo({
				disabled : !options.enabled,
				defaults : options.defaults,
				enhanced : options.enhanced,
				filterReveal : options.filterReveal,
				input : options.input
			});
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
			tabStrip[0].children[0].firstElementChild.children[activeIndex].children[0].classList.add("ui-btn-active");
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
	 * Combo helper methods.
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
				hidePlaceholderMenuItems : options.hidePlaceholderMenuItems
			});
		},
		destroy : function(control) {
			control.destroy();
		}
	}
};
