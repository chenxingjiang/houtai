/**
 * 多条件查询营销机会数据
 */
function searchSaleChances(){
	$("#dg").datagrid("load",{
		createMan:$("#createMan").val(),
		customerName:$("#customerName").val(),
		createDate:$("#createDate").datebox("getValue"),
		state:$("#state").combobox("getValue")
	});
}

function formatterState(val){
	if(val==0){
		return "未分配";
	}else if(val==1){
		return "已分配";
	}else{
		return "未定义";
	}
	
}

$(function(){
	$("#dlg").dialog({
		onClose:function(){
			initFormData();
		}
	});
	
	$("#createMan").val($.cookie("trueName"));
	
});

function openAddSaleChanceDialog(){
	openDlg("dlg", "添加营销记录数据");
}


function closeDialog(){
	closeDlg("dlg");
}


function initFormData(){
	$("#fm").form("clear");
}

function saveSaleChance(){
	var save_update_url=ctx+"/sale_chance/insert";
	var id=$("#id").val();
	if(!isEmpty(id)){
		/**
		 * 执行更新
		 */
		save_update_url=ctx+"/sale_chance/update";
	}
	saveOrUpdate("fm", save_update_url, searchSaleChances, "dlg");
}

/**
 * 打开修改对话框
 */
function openModifySaleChanceDialog(){
	openModifyDlg("dg", "fm", "dlg", "更新营销机会记录");
}


function deleteSaleChance(){
	delRecode("dg", ctx+"/sale_chance/delete", searchSaleChances);
}
