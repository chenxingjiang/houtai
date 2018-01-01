/**
 * 执行用户登陆操作
 */
function userLogin(){
	/**
	 * a.参数获取
	 *   1.用户名
	 *   2.密码
	 * b.非空校验
	 *    用户名  密码非空
	 * c.发送ajax 请求  执行登陆
	 *    获取后台登陆响应结果data
	 *       code==200 登陆成功
	 *          获取用户信息  写入cookie
	 *          跳转到主页main
	 *       code==300  登陆失败
	 *          提示登陆失败信息
	 */
	var userName=$("#userName").val();
	var userPwd=$("#userPwd").val();
	
	if(isEmpty(userName)){
		alert("用户名不能为空!");
		return;
	}
	
	if(isEmpty(userPwd)){
		alert("密码不能为空!");
		return;
	}
	var params={};
	params.userName=userName;
	params.userPwd=userPwd;
	console.log(params);
	$.ajax({
		type:"post",
		url:ctx+"/user/userLogin",
		data:params,
		dataType:"json",
		success:function(data){
			if(data.code==200){
				/**
				 * 获取后台返回的用户信息 
				 * 写入cookie 信息
				 *   用户名
				 *   真实名称
				 *   userIdStr
				 * 跳转主页
				 */
				var userInfo=data.result;
				$.cookie("userName",userInfo.userName);
				$.cookie("trueName",userInfo.trueName);
				$.cookie("userIdStr",userInfo.userIdStr);
				window.location.href="main";
			}else{
				alert(data.msg);
			}
		}
	});
	
	
	
	
	
	
}