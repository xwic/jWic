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
		
		var options = {
				    // Boolean - Whether to show lines for each scale point
				    scaleShowLine : true,

				    // Boolean - Whether we show the angle lines out of the
					// radar
				    angleShowLineOut : true,

				    // Boolean - Whether to show labels on the scale
				    scaleShowLabels : false,

				    // Boolean - Whether the scale should begin at zero
				    scaleBeginAtZero : true,

				    // String - Colour of the angle line
				    angleLineColor : "rgba(0,0,0,.1)",

				    // Number - Pixel width of the angle line
				    angleLineWidth : 1,

				    // String - Point label font declaration
				    pointLabelFontFamily : "'Arial'",

				    // String - Point label font weight
				    pointLabelFontStyle : "normal",

				    // Number - Point label font size in pixels
				    pointLabelFontSize : 10,

				    // String - Point label font colour
				    pointLabelFontColor : "#666",

				    // Boolean - Whether to show a dot for each point
				    pointDot : true,

				    // Number - Radius of each point dot in pixels
				    pointDotRadius : 3,

				    // Number - Pixel width of point dot stroke
				    pointDotStrokeWidth : 1,

				    // Number - amount extra to add to the radius to cater for
					// hit detection outside the drawn point
				    pointHitDetectionRadius : 20,

				    // Boolean - Whether to show a stroke for datasets
				    datasetStroke : true,

				    // Number - Pixel width of dataset stroke
				    datasetStrokeWidth : 2,

				    // Boolean - Whether to fill the dataset with a colour
				    datasetFill : true,

				    // String - A legend template
				    legendTemplate : "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].strokeColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>"

		}
		
		var data = {
			    labels: ["Eating", "Drinking", "Sleeping", "Designing", "Coding", "Cycling", "Running"],
			    datasets: [
			        {
			            label: "My First dataset",
			            fillColor: "rgba(220,220,220,0.2)",
			            strokeColor: "rgba(220,220,220,1)",
			            pointColor: "rgba(220,220,220,1)",
			            pointStrokeColor: "#fff",
			            pointHighlightFill: "#fff",
			            pointHighlightStroke: "rgba(220,220,220,1)",
			            data: [65, 59, 90, 81, 56, 55, 40]
			        },
			        {
			            label: "My Second dataset",
			            fillColor: "rgba(151,187,205,0.2)",
			            strokeColor: "rgba(151,187,205,1)",
			            pointColor: "rgba(151,187,205,1)",
			            pointStrokeColor: "#fff",
			            pointHighlightFill: "#fff",
			            pointHighlightStroke: "rgba(151,187,205,1)",
			            data: [28, 48, 40, 19, 96, 27, 100]
			        }
			    ]
			};
		
		var myRadarChart = new Chart(ctx).Radar(data, options);
	 
		
		 	
			

	}

	
	
}