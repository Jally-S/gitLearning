<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>读者注册</title>
</head>
<link href="css/register.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/register.js"></script>
<body>
	<%@include file="top.jsp"%>
	
	<div id="main">
		<form action="servlet/RegisterServlet" method="post" id="registerForm">
			<div class="navigation">
				当前位置:&nbsp;&nbsp;<a href="">读者管理</a>&nbsp;&gt;&nbsp;&nbsp;<a href="">读者档案管理</a>&nbsp;&gt;&nbsp;&nbsp;<a href="">添加读者信息</a>
			</div>
			<div class="clear"></div>
			<div class="item">
				<div class="item_title">
					用户名:
				</div>
				<div class="item_input">
					<input type="text" name="username" id="username" class="item_input_value"/>
				</div>
			</div>
			<div class="item">
				<div class="item_title">
					密码:
				</div>
				<div class="item_input">
					<input type="password" name="password" id="password" class="item_input_value"/>
				</div>
			</div>
			<div class="item">
				<div class="item_title">
					确认密码:
				</div>
				<div class="item_input">
					<input type="password" id="repassword" class="item_input_value"/>
				</div>
			</div>
			<div class="item">
				<div class="item_title">
					性别:
				</div>
				<div class="item_input">
					<input type="radio" class="item_input_radio" name="gender" value="男" checked="checked"/>
					<font class="item_input_radio_text">男</font>
					<input type="radio" class="item_input_radio" name="gender" value="女"/>
					<font class="item_input_radio_text">女</font>
				</div>
			</div>
			<div class="item">
				<div class="item_title">
					读者类型:
				</div>
				<div class="item_input_select">
					<select style="width:150px;height:30px;" name="typeid">
						<option value="1">普通读者</option>
						<option value="2">高级读者</option>
						<option value="3">VIP读者</option>
					</select>
				</div>
			</div>
			<div class="item">
				<div class="item_title">
					姓名:
				</div>
				<div class="item_input">
					<input type="text" id="name" name="name" class="item_input_value"/>
				</div>
			</div>
			<div class="item">
				<div class="item_title">
					证件号:
				</div>
				<div class="item_input">
					<input type="text" id="cardid" name="cardid" class="item_input_value"/>
				</div>
			</div>
			<div class="item">
				<div class="item_title">
					联系电话:
				</div>
				<div class="item_input">
					<input type="text" id="tel" name="tel" class="item_input_value"/>
				</div>
			</div>
			<div class="item">
				<div class="item_btn" id="submit">提   交</div>
				<div class="item_btn" id="reset">重   置</div>
			</div>
			<input type="hidden" name="method" value="add"/>
		</form>
	</div>
	
	<%@include file="footer.jsp"%>
</body>
</html>