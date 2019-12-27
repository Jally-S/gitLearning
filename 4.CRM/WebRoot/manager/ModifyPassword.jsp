<%@page import="org.jvsun.pojo.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改密码</title>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.js"></script>
     <link rel="stylesheet" href="../css/pintuer.css">
	<link rel="stylesheet" href="../css/admin.css">
  </head>
  
  <body>
  <center>
		<h2>修改密码</h2>
		<hr/>
		<form name = "pro">

			<br/>
			输入密码：<input type="password" name="password" class="input" style="width:250px; line-height:17px;display:inline-block; margin:20px;"/>
			<br/>
			确认密码：<input type="password" name="passwordagin" onblur ="checkPassword()" class="input" style="width:250px; line-height:17px;display:inline-block;  margin:20px;"/>
			<br/>
			<input type="button" value="确认" onclick="upd()" class="button border-main icon-search"/>
			<input type="button" value="返回" onclick="back()" class="button border-main icon-search"/>
		</form>
	</center>	
  </body>
  <script type="text/javascript">
	var flag = false;
		function upd(){
			var password=pro.password.value;
		
			if(flag){
			 $(document).ready(function(){
			
			 	//设置提交的路径，和参数
				$.post("<%=path%>/ModifyPassword",
				{"password":password},
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
	 				
	 				if(data == "true"){
	 					alert("密码修改成功");
	 					location.href= "WorkerInfo.jsp"
	 				}else{
	 					alert("密码修改失败，请联系系统管理员");
	 				}
	 			});
			});}
		}
			
		function back(){
			opener.location.reload(); 
			//window.dialogArguments.query(0);//刷新之前页面 
			window.close();//关闭当前页面
		}
		function checkPassword(){
			
			var password=pro.password.value;
			var passwordagin=pro.passwordagin.value;
				if(password!=passwordagin){
					alert("两次密码输入不一样,请重新输入");
					pro.password.value = "";
					pro.passwordagin.value = "";
					
				}else{
					flag=true;
				}
		}
	</script>
</html>
