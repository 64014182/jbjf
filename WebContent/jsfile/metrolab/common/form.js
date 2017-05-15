var common_form = function() {
	"use strict";

	var initSqlComponent = function() {
		$(".sqlComponent").each(function() {
			var sqlNo = $(this).attr("sqlNo");
			var domId = $(this).attr("id");
			var selectValue = $(this).attr("selectValue");
			common_ajax.ajaxFuncSqlComponent(domId, sqlNo,selectValue);
		});
	}

	return {
		initSqlComponent : function() {
			initSqlComponent();
		}
	};

}();
