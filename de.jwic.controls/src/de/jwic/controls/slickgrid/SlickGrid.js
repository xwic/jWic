{
	/**
	 * Invoked before the element is updated.
	 */ 
	beforeUpdate: function() {
	},
	
	/**
	 * Invoked when the element needs to be updated. If this function returns
	 * false, the existing HTML element is replaced by the rendered part that
	 * comes from the server. If the script is doing the update, it should return
	 * true, to prevent the update.
	 */
	doUpdate: function(element) {
		var field = jQuery(document.getElementById('${control.controlID}_thegrid'));
		if (field.length === 0){ // if the field does not exist, the element needs to be created regulary. (jQuery objects are never null, but if the selection returned null the length prop is 0)
			return false;
		}
		
		return true;
	},
	
	/**
	 * Invoked after the DOM element was updated. This function is NOT updated if
	 * the custom doUpdate function returned true.
	 */
	afterUpdate: function(element) {
		var columns = [
	    	#foreach ($col in $control.getModel().getColumns())
		    	#if ($foreach.count > 1),#end
		    	{
		    		id : '$col.id',
		    		field : '$col.field',
		    		name : '$col.name',
		    		
		    		#if($col.cssClass)
		    		cssClass : '$!col.cssClass',
		    		#end
		    		#if($col.headerCssClass)
		    		headerCssClass : '$!col.headerCssClass',
		    		#end
		    		#if($col.toolTip)
		    		toolTip : '$jwic.escapeJavaScript($!col.toolTip)',
		    		#end
		    		#if($col.formatter)
		    		formatter : $!col.formatter,
		    		#end
		    		#if($col.editor)
		    		editor : $!col.editor,
		    		#end
		    		
		    		resizable : $col.resizable,
		    		sortable : $col.sortable,
		    		width : $col.width,
		    		minWidth : $col.minWidth,
		    		maxWidth : $col.maxWidth
		    	}
		    #end
	    ];	  
	    
	    var options = $control.getOptionsAsJson();
	    
	    var data = $control.getDataAsJson();
	    
	    var containerDiv = jQuery(document.getElementById('${control.controlID}_thegrid'));
	    var grid = new Slick.Grid(containerDiv, data, columns, options);
	    
	    grid.setSelectionModel(new Slick.CellSelectionModel());

	    if (options.multiColumnSort) {
		    grid.onSort.subscribe(function (e, args) {
		    	var cols = args.sortCols;
		        data.sort(function (dataRow1, dataRow2) {
		        	for (var i = 0, l = cols.length; i < l; i++) {
		        		var field = cols[i].sortCol.field;
		        		var sign = cols[i].sortAsc ? 1 : -1;
		        		var value1 = dataRow1[field], value2 = dataRow2[field];
		        		var result = (value1 == value2 ? 0 : (value1 > value2 ? 1 : -1)) * sign;
		        		if (result != 0) {
		        			return result;
		        		}
		        	}
		        	return 0;
		        });
		        grid.invalidate();
		    });
	    } else {
	    	grid.onSort.subscribe(function(e, args){ // args: sort information.
				var field = args.sortCol.field;
				
				data.sort(function(a, b){
					var result = 
						a[field] > b[field] ? 1 :
						a[field] < b[field] ? -1 :
						0;
						
					return args.sortAsc ? result : -result;
				});
				
				grid.invalidate();
			});
	    }
	},
	
	/**
	 * Invoked when the existing element is removed from the DOM tree.
	 */
	destroy : function(element) {
	}
}