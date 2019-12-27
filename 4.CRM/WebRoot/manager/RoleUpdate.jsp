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
		<form name = "f"><br/>
			<input type="hidden" name="roleId" value="${pojo.roleId}" />
			角色名字：<input type="text" name="roleName" value="${pojo.roleName}" class="input"/>
			<br/>
			角色标识:&nbsp;&nbsp;&nbsp;&nbsp;：<input type="text" name="roleMark" value="${pojo.roleMark}" class="input"/>
			<br/>角色状态：<select name="isdelete"  class="input">
						<option value="1">可用</option>
						<option value="0">停用</option>
					</select>
					<br/>
			<input type="button" value="确认" onclick="update()"/>
			<input type="button" value="返回" onclick="back()"/>
		</form>
	</center>	
	</body>
	<script type="text/javascript">
		f.isDelete.value=${pojo.isDelete};//设置下拉列表的默认值
		
		function update(){
			var roleId = f.roleId.value;
			var roleName = f.roleName.value;
			var roleMark = f.roleMark.value;
		
			var isdelete = f.isdelete.value;
			
			 $(document).ready(function(){
			 	//设置提交的路径，和参数
				$.post("<%=path %>/RoleUpdate",
				{"roleId":roleId,"roleName":roleName,"roleMark":roleMark,"isdelete":isdelete},
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
	 				if(data == "true"){
	 					alert("角色修改成功");
	 					back();
	 				}else{
	 					alert("角色修改失败，请联系系统管理员");
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