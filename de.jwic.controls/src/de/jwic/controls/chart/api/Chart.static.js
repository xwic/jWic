(function($) {
	var Chartv2 = Chart.noConflict();

	$.extend(JWic.controls, {
		Chart : {
			openTooltipId : null,
			
			initialize : function(controlID, options, config, labelData, dataset, yaxes) {
				var canvas = document.getElementById('chart_' + controlID);
				var ctx = canvas.getContext("2d");	
				if (options.chartType == "overlay"){
					var chart = new Chartv2(ctx);
				} else {
					var chart = new Chart(ctx);
				}
			    
				if(config.customTooltip) {
					try {
						chart.__customTooltipGenerator = eval(config.customTooltipGenerator);
						config.customTooltips = JWic.controls.Chart.customTooltipHandler;
					} catch (e) {
						JWic.log("ERROR: Can't find CustomTooltipGenerator function: " + e);
					}
				}

				var chartImpl = undefined;
				var chartData = undefined;
				var findElement = undefined;
				
				if (options.chartType == 'bar' ||
					options.chartType == 'line' ||
					options.chartType == 'radar' ||
					options.chartType == 'stackedbar' ||
					options.chartType == 'overlay') {
					
					chartData = {
							    labels: labelData,
							    datasets: dataset,
							    yAxes: yaxes
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
					chartImpl = chart.StackedBar(chartData, config);
					findElement = chartImpl.getBarsAtEvent;
					break;
				
				case 'overlay':
					chartImpl = chart.Overlay(chartData, config);
					findElement = chartImpl.getBarsAtEvent;
					break;
					
				default:
					JWic.log("ERROR: Unsupported charttype: " + options.chartType)
				}

				chart.__controlID = controlID; //remember the control id
				
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
				
				// create legend
				if (options.legendLocation != "NONE") {
					var legendDiv = JWic.$("legend_" + controlID);
					if(legendDiv) {
						var innerHtml = "<ul>";
						var dataset = undefined;
						if (chartData.hasOwnProperty('datasets')) {
							dataset = chartData.datasets;
						} else {
							dataset = chartData;
						}
						dataset.forEach(function(ds, i) {
							var legendColor = ds.hasOwnProperty('fillColor')
								? ds.fillColor
								: (ds.hasOwnProperty('strokeColor')
								    ? ds.strokeColor : ds.color);
							innerHtml += "<li><span class=\"chartjs-colbox\" style=\"background-color: " + legendColor + "\">&nbsp;</span>" + ds.label + "</li>";
						});
						innerHtml += "</ul>";
						legendDiv.html(innerHtml);
					} else {
						JWic.log("ERROR: No legend div found for " + controlID);
					}
				}
			
			},
			
			customTooltipHandler : function(tooltip) {

				if (tooltip == false) {
					// hide the current tooltip
					if (JWic.controls.Chart.openTooltipId != null) {
						var tooltipEl = JWic.$('ctt_' + JWic.controls.Chart.openTooltipId);
						if (tooltipEl) {
							tooltipEl.css({
								display : "none"
							})
						}
					}
					return;
				}
				
				var controlID = tooltip.chart.__controlID;
				
		    	// Tooltip Element
				var tooltipEl = JWic.$('ctt_' + controlID);
				
		        // Set caret Position
    			tooltipEl.removeClass('above below');
    			tooltipEl.addClass(tooltip.yAlign);

		    	// Set Text
		    	var innerHtml='';
		    	var generator = tooltip.chart.__customTooltipGenerator; 
		    	if (generator != undefined) {
		    		innerHtml = generator(tooltip);
		    	}
		    	
		    	tooltipEl.html(innerHtml);

		        // Find Y Location on page
    			var top;
    			console.log(tooltip);
    			
//    			if (tooltip.yAlign == 'above') {
//    				top = tooltip.y - tooltip.caretHeight - tooltip.caretPadding;
//    			} else {
//    				top = tooltip.y + tooltip.caretHeight + tooltip.caretPadding;
//    			}
    			
    			var position = jQuery(tooltip.chart.canvas).position();
    			
    			top = tooltip.y;

		        // Display, position, and set styles for font
    			tooltipEl.css({
    				opacity: 1,
    				display: "block",
    				left: (position.left + tooltip.x) + 'px',
    				top: (position.top + top - 20) + 'px',
    				fontFamily: tooltip.fontFamily,
    				fontSize: tooltip.fontSize,
    				fontStyle: tooltip.fontStyle
    			});
    			JWic.controls.Chart.openTooltipId = controlID;
			},
			
			customTooltipGenerators : {
				standardList : function(tooltip) {
					var innerHtml = "";
					if (tooltip.labels) {
			    		for (var i = tooltip.labels.length - 1; i >= 0; i--) {
			    			innerHtml += "<div class=\"chartjs-tooltip-section\">"
								+ "	<span class=\"chartjs-tooltip-key\" style=\"background-color:" + tooltip.legendColors[i].fill + "\"></span>"
								+ "	<span class=\"chartjs-tooltip-value\">" + tooltip.labels[i] + "</span></div>";
			    		}
			    	} else {
			    		console.log(tooltip);
			    		innerHtml = tooltip.text;
			    	}
					return innerHtml; 
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
