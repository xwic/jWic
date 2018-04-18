(function($) {
	$.extend(JWic.controls, {
		SlickGrid : {
			
			getSelectedRowUID : function(grid) {
				var uid;
				
				var row = grid.getSelectedRows()[0];
				if (row !== undefined) {
					var dataItem = grid.getDataItem(row);
					uid = dataItem.slickGridRowUID;
				}
		    	
		    	return uid;
			},
			
			recordChanges : function(grid, args, fldChanges) {
				var cell = grid.getActiveCell();
		    	var dataItem = grid.getDataItem(cell.row);
		    	
	    		var uid = dataItem.slickGridRowUID;
	    		var fieldName = grid.getColumns()[cell.cell].field;
	    		var newValue = args.editor.serializeValue();
	    		
	    		var jsonObj = [];
	    		var changes = fldChanges.val();
	    		if (changes && changes.trim() !== '') {
	    			jsonObj = jQuery.parseJSON(changes);
	    		}
	    		
	    		var item = {};
	    	    item ["uid"] = uid;
	    	    item ["fieldName"] = fieldName;
	    	    item ["newValue"] = newValue;
	    	    jsonObj.push(item);
	    		
	    		fldChanges.val(JSON.stringify(jsonObj));
			},
			
			setupSelectionModel : function(grid, selectionModel) {
				if (selectionModel === 'ROW') {
			    	grid.setSelectionModel(new Slick.RowSelectionModel());
			    } else if (selectionModel === 'CELL') {
			    	grid.setSelectionModel(new Slick.CellSelectionModel());	    	
			    }
			},			
			
			setupHeaderAndFooter : function(grid) {
				var options = grid.getOptions();
				
				if (options.createPreHeaderPanel) {
			    	grid.onColumnsResized.subscribe(function (e, args) {
			    		JWic.controls.SlickGrid.createColumnGroupingRow(grid);
			    	});
			    	
			    	JWic.controls.SlickGrid.createColumnGroupingRow(grid);
			    }
			    
			    if (options.createFooterRow) {
			    	JWic.controls.SlickGrid.updateAllTotals(grid, grid.getData());
					
				    grid.onCellChange.subscribe(function(e, args) {
				    	JWic.controls.SlickGrid.updateTotal(args.cell, args.grid, grid.getData());
				    });
				    
				    grid.onColumnsReordered.subscribe(function(e, args) {
				    	JWic.controls.SlickGrid.updateAllTotals(args.grid, grid.getData());
				    });
			    }
			},
			
			setupSorting : function(grid) {
				var options = grid.getOptions();
				
			    if (options.multiColumnSort) {
				    grid.onSort.subscribe(function (e, args) {
				    	var cols = args.sortCols;
				    	grid.getData().sort(function (dataRow1, dataRow2) {
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
			    	grid.onSort.subscribe(function(e, args){
						var field = args.sortCol.field;
						
						grid.getData().sort(function(a, b){
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
			
			createColumnGroupingRow : function(grid) {
				var columns = grid.getColumns();
				
			    var $preHeaderPanel = $(grid.getPreHeaderPanel())
			        .empty()
			        .addClass("slick-header-columns")
			        .css('left','-1000px')
			        .width(grid.getHeadersWidth());
			    $preHeaderPanel.parent().addClass("slick-header");
			    var headerColumnWidthDiff = grid.getHeaderColumnWidthDiff();
			    var m, header, lastColumnGroup = '', widthTotal = 0;
			    
			    for (var i = 0; i < columns.length; i++) {
			      m = columns[i];
			      if (lastColumnGroup === m.columnGroup && i>0) {
			        widthTotal += m.width;
			        header.width(widthTotal - headerColumnWidthDiff);
			      } else {
			          widthTotal = m.width;
			          header = $("<div class='ui-state-default slick-header-column' />")
			            .html("<span class='slick-column-name'>" + (m.columnGroup || '') + "</span>")
			            .width(m.width - headerColumnWidthDiff)
			            .appendTo($preHeaderPanel);
			      }
			      lastColumnGroup = m.columnGroup;
			    }
			},
		
			updateAllTotals : function(grid, data) {
				var columnIdx = grid.getColumns().length;
				while (columnIdx--) {
					JWic.controls.SlickGrid.updateTotal(columnIdx, grid, data);
				}
				
				JWic.controls.SlickGrid.hideNonTotalFooterCells(grid);
			},
			
			updateTotal : function(columnIdx, grid, data) {
				var column = grid.getColumns()[columnIdx];
				var columnId = column.id;
				if (column.canBeSummedUp) {
					var total = 0;
					var i = data.length;
					while (i--) {
						total += (parseInt(data[i][columnId], 10) || 0);
					}
					var columnElement = grid.getFooterRowColumn(columnId);
					$(columnElement).html(column.totalLabel + total);
				}
			},
			
			hideNonTotalFooterCells : function(grid) {
				// hide the footer cells for the columns that don't support summing up
				var columns = grid.getColumns();
			    for (var i = 0; i < columns.length; i++) {
			    	var col = columns[i];
			    	if (!col.canBeSummedUp) {
			    		var idx = grid.getColumnIndex(col.id);
			    		var footerCell = grid.getFooterRowColumn(idx);
			    		$(footerCell).hide();
			    	}
			    }
			}
		}
	});
})(jQuery);
