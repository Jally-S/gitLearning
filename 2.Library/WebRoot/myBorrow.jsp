<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	request.setCharacterEncoding("utf-8");
 %>
 <%@ page import="com.hzit.entity.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>">
  </head>
  <link href="css/myBorrow.css" type="text/css" rel="stylesheet">  
  <script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="<%=path%>/js/myBorrow.js"></script>
<body>
	<%@include file="top.jsp"%>
	
	<div id="main">
		<div class="navigation">
				当前位置:&nbsp;<a href="servlet/BookServlet?method=getAll">首页</a>&nbsp;&gt;&nbsp;${reader.name}的借书列表
				<div id="readerBlock"><a href="servlet/LoginServlet?method=logout">退出</a></div>
		</div>
		<table border="0" class="table" cellspacing="0">
			<tr>
				<td>编号</td>
				<td>图书名称</td>
				<td>借书时间</td>
				<td>还书时间</td>
				<td>审核状态</td>
			</tr>
			<c:forEach items="${list }" var="obj">
			<tr>
				<td>${obj.id }</td>
				<td>${obj.book.name }</td>
				<td>${obj.borrowTime }</td>
				<td>${obj.returnTime }</td>
				<td>
					<c:choose>
						<c:when test="${obj.state==0}">
							未审核
						</c:when>
						<c:when test="${obj.state==1}">
							已借
						</c:when>
						<c:when test="${obj.state==2}">
							被拒绝
						</c:when>
						<c:when test="${obj.state==3}">
							已归还
						</c:when>
					</c:choose>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	
	<%@include file="footer.jsp"%>
</body>
</html>