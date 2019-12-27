<%@page contentType="text/html; charset=utf-8"%>
<%@ page import="org.jvsun.pojo.*"%>
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
	<body onload="queryProduct()">
	<center>
		<form name = "f" ><br/>
			<span>已购产品</span><div id="showProduct" ></div>
					<br/>
			报修信息：<textarea rows="3" cols="20" name="repairContent" class="input"></textarea>
			<br/>
			<input type="button" value="确认" onclick="add()" class="button border-main icon-search"/>
			<input type="reset" value="重置"  class="button border-main icon-search"/>
			<input type="button" value="返回" onclick="back()" class="button border-main icon-search"/>
		</form>
	</center>	
	</body>
	<script type="text/javascript">
		function add(){
			var productName = f.productName.value;
			var repairContent = f.repairContent.value;
			//需要加上判空语句
			
			 $(document).ready(function(){
			 	//设置提交的路径，和参数
				$.post("<%=path %>/RepairAdd",
				{"productName":productName,"repairContent":repairContent},
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
	 				if(data == "true"){
	 					alert("新增成功");
	 					back();
	 				}else{
	 					alert("新增失败，请联系系统管理员");
	 				}
	 			});
			});
		}
		function queryProduct(){
			$(document).ready(function(){
			 	//设置提交的路径，和参数
				$.post("<%=path %>/SelectProduct",
				
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
	 				$("#showProduct").html(data);//显示Servlet返回的内容
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