<%@page contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	int repairId = Integer.parseInt(request.getParameter("repairId"));
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript" src="<%=path%>/js/jquery.js">
		<link type="text/css" href="<%=path%>/css/css.css" rel="stylesheet"/>
		<link rel="stylesheet" href="<%=path %>/css/pintuer.css">
		<link rel="stylesheet" href="<%=path %>/css/admin.css">
</script>
	</head>
	<body>
		<center>
			<form name="f">
			<br />
				<input type="hidden" name="repairId" value="<%=repairId%>" />
				回执单名称：&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" name="reptName"  class="input"/>
				<br />
				&nbsp;回执单维修描述：
				<textarea rows="2" cols="3" name="reptContent"class="input"></textarea>
				<br />
				<input type="button" value="确认" onclick="add()"  class="button border-main icon-search"/>
				<input type="reset" value="重置"  class="button border-main icon-search"/>
				<input type="button" value="返回" onclick="back()" class="button border-main icon-search" />
			</form>
		</center>
	</body>
	<script type="text/javascript">
function add() {
	var reptName = f.reptName.value;
	var reptContent = f.reptContent.value;
	var repairId = f.repairId.value;
	if (reptName == "") {
		alert("回执单名称不能为空");
	} else if (reptContent == "") {
		alert("回执单描述不能为空");
	} else {
		$(document).ready(function() {
			//设置提交的路径，和参数
				$.post("<%=path%>/RepairReturnAdd", {
					"reptName" : reptName,
					"reptContent" : reptContent,
					"repairId" : repairId
				}, function(data) {//Servlet执行完之后执行方法，data表示的servlet返回数据内容
							if (data == "1") {
								alert("维修成功");
								back();
							} else {
								alert("维修失败，请联系系统管理员");
							}
						});
			});
	}

}

function back() {
	opener.location.reload();
	//window.dialogArguments.query(0);//刷新之前页面 
	window.close();//关闭当前页面
}
</script>
</html>