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

    var constantValue = {
        LOADING_BAR: "_LOADING_BAR"
    };

    var loadingBar = $("<div/>")
        .attr("id", constantValue.LOADING_BAR)
        .addClass("loading-area")
        .append(
            $("<div/>")
                .addClass("loading")
                .append(
                    $("<img/>").attr("src", "\\resources\\images\\loadingBar.gif")
                )
        );

    var showLoadingBar = lozm.func.showLoadingBar = function() {
        var _body = $("body");
        if (isEmpty(_body.find("[id='" + constantValue.LOADING_BAR + "']"))) {
            _body.append(loadingBar);
        }
    };

    var hideLoadingBar = lozm.func.hideLoadingBar = function() {
        var _body = $("body");
        if (isNotEmpty(_body.find("[id='" + constantValue.LOADING_BAR + "']"))) {
            loadingBar.remove();
        }
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

    //TODO
    //1. 파라미터에 의해서 버튼 숨김 처리
    lozm.func.initGrid = function (_ids, _urls, _columns) {
        $("#"+_ids.grid).DataTable({
            dom: 'Bftrip',
            ajax: _urls.get,
            columns: _columns,
            select: {
                style: 'multi',
                selector: 'td:first-child'
            },
            buttons: [
                {
                    text: '<i class="fa fa-check"></i>',
                    action: function () {
                        var _selectedRows = table.rows({selected: true}).data().toArray();
                        if(_selectedRows.length > 0) table.rows().deselect();
                        else table.rows().select();
                    }
                },
                {
                    //_urls.post
                    text: '<i class="fa fa-plus"></i>',
                    action: function () {
                        var _selectedRows = table.rows({selected: true}).data().toArray();
                        for (var _idx in _selectedRows) {
                            console.log(_selectedRows[_idx].id);
                        }
                    },
                    attr: {
                        "data-toggle": "modal",
                        "data-target": "#"+_ids.post
                    }
                },
                {
                    //_urls.put
                    text: '<i class="fa fa-refresh"></i>',
                    action: function () {
                        var _selectedRows = table.rows({selected: true}).data().toArray();
                        if(_selectedRows.length < 1) {
                            alert("At least select one row.");
                            return;
                        }
                    },
                    attr: {
                        "data-toggle": "modal",
                        "data-target": "#"+_ids.put
                    }
                },
                {
                    //_urls.delete
                    text: '<i class="fa fa-minus"></i>',
                    action: function () {
                        var _selectedRows = table.rows({selected: true}).data().toArray();
                        if(_selectedRows.length < 1) {
                            alert("At least select one row.");
                            return;
                        }
                        if(confirm("Are you sure to delete the data?")) console.log("Deleted.");
                    },
                },
            ],
        });
    };


    init.prototype = lozm.func;
    if (!noGlobal) {
        window.lozm = lozm;
    }

    return lozm;
});