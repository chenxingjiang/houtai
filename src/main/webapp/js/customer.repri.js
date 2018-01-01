$(function(){
	var lossId=$("#lossId").val();
	$("#dg").edatagrid({
		url:ctx+"/customer_repri/customerReprievesByLossId?lossId="+lossId,
		saveUrl:ctx+"/customer_repri/insert?lossId="+lossId,
		updateUrl:ctx+"/customer_repri/update?lossId="+lossId
	});
	
	var state= $("#state").val();
	if(state==1){
		$("#toolbar").remove();
		$("#dg").edatagrid("disableEditing");
	}
});



function saveCustomerRepri(){
	$("#dg").edatagrid("saveRow");
	$("#dg").edatagrid("load");
}

/*function updateCustomerRepri(){
	$("#dg").edatagrid("saveRow");
	$("#dg").edatagrid("load");
}*/

function delCustomerRepri(){
	var rows=$("#dg").edatagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("来自crm","请选择删除记录","info");
		return;
	}
	if(rows.length>1){
		$.messager.alert("来自crm","只能选择一条记录进行删除记录","info");
		return;
	}
	$.messager.confirm("来自crm","确认删除选中记录?",function(r){
		if(r){
			$.ajax({
				type:"post",
				url:ctx+"/customer_repri/delete",
				data:"id="+rows[0].id,
				dataType:"json",
				success:function(data){
					if(data.code==200){
						$("#dg").edatagrid("load");
					}else{
						$.messager.alert("来自crm",data.msg,"info");
					}
				}
			});
		}
	});
}


function updateCustomerLossState(){
	/**
	 * 1.lossId
	 * 2.state =1
	 * 3.流失原因添加
	 */
	$.messager.confirm("来自crm","该客户确定流失?",function(r){
		if(r){
			$.messager.prompt("来自crm","请输入客户流失原因",function(val){
				if(val){
					$.ajax({
						type:"post",
						url:ctx+"/customer_loss/updateCustomerLossState",
						data:"lossId="+$("#lossId").val()+"&lossReason="+val+"&state=1",
						dataType:"json",
						success:function(data){
							if(data.code==200){
								$("#toolbar").remove();
							}else{
								$.messager.alert("来自crm",data.msg,"info");
							}
						}
					});
				}else{
					$.messager.alert("来自crm","请输入流失原因","info");
				}
			});
		}
	});
	
}


