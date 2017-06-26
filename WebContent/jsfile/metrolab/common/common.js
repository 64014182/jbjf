/**
 * 公共通用的js方法集合
 */
var common_common = function() {
	
	/**
	 * 全选、反选
	 * @param id
	 * @param checked
	 */
	var checkFunc = function(id, checked){
		$("#" + id + " input[name='checkIds']").each(function(){
			$(this).prop("checked", checked);
	    });
	};
	
	/**
	 * 全选、反选
	 * @param id
	 */
	var getCheckFunc = function(id){
		var ids = "";
		$("#" + id + " input[name='checkIds']").each(function(){
			if($(this)[0].checked == true)
			ids += $(this).val() + ",";
	    });
		return ids;
	};
	
	/**
	 * 全选、反选
	 * @param id
	 */
	var getCheckComponetFunc = function(id){
		var checks = new Array();
		$("#" + id + " input[name='checkIds']").each(function(){
			if($(this)[0].checked == true)
			checks.push($(this));
	    });
		return checks;
	};
	
	return {
		checkFunc : checkFunc,
		getCheckFunc : getCheckFunc,
		getCheckComponetFunc:getCheckComponetFunc
	};
	
}();
