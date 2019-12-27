<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	request.setCharacterEncoding("utf-8");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link href="css/return.css" type="text/css" rel="stylesheet">  
  <script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="<%=path%>/js/examine.js"></script>
<body>
	<%@include file="top.jsp"%>
	
	<div id="main">
		<div class="navigation">
			当前位置:&nbsp;&nbsp;<a href="servlet/BookServlet?method=getExamineBorrow">借书管理</a>
			<div id="readerBlock">欢迎回来:&nbsp;${admin.username}&nbsp;<a href="servlet/LoginServlet?method=logout">退出</a></div>
		</div>
		<table border="0" class="table" cellspacing="0">
			<tr>
				<td>编号</td>
				<td>图书编号</td>
				<td>图书名称</td>
				<td>读者姓名</td>
				<td>证件号码</td>
				<td>联系电话</td>
				<td>借书时间</td>
				<td>还书时间</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${list }" var="borrow">
			<tr>
				<td>${borrow.id }</td>
				<td>${borrow.book.id }</td>
				<td>${borrow.book.name }</td>
				<td>${borrow.reader.name }</td>
				<td>${borrow.reader.cardid }</td>
				<td>${borrow.reader.tel }</td>
				<td>${borrow.borrowTime }</td>
				<td>${borrow.returnTime }</td>
				<td>
					<a href="servlet/BookServlet?method=returnBook&bookid=${borrow.book.id }&adminid=${admin.id}&readerid=${borrow.reader.id}&borrowid=${borrow.id}">归还</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	
	<%@include file="footer.jsp"%>
</body>
</html>