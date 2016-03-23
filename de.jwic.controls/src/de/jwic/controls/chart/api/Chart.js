#set($fld = $control.getField("content"))
{

	/**
	 * Invoked after the DOM element was updated. This function is NOT updated
	 * if the custom doUpdate function returned true.
	 */
	afterUpdate: function(element) {
		var config = $control.getConfigurationJSON();
		var options = $control.buildJsonOptions();
		var labelData = $control.model.labelsJson;
		var dataset = $control.model.datasetsJson;
		if (options.chartType=="overlay")
			var yaxes = $control.model.yaxesJson;
		else
			var yaxes = null;
		
		JWic.controls.Chart.initialize('$control.controlID', options, config, labelData, dataset, yaxes);
	}

}