//ValidatedInputBox
{
	afterUpdate:function(){
		#if($control.visible)
			var options = $control.buildJsonOptions();
			var control = JWic.$('${control.controlID}');
			JWic.controls.ValidatedInputBox.initialize(control,options);
		#end
	}
}
