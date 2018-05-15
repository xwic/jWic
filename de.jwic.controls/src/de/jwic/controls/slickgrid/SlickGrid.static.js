(function($) {
	$.extend(JWic.controls, {
		SlickGrid : {
			
			cellFormatterWithReadonlySupport : function (row, cell, value, columnDef, dataContext, grid) {
				var result;
				if (columnDef.origFormatter) {
					result = columnDef.origFormatter(row, cell, value, columnDef, dataContext);
				} else {
					result = value;
				}
				
				if (result === null || result === undefined) {
					result = '';
				}
				
				var colId = columnDef.id;
		    	var props = dataContext.slickGridNonEditableProperties;
		    	if (!columnDef.editor || props.includes(colId)) {
		    		result = '<div class=\'' + grid.getOptions().nonEditableCellCssClass + '\'>' + result + '</div>';
		    	}
		    	return result;
			},
			
			getSelectedRowUID : function(grid) {
				var uid;
				
				var row = grid.getSelectedRows()[0];
				if (row !== null && row !== undefined) {
					var dataItem = grid.getDataItem(row);
					uid = dataItem.id;
				}
		    	
		    	return uid;
			},
			
			recordChanges : function(grid, args, fldChanges) {
		    	var dataItem = grid.getDataItem(args.row);
		    	var column = grid.getColumns()[args.cell];
		    	
	    		var uid = dataItem.id;
	    		var fieldName = column.field;
	    		var newValue = dataItem[column.id];
	    		
	    		var changes = [];
	    		var strChanges = fldChanges.val();
	    		if (strChanges && strChanges.trim() !== '') {
	    			changes = jQuery.parseJSON(strChanges);
	    		}
	    		
	    		var found = false;
	    		for (var i = 0; i < changes.length; i++) {
					var change = changes[i];
					if (change["uid"] === uid && change["fieldName"] === fieldName) {
						change["newValue"] = newValue;
						found = true;
						break;
					}
				}
	    		
				if (!found) {
		    		var newChange = {};
		    		newChange["uid"] = uid;
		    		newChange["fieldName"] = fieldName;
		    		newChange["newValue"] = newValue;
		    		changes.push(newChange);
				}
	    		
	    		fldChanges.val(JSON.stringify(changes));
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
			    	JWic.controls.SlickGrid.updateAllTotals(grid, grid.getData().getFilteredItems());
					
				    grid.onCellChange.subscribe(function(e, args) {
				    	JWic.controls.SlickGrid.updateTotal(args.cell, args.grid, grid.getData().getFilteredItems());
				    });
				    
				    grid.onColumnsReordered.subscribe(function(e, args) {
				    	JWic.controls.SlickGrid.updateAllTotals(args.grid, grid.getData().getFilteredItems());
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
			
			setupFilters : function(grid, dataView, columnFilters) {
				dataView.onRowCountChanged.subscribe(function (e, args) {
	    	    	grid.updateRowCount();
	    	    	grid.render();
	    	    	if (grid.getOptions().createFooterRow) {
	    	    		JWic.controls.SlickGrid.updateAllTotals(grid, grid.getData().getFilteredItems());
	    	    	}
	    	    });
		    	
		    	dataView.onRowsChanged.subscribe(function (e, args) {
		    	    grid.invalidateRows(args.rows);
		    	    grid.render();
		    	    if (grid.getOptions().createFooterRow) {
		    	    	JWic.controls.SlickGrid.updateAllTotals(grid, grid.getData().getFilteredItems());
		    	    }
		    	});
		    	    
			    $(grid.getHeaderRow()).on("change keyup", ":input", function (e) {
			        var columnId = $(this).data("columnId");
			        if (columnId != null) {
			        	columnFilters[columnId] = $.trim($(this).val()).toLowerCase();
			        	dataView.refresh();
			        	if (grid.getOptions().createFooterRow) {
			        		JWic.controls.SlickGrid.updateAllTotals(grid, grid.getData().getFilteredItems());
			        	}
			        }
			    });
			    
			    grid.onHeaderRowCellRendered.subscribe(function(e, args) {
			        $(args.node).empty();
			        $("<input type='text'>")
			           .data("columnId", args.column.id)
			           .val(columnFilters[args.column.id])
			           .appendTo(args.node);
			    });
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
						var x = data[i][columnId];
						total += (parseFloat(data[i][columnId]) || 0);
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
