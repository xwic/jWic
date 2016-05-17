(function($) {

	$.extend(JWic.controls, {
		Chart : {
			openTooltipId : null,
			
			initialize : function(controlID, options, config, labelData, dataset, yaxes) {
				var canvas = document.getElementById('chart_' + controlID);
				var ctx = canvas.getContext("2d");
				
				var chartData = undefined;
				chartData = {
					    labels: labelData,
					    datasets: dataset,
					    yAxes: yaxes
				};
				chartConfig = {
						type : options.chartType,
						data : chartData,
						options : config
				};
				
				var chart = new Chart(ctx, chartConfig);
			    
				if(config.customTooltip) {
					try {
						chart.__customTooltipGenerator = eval(config.customTooltipGenerator);
						config.customTooltips = JWic.controls.Chart.customTooltipHandler;
					} catch (e) {
						JWic.log("ERROR: Can't find CustomTooltipGenerator function: " + e);
					}
				}

				var chartImpl = undefined;
				var findElement = undefined;
				
				if (options.chartType == 'bar' ||
					options.chartType == 'line' ||
					options.chartType == 'radar' ||
					options.chartType == 'stackedbar' ||
					options.chartType == 'overlay') {
					
				} else if (options.chartType == 'scatter') {
					chartData = JWic.controls.Chart.convertToDate(dataset);
				} else {
					
				}

				chart.__controlID = controlID; //remember the control id

				// create legend
				if (options.legendLocation != "NONE") {
					var legendDiv = JWic.$("legend_" + controlID);
					if(legendDiv) {
						if (options.chartType != "doughnut" && options.chartType != "polarArea"){
							var innerHtml = "<ul>";
							var dataset = undefined;
							if (chartData.hasOwnProperty('datasets')) {
								dataset = chartData.datasets;
							} else {
								dataset = chartData;
							}
							dataset.forEach(function(ds, i) {
								var legendColor = ds.hasOwnProperty('backgroundColor')
									? ds.backgroundColor
									: (ds.hasOwnProperty('hoverBackgroundColor')
									    ? ds.hoverBackgroundColor : ds.color);
								innerHtml += "<li><span class=\"chartjs-colbox\" style=\"background-color: " + legendColor + "\">&nbsp;</span>" + ds.label + "</li>";
							});
							innerHtml += "</ul>";
							legendDiv.html(innerHtml);
						} else {
							var innerHtml = "<ul>";
							var dataset = undefined;
							if (chartData.hasOwnProperty('labels')) {
								dataset = chartData.labels;
							} else {
								dataset = chartData;
							}
							dataset.forEach(function(ds, i) {
								var legendColor = chartData.datasets[0].hasOwnProperty('backgroundColor')
									? chartData.datasets[0].backgroundColor[i]
									: (chartData.datasets[0].hasOwnProperty('hoverBackgroundColor')
									    ? chartData.datasets[0].hoverBackgroundColor[i] : chartData.datasets[0].color);
								innerHtml += "<li><span class=\"chartjs-colbox\" style=\"background-color: " + legendColor + "\">&nbsp;</span>" + ds + "</li>";
							});
							innerHtml += "</ul>";
							legendDiv.html(innerHtml);
						}
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
