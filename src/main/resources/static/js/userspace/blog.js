"use strict";
//# sourceURL=blog.js

// execute after loading DOM
$(function() {
	$.catalog("#catalog", ".post-content");
	
	// delete the blog.
	$(".blog-content-container").on("click",".blog-delete-blog", function () { 
		// get the CSRF Token.
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");

		$.ajax({
			 url: blogUrl, 
			 type: 'DELETE', 
			 beforeSend: function(request) {
                 request.setRequestHeader(csrfHeader, csrfToken); // add CSRF Token to the request.
             },
			 success: function(data){
				 if (data.success) {
					 // redirect the page.
					 window.location = data.body;
				 } else {
					 toastr.error(data.message);
				 }
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// get the comment list.
	function getCommnet(blogId) {
		// get the CSRF Token.
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		
		$.ajax({ 
			 url: '/comments', 
			 type: 'GET', 
			 data:{"blogId":blogId},
			 beforeSend: function(request) {
	             request.setRequestHeader(csrfHeader, csrfToken); // add CSRF Token to the request.
	         },
			 success: function(data){
				$("#mainContainer").html(data);
	
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	}
	
	// add the comment.
	$(".blog-content-container").on("click","#submitComment", function () {
        // get the CSRF Token.
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
 		
		$.ajax({ 
			 url: '/comments', 
			 type: 'POST', 
			 data:{"blogId":blogId, "commentContent":$('#commentContent').val()},
			 beforeSend: function(request) {
                 request.setRequestHeader(csrfHeader, csrfToken); // add CSRF Token to the request.
             },
			 success: function(data){
				 if (data.success) {
					 // reset the comment box.
					 $('#commentContent').val('');
					 // refresh the comment list.
					 getCommnet(blogId);
				 } else {
					 toastr.error(data.message);
				 }
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// delete the comment.
	$(".blog-content-container").on("click",".blog-delete-comment", function () { 
		// get the CSRF Token.
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
 		
		$.ajax({ 
			 url: '/comments/'+$(this).attr("commentId")+'?blogId='+blogId, 
			 type: 'DELETE', 
			 beforeSend: function(request) {
                 request.setRequestHeader(csrfHeader, csrfToken); // add CSRF Token to the request.
             },
			 success: function(data){
				 if (data.success) {
					 // refresh the comment list.
					 getCommnet(blogId);
				 } else {
					 toastr.error(data.message);
				 }
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	
	// add the vote.
	$(".blog-content-container").on("click","#submitVote", function () { 
		// get the CSRF Token.
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
 		
		$.ajax({ 
			 url: '/votes', 
			 type: 'POST', 
			 data:{"blogId":blogId},
			 beforeSend: function(request) {
                 request.setRequestHeader(csrfHeader, csrfToken); // add CSRF Token to the request.
             },
			 success: function(data){
				 if (data.success) {
					 toastr.info(data.message);
					 // redirect the page.
					 window.location = blogUrl;
				 } else {
					 toastr.error(data.message);
				 }
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// delete the vote.
	$(".blog-content-container").on("click","#cancelVote", function () { 
		// get the CSRF Token.
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
 		
		$.ajax({ 
			 url: '/votes/'+$(this).attr('voteId')+'?blogId='+blogId, 
			 type: 'DELETE', 
			 beforeSend: function(request) {
                 request.setRequestHeader(csrfHeader, csrfToken); // add CSRF Token to the request.
             },
			 success: function(data){
				 if (data.success) {
					 toastr.info(data.message);
                     // redirect the page.
					 window.location = blogUrl;
				 } else {
					 toastr.error(data.message);
				 }
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// for initialize, get the list of comments after loading the blog.
	getCommnet(blogId);
	
});