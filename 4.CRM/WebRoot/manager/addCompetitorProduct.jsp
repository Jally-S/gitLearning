<%@page import="java.math.BigDecimal"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>新增竞争对手产品信息</title>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.js"></script>
    <link rel="stylesheet" href="<%=path %>/css/pintuer.css">
	<link rel="stylesheet" href="<%=path %>/css/admin.css">
  </head>
  
  <body>
  <% BigDecimal pid=new BigDecimal(request.getParameter("pid")); %>
  
	<center>
		<form name = "com">
			<input type="hidden" name="pid" value="<%=pid %>"/>
			对手产品名称：<br/>
			<input type="text" name="cname"class="input"/>
			<br/>
			对手产品级别：<br/>
					<select name="clas"class="input">
						<option value="1" selected="selected">一级竞争</option>
						<option value="2">二级竞争</option>
						<option value="3">三级竞争</option>
						<option value="4">四级竞争</option>
					</select>
			<br/>
			<input type="button" value="确认" onclick="add()"class="button border-main icon-search"/>
			<input type="reset" value="重置" class="button border-main icon-search"/>
			<input type="button" value="返回" onclick="back()"class="button border-main icon-search"/>
		</form>
	</center>	
  </body>
  <script type="text/javascript">
		function add(){
			var pid=com.pid.value;
			var cname=com.cname.value;
			var clas=com.clas.value;
			 $(document).ready(function(){
			 	//设置提交的路径，和参数
				$.post("<%=path%>/AddCompetitorProduct",
				{"pid":pid,"cname":cname,"clas":clas},
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
	 				if(data == "true"){
	 					alert("竞争对手产品新增成功");
	 					back();
	 				}else{
	 					alert("竞争对手产品新增失败，请联系系统管理员");
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
