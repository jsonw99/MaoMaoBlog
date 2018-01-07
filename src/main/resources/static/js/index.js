"use strict";
// sourceURL=index.js

// execute after loading DOM
$(function () {

    var _pageSize; // 5, 10, 20, or 50, the variable for displaying the page.

    // get the blog list according to key indexKeyword, pageIndex, and pageSize.
    function getBlogsByName(pageIndex, pageSize) {
        $.ajax({
            url: "/blogs",
            contentType: 'application/json',
            data: {
                "async": true,
                "pageIndex": pageIndex,
                "pageSize": pageSize,
                "keyword": $("#indexkeyword").val()
            },
            success: function (data) {
                $("#mainContainer").html(data);

                var keyword = $("#indexkeyword").val();

                // if search by key word, then remove the highlight style on the "hottest" and "newest" button.
                if (keyword.length > 0) {
                    $(".nav-item .nav-link").removeClass("active");
                }
            },
            error: function () {
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
    $("#indexsearch").click(function () {
        getBlogsByName(0, _pageSize);
    });

    // switch between the hottest and the newest search.
    $(".nav-item .nav-link").click(function () {

        var url = $(this).attr("url");

        // firstly move the style on both buttons, then add active style on the selected one.
        $(".nav-item .nav-link").removeClass("active");
        $(this).addClass("active");

        // reload the blogs into the right side bar
        $.ajax({
            url: url + '&async=true',
            success: function (data) {
                $("#mainContainer").html(data);
            },
            error: function () {
                toastr.error("error!");
            }
        })

        // clear the content in the search bar.
        $("#indexkeyword").val('');
    });


});