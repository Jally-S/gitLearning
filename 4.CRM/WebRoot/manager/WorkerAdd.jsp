<%@page contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
	<link type="text/css" href="<%=path %>/css/css.css" rel="stylesheet"/>
	<link rel="stylesheet" href="<%=path %>/css/pintuer.css">
	<link rel="stylesheet" href="<%=path %>/css/admin.css">
	</head>
	<body>
	<center>
		<form name="f">
			<input type="hidden" name="workerId" /><br />
				职工名字：<input type="text" name="workerName" class="input"/><br />
				职工性别：<br/><select name="workerSex" class="input">
					<option value="1">男</option>
					<option value="0">女</option>
				</select><br/>
				职工电话：<input type="text" name="workerTel" class="input"/><br />
				职工地址：<input type="text" name="workerAdress" class="input"/><br />
				职工账号：<input type="text" name= "wAccount "  class="input"/><br />
				在职状态：<br/><select name="isdelete" class="input">
					<option value="1">在职</option>
					<option value="0">离职</option>
				</select>
			<br/> <input type="button" value="确认" onclick="add()" class="button border-main icon-search"/> <input
				type="button" value="返回" onclick="back()" class="button border-main icon-search"/>
		</form>
		</center>
</body>
	<script type="text/javascript">
	
		function add(){
		
			var workerName = f.workerName .value;
			var workerSex = f.workerSex.value;
			var workerTel = f.workerTel.value;
			var workerAdress = f.workerAdress.value;
			var wAccount = f.wAccount .value;
			var isdelete = f.isdelete.value;
			
			 $(document).ready(function(){
			 	//设置提交的路径，和参数
			 	
				$.post("<%=path%>/WorkerAdd",
				{"workerName":workerName,"workerSex":workerSex,"workerTel":workerTel,"workerAdress":workerAdress,"wAccount":wAccount,"isdelete":isdelete},
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
	 				if(data == "true"){
	 					alert("增加成功");
	 					back();
	 				}else{
	 					alert("增加失败，请联系系统管理员");
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