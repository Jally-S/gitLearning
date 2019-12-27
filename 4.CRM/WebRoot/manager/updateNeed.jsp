<%@page import="org.jvsun.pojo.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>更新需求信息</title>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.js"></script>
    <link rel="stylesheet" href="<%=path %>/css/pintuer.css">
	<link rel="stylesheet" href="<%=path %>/css/admin.css">
  </head>
  
  <body>
  <center>
		<form name = "pro">
			<input type="hidden" name="pid" value = "${pojo.needId}"/>
			<br/>
			客户名称：${pojo.customerName}
			<br/>
			客户爱好：<input type="text" name="nhobby" value = "${pojo.nhobby}" class="input"/>
			<br/>
			产品名称：<input type="text" name="pname" value = "${pojo.productName}" class="input"/>
			<br/>
			<input type="button" value="确认" onclick="upd()" class="button border-main icon-search"/>
			<input type="button" value="返回" onclick="back()" class="button border-main icon-search"/>
		</form>
	</center>	
  </body>
  <script type="text/javascript">
		function upd(){
			var pid=pro.pid.value;
			var nhobby=pro.nhobby.value;
			var pname=pro.pname.value;
			 $(document).ready(function(){
			 	//设置提交的路径，和参数
				$.post("<%=path%>/NeedUpd",
				{"pid":pid,"nhobby":nhobby,"pname":pname},
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
	 				if(data == "true"){
	 					alert("需求修改成功");
	 					back();
	 				}else{
	 					alert("需求修改失败，请联系系统管理员");
	 				}
	 			});
			});
		}
			
		function back(){
			opener.location.reload(); 
			//window.dialogArguments.query(0);//刷新之前页面 
			window.close();//关闭当前页面
		}
	</script>
</html>
