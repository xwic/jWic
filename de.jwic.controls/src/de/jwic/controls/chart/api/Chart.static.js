(function($) {

	$.extend(JWic.controls, {
		Chart : {

			initialize : function(controlID, options, config, labelData, dataset) {
				var canvas = document.getElementById('chart_' + controlID);
				var ctx = canvas.getContext("2d");	
				var chart = new Chart(ctx);
			    
				if(config.customTooltip) {
			    	
					Chart.defaults.global.customTooltips = function(tooltip) {
				    	// Tooltip Element
						var tooltipEl = JWic.$('ctt_' + controlID);
	
				        // Hide if no tooltip
		    			if (!tooltip) {
							tooltipEl.css({
								opacity: 0
							});
							return;
		    			}
	
				        // Set caret Position
		    			tooltipEl.removeClass('above below');
		    			tooltipEl.addClass(tooltip.yAlign);
	
				    	// Set Text
				    	var innerHtml='';
				    		  
				    	if (tooltip.labels) {
				    		for (var i = tooltip.labels.length - 1; i >= 0; i--) {
				    			innerHtml += [config.customTooltipHtml].join('');;
				    		}
				    	} else {
				    		innerHtml = config.customTooltipHtml;
				    	}
				    	tooltipEl.html(innerHtml);
	
				        // Find Y Location on page
		    			var top;
		    			if (tooltip.yAlign == 'above') {
		    				top = tooltip.y - tooltip.caretHeight - tooltip.caretPadding;
		    			} else {
		    				top = tooltip.y + tooltip.caretHeight + tooltip.caretPadding;
		    			}
	
				        // Display, position, and set styles for font
		    			tooltipEl.css({
		    				opacity: 1,
		    				left: tooltip.chart.canvas.offsetLeft + tooltip.x + 'px',
		    				top: tooltip.chart.canvas.offsetTop + top + 'px',
		    				fontFamily: tooltip.fontFamily,
		    				fontSize: tooltip.fontSize,
		    				fontStyle: tooltip.fontStyle
		    			});
			    	};
				}

				var chartImpl = undefined;
				var chartData = undefined;
				var findElement = undefined;
				
				if (options.chartType == 'bar' ||
					options.chartType == 'line' ||
					options.chartType == 'radar' ||
					options.chartType == 'stackedbar') {
					
					chartData = {
							    labels: labelData,
							    datasets: dataset
							};
				} else if (options.chartType == 'scatter') {
					chartData = JWic.controls.Chart.convertToDate(dataset);
				} else {
					chartData = dataset;
				}
				
				switch (options.chartType) {
				case 'bar':
					chartImpl = chart.Bar(chartData, config);
					findElement = chartImpl.getBarsAtEvent;
					break;
					
				case 'line':
					chartImpl = chart.Line(chartData, config);
					findElement = chartImpl.getPointsAtEvent;
					break;
					
				case 'circle':
					chartImpl = chart.Doughnut(chartData, config);
					findElement = chartImpl.getSegmentsAtEvent;
					break;
					
				case 'radar':
					chartImpl = chart.Radar(chartData, config);
					findElement = chartImpl.getPointsAtEvent;
					break;
					
				case 'polar':
					chartImpl = chart.PolarArea(chartData, config);
					findElement = chartImpl.getSegmentsAtEvent;
					break;
					
				case 'scatter':
					chartImpl = chart.Scatter(chartData, config);
					findElement = activeElement = chartImpl.getPointsAtEvent;
					break;
					
				case 'stackedbar':
					chartImpl = chart.StackedBar(chartData, options);
					findElement = chartImpl.getBarsAtEvent;
					break;
					
				default:
					JWic.log("ERROR: Unsupported charttype: " + options.chartType)
				}

				canvas.onclick = function(evt){
					 var activeElement = findElement.call(chartImpl, evt);
					 if (activeElement) {
						 var chartElem;
						if (Array.isArray(activeElement)){
						     chartElem = activeElement[0];
						} else {
							 chartElem = activeElement;
						} 
						if (chartElem != undefined && chartElem.label != undefined){
							JWic.fireAction(controlID, 'click', chartElem.label);
						}
					 }
				}
			
			},
			
			zoom : function(div) {
				if ('zoom' in div.style) {
					var value = div.style.zoom;
					if (value != '200%') {
						div.style.zoom = '200%';
					} else {
						div.style.zoom = '100%';
					}
				}
			},
			
			/** Helper **/
			convertToDate : function(jsonArray) {
				 jQuery.each(jsonArray, function(i, val) {
					 jQuery.each(val.data, function(i, data) {
					
					var newDate =  JWic.controls.Chart.convertValueToDate(data.x);
					data.x = newDate;
						});
				 });
				 return jsonArray;
			 },
			 
			 convertValueToDate : function(value) {
				 var reggie = /(\d{4})-(\d{2})-(\d{2}) (\d{2}):(\d{2})/;
				 var dateArray = reggie.exec(value); 
				 var dateObject = new Date(
				     (+dateArray[1]),
				     (+dateArray[2])-1, // Careful, month starts at 0!
				     (+dateArray[3]),
				     (+dateArray[4]),
				     (+dateArray[5])
				 );
				 return dateObject;
			 }
			
		}
	// Chart
	});
})(jQuery);