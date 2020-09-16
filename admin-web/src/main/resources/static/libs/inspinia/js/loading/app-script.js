$(function () {
    "use strict";

    $(window).on('load', function () {
        $('#side-menu a[href="' + window.location.pathname + '"]').parent("li").addClass("active").parents(".collapse").slideDown().parents(".menu-title").addClass("active");

        $('#pageloader-overlay').fadeOut(1000);
    });

});