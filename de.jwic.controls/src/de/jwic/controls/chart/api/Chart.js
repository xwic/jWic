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
		var chart = new Chart(ctx);
		 var functionOptions = {
    		 onAnimationProgress:function() {
// JWic.fireAction('$control.controlID', 'animationInProgress');
	    		 },
// onAnimationComplete:function(){
// JWic.fireAction('$control.controlID', 'animationComplete');
// }
	    }
	    var options = $control.getConfigurationJSON();
	   // var options = extend(localOptions,functionOptions);
		var chartImpl = undefined;
		var chartData = undefined;
		
		if (chartType=='bar'){
			  chartData = {
					    labels: $control.model.labelsJson,
					    datasets: $control.model.datasetsJson
					};
			chartImpl = chart.Bar(chartData, options);
		}else if (chartType=='circle'){
			chartData = $control.model.datasetsJson;
			chartImpl = chart.Doughnut(chartData,options);
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
			
	 function extend(a, b){
		    for(var key in b)
		        if(b.hasOwnProperty(key))
		            a[key] = b[key];
		    return a;
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