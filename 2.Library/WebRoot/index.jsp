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
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <link href="css/index.css" type="text/css" rel="stylesheet">  
  <script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="<%=path%>/js/index.js"></script>
  <body>
  	
	<%@include file="top.jsp"%>
	
	
	<div id="main">
		<div class="navigation">
				当前位置:&nbsp;&nbsp;<a href="servlet/BookServlet?method=getAll">首页</a>
				<div id="readerBlock">欢迎回来&nbsp;:&nbsp;<a href="servlet/BookServlet?method=getBorrowByReaderId&readerid=${reader.id}">${reader.name}</a>&nbsp;&nbsp;<a href="servlet/LoginServlet?method=logout">退出</a></div>
		</div>
		<div class="img_block">
			<img src="images/main_booksort.gif" class="img" />
		</div>
		
		<table class="table" cellspacing="0">
			<tr>
				<td>编号</td>
				<td>图书名称</td>
				<td>作者</td>
				<td>出版社</td>
				<td>图书页数</td>
				<td>定价(元)</td>
				<td>书架</td>
				<td>图书类型</td>
				<td>借阅次数</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${list }" var="book">
				<tr>
					<td>${book.id }</td>
					<td>${book.name }</td>
					<td>${book.author }</td>
					<td>${book.publish }</td>
					<td>${book.pages }</td>
					<td>${book.price }</td>
					<td>${book.bookCase.name }</td>
					<td>${book.bookType.name }</td>
					<td>10</td>
					<td><a href="servlet/BookServlet?method=borrow&bookid=${book.id }&readerid=${reader.id}">借阅</a></td>
				</tr>
			</c:forEach>
			
		</table>
		<hr class="hr"/>
		<div id="pageControl">
			<div class="pageControl_item">每页<font id="dataPrePage">5</font>条数据</div>
			<div class="pageControl_item">最后一页</div>
			<div class="pageControl_item">下一页</div>
			<div class="pageControl_item"><font id="currentPage">${currentPage }</font>/<font id="pages">${pages }</font></div>
			<div class="pageControl_item">上一页</div>
			<div class="pageControl_item">首页</div>
		</div>
		
	</div>
	
	<%@include file="footer.jsp"%>
  </body>
</html>
