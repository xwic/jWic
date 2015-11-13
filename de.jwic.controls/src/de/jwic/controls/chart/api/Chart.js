#set($fld = $control.getField("content"))
{

	/**
	 * Invoked after the DOM element was updated. This function is NOT updated
	 * if the custom doUpdate function returned true.
	 */
	afterUpdate: function(element) {
		var field = jQuery(document.getElementById('${control.controlID}'));
		var chartType='$control.chartType';	
		var canvas = document.getElementById('chart');
		var ctx = document.getElementById('chart').getContext("2d");	
		var options = '$control.buildJsonOptions()';
		var chart = new Chart(ctx);
		var chartImpl = undefined;
		var chartData = undefined;
		
		if (chartType=='bar'){
			  chartData = {
					    labels: $control.model.labelsJson,
					    datasets: $control.model.datasetsJson
					};
			  debugger;
			chartImpl = chart.Bar(chartData, options);
		}else if (chartType=='pie'){
			chartData = $control.model.datasetsJson;
			chartImpl = chart.Pie(chartData,options);
		}else if (chartType=='line'){
			 chartData = {
				    labels: $control.model.labelsJson,
				    datasets:$control.model.datasetsJson
				};
			chartImpl = chart.Line(chartData, options);
		}else if (chartType=='radar'){
			chartData = {
				    labels: $control.model.labelsJson,
				    datasets:$control.model.datasetsJson
			}
			chartImpl = chart.Radar(chartData, options);
		}else if (chartType=='doughnut'){
			 chartData = $control.model.datasetsJson;
			chartImpl = chart.Doughnut(chartData,options);	
		}else if (chartType=='polar'){
			chartData = $control.model.datasetsJson;
			chartImpl = chart.PolarArea(chartData, options);
		}else {
			alert('wrong chart type defined');
		}
		
		
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
			
	 
	 function prepareOperation(evt){
		 var activeElement;  
			if (chartType=='bar'){
				activeElement = chartImpl.getBarsAtEvent(evt);
			}else if (chartType=='pie'){
				activeElement = chartImpl.getSegmentsAtEvent(evt);
			}else if (chartType=='line'){
				activeElement = chartImpl.getPointsAtEvent(evt);
			}else if (chartType=='radar'){
				activeElement = chartImpl.getPointsAtEvent(evt);
			}else if (chartType=='doughnut'){
				activeElement = chartImpl.getSegmentsAtEvent(evt);
			}else if (chartType=='polar'){
				activeElement = chartImpl.getSegmentsAtEvent(evt);
			}
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