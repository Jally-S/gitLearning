<%@page contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
%>
<html>
	<body>
		
		<form action="<%=path%>/CheckLoginId" method="post">
			账号：
			<input type="text" name="loginacount"/>
			<br />
			密码：
			<input type="password" name="password" />
			<br />
			<input type="submit" value="提交" />
		
		</form>

	</body>
	<script type="text/javascript">

</script>
</html>