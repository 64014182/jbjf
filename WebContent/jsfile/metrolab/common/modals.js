var common_modals = function() {
	"use strict";

	/**
	 * 操作提示弹出框
	 */
	var alert = function(content) {
		var d = dialog({
		    title: '操作提示',
		    content: content
		});
		d.show();
		return;
	};
	
	/**
	 * 删除多行数据
	 */
	var deleteList = function(button) {
		var table = $(button).attr("data-table");
		var url = $(button).attr("data-url");
		var ids = common_common.getCheckFunc(table);
		if(ids != ""){
			var d = dialog({
				align: 'bottom',
				width: 180,
				title: "确定要这样操作吗？", 
			    okValue: i18n_modals_common_determine, // 确定
			    ok: function () {
			    	var data = {'ids' : ids};
			    	common_ajax.ajaxMainPanel(url, data);
			    },
			    cancelValue: i18n_modals_common_close, // 取消
			    cancel: function () {}
			});
			d.showModal(document.getElementById('header'));
			
		}else{
			//"请选择操作数据！"
		}
	};
	
	/**
	 * 删除单行数据
	 */
	var deleteOne = function(button) {
		var url = $(button).attr("data-url");
		var d = dialog({
			align: 'bottom',
			width: 180,
			title: "确定要这样操作吗？", 
		    okValue: i18n_modals_common_determine, // 确定
		    ok: function () {
		    	common_ajax.ajaxMainPanel(url);
		    },
		    cancelValue: i18n_modals_common_close, // 取消
		    cancel: function () {}
		});
		d.showModal(document.getElementById('header'));
	};
	
	/**
	 * 通用弹出代码
	 */
	var dialogSelect = function(title, content, func){
		var d = dialog({
			align: 'bottom',
			title: title, 
		    content: content,
		    okValue: i18n_modals_common_determine, // 确定
		    ok: function () {
		    	if( func == "setCheckValue" ){
		    		setCheckValue();
				} else if( func == "dialogEditForm" ){
					common_ajax.ajaxForm('dialogEditForm');
				}
		    },
		    cancelValue: i18n_modals_common_close, // 取消
		    cancel: function () {}
		});
		//setToPage(d);
		d.showModal(document.getElementById('header'));
	};
	
	/**
	 * 部门单选
	 * @param deptId 点击确认时回写deptId值的地方
	 * @param deptName 点击确认时回写deptName值的地方
	 * @param checkedIds 默认选中的部门
	 */
	var deptRadioDiaLog = function(deptId, deptName, checkedIds){//ids默认选中：4
		var url = "/platform/dept/toUrl";
		var data = { "toUrl" : "/platform/department/radio.html", "ids" : checkedIds, "deptId" : deptId, "deptName" : deptName };
		var result = common_ajax.ajaxFunc(url, data);
		dialogSelect(i18n_modals_department_radio, result, "setCheckValue");
		return result;
	};
	
	/**
	 * 部门多选
	 * @param deptId 点击确认时回写deptId值的地方
	 * @param deptName 点击确认时回写deptName值的地方
	 * @param checkedIds 默认选中的部门
	 */
	var deptCheckboxDiaLog = function(deptId, deptName, checkedIds){//ids默认选中：4,或者4,5,
		var url = "/platform/dept/toUrl";
		var data = { "toUrl" : "/platform/department/checkbox.html", "ids" : checkedIds, "deptId" : deptId, "deptName" : deptName };
		var result = common_ajax.ajaxFunc(url, data);
		dialogSelect(i18n_modals_department_checkbox, result, "setCheckValue");
		return result;
	};
		
	/**
	 * 字典单选
	 * @param dictId 数据回填
	 * @param dictName 数据回填
	 * @param checkedIds 默认选中
	 * @param rootNumbers 根节点编号
	 * @param callback 回调
	 */
	var dictRadioDiaLog = function(dictId, dictName, checkedIds, rootNumbers, callback){
		var url = "/platform/dict/toUrl";
		var data = { "toUrl" : "/platform/dict/radio.html", "ids" : checkedIds, "dictId" : dictId, "dictName" : dictName };
		var result = common_ajax.ajaxFunc(url, data, callback);
		dialogSelect(i18n_modals_dict_radio, result, "setCheckValue");
		return result;
	};
	
	/**
	 * 参数单选
	 * @param dictId 数据回填
	 * @param dictName 数据回填
	 * @param checkedIds 默认选中
	 * @param rootNumbers 根节点编号
	 * @param callback 回调
	 */
	var paramRadioDiaLog = function(dictId, dictName, checkedIds, rootNumbers, callback){
		var url = "/platform/param/toUrl";
		var data = { "toUrl" : "/platform/param/radio.html", "ids" : checkedIds, "paramId" : dictId, "paramName" : dictName };
		var result = common_ajax.ajaxFunc(url, data, callback);
		dialogSelect(i18n_modals_param_radio, result, "setCheckValue");
		return result;
	};
	
	/**
	 * 模块单选
	 * @param moduleId
	 * @param moduleName
	 * @param checkedIds
	 */
	var moduleRadioDiaLog = function(moduleId, moduleName, checkedIds){
		var url = "/platform/module/toUrl";
		var data = { "toUrl" : "/platform/module/radio.html", "ids" : checkedIds, "moduleId" : moduleId, "moduleName" : moduleName };
		var result = common_ajax.ajaxFunc(url, data);
		dialogSelect(i18n_modals_module_radio, result, "setCheckValue");
		return result;
	};
	
	/**
	 * 岗位单选
	 * @param stationId
	 * @param stationName
	 * @param checkedIds
	 */
	var stationRadioDiaLog = function(stationId, stationName, checkedIds){
		var url = "/platform/station/toUrl";
		var data = { "toUrl" : "/platform/station/radio.html", "ids" : checkedIds, "stationId" : stationId, "stationName" : stationName };
		var result = common_ajax.ajaxFunc(url, data);
		dialogSelect(i18n_modals_station_radio, result, "setCheckValue");
		return result;
	};
	
	/**
	 * 岗位多选
	 * @param stationId
	 * @param stationName
	 * @param checkedIds
	 */
	var stationCheckboxDiaLog = function(stationId, stationName, checkedIds){
		var url = "/platform/station/toUrl";
		var data = { "toUrl" : "/platform/station/checkbox.html", "ids" : checkedIds, "stationId" : stationId, "stationName" : stationName };
		var result = common_ajax.ajaxFunc(url, data);
		dialogSelect(i18n_modals_station_checkbox, result, "setCheckValue");
		return result;
	};
	
	/**
	 * 人员单选
	 * @param userId
	 * @param userName
	 * @param checkedIds
	 */
	var userRadioDiaLog = function(userId, userName, checkedIds){
		var url = "/platform/user/toUrl";
		var data = { "toUrl" : "/platform/user/radio.html", "ids" : checkedIds, "userId" : userId, "userName" : userName };
		var result = common_ajax.ajaxFunc(url, data);
		dialogSelect(i18n_modals_user_radio, result, "setCheckValue");
		return result;
	};
	
	/**
	 * 人员多选
	 * @param userId
	 * @param userName
	 * @param deptIds
	 * @param deptNames
	 * @param checkedDeptIds
	 * @param checkedUserIds
	 */
	var userCheckboxDiaLog = function(userId, userName, deptIds, deptNames, checkedDeptIds, checkedUserIds){
		var url = "/platform/user/toUrl";
		var data = { "toUrl" : "/platform/user/checkbox.html", 
				"checkedDeptIds" : checkedDeptIds, "checkedUserIds" : checkedUserIds,
				"userId" : userId, "userName" : userName, "deptIds" : deptIds, "deptNames" : deptNames 
				};
		var result = common_ajax.ajaxFunc(url, data);
		dialogSelect(i18n_modals_user_checkbox, result, "setCheckValue");
		return result;
	};
	
	/**************************************		功能定制函数	start	***************************************************/
	
	/**
	 * 部门负责人设置
	 * @param deptId
	 */
	var setDeptPrincipalDiaLog = function(deptId){
		var url = "/platform/dept/toUrl";
		var data = { "toUrl" : "/platform/department/userTree.html", "ids" : deptId };
		var result = common_ajax.ajaxFunc(url, data);
		dialogSelect(i18n_modals_department_userTree, result, "setCheckValue");
		return result;
	};
	
	/**
	 * 菜单功能设置
	 * @param menuIds
	 */
	var setMenuOperatorDiaLog = function(menuIds){
		var url = "/platform/operator/toUrl";
		var data = { "toUrl" : "/platform/menu/operatorTree.html", "ids" : menuIds };
		var result = common_ajax.ajaxFunc(url, data);
		dialogSelect(i18n_modals_menu_operatorTree, result, "setCheckValue");
		return result;
	};
	
	/**
	 * 菜单国际化名称设置
	 * @param menuIds
	 */
	var setMenuEditDiaLog = function(menuIds){
		var url = "/platform/menu/toEdit";
		var data = { "ids" : menuIds };
		var result = common_ajax.ajaxFunc(url, data);
		dialogSelect(i18n_modals_menu_edit, result, "dialogEditForm");
		return result;
	};
	
	/**
	 * 角色功能设置
	 * @param roleIds
	 */
	var setRoleOperatorDiaLog = function(roleIds){
		var url = "/platform/operator/toUrl";
		var data = { "toUrl" : "/platform/role/operatorTree.html", "ids" : roleIds};
		var result = common_ajax.ajaxFunc(url, data);
		dialogSelect(i18n_modals_role_operatorTree, result, "setCheckValue");
		return result;
	};
	
	/**
	 * 岗位功能设置
	 * @param stationIds
	 */
	var setStationOperatorDiaLog = function(stationIds){
		var url = "/platform/operator/toUrl";
		var data = { "toUrl" : "/platform/station/operatorTree.html", "ids" : stationIds};
		var result = common_ajax.ajaxFunc(url, data);
		dialogSelect(i18n_modals_station_operatorTree, result, "setCheckValue");
		return result;
	};
	
	/**
	 * 用户的分组设置
	 * @param userIds
	 */
	var groupSelectDialog = function(userIds){
		var url = "/platform/userGroup/select";
		var data = { "ids" : userIds};
		var result = common_ajax.ajaxFunc(url, data);
		dialogSelect(i18n_modals_group_select, result, "");
		return result;
	};
	
	/**
	 * 分组的角色设置
	 * @param groupIds
	 */
	var roleSelectDialog = function (groupIds){
		var url = "/platform/groupRole/select";
		var data = { "ids" : groupIds};
		var result = common_ajax.ajaxFunc(url, data);
		dialogSelect(i18n_modals_role_select, result, "");
		return result;
	};
	
	var myDialog;
	/**
	 * 
	 * @param groupIds
	 */
	var orderplanDialog = function (declareplanIds){
		
		myDialog = dialog({
			align: 'bottom',
		    title: '选择计划订单完成情况',
		    icon: 'succeed',
		});// 初始化一个带有loading图标的空对话框
		jQuery.ajax({
		    url: '/JFinalUIBV4/trading/orderPlan/radioSelectDialog',
		    data:{ "declareplanIds" : declareplanIds },
		    success: function (data) {
		        ///myDialog.content(data);// 填充对话框内容
		    },
		    
		});
		myDialog.showModal(document.getElementById('header'));
	};
	
	/**
	 * 计划完成弹出框
	 * 
	 */
	var planOrderCompleteDialog = function(settlementIds){
		myDialog = dialog({
			align: 'bottom',
		    title: '选择计划完成',
		    icon: 'succeed',
		});
		$.ajax({
		    url: '/JFinalUIBV4/trading/planOrderComplete/radioSelectDialog',
		    data:{ "settlementIds" : settlementIds },
		    success: function (data) {
		    	//console.log(data);
		        myDialog.content(data);// 填充对话框内容
		    },
		});
		myDialog.showModal(document.getElementById('header'));
	}
	
	var passChangeDialog = function(button){
		var url = $(button).attr("data-url");
		myDialog = dialog({
			align: 'bottom',
			title: "修改密码",
		    icon: 'succeed',
		});
		$.ajax({
		    url: url,
		    success: function (data) {
		        myDialog.content(data);// 填充对话框内容
		    },
		});
		myDialog.showModal(document.getElementById('header'));
	}
	
	/**
	 * settlementNo,结算清单号
	 * invoice 发票号
	 */
	var seltt = function(button,settlementNo,invoice){
		var table = $(button).attr("data-table");
		var url = $(button).attr("data-url");
		if(settlementNo != ""){
			myDialog = dialog({
				align: 'bottom',
				title: "结算清单号",
			    icon: 'succeed',
			});
			$.ajax({
			    url: url,
			    data:{ "settlementNo" : settlementNo,"invoice":invoice },
			    success: function (data) {
			        myDialog.content(data);// 填充对话框内容
			    },
			});
			myDialog.showModal(document.getElementById('header'));
		}else{
			//"请选择操作数据！"
		}
	}
	
	/**
	 * 追溯
	 */
	var trace = function(url,tableId,title){
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
				toastr.error("选择的数据订货单位名称不一致！");
				return;
			}
			url+="&ids=" + encodeURI(encodeURI(ids));
			myDialog = dialog({
				align: 'bottom',
				title: title,
			    icon: 'succeed',
			});
			$.ajax({
			    url: url,
			    success: function (data) {
			        myDialog.content(data);// 填充对话框内容
			    },
			});
			myDialog.showModal(document.getElementById('header'));
		}
	}
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
	var modal = function(button){
		var table = $(button).attr("data-table");
		var url = $(button).attr("data-url");
		var dataStr = $(button).attr("data-param");
		var title = $(button).attr("modal-title");
		var data = JSON.parse(dataStr); 
		myDialog = dialog({
			align: 'bottom',
			title: title,
		    icon: 'succeed',
		});
		$.ajax({
		    url: url,
		    data:data,
		    success: function (data) {
		        myDialog.content(data);// 填充对话框内容
		    },
		});
		myDialog.showModal(document.getElementById('header'));
	}
	
	/**
	 * 销售结算明细打印
	 */
	var salesSettlementSummary = function(button){
		var table = $(button).attr("data-table");
		var url = $(button).attr("data-url");
		var ids = common_common.getCheckFunc(table);
		var data = {'ids' : ids};
		if(ids != ""){
			myDialog = dialog({
				align: 'bottom',
				title: "销售结算明细汇总",
			    icon: 'succeed',
			});
			$.ajax({
			    url: url,
			    data:data,
			    success: function (data) {
			    	alert(data);
			    	window.open(data);
			        //myDialog.content(data);// 填充对话框内容
			    },
			});
		}else{
			//"请选择操作数据！"
		}
	}
	
	/**
	 * 删除多行数据
	 */
	var deleteList = function(button) {
		var table = $(button).attr("data-table");
		var url = $(button).attr("data-url");
		var ids = common_common.getCheckFunc(table);
		if(ids != ""){
			var d = dialog({
				align: 'bottom',
				width: 180,
				title: "确定要这样操作吗？", 
			    okValue: i18n_modals_common_determine, // 确定
			    ok: function () {
			    	var data = {'ids' : ids};
			    	common_ajax.ajaxMainPanel(url, data);
			    },
			    cancelValue: i18n_modals_common_close, // 取消
			    cancel: function () {}
			});
			d.showModal(document.getElementById('header'));
			
		}else{
			//"请选择操作数据！"
		}
	};
	
	var closeDialog = function(){
		myDialog.close();
		myDialog = null;
	}
	
	var getDialog = function(){
		return myDialog;
	}

	/**************************************		功能定制函数	end	***************************************************/	
		
	return {
		getDialog:getDialog,
		alert : alert,
		deleteOne : deleteOne,
		deleteList : deleteList,
		deptRadioDiaLog : deptRadioDiaLog,
		deptCheckboxDiaLog : deptCheckboxDiaLog,
		dictRadioDiaLog : dictRadioDiaLog,
		paramRadioDiaLog : paramRadioDiaLog,
		moduleRadioDiaLog : moduleRadioDiaLog,
		stationRadioDiaLog : stationRadioDiaLog,
		stationCheckboxDiaLog : stationCheckboxDiaLog,
		userRadioDiaLog : userRadioDiaLog,
		userCheckboxDiaLog : userCheckboxDiaLog,
		setDeptPrincipalDiaLog : setDeptPrincipalDiaLog,
		setMenuOperatorDiaLog : setMenuOperatorDiaLog,
		setMenuEditDiaLog : setMenuEditDiaLog,
		setRoleOperatorDiaLog : setRoleOperatorDiaLog,
		setStationOperatorDiaLog : setStationOperatorDiaLog,
		groupSelectDialog : groupSelectDialog,
		roleSelectDialog : roleSelectDialog,
		orderplanDialog:orderplanDialog,
		planOrderCompleteDialog:planOrderCompleteDialog,
		seltt:seltt,
		salesSettlementSummary:salesSettlementSummary,
		modal:modal,
		closeDialog:closeDialog,
		trace:trace,
		passChangeDialog:passChangeDialog
	};
	
}(); 