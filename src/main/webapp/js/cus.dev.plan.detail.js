$(function(){
	
	/**
	 * 根据开发结果状态值  控制计划项工具栏是否显示
	 *   不显示时  devResult=2|3
	 */
	var devResult=$("#devResult").val();
	//alert(devResult);
	if(devResult==2||devResult==3){
		$("#toolbar").remove();
		//$("#toolbar").hide();
	}
	
	
	$("#dg").edatagrid({
		url:ctx+"/cus_dev_plan/queryCusDevPlansByParams?saleChanceId="+$("#saleChanceId").val(),
		saveUrl:ctx+"/cus_dev_plan/insert?saleChanceId="+$("#saleChanceId").val(),
		updateUrl:ctx+"/cus_dev_plan/update?saleChanceId="+$("#saleChanceId").val()
	});
});


function saveCusDevPlan(){
	$("#dg").edatagrid("saveRow");
	$("#dg").edatagrid("load");
}


function updateCusDevPlan(){
	$("#dg").edatagrid("saveRow");
	$("#dg").edatagrid("load");
}

function delCusDevPlan(){
	var rows=$("#dg").edatagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("来自crm","请选择删除记录","info");
		return;
	}
	if(rows.length>1){
		$.messager.alert("来自crm","只能选择一条记录进行删除记录","info");
		return;
	}
	
	console.log(rows[0].id);
	$.messager.confirm("来自crm","确认删除选中记录?",function(r){
		if(r){
			$.ajax({
				type:"post",
				url:ctx+"/cus_dev_plan/delete",
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



function updateSaleChanceDevResult(devResult){
	$.messager.confirm("来自crm","确认执行该操作?",function(r){
		if(r){
			$.ajax({
				type:"post",
				url:ctx+"/sale_chance/updateSaleChanceDevResult",
				data:"devResult="+devResult+"&sid="+$("#saleChanceId").val(),
				dataType:"json",
				success:function(data){
					if(data.code==200){
						$("#toolbar").remove();
					}else{
						$.messager.alert("来自crm",data.msg,"info");
					}
				}
			})
		}
	})
}