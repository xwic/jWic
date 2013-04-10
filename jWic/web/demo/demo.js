/**
 * This file customizes the demo application itself.
 */
 

var Samples = { 
	PartObjectLabelProvider : {
		getLabel : function(obj) {
			return "<b>" + obj.object.partNumber +  "</b><br>" + obj.object.name;
		}
	},

	FontNameLabelProvider : {
		getLabel : function(obj) {
			return "<span style=\"font-family: " + obj.title + ";\">" + obj.title + "</span>";
		}
	}

}

