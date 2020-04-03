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