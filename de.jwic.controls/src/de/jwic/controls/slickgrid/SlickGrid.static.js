(function($) {
	$.extend(JWic.controls, {
		SlickGrid : {
			
			getNonEditablePropertyMarker : function (columnId) {
				// see SlickGridDataRow.addNonEditableProperty
				return '<' + columnId + '>'
			},
			
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
		    	var colMarker = JWic.controls.SlickGrid.getNonEditablePropertyMarker(colId);
		    	if (!columnDef.editor || props.indexOf(colMarker) > -1) {
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
			
			recordChanges : function(grid, args, fldChanges, controlId) {
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
	    		
	    		var change = null;
	    		for (var i = 0; i < changes.length; i++) {
					var tmp = changes[i];
					if (tmp["uid"] === uid && tmp["fieldName"] === fieldName) {
						change = tmp;
						break;
					}
				}
	    		
				if (change === null) {
		    		change = {};
		    		change["uid"] = uid;
		    		change["fieldName"] = fieldName;
		    		changes.push(change);
				}
				
				if (change["newValue"] != newValue) {
					change["newValue"] = newValue;
					fldChanges.val(JSON.stringify(changes));
					if (grid.getOptions().sendCellChangesImmediatelyToServer) {
						JWic.fireAction(controlId, 'cellEdited', JSON.stringify(change));
					}
				}	    		
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
			
			setupFilters : function(grid, dataView, columnFilters, controlId) {
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
		    	    
		    	// we don't use the event delegating, because we didn't manage to trigger the event
		    	// when clearing the filters in SlickGrid.js.doUpdate()
		    	// instead, each input box will have the listener attached individually (see onHeaderRowCellRendered)
//			    $(grid.getHeaderRow()).on("change keyup", ":input", function (e) {
//			        var columnId = $(this).data("columnId");
//			        if (columnId != null) {
//			        	columnFilters[columnId] = $.trim($(this).val()).toLowerCase();
//			        	dataView.refresh();
//			        	if (grid.getOptions().createFooterRow) {
//			        		JWic.controls.SlickGrid.updateAllTotals(grid, grid.getData().getFilteredItems());
//			        	}
//			        }
//			    });
			    
			    grid.onHeaderRowCellRendered.subscribe(function(e, args) {
			        $(args.node).empty();
			        
			        // the id is used when clearing filters, see SlickGrid.js.doUpdate()
			        var input = $("<input type='text' id='" + controlId + "_filter_" + args.column.id + "'>");
			        
			        input.data("columnId", args.column.id)
			           .val(columnFilters[args.column.id])
			           .appendTo(args.node);
			        
			        input.on("change keyup", function (e) {
				        var columnId = $(this).data("columnId");
				        if (columnId != null) {
				        	columnFilters[columnId] = $.trim($(this).val()).toLowerCase();
				        	dataView.refresh();
				        	if (grid.getOptions().createFooterRow) {
				        		JWic.controls.SlickGrid.updateAllTotals(grid, grid.getData().getFilteredItems());
				        	}
				        }
				    });
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
					$(columnElement).html(column.totalLabel + total.toLocaleString('en-US', { minimumFractionDigits: 0, maximumFractionDigits: 2 }));
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
			},
			
			KeyTitleDropDownEditor : function(args) {
				
				var $select;
				
			    var previousValue;
			    var keyTitleValues;
			    
			    var scope = this;
			    
			    this.keyCaptureList = [ Slick.keyCode.HOME, Slick.keyCode.END, Slick.keyCode.UP, Slick.keyCode.DOWN ];
				
				this.init = function () {
					// initialize the UI control
					keyTitleValues = args.column.keyTitleValues;
					
					var options = '';
					for (var i = 0; i < keyTitleValues.length; i++) {
						var val = keyTitleValues[i];
						options += "<OPTION value='" + val.key + "'>" + val.title + "</OPTION>";
					}
					$select = $("<SELECT tabIndex='0' class='editor-yesno'>" + options + "</SELECT>");
					$select.appendTo(args.container);
					$select.focus();
			    };


			    /*********** REQUIRED METHODS ***********/

			    this.destroy = function() {
			        // remove all data, events & dom elements created in the constructor
			    	$select.remove();
			    };

			    this.focus = function() {
			        // set the focus on the main input control (if any)
			    	$select.focus();
			    };

			    this.isValueChanged = function() {
			        // return true if the value(s) being edited by the user has/have been changed
			    	if (previousValue === null || previousValue === undefined) {
			    		return $select.val() != "";
			    	}
			    	return $select.val() != previousValue;
			    };

			    this.serializeValue = function() {
			        // return the value(s) being edited by the user in a serialized form
			        // can be an arbitrary object
			        // the only restriction is that it must be a simple object that can be passed around even
			        // when the editor itself has been destroyed
			    	return $select.val();
			    };

			    this.loadValue = function(item) {
			        // load the value(s) from the data item and update the UI
			        // this method will be called immediately after the editor is initialized
			        // it may also be called by the grid if if the row/cell being edited is updated via grid.updateRow/updateCell
			    	$select.val(previousValue = item[args.column.field]);
			        $select.select();
			    };

			    this.applyValue = function(item, state) {
			        // deserialize the value(s) saved to "state" and apply them to the data item
			        // this method may get called after the editor itself has been destroyed
			        // treat it as an equivalent of a Java/C# "static" method - no instance variables should be accessed
			    	item[args.column.field] = state;
			    };

			    this.validate = function() {
			        // validate user input and return the result along with the validation message, if any
			        // if the input is valid, return {valid:true,msg:null}
			    	// if the input is not valid, return {valid:false,msg:"Some message here"}
			        
			    	// this editor can't really be invalid, since it's a list of values
			    	return {
			            valid: true,
			            msg: null
			        };
			    };


			    /*********** OPTIONAL METHODS***********/

//			    this.hide = function() {
//			        // if implemented, this will be called if the cell being edited is scrolled out of the view
//			        // implement this if your UI is not appended to the cell itself or if you open any secondary
//			        // selector controls (like a calendar for a datepicker input)
//			    };
//
//			    this.show = function() {
//			        // pretty much the opposite of hide
//			    };
//
//			    this.position = function(cellBox) {
//			        // if implemented, this will be called by the grid if any of the cell containers are scrolled
//			        // and the absolute position of the edited cell is changed
//			        // if your UI is constructed as a child of document BODY, implement this to update the
//			        // position of the elements as the position of the cell changes
//			        // 
//			        // the cellBox: { top, left, bottom, right, width, height, visible }
//			    };
			    
			    this.init();
			},
			
			getTitleByKey : function (key, keyTitleValues) {
				for (var i = 0; i < keyTitleValues.length; i++) {
					var item = keyTitleValues[i];
					// force toString before comparing, just in case
					if ((item.key + '') == (key + '')) {
						return item.title;
					}
				}
				
				return "";
			}
		}
	});
	

	$.extend(true, window, {
	"Jwic" : {
		"controls" : {
			"Slickgrid" : {
				"Formatters" : {
					"Date" : DateFormatter,
					"KeyTitle" : KeyTitleFormatter,
					"Decimal" : DecimalFormatter
				}
			}
		}
	}
	});
	
	function DateFormatter(row, cell, value, columnDef, dataContext) {
		if (value === null || value === undefined) {
			return "";
		}
		
		// uses the jquery formatting in dateFormat.js. 
		// See https://github.com/phstc/jquery-dateFormat for details
		return DateFormat.format.date(value, columnDef.dateFormat);
	}
	
	function KeyTitleFormatter(row, cell, value, columnDef, dataContext) {
		if (value === null || value == undefined) {
			return "";
		}
		
		return JWic.controls.SlickGrid.getTitleByKey(value, columnDef.keyTitleValues);
	}
	
	function DecimalFormatter(row, cell, value, columnDef, dataContext) {
		if (value === null || value == undefined) {
			return "";
		}
		
		return value.toLocaleString('en-US', { minimumFractionDigits: 0, maximumFractionDigits: 2 });
	}
	
})(jQuery);
