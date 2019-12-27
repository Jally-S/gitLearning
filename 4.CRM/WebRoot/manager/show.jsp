<%@page import="org.jvsun.dao.factory.PhotoDAOFactory"%>
<%@page import="org.jvsun.pojo.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <head>
    <title>查看合同照片</title>
  </head>
  
  <body>
	<% 
		int pid=Integer.parseInt(request.getParameter("pid"));
		List<PhotoPOJO> list = PhotoDAOFactory.getDAOInstance().findAllByContractId(pid);
		for(PhotoPOJO pojo:list){
			%>
			<img alt="合同照片" src="http://localhost/photo/<%=pojo.getPhotoName() %>"width="100" height="100">
			<br/>
			<a href="photo_del.jsp?pid=<%=pojo.getPhotoId() %>">删除合同照片</a>
			<%
		}
	%>
	
  </body>
 
</html>
