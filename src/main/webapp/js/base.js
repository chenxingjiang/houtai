/**
 * 打开对话框
 * @param dlgId 对话框id 值
 * @param title  对话框标题
 */
function openDlg(dlgId,title){
	$("#"+dlgId).dialog("open").dialog("setTitle",title);
}

/**
 * 关闭对话框
 * @param dlgId 对话框id
 */
function closeDlg(dlgId){
	$("#"+dlgId).dialog("close");
}


/**
 * 
 * @param formId  表单id
 * @param url     后台添加或者更新url
 * @param refresh 添加或者更新成功后的刷新方法
 * @param dlgId   对话框id
 */
function saveOrUpdate(formId,url,refresh,dlgId){
	$("#"+formId).form("submit",{
		url:url,
		onSubmit:function(){
			return $("#"+formId).form("validate");
		},
		success:function(data){
			data=JSON.parse(data);
			if(data.code==200){
				closeDlg(dlgId);
				refresh();
			}else{
				$.messager.alert("来自crm",data.msg,"info");
			}
		}
	});
}

/**
 * 打开修改对话框
 * @param dataGridId 表格id
 * @param formId  表单id
 * @param dlgId   对话框id
 * @param title   更新标题
 */
function openModifyDlg(dataGridId,formId,dlgId,title){
	var rows=$("#"+dataGridId).datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("来自crm","请选中待更新记录!","info");
		return;
	}
	
	if(rows.length>1){
		$.messager.alert("来自crm","只能选择一条记录执行更新!","info");
		return;
	}
	$("#"+formId).form("load",rows[0]);
	$("#"+dlgId).dialog("open").dialog("setTitle",title);
}

/**
 * 删除方法简单封装
 * @param dataGridId
 * @param url
 * @param refresh
 */
function delRecode(dataGridId,url,refresh){
	var rows=$("#"+dataGridId).datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("来自crm","请选中待删除记录!","info");
		return;
	}
	
	// ids=1&ids=2&ids=3
	var ids="ids=";
	for(var i=0;i<rows.length;i++){
		if(i<=rows.length-2){
			ids=ids+rows[i].id+"&ids=";
			continue;
		}
	    ids=ids+rows[i].id;
	}
	$.messager.confirm("来自crm","确定删除选中的"+rows.length+"条记录?",function(r){
		if(r){
			$.ajax({
				type:"post",
				url:url,
				data:ids,
				dataType:"json",
				success:function(data){
					if(data.code==200){
						refresh();
					}else{
						$.messager.alert("来自crm",data.msg,"info");
					}
				}
			});
		}
	});
	
}

