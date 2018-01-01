function searchCustomerLosses(){
	$("#dg").datagrid("load",{
		cusName:$("#s_cusName").val(),
		cusManager:$("#s_cusManager").combobox("getValue"),
		cusNo:$("#s_cusNo").val(),
		time:$("#s_time").datebox("getValue")
	});
}


$(function(){
	$("#dg").datagrid({
		rowStyler:function(index,rowData){
			if(rowData.state==0){
				return 'background-color:yellow';
			}
			if(rowData.state==1){
				return 'background-color:red';
			}
		}
	});
});


function formatterState(val){
	if(val==0){
		return "暂缓流失";
	}
	if(val==1){
		return "确认流失";
	}
}


function formatterOp(val,rowData){
	if(rowData.state==0){
		var href="javascript:openCustomerRepriDetailTab('添加暂缓处理_'+"+rowData.id+","+rowData.id+")";
		return "<a href="+href+">添加暂缓处理</a>";	
	}
	if(rowData.state==1){
		var href="javascript:openCustomerRepriDetailTab('流失详情查看_'+"+rowData.id+","+rowData.id+")";
		return "<a href="+href+">查看流失详情</a>";	
	}
}

function openCustomerRepriDetailTab(title,lossId){
	window.parent.openTab(title,ctx+"/customer_loss/toCustomerRepriPage/"+lossId);
}
