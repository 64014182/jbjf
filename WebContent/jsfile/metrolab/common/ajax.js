/**
 * Ajax请求方法二次封装
 */
var common_ajax = function() {
	
	/**
	 * ajax请求并返回结果
	 * @param url
	 * @param data
	 * @param callback
	 * @param hasCallbackPara boolean 是否将返回值做为回调函数参数
	 * @returns {String}
	 */
	var ajaxFunc = function(url, data, dataType, callback,hasCallbackPara){
		if(dataType == undefined || dataType == null){
			dataType = "html";
		}
		if(typeof(hasCallbackPara)=="undefined"){ 
			hasCallbackPara = false;
		}
		// 所有请求加上当前语言标示环境
//		if(!data){
//			data = {"localePram" : localePram};
//		}else{
//			data.localePram = localePram;
//		}
		
		var result = "";
		$.ajax({
			type : "post",
			url : encodeURI(encodeURI(cxt + url)),
			headers : {"localePram" : localePram}, // 所有请求加上当前语言标示环境
			data : data,
			dataType : dataType,
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			async: false,
			cache: false,
			success:function(data){
				if(typeof data=='string' && data.indexOf("loginForm") != -1){
					window.location.href = cxt + "/platform/login";
					return;
				}
				result = data;
				//扩展回调函数
				if( callback != null && hasCallbackPara == false){
					callback();
				}else if(callback != null && hasCallbackPara == true){
					callback(result);
				}
			}
		});
		return result;
	};

	/**
	 * ajax请求，执行前进行确认
	 */
	var ajaxFuncConfirm = function(url, data, dataType, callback) {
		bootbox.confirm("确定要这样操作吗？", function(result) {
			if(result){
				ajaxFunc(url, data, dataType, callback);
			}
		});
	};
	
	/**
	 * ajax请求url替换指定div
	 * @param divId 返回替换div
	 * @param url 请求地址
	 * @param data 参数
	 * @param callback 回调
	 */
	var ajaxDiv = function(divId, url, data, callback){
		var result = ajaxFunc(url, data, callback);
		$("#" + divId).html(result);
	};
	
	/**
	 * ajaxForm请求，执行前进行确认
	 */
	var ajaxDivConfirm = function(divId, url, data, callback) {
		bootbox.confirm("确定要这样操作吗？", function(result) {
			if(result){
				ajaxDiv(divId, url, data, callback);
			}
		});
	};
	
	/**
	 * ajax请求url替换主面板内容
	 * @param url 请求地址
	 * @param data 参数
	 * @param callback 回调
	 */
	var ajaxMainPanel = function(url, data, callback){
		var result = ajaxFunc(url, data, callback);
		$("#main-content").html(result);
	};
	
	/**
	 * ajaxForm请求，执行前进行确认
	 */
	var ajaxMainPanelConfirm = function(url, data, callback) {
		bootbox.confirm("确定要这样操作吗？", function(result) {
			if(result){
				ajaxMainPanel(url, data, callback);
			}
		});
	};
	
	/**
	 * ajax提交form求并返回结果
	 * @param divId 返回替换div
	 * @param formId 提交formid
	 * @param callback 回调
	 */
	var ajaxForm = function(formId, dataType, callback){
		if(dataType == undefined || dataType == null){
			dataType = "html";
		}

		var result = "";
		$("#" + formId).ajaxSubmit({
			dataType : dataType,
			async: false,
			cache: false,
			headers : {"localePram" : localePram}, // 所有请求加上当前语言标示环境
			//data: {"localePram" : localePram}, // 所有请求加上当前语言标示环境
		    success:  function (data) {
				if(data.indexOf("loginForm") != -1){
					window.location.href = cxt + "/platform/login";
					return;
				}
		    	result = data;
		    	
				//扩展回调函数
				if( callback != null ){
					callback();
				}
		    }
		});
		return result;
	};
	
	/**
	 * ajax提交form求并返回结果
	 * @param divId 返回替换div
	 * @param formId 提交formid
	 * @param callback 回调
	 */
	var ajaxExportForm = function(formId, dataType, callback){
		if(dataType == undefined || dataType == null){
			dataType = "html";
		}

		var result = "";
		var url = $("#" + formId).attr("exportUrl");
		$("#" + formId).ajaxSubmit({
			url:url,
			dataType : dataType,
			async: false,
			cache: false,
			headers : {"localePram" : localePram}, // 所有请求加上当前语言标示环境
			//data: {"localePram" : localePram}, // 所有请求加上当前语言标示环境
		    success:  function (data) {
				if(data.indexOf("loginForm") != -1){
					window.location.href = cxt + "/platform/login";
					return;
				}
		    	result = data;
		    	
				//扩展回调函数
				if( callback != null ){
					callback();
				}
		    }
		});
		return result;
	};
	
	/**
	 * ajax提交form求并返回结果
	 * @param divId 返回替换div
	 * @param formId 提交formid
	 * @param callback 回调
	 */
	var ajaxFormExcel = function(formId, tableId,dataType, callback){
		if(dataType == undefined || dataType == null){
			dataType = "html";
		}
		
//		var tableHeads = [];
//		$("#" + tableId + " thead tr:eq(0) th").each(
//			function(){
//				var headText = $(this).html();
//				console.log(headText.indexOf("checkbox") == -1  );
//				if( headText.indexOf("checkbox") == -1 || headText.indexOf("操作")  == -1){
//					tableHeads.push(headText);
//				}
//			}
//		);
		//console.log(tableHeads);
		
		var url = $("#" + formId).attr("action") + "/exportExcel";
		var result = "";
		$("#" + formId).ajaxSubmit({
			dataType : dataType,
			url:url,
			async: false,
			cache: false,
			headers : {"localePram" : localePram}, // 所有请求加上当前语言标示环境
		    success:  function (data) {
				if(data.indexOf("loginForm") != -1){
					window.location.href = cxt + "/platform/login";
					return;
				}
				
				var downFileUrl = "trading/salesSettlement/downStaticFile";
				downFileUrl = downFileUrl + "?fileName=" + encodeURI(encodeURI(data));
				downFileUrl = cxt + "/" + downFileUrl;
				window.open(downFileUrl);
//		    	result = data;
//		    	
//				//扩展回调函数
//				if( callback != null ){
//					callback();
//				}
		    }
		});
		return result;
	};
	
	/**
	 * ajaxForm请求，执行前进行确认
	 */
	var ajaxFormConfirm = function(formId, dataType, callback) {
		bootbox.confirm("确定要这样操作吗？", function(result) {
			if(result){
				ajaxForm(formId, dataType, callback);
			}
		});
	};
	
	/**
	 * ajax提交form替换指定div
	 * @param divId 返回替换div
	 * @param formId 提交formid
	 * @param callback 回调
	 */
	var ajaxFormDiv = function(divId, formId, callback){
		var result = ajaxForm(formId, callback);
		$("#" + divId).html(result);
	};
	
	/**
	 * ajaxFormDiv请求，执行前进行确认
	 */
	var ajaxFormDivConfirm = function(divId, formId, callback) {
		bootbox.confirm("确定要这样操作吗？", function(result) {
			if(result){
				ajaxFormDiv(divId, formId, callback);
			}
		});
	};
	
	/**
	 * ajax提交form替换指定主面板
	 * @param formId 提交formid
	 * @param callback 回调
	 */
	var ajaxFormMainPanel = function(formId, callback){
		var result = ajaxForm(formId, callback);
		$("#main-content").html(result);
	};
	
	/**
	 * ajaxFormMainPanel请求，执行前进行确认
	 */
	var ajaxFormMainPanelConfirm = function(formId, callback) {
		bootbox.confirm("确定要这样操作吗？", function(result) {
			if(result){
				ajaxFormMainPanel(formId, callback);
			}
		});
	};
	
	var ajaxFuncSqlComponent = function(domId,sqlNo,selectValue, data, callback){
		var select = $("#" + domId);
		var url = "/platform/dyComponent/ajaxDyComponent/" + sqlNo;
		var result = ajaxFunc(url,null,"json");
		for(var i=0;i<result.length;i++){
			var option = result[i];
			var value = option['value'];
			var text =  option['viewname'];
			
			console.log(value+" : "+text);
			if( value == selectValue){
				select.append("<option selected='selected' value='"+value+"'>"+text+"</option>");
			}else{
				select.append("<option value='"+value+"'>"+text+"</option>");
			}
		}
	};
	
	return {
		ajaxFunc : ajaxFunc,
		ajaxFuncConfirm : ajaxFuncConfirm,
		
		ajaxDiv : ajaxDiv,
		ajaxDivConfirm : ajaxDivConfirm,
		
		ajaxMainPanel : ajaxMainPanel,
		ajaxMainPanelConfirm : ajaxMainPanelConfirm,
		
		ajaxForm : ajaxForm,
		ajaxFormConfirm : ajaxFormConfirm,
		
		ajaxFormDiv : ajaxFormDiv,
		ajaxFormDivConfirm : ajaxFormDivConfirm,
		
		ajaxFormMainPanel : ajaxFormMainPanel,
		ajaxFormMainPanelConfirm : ajaxFormMainPanelConfirm,
		ajaxFuncSqlComponent:ajaxFuncSqlComponent,
		ajaxFormExcel:ajaxFormExcel,
	};
	
}();
