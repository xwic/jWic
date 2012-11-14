{
	afterUpdate: function(element) {	
		var menu =  jQuery('#' + JQryEscape("${control.controlID}_menu"));
		menu.menu();
		
		function showMenu() {
			menu.show();
		}
		jQuery('#' + JQryEscape("btn_${control.button.controlID}")).click(showMenu);
		
		function hideMenu() {
			menu.menu("collapseAll", null, true );
			setTimeout(function(){menu.hide()}, 310); // colapseAll has a delay and if we hide immediately, it's not executed.
		}
		menu.mouseleave(hideMenu);
		menu.click(hideMenu);
		jQuery('#'+ JQryEscape("${control.controlID}_menu")+' li a *').click(hideMenu);		
	}
}