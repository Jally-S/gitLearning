<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.jvsun.dao.factory.*"%>
<%@page import="org.jvsun.pojo.*"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>新增客户需求</title>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.js"></script>
    <link rel="stylesheet" href="<%=path %>/css/pintuer.css">
	<link rel="stylesheet" href="<%=path %>/css/admin.css">
  </head>
  
  <body>
	<center>
		<form name = "com"> <br/>
			客户名称：
			<select name="customerId" class="input">
			<%
 				List<CustomerPOJO> list = CustomerDAOFactory.getDAOInstance().findAll(100, 1);
  				for(CustomerPOJO pojo:list){
  			%>
				<option value="<%=pojo.getCustomerId() %>"><%=pojo.getCustomerName() %></option>
			<%
				} 
			%>
			</select> <br/>
			客户爱好：<input type="text" name="hobby" class="input"/>
			<br/>
			需求产品名：<input type="text" name="productName" class="input"/><br>
			<input type="button" value="确认" onclick="add()"class="button border-main icon-search"/>
			<input type="reset" value="重置" class="button border-main icon-search"/>
			<input type="button" value="返回" onclick="back()"class="button border-main icon-search"/>
		</form>
	</center>	
  </body>
  <script type="text/javascript">
		function add(){
			var customerId=com.customerId.value;
			var hobby=com.hobby.value;
			var productName=com.productName.value;
			 $(document).ready(function(){
			 	//设置提交的路径，和参数
				$.post("<%=path%>/AddNeed",
				{"customerId":customerId,"hobby":hobby,"productName":productName},
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
	 				if(data == "true"){
	 					alert("客户需求新增成功");
	 					back();
	 				}else{
	 					alert("客户需求新增失败，请联系系统管理员");
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
