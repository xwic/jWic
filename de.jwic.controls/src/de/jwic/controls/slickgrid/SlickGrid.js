{

	getData : function() {
		return $control.getDataAsJson();
	},
	
	getColumns : function() {
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
	    		formatter : $!col.formatter.jsName,
	    		#end
	    		#if($col.dateFormat)
	    		dateFormat : '$jwic.escapeJavaScript($!col.dateFormat)',
	    		#end
	    		#if($col.editor)
	    		editor : $!col.editor.jsName,
	    		#end
	    		
	    		resizable : $col.resizable,
	    		sortable : $col.sortable,
	    		canBeSummedUp: $col.canBeSummedUp,
	    		
	    		width : $col.width,
	    		minWidth : $col.minWidth,
	    		maxWidth : $col.maxWidth,
	    		
	    		keyTitleValues : $control.getKeyTitleValuesAsJson($col)
	    	}
	    	#end
	    ];
		
		for (var i = 0; i < columns.length; i++) {
			var col = columns[i];
			col.origFormatter = col.formatter;
			col.formatter = JWic.controls.SlickGrid.cellFormatterWithReadonlySupport;
		}
		
		return columns;
	},
	
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
		
		if ($control.isRerender()) {
			var grid = JWic.$('${control.controlID}_thegrid').data('theGridInstance');
			grid.destroy();
			return false;
		}
		
		#if ($control.isClearChanges())
			// clear the changes registered so far
			JWic.$('${control.controlID}_fldChanges').val('');
		#end
		
		var render = false;
		
		#if ($control.isReloadColumns()) 
			var grid = JWic.$('${control.controlID}_thegrid').data('theGridInstance');
			
			var columns = this.getColumns();
			grid.setColumns(columns);
			
			render = true;
			
			// note: if column reload is requested, then a data reload is automatically requested as well,
			// therefore we don't need to do it explicitely
			
			// re-create the column groups
			JWic.controls.SlickGrid.createColumnGroupingRow(grid);
		#end
		
		#if ($control.isReloadData()) 
			// the grid is stored in the div element's data.. see afterUpdate(), right after the grid is created
			var grid = JWic.$('${control.controlID}_thegrid').data('theGridInstance');
			var data = this.getData();
			
			var dataView = grid.getData();
			dataView.beginUpdate();
		    dataView.setItems(data);
		    dataView.endUpdate();
		    if (grid.getOptions().createFooterRow) {
        		JWic.controls.SlickGrid.updateAllTotals(grid, grid.getData().getFilteredItems());
        	}
		    
		    render = true;
		#end
		
		#if ($control.isClearSelection()) 
			// the grid is stored in the div element's data.. see afterUpdate(), right after the grid is created
			var grid = JWic.$('${control.controlID}_thegrid').data('theGridInstance');
		 	grid.setSelectedRows([]);
		 	grid.resetActiveCell();
		#end
		
		#if ($control.isClearFilters())
			var grid = JWic.$('${control.controlID}_thegrid').data('theGridInstance');
			if (grid.getOptions().showHeaderRow) {
				var headerRow = $(grid.getHeaderRow());
				var inputs = headerRow.find('input');
				for (var i = 0; i < inputs.length; i++) {
					var input = inputs[i];
					if ($(input).val()) {
						$(input).val("");
						JWic.$(input.id).keyup();
					}
				}
			}
		#end
		
		if (render) {
			grid.invalidate();
			grid.render();
		}
		
		// call this to let the control know that the update is complete and it should do some cleanup
		$control.redrawComplete()
		
		return true;
	},
	
	/**
	 * Invoked after the DOM element was updated. This function is NOT updated if
	 * the custom doUpdate function returned true.
	 */
	afterUpdate: function(element) {
		var columns = this.getColumns();
		
	    var options = $control.getOptionsAsJson();
	    options.explicitInitialization = true; // hardcode this option because we need it if using a data view
	    
	    var data = this.getData();
	    
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
	    		if (!options.stopRowSelectedEvent) {
	    			JWic.fireAction('${control.controlID}', 'rowSelected', uid);
	    		}
	    	}
	    });
	    
	    grid.onBeforeEditCell.subscribe(function (e, args) {
	    	var item = args.item;
	    	var colMarker = JWic.controls.SlickGrid.getNonEditablePropertyMarker(args.column.id);
	    	if (item.slickGridNonEditableProperties.indexOf(colMarker) > -1) {
	    		return false;
	    	}
	    	return true;
	    });
	    
	    grid.onCellChange.subscribe(function (e, args) {
	    	var fldChanges = JWic.$('${control.controlID}_fldChanges');
	    	if (fldChanges !== null && fldChanges !== undefined) {
	    		JWic.controls.SlickGrid.recordChanges(grid, args, fldChanges, '${control.controlID}');
	    	}
	    });
	    
	    JWic.addBeforeRequestCallback("$control.controlID", function() {
	    	grid.getEditorLock().commitCurrentEdit();
	    });
	    
	    if (options.showHeaderRow) {
	    	JWic.controls.SlickGrid.setupFilters(grid, dataView, columnFilters, '${control.controlID}');
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
	    
	    grid.invalidate();
    	grid.render();
    	
	    function filter(item) {
	        for (var columnId in columnFilters) {
	        	if (columnId === undefined || columnFilters[columnId] !== "") {
	        		var c = grid.getColumns()[grid.getColumnIndex(columnId)];
	        		// manipulate the value we will use for the comparison
	        		var strCellValue = item[c.field] + '';
	        		if (c.keyTitleValues && c.keyTitleValues.length > 0) {
	        			strCellValue = JWic.controls.SlickGrid.getTitleByKey(strCellValue, c.keyTitleValues);
	        		}
	        		strCellValue = strCellValue.toLowerCase();
	        		if (strCellValue === 'true' || strCellValue === '1') {
	        			strCellValue = 'yes';
	        		} else if (strCellValue === 'false' || strCellValue === '0') {
	        			strCellValue = 'no';
	        		}
	        		if (strCellValue.indexOf(columnFilters[columnId]) < 0) {
	        			return false;
	        		}
	        	}
	        }
	        return true;
	    }
	    
	    // just in case...
	    $control.redrawComplete();
	},
	
	/**
	 * Invoked when the existing element is removed from the DOM tree.
	 */
	destroy : function(element) {
	}
}