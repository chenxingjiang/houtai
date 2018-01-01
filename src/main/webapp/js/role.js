function searchRoles(){
	$("#dg").datagrid("load",{
		roleName:$("#roleName").val()
	});
}

function openAddRoleDialog(){
	openDlg("dlg", "添加角色记录");
}

function closeDialog(){
	closeDlg("dlg");
}

function saveOrUpdateRole(){
	var id=$("#id").val();
	var url=ctx+"/role/insert";
	if(!isEmpty(id)){
		url=ctx+"/role/update";
	}
	saveOrUpdate("fm", url, searchRoles, "dlg");
}

function openModifyRoleDialog(){
	$("#password").attr("type","hidden");
	$("#pw").remove();
	openModifyDlg("dg", "fm", "dlg", "修改角色记录");
}
function deleteRole(){
	delRecode("dg", ctx+"/role/delete", searchRoles);
}


/**
 * 打开关联权限对话框
 */
function openRelatePermissionDlg(){
	
	var rows=$("#dg").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("来自crm","请选择角色进行授权!","info");
		return;
	}
	
	if(rows.length>1){
		$.messager.alert("来自crm","只能选择一条角色进行授权!","info");
		return;
	}
	
	$("#rid").val(rows[0].id);
	
	loadModuleData();
	
	openDlg("dlg02", "关联权限");
}


var ztreeObj;
function loadModuleData(){
	$.ajax({
		type:"post",
		url:ctx+"/module/queryAllsModuleDtos",
		data:"rid="+$("#rid").val(),
		dataType:"json",
		success:function(data){
			// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
			var setting = {
					check: {
						enable: true
					},
					data: {
						simpleData: {
							enable: true
						}
					},
					callback: {
						onCheck: zTreeOnCheck
					}
			};
			// zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
			var zNodes =data;
			ztreeObj= $.fn.zTree.init($("#treeDemo"), setting, zNodes);
		}
	});
}

/**
 * 添加权限
 */
function addPermission(){
	/**
	 * 1.角色id
	 * 2.资源id
	 */
	$.ajax({
		type:"post",
		url:ctx+"/role/addPermission",
		data:"rid="+$("#rid").val()+"&"+$("#moduleIds").val(),
		dataType:"json",
		success:function(data){
			console.log(data);
			if(data.code==200){
				$.messager.alert("来自crm",data.msg,"info");
				$("#moduleIds").val("");
				$("#rid").val("");
				closeDialog02();
			}else{
				$.messager.alert("来自crm",data.msg,"info");
			}
			
			
		}
	});
}

function closeDialog02(){
	closeDlg("dlg02");
}



function zTreeOnCheck(event, treeId, treeNode){
	var znodes=ztreeObj.getCheckedNodes(true);
	var moduleIds="moduleIds=";
	if(znodes.length>0){
		for(var i=0;i<znodes.length;i++){
			if(i<=znodes.length-2){
				moduleIds=moduleIds+znodes[i].id+"&moduleIds=";
			}else{
				moduleIds=moduleIds+znodes[i].id;
			}
		}
	}
	console.log(moduleIds);
	$("#moduleIds").val(moduleIds);
}