<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.jvsun.dao.factory.*"%>
<%@page import="org.jvsun.pojo.*"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>新增合同</title>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.js"></script>
        <link rel="stylesheet" href="<%=path %>/css/pintuer.css">
	<link rel="stylesheet" href="<%=path %>/css/admin.css">
  </head>
  
  <body>
  
	<center>
		<form name = "com">
		<br/>
			客户名：
			<select name="customerName" class="input">
			<%
 				List<CustomerPOJO> list = CustomerDAOFactory.getDAOInstance().findAll(100, 1);
  				for(CustomerPOJO pojo:list){
  			%>
				<option value="<%=pojo.getCustomerId() %>"><%=pojo.getCustomerName() %></option>
			<%
				} 
			%>
			</select> <br/>
			
			职工名：
			<select name="workerName"class="input">
			<%
 				List<WorkerPOJO> listw = WorkerDAOFactory.getDAOInstance().findAll("",1,100, 1);
  				for(WorkerPOJO pojo:listw){
  			%>
				<option value="<%=pojo.getWorkerId() %>"><%=pojo.getWorkerName() %></option>
			<%
				} 
			%>
			</select> <br/>
			
			合同名：<input type="text" name="contractName"class="input"/><br>
			合同内容：<textarea rows="2" cols="3" name="contractContent"class="input"></textarea>
			<br/>
			拍照与否：<select name="isPhoto"class="input">
						<option value="0" selected="selected">尚未拍照</option>
						<option value="1">已经拍照</option>
					</select><br/>
			<input type="button" value="确认" onclick="add()"class="button border-main icon-search"/>
			<input type="reset" value="重置" class="button border-main icon-search"/>
			<input type="button" value="返回" onclick="back()"class="button border-main icon-search"/>
		</form>
	</center>	
  </body>
  <script type="text/javascript">
		function add(){
			var customerName=com.customerName.value;
			var workerName=com.workerName.value;
			var contractName=com.contractName.value;
			var contractContent=com.contractContent.value;
			var isPhoto=com.isPhoto.value;
			 $(document).ready(function(){
			 	//设置提交的路径，和参数
				$.post("<%=path%>/AddContract",
				{"customerName":customerName,"workerName":workerName,"contractName":contractName,"contractContent":contractContent,"isPhoto":isPhoto},
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
	 				if(data == "true"){
	 					alert("合同新增成功");
	 					back();
	 				}else{
	 					alert("合同新增失败，请联系系统管理员");
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
