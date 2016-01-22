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
	            if(options.enabled){
	    		    var clickHandler = function ButtonClickHandler() {
	    		        JWic.fireAction(jQuery(this).data('id'), 'click');
	    		    };
	                control.on('click', clickHandler);
	            }
	            control.button({
	            	disabled: !options.enabled
	            });
	        }
		},
		
		/**
		 * CheckBox helper methods
		 */
		CheckBox : {
		    initialize : function(control, options, fieldId) {
			    var clickHandler = function (evnt){
			    	var cb = jQuery(this);
			    	var field = JWic.$("chkVal_" + cb.data('fieldId'));
			    	if (field.length > 0) {
			    		field[0].value = cb[0].checked ? "1" : "";
			    		if (cb.data('options').changeNotification) {
			    			JWic.fireAction(cb.data('id'), 'clicked', '');
			    		}
			    	} else {
			    		JWic.log("WARN: Cannot find hidden field for checkbox value 'chkVal_" + cb.data('fieldId'));
			    	}
			        return true;
			    };
	            control.data('id', options.controlID);
	            control.data('fieldId', fieldId);
	            control.data('options', options);
	            control.checkboxradio({
	                disabled : !options.enabled
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
	                disabled : !options.enabled
	            });
		    }
		},
		/**
		 * InputBox helper methods
		 */
		InputBox : {
			initialize : function(control, options) {
				control.textinput();
			}
		},
		/**
		 * FlipSwitch helper methods
		 */
		FlipSwitch : {
			initialize : function(control, options, fieldId) {
			    var clickHandler = function (evnt){
			    	var cb = jQuery(this);
			    	var field = JWic.$("chkVal_" + cb.data('fieldId'));
			    	if (field.length > 0) {
			    		field[0].value = cb[0].checked ? "1" : "";
			    		if (cb.data('options').changeNotification) {
			    			JWic.fireAction(cb.data('id'), 'clicked', '');
			    		}
			    	} else {
			    		JWic.log("WARN: Cannot find hidden field for checkbox value 'chkVal_" + cb.data('fieldId'));
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
		}
};
