/**
 * 公共通用的js方法集合
 */
var crudtable = function() {
	"use strict";
	var $table = null;
	/**
     * @param table 表格Div Id
     * @param columns 表格字段的JSON
     * ex:var columns = [{
	        checkbox: true,
	        align: 'center',
	        valign: 'middle',
	    },{
	        field: 'ids',
	        title: '主键'
	    }, {
	        field: 'abs',
	        title: 'Item Name'
	    }, {
	        field: 'amount',
	        title: 'Item Price'
	    }];
     * 初始化表格
     */
    function create(table,columns) {
    	$table = $("#"+table);
    	$table.bootstrapTable({
    		columns: [
    		            {
    		                checkbox: true
    		            },
    		            {
    		                field: "abs",
    		                title: "用户名",
    		                editable: {
    		                    type: 'text',
    		                    title: '用户名',
    		                    validate: function (v) {
    		                        if (!v) return '用户名不能为空';

    		                    }
    		                }
    		            },
    		            {
    		                field: "price",
    		                title: "年龄",
    		            }],
			showExport: true,   
			showColumns:true,							//显示列
			minimumCountColumns:2,						//最少显示多少列
			striped: true,                              //是否显示行间隔色
			uniqueId: "ids",							 //每一行的唯一标识，一般为主键列
			pageNumber:1,  								//初始化加载第一页，默认第一页
			pageSize:10,  								//初始化加载第一页，默认第一页
			pageList: [10, 25, 50, 100], 				//可供选择的每页的行数（*）
			sidePagination: "server",					//分页方式：client客户端分页，server服务端分页（*）
			pagination:true,
		});
	};
    
	/**
	 * 返回table check选中的值 row为选中的对象
	 */
    var getChecks = function getIdSelections() {
    	return $.map($table.bootstrapTable('getSelections'), function (row) {
            return row.ids
        });
    }
    
    var deleteList = function deleteList(button){
    	var table = $(button).attr("data-table");
		var url = $(button).attr("data-url");
		
		var idArr = getChecks();
		var idsStr = idArr[0];
		for (var i = 1; i < idArr.length; i++){
			idsStr = idsStr + "," + idArr[i];
		}
		
		if(idsStr != ""){
			var d = dialog({
				align: 'bottom',
				width: 180,
				title: "确定要这样操作吗？", 
			    okValue: "确定",
			    ok: function () {
			    	var data = {'ids' : idsStr};
			    	common_ajax.ajaxMainPanel(url, data);
			    },
			    cancelValue: "取消", // 取消
			    cancel: function () {}
			});
			d.showModal(document.getElementById('header'));
		}else{
			//"请选择操作数据！"
		}
    };
    
	var init = function(table,columns) {
		create( table,columns);
	};

	return {
		init : init,
		getChecks:getChecks,
		deleteList:deleteList
	};

}();
