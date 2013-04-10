/*
 * Copyright 2005-2013 jWic group (http://www.jwic.de)
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
(function($) {

	$.extend(JWic.controls,
		{
			basics : {
				/*
				 * de.jwic.controls.basics.Button control
				 */
				Button : {
					initialize : function(btnElement, ctrlId) {
						JWic.log(btnElement);
						JWic.log("Initializing new button " + btnElement);
						btnElement
							.button()
							.click(JWic.controls.basics.Button.clickHandler);
						btnElement.data("controlId", ctrlId);
					},

					clickHandler : function(e) {
						e.stopPropagation();
						var elm = jQuery(e.currentTarget);
						
						var ctrlId = elm.data("controlId");
						var msg = elm.attr("_confirmMsg");
						if (msg && msg != "") {
							if (!confirm(msg)) {
								return false;
							}
						}
						JWic.log(elm);
						JWic.log("click - " + ctrlId);
						JWic.fireAction(ctrlId, 'click', '');
					},
				},
			
				/**
				 * de.jwic.controls.basics.TabStrip control functions. 
				 */
				TabStrip : {
						initialize : function(tabStrip, ctrlId, activeIndex) {
							JWic.log(activeIndex);
							tabStrip.tabs({
								activate : JWic.controls.basics.TabStrip.activateHandler,
								active : activeIndex
							});
						},
						
						activateHandler : function (event, ui) {
							if (ui.newPanel) {
								var tabStripId = ui.newPanel.attr("jwicTabStripId");
								var tabName = ui.newPanel.attr("jwicTabName");
								JWic.fireAction(tabStripId, "activateTab", tabName);
							}
						},
						activate : function(controlId, panelIdx) {
							var tabStrip = $("#" + JQryEscape(controlId));
							tabStrip.tabs("option", "active", panelIdx );
							tabStrip.tabs("refresh");
							
						}
				}
			}
		
	}
	);
})(jQuery);