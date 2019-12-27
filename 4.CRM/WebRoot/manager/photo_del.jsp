<%@page import="org.jvsun.dao.factory.PhotoDAOFactory"%>
<%@page import="org.jvsun.pojo.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
  </head>
  
  	<body>
		<%
	int pid=Integer.parseInt(request.getParameter("pid"));
    boolean flag = PhotoDAOFactory.getDAOInstance().doDel(pid);
	if(flag){
		out.println("<script>alert('删除了');window.close();</script>");
	}else{
		out.println("<script>alert('出错了');window.close();</script>");
	}
	%>
	</body>
</html>
