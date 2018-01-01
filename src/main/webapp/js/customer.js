function searchCustomer(){
	$("#dg").datagrid("load",{
		khNum:$("#s_khno").val(),
		cusName:$("#s_name").val()
	});
}

$(function(){
	$("#dlg").dialog({
		onClose:function(){
			initFormData();
		}
	});
});


function initFormData(){
	//$("#fm").form("clear");
	$("#id").val("");
	$("#name").val("");
	$("#area").combobox("clear");
	$("#cusManager").combobox("clear");
	$("#level").combobox("clear");
	$("#myd").combobox("clear");
	$("#xyd").combobox("clear");
	$("#postCode").val("");
	$("#phone").val("");
	$("#fax").val("");
	$("#webSite").val("");
	$("#address").val("");
	$("#yyzzzch").val("");
	$("#fr").val("");
	$("#zczj").val("");
	$("#nyye").val("");
	$("#khyh").val("");
    $("#khzh").val("");
    $("#dsdjh").val("");
    $("#gsdjh").val("");
    
    /**
     * jquery 选择器  
     *    批量清空 input 框内容
     *    下拉框   select 内容
     */
}
function openCustomerAddDialog(){
	openDlg("dlg", "添加客户信息");
}

function closeCustomerDialog(){
	closeDlg("dlg");
}

function saveOrUpdateCustomer(){
	var id=$("#id").val();
	var url=ctx+"/customer/insert";
	if(!isEmpty(id)){
		url=ctx+"/customer/update";
	}
	saveOrUpdate("fm", url, searchCustomer, "dlg");
}


function openCustomerModifyDialog(){
	openModifyDlg("dg", "fm", "dlg", "修改客户信息");
}


function deleteCustomer(){
	delRecode("dg", ctx+"/customer/delete", searchCustomer);
}



function openCustomerOtherInfo(title,type){
	var rows=$("#dg").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("来自crm","请选择一条客户记录","info");
		return;
	}
	if(rows.length>1){
		$.messager.alert("来自crm","只能选择一条客户记录","info");
		return;
	}
	
	window.parent.openTab(title,ctx+"/customer/toOtherPage/"+type+"/"+rows[0].id);
}





