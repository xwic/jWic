{
    afterUpdate : function PageAfterUpdate(){
       JWic.$('ctrl_$control.controlID').find('#$control.controlID').page();
       jQuery.mobile.initializePage();
       jQuery.mobile.loading("hide");
    }
}
