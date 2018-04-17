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
		var field = JWic.$('${control.controlID}_thegrid');
		
		if (field == undefined || field.length === 0){ 
			// if the field does not exist, the element needs to be created regulary 
			// jQuery objects are never null, but if the selection returned null the length prop is 0
			return false;
		}
		
		if ($control.isClearChanges()) {
			// clear the changes registered so far
			JWic.$('${control.controlID}_fldChanges').val('');
		}
		
		// call this to let the control know that rendering is complete and it should do some cleanup
		$control.redrawComplete();
		
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
	    		totalLabel : '$jwic.escapeJavaScript("$!col.totalLabel")',
	    		
	    		#if($col.cssClass)
	    		cssClass : '$!col.cssClass',
	    		#end
	    		#if($col.headerCssClass)
	    		headerCssClass : '$!col.headerCssClass',
	    		#end
	    		#if($col.toolTip)
	    		toolTip : '$jwic.escapeJavaScript($!col.toolTip)',
	    		#end
	    		#if($col.columnGroup)
	    			columnGroup : '$jwic.escapeJavaScript($!col.columnGroup)',
	    		#end
	    		#if($col.formatter)
	    		formatter : $!col.formatter.name,
	    		#end
	    		#if($col.editor)
	    		editor : $!col.editor.name,
	    		#end
	    		
	    		resizable : $col.resizable,
	    		sortable : $col.sortable,
	    		canBeSummedUp: $col.canBeSummedUp,
	    		
	    		width : $col.width,
	    		minWidth : $col.minWidth,
	    		maxWidth : $col.maxWidth
	    	}
	    	#end
	    ];
		
	    var options = $control.getOptionsAsJson();
	    var data = $control.getDataAsJson();
	    
	    var grid = new Slick.Grid(JWic.$('${control.controlID}_thegrid'), data, columns, options);
	    
	    JWic.controls.SlickGrid.setupSelectionModel(grid, '$control.options.selectionModel.toString()');
	    JWic.controls.SlickGrid.setupHeaderAndFooter(grid);
	    JWic.controls.SlickGrid.setupSorting(grid);
	    
	    grid.onClick.subscribe(function (e, args) {
	    	var uid = JWic.controls.SlickGrid.getSelectedRowUID(grid, e);	    	
	    	JWic.$('${control.controlID}_fldSelection').val(uid);
	    	JWic.fireAction('${control.controlID}', 'rowSelected', uid);
	    });
	    
	    grid.onBeforeCellEditorDestroy.subscribe(function (e, args) {
	    	var fldChanges = JWic.$('${control.controlID}_fldChanges');	    	
	    	JWic.controls.SlickGrid.recordChanges(grid, args, fldChanges,);
	    });
	},
	
	/**
	 * Invoked when the existing element is removed from the DOM tree.
	 */
	destroy : function(element) {
	}	
}