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
			chartImpl = chart.Doughnut(chartData,options);
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
		
		
		canvas.onclick = function(evt){
			 var element = prepareOperation(evt);
				if (element!=undefined){
					
					     JWic.fireAction('$control.controlID', 'click', element);
					 
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