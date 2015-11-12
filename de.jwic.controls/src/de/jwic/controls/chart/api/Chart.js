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
		//Chart.defaults.global = '$control.global.buildJsonOptions()';
		var options = '$control.buildJsonOptions()';
		var chart = new Chart(ctx);
		var chartImpl = undefined;
		var chartData = undefined;
		
		if (chartType=='bar'){
			  chartData = {
					    labels: $control.model.labels,
					    datasets: $control.model.datasets
					};
			chartImpl = chart.Bar(chartData, options);
		}else if (chartType=='pie'){
			chartData = $control.model.datasets;
			chartImpl = chart.Pie(chartData,options);
		}else if (chartType=='line'){
			 chartData = {
				    labels: $control.model.labels,
				    datasets:$control.model.datasets
				};
			chartImpl = chart.Line(chartData, options);
		}else if (chartType=='radar'){
			chartData = {
				    labels: $control.model.labels,
				    datasets:$control.model.datasets
			}
			chartImpl = chart.Radar(chartData, options);
		}else if (chartType=='doughnut'){
			 chartData = $control.model.datasets;
			chartImpl = chart.Doughnut(chartData,options);	
		}else if (chartType=='polar'){
			debugger;
			chartData = $control.model.datasets;
			chartImpl = chart.PolarArea(chartData, options);
		}else {
			alert('wrong chart type defined');
		}
		
		
		canvas.onclick = function(evt){
			debugger;
//			 var activeBars = bar.getBarsAtEvent(evt);
//			if(activeBars) {
//				var chartElem = activeBars[0];
//				JWic.fireAction('$control.controlID', 'click', chartElem.label + ';' + chartElem.value);
//			}
		}

	}

	
	
}