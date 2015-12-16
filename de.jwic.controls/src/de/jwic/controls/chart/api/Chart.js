

#set($fld = $control.getField("content"))
{
	
	
	/**
	 * Invoked after the DOM element was updated. This function is NOT updated
	 * if the custom doUpdate function returned true.
	 */
	afterUpdate: function(element) {
		var field = jQuery(document.getElementById('${control.controlID}'));
		var canvas = document.getElementById('chart');
		var ctx = document.getElementById('chart').getContext("2d");	
		var chart = new Chart(ctx);
	    var options = $control.getConfigurationJSON();
	    #if($control.getConfiguration().isCustomTootlip())
	    	
	           Chart.defaults.global.customTooltips = function(tooltip) {

	    	// Tooltip Element
	    		var tooltipEl = jQuery('#chartjs-tooltip');

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
	    		  
	    		if (tooltip.labels){
	    				  for (var i = tooltip.labels.length - 1; i >= 0; i--) {
	    					  
	    			        	innerHtml += [$control.getConfiguration().getCustomTooltipHtml()].join('');;
	    				  }
	    			} else {
	    				innerHtml = $control.getConfiguration().getCustomTooltipHtml();
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
	    				fontStyle: tooltip.fontStyle,
	    			});
	    	};
       #end
	    
		var chartImpl = undefined;
		var chartData = undefined;
		#if($control.chartType=='bar')
			  chartData = {
					    labels: $control.model.labelsJson,
					    datasets: $control.model.datasetsJson
					};
			chartImpl = chart.Bar(chartData, options);
		#end
		
		#if($control.chartType=='circle')
			chartData = $control.model.datasetsJson;
			chartImpl = chart.Doughnut(chartData, options);
		#end
			
		#if($control.chartType=='line')
			 chartData = {
				    labels: $control.model.labelsJson,
				    datasets:$control.model.datasetsJson
				};
			chartImpl = chart.Line(chartData, options);
		#end
		
		#if($control.chartType=='radar')
			chartData = {
				    labels: $control.model.labelsJson,
				    datasets:$control.model.datasetsJson
			}
			chartImpl = chart.Radar(chartData, options);
		#end
		
		#if($control.chartType=='polar')
			chartData = $control.model.datasetsJson;
			chartImpl = chart.PolarArea(chartData, options);
		#end
		
		#if($control.chartType=='scatter')
			debugger;
		 chartData =convertToDate($control.model.datasetsJson);
		chartImpl = chart.Scatter(chartData, options);
		#end
		
		#if($control.chartType=='stackedbar')
			
			
			  
			  chartData = {
					    labels: $control.model.labelsJson,
					    datasets: $control.model.datasetsJson
					};	
			
			chartImpl = chart.StackedBar(chartData, options);
		#end

		canvas.onclick = function(evt){
			 var element = prepareOperation(evt);
				if (element!=undefined){
					
					     JWic.fireAction('$control.controlID', 'click', element);
					 
				}
			}
// canvas.mousemove = function(handler){
// debugger;
// var element = prepareOperation(evt);
// if (element!=undefined){
//					
// JWic.fireAction('$control.controlID', 'move', element);
//					 
// }
// }
//		
// canvas.onmouseover = function(evt){
// debugger;
// var element = prepareOperation(evt);
// if (element!=undefined){
// JWic.fireAction('$control.controlID', 'select', element);
// }
// }
			
	 function extend(a, b){
		    for(var key in b)
		        if(b.hasOwnProperty(key))
		            a[key] = b[key];
		    return a;
		 }
	 
	 function convertToDate(jsonArray){
		 jQuery.each(jsonArray, function(i, val) {
			 jQuery.each(val.data, function(i, data) {
			
			var newDate =  convertValueToDate(data.x);
			data.x = newDate;
				});
		 });
		 return jsonArray;
	 }
	 
	 function convertValueToDate(value){
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
	 
	 function prepareOperation(evt){
		 var activeElement = undefined;  
			#if($control.chartType=='bar')
				activeElement = chartImpl.getBarsAtEvent(evt);
			#end
			
			#if($control.chartType=='pie')
				activeElement = chartImpl.getSegmentsAtEvent(evt);
			#end
			
			#if($control.chartType=='line')
				activeElement = chartImpl.getPointsAtEvent(evt);
			#end
			
			#if($control.chartType=='radar')
				activeElement = chartImpl.getPointsAtEvent(evt);
			#end
			
			#if($control.chartType=='doughnut')
				activeElement = chartImpl.getSegmentsAtEvent(evt);
			#end
			
			#if($control.chartType=='polar')
				activeElement = chartImpl.getSegmentsAtEvent(evt);
			#end
			
			#if($control.chartType=='scatter')
				activeElement = chartImpl.getPointsAtEvent(evt);
			#end
			
			#if($control.chartType=='stackedbar')
				activeElement = chartImpl.getBarsAtEvent(evt);
			#end
			
			
			if(activeElement) {
				var chartElem = undefined;
				if (Array.isArray(activeElement)){
				     chartElem = activeElement[0];
				} else {
					 chartElem = activeElement;
				} 
				if (chartElem!= undefined && chartElem.label!=undefined){
				
					return chartElem.label;
				   
				}
			}
	 }

	
	}

	
}