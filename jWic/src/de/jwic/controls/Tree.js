{
	beforeUpdate: function() {},
	doUpdate: function(element) {
		return false;
	},
	/**
	 * Initialize
	 */
	afterUpdate: function(element) {
	#if($control.visible)
		var treeElm = jQuery('#'+JQryEscape('${control.controlID}'));
		if (treeElm) {
				JWic.controls.Tree.initialize("${control.controlID}");
				//comboElm.dataLoader = $control.comboBehavior.dataLoaderJSClass;
			#if($control.defaultImage)
				treeElm.defaultImage = {
					path : "$control.defaultImage.getPath()",
					imgTag : "$escape.escapeJavaScript($control.defaultImage.toImgTag())",
					width : $control.defaultImage.width,
					height: $control.defaultImage.height
				};
			#else 
				treeElm.defaultImage = null; 
			#end
			#if($control.readonly)
				treeElm.addClassName("x-readonly");
			#end
		}

	#end
	},
	
	/**
	 * ..
	 */
	destroy : function(element) {
		var inpElm = $("${fld.id}");
		if (inpElm) {
			JWic.controls.Tree.destroy("$control.controlID", inpElm);
		}

	}
}