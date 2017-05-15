/**
 * 窗口操作
 */
var platform_windows = function() {
	"use strict";

	/**
	 * 关闭窗口
	 */
	var close = function (){
		top.window.opener = null;
		top.window.open('', '_self');
		top.window.close();
	};
	
	/**
	 * 打开新窗口url，还可以指定一个form作为参数
	 */
	var open = function(url, formId){
		if(url.indexOf("?") == -1){
			url = url + "?localePram=" + localePram;
		}else{
			url = url + "&localePram=" + localePram;
		}
		
		if(formId != undefined && formId != null){
			var data = $("#" + formId).formToArray();//console.log(data);
			for (var index in data) {//console.log(index);
				if(data[index].value != ""){
					url += "&" + data[index].name + "=" + encodeURI(encodeURI(data[index].value));
				}
			}
		}//console.log(url);
		
		window.open(url);
	};

	/**
	 * 打开新窗口url，还可以指定一个form作为参数
	 */
	var openSelectTable = function(url, tableId){
		if(tableId != undefined && tableId != null){
			if(url.indexOf("?") == -1){
				url = url + "?localePram=" + localePram;
			}else{
				url = url + "&localePram=" + localePram;
			}
			
			var units = new Array();
			var ids = "";
			$("#" + tableId + " input[name='checkIds']").each(function(index){
				if($(this)[0].checked == true){
					ids += $(this).val() + ",";
					var unit = $($(".orderUniteVali")[index]).text();
					units.push(unit);
				}
		    });

			if (ids == "") {
				toastr.error("必须选择至少一条记录！");
				return;
			}
			if(!isSam(units)){
				toastr.error("选择要导出的数据订货单位名称不一致！");
				return;
			}
			
			url+="&ids=" + encodeURI(encodeURI(ids));
			url+="&orderUnit=" + encodeURI(encodeURI(units[0]));
			window.open(url);
		}
	};
	
	function isSam(units){
		var valUnit = "";
		if (units.length > 1) {
			valUnit = units[0];
			for (var i = 1; i < units.length; i++) {
				if( valUnit != units[i]){
					return false;
				}
			}
		}
		return true;
	}
	
	return {
		close : close,
		open : open,
		openSelectTable:openSelectTable
	};
	
}();

