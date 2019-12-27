<%@page contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
		<link rel="stylesheet" href="<%=path %>/css/pintuer.css">
		<link rel="stylesheet" href="<%=path %>/css/admin.css">

	</head>
	<body>
		<center> 
			<form name="f"><br />
				<input type="hidden" name="reptId" value="${pojo.reptId}" />
				<input type="hidden" name="repairId" value="${pojo.repairId}"  />
				回执单名称：
				<input type="text" name="reptName" value="${pojo.reptName}" class="input" />
				<br />
				回执单描述：
				<input type="text" name="reptContent" value="${pojo.reptContent}" class="input" />
				<br />
				<input type="button" value="确认" onclick="update()"  class="button border-main icon-search"/>
				<input type="button" value="返回" onclick="back()"  class="button border-main icon-search"/>
			</form>
		</center>
	</body>
	<script type="text/javascript">
function update() {
	var reptId = f.reptId.value;
	var repairId = f.repairId.value;
	var reptName = f.reptName.value;
	var reptContent = f.reptContent.value;
	if (reptName == "") {
		alert("回执单名称不能为空");
	} else if (reptContent == "") {
		alert("回执单描述不能为空");
	} else {
		$(document).ready(function() {
			//设置提交的路径，和参数
				$.post("<%=path%>/RepairReturnUpd", {
					"reptId" : reptId,
					"repairId" : repairId,
					"reptName" : reptName,
					"reptContent" : reptContent
				}, function(data) {//Servlet执行完之后执行方法，data表示的servlet返回数据内容
							if (data == "true") {
								alert("修改成功");
								back();
							} else {
								alert("修改失败，请联系系统管理员");
							}
						});
			});
	}
}

function back() {
	opener.location.reload();

	window.close();//关闭当前页面
}
</script>
</html>