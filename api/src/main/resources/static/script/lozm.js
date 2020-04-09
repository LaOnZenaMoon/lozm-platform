(function (global, factory) {
    "use strict";

    if (typeof module === "object" && typeof module.exports === "object") {
        module.exports = global.document ? factory(global, true) : function (w) {
            if (!w.document) {
                throw new Error("lozm DataTable script Javascript requires a window with a document.");
            }
            return factory(w);
        }
    } else {
        factory(global);
    }
})(typeof window !== "undefined" ? window : this, function (window, noGlobal) {
    "use strict";

    var document = window.document;
    var version = "0.0.1";
    var lozm = function (selector) {
        return new lozm.func.init(selector);
    };

    lozm.func = lozm.prototype = {
        version: version
        , constructor: lozm
        , length: 0
    };

    var init = lozm.func.init = function (selector) {
        if (!selector) {
            return this;
        }

        var domList = document.querySelectorAll(selector);
        for (var i = 0; i < domList.length; i++) {
            this[i] = domList[i];
        }

        this.length = domList.length;
        return this;
    };

    var isEmpty = lozm.func.isEmpty = function isEmpty(value) {
        return value === "" || value === null || value === undefined || typeof value === "undefined" || (typeof value === "object" && !Object.keys(value).length) || (value != null && typeof value === "object" && value.length === 0);
    };

    var isNotEmpty = lozm.func.isNotEmpty = function isNotEmpty(value) {
        return !isEmpty(value);
    };

    var requestAjax = function(ajaxOptions) {
        if (isEmpty(ajaxOptions.showLoadingBar) || ajaxOptions.showLoadingBar) {
            showLoadingBar();
        }

        ajaxDefault(ajaxOptions);
        $.ajax(ajaxOptions);
        ajaxDefault();
    };

    var ajaxDefault = function(options) {
        var ajaxOptions = $.extend({
            url: null
            , type: "GET"
            , data: null
            , dataType: null
            , contentType: "application/x-www-form-urlencoded;"
            , async: true
            , success: null
            , headers: null
            , beforeSend: function(xhr) {
                // xhr.setRequestHeader(tokenHeader, token);
            }
        }, options);

        ajaxOptions.success = function(resData) {
            // success 함수가 있을 때 실행되는 코드
            if (options.callback.success) {
                options.callback.success(resData);
            }
        };

        ajaxOptions.error = function(xhr, status, error) {
            // error 함수가 있을 때 실행되는 코드
            if (options.callback.error) {
                options.callback.error(xhr, status, error);
            }
        };

        ajaxOptions.complete = function() {
            hideLoadingBar();

            // complete 함수가 있을 때 실행되는 코드
            if (options.callback.complete) {
                options.callback.complete();
            }
        };

        $.ajaxSetup(ajaxOptions);
    };


    var showLoadingBar = lozm.func.showLoadingBar = function() {
        $("#pageloader-overlay").show();
    };

    var hideLoadingBar = lozm.func.hideLoadingBar = function() {
        $("#pageloader-overlay").hide();
    };

    lozm.func.requestGet = function(options) {
        var ajaxOptions = $.extend({
            type: "GET"
            , contentType: "application/x-www-form-urlencoded; charset=UTF-8"
            , async: true
        }, options);

        if (isNotEmpty(options.data)) {
            ajaxOptions.data = $.param(options.data);
        }

        requestAjax(ajaxOptions);
    };

    lozm.func.requestPost = function(options) {
        var ajaxOptions = $.extend({
            type: "POST"
            , dataType: "json"
            , contentType: "application/json; charset=UTF-8"
            , async: true
        }, options);

        ajaxOptions.data = JSON.stringify(isNotEmpty(options.data) ? options.data : {});

        requestAjax(ajaxOptions);
    };

    lozm.func.requestPut = function(options) {
        var ajaxOptions = $.extend({
            type: "PUT"
            , dataType: "json"
            , contentType: "application/json; charset=UTF-8"
            , async: true
        }, options);

        ajaxOptions.data = JSON.stringify(isNotEmpty(options.data) ? options.data : {});

        requestAjax(ajaxOptions);
    };

    lozm.func.requestDelete = function(options) {
        var ajaxOptions = $.extend({
            type: "DELETE"
            , dataType: "json"
            , contentType: "application/json; charset=UTF-8"
            , async: true
        }, options);

        ajaxOptions.data = JSON.stringify(isNotEmpty(options.data) ? options.data : {});

        requestAjax(ajaxOptions);
    };

    init.prototype = lozm.func;
    if (!noGlobal) {
        window.lozm = lozm;
    }

    return lozm;
});