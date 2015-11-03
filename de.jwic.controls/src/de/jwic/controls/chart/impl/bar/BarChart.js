#set($fld = $control.getField("content"))
{

	/**
	 * Invoked after the DOM element was updated. This function is NOT updated
	 * if the custom doUpdate function returned true.
	 */
	afterUpdate: function(element) {
		var field = jQuery(document.getElementById('${control.controlID}'));
		var ctx = document.getElementById('chart').getContext("2d");
		var randomScalingFactor = function(){ return Math.round(Math.random()*100)};
		Chart.defaults.global ={
				responsive : $control.global.responsive,
				animation: $control.global.animation,
				animationSteps: $control.global.animationSteps,
				animationEasing: '$control.global.animationEasing',
				showScale: $control.global.showScale,
				scaleOverride: $control.global.scaleOverride,
				scaleSteps: null,
				scaleStepWidth: null,
				scaleStartValue: null,
				 scaleLineColor: '$control.global.scaleLineColor',
				 scaleLineWidth: 1,
				 scaleShowLabels: true,
				 scaleLabel: "<%=value%>",
				 scaleIntegersOnly: true,
				 scaleBeginAtZero: false,
				 scaleFontFamily: "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif",
				 scaleFontSize: 12,
				 scaleFontStyle: "normal",
				 scaleFontColor: "#666",
				 maintainAspectRatio: true,
				 showTooltips: true,
				 customTooltips: false,
				 tooltipEvents: ["mousemove", "touchstart", "touchmove"],
				 tooltipFillColor: "rgba(0,0,0,0.8)",
				 tooltipFontFamily: "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif",
				 tooltipFontSize: 14,
				 tooltipFontStyle: "normal",
				 tooltipFontColor: "#fff",
				 tooltipTitleFontFamily: "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif",
				 tooltipTitleFontSize: 14,
				 tooltipTitleFontStyle: "bold",
				  tooltipTitleFontColor: "#fff",
				  tooltipYPadding: 6,
				  tooltipXPadding: 6,
				  tooltipCaretSize: 8,
				  tooltipCornerRadius: 6,
				  tooltipXOffset: 10,
				  tooltipTemplate: "<%if (label){%><%=label%>: <%}%><%= value %>",
				  multiTooltipTemplate: "<%= value %>",
				  onAnimationProgress: function(){},
				  onAnimationComplete: function(){}

		};
	 
	
			var options= {
				    scaleBeginAtZero : $control.options.scaleBeginAtZero,
				    scaleShowGridLines : $control.options.scaleShowGridLines,
				    scaleGridLineColor : '$control.options.scaleGridLineColor',
				    scaleGridLineWidth : $control.options.scaleGridLineWidth,
				    scaleShowHorizontalLines: $control.options.scaleShowHorizontalLines,
                    scaleShowVerticalLines: $control.options.scaleShowVerticalLines,
                    barShowStroke : $control.options.barShowStroke,
                    barStrokeWidth : $control.options.barStrokeWidth,
                    barValueSpacing : $control.options.barValueSpacing,
                    barDatasetSpacing : $control.options.barDatasetSpacing,
				    legendTemplate : '$control.options.legendTemplate'
			};
				
		    var	chartData = {
				    labels: $control.model.labels,
				    datasets: $control.model.datasets
				};
			 
			var chart = new Chart(ctx).Bar(chartData, options);
			

	}

	
	
}