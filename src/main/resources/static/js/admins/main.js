"use strict";
//# sourceURL=main.js

// execute after loading DOM
$(function() {

	// the event of left side bar (selection about the menu).
	$(".blog-menu .list-group-item").click(function() {
 
		var url = $(this).attr("url");
		
		// first remove the style of the other buttons, then add style to the current button.
		$(".blog-menu .list-group-item").removeClass("active");
		$(this).addClass("active");  
 
		// update the result to the right side bar.
		 $.ajax({ 
			 url: url, 
			 success: function(data){
				 $("#rightContainer").html(data);
		 },
		 error : function() {
		     alert("error");
		     }
		 });
	});
	
	
	// by default, choose the first button of the menu list.
	 $(".blog-menu .list-group-item:first").trigger("click");
});