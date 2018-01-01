function formatterState(val){
	if(val==0){
		return "未支付";
	}
	if(val==1){
		return "已支付";
	}
}

function formatterOp(){
	var href="javascript:openOrderDetailByOid()";
	return "<a href="+href+">查看详情</a>";
}

function openOrderDetailByOid(){
	var rows= $("#dg").datagrid("getSelections");
	
    $.ajax({
    	type:"post",
    	url:ctx+"/customer_order/queryCustoemrOrderByOrderId",
    	data:"orderId="+rows[0].id,
    	dataType:"json",
    	success:function(data){
    		if(data.state==0){
    			data.state="未支付";
    		}
    		if(data.state==1){
    			date.state="已支付";
    		}
    		
    		
    		data.orderDate=new Date(data.orderDate).format("yyyy-MM-dd hh:mm:ss");
    		
    		
    		$("#fm").form("load",data);
    	}
    });
    
    $("#dg2").datagrid("load",{
    	orderId:rows[0].id
    });
    
	$("#dlg").dialog("open");
	
}
