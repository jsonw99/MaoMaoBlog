/**
 * generator the catalog from an article.
 *
 * suppose we have an article like following
 * // target (the article):
 * <article class="abc">
 *     <h1>this is the heading 1!</h1>
 *     <h2>this is the heading 2!</h2>
 *     <h3>this is the heading 3!</h3>
 * </article>
 *
 * // selector (the place to list the catalog):
 * <div id="catalog"></div>
 *
 * to generate the catalog, initialize the following:
 * $.catalog("#catalog", ".abc");
 *
 */
(function($) {
	
	"use strict";
 
	$.catalog = function(selector, target) {
		
		$(target + " :header").each(function(i,item){ // iterate all the headings
	        var tag = $(item).get(0).localName; // get "h1", "h2", or "h3" ...
	        $(item).attr("id","location"+i); // set the location id.
	        $(selector).append('<a class="new'+tag+'" href="#location'+i+'">'+$(this).text()+'</a></br>'); // append <a>'s on the selector.
	        $(".newh1").css("margin-left",0);
	        $(".newh2").css("margin-left",5);
	        $(".newh3").css("margin-left",10);
	        $(".newh4").css("margin-left",15);
	        $(".newh5").css("margin-left",20);
	        $(".newh6").css("margin-left",25);
	      });
	};

})(jQuery);