$(document).ready(function () {
	/*====================================
    METIS MENU 
    ======================================*/
    $('#main-menu').metisMenu({
    	toggle: false
    });
    /*====================================
    	Active Menu
    ======================================*/
    	var url = window.location;
        $('#main-menu li a[href="'+ url +'"]').addClass('active-menu');
        // Will also work for relative and absolute hrefs
	     $('#main-menu li a').filter(function() {
	         return this.href == url;
	     }).addClass('active-menu');

         /*====================================
           LOAD APPROPRIATE MENU BAR
        ======================================*/
         $(window).bind("load resize", function () {
             if ($(this).width() < 768) {
                 $('div.sidebar-collapse').addClass('collapse')
             } else {
                 $('div.sidebar-collapse').removeClass('collapse')
             }
         });    


	     
    });


