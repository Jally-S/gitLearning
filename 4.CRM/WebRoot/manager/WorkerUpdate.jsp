<%@page contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
%>
<html>
	<head>
	<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
	<link type="text/css" href="<%=path %>/css/css.css" rel="stylesheet"/>
	<link rel="stylesheet" href="<%=path %>/css/pintuer.css">
	<link rel="stylesheet" href="<%=path %>/css/admin.css">
	</head>
	<body>
		<form name = "f">
			<input type="hidden" name="workerId" value="${pojo.workerId}" />
			职工名字：<input type="text" name="workerName" value="${pojo.workerName}" class="input"/>
			职工性别:&nbsp;&nbsp;&nbsp;&nbsp;<select name="workerSex" class="input">
						<option value="1">男</option>
						<option value="0">女</option>
						</select>
			
			职工电话：<input type="text" name="workerTel" value="${pojo.workerTel}"class="input"/>
			职工地址：<input type="text" name="workerAdress" value="${pojo.workerAdress}"class="input"/>
			
			<input type="hidden" name=wAccount value="${pojo.wAccount}" class="input"/>
				
				
			在职状态：<select name="isdelete" class="input">
						<option value="1">在职</option>
						<option value="0">离职</option>
						
					</select>
					<br/>
			
			<input type="button" value="确认" onclick="update()"class="button border-main icon-search"/>
			<input type="button" value="返回" onclick="back()"class="button border-main icon-search"/>
		</form>
	</body>
	<script type="text/javascript">
		f.isDelete.value=${pojo.isDelete};//设置下拉列表的默认值
		f.workerSex.value = ${pojo.workerSex}
		function update(){
			var workerId = f.workerId.value;
			var workerName = f.workerName .value;
			var workerSex = f.workerSex.value;
			var workerTel = f.workerTel.value;
			var workerAdress = f.workerAdress.value;
			var wAccount = f.wAccount .value;
			var isdelete = f.isdelete.value;
			
			 $(document).ready(function(){
			 	//设置提交的路径，和参数
				$.post("<%=path %>/WorkerUpdate",
				{"workerId":workerId,"workerName":workerName,"workerSex":workerSex,"workerTel":workerTel,"workerAdress":workerAdress,"wAccount":wAccount,"isdelete":isdelete},
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