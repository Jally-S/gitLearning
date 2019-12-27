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
		<form name = "f">
			<input type="hidden" name="menuid" value="${pojo.menuId}" />
			<br />
			菜单名字：<input type="text" name="menuname" value="${pojo.menuName}" class="input"/><br />
		
			角色路径:&nbsp;&nbsp;&nbsp;&nbsp;：<input type="text" name="menupath" value="${pojo.menuPath}" class="input"/><br />
		
				
				
			菜单状态：<select name="isdelete"  class="input">
						<option value="1">可用</option>
						<option value="0">停用</option>
						
					</select>
					<br/>
			
			<input type="button" value="确认" onclick="update()" class="button border-main icon-search"/>
			<input type="button" value="返回" onclick="back()" class="button border-main icon-search"/>
		</form>
	</center>	
	</body>
	<script type="text/javascript">
		f.isDelete.value=${pojo.isDelete};//设置下拉列表的默认值
		
		function update(){
			var menuid = f.menuid.value;
			var menuname = f.menuname.value;
			var menupath = f.menupath.value;
		
			var isdelete = f.isdelete.value;
			
			 $(document).ready(function(){
			 	//设置提交的路径，和参数
				$.post("<%=path %>/MenuUpdate",
				{"menuid":menuid,"menuname":menuname,"menupath":menupath,"isdelete":isdelete},
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
	 				if(data == "true"){
	 					alert("菜单修改成功");
	 					back();
	 				}else{
	 					alert("菜单修改失败，请联系系统管理员");
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