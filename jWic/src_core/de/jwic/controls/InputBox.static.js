(function($) {

	$.extend(JWic.controls,
		{
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
						return inpElm.value;
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
					JWic.fireAction(elm.attr('id'), 'keyPressed', '' + e.keyCode);
				}
			}
			
		}
	}
	);
})(jQuery);