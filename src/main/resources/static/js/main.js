"use strict";
//# sourceURL=main.js

// execute after loading DOM
$(function () {

    // the event of return to the top button.
    NProgress.start();

    $(window).scroll(function () {  // active when the window scrolling happens
        var scrollt = document.documentElement.scrollTop + document.body.scrollTop; // get the height after the scrolling.
        if (scrollt > 200) {  // if the height > 200px, then show the button.
            $("#goToTop").fadeIn(400);
        } else {
            $("#goToTop").stop().fadeOut(400); // if the height <= 200px, then firstly stop the previous animate, and then vanish.
        }
    });
    $("#goToTop").click(function () { // when click, the animate will scroll to the top in 200 milliseconds.
        $("html,body").animate({scrollTop: "0px"}, 200);
    });
    NProgress.done();
});