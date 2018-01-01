function openTab(text, url, iconCls){
    if($("#tabs").tabs("exists",text)){
        $("#tabs").tabs("select",text);
    }else{
        var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='" + url + "'></iframe>";
        $("#tabs").tabs("add",{
            title:text,
            iconCls:iconCls,
            closable:true,
            content:content
        });
    }
}


/**
 * 用户退出
 */
function logout(){
	$.messager.confirm("来自crm","确定退出?",function(r){
		if(r){
			var result;
			$.ajax({
				type:"post",
				url:ctx+"/user/userLoginOut",
				dataType:"json",
				success:function(data){
					result=data;
				}
			});
			$.messager.alert("来自crm","系统将会在5秒后退出!!!","info");
			if(result.code=200){
				setTimeout(function(){
					window.location.href="index";
				},5000);
			}else{
				$.messager.alert("来自crm",result.msg,"info");
			}
			
		}
	});
}

/**
 * 打开修改密码对话框
 */
function openPasswordModifyDialog(){
	$("#dlg").dialog("open");
}


/**
 * 提交修改密码表单
 */
function modifyPassword(){
	$("#fm").form("submit",{
		url:ctx+"/user/updateUserPassword",
		onSubmit:function(){
			return $("#fm").form("validate");
		},
		success:function(data){
			data=JSON.parse(data);
			if(data.code==200){
				$.messager.alert("来自crm","密码修改成功,系统将在两秒后自动退出！！！","info");
				setTimeout(function(){
					$.removeCookie("userIdStr");
					$.removeCookie("userName");
					$.removeCookie("trueName");
					window.location.href="index";
				}, 2000);
			}else{
				$.messager.alert("来自crm",data.msg,"info");
			}
		}
	});
}

function closePasswordModifyDialog(){
	$("#dlg").dialog("close");
}


