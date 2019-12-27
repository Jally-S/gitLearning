<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.jvsun.pojo.*"%>
<%@page import="java.math.BigDecimal"%>
<%
String path = request.getContextPath();
%>
<html>
<head>
	<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
	<link type="text/css" href="<%=path %>/css/css.css" rel="stylesheet"/>
	<link rel="stylesheet" href="../css/pintuer.css">
	<link rel="stylesheet" href="../css/admin.css">
</head>
	<body>
<div class="panel admin-panel margin-top">
	<div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>修改个人信息</strong></div>
			<div >
			<form action="" id="showUserInfo" style="padding:20px;" name="f" ></form>
			</div>

</div>
	</body>
	<script type="text/javascript">
	QueryWorkerInfo();
	function WorkerInfoUpd(workerId){
	var workerId = f.workerId.value;
	var workerName = f.workerName.value;
	var workerSex = f.workerSex.value;
	var workerTel = f.workerTel.value;
	var workerAdress = f.workerAdress.value;
	if(confirm("确认修改？")){//参数workerId workerName workerSex workerTel workerAdress
				 $(document).ready(function(){
			 	//设置提交的路径，和参数
				$.post("<%=path %>/WorkerInfoUpd",{"workerId":workerId,"workerName":workerName,"workerSex":workerSex,
													"workerTel":workerTel,"workerAdress":workerAdress},
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
	 				if(data == "true"){
	 					alert("修改成功");
	 					QueryWorkerInfo();
	 				}else{
	 					alert("修改失败，请联系系统管理员");
	 				}
	 			});
			});
			}
	}
	function CustomerInfoUpd(customerId){
	var customerId = f.customerId.value;
	var customerName = f.customerName.value;
	var customerSex = f.customerSex.value;
	var customerAge = f.customerAge.value;
	var customerTel = f.customerTel.value;
	var customerAdress = f.customerAdress.value;
	var company = f.company.value;
	if(confirm("确认修改？")){//参数customerId customerName customerSex customerAge customerTel customerAdress company
				 $(document).ready(function(){
			 	//设置提交的路径，和参数
				$.post("<%=path %>/CustomerInfoUpd",{"customerId":customerId,"customerName":customerName,"customerSex":customerSex,
				"customerAge":customerAge,"customerTel":customerTel,"customerAdress":customerAdress,"company":company},
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
	 				if(data == "true"){
	 					alert("修改成功");
	 					QueryWorkerInfo();
	 				}else{
	 					alert("修改失败，请联系系统管理员");
	 				}
	 			});
			});
			}
	}
	function QueryWorkerInfo(){
			$(document).ready(function(){
			 	//设置提交的路径，和参数
				$.post("<%=path%>/UserInfo",
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
	 				$("#showUserInfo").html(data);//显示Servlet返回的内容
	 		});
			});
	
	}

	</script>
</html>
