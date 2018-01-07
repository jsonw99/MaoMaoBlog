/*!
 * u main JS.
 *
 */
"use strict";
//# sourceURL=u.js

// execute after loading DOM
$(function() {
	 
	var _pageSize; // 5, 10, 20, or 50, the variable for displaying the page.

    // get the blog list according to catalogId, keyword, pageIndex, and pageSize.
	function getBlogsByName(pageIndex, pageSize) {
		 $.ajax({ 
			 url: "/u/"+  username  +"/blogs", 
			 contentType : 'application/json',
			 data:{
				 "async":true, 
				 "pageIndex":pageIndex,
				 "pageSize":pageSize,
				 "catalog":catalogId,
				 "keyword":$("#keyword").val()
			 },
			 success: function(data){
				 $("#mainContainer").html(data);

                 // if search by key word, then remove the highlight style on the "hottest" and "newest" button.
				 if (catalogId) {
					$(".nav-item .nav-link").removeClass("active");
				 }
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	}

    // monitor the changes of the page navigator ("templates/fragments/page.html"), and control the splits of the pages.
    // details see "static/js/usethymeleaf-bootstrap-paginator.js"
	$.tbpage("#mainContainer", function (pageIndex, pageSize) {
		getBlogsByName(pageIndex, pageSize);
		_pageSize = pageSize;
	});

    // search by the key word.
	$("#searchBlogs").click(function() {
		getBlogsByName(0, _pageSize);
	});

    // switch between the hottest and the newest search.
	$(".nav-item .nav-link").click(function() {
 
		var url = $(this).attr("url");

        // firstly move the style on both buttons, then add active style on the selected one.
		$(".nav-item .nav-link").removeClass("active");
		$(this).addClass("active");

        // reload the blogs into the right side bar
		 $.ajax({ 
			 url: url+'&async=true', 
			 success: function(data){
				 $("#mainContainer").html(data);
			 },
			 error : function() {
				 toastr.error("error!");
			 }
		 })

        // clear the content in the search bar.
		 $("#keyword").val('');
	});
	
	
	// load the catalogs.
	function getCatalogs(username) {
		$.ajax({ 
			 url: '/catalogs', 
			 type: 'GET', 
			 data:{"username":username},
			 success: function(data){
				$("#catalogMain").html(data);
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	}
	
	
	// get the catalog edit page.
	$(".blog-content-container").on("click",".blog-add-catalog", function () { 
		$.ajax({ 
			 url: '/catalogs/edit', 
			 type: 'GET', 
			 success: function(data){
				 $("#catalogFormContainer").html(data);
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// get the specific catalog edit page.
	$(".blog-content-container").on("click",".blog-edit-catalog", function () { 
 
		$.ajax({ 
			 url: '/catalogs/edit/'+$(this).attr('catalogId'), 
			 type: 'GET', 
			 success: function(data){
				 $("#catalogFormContainer").html(data);
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// add the catalog.
	$("#submitEditCatalog").click(function() {
		// get the CSRF Token.
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
 		
		$.ajax({ 
			 url: '/catalogs', 
			 type: 'POST', 
			 contentType: "application/json; charset=utf-8",
			 data:JSON.stringify({"username":username, "catalog":{"id":$('#catalogId').val(), "name":$('#catalogName').val()}}),
			 beforeSend: function(request) {
                 request.setRequestHeader(csrfHeader, csrfToken); // add the CSRF Token into the query.
             },
			 success: function(data){
				 if (data.success) {
					 toastr.info(data.message);
					 // refresh the catalog list.
					 getCatalogs(username);
				 } else {
					 toastr.error(data.message);
				 }
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// delete the catalog.
	$(".blog-content-container").on("click",".blog-delete-catalog", function () { 
		// get the CSRF Token.
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
 		
		$.ajax({ 
			 url: '/catalogs/'+$(this).attr('catalogid')+'?username='+username, 
			 type: 'DELETE', 
			 beforeSend: function(request) {
                 request.setRequestHeader(csrfHeader, csrfToken); // add the CSRF Token into the query.
             },
			 success: function(data){
				 if (data.success) {
					 toastr.info(data.message);
					 // refresh the catalog list.
					 getCatalogs(username);
				 } else {
					 toastr.error(data.message);
				 }
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// set the catalogId, and search by catalog.
	$(".blog-content-container").on("click",".blog-query-by-catalog", function () { 
		catalogId = $(this).attr('catalogId');
		getBlogsByName(0, _pageSize);
	});
	

	// initialize the catalog list (left side bar).
	getCatalogs(username);
 
});