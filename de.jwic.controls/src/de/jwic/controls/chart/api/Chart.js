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

// // Set Text
    			tooltipEl.html($control.getConfiguration().getCustomTooltipHtml());

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
		
			options ={
				bezierCurve: true,
				showTooltips: true,
				scaleShowHorizontalLines: true,
				scaleShowLabels: true,
				scaleType: "date",
				scaleLabel: "<%=value%> C"
		}
			
		// chartData =$control.model.datasetsJson;
		// debugger;
		
		  chartData = [
			          				{
			        					label: 'temperature',
			        					strokeColor: '#A31515',
			        					data: [
			        						{
			        							x: '2011-04-11T11:45:00',
			        							y: 25
			        						},
			        						{
			        							x: '2011-04-11T12:51:00',
			        							y: 28
			        						},
			        						{
			        							x: '2011-04-11T14:10:00',
			        							y: 22
			        						},
			        						{
			        							x: '2011-04-11T15:15:00',
			        							y: 18
			        						},
			        						{
			        							x: '2011-04-11T17:00:00',
			        							y: 25
			        						},
			        						{
			        							x: '2011-04-11T21:00:00',
			        							y: 24
			        						},
			        						{
			        							x: '2011-04-12T13:00:00',
			        							y: 24
			        						}
			        					]
			        				}];
			chartImpl = chart.Scatter(chartData, options);
		#end
		
		#if($control.chartType=='stackedbar')
			var randomScalingFactor = function(){ return Math.round(Math.random()*100)};
			var randomColorFactor = function(){ return Math.round(Math.random()*255)};

		chartData = {
				labels : ["January","February","March","April","May","June","July"],
				datasets : [
					{
						fillColor : "rgba(220,220,220,0.5)",
						strokeColor : "rgba(220,220,220,0.8)",
						highlightFill: "rgba(220,220,220,0.75)",
						highlightStroke: "rgba(220,220,220,1)",
						data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
					},
					{
						fillColor : "rgba(151,187,205,0.5)",
						strokeColor : "rgba(151,187,205,0.8)",
						highlightFill : "rgba(151,187,205,0.75)",
						highlightStroke : "rgba(151,187,205,1)",
						data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
					},
					{
						fillColor : "rgba(240,73,73,0.5)",
						strokeColor : "rgba(240,73,73,0.8)",
						highlightFill : "rgba(240,73,73,0.75)",
						highlightStroke : "rgba(240,73,73,1)",
						data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
					}
				]
			};
			chartImpl = chart.StackedBar(chartData, options);
		#end

		canvas.onclick = function(evt){
			 var element = prepareOperation(evt);
				if (element!=undefined){
					
					     JWic.fireAction('$control.controlID', 'click', element);
					 
				}
			}
		canvas.mousemove = function(handler){
			debugger;
			 var element = prepareOperation(evt);
				if (element!=undefined){
					
					     JWic.fireAction('$control.controlID', 'move', element);
					 
				}
		}
		
	 canvas.onmouseover = function(evt){
	           var element = prepareOperation(evt);
				if (element!=undefined){
					JWic.fireAction('$control.controlID', 'select', element);
				}				     
			}
			
	 function extend(a, b){
		    for(var key in b)
		        if(b.hasOwnProperty(key))
		            a[key] = b[key];
		    return a;
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
				activeElement = chartImpl.getSegmentsAtEvent(evt);
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