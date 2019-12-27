<%@page import="org.jvsun.pojo.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>更新对手信息</title>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.js"></script>
    <link rel="stylesheet" href="<%=path %>/css/pintuer.css">
	<link rel="stylesheet" href="<%=path %>/css/admin.css">
  </head>
  
  <body>
  <center>
		<form name = "com">
		<br/>
			<input type="hidden" name="cid" value = "${pojo.competitorId}"/>
			<br/>
			对手名称：<input type="text" name="cname" value = "${pojo.competitorName}" class="input"/>
			<br/>
			对手类别：<input type="text" name="clas" value = "${pojo.competitorClass}" class="input"/>
			<br/>
			<input type="button" value="确认" onclick="upd()" class="button border-main icon-search"/>
			<input type="button" value="返回" onclick="back()" class="button border-main icon-search"/>
		</form>
	</center>	
  </body>
  <script type="text/javascript">
		function upd(){
			var cid=com.cid.value;
			var cname=com.cname.value;
			var clas=com.clas.value;
			 $(document).ready(function(){
			 	//设置提交的路径，和参数
				$.post("<%=path%>/CompetitorUpd",
				{"cid":cid,"cname":cname,"clas":clas},
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
	 				if(data == "true"){
	 					alert("对手修改成功");
	 					back();
	 				}else{
	 					alert("对手修改失败，请联系系统管理员");
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
