/**
 * This is a basic construct of static javascript files.
 * This file is loaded only once, therefore the BasicControlScript object is as global 'singleton'
 * Note that these kind of control should be placed in a namespace-like structure to avoid global object pollution
 * 
 * The callback methods reflect the method defined in the BasicControlScript.js for easy reading, but this is not necessary
 *
 */
var BasicControlScript = (function($, jWic, undefined){
	"use strict";
	//private methods
	
	//public API goes here
	return {
		beforeUpdate : function(control, options){

		},
		doUpdate : function(control, options){
			return false;
		},
		afterUpdate : function(control, options){
			
		},
		destroy : function(control, options){
			
		}
	}
	
}(jQuery, JWic));// dependencies
