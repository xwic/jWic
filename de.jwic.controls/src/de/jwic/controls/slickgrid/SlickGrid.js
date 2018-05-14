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
		
		if (field === null || field === undefined || field.length === 0){ 
			// if the field does not exist, the element needs to be created regulary 
			// jQuery objects are never null, but if the selection returned null the length prop is 0
			return false;
		}
		
		if ($control.isClearChanges()) {
			// clear the changes registered so far
			JWic.$('${control.controlID}_fldChanges').val('');
		}
		
		if ($control.isReloadData()) {
			// the grid is stored in the div element's data.. see afterUpdate(), right after the grid is created
			var grid = JWic.$('${control.controlID}_thegrid').data('theGridInstance');
			var data = $control.getDataAsJson();
			grid.setData(data, true);
			grid.render();
		}
		
		// call this to let the control know that the update is complete and it should do some cleanup
		$control.redrawComplete();
		
		return true; // if the doUpdate script didn't do any actual updates, we simply redraw the whole grid
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
	    		formatter : $!col.formatter,
	    		#end
	    		#if($col.editor)
	    		editor : $!col.editor,
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
		
		for (var i = 0; i < columns.length; i++) {
			var col = columns[i];
			col.origFormatter = col.formatter;
			col.formatter = JWic.controls.SlickGrid.nonEditableCellFormatter;
		}
		
	    var options = $control.getOptionsAsJson();
	    options.explicitInitialization = true; // hardcode this option because we need it if using a data view
	    
	    var data = $control.getDataAsJson();
	    
	    var dataView = new Slick.Data.DataView();
	    
	    var columnFilters = {};
	    
	    var grid = new Slick.Grid(JWic.$('${control.controlID}_thegrid'), dataView, columns, options);
	    
	    // store the grid in the div element's data, so that we can fetch it back in doUpdate()
	    JWic.$('${control.controlID}_thegrid').data('theGridInstance', grid);
	    
	    grid.onSelectedRowsChanged.subscribe(function (e, args) {
	    	var uid = JWic.controls.SlickGrid.getSelectedRowUID(grid);	    	
	    	var currentUID = JWic.$('${control.controlID}_fldSelection').val();
	    	if (uid !== currentUID) {
	    		if (uid === null || uid === undefined) {
	    			// don't send junk to the server
	    			uid = '';
	    		}
	    		// only fire the selection event if the row changed, as users might double-click to go into edit mode
	    		JWic.$('${control.controlID}_fldSelection').val(uid);
	    		JWic.fireAction('${control.controlID}', 'rowSelected', uid);
	    	}
	    });
	    
	    grid.onBeforeEditCell.subscribe(function (e, args) {
	    	var item = args.item;
	    	var column = args.column;
	    	if (item.slickGridNonEditableProperties.includes(column.id)) {
	    		return false;
	    	}
	    	return true;
	    });
	    
	    grid.onCellChange.subscribe(function (e, args) {
	    	var fldChanges = JWic.$('${control.controlID}_fldChanges');
	    	if (fldChanges !== null && fldChanges !== undefined) {
	    		JWic.controls.SlickGrid.recordChanges(grid, args, fldChanges);
	    	}
	    });
	    
	    JWic.addBeforeRequestCallback("$control.controlID", function() {
	    	grid.getEditorLock().commitCurrentEdit();
	    });
	    
	    if (options.showHeaderRow) {
	    	JWic.controls.SlickGrid.setupFilters(grid, dataView, columnFilters);
	    }
	    
	    grid.init();
	    
	    dataView.beginUpdate();
	    dataView.setItems(data);
	    if (options.showHeaderRow) {
	    	dataView.setFilter(filter);
	    }
	    dataView.endUpdate();
	    
	    JWic.controls.SlickGrid.setupSelectionModel(grid, '$control.options.selectionModel.toString()');
	    JWic.controls.SlickGrid.setupHeaderAndFooter(grid);
	    JWic.controls.SlickGrid.setupSorting(grid);
	    
    	grid.render();
	    
	    function filter(item) {
	        for (var columnId in columnFilters) {
	        	if (columnId === undefined || columnFilters[columnId] !== "") {
	        		var c = grid.getColumns()[grid.getColumnIndex(columnId)];
	        		// manipulate the value we will use for the comparison
	        		var strCellValue = item[c.field] + '';
	        		strCellValue = strCellValue.toLowerCase();
	        		if (strCellValue === 'true') {
	        			strCellValue = 'yes';
	        		}
	        		if (strCellValue === 'false') {
	        			strCellValue = 'no';
	        		}
	        		if (!strCellValue.includes(columnFilters[columnId])) {
	        			return false;
	        		}
	        	}
	        }
	        return true;
	    }
	},
	
	/**
	 * Invoked when the existing element is removed from the DOM tree.
	 */
	destroy : function(element) {
	}
}