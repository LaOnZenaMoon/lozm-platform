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
            if (options.callback.success) {
                options.callback.success(resData);
            }
        };

        ajaxOptions.error = function(xhr, status, error) {
            if (options.callback.error) {
                alertFail();
                options.callback.error(xhr, status, error);
            }
        };

        ajaxOptions.complete = function() {
            hideLoadingBar();

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

    lozm.func.setDetail = function (_id, _data) {
        if(isNotEmpty(_data)) {
            for(var _idx in _data) {
                $("#"+_id+_idx).val(_data[_idx]);
            }
        }
    };

    lozm.func.changeFileLabel = function(_targetId) {
        $("#"+_targetId).change(function(){
            $("#"+_targetId).next(".custom-file-label").text(this.files[0].name);
        });
    };

    lozm.func.checkFileType = function(_targetId, _fileType) {
        var _fileName = $("#"+_targetId).next(".custom-file-label").text();
        if(_fileName.toLowerCase().indexOf(_fileType.toLowerCase()) == -1) return true;
        return false;
    };

    lozm.func.requestMultipartFormData = function (options) {
        setRequestMultipartFormData(options);
    };

    var setRequestMultipartFormData = function (options) {
        var ajaxOptions = $.extend({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "",
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
        }, options);

        ajaxOptions.data = options.data;

        requestAjax(ajaxOptions);
    };

    lozm.func.alertSuccess = function() {
        swal("Success", "Succeed to process the data.", "success");
    };

    var alertFail = lozm.func.alertFail = function() {
        swal("Error", "Failed to process the data. Please check or contact to administrator.", "error");
    };

    lozm.func.alertWarning = function(_contents) {
        swal("Warning", _contents, "warning");
    };

    lozm.func.alertRowIsSelected = function() {
        swal("Error", "At least select one row.", "error");
    };

    lozm.func.alertRowsAreSelected = function() {
        swal("Error", "Cannot select more then one row.", "error");
    };

    lozm.func.datetimepicker = function(_id) {
        $("#"+_id).datetimepicker({
            format:'yyyy-mm-ddThh:ii:ss',
        });
    }

    init.prototype = lozm.func;
    if (!noGlobal) {
        window.lozm = lozm;
    }

    return lozm;
});