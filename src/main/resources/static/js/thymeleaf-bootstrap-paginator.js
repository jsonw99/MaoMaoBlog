
(function($) {
	
	"use strict";
	
	/**
	 * parameters for the handler:
	 * pageIndex pageIndex is the index of current page which displayed, starting from 0.
	 * pageSize is the 10 size of current page, by default is 10.
	 */
	$.tbpage = function(selector, handler) {
		// change the page index.
		$(selector).off("click", ".tbpage-item").on("click", ".tbpage-item", function() {
		 
			var pageIndex = $(this).attr("pageIndex");
 
			var pageSize = $('.tbpage-size option:selected').val();
			// check if the selected page is the current page.
			// proceed only when the selected page is not current page.
			if($(this).parent().attr("class").indexOf("active")>0){ 
				//console.log("the selected page is current page");
			}else{
				handler(pageIndex, pageSize);
			}
 
		});
		
		// change the page size.
		$(selector).off("change", ".tbpage-size").on("change", ".tbpage-size", function() {
			 
			var pageIndex = $(this).attr("pageIndex");
 
			var pageSize = $('.tbpage-size option:selected').val();
 
			handler(pageIndex, pageSize);
		});
	};

})(jQuery);