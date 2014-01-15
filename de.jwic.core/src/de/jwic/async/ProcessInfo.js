{
	/**
	 * Invoked after the DOM element was updated. This function is NOT updated if
	 * the custom doUpdate function returned true.
	 */
	afterUpdate: function(element) {
		JWic.controls.ProcessInfo.initialize('$control.controlID', {
			#if($control.progressMonitor)
				value : $control.getPercent(),
			#else
				empty : true,
			#end
				active : $control.active,
				showPercentage : $control.showPercentage,
				showValues : $control.showValues,
				compactView : $control.compactView
		});
	}
}