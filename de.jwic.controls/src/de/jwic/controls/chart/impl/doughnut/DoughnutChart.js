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


		}

			
			var options = {
				 // Boolean - Whether we should show a stroke on each segment
			    segmentShowStroke : true,

			    // String - The colour of each segment stroke
			    segmentStrokeColor : "#fff",

			    // Number - The width of each segment stroke
			    segmentStrokeWidth : 2,

			    // Number - The percentage of the chart that we cut out of the
				// middle
			    percentageInnerCutout : 50, // This is 0 for Pie charts

			    // Number - Amount of animation steps
			    animationSteps : 100,

			    // String - Animation easing effect
			    animationEasing : "easeOutBounce",

			    // Boolean - Whether we animate the rotation of the Doughnut
			    animateRotate : true,

			    // Boolean - Whether we animate scaling the Doughnut from the
				// centre
			    animateScale : false,

			    // String - A legend template
			    legendTemplate : "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>"

			};
			
		var data = [
		            {
		                value: 300,
		                color:"#F7464A",
		                highlight: "#FF5A5E",
		                label: "Red"
		            },
		            {
		                value: 50,
		                color: "#46BFBD",
		                highlight: "#5AD3D1",
		                label: "Green"
		            },
		            {
		                value: 100,
		                color: "#FDB45C",
		                highlight: "#FFC870",
		                label: "Yellow"
		            }
		        ]
		var myDoughnutChart = new Chart(ctx).Doughnut(data,options);


	}

	
	
}