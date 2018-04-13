(function($) {
	$.extend(JWic.controls, {
		SlickGrid : {
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
