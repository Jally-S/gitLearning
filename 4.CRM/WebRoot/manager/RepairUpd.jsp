<%@page contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<script type="text/javascript" src="<%=path %>/js/jquery-1.8.3.js"></script>
		<link rel="stylesheet" href="<%=path %>/css/pintuer.css">
	<link rel="stylesheet" href="<%=path %>/css/admin.css">
	</head>
	<body >
	<center>
		<form name = "f"><br/>
			<input type="hidden" name = "repairId" value = "${rpojo.repairId}">
			<br/>
			报修信息：<input type="text" name="repairContent" value = "${rpojo.repairContent}" class="input"/>
			<br/>
			<input type="button" value="确认" onclick="update()" class="button border-main icon-search"/>
			<input type="button" value="返回" onclick="back()" class="button border-main icon-search"/>
		</form>
	</center>	
	</body>
	<script type="text/javascript">
		function update(){
			var repairId = f.repairId.value;
			var repairContent = f.repairContent.value;
			 $(document).ready(function(){
			 	//设置提交的路径，和参数
				$.post("<%=path %>/RepairUpd",
				{"repairId":repairId,"repairContent":repairContent},
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
	 				if(data == "true"){
	 					alert("修改成功");
	 					back();
	 				}else{
	 					alert("修改失败，请联系系统管理员");
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