(function($) {

	$.extend(JWic.controls,
		{
		
			/**
			 * Tree Control Scripts.
			 */
			Tree : {
				_requestIndexCall : 0,
				
				/**
				 * Initialize a new control.
				 */
				initialize : function(elm) {
					var tree = jQuery("#" + JWic.util.JQryEscape(elm)).get(0);
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
						var tree = jQuery("#"  + JWic.util.JQryEscape(response.controlId)).get(0);
						tree.dataStore = []; // $A(response.data);
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
	);
})(jQuery);