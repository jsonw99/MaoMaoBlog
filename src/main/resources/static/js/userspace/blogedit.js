/*!
 * blogedit.html
 *
 */
"use strict";
//# sourceURL=blogedit.js

// execute after loading DOM
$(function() {
 
	// initialize the markdown editor. ("js/thinker-md.vendor.js")
    $("#md").markdown({
        // language: 'zh',
        fullscreen: {
            enable: true
        },
        resize:'vertical',
        localStorage:'md',
        imgurl: 'http://localhost:8081', // the server to save images.
        base64url: 'http://localhost:8081'
    });
 

    
    // initialize the catalog selection box. ("js/chosen.jquery.js")
    $('.form-control-chosen').chosen();
    
    
    // initialize the tag input box. ("js/bootstrap-tagsinput.js")
    $('.form-control-tag').tagsInput({
    	'defaultText':'input tags'
    });
 
 	$("#uploadImage").click(function() {
		$.ajax({
		    url: 'http://localhost:8081/upload',
		    type: 'POST',
		    cache: false,
		    data: new FormData($('#uploadformid')[0]),
		    processData: false,
		    contentType: false,
		    success: function(data){
		    	var mdcontent=$("#md").val();
		    	 $("#md").val(mdcontent + "\n![]("+data +") \n"); // add the returned string at the end of the article content.
	         }
		}).done(function(res) {
			$('#file').val(''); // reset the file selector.
		}).fail(function(res) {});
 	})
 
 	// add the blog.
 	$("#submitBlog").click(function() {
 		
		// get the CSRF Token.
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");

		$.ajax({
		    url: '/u/'+ $(this).attr("userName") + '/blogs/edit',
		    type: 'POST',
			contentType: "application/json; charset=utf-8",
		    data:JSON.stringify({"id":$('#blogId').val(), 
		    	"title": $('#title').val(), 
		    	"summary": $('#summary').val() , 
		    	"content": $('#md').val(), 
		    	"catalog":{"id":$('#catalogSelect').val()},
		    	"tags":$('.form-control-tag').val()
		    	}),
			beforeSend: function(request) {
			    request.setRequestHeader(csrfHeader, csrfToken); // add the CSRF Token to the request.
			},
			 success: function(data){
				 if (data.success) {
					// redirect the page.
					 window.location = data.body;
				 } else {
					 toastr.error("error!"+data.message);
				 }
				 
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		})
 	})
 	
 	
});