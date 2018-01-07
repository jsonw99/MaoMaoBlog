/*!
 * avatar JS.
 *
 */

"use strict";
//# sourceURL=avatar.js

// execute after loading DOM
$(function () {
    var avatarApi;

    // get the edit avatar page.
    $(".blog-content-container").on("click", ".blog-edit-avatar", function () {
        avatarApi = "/u/" + $(this).attr("userName") + "/avatar";
        $.ajax({
            url: avatarApi,
            success: function (data) {
                $("#avatarFormContainer").html(data);
            },
            error: function () {
                toastr.error("error!");
            }
        });
    });

    /**
     * the base-64 encoding image data is represented in url.
     * convert it into Blob(Binary Large OBject).
     *
     * @param urlData
     *
     */
    function convertBase64UrlToBlob(urlData) {

        var bytes = window.atob(urlData.split(',')[1]); //remove the head of the url, and decode into bytes.

        var ab = new ArrayBuffer(bytes.length);
        var ia = new Uint8Array(ab);
        for (var i = 0; i < bytes.length; i++) {
            ia[i] = bytes.charCodeAt(i);
        }

        return new Blob([ab], {type: 'image/png'});
    }

    // add user avatar
    $("#submitEditAvatar").on("click", function () {
        var form = $('#avatarformid')[0];
        var formData = new FormData(form);   // here we need a form to handle the POST query.
        var base64Codes = $(".cropImg > img").attr("src");
        formData.append("file", convertBase64UrlToBlob(base64Codes));  //append the image file to the formData.

        // upload the avatar image to the file server.
        $.ajax({
            url: 'http://localhost:8081/upload',
            type: 'POST',
            cache: false,
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {

                var avatarUrl = data;

                // get the CSRF Token.
                var csrfToken = $("meta[name='_csrf']").attr("content");
                var csrfHeader = $("meta[name='_csrf_header']").attr("content");
                // save the avatarUrl to the database.
                $.ajax({
                    url: avatarApi,
                    type: 'POST',
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify({"id": Number($("#userId").val()), "avatar": avatarUrl}),
                    beforeSend: function (request) {
                        request.setRequestHeader(csrfHeader, csrfToken); // add the CSRF Token to the query.
                    },
                    success: function (data) {
                        if (data.success) {
                            // replace the avatar source.
                            $(".blog-avatar").attr("src", data.avatarUrl);
                        } else {
                            toastr.error("error!" + data.message);
                        }

                    },
                    error: function () {
                        toastr.error("error!");
                    }
                });
            },
            error: function () {
                toastr.error("error!");
            }
        })
    });


});