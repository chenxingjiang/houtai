
$(function(){
	$("#dg").datagrid({
		rowStyler:function(index,rowData){
			if(rowData.devResult==0||rowData.devResult==1){
				return 'background-color:yellow';
			}
			
			if(rowData.devResult==2){
				return 'background-color:green';
			}
			
			if(rowData.devResult==3){
				return 'background-color:red';
			}
		}
	});
});

function formatterDevResult(val){
	if(val==0||val==1){
		return "开发";
	}
	if(val==2){
		return  "开发成功";
	}
	
	if(val==3){
		return  "开发失败";
	}
}

function formatterOp(val,rowData){
	if(rowData.devResult==0||rowData.devResult==1){
		var href="javascript:openCusDevPlanDetailTab('客户开发计划项管理_"+rowData.id+"',"+rowData.id+")";
		return "<a href="+href+">开发</a>";
	}
	if(rowData.devResult==2||rowData.devResult==3){
		var href="javascript:openCusDevPlanDetailTab('客户开发计划项详情查看_"+rowData.id+"',"+rowData.id+")";
		return "<a href="+href+">查看详情</a>";
	}
}

function openCusDevPlanDetailTab(title,sid){
	//alert(title+"--"+sid);
	window.parent.openTab(title,ctx+"/cus_dev_plan/index?sid="+sid);
}
