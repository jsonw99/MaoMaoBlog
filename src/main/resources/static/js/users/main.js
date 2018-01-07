"use strict";
//# sourceURL=main.js
 
// execute after loading DOM
$(function() {
	
	var _pageSize; // 5, 10, 20, or 50, the variable for displaying the page.
	
	// get the user list according to key nameKeyword, pageIndex, and pageSize.
	function getUersByName(pageIndex, pageSize) {
		 $.ajax({ 
			 url: "/users", 
			 contentType : 'application/json',
			 data:{
				 "async":true, 
				 "pageIndex":pageIndex,
				 "pageSize":pageSize,
				 "name":$("#searchName").val()
			 },
			 success: function(data){
				 $("#mainContainer").html(data);
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	}

    // monitor the changes of the page navigator ("templates/fragments/page.html"), and control the splits of the pages.
    // details see "static/js/usethymeleaf-bootstrap-paginator.js"
	$.tbpage("#mainContainer", function (pageIndex, pageSize) {
		getUersByName(pageIndex, pageSize);
		_pageSize = pageSize;
	});
   
	// search by user name.
	$("#searchNameBtn").click(function() {
		getUersByName(0, _pageSize);
	});
	
	// get the add user page.
	$("#addUser").click(function() {
		$.ajax({ 
			 url: "/users/add", 
			 success: function(data){
				 $("#userFormContainer").html(data);
		     },
		     error : function(data) {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// get the edit user page.
	$("#rightContainer").on("click",".blog-edit-user", function () { 
		$.ajax({ 
			 url: "/users/edit/" + $(this).attr("userId"), 
			 success: function(data){
				 $("#userFormContainer").html(data);
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// submit the form，and clear the form content.
	$("#submitEdit").click(function() {
		$.ajax({ 
			 url: "/users", 
			 type: 'POST',
			 data:$('#userForm').serialize(),
			 success: function(data){
				 $('#userForm')[0].reset();  
				 
				 if (data.success) {
					 // refresh the page.
					 getUersByName(0, _pageSize);
				 } else {
					 toastr.error(data.message);
				 }

		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// delete the user.
	$("#rightContainer").on("click",".blog-delete-user", function () { 
		// in the thymeleaf template, the GET and POST requests can handle the CSRF verification automatically.
		// for the PUT and DELETE request, the CSRF Token need add to the ajax manually.
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");

		$.ajax({ 
			 url: "/users/" + $(this).attr("userId") , 
			 type: 'DELETE', 
			 beforeSend: function(request) {
                 request.setRequestHeader(csrfHeader, csrfToken); // add the CSRF Token into the http request.
             },
			 success: function(data){
				 if (data.success) {
					 // refresh the page.
					 getUersByName(0, _pageSize);
				 } else {
					 toastr.error(data.message);
				 }
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
});